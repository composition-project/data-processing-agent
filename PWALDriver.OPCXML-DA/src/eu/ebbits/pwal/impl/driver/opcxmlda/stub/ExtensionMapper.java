package eu.ebbits.pwal.impl.driver.opcxmlda.stub;
/**
 * Stub class used as for the mapping of the classes
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 * 
 * 
 * @author     ISMB
 * @version    %I%, %G%
 * @since      PWAL 0.2.0
 */ 
public final class ExtensionMapper {
    
    private ExtensionMapper() {
    }
    
    
    /**
     * Returns an <code>ADBBeanImplementation</code> extension from an xml content
     * 
     * @param namespaceURI - mamespace to use as <code>String</code>  
     * @param typeName - type name of the extension as <code>String</code>
     * @param reader - the <code>javax.xml.stream.XMLStreamReader</code> to use to read the xml
     * 
     * @return    the <code>ADBBeanImplementation</code> extension, created parsing the xml 
     * 
     * @throws java.lang.Exception - if something goes wrong parsing the xml
     */
    public static java.lang.Object getTypeObject(String namespaceURI,
            String typeName,
            javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
        if (ADBBeanImplementation.OPC_NAMESPACE.equals(namespaceURI) && 
                "interfaceVersion".equals(typeName)) {
            return  InterfaceVersion.Factory.parse(reader);
        }
        if (ADBBeanImplementation.OPC_NAMESPACE.equals(namespaceURI) &&
                "SubscribeRequestItemList".equals(typeName)) {
            return  SubscribeRequestItemList.Factory.parse(reader);
        }
        if (ADBBeanImplementation.OPC_NAMESPACE.equals(namespaceURI) &&
                "SubscribeItemValue".equals(typeName)) {
            return  SubscribeItemValue.Factory.parse(reader);
        }
        if (ADBBeanImplementation.OPC_NAMESPACE.equals(namespaceURI) &&
                "BrowseElement".equals(typeName)) {
            return  BrowseElement.Factory.parse(reader);
        }
        if (ADBBeanImplementation.OPC_NAMESPACE.equals(namespaceURI) &&
                "SubscribeReplyItemList".equals(typeName)) {
            return  SubscribeReplyItemList.Factory.parse(reader);
        }
        if (ADBBeanImplementation.OPC_NAMESPACE.equals(namespaceURI) &&
                "ReadRequestItem".equals(typeName)) {
            return  ReadRequestItem.Factory.parse(reader);
        }
        if (ADBBeanImplementation.OPC_NAMESPACE.equals(namespaceURI) &&
                "OPCQuality".equals(typeName)) {
            return  OPCQuality.Factory.parse(reader);
        }
        if (ADBBeanImplementation.OPC_NAMESPACE.equals(namespaceURI) &&
                "browseFilter".equals(typeName)) {
            return  BrowseFilter.Factory.parse(reader);
        }
        if (ADBBeanImplementation.OPC_NAMESPACE.equals(namespaceURI) &&
                "ReplyBase".equals(typeName)) {
            return  ReplyBase.Factory.parse(reader);
        }
        if (ADBBeanImplementation.OPC_NAMESPACE.equals(namespaceURI) &&
                "ReplyItemList".equals(typeName)) {
            return  ReplyItemList.Factory.parse(reader);
        }
        if (ADBBeanImplementation.OPC_NAMESPACE.equals(namespaceURI) &&
                "OPCError".equals(typeName)) {
            return  OPCError.Factory.parse(reader);
        }
        if (ADBBeanImplementation.OPC_NAMESPACE.equals(namespaceURI) &&
                "SubscribeRequestItem".equals(typeName)) {
            return  SubscribeRequestItem.Factory.parse(reader);
        }
        if (ADBBeanImplementation.OPC_NAMESPACE.equals(namespaceURI) &&
                "PropertyReplyList".equals(typeName)) {
            return  PropertyReplyList.Factory.parse(reader);
        }
        if (ADBBeanImplementation.OPC_NAMESPACE.equals(namespaceURI) &&
                "qualityBits".equals(typeName)) {
            return  QualityBits.Factory.parse(reader);
        }
        if (ADBBeanImplementation.OPC_NAMESPACE.equals(namespaceURI) &&
                "ItemProperty".equals(typeName)) {
            return  ItemProperty.Factory.parse(reader);
        }
        if (ADBBeanImplementation.OPC_NAMESPACE.equals(namespaceURI) &&
                "ItemIdentifier".equals(typeName)) {
            return  ItemIdentifier.Factory.parse(reader);
        }
        if (ADBBeanImplementation.OPC_NAMESPACE.equals(namespaceURI) &&
                "ServerStatus".equals(typeName)) {
            return  ServerStatus.Factory.parse(reader);
        }
        if (ADBBeanImplementation.OPC_NAMESPACE.equals(namespaceURI) &&
                "RequestOptions".equals(typeName)) {
            return  RequestOptions.Factory.parse(reader);
        }
        if (ADBBeanImplementation.OPC_NAMESPACE.equals(namespaceURI) &&
                "limitBits".equals(typeName)) {
            return  LimitBits.Factory.parse(reader);
        }
        if (ADBBeanImplementation.OPC_NAMESPACE.equals(namespaceURI) &&
                "serverState".equals(typeName)) {
            return  ServerState.Factory.parse(reader);
        }
        if (ADBBeanImplementation.OPC_NAMESPACE.equals(namespaceURI) &&
                "ItemValue".equals(typeName)) {
            return  ItemValue.Factory.parse(reader);
        }
        if (ADBBeanImplementation.OPC_NAMESPACE.equals(namespaceURI) &&
                "SubscribePolledRefreshReplyItemList".equals(typeName)) {
            return  SubscribePolledRefreshReplyItemList.Factory.parse(reader);
        }
        if (ADBBeanImplementation.OPC_NAMESPACE.equals(namespaceURI) &&
                "ReadRequestItemList".equals(typeName)) {
            return  ReadRequestItemList.Factory.parse(reader);
        }
        if (ADBBeanImplementation.OPC_NAMESPACE.equals(namespaceURI) &&
                "WriteRequestItemList".equals(typeName)) {
            return  WriteRequestItemList.Factory.parse(reader);
        }
        throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
    }
}