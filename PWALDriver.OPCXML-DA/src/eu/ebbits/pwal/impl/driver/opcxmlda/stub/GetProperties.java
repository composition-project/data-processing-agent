package eu.ebbits.pwal.impl.driver.opcxmlda.stub;

/**
 * Stub class used for GetProperties request
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 * 
 * 
 * @author     ISMB
 * @version    %I%, %G%
 * @since      PWAL 0.2.0
 */ 
public class GetProperties extends ADBBeanImplementation {

    /**
     * 
     */
    private static final long serialVersionUID = 4920469778867531310L;

    /* QNAME for this class */
    public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
            OPC_NAMESPACE,
            "GetProperties",
            OPC_PREFIX);

    /* static reference to the name of this class*/
    private static final String NAME = GetProperties.class.getSimpleName();
    
    @Override
    protected String getClassName() {
        return this.getClass().getSimpleName();
    }

    
    //==========literal constants=========
    private static final String ITEM_IDS = "ItemIDs";
    
    /**
     * field for ItemPath
     * This was an Attribute!
     */    
    private String localItemPath;
    
    
    /**
     * field for LocaleID
     * This was an Attribute!
     */
    private String localLocaleID ;

    
    /**
     * field for ClientRequestHandle
     * This was an Attribute!
     */
    private String localClientRequestHandle;
    
    /**
     * field for ItemIDs
     * This was an Array!
     */
    private ItemIdentifier[] localItemIDs ;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    private boolean localItemIDsTracker = false ;


    /**
     * Retrieves the list of Item IDS
     * 
     * @return  the list of Item IDs as <code>ItemIdentifier[]</code>
     */
    public  ItemIdentifier[] getItemIDs() {
        return localItemIDs;
    }

    /**
     * Validates the array for ItemIDs
     * 
     * @param param - the array to validate
     */
    private void validateItemIDs(ItemIdentifier[] param) {
    }


    /**
     * Sets the array passed as parameter as value of ItemIDs
     * 
     * @param param - array containing the <code>ItemIdentifier</code>s to be set
     */
    public void setItemIDs(ItemIdentifier[] param) {
        ItemIdentifier[] itemIDsToUse = param.clone();
        validateItemIDs(itemIDsToUse);
        if (itemIDsToUse != null) {
            //update the setting tracker
            localItemIDsTracker = true;
        } else {
            localItemIDsTracker = false;
        }
        this.localItemIDs=itemIDsToUse;
    }



    /**
     * Adds an <code>ItemIdentifier<code> to the list of ItemIDs
     * 
     * @param param - <code>ItemIdentifier</code> to be added
     */
    public void addItemIDs(ItemIdentifier param) {
        if (localItemIDs == null) {
            localItemIDs = new ItemIdentifier[]{};
        }
        //update the setting tracker
        localItemIDsTracker = true;
        java.util.List <ItemIdentifier>list =
                org.apache.axis2.databinding.utils.ConverterUtil.toList(localItemIDs);
        list.add(param);
        this.localItemIDs =
                (ItemIdentifier[])list.toArray(
                        new ItemIdentifier[list.size()]);
    }


    /**
     * field for PropertyNames
     * This was an Array!
     */
    private javax.xml.namespace.QName[] localPropertyNames ;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    private boolean localPropertyNamesTracker = false;
    

    /**
     * field for ReturnAllProperties
     * This was an Attribute!
     */
    private boolean localReturnAllProperties ;


    /**
     * field for ReturnPropertyValues
     * This was an Attribute!
     */
    private boolean localReturnPropertyValues ;


    /**
     * Auto generated getter method
     * @return boolean
     */
    public boolean getReturnPropertyValues() {
        return localReturnPropertyValues;
    }


    /**
     * field for ReturnErrorText
     * This was an Attribute!
     */
    private boolean localReturnErrorText ;



    @Override
    protected void writeAttributes(String prefix,String namespace, org.apache.axiom.om.OMFactory factory, org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) 
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
        writeAttribute("", "LocaleID", localLocaleID, xmlWriter);
        writeAttribute("", "ClientRequestHandle", localClientRequestHandle, xmlWriter);
        writeAttribute("", "ItemPath", localItemPath, xmlWriter);
        writeAttribute("",
                "ReturnAllProperties",
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReturnAllProperties), xmlWriter);
        writeAttribute("",
                "ReturnPropertyValues",
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReturnPropertyValues), xmlWriter);
        writeAttribute("",
                "ReturnErrorText",
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReturnErrorText), xmlWriter);
        serializeAttributes(localItemIDsTracker,localItemIDs,ITEM_IDS,factory,xmlWriter);
        writeAttributesWithNamespace(localPropertyNamesTracker,"PropertyNames",localPropertyNames,xmlWriter);
        xmlWriter.writeEndElement();
    }


    @Override
    public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException{


        java.util.ArrayList<Object> elementList = new java.util.ArrayList<Object>();
        java.util.ArrayList<Object> attribList = new java.util.ArrayList<Object>();

        addItems(localItemIDsTracker,localItemIDs,ITEM_IDS,elementList);
        addItems(localPropertyNamesTracker,localPropertyNames,"PropertyNames",elementList);
        attribList.add(new javax.xml.namespace.QName("","LocaleID"));
        attribList.add(localLocaleID);
        attribList.add(new javax.xml.namespace.QName("","ClientRequestHandle"));
        attribList.add(localClientRequestHandle);
        attribList.add(new javax.xml.namespace.QName("","ItemPath"));
        attribList.add(localItemPath);
        attribList.add(new javax.xml.namespace.QName("","ReturnAllProperties"));
        attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReturnAllProperties));
        attribList.add(new javax.xml.namespace.QName("","ReturnPropertyValues"));
        attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReturnPropertyValues));
        attribList.add(new javax.xml.namespace.QName("","ReturnErrorText"));
        attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReturnErrorText));
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
         * @return the <code>GetProperties</code> parsed
         * 
         * @throws Exception - if something goes wrong parsing the xml
         */
        public static GetProperties parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            GetProperties object = new GetProperties();
            try {
                GenericFactory.goToNextStartElement(reader);
                GetProperties result = (GetProperties) checkADBBeanType(NAME,reader); 
                if(result!=null) {
                    return result;
                }
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.List<String> handledAttributes = new java.util.ArrayList<String>();
                // handle attribute "LocaleID"
                GenericFactory.handleLocaleID(reader.getAttributeValue(null,"LocaleID"), object, handledAttributes);
                // handle attribute "ClientRequestHandle"
                GenericFactory.handleClientRequestHandle(reader.getAttributeValue(null,"ClientRequestHandle"), object, handledAttributes);
                // handle attribute "ItemPath"
                GenericFactory.handleItemPath(reader.getAttributeValue(null,"ItemPath"), object, handledAttributes);
                // handle attribute "ReturnAllProperties"
                GenericFactory.handleReturnAllProperties(reader.getAttributeValue(null,"ReturnAllProperties"), object, handledAttributes);
                // handle attribute "ReturnPropertyValues"
                GenericFactory.handleReturnPropertyValues(reader.getAttributeValue(null,"ReturnPropertyValues"), object, handledAttributes);
                // handle attribute "ReturnErrorText"
                GenericFactory.handleReturnErrorText(reader.getAttributeValue(null,"ReturnErrorText"), object, handledAttributes);
                reader.next();
                java.util.ArrayList<Object> list1 = new java.util.ArrayList<Object>();
                GenericFactory.goToNextStartElement(reader);
                if (reader.isStartElement() && new javax.xml.namespace.QName(OPC_NAMESPACE,ITEM_IDS).equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    list1.add(ItemIdentifier.Factory.parse(reader));
                    //loop until we find a start element that is not part of this array
                    boolean loopDone1 = false;
                    while(!loopDone1) {
                        loopDone1 = GenericFactory.findStartElementNotPartOfTheArray(ITEM_IDS,reader);
                        if(!loopDone1) {
                            list1.add(ItemIdentifier.Factory.parse(reader));
                        }
                    }
                    // call the converter utility  to convert and set the array
                    object.setItemIDs((ItemIdentifier[])
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                    ItemIdentifier.class,
                                    list1));

                }  // End of if for expected property start element
                GenericFactory.readPropertyNamesElement(reader,object);
                GenericFactory.checkUnexpectedStartElements(reader);
            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }
            return object;
        }
    }//end of factory class
}