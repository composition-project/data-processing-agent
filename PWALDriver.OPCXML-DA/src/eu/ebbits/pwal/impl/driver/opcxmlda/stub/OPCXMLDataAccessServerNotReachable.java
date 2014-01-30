package eu.ebbits.pwal.impl.driver.opcxmlda.stub;

/**
 * Class to be used in case of server not reachable
 * 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 *
 * @author     ISMB
 * @version    %I%, %G%
 * @see        eu.ebbits.pwal.api.driver.PWALDriver
 * @since      PWAL 0.1.0
 */
public class OPCXMLDataAccessServerNotReachable extends OPCXMLDataAccessMessage {

    /**
     * Creates the exception with the message passed as parameter
     * 
     * @param msg - message to set for the exception as <code>String</code>
     */
    public OPCXMLDataAccessServerNotReachable(String msg) {
        super(msg);
        this.setInnerMessage(msg);
    }

    /**
     * Creates the exception with message corresponding to the url passed as parameter
     * and inner exception the <code>Exception</code> passed as parameter
     * 
     * @param url - url of the unreachable server as <code>String</code>
     * @param e - <code>Exception</code> to set as inner exception
     */
    public OPCXMLDataAccessServerNotReachable(String url, Exception e) {
        super(url + e.toString());
        this.setInnerException(e);
        this.setInnerMessage(url);
    }

}
