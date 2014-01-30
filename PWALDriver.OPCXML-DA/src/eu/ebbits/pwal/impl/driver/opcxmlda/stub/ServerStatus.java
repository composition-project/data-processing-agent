package eu.ebbits.pwal.impl.driver.opcxmlda.stub;


/**
 * Stub class used for the ServerStatus request
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 * 
 * 
 * @author     ISMB
 * @version    %I%, %G%
 * @since      PWAL 0.2.0
 */ 
public class ServerStatus extends ADBBeanImplementation {
    /* This type was generated from the piece of schema that had
         name = ServerStatus
         Namespace URI = http://opcfoundation.org/webservices/XMLDA/1.0/
         Namespace Prefix = ns1
     */

    /**
     * 
     */
    private static final long serialVersionUID = -3130101106287367328L;

    
    @Override
    protected String getClassName() {
        return this.getClass().getSimpleName();
    }

    
    //===========literal constants===========
    private static final String START_TIME = "StartTime";
    
    private static final String PRODUCT_VERSION = "ProductVersion";
    
    private static final String SUPPORTED_LOCALE_IDS = "SupportedLocaleIDs";
    
    private static final String SUPPORTED_INTERFACE_VERSIONS = "SupportedInterfaceVersions";
    
    /**
     * field for StatusInfo
     */
    private String localStatusInfo ;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    private boolean localStatusInfoTracker = false ;


    /**
     * Retrieves the status info
     * 
     * @return  a <code>String</code> containing the status info
     */
    public String getStatusInfo() {
        return localStatusInfo;
    }



    /**
     * Sets the status info
     * 
     * @param param - the status info to be set as <code>String</code>
     * 
     */
    public void setStatusInfo(String param) {
        if (param != null) {
            //update the setting tracker
            localStatusInfoTracker = true;
        } else {
            localStatusInfoTracker = false;
        }
        this.localStatusInfo=param;
    }


    /**
     * field for VendorInfo
     */
    private String localVendorInfo ;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    private boolean localVendorInfoTracker = false ;


    /**
     * Returns the vendor info
     * 
     * @return   the vendor info as <code>String</code>
     */
    public String getVendorInfo() {
        return localVendorInfo;
    }



    /**
     * Sets the vendor info
     * 
     * @param param - vendor info to be set as <code>String</code>
     */
    public void setVendorInfo(String param) {
        if (param != null) {
            //update the setting tracker
            localVendorInfoTracker = true;
        } else {
            localVendorInfoTracker = false;
        }
        this.localVendorInfo=param;
    }


    /**
     * field for SupportedLocaleIDs
     * This was an Array!
     */
    private String[] localSupportedLocaleIDs ;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    private boolean localSupportedLocaleIDsTracker = false ;


    /**
     * Returns the list of the IDs of the supported locale
     * 
     * @return    a <code>String[]</code> reppresenting the list of the IDs of the supported locale
     *
     */
    public  String[] getSupportedLocaleIDs() {
        return localSupportedLocaleIDs;
    }


    /**
     * Validates the array for SupportedLocaleIDs
     * 
     * @param param - the array to be validated
     *
     */
    protected void validateSupportedLocaleIDs(String[] param) {
    }


    /**
     * Sets the list of IDs of the supported locale
     * 
     * @param param - list of IDs to be set as <code>String[]</code> 
     * 
     */
    public void setSupportedLocaleIDs(String[] param) {
        String[] supportedLocaleIDsToUse = param.clone();
        validateSupportedLocaleIDs(supportedLocaleIDsToUse);
        if (supportedLocaleIDsToUse != null) {
            //update the setting tracker
            localSupportedLocaleIDsTracker = true;
        } else {
            localSupportedLocaleIDsTracker = false;
        }
        this.localSupportedLocaleIDs=supportedLocaleIDsToUse;
    }



    /**
     * Adds an ID to the list of supported locale
     * 
     * @param param - ID to be added as <code>String</code>
     */
    public void addSupportedLocaleIDs(String param) {
        if (localSupportedLocaleIDs == null) {
            localSupportedLocaleIDs = new String[]{};
        }
        //update the setting tracker
        localSupportedLocaleIDsTracker = true;
        java.util.List<String> list =
                org.apache.axis2.databinding.utils.ConverterUtil.toList(localSupportedLocaleIDs);
        list.add(param);
        this.localSupportedLocaleIDs =
                (String[])list.toArray(
                        new String[list.size()]);
    }


    /**
     * field for SupportedInterfaceVersions
     * This was an Array!
     */
    private InterfaceVersion[] localSupportedInterfaceVersions ;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    private boolean localSupportedInterfaceVersionsTracker = false ;


