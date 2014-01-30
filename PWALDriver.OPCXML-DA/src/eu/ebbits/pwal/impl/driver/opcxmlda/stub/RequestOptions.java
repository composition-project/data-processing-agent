package eu.ebbits.pwal.impl.driver.opcxmlda.stub;

/**
 * Stub class used the options of a request
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 * 
 * 
 * @author     ISMB
 * @version    %I%, %G%
 * @since      PWAL 0.2.0
 */ 
public class RequestOptions extends ADBBeanImplementation {
    /* This type was generated from the piece of schema that had
         name = RequestOptions
         Namespace URI = http://opcfoundation.org/webservices/XMLDA/1.0/
         Namespace Prefix = ns1
     */

    /**
     * 
     */
    private static final long serialVersionUID = -1916007107934337775L;


    /* static name of the class */
    private static final String NAME = RequestOptions.class.getSimpleName();
    
    @Override
    protected String getClassName() {
        return this.getClass().getSimpleName();
    }

    
    //==========literal constants==================
    private static final String REQUEST_DEADLINE = "RequestDeadline";
    
    private static final String RETURN_DIAGNOSTIC_INFO = "ReturnDiagnosticInfo";
    
    private static final String RETURN_ITEM_TIME = "ReturnItemTime";
    
    private static final String RETURN_ITEM_PATH = "ReturnItemPath";
    
    private static final String RETURN_ITEM_NAME = "ReturnItemName";
    
    /**
     * field for RequestDeadline
     * This was an Attribute!
     */
    private java.util.Calendar localRequestDeadline ;


    /**
     * Returns the request deadline
     * 
     * @return   the request deadline as <code>java.util.Calendar</code>
     * 
     */
    public java.util.Calendar getRequestDeadline() {
        return localRequestDeadline;
    }



    /**
     * Sets the request deadline
     * 
     * @param param . the request deadline as <code>java.util.Calendar</code>
     */
    public void setRequestDeadline(java.util.Calendar param) {
        this.localRequestDeadline=param;
    }


    /**
     * field for ReturnErrorText
     * This was an Attribute!
     */
    private boolean localReturnErrorText =
            org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean("true");


    /**
     * field for ReturnDiagnosticInfo
     * This was an Attribute!
     */
    private boolean localReturnDiagnosticInfo =
            org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(FALSE);


    /**
     * Retrieves the return diagnostic info
     * 
     * 
     * @return   the diagnostic info as <code>boolean</code>
     */
    public boolean getReturnDiagnosticInfo() {
        return localReturnDiagnosticInfo;
    }



    /**
     * Sets the return diagnostic info
     * 
     * @param param - the return diagnostic info as <code>boolean</code>
     */
    public void setReturnDiagnosticInfo(boolean param) {
        this.localReturnDiagnosticInfo=param;
    }


    /**
     * field for ReturnItemTime
     * This was an Attribute!
     */
    private boolean localReturnItemTime =
            org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(FALSE);


    /**
     * Retrieves the return item time
     * 
     * 
     * @return   the return item time as a <code>boolean</code>
     */
    public boolean getReturnItemTime() {
        return localReturnItemTime;
    }



    /**
     * Sets the return item time
     * 
     * @param param - the return item time as <code>boolean</code>
     */
    public void setReturnItemTime(boolean param) {
        this.localReturnItemTime=param;
    }


    /**
     * field for ReturnItemPath
     * This was an Attribute!
     */
    private boolean localReturnItemPath =
            org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(FALSE);


    /**
     * Retrieves the return item path
     * 
     * @return   the return item path as <code>boolean</code>
     */
    public boolean getReturnItemPath() {
        return localReturnItemPath;
    }



    /**
     * Sets the return item path
     * 
     * @param param - the return item path to be set as <code>boolean</code>
     */
    public void setReturnItemPath(boolean param) {
        this.localReturnItemPath=param;
    }


    /**
     * field for ReturnItemName
     * This was an Attribute!
     */
    private boolean localReturnItemName =
            org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(FALSE);


    /**
     * Returns the return item name 
     * 
     * @return   the return item name as <code>boolean</code>
     */
    public boolean getReturnItemName() {
        return localReturnItemName;
    }



