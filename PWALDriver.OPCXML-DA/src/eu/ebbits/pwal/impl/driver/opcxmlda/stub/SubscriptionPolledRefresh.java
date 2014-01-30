package eu.ebbits.pwal.impl.driver.opcxmlda.stub;

/**
 * Stub class used for the request of subscription refresh
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 * 
 * 
 * @author     ISMB
 * @version    %I%, %G%
 * @since      PWAL 0.2.0
 */ 
public class SubscriptionPolledRefresh extends ADBBeanImplementation {

    /**
     * 
     */
    private static final long serialVersionUID = -3678421011770542313L;

    /* QNAME for this class */
    public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
            OPC_NAMESPACE,
            "SubscriptionPolledRefresh",
            OPC_PREFIX);

    /* static name of the class */
    private static final String NAME = SubscriptionPolledRefresh.class.getSimpleName();
    
    @Override
    protected String getClassName() {
        return this.getClass().getSimpleName();
    }

    
    //===========literal constants===========
    private static final String HOLD_TIME = "HoldTime";
    
    private static final String WAIT_TIME = "WaitTime";
    
    private static final String RETURN_ALL_ITEMS= "ReturnAllItems";
    
    /**
     * field for Options
     */
    private RequestOptions localOptions ;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    private boolean localOptionsTracker = false ;


    /**
     * field for ServerSubHandles
     * This was an Array!
     */
    private String[] localServerSubHandles ;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    private boolean localServerSubHandlesTracker = false ;


    /**
     * Returns the list of server subscription handles
     * 
     * @return   the list of server subscription handles as <code>String[]</code>
     */
    public String[] getServerSubHandles() {
        return localServerSubHandles;
    }


    /**
     * Validate the array for ServerSubHandles
     * 
     * @param param - array to be validated
     */
    protected void validateServerSubHandles(String[] param) {
    }


    /**
     * Sets the list of server subscription handles
     * 
     * @param param - the list to be set as <code>String[]</code>
     */
    public void setServerSubHandles(String[] param) {
        String[] serverSubHandlesToUse = param.clone();
        validateServerSubHandles(serverSubHandlesToUse);
        if (serverSubHandlesToUse != null) {
            //update the setting tracker
            localServerSubHandlesTracker = true;
        } else {
            localServerSubHandlesTracker = false;
        }
        this.localServerSubHandles=serverSubHandlesToUse;
    }



    /**
     * Adds a server subscription handle to the list
     * 
     * @param param - Server subscription handle to be add as <code>String</code>
     */
    public void addServerSubHandles(String param) {
        if (localServerSubHandles == null) {
            localServerSubHandles = new String[]{};
        }
        //update the setting tracker
        localServerSubHandlesTracker = true;
        java.util.List<String> list =
                org.apache.axis2.databinding.utils.ConverterUtil.toList(localServerSubHandles);
        list.add(param);
        this.localServerSubHandles =
                (String[])list.toArray(
                        new String[list.size()]);
    }


    /**
     * field for HoldTime
     * This was an Attribute!
     */
    private java.util.Calendar localHoldTime ;


    /**
     * Returns the hold time
     * 
     * @return  the hold time as <code>java.util.Calendar</code>
     */
    public java.util.Calendar getHoldTime() {
        return localHoldTime;
    }



    /**
     * Sets the hold time
     * 
     * @param param - the hold time to be set as <code>java.util.Calendar</code>
     */
    public void setHoldTime(java.util.Calendar param) {
        this.localHoldTime=param;
    }


    /**
     * field for WaitTime
     * This was an Attribute!
     */
    private int localWaitTime ;


    /**
     * Returns the wait time
     * 
     * @return   the wait time as <code>int</code>
     */
    public  int getWaitTime() {
        return localWaitTime;
    }



    /**
     * Sets the wait time
     * 
     * @param param - wait time to be set as <code>int</code>
     */
    public void setWaitTime(int param) {
        this.localWaitTime=param;
    }


    /**
     * field for ReturnAllItems
     * This was an Attribute!
     */
    private boolean localReturnAllItems ;


    /**
     * Returns the indication if the request return all the items
     * 
     * @return   a <code>boolean</code>, true if the request returns all the imtes, false otherwise
     */
    public  boolean getReturnAllItems() {
        return localReturnAllItems;
    }


    /**
     * Sets the indication if the request return all the items
     * 
     * @param param -  a <code>boolean</code>, true if the request returns all the imtes, false otherwise
     */
    public void setReturnAllItems(boolean param) {
        this.localReturnAllItems=param;
    }

    
    
    @Override
    protected void writeAttributes(String prefix,String namespace, org.apache.axiom.om.OMFactory factory, org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) 
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
        if (localHoldTime != null) {
            writeAttribute("",
                    HOLD_TIME,
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHoldTime), xmlWriter);
        }
        if (localWaitTime!=java.lang.Integer.MIN_VALUE) {
            writeAttribute("",
                    WAIT_TIME,
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localWaitTime), xmlWriter);
        } else {
            throw new org.apache.axis2.databinding.ADBException("required attribute localWaitTime is null");
        }
        writeAttribute("",
                RETURN_ALL_ITEMS,
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReturnAllItems), xmlWriter);
        serializeAttribute(localOptionsTracker,localOptions,"Options",factory,xmlWriter);
        writeAttributesWithNamespace(localServerSubHandlesTracker,"ServerSubHandles",localServerSubHandles,xmlWriter);
        xmlWriter.writeEndElement();
    }



    @Override
    public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException{

        java.util.ArrayList<Object> elementList = new java.util.ArrayList<Object>();
        java.util.ArrayList<Object> attribList = new java.util.ArrayList<Object>();

        addItem(localOptionsTracker,localOptions,"Options",elementList);
        attribList.add(new javax.xml.namespace.QName("",HOLD_TIME));
        attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHoldTime));
        attribList.add(new javax.xml.namespace.QName("",WAIT_TIME));
        attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localWaitTime));
        attribList.add(new javax.xml.namespace.QName("",RETURN_ALL_ITEMS));
        attribList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReturnAllItems));
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
         * @return the <code>SubscriptionPolledRefresh</code> parsed
         * 
         * @throws Exception - if something goes wrong parsing the xml
         *
         */
        public static SubscriptionPolledRefresh parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            SubscriptionPolledRefresh object = new SubscriptionPolledRefresh();
            try {
                GenericFactory.goToNextStartElement(reader);
                SubscriptionPolledRefresh result = (SubscriptionPolledRefresh) checkADBBeanType(NAME,reader); 
                if(result!=null) {
                    return result;
                }
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.List<String> handledAttributes = new java.util.ArrayList<String>();
                // handle attribute "HoldTime"
                String tempAttribHoldTime = reader.getAttributeValue(null,HOLD_TIME);
                if (tempAttribHoldTime!=null) {
                    object.setHoldTime(
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(tempAttribHoldTime));
                }
                handledAttributes.add(HOLD_TIME);
                // handle attribute "WaitTime"
                String tempAttribWaitTime = reader.getAttributeValue(null,WAIT_TIME);
                if (tempAttribWaitTime!=null) {
                    object.setWaitTime(
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(tempAttribWaitTime));
                } else {
                    throw new org.apache.axis2.databinding.ADBException("Required attribute WaitTime is missing");
                }
                handledAttributes.add(WAIT_TIME);
                // handle attribute "ReturnAllItems"
                String tempAttribReturnAllItems = reader.getAttributeValue(null,RETURN_ALL_ITEMS);
                if (tempAttribReturnAllItems!=null) {
                    object.setReturnAllItems(
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(tempAttribReturnAllItems));
                } else {
                    throw new org.apache.axis2.databinding.ADBException("Required attribute ReturnAllItems is missing");
                }
                handledAttributes.add(RETURN_ALL_ITEMS);
                reader.next();
                java.util.ArrayList<Object> list2 = new java.util.ArrayList<Object>();
                GenericFactory.readOptionsElement(reader,object);
                if (reader.isStartElement() && new javax.xml.namespace.QName(OPC_NAMESPACE,"ServerSubHandles").equals(reader.getName())) {
                    // Process the array and step past its final element's end.
                    list2.add(reader.getElementText());
                    //loop until we find a start element that is not part of this array
                    boolean loopDone2 = false;
                    while(!loopDone2) {
                        loopDone2 = GenericFactory.findStartElementNotPartOfTheArray("ServerSubHandles",reader);
                        if(!loopDone2) {
                            list2.add(reader.getElementText());
                        }
                    }
                    // call the converter utility  to convert and set the array
                    object.setServerSubHandles((String[])
                            list2.toArray(new String[list2.size()]));

                }  // End of if for expected property start element
                GenericFactory.goToAndCheckUnexpectedStartElements(reader);
            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }
            return object;
        }
    }//end of factory class
}