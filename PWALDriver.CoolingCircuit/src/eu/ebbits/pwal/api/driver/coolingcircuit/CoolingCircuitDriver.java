package eu.ebbits.pwal.api.driver.coolingcircuit;

import eu.ebbits.pwal.api.driver.PWALDriver;

/**
 * This is the interface for PWALDriver for the CoolingCircuit/ArduinoSerial prototype.
 * 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 * 
 * @author FIT
 * @version %I%, %G%
 * @see eu.ebbits.pwal.impl.driver.PWALDriverImpl
 * @since      PWAL 0.1.0
 */
public interface CoolingCircuitDriver extends PWALDriver {

    /**
     * Gets the current water flow.
     * 
     * @return the current state of the water flow sensor (litres per minute)
     */
    float getCurrentWaterFlow();

    /**
     * Gets the current temperature.
     * 
     * @return the current state of the water temperature sensor (degrees Celsius)
     */
    float getCurrentTemperature();

    /**
     * Gets the current pump speed.
     * 
     * @return the current state of the pump speed actuator (speed level 0..127)
     */
    byte getCurrentSpeed();

    /**
     * Sets the new state of the pump speed actuator.
     * 
     * @param speed
     *          the speed level to set, in the range 0..127
     * @return whether the value was successfully set (i.e., was in valid range)
     */
    boolean setCurrentSpeed(byte speed);
}
