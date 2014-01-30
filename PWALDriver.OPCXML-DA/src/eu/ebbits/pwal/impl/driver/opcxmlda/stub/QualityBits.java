package eu.ebbits.pwal.impl.driver.opcxmlda.stub;

/**
 * Stub class for QualityBits
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 * 
 * 
 * @author     ISMB
 * @version    %I%, %G%
 * @since      PWAL 0.2.0
 */ 
public class QualityBits extends ADBBeanImplementation {
    //http://192.168.87.101/OPCXMLServer/sopcweb.asmx
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /* QNAME for this class */
    public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
            OPC_NAMESPACE,
            "qualityBits",
            OPC_PREFIX);

    
    @Override
    protected String getClassName() {
        return this.getClass().getSimpleName();
    }
    


    /**
     * field for QualityBits
     */
    private String localQualityBits;

    /* table used to store the registered QualityBits instances */
    private static java.util.Map<String,QualityBits> table = new java.util.HashMap<String,QualityBits>();

    /**
     * Constructor of the QualityBits
     * 
     * @param value - value to filter as <code>String</code>
     * @param isRegisterValue - indicates if the quality bits must be stored in the table of the register values, 
     *                              true yes, false no
     */
    protected QualityBits(String value, boolean isRegisterValue) {
        localQualityBits = value;
        if (isRegisterValue) {
            table.put(localQualityBits, this);
        }
    }

    //=========literal constants================
    
    public static final String BAD = "bad";

    public static final String BAD_CONFIGURATION_ERROR = "badConfigurationError";

    public static final String BAD_NOT_CONNECTED = "badNotConnected";

    public static final String BAD_DEVICE_FAILURE = "badDeviceFailure";

    public static final String BAD_SENSOR_FAILURE = "badSensorFailure";

    public static final String BAD_LAST_KNOWN_VALUE = "badLastKnownValue";

    public static final String BAD_COMM_FAILURE = "badCommFailure";

    public static final String BAD_OUT_OF_SERVICE = "badOutOfService";

    public static final String BAD_WAITING_FOR_INITIAL_DATA = "badWaitingForInitialData";

    public static final String UNCERTAIN = "uncertain";

    public static final String UNCERTAIN_LAST_USABLE_VALUE = "uncertainLastUsableValue";

    public static final String UNCERTAIN_SENSOR_NOT_ACCURATE = "uncertainSensorNotAccurate";

    public static final String UNCERTAIN_EU_EXCEEDED = "uncertainEUExceeded";

    public static final String UNCERTAIN_SUB_NORMAL = "uncertainSubNormal";

    public static final String GOOD = "good";

    public static final String GOOD_LOCAL_OVERRIDE = "goodLocalOverride";

    
    //=========Quality Bits constants================
    

    public static final QualityBits BAD_QUALITY_BITS = new QualityBits(BAD,true);

    public static final QualityBits BAD_CONFIGURATION_ERROR_QUALITY_BITS = new QualityBits(BAD_CONFIGURATION_ERROR,true);

    public static final QualityBits BAD_NOT_CONNECTED_QUALITY_BITS = new QualityBits(BAD_NOT_CONNECTED,true);

    public static final QualityBits BAD_DEVICE_FAILURE_QUALITY_BITS = new QualityBits(BAD_DEVICE_FAILURE,true);

    public static final QualityBits BAD_SENSOR_FAILURE_QUALITY_BITS = new QualityBits(BAD_SENSOR_FAILURE,true);

    public static final QualityBits BAD_LAST_KNOWN_VALUE_QUALITY_BITS = new QualityBits(BAD_LAST_KNOWN_VALUE,true);

    public static final QualityBits BAD_COMM_FAILURE_QUALITY_BITS = new QualityBits(BAD_COMM_FAILURE,true);

    public static final QualityBits BAD_OUT_OF_SERVICE_QUALITY_BITS = new QualityBits(BAD_OUT_OF_SERVICE,true);

    public static final QualityBits BAD_WAITING_FOR_INITIAL_DATA_QUALITY_BITS = new QualityBits(BAD_WAITING_FOR_INITIAL_DATA,true);

    public static final QualityBits UNCERTAIN_QUALITY_BITS = new QualityBits(UNCERTAIN,true);

    public static final QualityBits UNCERTAIN_LAST_USABLE_VALUE_QUALITY_BITS = new QualityBits(UNCERTAIN_LAST_USABLE_VALUE,true);

    public static final QualityBits UNCERTAIN_SENSOR_NOT_ACCURATE_QUALITY_BITS = new QualityBits(UNCERTAIN_SENSOR_NOT_ACCURATE,true);

    public static final QualityBits UNCERTAIN_EU_EXCEEDED_QUALITY_BITS = new QualityBits(UNCERTAIN_EU_EXCEEDED,true);

    public static final QualityBits UNCERTAIN_SUBNORMAL_QUALITY_BITS = new QualityBits(UNCERTAIN_SUB_NORMAL,true);

    public static final QualityBits GOOD_QUALITY_BITS = new QualityBits(GOOD,true);

    public static final QualityBits GOOD_LOCAL_OVERRIDE_QUALITY_BITS = new QualityBits(GOOD_LOCAL_OVERRIDE,true);

    /**
     * Returns the value of QualityBits for this object
     * 
     * @return   the QualityBits value as <code>String</code>
     */
    public String getValue() { 
        return localQualityBits;
    }

    
    //=========Utility methods used to compare QualityBits classes========
    
    @Override
    public boolean equals(java.lang.Object obj) {
        return (obj == this);
    }
    
    @Override
    public int hashCode() { 
        return toString().hashCode();
    }
    
    @Override
    public String toString() {
        return localQualityBits;
    }

    //=========Utility methods used to compare QualityBits classes end ========
    

    @Override
    protected void writeAttributes(String prefix,String namespace, org.apache.axiom.om.OMFactory factory, org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) 
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
         if (localQualityBits==null) {
             throw new org.apache.axis2.databinding.ADBException("Value cannot be null !!");
         }else{
             xmlWriter.writeCharacters(localQualityBits);
         }
         xmlWriter.writeEndElement();
     }



    @Override
    public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException{

        //We can safely assume an element has only one type associated with it
        return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(MY_QNAME,
                new java.lang.Object[]{
                org.apache.axis2.databinding.utils.reader.ADBXMLStreamReader.ELEMENT_TEXT,
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localQualityBits)
        }, null);
    }



     /**
      *  Factory class that keeps the parse method
      */
     public static class Factory  {


         /**
          * Returns the <code>QualityBits</code> object registered
          *  
          * @param value - the value registered when the object has been created as a <code>String</code> 
          * 
          * @return   the <code>QualityBits</code> associated with the value passed as parameter
          * 
          * @throws java.lang.IllegalArgumentException - if the value passed as parameter is not registered
          */
         public static QualityBits fromValue(String value) {
             QualityBits enumeration = (QualityBits) table.get(value);
             if (enumeration==null) {
                 throw new java.lang.IllegalArgumentException();
             }
             return enumeration;
         }
         
         /**
          * Returns the <code>QualityBits</code> object registered
          *  
          * @param value - the value registered when the object has been created as a <code>String</code> 
          * @param namespace - the namespace to use
          * 
          * @return   the <code>QualityBits</code> associated with the value passed as parameter
          * 
          * @throws java.lang.IllegalArgumentException - if the value passed as parameter is not registered
          * 
          */
         public static QualityBits fromString(String value,String namespaceURI) {
             try {
                 return fromValue(value);
             } catch (java.lang.Exception e) {
                 throw new java.lang.IllegalArgumentException(e);
             }
         }

         
         /**
          * Returns the <code>QualityBits</code> object from an xml content
          *  
          * @param reader - the <code>javax.xml.stream.XMLStreamReader</code> to use
          * @param content - the xml content as <code>String</code>
          * 
          * @return   the <code>QualityBits</code> generated parsing the xml
          * 
          * 
          */
         public static QualityBits fromString(javax.xml.stream.XMLStreamReader xmlStreamReader,
                 String content) {
             if (content.indexOf(':') > -1) {
                 String prefix = content.substring(0,content.indexOf(':'));
                 String namespaceUri = xmlStreamReader.getNamespaceContext().getNamespaceURI(prefix);
                 return QualityBits.Factory.fromString(content,namespaceUri);
             } else {
                 return QualityBits.Factory.fromString(content,"");
             }
         }


         /**
          * static method to create the object
          * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
          *             If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
          * Postcondition: If this object is an element, the reader is positioned at its end element
          *             If this object is a complex type, the reader is positioned at the end element of its outer element
          *
          * @param reader - the <code>javax.xml.stream.XMLStreamReader</code> to use to parse the xml
          * 
          * @return the <code>QualityBits</code> parsed
          * 
          * @throws Exception - if something goes wrong parsing the xml
          *
          */
         public static QualityBits parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
             QualityBits object = null;
             String prefix ="";
             String namespaceuri ="";
             try {
                 GenericFactory.goToNextStartElement(reader);
                 while(!reader.isEndElement()) {
                     if (reader.isStartElement()  || reader.hasText()) {
                         String content = reader.getElementText();
                         if (content.indexOf(':') > 0) {
                             // this seems to be a Qname so find the namespace and send
                             prefix = content.substring(0, content.indexOf(':'));
                             namespaceuri = reader.getNamespaceURI(prefix);
                             object = QualityBits.Factory.fromString(content,namespaceuri);
                         } else {
                             // this seems to be not a qname send and empty namespace incase of it is
                             // check is done in fromString method
                             object = QualityBits.Factory.fromString(content,"");
                         }
                     } else {
                         reader.next();
                     }  
                 }  // end of while loop
             } catch (javax.xml.stream.XMLStreamException e) {
                 throw new java.lang.Exception(e);
             }
             return object;
         }
     }//end of factory class
}