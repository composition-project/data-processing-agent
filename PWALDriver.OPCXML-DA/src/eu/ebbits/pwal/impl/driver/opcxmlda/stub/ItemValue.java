package eu.ebbits.pwal.impl.driver.opcxmlda.stub;

/**
 * Stub class used for ItemValue
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 * 
 * 
 * @author     ISMB
 * @version    %I%, %G%
 * @since      PWAL 0.2.0
 */
public class ItemValue extends ADBBeanImplementation {
    /* This type was generated from the piece of schema that had
         name = ItemValue
         Namespace URI = http://opcfoundation.org/webservices/XMLDA/1.0/
         Namespace Prefix = ns1
     */
    
    /**
     * 
     */
    private static final long serialVersionUID = 6478418555574777804L;

    /* static reference to the name of this class*/
    private static final String NAME = ItemValue.class.getSimpleName();

    @Override
    protected String getClassName() {
        return this.getClass().getSimpleName();
    }

    
    //===========literal constants===========
    private static final String TIMESTAMP = "Timestamp";
    
    private static final String VALUE_TYPE_QUALIFIER = "ValueTypeQualifier";
    
    
    /**
     * field for IteName
     * This was an Attribute!
     */
    private String localItemName;
    
    /**
     * field for ItemPath
     * This was an Attribute!
     */
    private String localItemPath;
    
    
    /**
     * field for DiagnosticInfo
     */
    private String localDiagnosticInfo ;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    private boolean localDiagnosticInfoTracker = false ;


    /**
     * Retrieves the diagnostic info
     * 
     * @return    a <code>String</code> containing the diagnostic info 
     */
    public  String getDiagnosticInfo() {
        return localDiagnosticInfo;
    }



    /**
     * Sets the diagnostic info
     * 
     * @param param - the <code>DiagnosticInfo</code> to be set
     */
    public void setDiagnosticInfo(String param) {
        if (param != null) {
            //update the setting tracker
            localDiagnosticInfoTracker = true;
        } else {
            localDiagnosticInfoTracker = false;
        }
        this.localDiagnosticInfo=param;
    }


    /**
     * field for Value
     */
    private org.apache.axiom.om.OMElement localValue ;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    private boolean localValueTracker = false ;


    /**
     * Auto generated getter method
     * @return org.apache.axiom.om.OMElement
     */
    public  org.apache.axiom.om.OMElement getValue() {
        return localValue;
    }



    


    /**
     * field for Quality
     */
    private OPCQuality localQuality ;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    private boolean localQualityTracker = false ;


    /**
     * Gets the OPC quality of this value
     * 
     * @return     an <code>OPCQuality</code> containing the OPC quality
     */
    public  OPCQuality getQuality() {
        return localQuality;
    }



    /**
     * Sets the OPC quality of this value
     * 
     * @param param - the OPC quality to be set as <code>OPCQuality</code object
     */
    public void setQuality(OPCQuality param) {
        if (param != null) {
            //update the setting tracker
            localQualityTracker = true;
        } else {
            localQualityTracker = false;
        }
        this.localQuality=param;
    }


    /**
     * field for ClientItemHandle
     * This was an Attribute!
     */
    private String localClientItemHandle ;


    /**
     * field for Timestamp
     * This was an Attribute!
     */
    private java.util.Calendar localTimestamp ;


    /**
     * Gets the timestamp of this value
     * 
     * @return    the timestamp of this value as <code>java.util.Calendar</code>
     */
    public  java.util.Calendar getTimestamp() {
        return localTimestamp;
    }



    /**
     * Sets the timestamp for this value
     * 
     * @param param - the timestamp to be set as <code>java.util.Calendar</code>
     */
    public void setTimestamp(java.util.Calendar param) {
        this.localTimestamp=param;
    }


    /**
     * field for ResultID
     * This was an Attribute!
     */
    private javax.xml.namespace.QName localResultID ;



    /**
     * field for ValueTypeQualifier
     * This was an Attribute!
     */
    private javax.xml.namespace.QName localValueTypeQualifier ;


    /**
     * Auto generated getter method
     * @return javax.xml.namespace.QName
     */
    public  javax.xml.namespace.QName getValueTypeQualifier() {
        return localValueTypeQualifier;
    }



    /**
     * Auto generated setter method
     * @param param ValueTypeQualifier
     */
    public void setValueTypeQualifier(javax.xml.namespace.QName param) {
        this.localValueTypeQualifier=param;
    }


