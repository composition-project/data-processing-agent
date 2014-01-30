package eu.ebbits.pwal.impl.driver.coolingcircuit;

import org.osgi.service.component.ComponentContext;

import eu.ebbits.pwal.api.exceptions.PWALWriteNotPossibleException;
import eu.ebbits.pwal.api.model.PWALValue;
import eu.ebbits.pwal.api.model.PWALVariable;
import eu.ebbits.pwal.impl.driver.PWALVariablesDelegateImpl;

/**
 * Variables delegate. Sensor and actuator state is accessed through this delegate.
 * To read the values, use {@link #readNow(String)} or {@link #readNow(PWALVariable)}.
 * To set a new value (only applicable for pump speed), use {@link #writeNow(PWALVariable, PWALValue)}.
 * Use the static constants of this class for variable names.
 * 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 * 
 * @author ISMB
 * @version %I%, %G%
 * @see eu.ebbits.pwal.api.driver.PWALDriver
 * @since PWAL 0.1.0
 */
public class CoolingCircuitVariablesDelegate extends PWALVariablesDelegateImpl {

    public static final String VARIABLE_WATER_FLOW = "WaterFlow";
    public static final String VARIABLE_TEMPERATURE = "Temperature";
    public static final String VARIABLE_PUMP_SPEED = "PumpSpeed";

    /**
     * Constructor of the <code>CoolingCircuitVariablesDelegate</code>
     * 
     * @param driver - driver that uses the Variables Delegate
     * 
     */
    public CoolingCircuitVariablesDelegate(CoolingCircuitDriverImpl driver) {
        super(driver);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(ComponentContext context) {
        this.getAvailableVariables().put(VARIABLE_WATER_FLOW, new PWALVariable(
                VARIABLE_WATER_FLOW, null));
        this.getAvailableVariables().put(VARIABLE_TEMPERATURE, new PWALVariable(
                VARIABLE_TEMPERATURE, null));
        this.getAvailableVariables().put(VARIABLE_PUMP_SPEED, new PWALVariable(
                VARIABLE_PUMP_SPEED, 0));
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public PWALValue readNow(PWALVariable variable) {

        Number value = null;

        if (variable.getName().equals(VARIABLE_WATER_FLOW)) {
            value = ((CoolingCircuitDriverImpl) this.getDriver()).getCurrentWaterFlow();
        } else if (variable.getName().equals(VARIABLE_TEMPERATURE)) {
            value = ((CoolingCircuitDriverImpl) this.getDriver())
                    .getCurrentTemperature();
        } else if (variable.getName().equals(VARIABLE_PUMP_SPEED)) {
            value = ((CoolingCircuitDriverImpl) this.getDriver()).getCurrentSpeed();
        }

        return new PWALValue(value);

    }

    @Override
    public boolean writeNow(PWALVariable variable, PWALValue value)
            throws PWALWriteNotPossibleException {
        if (variable.getName().equals(VARIABLE_PUMP_SPEED)) {
            return ((CoolingCircuitDriverImpl) this.getDriver()).setCurrentSpeed((Byte) value.getValue());
        }
        throw new PWALWriteNotPossibleException();
    }

    @Override
    public boolean updatePWALVariablesCollection() {
        // TODO Auto-generated method stub
        return true;
    }
}
