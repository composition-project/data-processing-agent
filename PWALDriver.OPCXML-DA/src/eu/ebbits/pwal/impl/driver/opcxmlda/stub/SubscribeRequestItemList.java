package eu.ebbits.pwal.impl.driver.opcxmlda.stub;


/**
 * Stub class used for the list of items of a subscribe request
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 * 
 * 
 * @author     ISMB
 * @version    %I%, %G%
 * @since      PWAL 0.2.0
 */ 
public class SubscribeRequestItemList extends ADBBeanImplementation {
    /* This type was generated from the piece of schema that had
         name = SubscribeRequestItemList
         Namespace URI = http://opcfoundation.org/webservices/XMLDA/1.0/
         Namespace Prefix = ns1
     */

    /**
     * 
     */
    private static final long serialVersionUID = -7284090892187816340L;

    /* static name of the class */
    private static final String NAME = SubscribeRequestItemList.class.getSimpleName();
    
    
    @Override
    protected String getClassName() {
        return this.getClass().getSimpleName();
    }
    
    
    //==========literal constant=========
        private static final String REQUESTED_SAMPLING_RATE = "RequestedSamplingRate";
    
    /**
     * field for ItemPath
     * This was an Attribute!
     */    
    private String localItemPath;
    
    /**
     * field for Items
     * This was an Array!
     */
    private SubscribeRequestItem[] localItems ;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    private boolean localItemsTracker = false ;


    /**
     * Returns the list of items of the request
     * 
     * @return    the list of items of the request as <code>SubscribeRequestItem[]</code>
     */
    public SubscribeRequestItem[] getItems() {
        return localItems;
    }


    /**
     * Validate the array for Items
     * 
     * @param param - the array to be set
     */
    protected void validateItems(SubscribeRequestItem[] param) {
    }


    /**
     * Sets the list of items of the request
     * 
     * @param param - list of items to be set as <code>SubscribeRequestItem[]</code>
     */
    public void setItems(SubscribeRequestItem[] param) {
        SubscribeRequestItem[] subscribeRequestItemToUse = param.clone();
        validateItems(subscribeRequestItemToUse);
        if (subscribeRequestItemToUse != null) {
            //update the setting tracker
            localItemsTracker = true;
        } else {
            localItemsTracker = false;
        }
        this.localItems=subscribeRequestItemToUse;
    }



    /**
     * Adds an item to the list
     * 
     * @param param - item to be added as <code>SubscribeRequestItem</code>
     */
    public void addItems(SubscribeRequestItem param) {
        if (localItems == null) {
            localItems = new SubscribeRequestItem[]{};
        }
        //update the setting tracker
        localItemsTracker = true;
        java.util.List <SubscribeRequestItem>list =
                org.apache.axis2.databinding.utils.ConverterUtil.toList(localItems);
        list.add(param);
        this.localItems =
                (SubscribeRequestItem[])list.toArray(
                        new SubscribeRequestItem[list.size()]);
    }


    /**
     * field for ReqType
     * This was an Attribute!
     */
    private javax.xml.namespace.QName localReqType ;


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
        if (!java.lang.Float.isNaN(localDeadband)) {
            writeAttribute("",
                    "Deadband",
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDeadband), xmlWriter);
        }
        if (localRequestedSamplingRate!=java.lang.Integer.MIN_VALUE) {
            writeAttribute("",
                    REQUESTED_SAMPLING_RATE,
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRequestedSamplingRate), xmlWriter);
        }
        writeAttribute("",
                "EnableBuffering",
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEnableBuffering), xmlWriter);
        serializeAttributes(localItemsTracker,localItems,"Items",factory,xmlWriter);
    }


    @Override
    public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException{

        java.util.ArrayList<Object> elementList = new java.util.ArrayList<Object>();
        java.util.ArrayList<Object> attribList = new java.util.ArrayList<Object>();

        addRequestItemList(localItemsTracker,localItems,localItemPath,localReqType,attribList,elementList);
        attribList.add(new javax.xml.namespace.QName("","Deadband"));
        attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDeadband));
        attribList.add(new javax.xml.namespace.QName("",REQUESTED_SAMPLING_RATE));
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
         * @return the <code>SubscribeRequestItemList</code> parsed
         * 
         * @throws Exception - if something goes wrong parsing the xml
         *
         */
        public static SubscribeRequestItemList parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            SubscribeRequestItemList object = new SubscribeRequestItemList();
            try {
                GenericFactory.goToNextStartElement(reader);
                SubscribeRequestItemList result = (SubscribeRequestItemList) checkADBBeanType(NAME,reader); 
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
                // handle attribute "Deadband"
                GenericFactory.handleDeadband(reader.getAttributeValue(null,"Deadband"),object,handledAttributes);
                // handle attribute "RequestedSamplingRate"
                String tempAttribRequestedSamplingRate = reader.getAttributeValue(null,REQUESTED_SAMPLING_RATE);
                if (tempAttribRequestedSamplingRate!=null) {
                    object.setRequestedSamplingRate(
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(tempAttribRequestedSamplingRate));
                } else {
                    object.setRequestedSamplingRate(java.lang.Integer.MIN_VALUE);
                }
                handledAttributes.add(REQUESTED_SAMPLING_RATE);
                // handle attribute "EnableBuffering"
                GenericFactory.handleEnableBuffering(reader.getAttributeValue(null,"EbableBuffering"),object,handledAttributes);
                reader.next();
                java.util.ArrayList<Object> list1 = new java.util.ArrayList<Object>();
                GenericFactory.goToNextStartElement(reader);
                if (reader.isStartElement() && new javax.xml.namespace.QName(OPC_NAMESPACE,"Items").equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    list1.add(SubscribeRequestItem.Factory.parse(reader));
                    //loop until we find a start element that is not part of this array
                    boolean loopDone1 = false;
                    while(!loopDone1) {
                        loopDone1 = GenericFactory.findStartElementNotPartOfTheArray("Items",reader);
                        if(!loopDone1) {
                            list1.add(SubscribeRequestItem.Factory.parse(reader));
                        }
                    }
                    // call the converter utility  to convert and set the array
                    object.setItems((SubscribeRequestItem[])
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                    SubscribeRequestItem.class,
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