package eu.ebbits.pwal.impl.driver.opcxmlda.stub;


/**
 * Stub class used for the value of an item of a subscribe request
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 * 
 * 
 * @author     ISMB
 * @version    %I%, %G%
 * @since      PWAL 0.2.0
 */ 
public class SubscribeItemValue extends ADBBeanImplementation {
    /* This type was generated from the piece of schema that had
         name = SubscribeItemValue
         Namespace URI = http://opcfoundation.org/webservices/XMLDA/1.0/
         Namespace Prefix = ns1
     */

    /**
     * 
     */
    private static final long serialVersionUID = 2640057747419447535L;

    /* static name of the class */
    private static final String NAME = SubscribeItemValue.class.getSimpleName();
    
    @Override
    protected String getClassName() {
        return this.getClass().getSimpleName();
    }

    /**
     * field for ItemValue
     */
    private ItemValue localItemValue ;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    private boolean localItemValueTracker = false ;


    /**
     * Returns the item value
     * 
     * @return  the item value as <code>ItemValue</code>
     */
    public  ItemValue getItemValue() {
        return localItemValue;
    }



    /**
     * Sets the item value
     * 
     * @param param - item value to set as <code>ItemValue</code>
     */
    public void setItemValue(ItemValue param) {
        if (param != null) {
            //update the setting tracker
            localItemValueTracker = true;
        } else {
            localItemValueTracker = false;
        }
        this.localItemValue=param;
    }


    /**
     * field for RevisedSamplingRate
     * This was an Attribute!
     */
    private int localRevisedSamplingRate;


    @Override
    protected void writeAttributes(String prefix,String namespace, org.apache.axiom.om.OMFactory factory, org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) 
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
        if (localRevisedSamplingRate!=java.lang.Integer.MIN_VALUE) {
            writeAttribute("",
                    "RevisedSamplingRate",
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRevisedSamplingRate), xmlWriter);
        }
        serializeAttribute(localItemValueTracker,localItemValue,"ItemValue",factory,xmlWriter);
        xmlWriter.writeEndElement();
    }



    @Override
    public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException{

        java.util.ArrayList<Object> elementList = new java.util.ArrayList<Object>();
        java.util.ArrayList<Object> attribList = new java.util.ArrayList<Object>();
        
        addItem(localItemValueTracker,localItemValue,"ItemValue",elementList);
        attribList.add(new javax.xml.namespace.QName("","RevisedSamplingRate"));
        attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRevisedSamplingRate));
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
         *
         * @param reader - the <code>javax.xml.stream.XMLStreamReader</code> to use to parse the xml
         * 
         * @return the <code>SubscribeItemValue</code> parsed
         * 
         * @throws Exception - if something goes wrong parsing the xml
         *
         */
        public static SubscribeItemValue parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            SubscribeItemValue object = new SubscribeItemValue();
            try {
                GenericFactory.goToNextStartElement(reader);
                SubscribeItemValue result = (SubscribeItemValue) checkADBBeanType(NAME,reader); 
                if(result!=null) {
                    return result;
                }
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.List<String> handledAttributes = new java.util.ArrayList<String>();
                // handle attribute "RevisedSamplingRate"
                GenericFactory.handleRevisedSamplingRate(reader.getAttributeValue(null,"RevisedSamplingRate"),object,handledAttributes);
                reader.next();
                GenericFactory.goToNextStartElement(reader);
                if (reader.isStartElement() && new javax.xml.namespace.QName(OPC_NAMESPACE,"ItemValue").equals(reader.getName())) {
                    object.setItemValue(ItemValue.Factory.parse(reader));
                    reader.next();
                }  // End of if for expected property start element
                GenericFactory.checkUnexpectedStartElements(reader);
            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }
            return object;
        }
    }//end of factory class
}