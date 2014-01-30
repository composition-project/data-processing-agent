package eu.ebbits.pwal.impl.driver.opcxmlda.stub;

/**
 * Stub class used for the response to the GetStatus request
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 * 
 * 
 * @author     ISMB
 * @version    %I%, %G%
 * @since      PWAL 0.2.0
 */ 
public class GetStatusResponse extends ADBBeanImplementation {

    /**
     * 
     */
    private static final long serialVersionUID = -982161563379808508L;

    /* QNAME for this class */
    public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
            OPC_NAMESPACE,
            "GetStatusResponse",
            OPC_PREFIX);

    /* static reference to the name of this class*/
    private static final String NAME = GetStatusResponse.class.getSimpleName();
    
    @Override
    protected String getClassName() {
        return this.getClass().getSimpleName();
    }

    
    /**
     * field for GetStatusResult
     */
    private ReplyBase localGetStatusResult ;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    private boolean localGetStatusResultTracker = false ;


    /**
     * Retrieves the result of the GetStatus request as <code>ReplyBase</code> object
     * 
     * @return    a <code>ReplyBase</code> containing the result of the GetStatus request
     */
    public  ReplyBase getGetStatusResult() {
        return localGetStatusResult;
    }


    /**
     * Sets the result of the GetStatus request
     * 
     * @param param - the result to be set as a <code>ReplyBase</code> object
     */
    public void setGetStatusResult(ReplyBase param) {
        if (param != null) {
            //update the setting tracker
            localGetStatusResultTracker = true;
        } else {
            localGetStatusResultTracker = false;
        }
        this.localGetStatusResult=param;
    }


    /**
     * field for Status
     */
    private ServerStatus localStatus ;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    private boolean localStatusTracker = false ;


    /**
     * Retrieves the server status as a <code>ServerStatus></code> object
     * 
     * @return - a <code>ServerStatus</code> containing the server status
     */
    public  ServerStatus getStatus() {
        return localStatus;
    }



    /**
     * Sets the server status
     * 
     * @param param - <code>ServerStatus</code> object containing the server status to be set
     */
    public void setStatus(ServerStatus param) {
        if (param != null) {
            //update the setting tracker
            localStatusTracker = true;
        } else {
            localStatusTracker = false;
        }
        this.localStatus=param;
    }

    @Override
    protected void writeAttributes(String prefix,String namespace, org.apache.axiom.om.OMFactory factory, org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) 
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
        serializeAttribute(localGetStatusResultTracker,localGetStatusResult,"GetStatusResult",factory,xmlWriter);
        serializeAttribute(localStatusTracker,localStatus,"Status",factory,xmlWriter);
        xmlWriter.writeEndElement();
    }


    @Override
    public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException{

        java.util.ArrayList<Object> elementList = new java.util.ArrayList<Object>();
        java.util.ArrayList<Object> attribList = new java.util.ArrayList<Object>();

        addItem(localGetStatusResultTracker,localGetStatusResult,"GetStatusResult",elementList);
        addItem(localStatusTracker,localStatus,"Status",elementList);
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
         * @return the <code>GetStatusResponse</code> parsed
         * 
         * @throws Exception - if something goes wrong parsing the xml
         *
         */
        public static GetStatusResponse parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            GetStatusResponse object =
                    new GetStatusResponse();
            try {
                GenericFactory.goToNextStartElement(reader);
                GetStatusResponse result = (GetStatusResponse) checkADBBeanType(NAME,reader); 
                if(result!=null) {
                    return result;
                }
                reader.next();
                GenericFactory.goToNextStartElement(reader);
                if (reader.isStartElement() && new javax.xml.namespace.QName(OPC_NAMESPACE,"GetStatusResult").equals(reader.getName())) {
                    object.setGetStatusResult(ReplyBase.Factory.parse(reader));
                    reader.next();
                }  // End of if for expected property start element
                GenericFactory.goToNextStartElement(reader);
                if (reader.isStartElement() && new javax.xml.namespace.QName(OPC_NAMESPACE,"Status").equals(reader.getName())) {
                    object.setStatus(ServerStatus.Factory.parse(reader));
                    reader.next();
                }  // End of if for expected property start element
                GenericFactory.goToAndCheckUnexpectedStartElements(reader);
            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }
            return object;
        }
    }//end of factory class
}