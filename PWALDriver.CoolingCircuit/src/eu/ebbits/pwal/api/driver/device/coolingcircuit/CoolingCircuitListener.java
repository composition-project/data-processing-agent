package eu.ebbits.pwal.api.driver.device.coolingcircuit;

/**
 * Interface for sensor updates from the cooling circuit. To be implemented by
 * listeners that should be added using
 * {@link CoolingCircuit#addListener(CoolingCircuitListener)}.
 * 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 * 
 * @author ISMB
 * @version    %I%, %G%
 * @since      PWAL 0.1.0
 *
 */
public interface CoolingCircuitListener {
    /**
     * Notification of new water flow values
     * @param id the ID of the water flow sensor
     * @param lpm the value, in litres per minute
     */
    void updateWaterFlow(int id, float lpm);

    /**
     * Notification of new temperature values
     * @param id the ID of the temperature sensor
     * @param degCelsius the value, in degrees Celsius
     */
    void updateTemperature(int id, float degCelsius);
}
