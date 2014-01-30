package eu.ebbits.pwal.impl.driver.opcxmlda;

import org.osgi.service.component.ComponentContext;

import eu.ebbits.pwal.impl.driver.PWALEventsDelegateImpl;

/**
 * 
 * The PLC PWAL adapter currently is oriented mostly to variables (as the current OPC connector mostly relies on a read-write approach).
 * Future possible extensions might consider events definition (but this should be considered in future iterations,  as involves discussions with WP5 and WP7)
 *  
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 *
 * @author        ISMB
 * @version        %I%, %G%
 * @see            eu.ebbits.pwal.api.driver.PWALDriver
 * @since         PWAL 0.1.0
 */
public class OPCEventsDelegate extends PWALEventsDelegateImpl {

    /**
     * Constructor of the delegate
     * 
     *    @param driver - <code>OPCDriverImpl</code> that uses the delegate
     */
    public OPCEventsDelegate(OPCDriverImpl driver) {
        super(driver);
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
    public void updatePWALEventsCollection() {
        // TODO Auto-generated method stub
        
    }

}
