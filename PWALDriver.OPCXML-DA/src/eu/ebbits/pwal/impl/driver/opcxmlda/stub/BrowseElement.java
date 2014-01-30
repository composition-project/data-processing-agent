package eu.ebbits.pwal.impl.driver.opcxmlda.stub;

/**
 * Stub class used for BrowseElement
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 * 
 * 
 * @author     ISMB
 * @version    %I%, %G%
 * @since      PWAL 0.2.0
 */ 
public class BrowseElement extends ADBBeanImplementation {
    /* This type was generated from the piece of schema that had
         name = BrowseElement
         Namespace URI = http://opcfoundation.org/webservices/XMLDA/1.0/
         Namespace Prefix = ns1
     */

    /**
     * 
     */
    private static final long serialVersionUID = -1261565639244042060L;

    /* static reference to the name of this class*/
    private static final String NAME = BrowseElement.class.getSimpleName();
    
    @Override
    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    
    //===========literal constants===========
    private static final String IS_ITEM = "IsItem";
    
    private static final String HAS_CHILDREN = "HasChildren";
    
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
     * field for Properties
     * This was an Array!
     */
    private ItemProperty[] localProperties ;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    private boolean localPropertiesTracker = false ;


    /**
     * field for Name
     * This was an Attribute!
     */
    private String localName;


    /**
     * Retrieves the name of the BrowseElement 
     * 
     * @return   the name of the BrowseElement as <code>String</code>
     */
    public String getName() {
        return localName;
    }



    /**
     * Sets the name of the BrowseElement
     * 
     * @param param -  the name of the BrowseElement as <code>String</code> 
     */
    public void setName(String param) {
        this.localName=param;
    }


    /**
     * field for IsItem
     * This was an Attribute!
     */
    private boolean localIsItem ;


    /**
     * Indicates if this element is an Item
     * 
     * @return    a <code>boolean</code> - true if is an Item, false otherwise
     */
    public  boolean getIsItem() {
        return localIsItem;
    }



    /**
     * Sets if this element is an Item
     * 
     * @param param - a <code>boolean</code> - true to set the BrowseElement as an Item, false otherwise
     */
    public void setIsItem(boolean param) {
        this.localIsItem=param;
    }


    /**
     * field for HasChildren
     * This was an Attribute!
     */
    private boolean localHasChildren ;


    /**
     * Indicates if the element has children
     * 
     * @return    a <code>boolean</code> - true if the element has children, false otherwise
     */
    public boolean getHasChildren() {
        return localHasChildren;
    }



    /**
     * Sets if this element has children
     * 
     * @param param - a <code>boolean</code> - true if the element has children, false otherwise
     */
    public void setHasChildren(boolean param) {
        this.localHasChildren=param;
    }



    @Override
    protected void writeAttributes(String prefix,String namespace, org.apache.axiom.om.OMFactory factory, org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) 
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
        writeAttribute("", "Name", localName, xmlWriter);
        writeAttribute("", "ItemPath", localItemPath, xmlWriter);
        writeAttribute("", "ItemName", localItemName, xmlWriter);
        writeAttribute("",
                IS_ITEM,
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIsItem), xmlWriter);
        writeAttribute("",
                HAS_CHILDREN,
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHasChildren), xmlWriter);
        serializeAttributes(localPropertiesTracker,localProperties,"Properties",factory,xmlWriter);        
        xmlWriter.writeEndElement();
    }




    @Override
    public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException{
        java.util.ArrayList<Object> elementList = new java.util.ArrayList<Object>();
        java.util.ArrayList<Object> attribList = new java.util.ArrayList<Object>();

        addItems(localPropertiesTracker,localProperties,"Properties",elementList);
        attribList.add(new javax.xml.namespace.QName("","Name"));
        attribList.add(localName);
        attribList.add(new javax.xml.namespace.QName("","ItemPath"));
        attribList.add(localItemPath);
        attribList.add(new javax.xml.namespace.QName("","ItemName"));
        attribList.add(localItemName);
        attribList.add(new javax.xml.namespace.QName("",IS_ITEM));
        attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIsItem));
        attribList.add(new javax.xml.namespace.QName("",HAS_CHILDREN));
        attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHasChildren));
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
         * @param reader - the <code>XMLStreamReader</code> to use to parse the xml
         * 
         * @return the <code>BrowseElement</code> parsed
         * 
         * @throws Exception - if something goes wrong parsing the xml
         *
         */
        public static BrowseElement parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            BrowseElement object =
                    new BrowseElement();
            try {
                GenericFactory.goToNextStartElement(reader);
                BrowseElement result = (BrowseElement) checkADBBeanType(NAME,reader); 
                if(result!=null) {
                    return result;
                }
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.List<String> handledAttributes = new java.util.ArrayList<String>();
                // handle attribute "Name"
                GenericFactory.handleName(reader.getAttributeValue(null,"Name"),object,handledAttributes);
                // handle attribute "ItemPath"
                GenericFactory.handleItemPath(reader.getAttributeValue(null,"ItemPath"),object,handledAttributes);
                // handle attribute "ItemName"
                GenericFactory.handleItemName(reader.getAttributeValue(null,"ItemName"),object,handledAttributes);
                // handle attribute "IsItem"
                String tempAttribIsItem = reader.getAttributeValue(null,IS_ITEM);
                if (tempAttribIsItem!=null) {
                    object.setIsItem(
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(tempAttribIsItem));
                } else {
                    throw new org.apache.axis2.databinding.ADBException("Required attribute IsItem is missing");
                }
                handledAttributes.add(IS_ITEM);
                // handle attribute "HasChildren"
                String tempAttribHasChildren = reader.getAttributeValue(null,HAS_CHILDREN);
                if (tempAttribHasChildren!=null) {
                    object.setHasChildren(
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(tempAttribHasChildren));
                } else {
                    throw new org.apache.axis2.databinding.ADBException("Required attribute HasChildren is missing");
                }
                handledAttributes.add(HAS_CHILDREN);
                reader.next();
                GenericFactory.readItemProperties(reader, object);
                GenericFactory.goToAndCheckUnexpectedStartElements(reader);
            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }
            return object;
        }
    }//end of factory class
}