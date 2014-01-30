package eu.ebbits.pwal.api.driver;

import eu.ebbits.pwal.api.model.PWALControlEvent;

/**
 * Interface for receiving and processing events generated from the 
 * <code>PWALDriver</code>.
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 *
 * @author        ISMB
 * @version        %I%, %G%
 * @see         eu.ebbits.pwal.api.driver.PWALDriver
 * @since        PWAL 0.1.0
 */
public interface PWALDelegateSubscriber {

    /** 
    * Processes the driver started event.
    *
    * @param e -     the <code>PWALDriverControlEvent</code> to process
    *  
    * @see            eu.ebbits.pwal.api.model.PWALControlEvent
    * @since        PWAL 0.1.0
    */
    void driverStarted(PWALControlEvent e);
    
    /** 
    * Processes the driver stopped event.
    *
    * @param e -    the <code>PWALDriverControlEvent</code> to process
    *  
    * @see            eu.ebbits.pwal.api.model.PWALControlEvent
    * @since        PWAL 0.1.0
    */
    void driverStopped(PWALControlEvent e);
    
    /** 
    * Processes driver debug events.
    *
    * @param e -     the <code>PWALDriverControlEvent</code> to process
    *  
    * @see            eu.ebbits.pwal.api.model.PWALControlEvent
    * @since        PWAL 0.1.0
    */
    void driverDebug(PWALControlEvent e);
    
    /** 
    * Processes driver information events.
    *
    * @param e -    the <code>PWALDriverControlEvent</code> to process
    *  
    * @see            eu.ebbits.pwal.api.model.PWALControlEvent
    * @since        PWAL 0.1.0
    */
    void driverInfo(PWALControlEvent e);
    
    /** 
    * Processes driver warning events.
    *
    * @param e -    the <code>PWALDriverControlEvent</code> to process
    *  
    * @see            eu.ebbits.pwal.api.model.PWALControlEvent
    * @since        PWAL 0.1.0
    */
    void driverWarning(PWALControlEvent e);
    
    /** 
    * Processes driver error events.
    *
    * @param e -    the <code>PWALDriverControlEvent</code> to process
    *  
    * @see            eu.ebbits.pwal.api.model.PWALControlEvent
    * @since        PWAL 0.1.0
    */
    void driverError(PWALControlEvent e);
    
    /** 
    * Processes driver critical error events.
    *
    * @param e -    the <code>PWALDriverControlEvent</code> to process
    *  
    * @see            eu.ebbits.pwal.api.model.PWALControlEvent
    * @since        PWAL 0.1.0
    */
    void driverCriticalError(PWALControlEvent e);
    
}
