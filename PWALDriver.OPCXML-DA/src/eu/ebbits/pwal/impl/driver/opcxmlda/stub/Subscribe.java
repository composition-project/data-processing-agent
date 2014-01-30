package eu.ebbits.pwal.impl.driver.opcxmlda.stub;

/**
 * Stub class used for the Subscribe request
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 * 
 * 
 * @author     ISMB
 * @version    %I%, %G%
 * @since      PWAL 0.2.0
 */ 
public class Subscribe extends ADBBeanImplementation {

    /**
     * 
     */
    private static final long serialVersionUID = 939393764524137122L;

    /* QNAME for this class */
    public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
            OPC_NAMESPACE,
            "Subscribe",
            OPC_PREFIX);

    /* static name of the class */
    private static final String NAME = Subscribe.class.getSimpleName();
    
    @Override
    protected String getClassName() {
        return this.getClass().getSimpleName();
    }
    
    
    //==========literal constant=========
        private static final String SUBSCRIPTION_PING_RATE = "SubscriptionPingRate";

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
    private SubscribeRequestItemList localItemList ;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    private boolean localItemListTracker = false ;


    /**
     * Returns the list of items of the request
     * 
     * @return   the list of item of the request as <code>SubscribeRequestItemList</code>
     */
    public SubscribeRequestItemList getItemList() {
        return localItemList;
    }



    /**
     * Sets the list of items of the request
     * 
     * @param param - the list of items to be set as <code>SubscribeRequestItemList</code>
     */
    public void setItemList(SubscribeRequestItemList param) {
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
     * @return   a <code>boolean</code>, true if the request returns values on reply, false otherwise
     */
    public  boolean getReturnValuesOnReply() {
        return localReturnValuesOnReply;
    }



    /**
     * Sets the indication if the request returns values on reply
     * 
     * @param param -  a <code>boolean</code>, true if the request returns values on reply, false otherwise
     */
    public void setReturnValuesOnReply(boolean param) {
        this.localReturnValuesOnReply=param;
    }


    /**
     * field for SubscriptionPingRate
     * This was an Attribute!
     */
    private int localSubscriptionPingRate;


    /**
     * Returns the subscription ping rate
     * 
     * @return   the subscription ping rate as <code>int</code>
     */
    public int getSubscriptionPingRate() {
        return localSubscriptionPingRate;
    }



    /**
     * Sets the subscription ping rate
     * 
     * 
     * @param param - the subscription ping rate as <code>int</code>
     */
    public void setSubscriptionPingRate(int param) {
        this.localSubscriptionPingRate=param;
    }


    @Override
    protected void writeAttributes(String prefix,String namespace, org.apache.axiom.om.OMFactory factory, org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) 
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
        writeAttribute("",
                "ReturnValuesOnReply",
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReturnValuesOnReply), xmlWriter);
        if (localSubscriptionPingRate!=java.lang.Integer.MIN_VALUE) {
            writeAttribute("",
                    SUBSCRIPTION_PING_RATE,
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSubscriptionPingRate), xmlWriter);
        } else {
            throw new org.apache.axis2.databinding.ADBException("required attribute localSubscriptionPingRate is null");
        }
        serializeAttribute(localOptionsTracker,localOptions, "Options", factory, xmlWriter);
        serializeAttribute(localItemListTracker,localItemList, "ItemList", factory, xmlWriter);
        xmlWriter.writeEndElement();
    }


    @Override    
    public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException{


        java.util.ArrayList<Object> elementList = new java.util.ArrayList<Object>();
        java.util.ArrayList<Object> attribList = new java.util.ArrayList<Object>();
        addSubscribeWriteCommonItems(localOptionsTracker,localOptions,localItemListTracker,localItemList,localReturnValuesOnReply,attribList,elementList);
        attribList.add(new javax.xml.namespace.QName("",SUBSCRIPTION_PING_RATE));
        attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSubscriptionPingRate));
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
          * @return the <code>Subscribe</code> parsed
          * 
          * @throws Exception - if something goes wrong parsing the xml
          *
          */
        public static Subscribe parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            Subscribe object =    new Subscribe();
            try {
                GenericFactory.goToNextStartElement(reader);
                Subscribe result = (Subscribe) checkADBBeanType(NAME,reader); 
                if(result!=null) {
                    return result;
                }
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.List<String> handledAttributes = new java.util.ArrayList<String>();
                // handle attribute "ReturnValuesOnReply"
                String tempAttribReturnValuesOnReply = reader.getAttributeValue(null,"ReturnValuesOnReply");
                if (tempAttribReturnValuesOnReply!=null) {
                    object.setReturnValuesOnReply(
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(tempAttribReturnValuesOnReply));
                } else {
                    throw new org.apache.axis2.databinding.ADBException("Required attribute ReturnValuesOnReply is missing");
                }
                handledAttributes.add("ReturnValuesOnReply");
                // handle attribute "SubscriptionPingRate"
                String tempAttribSubscriptionPingRate = reader.getAttributeValue(null,SUBSCRIPTION_PING_RATE);
                if (tempAttribSubscriptionPingRate!=null) {
                    object.setSubscriptionPingRate(
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(tempAttribSubscriptionPingRate));
                } else {
                    throw new org.apache.axis2.databinding.ADBException("Required attribute SubscriptionPingRate is missing");
                }
                handledAttributes.add(SUBSCRIPTION_PING_RATE);
                reader.next();
                GenericFactory.readOptionsElement(reader,object);
                if (reader.isStartElement() && new javax.xml.namespace.QName(OPC_NAMESPACE,"ItemList").equals(reader.getName())) {
                    object.setItemList(SubscribeRequestItemList.Factory.parse(reader));
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