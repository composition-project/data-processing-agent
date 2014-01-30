package eu.ebbits.pwal.impl.driver.coolingcircuit;

import org.osgi.service.component.ComponentContext;

import eu.ebbits.pwal.impl.driver.PWALServicesDelegateImpl;

/**
 * Services Delegate. CoolingCircuitDriver does not currently support PWALServices.
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 *
 * @author     FIT
 * @version    %I%, %G%
 * @see        eu.ebbits.pwal.api.driver.PWALDriver
 */
public class CoolingCircuitServicesDelegate extends PWALServicesDelegateImpl {

    /**
     * Constructor of the <code>CoolingCircuitServicesDelegate</code>
     * 
     * @param driver - driver that uses the Services Delegate
     * 
     */
    public CoolingCircuitServicesDelegate(CoolingCircuitDriverImpl driver) {
        super(driver);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(ComponentContext context) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updatePWALServicesCollection() {
        // TODO Auto-generated method stub

    }
}
