package eu.linksmart.services.event.core;

import eu.linksmart.services.event.connectors.MqttIncomingConnectorService;


import eu.linksmart.services.event.connectors.FileConnector;
import eu.linksmart.services.event.connectors.Observers.EventMqttObserver;
import eu.linksmart.services.event.connectors.Observers.StatementMqttObserver;
import eu.linksmart.services.event.feeder.EventFeeder;
import eu.linksmart.services.event.feeder.StatementFeeder;
import eu.linksmart.api.event.types.impl.BootstrappingBean;
import eu.linksmart.services.event.intern.DynamicConst;
import eu.linksmart.services.event.intern.Utils;
import eu.linksmart.api.event.components.CEPEngine;
import eu.linksmart.api.event.components.CEPEngineAdvanced;
import eu.linksmart.services.utils.configuration.Configurator;
import eu.linksmart.services.event.intern.Const;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.List;

/**
 * Created by J. Angel Caravajal on 06.10.2014.
 *
 */
public class DataProcessingCore {

 //   protected static List<Feeder> feeders = new ArrayList<>();

    protected static MqttIncomingConnectorService mqtt =null;
    private DataProcessingCore() {
    }

    public static boolean isActive() {
        return active;
    }

    protected static boolean active =false;
    protected static boolean started =false;
    protected transient static Configurator conf;
    protected transient static  Logger loggerService;

    public static void run(String args){
        if(!started) {
            init(args);
            statusLoop();
        }
    }

    public static boolean start(String args){
        if(!started) {
            Boolean ret = init(args);
            new Thread(() -> statusLoop()).start();
            return ret;
        }
        return false;

    }
    static protected void statusLoop(){
        active = true;
        while (active){

            active = mqtt.isUp();

            if(active) {
                loggerService.info("The Agent with ID "+DynamicConst.getId()+" is alive");
                int hb = 5000;
                try {
                     hb=conf.getInt(Const.LOG_DEBUG_HEARTBEAT_TIME_CONF_PATH);

                } catch (Exception ignored) {

                }
                try {

                    Thread.sleep(hb);
                } catch (Exception e) {
                    loggerService.error(e.getMessage(), e);
                }
            }
        }
    }
    private static void initConf(String args){
        if(args != null)
            Configurator.addConfFile(args);
        else if(Utils.fileExists(Const.APPLICATION_CONFIGURATION_FILE))
            Configurator.addConfFile(Const.APPLICATION_CONFIGURATION_FILE);

        conf = Configurator.getDefaultConfig();

        loggerService = Utils.initLoggingConf(DataProcessingCore.class);
        if(args != null) {
            loggerService.info("Loading configuration form file " + args);

        }else if(Utils.fileExists(Const.APPLICATION_CONFIGURATION_FILE)) {
            loggerService.info("Loading configuration form file " + Const.APPLICATION_CONFIGURATION_FILE);
        }else
            loggerService.info("No configuration conf.cfg file in the class path or as argument at start. Only the defaults values are used");
        String idPath= conf.getString(Const.ID_CONF_PATH);
        if("*".equals(idPath))
            DynamicConst.setIsSet(true);
        else
            DynamicConst.setId(conf.getString(Const.ID_CONF_PATH));
        DynamicConst.setWill(conf.getString(Const.WILL_MESSAGE).replace("<id>",DynamicConst.getId()));
        DynamicConst.setWillTopic(conf.getString(Const.WILL_TOPIC)+"/"+DynamicConst.getId()+"/");
    }

    protected static synchronized boolean init(String args){
        started =true;
        initConf(args);


        loggerService.info("The Agent streaming core version "+Utils.getVersion()+" is starting with ID: " + DynamicConst.getId());

        initCEPEngines();
        initForceLoading();
        boolean success = initFeeders();

        return success;
    }

    private static void initForceLoading() {
        if(conf.containsKey(Const.ADDITIONAL_CLASS_TO_BOOTSTRAPPING)) {
            String[] modules = conf.getStringArray(Const.ADDITIONAL_CLASS_TO_BOOTSTRAPPING);
            loggerService.info("Loading following extensions "+ Arrays.toString(modules));
            Arrays.stream(modules).forEach(cls -> {
                try {
                    if (!"".equals(cls)) {
                        Class c = Class.forName(cls);
                        loggerService.info("Extension: " + c.getSimpleName() + " loaded");
                    }
                } catch (ClassNotFoundException e) {
                    loggerService.error(e.getMessage(), e);
                }
            });
        }
    }


