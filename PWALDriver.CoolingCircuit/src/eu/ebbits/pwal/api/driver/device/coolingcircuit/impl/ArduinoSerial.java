package eu.ebbits.pwal.api.driver.device.coolingcircuit.impl;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.TooManyListenersException;

import org.apache.log4j.Logger;

/**
 * Arduino sensor/actuator platform. The Arduino board is connected to the
 * serial port, communication is using a text-based Protocol.
 * 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 * 
 * @author    ISMB
 * @version    %I%, %G%
 * @since      PWAL 0.1.0
 * 
 */
public class ArduinoSerial implements SerialPortEventListener {

    private final Logger log = Logger.getLogger(ArduinoSerial.class.getName());

    private SerialPort serialPort;

    /** Buffered input stream from the port */
    private InputStream input;
    /** The output stream to the port */
    private OutputStream output;
    /** Milliseconds to block while waiting for port open */
    private static final int TIME_OUT = 2000;
    /** Default bits per second for COM port. */
    private static final int DATA_RATE = 9600;

    private String buffer = "";

    /**
     * The set of {@link ArduinoListener}s that are notified of new events.
     */
    private Set<ArduinoListener> listeners;

    /**
     * Constructor. Creates a new ArduinoSerial instance using the given port
     * name.
     * 
     * @param portName
     */
    public ArduinoSerial(String portName) {
        if ("".equals(portName)) {
            throw new IllegalArgumentException("portName must be provided!");
        }

        CommPortIdentifier portId = null;
        @SuppressWarnings("rawtypes")
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        // iterate through, looking for the port
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum
                    .nextElement();
            if (currPortId.getName().equals(portName)) {
                portId = currPortId;
                break;
            }
        }
        if (portId == null) {
            throw new IllegalArgumentException("Could not open ComPort "
                    + portName);
        } else {
            openSerialPort(portId);
        }
    }

    
    private void openSerialPort(CommPortIdentifier portId) {
        // open serial port, and use class name for the appName.
        try {
            serialPort = (SerialPort) portId.open(this.getClass().getName(),
                    TIME_OUT);

            // set port parameters
            serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

            // open the streams
            input = serialPort.getInputStream();
            output = serialPort.getOutputStream();

            // add event listeners
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
            // LOG.debug("Listening on port " + portName);
        } catch (PortInUseException e) {
            throw new IllegalArgumentException(
                    "Serial Port in use. On Mac OS X, ", e);
        } catch (UnsupportedCommOperationException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        } catch (TooManyListenersException e) {
            throw new IllegalArgumentException(
                    "Too many listeners for serial port "
                            + serialPort.getName(), e);
        }

        this.listeners = new HashSet<ArduinoListener>();
    }
    
    protected void stopSerialConnection() {
        flush();
        close();
    }

    /**
     * Sends a message to the sensor/actuator platform using the Protocol.
     * @param key the key to send, as a String
     * @param value the value to send, as a byte
     */
    public void writeToSerial(String key, byte value) {
        try {
            output.write(Protocol.encode(key, value).getBytes());
        } catch (IOException e) {
            log.error(e.toString());
        }
    }

    private void flush() {
        try {
            output.flush();
        } catch (IOException e) {
            log.error(e.toString());
        }
    }

    /**
     * This should be called when you stop using the port. This will prevent
     * port locking on platforms like Linux.
     */
    private synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    /**
     * Handle an event on the serial port. Read the data and print it.
     */
    public synchronized void serialEvent(SerialPortEvent oEvent) {
        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            int available;
            try {
                available = input.available();
                byte chunk[] = new byte[available];
                input.read(chunk, 0, available);
                // Displayed results are codepage dependent
                String sensorResult = new String(chunk);
                // TODO Ottos analyze sensor readings. Use some protocol with
                // pre- and postfix
                // LOG.info(sensorResult);
                sensorResult = buffer + sensorResult;
                int lastDelimiter = sensorResult
                        .lastIndexOf(Protocol.DELIMITER_STRING);
                if (lastDelimiter == -1) {
                    buffer = sensorResult;
                    return;
                }
                buffer = sensorResult.substring(lastDelimiter + 1);
                sensorResult = sensorResult.substring(0, lastDelimiter + 1);
                for (String[] result : Protocol.decodeMultiple(sensorResult)) {
                    if (result != null) {
                        publishSensorEvent(result[0], result[1]);
                    }
                }

                // TODO check if using a custom exception instead of null will
                // lead to performance problems

            } catch (IOException e) {
                // LOG.error("Cannot read from Serial Port.", e);
            }
        }
        // Ignore all the other eventTypes, but you should consider the other
        // ones.
    }

    /**
     * Takes the key-value pair and handles it to notify the {@link #listeners}. Called from {@link #serialEvent(SerialPortEvent)}.
     * @param key the key of the event, as a String
     * @param value the value of the event, as a String
     */
    private void publishSensorEvent(String key, String value) {
        int id = Integer.parseInt(key.substring(1));

        if (key.startsWith("t")) {
            float degreesCelsius = Float.parseFloat(value);
            for (ArduinoListener listener : listeners) {
                listener.updateTemperature(id, degreesCelsius);
            }
        } else if (key.startsWith("m")) {
            boolean motion = (Integer.parseInt(value) == 1);
            for (ArduinoListener listener : listeners) {
                listener.updateMotion(motion);
            }
        } else if (key.startsWith("l")) {
            int percentage = Integer.parseInt(value);
            for (ArduinoListener listener : listeners) {
                listener.updateBrightness(percentage);
            }
        } else if (key.startsWith("f")) {
            float lpm = Float.parseFloat(value);
            for (ArduinoListener listener : listeners) {
                listener.updateWaterFlow(id, lpm);
            }
        }
    }

    /**
     * Adds a new {@link ArduinoListener} to the {@link #listeners}.
     * @param listener the {@link ArduinoListener} to add.
     */
    public void addListener(ArduinoListener listener) {
        this.listeners.add(listener);
    }
}
