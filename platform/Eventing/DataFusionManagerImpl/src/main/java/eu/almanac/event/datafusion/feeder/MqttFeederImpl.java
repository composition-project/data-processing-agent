package eu.almanac.event.datafusion.feeder;

import eu.almanac.event.datafusion.intern.Const;
import eu.almanac.event.datafusion.intern.Utils;
import eu.linksmart.api.event.datafusion.*;
import eu.linksmart.api.event.datafusion.core.EventFeederLogic;
import eu.linksmart.gc.utils.configuration.Configurator;
import eu.linksmart.gc.utils.logging.LoggerService;
import eu.linksmart.gc.utils.mqtt.broker.StaticBroker;
import eu.linksmart.gc.utils.mqtt.types.MqttMessage;
import eu.linksmart.gc.utils.mqtt.types.Topic;
import org.antlr.v4.runtime.misc.NotNull;
import org.eclipse.paho.client.mqttv3.*;

import java.net.MalformedURLException;
import java.util.*;

/**
 * Created by Caravajal on 22.05.2015.
 */
public abstract class MqttFeederImpl implements Runnable, Feeder, EventFeederLogic, Observer {

    protected Map<String,DataFusionWrapper> dataFusionWrappers = new HashMap<>();
    protected LoggerService loggerService = Utils.initDefaultLoggerService(this.getClass());
    protected Configurator conf =  Configurator.getDefaultConfig();
    protected StaticBroker brokerService= null;
    protected Map<Topic,Class> topicToClass= new Hashtable<Topic,Class>();
    protected Map<String,String> classToAlias= new Hashtable<String, String>();
    @NotNull
    protected static Boolean toShutdown = false;
    protected long debugCount=0;

    protected Thread thisTread;
    static protected @NotNull Boolean down =false;

    public MqttFeederImpl(String brokerName, String brokerPort, String topic) throws MalformedURLException, MqttException {

        brokerService = new StaticBroker(brokerName,brokerPort);
        brokerService.addListener(topic,this);
        thisTread = new Thread(this);
        thisTread.start();
        try {
            LoadTypesIntoEngines();
        } catch (InstantiationException e) {
            loggerService.error(e.getMessage(),e);
        }

    }




    @Override
    public boolean dataFusionWrapperSignIn(DataFusionWrapper dfw) {
        dataFusionWrappers.put(dfw.getName(), dfw);

        //TODO: add code for the OSGi future
        return true;
    }

    @Override
    public boolean dataFusionWrapperSignOut(DataFusionWrapper dfw) {
        dataFusionWrappers.remove(dfw.getName());

        //TODO: add code for the OSGi future
        return true;
    }

    
    @SuppressWarnings("SynchronizeOnNonFinalField")
    public @NotNull boolean isDown(){
        @NotNull
        boolean tmp ;

        synchronized (down) {

            tmp = down;
        }

        return tmp;
    }

    @SuppressWarnings("SynchronizeOnNonFinalField")
    @Override
    public void run(){
        while (!down) {
            synchronized (toShutdown) {
                if (toShutdown  ) {



                    for (DataFusionWrapper i : dataFusionWrappers.values())
                        i.destroy();



                    brokerService.removeListener(this);
                    try {
                        brokerService.destroy();
                    } catch (Exception e) {
                        loggerService.error(e.getMessage(),e);
                    }/**
	 * The feeder do not have an awareness of which engines are available. <p>
	 * For the feeder is enable to interact with a Data Fusion Engine,
	 * the wrapper of the engine has to explicitly subscribe to the feeder as a Data Fusion engine.<p>
	 * Doing so through this function
	 * @param dfw is the {@link DataFusionWrapper} which what to be subscribed.
	 *
	 * @return <code>true</code> in a successful subscription, <code>false</code> otherwise.
	 * */

                    loggerService.info(this.getClass().getSimpleName() + " logged off");

                    synchronized (down) {
                        down = true;
                    }

                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
               loggerService.error(e.getMessage(),e);
            }
        }

    }
    @Override
    public boolean subscribeToTopic(String topic) {

          brokerService.addListener(topic,this);


        return true;
    }




    @Override
    public void update(Observable topic, Object mqttMessage)  {

        debugCount=(debugCount+1)%Long.MAX_VALUE;
        if(debugCount%conf.getInt(FeederConst.LOG_DEBUG_NUM_IN_EVENTS_REPORTED_CONF_PATH) == 0)
            loggerService.info(Utils.getDateNowString() + " message arrived with topic: " + ((MqttMessage) mqttMessage).getTopic());


        mangeEvent(((MqttMessage)mqttMessage).getTopic(), ((MqttMessage)mqttMessage).getPayload() );

    }



    protected abstract void mangeEvent(String topic, byte[] rawEvent);
    protected void LoadTypesIntoEngines() throws  InstantiationException {
        List topics =conf.getList(Const.FeederPayloadTopic);
        List classes =conf.getList(Const.FeederPayloadClass);
        List aliases =conf.getList(Const.FeederPayloadAlias);

        if(classes.size()!=aliases.size()&&aliases.size()!=topics.size())
            throw new InstantiationException(
                    "The configuration parameters of "
                            +Const.FeederPayloadAlias+" "
                            +Const.FeederPayloadClass+" "
                            +Const.FeederPayloadTopic+" do not match"
            );
        for (DataFusionWrapper dfw:DataFusionWrapper.instancedEngines.values()) {
            for(int i=0; i<classes.size();i++) {
                try {
                    Object aClassObject = Class.forName(classes.get(i).toString()).newInstance();
                    topicToClass.put(new Topic(topics.get(i).toString()),aClassObject.getClass());
                    classToAlias.put(aClassObject.getClass().getCanonicalName(),aliases.get(i).toString());
                    dfw.addEventType(aliases.get(i).toString(), aClassObject);
                } catch (ClassNotFoundException|IllegalAccessException e) {
                    loggerService.error(e.getMessage(), e);
                }
            }

        }
    }
}