package eu.linksmart.services.event.cep.tooling;

import eu.almanac.event.datafusion.utils.payload.IoTPayload.IoTEntityEvent;
import eu.almanac.event.datafusion.utils.payload.IoTPayload.IoTProperty;
import eu.almanac.ogc.sensorthing.api.datamodel.Observation;
import eu.linksmart.api.event.types.EventEnvelope;
import eu.linksmart.services.payloads.ogc.sensorthing.linked.ObservationImpl;
import eu.linksmart.services.utils.function.Utils;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateUtils;
//import eu.almanac.ogc.sensorthing.api.datamodel.*;
//import it.ismb.pertlab.ogc.sensorthings.api.datamodel.Observation;
//import it.ismb.pertlab.ogc.sensorthings.api.datamodel.Sensor;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tools {
    static private Map<String, Object> variables= new HashMap<>();
    static public Random Random = new Random();

    static public long getMilliseconds(Date date){
        return date.getTime();
    }


    static public String lastObservationOfProperty(IoTEntityEvent entity, String property ){

      return  entity.getProperties(property).getIoTStateObservation(0).getValue();
    }
    static public String lastObservationLikeProperty(IoTEntityEvent entity, String property ){

        for (IoTProperty p: entity.getProperties())
            if(p.getAbout().contains(property)) {

                return p.getIoTStateObservation().get(0).getValue();
            }

        return null;
    }
    static public String generateRandomAbout(){

        return UUID.randomUUID().toString().replace("-","_").replace("#","_");
    }

    static public Observation ObservationFactory(Object event, String resultType, String StreamID, String sensorID, long time){
        Observation ob = Observation.factory(event,resultType,StreamID,sensorID,time);
         /*if(Configurator.getDefaultConfig().getBoolean(Const.SIMULATION_EXTERNAL_CLOCK))
           try {
                if(ob.getPhenomenonTime().after(getDateNow()))
                    EsperEngine.getEngine().setEngineTimeTo(ob.getDate());
            } catch (Exception e) {
                LogManager.getLogger(Tools.class).error(e.getMessage(),e.getCause());
            }*/
        return ob;

    }

    static public String getIsoTimeFormat(){
        return Utils.isoFormatMSTZ.toString();
    }
    static public String getEsperTimeFormat(){
        return Utils.getDateFormat().toString();
    }
    static private DateFormat dateFormat =null;

    static private DateFormat getDateFormat(String timeFormat, String gmt){

        if(dateFormat == null){
            TimeZone tz = TimeZone.getTimeZone(gmt);
            dateFormat  = new SimpleDateFormat(getIsoTimeFormat());
            dateFormat.setTimeZone(tz);
        }
        if (!dateFormat.getTimeZone().getID().equals(gmt)){
            TimeZone tz = TimeZone.getTimeZone(gmt);
            dateFormat.setTimeZone(tz);
        }


        return dateFormat;

    }
    static public String getDateNowString(){
        return getDateFormat(getIsoTimeFormat(), "UTC").format(new Date());
    }
    static private String toTimestamp(Date date){

        return getDateFormat(getEsperTimeFormat(), "UTC").format(date);

    }
    static public String hashIt(String string){
        MessageDigest SHA256 = null;
        try {
            SHA256 = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return (new BigInteger(1,SHA256.digest((string).getBytes()))).toString();
    }


    static private Map<String, Map> used = new Hashtable<String,Map>();
    static public boolean hadBeanUsed(String queryName, Object[] objects){
        boolean hadBeanUsed ;
        if(!used.containsKey(queryName))
            used.put(queryName,null);
        if(used.get(queryName)==null) {
            used.put(queryName,new Hashtable());
            for (Object object: objects)
                used.get(queryName).put(object,object);

            hadBeanUsed = false;
        }else {
            hadBeanUsed = false;
            for (int i = objects.length-1; i >0 && !hadBeanUsed; i--)
                hadBeanUsed = used.get(queryName).containsKey(objects[i]);

        }

        return hadBeanUsed;
    }
    static public boolean isTimeContinuous(EventEnvelope[] events){
        EventEnvelope previous= null;

        for (EventEnvelope event: Arrays.asList(events)) {

            if (previous!=null && !DateUtils.addHours(previous.getDate(),2 ).after(event.getDate()))
                return false;

            previous = event;

        }
        return true;
    }
    static public boolean isTimeContinuous(EventEnvelope[][] eventss){
        EventEnvelope previous= null;

        for(EventEnvelope[] events: Arrays.asList(eventss))
            if(!isTimeContinuous(events))
                return false;
        return true;
    }
    static public boolean isTimeContinuous(EventEnvelope[] events1,EventEnvelope[] events2,boolean inBetweenOnly){

        return (inBetweenOnly || (isTimeContinuous(events1) && isTimeContinuous(events2)) )&&
                (DateUtils.addHours(  events1[events1.length-1].getDate(),2 ).after(  events2[0].getDate()) &&
                        events1[events1.length-1].getDate().before(events2[0].getDate())
                );


    }
    static public boolean isTimeContinuous(EventEnvelope[] events1,EventEnvelope[] events2){

        return isTimeContinuous(events1,events2,false);


    }
    static public boolean isTimeContinuous(Object events1,Object events2) {
        return events1 instanceof EventEnvelope[] && events2 instanceof EventEnvelope[] && isTimeContinuous((EventEnvelope[]) events1, (EventEnvelope[]) events2);
    }
    static public boolean isTimeContinuous(Object events1,Object events2, Object inBetweenOnly) {
        return events1 instanceof EventEnvelope[] && events2 instanceof EventEnvelope[] && inBetweenOnly instanceof Boolean && isTimeContinuous((EventEnvelope[]) events1, (EventEnvelope[]) events2,(boolean)inBetweenOnly);
    }
    static public EventEnvelope[] flattArrays(EventEnvelope[][] eventss){


        EventEnvelope[] result  =null;
        if(eventss!=null && eventss.length>0) {
            if (eventss.length > 1) {
                for (int i = 1; i < eventss.length; i++)
                    result =  ArrayUtils.addAll(result, eventss[i]);
            }
            else result = eventss[0];
        }

            return result;
    }

    static public Object[] addAll(Object o, Object o2){
        if(o instanceof Object[] && o2 instanceof Object[] ){
            return ArrayUtils.addAll((Object[])o,(Object[])o2);
        }
        if(o instanceof List ){
            return addAll(((List) o).toArray(),  o2);

        }
        if(o2 instanceof List ){
            return addAll(o,  ((List) o2).toArray());

        }
        if(o instanceof Object[] ){
            return ArrayUtils.addAll((Object[])o, Collections.singletonList(o2));
        }
        if(o2 instanceof Object[] ){
            return ArrayUtils.addAll( new Object[]{o}, (Object[])o2) ;
        }
        return new Object[]{o,o2};
    }
    static public Object[] removeAll(Object o, Object o2){
        List<Object> list=null, list2=null, result;
        if(o instanceof Object[]){
             list= Arrays.asList((Object[])o);
        }else if(o instanceof List){
            list= (List<Object>)o;
        }

        if(o2 instanceof Object[]){
            list2= Arrays.asList((Object[])o2);
        }else if(o2 instanceof List){
            list2= (List<Object>)o2;
        }

        if(list!= null && list2!=null){
            list.removeAll(list2);
            return list.toArray();
        }else if(list!=null){
            list.remove(o2);
            return list.toArray();
        }else if(list2!=null){
            list = new LinkedList<>();
            list.add(o);
            list.removeAll(list2);
            return list.toArray();
        }

        return new Object[0];
    }
    public static Object[] sortArray(Object array, Object order, Object reorder){
        if(array==null || order==null)
            return new Object[0];


        final int[] newOrder = sortArrayLike(order,reorder);
        final  Object[] ret = new Object[newOrder.length];

        if(array instanceof Object[] ){
            final Object[] toOrder =(Object[])array;
            Arrays.stream(newOrder).forEach(i->ret[i] = toOrder[newOrder[i]] );

        } else if(array instanceof List){
            final List toOrder =(List)array;
            Object[] oldOrder = (Object[]) order;
            Arrays.stream(newOrder).forEach(i->ret[i] = toOrder.get(newOrder[i]));

        }
        return ret;
    }
    public static int[] sortArrayLike(Object order, Object reorder){
        int[] sorted = null;
        if(reorder instanceof Object[] && order instanceof Object[]){
            Object[] toOrder =(Object[])reorder, newOrder = (Object[]) order;
            sorted = new int[toOrder.length];

            for(int i=0;i<sorted.length;i++){
                for(int j=0;j<sorted.length;j++){
                    if(toOrder[i].equals(newOrder[j])){
                        sorted[j] = i;
                        break;
                    }

                }
            }

        } else if((reorder instanceof List) && order instanceof Object[]){
            List toOrder =(List)reorder;
            Object[] newOrder = (Object[]) order;
            sorted = new int[toOrder.size()];

            for(int i=0;i<toOrder.size();i++){
                for(int j=0;j<toOrder.size();j++){
                    if(toOrder.get(i).equals(newOrder[j])){
                        sorted[j] = i;
                        break;
                    }

                }
            }
        }else if(reorder instanceof Object[] && order instanceof List){
            Object[] toOrder =(Object[])reorder;
            List newOrder = (List) order;
            sorted = new int[toOrder.length];

            for(int i=0;i<toOrder.length;i++){
                for(int j=0;j<toOrder.length;j++){
                    if(toOrder[i].equals(newOrder.get(j))){
                        sorted[j] = i;
                        break;
                    }

                }
            }
        }else if(reorder instanceof List && order instanceof List){
            List toOrder =(List)reorder, newOrder = (List) order;
            sorted = new int[toOrder.size()];

            for(int i=0;i<sorted.length;i++){
                for(int j=0;j<sorted.length;j++){
                    if(toOrder.get(i).equals(newOrder.get(j))){
                        sorted[j] = i;
                        break;
                    }

                }
            }
        }

        return sorted;

    }
    public static Map toMap(Object keys, Object values ){
        final Map map =new HashMap();
        if(keys instanceof Object[] && values instanceof Object[]){
            final Object[] keyss =(Object[])keys, valuess = (Object[]) values;
            for(int i=0;i<keyss.length;i++)
                map.put(keyss[i],valuess[i]);


        } else if((keys instanceof List) && values instanceof Object[]){
            List keyss =(List)keys;
            Object[] valuess = (Object[]) values;
            for(int i=0;i<keyss.size();i++)
                map.put(keyss.get(i),valuess[i]);

        }else if(keys instanceof Object[] && values instanceof List){
            Object[] keyss =(Object[])keys;
            List valuess = (List) values;
            for(int i=0;i<keyss.length;i++)
                map.put(keyss[i], valuess.get(i));

        }else if(keys instanceof List && values instanceof List){
            List keyss =(List)keys, valuess = (List) values;
            for(int i=0;i<keyss.size();i++)
                map.put(keyss.get(i), valuess.get(i));

        }
        return map;
    }
    Map applyTo(Object values ){
        final Map map =new HashMap();
        if( values instanceof Object[]){
            final Object[]  valuess = (Object[]) values;
            for(int i=0;i<valuess.length;i++)
                map.put("D"+String.valueOf(i),valuess[i]);


        }else if( values instanceof List){

            List valuess = (List) values;
            for(int i=0;i<valuess.size();i++)
                map.put("D"+String.valueOf(i), valuess.get(i));

        }
        return map;
    }
   static public int CompositionOrder(Object values ){
        final Map<Object,Integer> map =new HashMap();
        map.put("ds_1-0", 1);
        map.put("ds_2-0", 2);
        map.put("ds_3-0", 3);
        map.put("ds_1-1", 4);
        map.put("ds_2-1", 5);
        map.put("ds_3-1", 6);
        map.put("ds_1-2", 7);
        map.put("ds_2-2", 8);
        map.put("ds_3-2", 9);
        map.put("ds_1-3", 10);
        map.put("ds_2-3", 11);
        map.put("ds_3-3", 12);
        map.put("ds_1-4", 13);
        map.put("ds_2-4", 14);
        map.put("ds_3-4", 15);
        map.put("ds_1-5", 16);
        map.put("ds_2-5", 17);
        map.put("ds_3-5", 18);
        map.put("ds_1-6", 19);
        map.put("ds_2-6", 20);
        map.put("ds_3-6", 21);
        map.put("ds_1-7", 22);
        map.put("ds_2-7", 23);
        map.put("ds_3-7", 24);
        map.put("ds_1-8", 25);
        map.put("ds_2-8", 26);
        map.put("ds_3-8", 27);
        map.put("ds_1-9", 28);
        map.put("ds_2-9", 29);
        map.put("ds_3-9", 30);
        map.put("ds_1-10", 31);
        map.put("ds_2-10", 32);
        map.put("ds_3-10", 33);
        map.put("ds_1-11", 34);
        map.put("ds_2-11", 35);
        map.put("ds_3-11", 36);
        map.put("ds_1-12", 37);
        map.put("ds_2-12", 38);
        map.put("ds_3-12", 39);
        map.put("ds_1-14", 40);
        map.put("ds_2-14", 41);
        map.put("ds_3-14", 42);
        map.put("ds_1-15", 43);
        map.put("ds_2-15", 44);
        map.put("ds_3-15", 45);
        map.put("ds_1-16", 46);
        map.put("ds_2-16", 47);
        map.put("ds_3-16", 48);
        map.put("ds_1-17", 49);
        map.put("ds_2-17", 50);
        map.put("ds_3-17", 51);
        map.put("ds_1-18", 52);
        map.put("ds_2-18", 53);
        map.put("ds_3-18", 54);
        map.put("ds_1-19", 55);
        map.put("ds_2-19", 56);
        map.put("ds_3-19", 57);
        map.put("ds_1-55", 58);
        map.put("ds_2-55", 59);
        map.put("ds_3-55", 60);
       return sort(map, values);
    }

    static private int sort(Map<Object,Integer> map, Object values ){

        if(values instanceof ObservationImpl){
            return map.get(((ObservationImpl) values).getDatastream().getId().toString());
        } else if(values instanceof String){
            return map.get(values);
        }
        return -1;
    }
    static public int sortBy(Object values, Object order ){
        Map<Object,Integer> map = new HashMap<>();
        Stream stream;
        if(order instanceof Collection){
            stream= ((Collection) order).stream();
        }else if (order instanceof Object[]){
            stream = Arrays.stream((Object[]) order);
        }else
            return -1;

        stream.forEach(i->map.put(i,map.size()));

        return sort(map,values);
    }

}