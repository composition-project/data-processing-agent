package eu.ebbits.pwal.impl.driver.opcxmlda.stub;


/**
 * Stub class used for the Item Identifier
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 * 
 * 
 * @author     ISMB
 * @version    %I%, %G%
 * @since      PWAL 0.2.0
 */ 
public class ItemIdentifier extends ADBBeanImplementation {
    /* This type was generated from the piece of schema that had
         name = ItemIdentifier
         Namespace URI = http://opcfoundation.org/webservices/XMLDA/1.0/
         Namespace Prefix = ns1
     */

    /**
     * 
     */
    private static final long serialVersionUID = -2436768478300842836L;

    /* static reference to the name of this class*/
    private static final String NAME = ItemIdentifier.class.getSimpleName();
    
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


    @Override
    protected void writeAttributes(String prefix,String namespace, org.apache.axiom.om.OMFactory factory, org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) 
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
        writeAttribute("", "ItemPath", localItemPath, xmlWriter);
        writeAttribute("", "ItemName", localItemName, xmlWriter);
        xmlWriter.writeEndElement();
    }


    @Override
    public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException{


        java.util.ArrayList<Object> elementList = new java.util.ArrayList<Object>();
        java.util.ArrayList<Object> attribList = new java.util.ArrayList<Object>();

        attribList.add(new javax.xml.namespace.QName("","ItemPath"));
        attribList.add(localItemPath);
        attribList.add(new javax.xml.namespace.QName("","ItemName"));
        attribList.add(localItemName);
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
         * @return the <code>ItemIdentifier</code> parsed
         * 
         * @throws Exception - if something goes wrong parsing the xml
         * 
         */
        public static ItemIdentifier parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            ItemIdentifier object = new ItemIdentifier();
            try {
                GenericFactory.goToNextStartElement(reader);
                ItemIdentifier result = (ItemIdentifier) checkADBBeanType(NAME,reader); 
                if(result!=null) {
                    return result;
                }
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.List<String> handledAttributes = new java.util.ArrayList<String>();
                // handle attribute "ItemPath"
                GenericFactory.handleItemPath(reader.getAttributeValue(null,"ItemPath"),object,handledAttributes);
                // handle attribute "ItemName"
                GenericFactory.handleItemName(reader.getAttributeValue(null,"ItemName"),object,handledAttributes);
                reader.next();
            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }
            return object;
        }

    }//end of factory class



}
