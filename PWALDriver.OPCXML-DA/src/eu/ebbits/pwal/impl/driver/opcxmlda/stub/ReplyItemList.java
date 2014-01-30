package eu.ebbits.pwal.impl.driver.opcxmlda.stub;


/**
 * Stub class used for the list of items of a reply
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 * 
 * 
 * @author     ISMB
 * @version    %I%, %G%
 * @since      PWAL 0.2.0
 */ 

public class ReplyItemList extends ADBBeanImplementation {
    /* This type was generated from the piece of schema that had
         name = ReplyItemList
         Namespace URI = http://opcfoundation.org/webservices/XMLDA/1.0/
         Namespace Prefix = ns1
     */

    /**
     * 
     */
    private static final long serialVersionUID = 2336088421685918145L;

    /* static name of the class */
    private static final String NAME = ReplyItemList.class.getSimpleName();
    
    @Override
    protected String getClassName() {
        return this.getClass().getSimpleName();
    }
    
    
    //===========literal constant===========
    private static final String RESERVED = "Reserved";

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
     * Returns the items list
     * 
     * @return    the items list as <code>ItemValue[]</code>
     */
    public ItemValue[] getItems() {
        return localItems;
    }


    /**
     * field for Reserved
     * This was an Attribute!
     */
    private String localReserved ;


    /**
     * Returns the reserver attribute
     * 
     * @return   the reserved attribtue as <code>String</code>
     * 
     */
    public  String getReserved() {
        return localReserved;
    }



    /**
     * Sets the reserved attribute 
     * 
     * @param param - the value to set for the reserved attribute as <code>String</code>
     */
    public void setReserved(String param) {
        this.localReserved=param;
    }


    
    @Override
    protected void writeAttributes(String prefix,String namespace, org.apache.axiom.om.OMFactory factory, org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) 
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
        writeAttribute("", RESERVED, localReserved, xmlWriter);
        serializeAttributes(localItemsTracker,localItems,"Items",factory,xmlWriter);
    }



    @Override
    public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException{

        java.util.ArrayList<Object> elementList = new java.util.ArrayList<Object>();
        java.util.ArrayList<Object> attribList = new java.util.ArrayList<Object>();

        addItems(localItemsTracker,localItems,"Items",elementList);
        attribList.add(new javax.xml.namespace.QName("",RESERVED));
        attribList.add(localReserved);
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
         * @return the <code>ReplyItemList</code> parsed
         * 
         * @throws Exception - if something goes wrong parsing the xml
         *
         */
        public static ReplyItemList parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            ReplyItemList object = new ReplyItemList();
            try {
                GenericFactory.goToNextStartElement(reader);
                ReplyItemList result = (ReplyItemList) checkADBBeanType(NAME,reader); 
                if(result!=null) {
                    return result;
                }
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.List<String> handledAttributes = new java.util.ArrayList<String>();
                // handle attribute "Reserved"
                String tempAttribReserved = reader.getAttributeValue(null,RESERVED);
                if (tempAttribReserved!=null) {
                    object.setReserved(
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(tempAttribReserved));
                }
                handledAttributes.add(RESERVED);
                reader.next();
                java.util.ArrayList<Object> list1 = new java.util.ArrayList<Object>();
                GenericFactory.goToNextStartElement(reader);
                if (reader.isStartElement() && new javax.xml.namespace.QName(OPC_NAMESPACE,"Items").equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    ItemValue myvalzzz = ItemValue.Factory.parse(reader);
                    list1.add(myvalzzz);
                    //loop until we find a start element that is not part of this array
                    boolean loopDone1 = false;
                    while(!loopDone1) {
                        loopDone1 = GenericFactory.findStartElementNotPartOfTheArray("PropertyNames",reader);
                        if(!loopDone1) {
                            list1.add(ReplyItemList.Factory.parse(reader));
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