package de.fraunhofer.fit.event.ceml.type.requests.builded;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.fraunhofer.fit.event.ceml.CEML;
import de.fraunhofer.fit.event.ceml.type.requests.ModelStructure;
import eu.linksmart.api.event.datafusion.CEPEngine;
import eu.linksmart.api.event.datafusion.CEPEngineAdvanced;
import eu.linksmart.gc.utils.gson.GsonSerializable;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import weka.classifiers.Classifier;
import weka.core.Instance;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by angel on 26/11/15.
 */
public class Model implements Serializable {
    private LearningRequest origin =null;
    private String name;
    private Class nativeType;
    private Object lerner;
    @JsonPropertyDescription("Algorithm use to build the model")
    @JsonProperty(value = "Type")
    protected String type;
    public Model() {
        super();
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getNativeType() {
        return nativeType;
    }

    public void setNativeType(Class nativeType) {
        this.nativeType = nativeType;
    }

    public Object getLerner() {
        return lerner;
    }

    public void setLerner(Object lerner) {
        this.lerner = lerner;
    }

    private void initialize() throws ClassNotFoundException, IllegalAccessException, InstantiationException {


        lerner = Class.forName(type).newInstance();

        nativeType = lerner.getClass();

        //Hardcode HACK to set defaults, initialization API missing
       /* if(objectType.equals( "weka.classifiers.functions.SGD"))
            ((SGD)learningObjects.get(objectName)).setLossFunction(new SelectedTag(SGD.SQUAREDLOSS, SGD.TAGS_SELECTION));
        */



    }
    public void build(LearningRequest origin) throws Exception {
        this.origin =origin;
        initialize();
         /*           TODO: do this with reflection
          Method mth = getMethod(
                    lerner.getClass(),
                    "buildClassifier",
                     dataStructure.getInstances()
            );
            if(mth==null)
                return false;
            try {
                mth.invoke(lerner, dataStructure.getInstances());
            } catch (Exception e) {
                loggerService.error(e.getMessage(),e);
                return false;
            }*/

            ((Classifier)lerner).buildClassifier(origin.getData().getInstances());
        insertInCEPEngines();
    }
    public int insertInCEPEngines(){
        int n=0;
        for (CEPEngine dfw: CEPEngine.instancedEngines.values()      ) {
            CEPEngineAdvanced extended = dfw.getAdvancedFeatures();
            if(extended!=null) {
                extended.insertObject(name, this);
                n++;
            }

        }
        return n;
    }
    public String classify(Object... args){
        if(args.length==origin.getData().getAttributesStructures().size()-1) {
            Instance instance=null;
            Map<String, Object> aux = new Hashtable<>();
            for(int i=0;i<args.length;i++) {
                aux.put(origin.getData().getAttributesStructures().get(i).getAttributeName(), args[i]);
                instance = CEML.populateInstance(aux, origin);

            }
            int i = (int) CEML.predict(lerner, instance);
            return origin.getData().getLearningTarget().value(i);

        }
        return null;
    }
   /* public String classify(Entry... args) {

        Instance instance = null;
        Map<String, Object> aux = new Hashtable<>();
        for (int i = 0; i < args.length; i++) {
            aux.put(args[i].getName(), args[i].getValue());
            instance = CEML.populateInstance(aux, origin);

        }
        int i = (int) CEML.predict(lerner, instance);
        return origin.getData().getAttributesStructures().get(i).getAttributeName();


    }
    public String classify( Object[] args, String name) {

        Instance instance = null;
        Map<String, Object> aux = new Hashtable<>();

        aux.put(name,args);
        instance = CEML.populateInstance(aux, origin);

        int i = (int) CEML.predict(lerner, instance);
        return origin.getData().getAttributesStructures().get(i).getAttributeName();


    }
    public String classify( Entry entry) {

        Instance instance = null;
        Map<String, Object> aux = new Hashtable<>();

        aux.put(entry.getName(),entry.getValue());
        instance = CEML.populateInstance(aux, origin);

        int i = (int) CEML.predict(lerner, instance);
        return origin.getData().getAttributesStructures().get(i).getAttributeName();


    }*/

    public void reBuild(Model request){
        /*TODO: chnaging configuration of the model */

    }

}
