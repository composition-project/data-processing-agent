package eu.ebbits.pwal.impl.driver.opcxmlda.stub;


/**
 * Stub class used for the list of items of the response to a subscribe polled refresh request
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 * 
 * 
 * @author     ISMB
 * @version    %I%, %G%
 * @since      PWAL 0.2.0
 */ 
public class SubscribePolledRefreshReplyItemList extends ADBBeanImplementation {
    /* This type was generated from the piece of schema that had
         name = SubscribePolledRefreshReplyItemList
         Namespace URI = http://opcfoundation.org/webservices/XMLDA/1.0/
         Namespace Prefix = ns1
     */

    /**
     * 
     */
    private static final long serialVersionUID = 6635609621140755119L;

    /* static name of the class */
    private static final String NAME = SubscribePolledRefreshReplyItemList.class.getSimpleName();
    
    @Override
    protected String getClassName() {
        return this.getClass().getSimpleName();
    }

    
    //==========literal constant=========
    private static final String SUBSCRIPTION_HANDLE = "SubscriptionHandle";
    
        
    /**
     * field for Items
     * This was an Array!
     */
    private ItemValue[] localItems ;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    private boolean localItemsTracker = false ;


    /**
     * Returns the list of items of this response
     * 
     * @return the list of items as as <code>ItemValue[]</code> 
     * 
     */
    public  ItemValue[] getItems() {
        return localItems;
    }


    /**
     * field for SubscriptionHandle
     * This was an Attribute!
     */
    private String localSubscriptionHandle ;


    /**
     * Returns the subscription handle
     * 
     * @return  a <code>String</code> containing the subscription handle
     */
    public  String getSubscriptionHandle() {
        return localSubscriptionHandle;
    }



    /**
     * Sets the subscription handle
     * 
     * 
     * @param param - the subscription handle to be set as <code>String</code>
     */
    public void setSubscriptionHandle(String param) {
        this.localSubscriptionHandle=param;
    }


    @Override
    protected void writeAttributes(String prefix,String namespace, org.apache.axiom.om.OMFactory factory, org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) 
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
        writeAttribute("", SUBSCRIPTION_HANDLE, localSubscriptionHandle, xmlWriter);
        serializeAttributes(localItemsTracker,localItems,"Items",factory,xmlWriter);
    }


    @Override
    public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException{

        java.util.ArrayList<Object> elementList = new java.util.ArrayList<Object>();
        java.util.ArrayList<Object> attribList = new java.util.ArrayList<Object>();

        addItems(localItemsTracker,localItems,"Items",elementList);
        attribList.add(new javax.xml.namespace.QName("",SUBSCRIPTION_HANDLE));
        attribList.add(localSubscriptionHandle);
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
         * @return the <code>SubscribePolledRefreshReplyItemList</code> parsed
         * 
         * @throws Exception - if something goes wrong parsing the xml
         *
         */
        public static SubscribePolledRefreshReplyItemList parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            SubscribePolledRefreshReplyItemList object =
                    new SubscribePolledRefreshReplyItemList();
            try {
                GenericFactory.goToNextStartElement(reader);
                SubscribePolledRefreshReplyItemList result = (SubscribePolledRefreshReplyItemList) checkADBBeanType(NAME,reader); 
                if(result!=null) {
                    return result;
                }
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.List<String> handledAttributes = new java.util.ArrayList<String>();
                // handle attribute "SubscriptionHandle"
                String tempAttribSubscriptionHandle = reader.getAttributeValue(null,SUBSCRIPTION_HANDLE);
                if (tempAttribSubscriptionHandle!=null) {
                    object.setSubscriptionHandle(
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(tempAttribSubscriptionHandle));
                }
                handledAttributes.add(SUBSCRIPTION_HANDLE);
                reader.next();
                java.util.ArrayList<Object> list1 = new java.util.ArrayList<Object>();
                GenericFactory.goToNextStartElement(reader);
                if (reader.isStartElement() && new javax.xml.namespace.QName(OPC_NAMESPACE,"Items").equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    list1.add(ItemValue.Factory.parse(reader));
                    //loop until we find a start element that is not part of this array
                    boolean loopDone1 = false;
                    while(!loopDone1) {
                        loopDone1 = GenericFactory.findStartElementNotPartOfTheArray("PropertyNames",reader);
                        if(!loopDone1) {
                            list1.add(SubscribePolledRefreshReplyItemList.Factory.parse(reader));
                        }
                    }
                    // call the converter utility  to convert and set the array
                    object.setItems((ItemValue[])
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                    ItemValue.class,
                                    list1));
                }  // End of if for expected property start element
                GenericFactory.goToAndCheckUnexpectedStartElements(reader);
            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }
            return object;
        }
    }//end of factory class
}