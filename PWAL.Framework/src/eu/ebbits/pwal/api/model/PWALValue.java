package eu.ebbits.pwal.api.model;

import java.util.Date;

/**
 * Class to encapsulate all variables and attributes values of any type
 * supported by the <code>PWAL</code>.
 * <p>
 * <code>PWALValue</code>s represent physical world measurements which can be 
 * acquired by the <code>PWALDriver</code> and propagated to the Device 
 * Manager in the form of a <code>PWALVariable</code>.
 * <p>
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 *
 * @author     ISMB
 * @version    %I%, %G%
 * @see        eu.ebbits.pwal.api.model.PWALService
 * @see            eu.ebbits.pwal.api.model.PWALVariable
 * @since      PWAL 0.1.0
 */
public class PWALValue {
    
    /** Internal value. */
    private Object value;
    
    /** Internal value. */
    private Date timestamp;
    
    /**
     * Generic constructor.
     * 
     * @param val -    the <code>Object</code> value
     * 
     * @since      PWAL 0.1.0
     */
    public PWALValue(final Object val) {
        this.value = val;
        this.timestamp = new Date();
    }

    /**
     * Generic constructor.
     * 
     * @param val -    the <code>Object</code> value
     * @param tms -    the <code>int</code> timestamp epoch in ms
     * 
     * @since      PWAL 0.1.0
     */
    public PWALValue(final Object val, final int tms) {
        this.value = val;
        this.timestamp = new Date(tms);
    }

    /**
     * Generic constructor.
     * 
     * @param val -    the <code>Object</code> value
     * @param tim -    the <code>Date</code> timestamp
     * 
     * @since      PWAL 0.1.0
     */
    public PWALValue(final Object val, final Date tim) {
        this.value = val;
        this.timestamp = tim;
    }

    /**
     * Retrieves the value as an object.
     * 
     * @return        the <code>Object</code> value
     * 
     * @since      PWAL 0.1.0
     */
    public final Date getTimestamp() {
        return this.timestamp;
    }
    
    /**
     * Retrieves the PWALValue type.
     * 
     * @return        the value's <code>Class</code>
     * 
     * @since      PWAL 0.1.0
     */
    public final Class<?> getType() {
        return this.value.getClass();
    }
    
    /**
     * Retrieves the value as an object.
     * 
     * @return        the <code>Object</code> value
     * 
     * @since      PWAL 0.1.0
     */
    public final Object getValue() {
        return this.value;
    }
    
    /**
     * Retrieves the value as a boolean.
     * 
     * @return        the <code>boolean</code> value
     * 
     * @since      PWAL 0.1.0
     */
    public final boolean toBoolean() {
        return ((Number) value).doubleValue() != 0.0;
    }
    
    /**
     * Retrieves the value as a byte.
     * 
     * @return        the <code>byte</code> value
     * 
     * @since      PWAL 0.1.0
     */
    public final byte toByte() {
        return ((Number) value).byteValue();
    }
    
    /**
     * Retrieves the value as a double.
     * 
     * @return        the <code>double</code> value
     * 
     * @since      PWAL 0.1.0
     */
    public final double toDouble() {
        return ((Number) value).doubleValue();
    }
    
    /**
     * Retrieves the value as a single.
     * 
     * @return        the <code>float</code> value
     * 
     * @since      PWAL 0.1.0
     */
    public final float toFloat() {
        return ((Number) value).floatValue();
    }
    
    /**
     * Retrieves the value as an integer.
     * 
     * @return        the <code>int</code> value
     * 
     * @since      PWAL 0.1.0
     */
    public final int toInteger() {
        return ((Number) value).intValue();
    }
    
    /**
     * Retrieves the value as a long integer.
     * 
     * @return        the <code>long</code> value
     * 
     * @since      PWAL 0.1.0
     */
    public final long toLong() {
        return ((Number) value).longValue();
    }
    
    /**
     * Retrieves the value as a Number object.
     * 
     * @return        the <code>Number</code> value
     * 
     * @since      PWAL 0.1.0
     */
    public final Number toNumber() {
        return ((Number) value);
    }
    
    /**
     * Retrieves the value as a short integer.
     * 
     * @return        the <code>short</code> value
     * 
     * @since      PWAL 0.1.0
     */
    public final short toShort() {
        return ((Number) value).shortValue();
    }
    
    /**
     * Retrieves the value as a string.
     * 
     * @return        the <code>String</code> value
     * 
     * @since      PWAL 0.1.0
     */
    public final String toString() {
        return value.toString();
    }

    /**
     * Sets or replaces the value
     * 
     * @param tms -    the <code>int</code> timestamp epoch
     * 
     * @since      PWAL 0.1.0
     */
    public final void setTimestamp(final int tms) {
        this.timestamp = new Date(tms);
    }
    
    /**
     * Sets or replaces the value
     * 
     * @param tim -    the <code>Date</code> timestamp
     * 
     * @since      PWAL 0.1.0
     */
    public final void setTimestamp(final Date tim) {
        this.timestamp = tim;
    }
    
    /**
     * Sets or replaces the value
     * 
     * @param val -    the <code>String</code> to parse
     * 
     * @since      PWAL 0.1.0
     */
    public final void setValue(final Object val) {
        this.value = val;
    }
    
    /**
     * Sets or replaces the value parsing a string.
     * 
     * @param val -    the <code>String</code> to parse
     * 
     * @since      PWAL 0.1.0
     */
    public final void setValueFromString(final String val) {
        //TODO we should fix this by modifying correctly the 
        //PWALValue/PWALVariable stuff, exploiting the xsitype in the browse 
        //PLC function...
        if (val.toLowerCase().contentEquals("true")) {
            this.value = 1.0;
        } else if (val.toLowerCase().contentEquals("false")) {
            this.value = 0.0;
        } else {
            try {
                this.value = new Double(Double.parseDouble(val));
            } catch (Exception e) {
                //nop
            }
        }
    }
}
