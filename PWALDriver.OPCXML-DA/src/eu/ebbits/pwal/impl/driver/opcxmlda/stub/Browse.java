package eu.ebbits.pwal.impl.driver.opcxmlda.stub;

/**
 * Stub class used for the Browse request
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 * 
 * 
 * @author     ISMB
 * @version    %I%, %G%
 * @since      PWAL 0.2.0
 */ 
public class Browse extends ADBBeanImplementation {

    /**
     * 
     */
    private static final long serialVersionUID = 2886804335955344724L;

    /* static name of the class */
    private static final String NAME = Browse.class.getSimpleName();
    
    /* QNAME for this class */
    public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
            OPC_NAMESPACE,
            "Browse",
            OPC_PREFIX);

    
    @Override
    protected String getClassName() {
        return this.getClass().getSimpleName();
    }
    
    
    //==========literal constants=========
    private static final String MAX_ELEMENTS_RETURNED = "MaxElementsReturned";

    private static final String BROWSE_FILTER = "BrowseFilter";
    
    private static final String ELEMENT_FILTER = "ElementNameFilter";
    
    private static final String VENDOR_FILTER = "VendorFilter";

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
     * field for ContinuationPoint
     * This was an Attribute!
     */
    private String localContinuationPoint ;


    /**
     * field for ClientRequestHandle
     * This was an Attribute!
     */
    private String localClientRequestHandle ;

    /**
     * field for LocaleID
     * This was an Attribute!
     */
    private String localLocaleID ;


    /**
     * field for MaxElementsReturned
     * This was an Attribute!
     */
    private int localMaxElementsReturned ;


    /**
     * Retrieves the max number of elements returned
     * 
     * @return     the max number of elements returned as <code>int</code>
     */
    public int getMaxElementsReturned() {
        return localMaxElementsReturned;
    }



    /**
     * Sets the max number of elements returned
     * 
     * @param param - the max number of elements returned to set as <code>int</code>
     */
    public void setMaxElementsReturned(int param) {
        this.localMaxElementsReturned=param;
    }


    /**
     * field for BrowseFilter
     * This was an Attribute!
     */
    private BrowseFilter localBrowseFilter ;


    /**
     * Retrieves the <code>BrowseFilter</code> object used
     * 
     * @return   the <code>BrowseFilter</code> object used 
     */
    public  BrowseFilter getBrowseFilter() {
        return localBrowseFilter;
    }



    /**
     * Sets the <code>BrowseFilter</code> object to use
     * 
     * @param param - the <code>BrowseFilter</code> object to set
     */
    public void setBrowseFilter(BrowseFilter param) {
        this.localBrowseFilter=param;
    }


    /**
     * field for ElementNameFilter
     * This was an Attribute!
     */
    private String localElementNameFilter ;


    /**
     * Retrieves the Element Name Filter used
     * 
     * @return   a <code>String</code> containing the element name filter used
     */
    public  String getElementNameFilter() {
        return localElementNameFilter;
    }



    /**
     * Sets the Element Name Filter to use
     * 
     * @param param - the Element Name Filter to use as <code>String</code>
     */
    public void setElementNameFilter(String param) {
        this.localElementNameFilter=param;
    }


    /**
     * field for VendorFilter
     * This was an Attribute!
     */
    private String localVendorFilter ;


    /**
     * Retrieves the vendor filter used
     * 
     * @return   the vendor filter used as a <code>String</code>
     */
    public  String getVendorFilter() {
        return localVendorFilter;
    }


    /**
     * Sets the vendor filter to use
     * 
     * @param param - the vendor filter to use as a <code>String</code>
     */
    public void setVendorFilter(String param) {
        this.localVendorFilter=param;
    }


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
     * field for ReturnErrorText
     * This was an Attribute!
     */
    private boolean localReturnErrorText ;


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

    
    @Override
    protected void writeAttributes(String prefix,String namespace, org.apache.axiom.om.OMFactory factory, org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) 
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
        writeAttribute("","LocaleID",localLocaleID, xmlWriter);
        writeAttribute("","ClientRequestHandle", localClientRequestHandle, xmlWriter);
        writeAttribute("","ItemPath",localItemPath, xmlWriter);
        writeAttribute("","ItemName",localItemName, xmlWriter);
        writeAttribute("","ContinuationPoint",localContinuationPoint, xmlWriter);
        if (localMaxElementsReturned!=java.lang.Integer.MIN_VALUE) {
            writeAttribute("",
                    MAX_ELEMENTS_RETURNED,
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMaxElementsReturned), xmlWriter);
        } else {
            throw new org.apache.axis2.databinding.ADBException("required attribute localMaxElementsReturned is null");
        }
        if (localBrowseFilter != null) {
            writeAttribute("",
                    BROWSE_FILTER,
                    localBrowseFilter.toString(), xmlWriter);
        } else {
            throw new org.apache.axis2.databinding.ADBException("required attribute localBrowseFilter is null");
        }
        writeAttribute("",ELEMENT_FILTER,localElementNameFilter, xmlWriter);
        writeAttribute("",VENDOR_FILTER,localVendorFilter, xmlWriter);
        writeAttribute("",
                "ReturnAllProperties",
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReturnAllProperties), xmlWriter);
        writeAttribute("",
                "ReturnPropertyValues",
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReturnPropertyValues), xmlWriter);
        writeAttribute("",
                "ReturnErrorText",
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReturnErrorText), xmlWriter);
        writeAttributesWithNamespace(localPropertyNamesTracker,"PropertyNames",localPropertyNames,xmlWriter);
        xmlWriter.writeEndElement();
    }


    @Override
    public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException{
        java.util.ArrayList<Object> elementList = new java.util.ArrayList<Object>();
        java.util.ArrayList<Object> attribList = new java.util.ArrayList<Object>();
        
        addItems(localPropertyNamesTracker,localPropertyNames,"PropertyNames",elementList);
        attribList.add(new javax.xml.namespace.QName("","LocaleID"));
        attribList.add(localLocaleID);
        attribList.add(new javax.xml.namespace.QName("","ClientRequestHandle"));
        attribList.add(localClientRequestHandle);
        attribList.add(new javax.xml.namespace.QName("","ItemPath"));
        attribList.add(localItemPath);
        attribList.add(new javax.xml.namespace.QName("","ItemName"));
        attribList.add(localItemName);
        attribList.add(new javax.xml.namespace.QName("","ContinuationPoint"));
        attribList.add(localContinuationPoint);
        attribList.add(new javax.xml.namespace.QName("",MAX_ELEMENTS_RETURNED));
        attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMaxElementsReturned));
        attribList.add(new javax.xml.namespace.QName("",BROWSE_FILTER));
        attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBrowseFilter.toString()));
        attribList.add(new javax.xml.namespace.QName("",ELEMENT_FILTER));
        attribList.add(localElementNameFilter);
        attribList.add(new javax.xml.namespace.QName("",VENDOR_FILTER));
        attribList.add(localVendorFilter);
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
    public static class Factory {

        /**
         * static method to create the object
         * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
         *             If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
         * Postcondition: If this object is an element, the reader is positioned at its end element
         *             If this object is a complex type, the reader is positioned at the end element of its outer element
         *
         * @param reader - the <code>javax.xml.stream.XMLStreamReader</code> to use to parse the xml
         * 
         * @return the <code>Browse</code> parsed
         * 
         * @throws Exception - if something goes wrong parsing the xml
         *
         */
        public static Browse parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            Browse object =
                    new Browse();
            try {
                GenericFactory.goToNextStartElement(reader);
                Browse result = (Browse) checkADBBeanType(NAME,reader); 
                if(result!=null) {
                    return result;
                }
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.List <String> handledAttributes = new java.util.ArrayList<String>();
                // handle attribute "LocaleID"
                GenericFactory.handleLocaleID(reader.getAttributeValue(null,"LocaleID"),object,handledAttributes);
                // handle attribute "ClientRequestHandle"
                GenericFactory.handleClientRequestHandle(reader.getAttributeValue(null,"ClientRequestHandle"),object,handledAttributes);
                // handle attribute "ItemPath"
                GenericFactory.handleItemPath(reader.getAttributeValue(null,"ItemPath"),object,handledAttributes);
                // handle attribute "ItemName"
                GenericFactory.handleItemName(reader.getAttributeValue(null,"ItemName"),object,handledAttributes);
                // handle attribute "ContinuationPoint"
                GenericFactory.handleContinuationPoint(reader.getAttributeValue(null,"ContinuationPoint"),object,handledAttributes);
                // handle attribute "MaxElementsReturned"
                String tempAttribMaxElementsReturned = reader.getAttributeValue(null,MAX_ELEMENTS_RETURNED); 
                if (tempAttribMaxElementsReturned!=null) {
                    object.setMaxElementsReturned(
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(tempAttribMaxElementsReturned));
                } else {
                    throw new org.apache.axis2.databinding.ADBException("Required attribute MaxElementsReturned is missing");
                }
                handledAttributes.add(MAX_ELEMENTS_RETURNED);
                // handle attribute "BrowseFilter"
                String tempAttribBrowseFilter = reader.getAttributeValue(null,BROWSE_FILTER);
                if (tempAttribBrowseFilter!=null) {
                    object.setBrowseFilter(
                            BrowseFilter.Factory.fromString(reader,tempAttribBrowseFilter));
                } else {
                    throw new org.apache.axis2.databinding.ADBException("Required attribute BrowseFilter is missing");
                }
                handledAttributes.add(BROWSE_FILTER);
                // handle attribute "ElementNameFilter"
                String tempAttribElementNameFilter = reader.getAttributeValue(null,ELEMENT_FILTER);
                if (tempAttribElementNameFilter!=null) {
                    object.setElementNameFilter(
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(tempAttribElementNameFilter));
                }
                handledAttributes.add(ELEMENT_FILTER);
                // handle attribute "VendorFilter"
                String tempAttribVendorFilter = reader.getAttributeValue(null,VENDOR_FILTER);
                if (tempAttribVendorFilter!=null) {
                    object.setVendorFilter(
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(tempAttribVendorFilter));
                }
                handledAttributes.add(VENDOR_FILTER);
                // handle attribute "ReturnAllProperties"
                GenericFactory.handleReturnAllProperties(reader.getAttributeValue(null,"ReturnAllProperties"),object,handledAttributes);
                // handle attribute "ReturnPropertyValues"
                GenericFactory.handleReturnPropertyValues(reader.getAttributeValue(null,"ReturnPropertyValues"),object,handledAttributes);
                // handle attribute "ReturnErrorText"
                GenericFactory.handleReturnErrorText(reader.getAttributeValue(null,"ReturnErrorText"),object,handledAttributes);
                reader.next();
                GenericFactory.readPropertyNamesElement(reader,object);
                GenericFactory.checkUnexpectedStartElements(reader);
            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }
            return object;
        }
    }//end of factory class
}