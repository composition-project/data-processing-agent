package eu.ebbits.pwal.impl.driver.opcxmlda;

import org.apache.log4j.Logger;
import org.osgi.service.component.ComponentContext;

import eu.ebbits.pwal.api.driver.PWALEventsDelegate;
import eu.ebbits.pwal.api.driver.PWALServicesDelegate;
import eu.ebbits.pwal.api.driver.PWALVariablesDelegate;
import eu.ebbits.pwal.api.driver.opcxmlda.OPCDriver;
import eu.ebbits.pwal.impl.driver.PWALDriverImpl;
import eu.ebbits.pwal.impl.driver.opcxmlda.stub.OPCXMLDataAccessClient;

/**
 * PLC PWAL driver implementation, employing OPC.
 * 
 * The PLC PWAL adapter currently is oriented mostly to variables 
 * (as the current OPC connector mostly relies on a read-write approach).
 *  
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 *
 * @author     ISMB
 * @version    %I%, %G%
 * @see        eu.ebbits.pwal.api.driver.PWALDriver
 * @since      PWAL 0.1.0
 */
public class OPCDriverImpl extends PWALDriverImpl implements OPCDriver {

    /* Logger reference */
    @SuppressWarnings("unused")
    private static Logger log = Logger.getLogger(OPCDriver.class.getName());

    /*  Reference to the OPC client */
    private OPCXMLDataAccessClient client;

    @Override
    public void init(ComponentContext context) {
        this.client = new OPCXMLDataAccessClient();
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected PWALEventsDelegate initEventsDelegate() {
        return (PWALEventsDelegate) new OPCEventsDelegate(this);
    }
    
    @Override
    protected PWALServicesDelegate initServicesDelegate() {
        return (PWALServicesDelegate) new OPCServicesDelegate(this);
    }

    @Override
    protected PWALVariablesDelegate initVariablesDelegate() {
        return (PWALVariablesDelegate) new OPCVariablesDelegate(this);
    }

    /**
     * Retrieves the server endpoint
     * 
     * @return a <code>String</code> containing the server endpoint    
     *  
     */
    public String getOpcXmlServerEndpoint() {
        return this.client.getOpcXmlServerEndpoint();
    }

    /**
     * Sets the server endpoint
     * 
     * @param opcXmlServerEndpoint the server enpoint to be set
     */
    public void setOpcXmlServerEndpoint(String opcXmlServerEndpoint) {
        this.client.setOpcXmlServerEndpoint(opcXmlServerEndpoint);
    }

    /**
     * Retrieves the access protocol used 
     * 
     * @return a <code>String</code> containing the access protocol used
     */
    public String getOpcXmlAccessProtocol() {
        return this.client.getOpcXmlAccessProtocol();
    }

    /**
     * Sets the access protocol to be used
     * 
     * @param opcXmlAccessProtocol the access protocol to be used
     */
    public void setOpcXmlAccessProtocol(String opcXmlAccessProtocol) {
        this.client.setOpcXmlAccessProtocol(opcXmlAccessProtocol);
    }

    /**
     * Retrieves the OPC symbolic name
     * 
     *  @return a String containing the OPC symbolic name
     */
    public String getOpcXmlSymbolPath() {
        return this.client.getOpcXmlSymbolPath();
    }

    /**
     * Set the OPC symbolic name
     * 
     * @param opcXmlSymbolPath the symbolic name to set
     */
    public void setOpcXmlSymbolPath(String opcXmlSymbolPath) {
        this.client.setOpcXmlSymbolPath(opcXmlSymbolPath);
    }

    /**
     * Retrieves the reference to the OPC client
     * 
     * @return    the reference to the OPC client as <code>OPCXMLDataAccessClient</code>
     */
    protected OPCXMLDataAccessClient getClient() {
        return client;
    }

    /**
     * Sets the reference to the OPC client
     * 
     * @param client - the client to set as <code>OPCXMLDataAccessClient</code>
     */
    protected void setClient(OPCXMLDataAccessClient client) {
        this.client = client;
    }
}
