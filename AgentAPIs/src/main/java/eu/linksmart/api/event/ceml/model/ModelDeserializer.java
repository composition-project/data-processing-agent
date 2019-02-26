package eu.linksmart.api.event.ceml.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import eu.linksmart.api.event.ceml.evaluation.TargetRequest;
import eu.linksmart.services.utils.serialization.DefaultSerializerDeserializer;
import eu.linksmart.services.utils.serialization.DeserializerMode;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by José Ángel Carvajal on 19.07.2016 a researcher of Fraunhofer FIT.
 */
public class ModelDeserializer extends DeserializerMode<Model> {
    private static final ObjectMapper mapper = (ObjectMapper) new DefaultSerializerDeserializer().getParser();
    private static final CollectionType collectionType =TypeFactory.defaultInstance().constructCollectionType(List.class, TargetRequest.class);
    //private static final CollectionType learnersListType =TypeFactory.defaultInstance().constructCollectionType(List.class,MultiLayerNetwork.class);
    private static final MapType mapType =TypeFactory.defaultInstance().constructMapType(Map.class, String.class,Object.class);
    protected static final Map<String,JavaType> learners = new Hashtable<>();

    static public void registerModule(Module module){
        mapper.registerModule(module);
    }
    static public void setLearnerType(String name, JavaType type){
        learners.put(name,type);
    }

    @Override
    public Model deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);


       String name = loadName(node);
       return constructModel(node,name,loadTargets(node),loadParameters(node),loadLerner(node,name), loadInitialConfusionMatrix(node), loadInitialSamplesMatrix(node));

       //else
         //   throw new IOException("The field Parameters is a mandatory field!");


    }
    protected String loadName(JsonNode node) throws IOException{
        String name =  node.hasNonNull("Name")?node.get("Name").textValue():node.get("name").textValue();
        if(!loadClass("eu.linksmart.services.event.ceml.models."+name) && !loadClass(name))
            throw new IOException("Loaded class: "+name+" or "+"eu.linksmart.services.event.ceml.models."+name+ " do not exist!");
        return name;
    }
    protected  List<TargetRequest>  loadTargets(JsonNode node) throws IOException{
        List<TargetRequest> targetRequests;

        if(node.hasNonNull("Targets"))
            targetRequests = mapper.reader(collectionType).readValue(node.get("Targets"));

        else if(node.hasNonNull("targets"))
            targetRequests = mapper.reader(collectionType).readValue(node.get("targets"));

        else
            throw new IOException("The field Targets is a mandatory field!");

        return targetRequests;
    }
    protected  long[][]  loadInitialConfusionMatrix(JsonNode node) throws IOException{
        long[][] confusionMatrix = null;
        if(node.hasNonNull("InitialConfusionMatrix")) {
            confusionMatrix = mapper.readValue(node.get("InitialConfusionMatrix").toString(),long[][].class);

        }else if (node.hasNonNull("initialConfusionMatrix"))
            confusionMatrix = mapper.readValue(node.get("initialConfusionMatrix").toString(),long[][].class);

        return confusionMatrix;
    }
    protected  long[][]  loadInitialSamplesMatrix(JsonNode node) throws IOException{
        long[][] confusionMatrix = null;
        if(node.hasNonNull("InitialSamplesMatrix")) {
            confusionMatrix = mapper.readValue(node.get("InitialSamplesMatrix").toString(),long[][].class);

        }else if (node.hasNonNull("initialSamplesMatrix"))
            confusionMatrix = mapper.readValue(node.get("initialSamplesMatrix").toString(),long[][].class);

        return confusionMatrix;
    }
    protected Map<String,Object> loadParameters(JsonNode node) throws IOException{
        Map<String,Object> parameters = new Hashtable<>();
        if(node.hasNonNull("Parameters"))
            parameters = mapper.reader(mapType).readValue(node.get("Parameters"));

        else if(node.hasNonNull("parameters"))
            parameters = mapper.reader(mapType).readValue(node.get("parameters"));


        return parameters;
    }
    protected Object loadLerner(JsonNode node, String name) throws IOException{
        Object learner = null;
        if(node.hasNonNull("Learner")) {
            JavaType learnerType = learners.get(name);
            learner = mapper.reader(learnerType).readValue(node.get("Learner"));
        } else if(node.hasNonNull("learner")) {
            JavaType learnerType = learners.get(name);
            learner = mapper.reader(learnerType).readValue(node.get("learner"));
        }
        return learner;
    }
    protected Model constructModel(JsonNode node, String name, List<TargetRequest> targetRequests, Map<String,Object> parameters, Object learner, long[][] initialConfusionMatrix, long[][] initialSamplesMatrix)throws IOException {
        try {
            Model model = Model.factory(name,targetRequests,parameters,learner);
            if(model.isClassifier()) {
                if(initialConfusionMatrix!=null)
                    model.getParameters().put("initialConfusionMatrix", initialConfusionMatrix);
                if(initialSamplesMatrix!=null)
                    model.getParameters().put("initialSamplesMatrix", initialSamplesMatrix);
            }
            return model;
        } catch (Exception e) {
            throw new IOException(e);
        }
    }
    private boolean loadClass(String className){
        try {
            Class.forName(className);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
