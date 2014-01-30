package eu.ebbits.pwal.impl.driver.opcxmlda.stub;

/**
 * Stub class used for the LimitBits
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 * 
 * 
 * @author     ISMB
 * @version    %I%, %G%
 * @since      PWAL 0.2.0
 */ 
public class LimitBits extends ADBBeanImplementation {

    /**
     * 
     */
    private static final long serialVersionUID = -7558807475057616971L;

    /* QNAME for this class */
    public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
            OPC_NAMESPACE,
            "limitBits",
            OPC_PREFIX);

    
    @Override
    protected String getClassName() {
        return this.getClass().getSimpleName();
    }
    

    /**
     * field for LimitBits
     */
    private String localLimitBits ;

    /* table used to stored the registered instances of LimitBits */
    private static java.util.Map<String,LimitBits> table = new java.util.HashMap<String,LimitBits>();


    /**
     * Constructor of the LimitBits
     * 
     * @param value - value to for the LimitBits as <code>String</code>
     * @param isRegisterValue - indicates if the limit bits must be stored in the table of the register values, 
     *                             true yes, false no
     */
    protected LimitBits(String value, boolean isRegisterValue) {
        localLimitBits = value;
        if (isRegisterValue) {
            table.put(localLimitBits, this);
        }
    }

    //=========literal constants================
    
    public static final String NONE = "none";

    public static final String LOW = "low";

    public static final String HIGH = "high";

    public static final String CONSTANT = "constant";

    //=========limit bits constants================
    public static final LimitBits NONE_LIMITBITS = new LimitBits(NONE,true);

    public static final LimitBits LOW_LIMITBITS = new LimitBits(LOW,true);

    public static final LimitBits HIGH_LIMITBITS = new LimitBits(HIGH,true);

    public static final LimitBits CONSTANT_LIMITBITS = new LimitBits(CONSTANT,true);

    /**
     * Returns the value of LimitBits for this instance
     * 
     * @return   the value of LimitBits as <code>String</code>
     */
    public String getValue() { 
        return localLimitBits;
    }

    //=========Utility methods used to compare LimitBits classes========
    
    @Override
    public boolean equals(java.lang.Object obj) {
        return (obj == this);
    }
    
    @Override
    public int hashCode() { 
        return toString().hashCode();
    }
    
    @Override
    public String toString() {
        return localLimitBits;
    }

    //=========Utility methods used to compare LimitBits classes end ========
    
    
    @Override
    protected void writeAttributes(String prefix,
             String namespace, 
             org.apache.axiom.om.OMFactory factory, 
             org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) 
                     throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
         if (localLimitBits==null) {
             throw new org.apache.axis2.databinding.ADBException("Value cannot be null !!");
         }else{
             xmlWriter.writeCharacters(localLimitBits);
         }
         xmlWriter.writeEndElement();
     }     


    @Override
    public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException{
        //We can safely assume an element has only one type associated with it
        return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(MY_QNAME,
                new java.lang.Object[]{
                org.apache.axis2.databinding.utils.reader.ADBXMLStreamReader.ELEMENT_TEXT,
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localLimitBits)
        }, null);
    }



     /**
      *  Factory class that keeps the parse method
      */
     public static class Factory {

         
         /**
          * Returns the <code>LimitBits</code> object registered
          *  
          * @param value - the value registered when the object has been created as a <code>String</code> 
          * 
          * @return   the <code>LimitBits</code> associated with the value passed as parameter
          * 
          * @throws java.lang.IllegalArgumentException - if the value passed as parameter is not registered
          */
         public static LimitBits fromValue(String value) {
             LimitBits enumeration = (LimitBits) table.get(value);
             if (enumeration==null) {
                 throw new java.lang.IllegalArgumentException();
             }
             return enumeration;
         }
         
         /**
          * Returns the <code>LimitBits</code> object registered
          *  
          * @param value - the value registered when the object has been created as a <code>String</code> 
          * @param namespace - the namespace to use
          * 
          * @return   the <code>LimitBits</code> associated with the value passed as parameter
          * 
          * @throws java.lang.IllegalArgumentException - if the value passed as parameter is not registered
          * 
          */
         public static LimitBits fromString(String value,String namespaceURI)  {
             try {
                 return fromValue(value);
             } catch (java.lang.Exception e) {
                 throw new java.lang.IllegalArgumentException(e);
             }
         }

         /**
          * Returns the <code>LimitBits</code> object from an xml content
          *  
          * @param reader - the <code>javax.xml.stream.XMLStreamReader</code> to use
          * @param content - the xml content as <code>String</code>
          * 
          * @return   the <code>LimitBits</code> generated parsing the xml
          * 
          * 
          */
         public static LimitBits fromString(javax.xml.stream.XMLStreamReader xmlStreamReader,
                 String content) {
             if (content.indexOf(':') > -1) {
                 String prefix = content.substring(0,content.indexOf(':'));
                 String namespaceUri = xmlStreamReader.getNamespaceContext().getNamespaceURI(prefix);
                 return LimitBits.Factory.fromString(content,namespaceUri);
             } else {
                 return LimitBits.Factory.fromString(content,"");
             }
         }


         /**
          * static method to create the object
          * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
          *             If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
          * Postcondition: If this object is an element, the reader is positioned at its end element
          *             If this object is a complex type, the reader is positioned at the end element of its outer element
          *
          * @param reader - the <code>javax.xml.stream.XMLStreamReader</code> to use to parse the xml
          * 
          * @return the <code>LimitBits</code> parsed
          * 
          * @throws Exception - if something goes wrong parsing the xml
          */
         public static LimitBits parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
             LimitBits object = null;
             try {
                 GenericFactory.goToNextStartElement(reader);
                 while(!reader.isEndElement()) {
                     if (reader.isStartElement()  || reader.hasText()) {
                         String content = reader.getElementText();
                         object = LimitBits.Factory.fromString(content,GenericFactory.getNamespaceURI(reader,content));
                     } else {
                         reader.next();
                     }  
                 }  // end of while loop
             } catch (javax.xml.stream.XMLStreamException e) {
                 throw new java.lang.Exception(e);
             }
             return object;
         }
     }//end of factory class
}