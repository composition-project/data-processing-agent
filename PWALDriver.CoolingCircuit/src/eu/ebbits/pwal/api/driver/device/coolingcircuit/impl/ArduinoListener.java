package eu.ebbits.pwal.api.driver.device.coolingcircuit.impl;

/**
 * Interface for sensor event subscribers
 * 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 * 
 * @author simon
 * @version    %I%, %G%
 * @since      PWAL 0.1.0
 * 
 */
public interface ArduinoListener {

    /**
     * Called when new brightness value is received from the Arduino
     * @param percentage the brightness percentage, as an int
     */
    void updateBrightness(int percentage);

    /**
     * Called when a new temperature value is received from the Arduino
     * @param degreesCelsius the temperature in degrees Celsius, as a float
     */
    void updateTemperature(int id, float degreesCelsius);
    
    /**
     * Called when a new motion value is received from the Arduino
     * @param motion
     */
    void updateMotion(boolean motion);
    
    /**
     * Called when a new water flow value is received from the Arduino
     * @param lpm
     */
    void updateWaterFlow(int id, float lpm);
}
