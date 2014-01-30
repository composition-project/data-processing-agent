package eu.ebbits.pwal.api.driver;

/**
* Generic interface for PWAL drivers.
*
* <p> 
* Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
*
* @author        ISMB
* @version        %I%, %G%
* @see         PWALEventsDelegate
* @see         PWALServicesDelegate
* @see         PWALVariablesDelegate
* @see         PWALDelegate
* 
* @since        PWAL 0.2.0
*/
public interface PWALDriver extends PWALDelegate {
    
    /**
     * Retrieves the driver id.
     * 
     * @return         Driver unique identification
     * 
     * @since        PWAL 0.2.0
     */
    String getDriverID();
    
    /**
     * Retrieves the driver name.
     * 
     * @return         Driver name that describe the driver
     * 
     * @since        PWAL 0.2.0
     */
    String getDriverName();
    
    /**
     * Retrieves the drivers version.
     * 
     * @return Version of the driver
     * 
     * @since        PWAL 0.2.0
     */
    String getDriverVersion();

    /**
     * Retrieves the Events delegate.
     * 
     * @return        instance of <code>PWALEventsDelegate</code> used by the driver to manage the events
     * 
     * @since        PWAL 0.2.0
     */
    PWALEventsDelegate getEventsDelegate();
    
    /**
     * Retrieves the Services delegate.
     * 
     * @return         instance of <code>PWALEventsDelegate</code> used by the driver to manage the services
     * 
     * @since        PWAL 0.2.0
     */
    PWALServicesDelegate getServicesDelegate();
    
    /**
     * Retrieves the Variables delegate.
     * 
     * @return         instance of <code>PWALVariablesDelegate</code> used by the driver to manage the variables
     * 
     * @since        PWAL 0.2.0
     */
    PWALVariablesDelegate getVariablesDelegate();
}