    private static boolean initFeeders() {

        // loading of feeders
        //IncomingConnector mqtt = null;
        try {

            Class.forName(EventFeeder.class.getCanonicalName());
            Class.forName(StatementFeeder.class.getCanonicalName());
            Class.forName(BootstrappingBean.class.getCanonicalName());
            mqtt = MqttIncomingConnectorService.getReference(DynamicConst.getWill(),DynamicConst.getWillTopic());

            if(conf.getList(Const.PERSISTENT_DATA_FILE) != null ) {
                FileConnector fileFeeder = new FileConnector((String[]) conf.getList(Const.PERSISTENT_DATA_FILE).toArray(new String[conf.getList(Const.PERSISTENT_DATA_FILE).size()]));

                fileFeeder.loadFiles();
            }
            if(conf.getList(Const.PERSISTENT_DATA_DIRECTORY) != null ) {
                FileConnector directoryFeeder = new FileConnector((String[]) conf.getList(Const.PERSISTENT_DATA_DIRECTORY).toArray(new String[conf.getList(Const.PERSISTENT_DATA_DIRECTORY).size()]));

                directoryFeeder.loadFiles();
            }

            if(conf.getBoolean(Const.START_MQTT_STATEMENT_API))
                mqtt.addAddListener(conf.getString(Const.STATEMENT_INOUT_BROKER_CONF_PATH),conf.getString(Const.STATEMENT_INOUT_BASE_TOPIC_CONF_PATH)+"#", new StatementMqttObserver(conf.getString(Const.STATEMENT_INOUT_BASE_TOPIC_CONF_PATH)+"#"));
            //
            conf.getList(Const.EVENT_IN_TOPIC_CONF_PATH).stream().forEach(i->{
                try {
                    mqtt.addAddListener(conf.getString(Const.EVENTS_IN_BROKER_CONF_PATH), i.toString(), new EventMqttObserver(i.toString()));
                } catch (Exception e) {
                    loggerService.error(e.getMessage(),e);
                }
            });



        } catch (Exception e) {
            loggerService.error(e.getMessage(),e);
        }

        if(mqtt!=null&& !mqtt.isUp()){
            loggerService.error("The feeders couldn't start! Agent now is stopping");
            return false;
        }


        return true;
    }

    private static void initCEPEngines() {
        // loading the CEP engines
        for (Object engines: conf.getList(Const.CEP_ENGINES_PATH))
            try {
                Class.forName(engines.toString());
            } catch (ClassNotFoundException e) {
                loggerService.error(e.getMessage(),e);
            }
        //initializing engines
        for (CEPEngine dfw: CEPEngine.instancedEngines.values()  ) {
            List pkgList= conf.getList(Const.AdditionalImportPackage);
            for (Object pkgName : pkgList    ) {

                try {
                    CEPEngineAdvanced dfwExtensions =dfw.getAdvancedFeatures();
                    if(dfwExtensions != null)
                        dfwExtensions.loadAdditionalPackages(pkgName.toString());
                } catch (Exception e) {
                    loggerService.error(e.getMessage(),e);
                }
            }

        }
        intoCEPTypes();

    }
    protected static void intoCEPTypes() {

        List classes =conf.getList(Const.FeederPayloadClass);
        List aliases =conf.getList(Const.FeederPayloadAlias);

        if(classes.size()!=aliases.size())
            loggerService.error("The configuration parameters of "
                    +Const.FeederPayloadAlias+" "
                    +Const.FeederPayloadClass+" do not match");

        for (CEPEngine dfw: CEPEngine.instancedEngines.values()) {
            for(int i=0; i<classes.size();i++) {
                try {
                    Class aClass = Class.forName(classes.get(i).toString());
                    dfw.addEventType(aliases.get(i).toString(), aClass );
                } catch (ClassNotFoundException e) {
                    loggerService.error(e.getMessage(), e);
                }
            }

        }
    }
}
