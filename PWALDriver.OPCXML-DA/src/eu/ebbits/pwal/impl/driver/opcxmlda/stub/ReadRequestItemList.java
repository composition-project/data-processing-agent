package eu.ebbits.pwal.impl.driver.opcxmlda.stub;


/**
 * Stub class used for the list of Items of a Read request
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 * 
 * 
 * @author     ISMB
 * @version    %I%, %G%
 * @since      PWAL 0.2.0
 */ 
public class ReadRequestItemList extends ADBBeanImplementation {
    /* This type was generated from the piece of schema that had
         name = ReadRequestItemList
         Namespace URI = http://opcfoundation.org/webservices/XMLDA/1.0/
         Namespace Prefix = ns1
     */

    /**
     * 
     */
    private static final long serialVersionUID = 4059725012266338532L;

    /* static name of the class */
    private static final String NAME = ReadRequestItemList.class.getSimpleName();
    
    @Override
    protected String getClassName() {
        return this.getClass().getSimpleName();
    }

    
    /**
     * field for ItemPath
     * This was an Array!
     */
    private String localItemPath;
    
    /**
     * field for Items
     * This was an Array!
     */
    private ReadRequestItem[] localItems ;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    private boolean localItemsTracker = false ;


    /**
     * Returns the items list
     * 
     * @return    the list of items as <code>ReadRequestItem[]</code>
     */
    public ReadRequestItem[] getItems() {
        return localItems;
    }


    /**
     * Validates the items array 
     * 
     * @param param - array of items to be validated as <code>ReadRequestItem[]</code>
     */
    protected void validateItems(ReadRequestItem[] param) {

    }


    /**
     * Sets the array of the items
     * 
     * @param param - array of items to be set as <code>ReadRequestItem[]</code>
     */
    public void setItems(ReadRequestItem[] param) {
        ReadRequestItem[] readRequestsItemToUse = param.clone();
        validateItems(readRequestsItemToUse);
        if (readRequestsItemToUse != null) {
            //update the setting tracker
            localItemsTracker = true;
        } else {
            localItemsTracker = false;
        }
        this.localItems=readRequestsItemToUse;
    }



    /**
     * Adds an item to the items array
     * 
     * 
     * @param param - the item to add as <code>ReadRequestItem</code> object
     *  
     */
    public void addItems(ReadRequestItem param) {
        if (localItems == null) {
            localItems = new ReadRequestItem[]{};
        }
        //update the setting tracker
        localItemsTracker = true;
        java.util.List<ReadRequestItem> list =
                org.apache.axis2.databinding.utils.ConverterUtil.toList(localItems);
        list.add(param);
        this.localItems =
                (ReadRequestItem[])list.toArray(
                        new ReadRequestItem[list.size()]);
    }

    /**
     * field for ReqType
     * This was an Attribute!
     */
    private javax.xml.namespace.QName localReqType ;

    /**
     * field for MaxAge
     * This was an Attribute!
     */
    private int localMaxAge ;


    
    @Override
    protected void writeAttributes(String prefix,String namespace, org.apache.axiom.om.OMFactory factory, org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) 
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
        writeAttribute("", "ItemPath", localItemPath, xmlWriter);
        writeQNameAttribute("", "ReqType", localReqType, xmlWriter);
        if (localMaxAge!=java.lang.Integer.MIN_VALUE) {
            writeAttribute("",
                    "MaxAge",
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMaxAge), xmlWriter);
        }
        serializeAttributes(localItemsTracker,localItems,"Items",factory,xmlWriter);
    }


    @Override
    public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException{

        java.util.ArrayList<Object> elementList = new java.util.ArrayList<Object>();
        java.util.ArrayList<Object> attribList = new java.util.ArrayList<Object>();
        
        addRequestItemList(localItemsTracker,localItems,localItemPath,localReqType,attribList,elementList);
        attribList.add(new javax.xml.namespace.QName("","MaxAge"));
        attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMaxAge));
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
         * @return the <code>ReadRequestItemList</code> parsed
         * 
         * @throws Exception - if something goes wrong parsing the xml
         *
         */
        public static ReadRequestItemList parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            ReadRequestItemList object = new ReadRequestItemList();
            try {
                GenericFactory.goToNextStartElement(reader);
                ReadRequestItemList result = (ReadRequestItemList) checkADBBeanType(NAME,reader); 
                if(result!=null) {
                    return result;
                }
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.List<String> handledAttributes = new java.util.ArrayList<String>();
                // handle attribute "ItemPath"
                GenericFactory.handleItemPath(reader.getAttributeValue(null,"ItemPath"),object,handledAttributes);
                // handle attribute "ReqType"
                GenericFactory.handleReqType(reader.getAttributeValue(null,"ReqType"),object,handledAttributes, reader);
                // handle attribute "MaxAge"
                GenericFactory.handleMaxAge(reader.getAttributeValue(null,"MaxAge"),object,handledAttributes);
                reader.next();
                java.util.ArrayList<Object> list1 = new java.util.ArrayList<Object>();
                GenericFactory.goToNextStartElement(reader);
                if (reader.isStartElement() && new javax.xml.namespace.QName(OPC_NAMESPACE,"Items").equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    list1.add(ReadRequestItem.Factory.parse(reader));

                    //loop until we find a start element that is not part of this array
                    boolean loopDone1 = false;
                    while(!loopDone1) {
                        loopDone1 = GenericFactory.findStartElementNotPartOfTheArray("Items",reader);
                        if(!loopDone1) {
                            list1.add(ReadRequestItem.Factory.parse(reader));
                        }
                    }
                    // call the converter utility  to convert and set the array

                    object.setItems((ReadRequestItem[])
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                    ReadRequestItem.class,
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