    /**
     * Returns the list of supported interface versions
     * 
     * @return   the list of supported interface versions as <code>InterfaceVersion[]</code>
     */
    public  InterfaceVersion[] getSupportedInterfaceVersions() {
        return localSupportedInterfaceVersions;
    }


    /**
     * Validates the array for SupportedInterfaceVersions
     * 
     * @param param - the array to be validated
     */
    protected void validateSupportedInterfaceVersions(InterfaceVersion[] param) { 
    }


    /**
     * Sets the list of supported interface versions
     * 
     * 
     * @param param - the list of supported interface versions to be set as <code>InterfaceVersion[]</code>
     */
    public void setSupportedInterfaceVersions(InterfaceVersion[] param) {
        InterfaceVersion[] supportedInterfaceVersionToUse = param.clone();
        validateSupportedInterfaceVersions(supportedInterfaceVersionToUse);
        if (supportedInterfaceVersionToUse != null) {
            //update the setting tracker
            localSupportedInterfaceVersionsTracker = true;
        } else {
            localSupportedInterfaceVersionsTracker = false;
        }
        this.localSupportedInterfaceVersions=supportedInterfaceVersionToUse;
    }



    /**
     * Adds a version to the list of supported interface versions
     * 
     * @param param - version to be added as <code>InterfaceVersion</code>
     */
    public void addSupportedInterfaceVersions(InterfaceVersion param) {
        if (localSupportedInterfaceVersions == null) {
            localSupportedInterfaceVersions = new InterfaceVersion[]{};
        }
        //update the setting tracker
        localSupportedInterfaceVersionsTracker = true;
        java.util.List<InterfaceVersion> list =
                org.apache.axis2.databinding.utils.ConverterUtil.toList(localSupportedInterfaceVersions);
        list.add(param);
        this.localSupportedInterfaceVersions =
                (InterfaceVersion[])list.toArray(
                        new InterfaceVersion[list.size()]);

    }


    /**
     * field for StartTime
     * This was an Attribute!
     */
    private java.util.Calendar localStartTime ;


    /**
     * Returns the start time
     * 
     * @return   the start time as <code>java.util.Calendar</code>
     */
    public java.util.Calendar getStartTime() {
        return localStartTime;
    }



    /**
     * Sets the start time
     * 
     * @param param - start time to be set as <code>java.util.Calendar</code>
     */
    public void setStartTime(java.util.Calendar param) {
        this.localStartTime=param;
    }


    /**
     * field for ProductVersion
     * This was an Attribute!
     */
    private String localProductVersion ;


    /**
     * Returns the product version
     * 
     * @return   the product version as <code>String</code>
     */
    public  String getProductVersion() {
        return localProductVersion;
    }



    /**
     * Sets the product version
     * 
     * @param param - the product version to be set as <code>String</code>
     */
    public void setProductVersion(String param) {
        this.localProductVersion=param;
    }

    
    
