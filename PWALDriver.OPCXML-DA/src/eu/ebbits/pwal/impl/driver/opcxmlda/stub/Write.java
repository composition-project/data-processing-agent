package eu.ebbits.pwal.impl.driver.opcxmlda.stub;


/**
 * Stub class used for the Write request
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 * 
 * 
 * @author     ISMB
 * @version    %I%, %G%
 * @since      PWAL 0.2.0
 */ 
public class Write extends ADBBeanImplementation {

    /**
     * 
     */
    private static final long serialVersionUID = 4621696992866856020L;

    /* QNAME for this class */
    public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
            OPC_NAMESPACE,
            "Write",
            OPC_PREFIX);

    /* static name of the class */
    private static final String NAME = Write.class.getSimpleName();
    
    @Override
    protected String getClassName() {
        return this.getClass().getSimpleName();
    }
    
    
    //==========literal constant=========
    private static final String RETURN_VALUES_ON_REPLY = "ReturnValuesOnReply";
    
    /**
     * field for Options
     */
    private RequestOptions localOptions ;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    private boolean localOptionsTracker = false ;



    /**
     * field for ItemList
     */
    private WriteRequestItemList localItemList ;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    private boolean localItemListTracker = false ;


    /**
     * Returns the list of items of the request
     * 
     * @return   list of items of the request as <code>WriteRequestItemList</code>
     */
    public WriteRequestItemList getItemList() {
        return localItemList;
    }

    /**
     * Sets the list of items of the request
     * 
     * @param param - the list of items to set as <code>WriteRequestItemList</code>
     */
    public void setItemList(WriteRequestItemList param) {
        setProtectedFieldValueWithTracker("localItemList", "localItemListTracker", param);
    }


    /**
     * field for ReturnValuesOnReply
     * This was an Attribute!
     */
    private boolean localReturnValuesOnReply ;


    /**
     * Retrieves the indication if the request returns values on reply
     * 
     * @return   a <code>boolean</code>, true if the request return values, false otherwise
     */
    public boolean getReturnValuesOnReply() {
        return localReturnValuesOnReply;
    }



    /**
     * Sets the indication if the request returns values on reply
     * 
     * @param param - a <code>boolean</code>, true if the request return values, false otherwise
     * 
     */
    public void setReturnValuesOnReply(boolean param) {
        this.localReturnValuesOnReply=param;
    }


    @Override
    protected void writeAttributes(String prefix,String namespace, org.apache.axiom.om.OMFactory factory, org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) 
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
        writeAttribute("",
                RETURN_VALUES_ON_REPLY,
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReturnValuesOnReply), xmlWriter);
        serializeAttribute(localOptionsTracker,localOptions, "Options", factory, xmlWriter);
        serializeAttribute(localItemListTracker,localItemList, "ItemList", factory, xmlWriter);
        xmlWriter.writeEndElement();
    }


    @Override
    public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException{

        java.util.ArrayList<Object> elementList = new java.util.ArrayList<Object>();
        java.util.ArrayList<Object> attribList = new java.util.ArrayList<Object>();

        addItem(localOptionsTracker,localOptions,"Options",elementList);
        addItem(localItemListTracker,localItemList,"ItemList",elementList);
        attribList.add(new javax.xml.namespace.QName("",RETURN_VALUES_ON_REPLY));
        attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReturnValuesOnReply));
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
          * @return the <code>Write</code> parsed
          * 
          * @throws Exception - if something goes wrong parsing the xml
          *
          */
        public static Write parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            Write object = new Write();
            try {
                GenericFactory.goToNextStartElement(reader);
                Write result = (Write) checkADBBeanType(NAME,reader); 
                if(result!=null) {
                    return result;
                }
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.List<Object>handledAttributes = new java.util.ArrayList<Object>();
                // handle attribute "ReturnValuesOnReply"
                String tempAttribReturnValuesOnReply =
                        reader.getAttributeValue(null,RETURN_VALUES_ON_REPLY);
                if (tempAttribReturnValuesOnReply!=null) {
                    object.setReturnValuesOnReply(
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(tempAttribReturnValuesOnReply));
                } else {
                    throw new org.apache.axis2.databinding.ADBException("Required attribute ReturnValuesOnReply is missing");
                }
                handledAttributes.add(RETURN_VALUES_ON_REPLY);
                reader.next();
                GenericFactory.readOptionsElement(reader,object);
                if (reader.isStartElement() && new javax.xml.namespace.QName(OPC_NAMESPACE,"ItemList").equals(reader.getName())) {
                    object.setItemList(WriteRequestItemList.Factory.parse(reader));
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