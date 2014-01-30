package eu.ebbits.pwal.impl.driver.opcxmlda.stub;

/**
 * Stub class used for the response to a read request
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 * 
 * 
 * @author     ISMB
 * @version    %I%, %G%
 * @since      PWAL 0.2.0
 */ 
public class ReadResponse  extends ADBBeanImplementation {

    /**
     * 
     */
    private static final long serialVersionUID = 4546420883807786274L;

    /* QName for this class */
    public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
            OPC_NAMESPACE,
            "ReadResponse",
            OPC_PREFIX);

    /* static name of the class */
    private static final String NAME = ReadResponse.class.getSimpleName();
    
    @Override
    protected String getClassName() {
        return this.getClass().getSimpleName();
    }

    
    /**
     * field for ReadResult
     */
    private ReplyBase localReadResult ;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    private boolean localReadResultTracker = false ;


    /**
     * Returns the result of the read request as <code>ReplyBase</code>
     * 
     * @return    the result of the Read requst as <code>ReplyBase</code>
     * 
     */
    public  ReplyBase getReadResult() {
        return localReadResult;
    }


    /**
     * Sets the result of the read request
     * 
     * @param param - the reult to be set as <code>ReplyBase</code>
     */
    public void setReadResult(ReplyBase param) {
        if (param != null) {
            //update the setting tracker
            localReadResultTracker = true;
        } else {
            localReadResultTracker = false;
        }
        this.localReadResult=param;
    }


    /**
     * field for RItemList
     */
    private ReplyItemList localRItemList;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    private boolean localRItemListTracker = false ;

    /**
     * Rturns the list of items of the response
     * 
     * @return    the list of items of the response as <code>ReplyItemList</code>
     */
    public ReplyItemList getRItemList() {
        return localRItemList;
    }


    /**
     * field for Errors
     * This was an Array!
     */
    private OPCError[] localErrors ;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    private boolean localErrorsTracker = false ;

    
    @Override
    protected void writeAttributes(String prefix,String namespace, org.apache.axiom.om.OMFactory factory, org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) 
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
        serializeAttribute(localReadResultTracker,localReadResult,"ReadResult",factory,xmlWriter);
        serializeAttribute(localRItemListTracker,localRItemList,"RItemList",factory,xmlWriter);
        serializeAttributes(localErrorsTracker,localErrors,"Errors",factory,xmlWriter);
        xmlWriter.writeEndElement();
    }


    @Override
    public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException{

        java.util.ArrayList<Object> elementList = new java.util.ArrayList<Object>();
        java.util.ArrayList<Object> attribList = new java.util.ArrayList<Object>();

        addItem(localReadResultTracker,localReadResult,"ReadResult",elementList);
        addItem(localRItemListTracker,localRItemList,"RItemList",elementList);
        addItems(localErrorsTracker,localErrors,"Errors",elementList);
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
         * @return the <code>ReadResponse</code> parsed
         * 
         * @throws Exception - if something goes wrong parsing the xml
         *
         */
        public static ReadResponse parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            ReadResponse object = new ReadResponse();
            try {
                GenericFactory.goToNextStartElement(reader);
                ReadResponse result = (ReadResponse) checkADBBeanType(NAME,reader); 
                if(result!=null) {
                    return result;
                }
                reader.next();
                GenericFactory.goToNextStartElement(reader);
                if (reader.isStartElement() && new javax.xml.namespace.QName(OPC_NAMESPACE,"ReadResult").equals(reader.getName())) {
                    object.setReadResult(ReplyBase.Factory.parse(reader));
                    reader.next();
                }  // End of if for expected property start element
                GenericFactory.goToNextStartElement(reader);
                if (reader.isStartElement() && new javax.xml.namespace.QName(OPC_NAMESPACE,"RItemList").equals(reader.getName())) {
                    object.setRItemList(ReplyItemList.Factory.parse(reader));
                    reader.next();
                }  // End of if for expected property start element
                GenericFactory.readErrorsElement(reader,object);
                if (reader.isStartElement()) {
                    // A start element we are not expecting indicates a trailing invalid property
                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                }
            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }
            return object;
        }
    }//end of factory class
}