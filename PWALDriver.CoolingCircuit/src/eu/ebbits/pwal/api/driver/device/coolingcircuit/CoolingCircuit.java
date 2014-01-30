package eu.ebbits.pwal.api.driver.device.coolingcircuit;

/**
 * Interface to access the CoolingCircuit via ArduinoSerial.
 *
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 * 
 * @author ISMB
 * @version    %I%, %G%
 * @since      PWAL 0.1.0
 * 
 */
public interface CoolingCircuit {
    /**
     * @param id
     * @param speed
     *          (from 0..127)
     */
    void setPump(int id, byte speed);

    /**
     * Gets the latest temperature value
     * 
     * @return the latest temperature in degrees Celsius, as a float
     */
    float getTemperature(int id);

    /**
     * Gets the latest water flow value
     * 
     * @return the latest water flow value in lpm, as a float
     */
    float getWaterFlow(int id);


    /**
     * Add a CoolingCircuitListener
     * 
     * @param listener
     *          the Listener to add
     */
    void addListener(CoolingCircuitListener listener);
}
