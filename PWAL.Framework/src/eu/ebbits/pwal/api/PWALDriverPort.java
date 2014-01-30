package eu.ebbits.pwal.api;

import eu.ebbits.pwal.api.driver.PWALDriver;

/**
 * <code>PWALDriverPort</code> service interface.
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 *
 * @author    ISMB
 * @version    %I%, %G%
 * @since    PWAL 0.2.0
 */
public interface PWALDriverPort {

    /**
     * Checks whether a Driver is registered or not.
     * 
     * @param driver - the PWALDriver to check.
     * 
     * @return        <code>True</code> if the Driver is loaded,
     *                 <code>False</code> otherwise.
     * 
     * @since        PWAL 2.0
     */
    boolean isDriverRegistered(PWALDriver driver);

    /**
     * Registers a Driver.
     * 
     * @param driver - the PWALDriver to register.
     * 
     * @since        PWAL 0.2.0
     */
    void registerDriver(PWALDriver driver);

    /**
     * Unregisters a Driver.
     * 
     * @param driver - the PWALDriver to unregister.
     * 
     * @since        PWAL 0.2.0
     */
    void unregisterDriver(PWALDriver driver);

}
