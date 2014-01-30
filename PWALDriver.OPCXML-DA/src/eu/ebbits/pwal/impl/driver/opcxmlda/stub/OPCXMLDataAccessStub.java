/**
 * 
 * Inner class, wrapping Webservices calls to OPC server - WWSDL STUB
 *  
 * Copyright (c) 2010-2012 the ebbits project. All Rights Reserved.
 *
 * * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.1  Built on : Oct 19, 2009 (10:59:00 EDT)
 *
 * @author     ISMB
 * @version    %I%, %G%
 * @see        eu.ebbits.pwal.impl.driver.framework.PWALDriver
 * @since      PWAL 0.1.0
 */
package eu.ebbits.pwal.impl.driver.opcxmlda.stub;

import java.rmi.RemoteException;

import javax.xml.namespace.QName;

import org.apache.axis2.description.AxisOperation;

/**
 *  OPCXML_DataAccessStub java implementation
 */
@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public class OPCXMLDataAccessStub extends org.apache.axis2.client.Stub {
    /* array fo the operations */
    private org.apache.axis2.description.AxisOperation[] operations;

    //hashmaps to keep the fault mapping
    private java.util.Map<QName,String> faultExceptionNameMap = new java.util.HashMap<QName,String>();
    private java.util.Map<QName,String> faultExceptionClassNameMap = new java.util.HashMap<QName,String>();
    private java.util.Map<QName,String> faultMessageMap = new java.util.HashMap<QName,String>();

    private static int counter = 0;

    //========numeric constants===========
    
    /* reset the counter if it is greater than this value */
    private static final int MAX_COUNTER = 99999;
    
    /* size of the operations array */
    private static final int OPERATIONS_SIZE = 8;
    
    /* operations indexes */
    private static final int READ_OPERATIONS_INDEX = 0;
    private static final int WRITE_OPERATIONS_INDEX = 1;
    private static final int SUBSCRIBE_OPERATIONS_INDEX = 2;
    private static final int SUBSCRIPTION_POLLED_REFRESH_OPERATIONS_INDEX = 3;
    private static final int GET_STATUS_OPERATIONS_INDEX = 4;
    private static final int SUBSCRIPTION_CANCEL_OPERATIONS_INDEX = 5;
    private static final int GET_PROPERTIES_OPERATIONS_INDEX = 6;
    private static final int BROWSE_OPERATIONS_INDEX = 7;
   
    //========numeric constants end===========
    
    
    private static synchronized String getUniqueSuffix() {
        // reset the counter if it is greater than 99999
        if (counter > MAX_COUNTER) {
            counter = 0;
        }
        counter = counter + 1; 
        return java.lang.Long.toString(System.currentTimeMillis()) + "_" + counter;
    }


    private AxisOperation createOperation(String name) {
        org.apache.axis2.description.AxisOperation operation = new org.apache.axis2.description.OutInAxisOperation();
        operation.setName(new javax.xml.namespace.QName(ADBBeanImplementation.OPC_NAMESPACE, name));
        _service.addOperation(operation);
        return operation;
    }
    
    private void populateAxisService() throws org.apache.axis2.AxisFault {
        //creating the Service with a unique name
        _service = new org.apache.axis2.description.AxisService("OPCXML_DataAccess" + getUniqueSuffix());
        addAnonymousOperations();

        //creating the operations
        operations = new org.apache.axis2.description.AxisOperation[OPERATIONS_SIZE];
        
        operations[READ_OPERATIONS_INDEX]=createOperation("read");
        operations[WRITE_OPERATIONS_INDEX]=createOperation("write");
        operations[SUBSCRIBE_OPERATIONS_INDEX]=createOperation("subscribe");
        operations[SUBSCRIPTION_POLLED_REFRESH_OPERATIONS_INDEX]=createOperation("subscriptionPolledRefresh");
        operations[GET_STATUS_OPERATIONS_INDEX]=createOperation("getStatus");
        operations[SUBSCRIPTION_CANCEL_OPERATIONS_INDEX]=createOperation("subscriptionCancel");
        operations[GET_PROPERTIES_OPERATIONS_INDEX]=createOperation("getProperties");
        operations[BROWSE_OPERATIONS_INDEX]=createOperation("browse");        
    }

    //populates the faults
    private void populateFaults() {
        
    }

    /**
     *Constructor that takes in a configContext
     */

    public OPCXMLDataAccessStub(org.apache.axis2.context.ConfigurationContext configurationContext,
            String targetEndpoint)
                    throws org.apache.axis2.AxisFault {
        this(configurationContext,targetEndpoint,false);
    }


    /**
     * Constructor that takes in a configContext  and useseperate listner
     */
    public OPCXMLDataAccessStub(org.apache.axis2.context.ConfigurationContext configurationContext,
            String targetEndpoint, boolean useSeparateListener)
                    throws org.apache.axis2.AxisFault {
        //To populate AxisService
        populateAxisService();
        populateFaults();

        _serviceClient = new org.apache.axis2.client.ServiceClient(configurationContext,_service);

        _serviceClient.getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
                targetEndpoint));
        _serviceClient.getOptions().setUseSeparateListener(useSeparateListener);
    }

    /**
     * Default Constructor
     */
    public OPCXMLDataAccessStub(org.apache.axis2.context.ConfigurationContext configurationContext) throws org.apache.axis2.AxisFault {
        this(configurationContext,"http://192.168.87.101/OPCXMLServer/sopcweb.asmx" );
    }

    /**
     * Default Constructor
     */
    public OPCXMLDataAccessStub() throws org.apache.axis2.AxisFault {
        this("http://192.168.87.101/OPCXMLServer/sopcweb.asmx" );
    }

    /**
     * Constructor taking the target endpoint
     */
    public OPCXMLDataAccessStub(String targetEndpoint) throws org.apache.axis2.AxisFault {
        this(null,targetEndpoint);
    }


    private void manageAxisFaultException(org.apache.axis2.AxisFault f) throws RemoteException {
        org.apache.axiom.om.OMElement faultElt = f.getDetail();
        if (faultElt!=null) {
            if (faultExceptionNameMap.containsKey(faultElt.getQName())) {
                //make the fault by reflection
                try{
                    String exceptionClassName = (String)faultExceptionClassNameMap.get(faultElt.getQName());
                    java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                    java.lang.Exception ex=
                            (java.lang.Exception) exceptionClass.newInstance();
                    //message class
                    String messageClassName = (String)faultMessageMap.get(faultElt.getQName());
                    java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                    java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
                    java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                            new java.lang.Class[]{messageClass});
                    m.invoke(ex,new java.lang.Object[]{messageObject});
                    throw new java.rmi.RemoteException(ex.getMessage(), ex);
                }catch(java.lang.ClassCastException e) {
                    // we cannot intantiate the class - throw the original Axis fault
                    throw f;
                } catch (java.lang.ClassNotFoundException e) {
                    // we cannot intantiate the class - throw the original Axis fault
                    throw f;
                }catch (java.lang.NoSuchMethodException e) {
                    // we cannot intantiate the class - throw the original Axis fault
                    throw f;
                } catch (java.lang.reflect.InvocationTargetException e) {
                    // we cannot intantiate the class - throw the original Axis fault
                    throw f;
                }  catch (java.lang.IllegalAccessException e) {
                    // we cannot intantiate the class - throw the original Axis fault
                    throw f;
                }   catch (java.lang.InstantiationException e) {
                    // we cannot intantiate the class - throw the original Axis fault
                    throw f;
                }
            }else{
                throw f;
            }
        }else{
            throw f;
        }
    }
    
    
    /**
     * Auto generated method signature
     * 
     * @see eu.ebbits.m12demo.manufacturing.pwal.opcaxis2.OPCXML_DataAccess#read
     * @param read

     */
    public  ReadResponse read(Read read) throws java.rmi.RemoteException {
        org.apache.axis2.context.MessageContext messageContext = null;
        try{
            org.apache.axis2.client.OperationClient operationClient = _serviceClient.createClient(operations[0].getName());
            operationClient.getOptions().setAction("http://opcfoundation.org/webservices/XMLDA/1.0/Read");
            operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);
            addPropertyToOperationClient(operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
            // create a message context
            messageContext = new org.apache.axis2.context.MessageContext();
            // create SOAP envelope with that payload
            org.apache.axiom.soap.SOAPEnvelope env = null;
            env = toEnvelope(getFactory(operationClient.getOptions().getSoapVersionURI()),
                    read,
                    optimizeContent(new javax.xml.namespace.QName(ADBBeanImplementation.OPC_NAMESPACE,
                            "read")));

            //adding SOAP soap_headers
            _serviceClient.addHeadersToEnvelope(env);
            // set the message context with that soap envelope
            messageContext.setEnvelope(env);

            // add the message contxt to the operation client
            operationClient.addMessageContext(messageContext);

            //execute the operation client
            operationClient.execute(true);

            org.apache.axis2.context.MessageContext returnMessageContext = operationClient.getMessageContext(
                    org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
            org.apache.axiom.soap.SOAPEnvelope returnEnv = returnMessageContext.getEnvelope();

            java.lang.Object object = fromOM(
                    returnEnv.getBody().getFirstElement(),
                    ReadResponse.class,
                    getEnvelopeNamespaces(returnEnv));

            return (ReadResponse)object;

        }catch(org.apache.axis2.AxisFault f) {
            manageAxisFaultException(f);
        } finally {
            messageContext.getTransportOut().getSender().cleanup(messageContext);
        }
        return null;
    }

    /**
     * Auto generated method signature
     * 
     * @see eu.ebbits.m12demo.manufacturing.pwal.opcaxis2.OPCXML_DataAccess#write
     * @param write
     *
     */
    public  WriteResponse write(Write write) throws java.rmi.RemoteException {
        org.apache.axis2.context.MessageContext messageContext = null;
        try{
            org.apache.axis2.client.OperationClient operationClient = _serviceClient.createClient(operations[1].getName());
            operationClient.getOptions().setAction("http://opcfoundation.org/webservices/XMLDA/1.0/Write");
            operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

            addPropertyToOperationClient(operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");

            // create a message context
            messageContext = new org.apache.axis2.context.MessageContext();

            // create SOAP envelope with that payload
            org.apache.axiom.soap.SOAPEnvelope env = null;

            env = toEnvelope(getFactory(operationClient.getOptions().getSoapVersionURI()),
                    write,
                    optimizeContent(new javax.xml.namespace.QName(ADBBeanImplementation.OPC_NAMESPACE,
                            "write")));

            //adding SOAP soap_headers
            _serviceClient.addHeadersToEnvelope(env);
            // set the message context with that soap envelope
            messageContext.setEnvelope(env);

            // add the message contxt to the operation client
            operationClient.addMessageContext(messageContext);

            //execute the operation client
            operationClient.execute(true);

            org.apache.axis2.context.MessageContext returnMessageContext = operationClient.getMessageContext(
                    org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
            org.apache.axiom.soap.SOAPEnvelope returnEnv = returnMessageContext.getEnvelope();

            java.lang.Object object = fromOM(
                    returnEnv.getBody().getFirstElement() ,
                    WriteResponse.class,
                    getEnvelopeNamespaces(returnEnv));

            return (WriteResponse)object;
        }catch(org.apache.axis2.AxisFault f) {
            manageAxisFaultException(f);
        } finally {
            messageContext.getTransportOut().getSender().cleanup(messageContext);
        }
        return null;
    }

    /**
     * Auto generated method signature
     * 
     * @see eu.ebbits.m12demo.manufacturing.pwal.opcaxis2.OPCXML_DataAccess#subscribe
     * @param subscribe
     *
     */
    public  SubscribeResponse subscribe(Subscribe subscribe) throws java.rmi.RemoteException {
        org.apache.axis2.context.MessageContext messageContext = null;
        try{
            org.apache.axis2.client.OperationClient operationClient = _serviceClient.createClient(operations[2].getName());
            operationClient.getOptions().setAction("http://opcfoundation.org/webservices/XMLDA/1.0/Subscribe");
            operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

            addPropertyToOperationClient(operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");

            // create a message context
            messageContext = new org.apache.axis2.context.MessageContext();

            // create SOAP envelope with that payload
            org.apache.axiom.soap.SOAPEnvelope env = null;

            env = toEnvelope(getFactory(operationClient.getOptions().getSoapVersionURI()),
                    subscribe,
                    optimizeContent(new javax.xml.namespace.QName(ADBBeanImplementation.OPC_NAMESPACE,
                            "subscribe")));

            //adding SOAP soap_headers
            _serviceClient.addHeadersToEnvelope(env);
            // set the message context with that soap envelope
            messageContext.setEnvelope(env);

            // add the message contxt to the operation client
            operationClient.addMessageContext(messageContext);

            //execute the operation client
            operationClient.execute(true);

            org.apache.axis2.context.MessageContext returnMessageContext = operationClient.getMessageContext(
                    org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
            org.apache.axiom.soap.SOAPEnvelope returnEnv = returnMessageContext.getEnvelope();

            java.lang.Object object = fromOM(
                    returnEnv.getBody().getFirstElement() ,
                    SubscribeResponse.class,
                    getEnvelopeNamespaces(returnEnv));

            return (SubscribeResponse)object;
        }catch(org.apache.axis2.AxisFault f) {
            manageAxisFaultException(f);
        } finally {
            messageContext.getTransportOut().getSender().cleanup(messageContext);
        }
        return null;
    }


    /**
     * Auto generated method signature
     * 
     * @see eu.ebbits.m12demo.manufacturing.pwal.opcaxis2.OPCXML_DataAccess#subscriptionPolledRefresh
     * @param subscriptionPolledRefresh
     *
     */
    public  SubscriptionPolledRefreshResponse subscriptionPolledRefresh(SubscriptionPolledRefresh subscriptionPolledRefresh) throws java.rmi.RemoteException {
        org.apache.axis2.context.MessageContext messageContext = null;
        try{
            org.apache.axis2.client.OperationClient operationClient = _serviceClient.createClient(operations[SUBSCRIPTION_POLLED_REFRESH_OPERATIONS_INDEX].getName());
            operationClient.getOptions().setAction("http://opcfoundation.org/webservices/XMLDA/1.0/SubscriptionPolledRefresh");
            operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

            addPropertyToOperationClient(operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");

            // create a message context
            messageContext = new org.apache.axis2.context.MessageContext();

            // create SOAP envelope with that payload
            org.apache.axiom.soap.SOAPEnvelope env = null;

            env = toEnvelope(getFactory(operationClient.getOptions().getSoapVersionURI()),
                    subscriptionPolledRefresh,
                    optimizeContent(new javax.xml.namespace.QName(ADBBeanImplementation.OPC_NAMESPACE,
                            "subscriptionPolledRefresh")));

            //adding SOAP soap_headers
            _serviceClient.addHeadersToEnvelope(env);
            // set the message context with that soap envelope
            messageContext.setEnvelope(env);

            // add the message contxt to the operation client
            operationClient.addMessageContext(messageContext);

            //execute the operation client
            operationClient.execute(true);

            org.apache.axis2.context.MessageContext returnMessageContext = operationClient.getMessageContext(
                    org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
            org.apache.axiom.soap.SOAPEnvelope returnEnv = returnMessageContext.getEnvelope();

            java.lang.Object object = fromOM(
                    returnEnv.getBody().getFirstElement() ,
                    SubscriptionPolledRefreshResponse.class,
                    getEnvelopeNamespaces(returnEnv));

            return (SubscriptionPolledRefreshResponse)object;
        }catch(org.apache.axis2.AxisFault f) {
            manageAxisFaultException(f);
        } finally {
            messageContext.getTransportOut().getSender().cleanup(messageContext);
        }
        return null;
    }

    /**
     * Auto generated method signature
     * 
     * @see eu.ebbits.m12demo.manufacturing.pwal.opcaxis2.OPCXML_DataAccess#getStatus
     * @param getStatus
     *
     */
    public  GetStatusResponse getStatus(GetStatus getStatus) throws java.rmi.RemoteException {
        org.apache.axis2.context.MessageContext messageContext = null;
        try{
            org.apache.axis2.client.OperationClient operationClient = _serviceClient.createClient(operations[GET_STATUS_OPERATIONS_INDEX].getName());
            operationClient.getOptions().setAction("http://opcfoundation.org/webservices/XMLDA/1.0/GetStatus");
            operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

            addPropertyToOperationClient(operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");

            // create a message context
            messageContext = new org.apache.axis2.context.MessageContext();

            // create SOAP envelope with that payload
            org.apache.axiom.soap.SOAPEnvelope env = null;

            env = toEnvelope(getFactory(operationClient.getOptions().getSoapVersionURI()),
                    getStatus,
                    optimizeContent(new javax.xml.namespace.QName(ADBBeanImplementation.OPC_NAMESPACE,
                            "getStatus")));

            //adding SOAP soap_headers
            _serviceClient.addHeadersToEnvelope(env);
            // set the message context with that soap envelope
            messageContext.setEnvelope(env);

            // add the message contxt to the operation client
            operationClient.addMessageContext(messageContext);

            //execute the operation client
            operationClient.execute(true);

            org.apache.axis2.context.MessageContext returnMessageContext = operationClient.getMessageContext(
                    org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
            org.apache.axiom.soap.SOAPEnvelope returnEnv = returnMessageContext.getEnvelope();

            java.lang.Object object = fromOM(
                    returnEnv.getBody().getFirstElement() ,
                    GetStatusResponse.class,
                    getEnvelopeNamespaces(returnEnv));

            return (GetStatusResponse)object;
        }catch(org.apache.axis2.AxisFault f) {
            manageAxisFaultException(f);
        } finally {
            messageContext.getTransportOut().getSender().cleanup(messageContext);
        }
        return null;
    }

    /**
     * Auto generated method signature
     * 
     * @see eu.ebbits.m12demo.manufacturing.pwal.opcaxis2.OPCXML_DataAccess#subscriptionCancel
     * @param subscriptionCancel
     *
     */
    public  SubscriptionCancelResponse subscriptionCancel(SubscriptionCancel subscriptionCancel) throws java.rmi.RemoteException {
        org.apache.axis2.context.MessageContext messageContext = null;
        try{
            org.apache.axis2.client.OperationClient operationClient = _serviceClient.createClient(operations[SUBSCRIPTION_CANCEL_OPERATIONS_INDEX].getName());
            operationClient.getOptions().setAction("http://opcfoundation.org/webservices/XMLDA/1.0/SubscriptionCancel");
            operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

            addPropertyToOperationClient(operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");

            // create a message context
            messageContext = new org.apache.axis2.context.MessageContext();

            // create SOAP envelope with that payload
            org.apache.axiom.soap.SOAPEnvelope env = null;

            env = toEnvelope(getFactory(operationClient.getOptions().getSoapVersionURI()),
                    subscriptionCancel,
                    optimizeContent(new javax.xml.namespace.QName(ADBBeanImplementation.OPC_NAMESPACE,
                            "subscriptionCancel")));

            //adding SOAP soap_headers
            _serviceClient.addHeadersToEnvelope(env);
            // set the message context with that soap envelope
            messageContext.setEnvelope(env);

            // add the message contxt to the operation client
            operationClient.addMessageContext(messageContext);

            //execute the operation client
            operationClient.execute(true);

            org.apache.axis2.context.MessageContext returnMessageContext = operationClient.getMessageContext(
                    org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
            org.apache.axiom.soap.SOAPEnvelope returnEnv = returnMessageContext.getEnvelope();

            java.lang.Object object = fromOM(
                    returnEnv.getBody().getFirstElement() ,
                    SubscriptionCancelResponse.class,
                    getEnvelopeNamespaces(returnEnv));

            return (SubscriptionCancelResponse)object;
        }catch(org.apache.axis2.AxisFault f) {
            manageAxisFaultException(f);
        } finally {
            messageContext.getTransportOut().getSender().cleanup(messageContext);
        }
        return null;
    }

    /**
     * Auto generated method signature
     * 
     * @see eu.ebbits.m12demo.manufacturing.pwal.opcaxis2.OPCXML_DataAccess#getProperties
     * @param getProperties
     *
     */
    public  GetPropertiesResponse getProperties(GetProperties getProperties) throws java.rmi.RemoteException {
        org.apache.axis2.context.MessageContext messageContext = null;
        try{
            org.apache.axis2.client.OperationClient operationClient = _serviceClient.createClient(operations[GET_PROPERTIES_OPERATIONS_INDEX].getName());
            operationClient.getOptions().setAction("http://opcfoundation.org/webservices/XMLDA/1.0/GetProperties");
            operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

            addPropertyToOperationClient(operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");

            // create a message context
            messageContext = new org.apache.axis2.context.MessageContext();

            // create SOAP envelope with that payload
            org.apache.axiom.soap.SOAPEnvelope env = null;

            env = toEnvelope(getFactory(operationClient.getOptions().getSoapVersionURI()),
                    getProperties,
                    optimizeContent(new javax.xml.namespace.QName(ADBBeanImplementation.OPC_NAMESPACE,
                            "getProperties")));

            //adding SOAP soap_headers
            _serviceClient.addHeadersToEnvelope(env);
            // set the message context with that soap envelope
            messageContext.setEnvelope(env);

            // add the message contxt to the operation client
            operationClient.addMessageContext(messageContext);

            //execute the operation client
            operationClient.execute(true);

            org.apache.axis2.context.MessageContext returnMessageContext = operationClient.getMessageContext(
                    org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
            org.apache.axiom.soap.SOAPEnvelope returnEnv = returnMessageContext.getEnvelope();

            java.lang.Object object = fromOM(
                    returnEnv.getBody().getFirstElement() ,
                    GetPropertiesResponse.class,
                    getEnvelopeNamespaces(returnEnv));

            return (GetPropertiesResponse)object;
        }catch(org.apache.axis2.AxisFault f) {
            manageAxisFaultException(f);
        } finally {
            messageContext.getTransportOut().getSender().cleanup(messageContext);
        }
        return null;
    }

    /**
     * Auto generated method signature
     * 
     * @see eu.ebbits.m12demo.manufacturing.pwal.opcaxis2.OPCXML_DataAccess#browse
     * @param browse
     *
     */
    public  BrowseResponse browse(Browse browse) throws java.rmi.RemoteException {
        org.apache.axis2.context.MessageContext messageContext = null;
        try{
            org.apache.axis2.client.OperationClient operationClient = _serviceClient.createClient(operations[BROWSE_OPERATIONS_INDEX].getName());
            operationClient.getOptions().setAction("http://opcfoundation.org/webservices/XMLDA/1.0/Browse");
            operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

            addPropertyToOperationClient(operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");

            // create a message context
            messageContext = new org.apache.axis2.context.MessageContext();

            // create SOAP envelope with that payload
            org.apache.axiom.soap.SOAPEnvelope env = null;

            env = toEnvelope(getFactory(operationClient.getOptions().getSoapVersionURI()),
                    browse,
                    optimizeContent(new javax.xml.namespace.QName(ADBBeanImplementation.OPC_NAMESPACE,
                            "browse")));

            //adding SOAP soap_headers
            _serviceClient.addHeadersToEnvelope(env);
            // set the message context with that soap envelope
            messageContext.setEnvelope(env);

            // add the message contxt to the operation client
            operationClient.addMessageContext(messageContext);

            //execute the operation client
            operationClient.execute(true);

            org.apache.axis2.context.MessageContext returnMessageContext = operationClient.getMessageContext(
                    org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
            org.apache.axiom.soap.SOAPEnvelope returnEnv = returnMessageContext.getEnvelope();

            java.lang.Object object = fromOM(
                    returnEnv.getBody().getFirstElement() ,
                    BrowseResponse.class,
                    getEnvelopeNamespaces(returnEnv));

            return (BrowseResponse)object;
        }catch(org.apache.axis2.AxisFault f) {
            manageAxisFaultException(f);
        } finally {
            messageContext.getTransportOut().getSender().cleanup(messageContext);
        }
        return null;
    }


    /**
     *  A utility method that copies the namepaces from the SOAPEnvelope
     */
    private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env) {
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
        while (namespaceIterator.hasNext()) {
            org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
            returnMap.put(ns.getPrefix(),ns.getNamespaceURI());
        }
        return returnMap;
    }


    private javax.xml.namespace.QName[] opNameArray = null;

    private boolean optimizeContent(javax.xml.namespace.QName opName) {
        if (opNameArray == null) {
            return false;
        }
        for (int i = 0; i < opNameArray.length; i++) {
            if (opName.equals(opNameArray[i])) {
                return true;   
            }
        }
        return false;
    }


    private  org.apache.axiom.om.OMElement  toOM(Read param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {
        try{
            return param.getOMElement(Read.MY_QNAME,
                    org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch(org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private  org.apache.axiom.om.OMElement  toOM(ReadResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {
        try{
            return param.getOMElement(ReadResponse.MY_QNAME,
                    org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch(org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private  org.apache.axiom.om.OMElement  toOM(Write param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {
        try{
            return param.getOMElement(Write.MY_QNAME,
                    org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch(org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private  org.apache.axiom.om.OMElement  toOM(WriteResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {
        try{
            return param.getOMElement(WriteResponse.MY_QNAME,
                    org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch(org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }


    }

    private  org.apache.axiom.om.OMElement  toOM(Subscribe param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {
        try{
            return param.getOMElement(Subscribe.MY_QNAME,
                    org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch(org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }


    }

    private  org.apache.axiom.om.OMElement  toOM(SubscribeResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {
        try{
            return param.getOMElement(SubscribeResponse.MY_QNAME,
                    org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch(org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private  org.apache.axiom.om.OMElement  toOM(SubscriptionPolledRefresh param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {
        try{
            return param.getOMElement(SubscriptionPolledRefresh.MY_QNAME,
                    org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch(org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private  org.apache.axiom.om.OMElement  toOM(SubscriptionPolledRefreshResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {
        try{
            return param.getOMElement(SubscriptionPolledRefreshResponse.MY_QNAME,
                    org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch(org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private  org.apache.axiom.om.OMElement  toOM(GetStatus param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {
        try{
            return param.getOMElement(GetStatus.MY_QNAME,
                    org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch(org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private  org.apache.axiom.om.OMElement  toOM(GetStatusResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {
        try{
            return param.getOMElement(GetStatusResponse.MY_QNAME,
                    org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch(org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private  org.apache.axiom.om.OMElement  toOM(SubscriptionCancel param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {
        try{
            return param.getOMElement(SubscriptionCancel.MY_QNAME,
                    org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch(org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private  org.apache.axiom.om.OMElement  toOM(SubscriptionCancelResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {
        try{
            return param.getOMElement(SubscriptionCancelResponse.MY_QNAME,
                    org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch(org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private  org.apache.axiom.om.OMElement  toOM(GetProperties param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {
        try{
            return param.getOMElement(GetProperties.MY_QNAME,
                    org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch(org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private  org.apache.axiom.om.OMElement  toOM(GetPropertiesResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {
        try{
            return param.getOMElement(GetPropertiesResponse.MY_QNAME,
                    org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch(org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private  org.apache.axiom.om.OMElement  toOM(Browse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {
        try{
            return param.getOMElement(Browse.MY_QNAME,
                    org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch(org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private  org.apache.axiom.om.OMElement  toOM(BrowseResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {
        try{
            return param.getOMElement(BrowseResponse.MY_QNAME,
                    org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch(org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }


    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, Read param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault{
        try{
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
            emptyEnvelope.getBody().addChild(param.getOMElement(Read.MY_QNAME,factory));
            return emptyEnvelope;
        } catch(org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }


    /* methods to provide back word compatibility */
    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, Write param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault{
        try{
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
            emptyEnvelope.getBody().addChild(param.getOMElement(Write.MY_QNAME,factory));
            return emptyEnvelope;
        } catch(org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }


    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, Subscribe param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault{
        try{

            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
            emptyEnvelope.getBody().addChild(param.getOMElement(Subscribe.MY_QNAME,factory));
            return emptyEnvelope;
        } catch(org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, SubscriptionPolledRefresh param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault{
        try{
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
            emptyEnvelope.getBody().addChild(param.getOMElement(SubscriptionPolledRefresh.MY_QNAME,factory));
            return emptyEnvelope;
        } catch(org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }


    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, GetStatus param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault{
        try{
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
            emptyEnvelope.getBody().addChild(param.getOMElement(GetStatus.MY_QNAME,factory));
            return emptyEnvelope;
        } catch(org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }


    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, SubscriptionCancel param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault{
        try{
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
            emptyEnvelope.getBody().addChild(param.getOMElement(SubscriptionCancel.MY_QNAME,factory));
            return emptyEnvelope;
        } catch(org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, GetProperties param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault{
        try{
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
            emptyEnvelope.getBody().addChild(param.getOMElement(GetProperties.MY_QNAME,factory));
            return emptyEnvelope;
        } catch(org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, Browse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault{
        try{
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
            emptyEnvelope.getBody().addChild(param.getOMElement(Browse.MY_QNAME,factory));
            return emptyEnvelope;
        } catch(org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    /**
     *  get the default envelope
     */
     private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory) {
         return factory.getDefaultEnvelope();
     }


     private  java.lang.Object fromOM(
             org.apache.axiom.om.OMElement param,
             java.lang.Class type,
             java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault{
         try {
             if (Read.class.equals(type)) {
                 return Read.Factory.parse(param.getXMLStreamReaderWithoutCaching());
             }
             if (ReadResponse.class.equals(type)) {
                 return ReadResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
             }
             if (Write.class.equals(type)) {
                 return Write.Factory.parse(param.getXMLStreamReaderWithoutCaching());
             }
             if (WriteResponse.class.equals(type)) {
                 return WriteResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
             }
             if (Subscribe.class.equals(type)) {
                 return Subscribe.Factory.parse(param.getXMLStreamReaderWithoutCaching());
             }
             if (SubscribeResponse.class.equals(type)) {
                 return SubscribeResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
             }
             if (SubscriptionPolledRefresh.class.equals(type)) {
                 return SubscriptionPolledRefresh.Factory.parse(param.getXMLStreamReaderWithoutCaching());
             }
             if (SubscriptionPolledRefreshResponse.class.equals(type)) {
                 return SubscriptionPolledRefreshResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
             }
             if (GetStatus.class.equals(type)) {
                 return GetStatus.Factory.parse(param.getXMLStreamReaderWithoutCaching());
             }
             if (GetStatusResponse.class.equals(type)) {
                 return GetStatusResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
             }
             if (SubscriptionCancel.class.equals(type)) {
                 return SubscriptionCancel.Factory.parse(param.getXMLStreamReaderWithoutCaching());
             }
             if (SubscriptionCancelResponse.class.equals(type)) {
                 return SubscriptionCancelResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
             }
             if (GetProperties.class.equals(type)) {
                 return GetProperties.Factory.parse(param.getXMLStreamReaderWithoutCaching());
             }
             if (GetPropertiesResponse.class.equals(type)) {
                 return GetPropertiesResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
             }
             if (Browse.class.equals(type)) {
                 return Browse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
             }
             if (BrowseResponse.class.equals(type)) {
                 return BrowseResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
             }
         } catch (java.lang.Exception e) {
             throw org.apache.axis2.AxisFault.makeFault(e);
         }
         return null;
     }
}
