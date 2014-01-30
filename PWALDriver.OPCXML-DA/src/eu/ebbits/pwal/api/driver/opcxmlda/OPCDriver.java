package eu.ebbits.pwal.api.driver.opcxmlda;

import eu.ebbits.pwal.api.driver.PWALDriver;

/**
 * <code>OPCDriver</code> service interface.
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 *
 * @author    ISMB
 * @version    %I%, %G%
 * @since    PWAL 0.1.0
 */
public interface OPCDriver extends PWALDriver {

    /**
     * Retrieves the server endpoint
     * 
     * @return a <code>String</code> containing the server endpoint    
     *  
     */
    String getOpcXmlServerEndpoint();

    /**
     * Sets the server endpoint
     * 
     * @param opcXmlServerEndpoint the server enpoint to be set
     */
    void setOpcXmlServerEndpoint(String opcXmlServerEndpoint);

    /**
     * Retrieves the access protocol used 
     * 
     * @return a <code>String</code> containing the access protocol used
     */
    String getOpcXmlAccessProtocol();

    /**
     * Sets the access protocol to be used
     * 
     * @param opcXmlAccessProtocol the access protocol to be used
     */
    void setOpcXmlAccessProtocol(String opcXmlAccessProtocol);

    /**
     * Retrieves the OPC symbolic name
     * 
     *  @return a String containing the OPC symbolic name
     */
    String getOpcXmlSymbolPath();

    /**
     * Set the OPC symbolic name
     * 
     * @param opcXmlSymbolPath the symbolic name to set
     */
    void setOpcXmlSymbolPath(String opcXmlSymbolPath);
}
