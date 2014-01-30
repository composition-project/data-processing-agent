package eu.ebbits.pwal.impl.driver.wsn;

import org.osgi.service.component.ComponentContext;

import eu.ebbits.pwal.api.exceptions.PWALWriteNotPossibleException;
import eu.ebbits.pwal.api.model.PWALValue;
import eu.ebbits.pwal.api.model.PWALVariable;
import eu.ebbits.pwal.impl.driver.PWALVariablesDelegateImpl;


/**
 * <code>WSNDriver</code> variables delegate.
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 *
 * @author    ISMB
 * @version    %I%, %G%
 * @since    M36demo 1.0
 */
public class WSNVariablesDelegate extends PWALVariablesDelegateImpl {
    
    /**
     * Constructor of the delegate
     * 
     *    @param driver - <code>WSNDriverImpl</code> that uses the delegate
     */
    public WSNVariablesDelegate(WSNDriverImpl driver) {
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
    public PWALValue readNow(PWALVariable variable) {
        return null;
    }

    @Override
    public boolean writeNow(PWALVariable variable, PWALValue value)
            throws PWALWriteNotPossibleException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updatePWALVariablesCollection() {
        return true;
    }
}