    @Override
    protected void writeAttributes(String prefix,String namespace, org.apache.axiom.om.OMFactory factory, org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) 
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
        writeAttribute("", "ItemPath", localItemPath, xmlWriter);
        writeAttribute("", "ItemName", localItemName, xmlWriter);
        writeAttribute("", "ClientItemHandle", localClientItemHandle, xmlWriter);
        if (localTimestamp != null) {
            writeAttribute("",
                    TIMESTAMP,
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTimestamp), xmlWriter);
        }
        writeQNameAttribute("", "ResultID", localResultID, xmlWriter);
        writeQNameAttribute("", VALUE_TYPE_QUALIFIER, localValueTypeQualifier, xmlWriter);
        writeAttributeWithNamespace(localDiagnosticInfoTracker,"DiagnosticInfo",localDiagnosticInfo,xmlWriter);
        writeAttributeWithNamespace(localValueTracker,"Value",localValue,xmlWriter);
        serializeAttribute(localQualityTracker,localQuality,"Quality",factory,xmlWriter);
        xmlWriter.writeEndElement();
    }


    @Override
    public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException{

        java.util.ArrayList<Object> elementList = new java.util.ArrayList<Object>();
        java.util.ArrayList<Object> attribList = new java.util.ArrayList<Object>();

        addItem(localDiagnosticInfoTracker,localDiagnosticInfo,"DiagnosticInfo",elementList);
        addItem(localValueTracker,localValue,"Value",elementList);
        addItem(localQualityTracker,localQuality,"Quality",elementList);
        attribList.add(new javax.xml.namespace.QName("","ItemPath"));
        attribList.add(localItemPath);
        attribList.add(new javax.xml.namespace.QName("","ItemName"));
        attribList.add(localItemName);
        attribList.add(new javax.xml.namespace.QName("","ClientItemHandle"));
        attribList.add(localClientItemHandle);
        attribList.add(new javax.xml.namespace.QName("",TIMESTAMP));
        attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTimestamp));
        attribList.add(new javax.xml.namespace.QName("","ResultID"));
        attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localResultID));
        attribList.add(new javax.xml.namespace.QName("",VALUE_TYPE_QUALIFIER));
        attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localValueTypeQualifier));
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
         * @return the <code>ItemValue</code> parsed
         * 
         * @throws Exception - if something goes wrong parsing the xml
         *
         */
        public static ItemValue parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            ItemValue object = new ItemValue();
            String prefix ="";
            String namespaceuri ="";
            try {
                GenericFactory.goToNextStartElement(reader);
                ItemValue result = (ItemValue) checkADBBeanType(NAME,reader); 
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
                // handle attribute "ClientItemHandle"
                GenericFactory.handleClientItemHandle(reader.getAttributeValue(null,"ClientItemHandle"),object,handledAttributes);
                // handle attribute "Timestamp"
                String tempAttribTimestamp = reader.getAttributeValue(null,TIMESTAMP);
                if (tempAttribTimestamp!=null) {
                    object.setTimestamp(
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(tempAttribTimestamp));
                }
                handledAttributes.add(TIMESTAMP);
                // handle attribute "ResultID"
                GenericFactory.handleResultID(reader.getAttributeValue(null,"ResultID"),object,handledAttributes,reader);
                // handle attribute "ValueTypeQualifier"
                String tempAttribValueTypeQualifier = reader.getAttributeValue(null,VALUE_TYPE_QUALIFIER);
                if (tempAttribValueTypeQualifier!=null) {
                    int index = tempAttribValueTypeQualifier.indexOf(':');
                    if(index > -1) {
                        prefix = tempAttribValueTypeQualifier.substring(0,index);
                    } else {
                        // i.e this is in default namesace
                        prefix = "";
                    }
                    namespaceuri = reader.getNamespaceURI(prefix);
                    object.setValueTypeQualifier(
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToQName(tempAttribValueTypeQualifier,namespaceuri));
                }
                handledAttributes.add(VALUE_TYPE_QUALIFIER);
                reader.next();
                GenericFactory.goToNextStartElement(reader);
                if (reader.isStartElement() && new javax.xml.namespace.QName(OPC_NAMESPACE,"DiagnosticInfo").equals(reader.getName())) {
                    String content = reader.getElementText();
                    object.setDiagnosticInfo(
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                    reader.next();
                }  // End of if for expected property start element
                GenericFactory.goToNextStartElement(reader);
                if (reader.isStartElement()) {
                    object.setValue(GenericFactory.createOMElement(reader));
                    reader.next(); 
                }  // End of if for expected property start element
                GenericFactory.goToNextStartElement(reader);
                if (reader.isStartElement() && new javax.xml.namespace.QName(OPC_NAMESPACE,"Quality").equals(reader.getName())) {
                    object.setQuality(OPCQuality.Factory.parse(reader));
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
