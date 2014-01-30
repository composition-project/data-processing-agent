package eu.ebbits.pwal.impl.driver.llrpreader;

import org.osgi.service.component.ComponentContext;

import eu.ebbits.pwal.api.exceptions.PWALWriteNotPossibleException;
import eu.ebbits.pwal.api.model.PWALValue;
import eu.ebbits.pwal.api.model.PWALVariable;
import eu.ebbits.pwal.impl.driver.PWALVariablesDelegateImpl;

/**
 * <code>LLRPReaderDriver</code> variables delegate.
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 *
 * @author    ISMB
 * @version    %I%, %G%
 * @since   PWAL 0.2.0
 */
public class LLRPReaderVariablesDelegate extends PWALVariablesDelegateImpl {

    /**
     * Constructor of the variables delegate of the LLRP driver
     * 
     * @param driver - driver that uses the delegate as <codeLLRPReaderDriverImpl<code>
     */
    public LLRPReaderVariablesDelegate(LLRPReaderDriverImpl driver) {
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
        if(variable.getName().equals("ReadCycle")) {
            int response = ((LLRPReaderDriverImpl) this.getDriver()).getReader().getReadCycle();
            return new PWALValue(response);
        } else if(variable.getName().equals("InventoryOn")) {
            boolean response = ((LLRPReaderDriverImpl) this.getDriver()).getReader().isInventoryOn();
            return new PWALValue(response);            
        }
        return null;
    }

    @Override
    public boolean writeNow(PWALVariable variable, PWALValue value)
            throws PWALWriteNotPossibleException {
        if(variable.getName().equals("ReadCycle")) {
            ((LLRPReaderDriverImpl) this.getDriver()).getReader().setReadCycle(value.toInteger());
        } else if(variable.getName().equals("InventoryOn")) {
            ((LLRPReaderDriverImpl) this.getDriver()).getReader().setInventoryOn(value.toBoolean());
        }
        return true;
    }

    @Override
    public boolean updatePWALVariablesCollection() {
        this.registerPWALVariable("ReadCycle", null);
        this.registerPWALVariable("InventoryOn", null);
        return true;
    }

}
