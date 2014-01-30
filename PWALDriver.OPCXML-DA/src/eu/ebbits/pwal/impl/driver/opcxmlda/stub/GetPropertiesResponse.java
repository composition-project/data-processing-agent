package eu.ebbits.pwal.impl.driver.opcxmlda.stub;

/**
 * Stub class used for response to a GetProperties request
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 * 
 * 
 * @author     ISMB
 * @version    %I%, %G%
 * @since      PWAL 0.2.0
 */ 
public class GetPropertiesResponse extends ADBBeanImplementation {

    /**
     * 
     */
    private static final long serialVersionUID = 3008744594903406923L;

    /* QNAME for this class */
    public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
            OPC_NAMESPACE,
            "GetPropertiesResponse",
            OPC_PREFIX);

    /* static reference to the name of this class*/
    private static final String NAME = GetPropertiesResponse.class.getSimpleName();
    
    
    @Override
    protected String getClassName() {
        return this.getClass().getSimpleName();
    }

    //===========literal constants===========
    private static final String PROPERTY_LISTS = "PropertyLists";
    
    /**
     * field for GetPropertiesResult
     */
    private ReplyBase localGetPropertiesResult ;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    private boolean localGetPropertiesResultTracker = false ;


    /**
     * Retrieves the result of the GetProperties request as <code>ReplyBase</code> object
     * 
     * @return    a <code>ReplyBase</code> containing the result of the GetProperties request
     */
    public ReplyBase getGetPropertiesResult() {
        return localGetPropertiesResult;
    }



    /**
     * Sets the result of the GetProperties request
     * 
     * @param param - the result to be set as a <code>ReplyBase</code> object
     */
    public void setGetPropertiesResult(ReplyBase param) {
        if (param != null) {
            //update the setting tracker
            localGetPropertiesResultTracker = true;
        } else {
            localGetPropertiesResultTracker = false;
        }
        this.localGetPropertiesResult=param;
    }


    /**
     * field for PropertyLists
     * This was an Array!
     */
    private PropertyReplyList[] localPropertyLists ;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    private boolean localPropertyListsTracker = false ;


    /**
     * Retrieves the list of properties for the response
     * 
     * @return an array of <code>PropertyReplyList</code> containing the properties
     */
    public  PropertyReplyList[] getPropertyLists() {
        return localPropertyLists;
    }


    /**
     * Validate the array for PropertyLists
     * 
     * @param param - the list to be validated
     */
    private void validatePropertyLists(PropertyReplyList[] param) {
    }


    /**
     * Sets the list of properties 
     * 
     * @param param - list of <code>PropertyReplyList[]</code> to be set
     */
    public void setPropertyLists(PropertyReplyList[] param) {
        PropertyReplyList[] propertyListsToSet = param.clone();
        validatePropertyLists(propertyListsToSet);
        if (propertyListsToSet != null) {
            //update the setting tracker
            localPropertyListsTracker = true;
        } else {
            localPropertyListsTracker = false;
        }
        this.localPropertyLists=propertyListsToSet;
    }



    /**
     * Adds a property to the list of properties
     * 
     * @param param - property to be added, as <code>PropertyReplyList</code>
     */
    public void addPropertyLists(PropertyReplyList param) {
        if (localPropertyLists == null) {
            localPropertyLists = new PropertyReplyList[]{};
        }
        //update the setting tracker
        localPropertyListsTracker = true;
        java.util.List <PropertyReplyList>list =
                org.apache.axis2.databinding.utils.ConverterUtil.toList(localPropertyLists);
        list.add(param);
        this.localPropertyLists =
                (PropertyReplyList[])list.toArray(
                        new PropertyReplyList[list.size()]);

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
        serializeAttribute(localGetPropertiesResultTracker,localGetPropertiesResult,"GetPropertiesResult",factory,xmlWriter);
        serializeAttributes(localPropertyListsTracker,localPropertyLists,PROPERTY_LISTS,factory,xmlWriter);
        serializeAttributes(localErrorsTracker,localErrors,"Errors",factory,xmlWriter);
        xmlWriter.writeEndElement();
    }


    @Override
    public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException{

        java.util.ArrayList<Object> elementList = new java.util.ArrayList<Object>();
        java.util.ArrayList<Object> attribList = new java.util.ArrayList<Object>();

        addItem(localGetPropertiesResultTracker,localGetPropertiesResult,"GetPropertiesResult",elementList);
        addItems(localPropertyListsTracker,localPropertyLists,PROPERTY_LISTS,elementList);
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
         * @return the <code>GetPropertiesResponse</code> parsed
         * 
         * @throws Exception - if something goes wrong parsing the xml
         *
         */
        public static GetPropertiesResponse parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            GetPropertiesResponse object = new GetPropertiesResponse();
            try {
                GenericFactory.goToNextStartElement(reader);
                GetPropertiesResponse result = (GetPropertiesResponse) checkADBBeanType(NAME,reader); 
                if(result!=null) {
                    return result;
                }
                reader.next();
                java.util.ArrayList <Object> list2 = new java.util.ArrayList<Object>();
                GenericFactory.goToNextStartElement(reader);
                if (reader.isStartElement() && new javax.xml.namespace.QName(OPC_NAMESPACE,"GetPropertiesResult").equals(reader.getName())) {
                    object.setGetPropertiesResult(ReplyBase.Factory.parse(reader));
                    reader.next();
                }  // End of if for expected property start element
                GenericFactory.goToNextStartElement(reader);
                if (reader.isStartElement() && new javax.xml.namespace.QName(OPC_NAMESPACE,PROPERTY_LISTS).equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    list2.add(PropertyReplyList.Factory.parse(reader));
                    //loop until we find a start element that is not part of this array
                    boolean loopDone2 = false;
                    while(!loopDone2) {
                        loopDone2 = GenericFactory.findStartElementNotPartOfTheArray(PROPERTY_LISTS,reader);
                        if(!loopDone2) {
                            list2.add(PropertyReplyList.Factory.parse(reader));
                        }
                    }
                    // call the converter utility  to convert and set the array
                    object.setPropertyLists((PropertyReplyList[])
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                    PropertyReplyList.class,
                                    list2));
                }  // End of if for expected property start element
                GenericFactory.readErrorsElement(reader,object);
                GenericFactory.checkUnexpectedStartElements(reader);
            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }
            return object;
        }
    }//end of factory class
}