    @Override
    protected void writeAttributes(String prefix,String namespace, org.apache.axiom.om.OMFactory factory, org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) 
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
        if (localStartTime != null) {
            writeAttribute("",
                    START_TIME,
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localStartTime), xmlWriter);
        }
        else {
            throw new org.apache.axis2.databinding.ADBException("required attribute localStartTime is null");
        }
        writeAttribute("", PRODUCT_VERSION, localProductVersion, xmlWriter);
        writeAttributeWithNamespace(localStatusInfoTracker,"StatusInfo",localStatusInfo,xmlWriter);
        writeAttributeWithNamespace(localVendorInfoTracker,"VendorInfo",localVendorInfo,xmlWriter);
        writeAttributesWithNamespace(localSupportedLocaleIDsTracker,SUPPORTED_LOCALE_IDS,localSupportedLocaleIDs,xmlWriter);
        serializeAttributes(localSupportedInterfaceVersionsTracker,localSupportedInterfaceVersions,SUPPORTED_INTERFACE_VERSIONS,factory,xmlWriter);
        xmlWriter.writeEndElement();
    }


    @Override
    public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException{

        java.util.ArrayList<Object> elementList = new java.util.ArrayList<Object>();
        java.util.ArrayList<Object> attribList = new java.util.ArrayList<Object>();

        addItem(localStatusInfoTracker,localStatusInfo,"StatusInfo",elementList);
        addItem(localVendorInfoTracker,localVendorInfo,"VendorInfo",elementList);
        addItems(localSupportedLocaleIDsTracker,localSupportedLocaleIDs,SUPPORTED_LOCALE_IDS,elementList);
        addItems(localSupportedInterfaceVersionsTracker,localSupportedInterfaceVersions,SUPPORTED_INTERFACE_VERSIONS,elementList);
        attribList.add(new javax.xml.namespace.QName("",START_TIME));
        attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localStartTime));
        attribList.add(new javax.xml.namespace.QName("",PRODUCT_VERSION));
        attribList.add(localProductVersion);
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
         * @return the <code>ServerStatus</code> parsed
         * 
         * @throws Exception - if something goes wrong parsing the xml
         *
         */
        public static ServerStatus parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            ServerStatus object = new ServerStatus();
            try {
                GenericFactory.goToNextStartElement(reader);
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null) {
                    String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                            "type");
                    if (fullTypeName!=null) {
                        String nsPrefix = null;
                        if (fullTypeName.indexOf(':') > -1) {
                            nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(':'));
                        }
                        nsPrefix = nsPrefix==null?"":nsPrefix;
                        String type = fullTypeName.substring(fullTypeName.indexOf(':')+1);
                        if (!"ServerStatus".equals(type)) {
                            //find namespace for the prefix
                            String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                            return (ServerStatus)ExtensionMapper.getTypeObject(
                                    nsUri,type,reader);
                        }
                    }
                }
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.List<String> handledAttributes = new java.util.ArrayList<String>();
                // handle attribute "StartTime"
                String tempAttribStartTime = reader.getAttributeValue(null,START_TIME);
                if (tempAttribStartTime!=null) {
                    object.setStartTime(
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(tempAttribStartTime));
                } else {
                    throw new org.apache.axis2.databinding.ADBException("Required attribute StartTime is missing");
                }
                handledAttributes.add(START_TIME);
                // handle attribute "ProductVersion"
                String tempAttribProductVersion = reader.getAttributeValue(null,PRODUCT_VERSION);
                if (tempAttribProductVersion!=null) {
                    object.setProductVersion(
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(tempAttribProductVersion));
                }
                handledAttributes.add(PRODUCT_VERSION);
                reader.next();
                java.util.ArrayList <Object> list3 = new java.util.ArrayList<Object>();
                java.util.ArrayList <Object> list4 = new java.util.ArrayList<Object>();
                GenericFactory.goToNextStartElement(reader);
                if (reader.isStartElement() && new javax.xml.namespace.QName(OPC_NAMESPACE,"StatusInfo").equals(reader.getName())) {
                    String content = reader.getElementText();
                    object.setStatusInfo(
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                    reader.next();
                }  // End of if for expected property start element
                GenericFactory.goToNextStartElement(reader);
                if (reader.isStartElement() && new javax.xml.namespace.QName(OPC_NAMESPACE,"VendorInfo").equals(reader.getName())) {
                    String content = reader.getElementText();
                    object.setVendorInfo(
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                    reader.next();
                }  // End of if for expected property start element
                GenericFactory.goToNextStartElement(reader);
                if (reader.isStartElement() && new javax.xml.namespace.QName(OPC_NAMESPACE,SUPPORTED_LOCALE_IDS).equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    list3.add(reader.getElementText());
                    //loop until we find a start element that is not part of this array
                    boolean loopDone3 = false;
                    while(!loopDone3) {
                        loopDone3 = GenericFactory.findStartElementNotPartOfTheArray(SUPPORTED_LOCALE_IDS,reader);
                        if(!loopDone3) {
                                list3.add(reader.getElementText());
                        }
                    }
                    // call the converter utility  to convert and set the array
                    object.setSupportedLocaleIDs((String[])
                            list3.toArray(new String[list3.size()]));
                }  // End of if for expected property start element
                GenericFactory.goToNextStartElement(reader);
                if (reader.isStartElement() && new javax.xml.namespace.QName(OPC_NAMESPACE,SUPPORTED_INTERFACE_VERSIONS).equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    list4.add(InterfaceVersion.Factory.parse(reader));
                    //loop until we find a start element that is not part of this array
                    boolean loopDone4 = false;
                    while(!loopDone4) {
                        loopDone4 = GenericFactory.findStartElementNotPartOfTheArray(SUPPORTED_INTERFACE_VERSIONS,reader);
                        if(!loopDone4) {
                            list4.add(InterfaceVersion.Factory.parse(reader));
                        }
                    }
                    // call the converter utility  to convert and set the array
                    object.setSupportedInterfaceVersions((InterfaceVersion[])
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                    InterfaceVersion.class,
                                    list4));

                }  // End of if for expected property start element
                GenericFactory.goToAndCheckUnexpectedStartElements(reader);
            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }
            return object;
        }
    }//end of factory class
}