    /**
     * Sets the return item name
     * 
     * 
     * @param param - the return item name to be set as <code>boolean</code>
     * 
     */
    public void setReturnItemName(boolean param) {
        this.localReturnItemName=param;
    }


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

    
    @Override
    protected void writeAttributes(String prefix,String namespace, org.apache.axiom.om.OMFactory factory, org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) 
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
        if (localRequestDeadline != null) {
            writeAttribute("",
                    REQUEST_DEADLINE,
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRequestDeadline), xmlWriter);
        }
        writeAttribute("",
                "ReturnErrorText",
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReturnErrorText), xmlWriter);
        writeAttribute("",
                RETURN_DIAGNOSTIC_INFO,
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReturnDiagnosticInfo), xmlWriter);
        writeAttribute("",
                RETURN_ITEM_TIME,
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReturnItemTime), xmlWriter);
        writeAttribute("",
                RETURN_ITEM_PATH,
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReturnItemPath), xmlWriter);
        writeAttribute("",
                RETURN_ITEM_NAME,
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReturnItemName), xmlWriter);
        writeAttribute("", "ClientRequestHandle", localClientRequestHandle, xmlWriter);
        writeAttribute("", "LocaleID", localLocaleID, xmlWriter);
        xmlWriter.writeEndElement();
    }


    @Override
    public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException{

        java.util.ArrayList<Object> elementList = new java.util.ArrayList<Object>();
        java.util.ArrayList<Object> attribList = new java.util.ArrayList<Object>();

        attribList.add(new javax.xml.namespace.QName("",REQUEST_DEADLINE));
        attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRequestDeadline));
        attribList.add(new javax.xml.namespace.QName("","ReturnErrorText"));
        attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReturnErrorText));
        attribList.add(new javax.xml.namespace.QName("",RETURN_DIAGNOSTIC_INFO));
        attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReturnDiagnosticInfo));
        attribList.add(new javax.xml.namespace.QName("",RETURN_ITEM_TIME));
        attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReturnItemTime));
        attribList.add(new javax.xml.namespace.QName("",RETURN_ITEM_PATH));
        attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReturnItemPath));
        attribList.add(new javax.xml.namespace.QName("",RETURN_ITEM_NAME));
        attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReturnItemName));
        attribList.add(new javax.xml.namespace.QName("","ClientRequestHandle"));
        attribList.add(localClientRequestHandle);
        attribList.add(new javax.xml.namespace.QName("","LocaleID"));
        attribList.add(localLocaleID);
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
         * @return the <code>RequestOptions</code> parsed
         * 
         * @throws Exception - if something goes wrong parsing the xml
         *
         */
        public static RequestOptions parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            RequestOptions object = new RequestOptions();
            try {
                GenericFactory.goToNextStartElement(reader);
                RequestOptions result = (RequestOptions) checkADBBeanType(NAME,reader); 
                if(result!=null) {
                    return result;
                }
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.List<String> handledAttributes = new java.util.ArrayList<String>();
                // handle attribute "RequestDeadline"
                String tempAttribRequestDeadline = reader.getAttributeValue(null,REQUEST_DEADLINE);
                if (tempAttribRequestDeadline!=null) {
                    object.setRequestDeadline(
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(tempAttribRequestDeadline));
                }
                handledAttributes.add(REQUEST_DEADLINE);
                // handle attribute "ReturnErrorText"
                GenericFactory.handleReturnErrorText(reader.getAttributeValue(null,"ReturnErrorText"),object,handledAttributes);
                // handle attribute "ReturnDiagnosticInfo"
                String tempAttribReturnDiagnosticInfo = reader.getAttributeValue(null,RETURN_DIAGNOSTIC_INFO);
                if (tempAttribReturnDiagnosticInfo!=null) {
                    object.setReturnDiagnosticInfo(
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(tempAttribReturnDiagnosticInfo));
                }
                handledAttributes.add(RETURN_DIAGNOSTIC_INFO);
                // handle attribute "ReturnItemTime"
                String tempAttribReturnItemTime = reader.getAttributeValue(null,RETURN_ITEM_TIME);
                if (tempAttribReturnItemTime!=null) {
                    object.setReturnItemTime(
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(tempAttribReturnItemTime));
                }
                handledAttributes.add(RETURN_ITEM_TIME);
                // handle attribute "ReturnItemPath"
                String tempAttribReturnItemPath = reader.getAttributeValue(null,RETURN_ITEM_PATH);
                if (tempAttribReturnItemPath!=null) {
                    object.setReturnItemPath(
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(tempAttribReturnItemPath));
                }
                handledAttributes.add(RETURN_ITEM_PATH);
                // handle attribute "ReturnItemName"
                String tempAttribReturnItemName = reader.getAttributeValue(null,RETURN_ITEM_NAME);
                if (tempAttribReturnItemName!=null) {
                    object.setReturnItemName(
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(tempAttribReturnItemName));
                }
                handledAttributes.add(RETURN_ITEM_NAME);
                // handle attribute "ClientRequestHandle"
                GenericFactory.handleClientRequestHandle(reader.getAttributeValue(null,"ClientRequestHandle"),object,handledAttributes);
                // handle attribute "LocaleID"
                GenericFactory.handleLocaleID(reader.getAttributeValue(null,"localeID"),object,handledAttributes);
                reader.next();
            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }
            return object;
        }
    }//end of factory class
}
