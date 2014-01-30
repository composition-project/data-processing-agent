package eu.ebbits.pwal.impl.driver.opcxmlda.stub;

/**
 * Stub class used for an item of the subscribe request
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 * 
 * 
 * @author     ISMB
 * @version    %I%, %G%
 * @since      PWAL 0.2.0
 */ 
public class SubscribeRequestItem extends ADBBeanImplementation {
    /* This type was generated from the piece of schema that had
         name = SubscribeRequestItem
         Namespace URI = http://opcfoundation.org/webservices/XMLDA/1.0/
         Namespace Prefix = ns1
     */

    /**
     * 
     */
    private static final long serialVersionUID = -5320970138468631640L;

    /* static name of the class */
    private static final String NAME = SubscribeRequestItem.class.getSimpleName();

    @Override
    protected String getClassName() {
        return this.getClass().getSimpleName();
    }

    
    /**
     * field for ItemPath
     * This was an Attribute!
     */    
    private String localItemPath;
    
    /**
     * field for ItemName
     * This was an Attribute!
     */        
    private String localItemName;

    /**
     * field for ReqType
     * This was an Attribute!
     */
    private javax.xml.namespace.QName localReqType ;


    /**
     * field for ClientItemHandle
     * This was an Attribute!
     */
    private String localClientItemHandle ;

    
    /**
     * field for Deadband
     * This was an Attribute!
     */
    private float localDeadband ;


    /**
     * field for RequestedSamplingRate
     * This was an Attribute!
     */
    private int localRequestedSamplingRate ;


    /**
     * field for EnableBuffering
     * This was an Attribute!
     */
    private boolean localEnableBuffering ;


    
    @Override
    protected void writeAttributes(String prefix,String namespace, org.apache.axiom.om.OMFactory factory, org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) 
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
        writeAttribute("", "ItemPath", localItemPath, xmlWriter);
        writeQNameAttribute("", "ReqType", localReqType, xmlWriter);
        writeAttribute("", "ItemName", localItemName, xmlWriter);
        writeAttribute("", "ClientItemHandle", localClientItemHandle, xmlWriter);
        if (!java.lang.Float.isNaN(localDeadband)) {
            writeAttribute("",
                    "Deadband",
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDeadband), xmlWriter);
        }
        if (localRequestedSamplingRate!=java.lang.Integer.MIN_VALUE) {
            writeAttribute("",
                    "RequestedSamplingRate",
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRequestedSamplingRate), xmlWriter);
        }
        writeAttribute("",
                "EnableBuffering",
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEnableBuffering), xmlWriter);
        xmlWriter.writeEndElement();
    }


    @Override
    public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException{
        java.util.ArrayList<Object> elementList = new java.util.ArrayList<Object>();
        java.util.ArrayList<Object> attribList = new java.util.ArrayList<Object>();

        attribList.add(new javax.xml.namespace.QName("","ItemPath"));
        attribList.add(localItemPath);
        attribList.add(new javax.xml.namespace.QName("","ReqType"));
        attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReqType));
        attribList.add(new javax.xml.namespace.QName("","ItemName"));
        attribList.add(localItemName);
        attribList.add(new javax.xml.namespace.QName("","ClientItemHandle"));
        attribList.add(localClientItemHandle);
        attribList.add(new javax.xml.namespace.QName("","Deadband"));
        attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDeadband));
        attribList.add(new javax.xml.namespace.QName("","RequestedSamplingRate"));
        attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRequestedSamplingRate));
        attribList.add(new javax.xml.namespace.QName("","EnableBuffering"));
        attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEnableBuffering));
        return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
    }



    /**
     *  Factory class that keeps the parse method
     */
    public static class Factory  {

        /**
         * static method to create the object
         * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
         *             If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
         * Postcondition: If this object is an element, the reader is positioned at its end element
         *             If this object is a complex type, the reader is positioned at the end element of its outer element
         *
         * @param reader - the <code>javax.xml.stream.XMLStreamReader</code> to use to parse the xml
         * 
         * @return the <code>SubscribeRequestItem</code> parsed
         * 
         * @throws Exception - if something goes wrong parsing the xml
         *
         */
        public static SubscribeRequestItem parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            SubscribeRequestItem object =
                    new SubscribeRequestItem();
            try {
                GenericFactory.goToNextStartElement(reader);
                SubscribeRequestItem result = (SubscribeRequestItem) checkADBBeanType(NAME,reader); 
                if(result!=null) {
                    return result;
                }
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.List<String> handledAttributes = new java.util.ArrayList<String>();
                // handle attribute "ItemPath"
                GenericFactory.handleItemPath(reader.getAttributeValue(null,"ItemPath"),object,handledAttributes);
                // handle attribute "ReqType"
                GenericFactory.handleReqType(reader.getAttributeValue(null,"ReqType"),object,handledAttributes,reader);
                // handle attribute "ItemName"
                GenericFactory.handleItemPath(reader.getAttributeValue(null,"ItemName"),object,handledAttributes);
                // handle attribute "ClientItemHandle"
                GenericFactory.handleClientItemHandle(reader.getAttributeValue(null,"ClientItemHandle"),object,handledAttributes);
                // handle attribute "Deadband"
                GenericFactory.handleDeadband(reader.getAttributeValue(null,"Deadband"),object,handledAttributes);
                // handle attribute "RequestedSamplingRate"
                GenericFactory.handleRequestedSamplingRate(reader.getAttributeValue(null,"RequestedSamplingRate"),object,handledAttributes);
                // handle attribute "EnableBuffering"
                GenericFactory.handleEnableBuffering(reader.getAttributeValue(null,"EnableBuffering"),object,handledAttributes);
                reader.next();
            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }
            return object;
        }
    }//end of factory class
}