package eu.ebbits.pwal.api.model;

/**
 * Class to represent specific Events types (as well as Event instances) related
 * to the correct functioning of the {@link PWALDriver}. 
 * For example, this model could inform about a Driver error happening, or about
 * the driver restarting. 
 * PWALDriverControlEvents can be used to support driver-management features. 
 * 
 *  
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 *
 * @author     ISMB
 * @version    %I%, %G%
 * @see        eu.ebbits.pwal.impl.driver.PWALDriver
 * @since      PWAL 0.1.0
 */
public class PWALControlEvent extends PWALEvent {

    //TODO this class might not be fully consistent: it could be a refactored

    /**
     * PWALDriverControlEvents can represent a limited number of driver events 
     * types, specified in this <code>enum</code>.
     */
    public enum ControlEventType {
        /** Stop event. */ 
        DEFAULT_STOP,
        /** Start event. */ 
        DEFAULT_START,
        /** Debug event. */ 
        DEFAULT_DEBUG,
        /** Information event. */ 
        DEFAULT_INFO,
        /** Warning event. */ 
        DEFAULT_WARNING,
        /** Error event. */ 
        DEFAULT_ERROR,
        /** Critical event. */ 
        DEFAULT_CRITICAL
    }
    
    /** Generic message (comment) associated with this event. */
    private String innermsg;

    /**
     * Since this class is often used to inform about driver errors, 
     * it has been decided to use it to directly support the embedding of a 
     * Exception.
     */
    private Exception innerexp;

    /** Type of event control. */
    private ControlEventType type;
    
    /**
     * Generic, message-oriented constructor.
     * 
     * @param msg -    the control event message <code>String</code>
    *  
    * @since        PWAL 0.1.0
     */
    public PWALControlEvent(final String msg) {
        super(msg);
    }

    /**
     * Generic, type-oriented constructor.
     * 
     * @param dbug - a <code>ControlEventType</code>
    *  
    * @since        PWAL 0.1.0
     */
    public PWALControlEvent(final ControlEventType dbug) {
        super(dbug.toString());
        this.innermsg = dbug.toString();
    }

    /**
     * Generic, type, exception-oriented constructor.
     * 
     * @param dbug - a <code>ControlEventType</code>
     * @param e -    an <code>Exception</code>
    *  
    * @since        PWAL 0.1.0
     */
    public PWALControlEvent(final ControlEventType dbug, 
                                  final Exception e) {
        super(dbug.toString() + e.toString()); //TODO to be improved
        this.innermsg = dbug.toString();
        this.innerexp = e;
    }

    /**
     * Generic, message, exception-oriented constructor.
     * 
     * @param msg -    the control event message <code>String</code>
     * @param e -    an <code>Exception</code>
    *  
    * @since        PWAL 0.1.0
     */
    public PWALControlEvent(final String msg, final Exception e) {
        super(msg + e.toString());
        this.innermsg = msg;
        this.innerexp = e;
    }
    
    /**
     * Generic exception-oriented constructor.
     *
     * @param e -    a <code>PWALDriverControlEvent</code>
    *  
    * @since        PWAL 0.1.0
     */
    public PWALControlEvent(final PWALControlEvent e) {
        super(e.innermsg);
        this.innerexp = e.innerexp;
        this.innermsg = e.innermsg;
    }

    /**
     * Generates a string representation of the object.
     *
     * @return        the <code>String</code> representation
     *
    * @since        PWAL 0.1.0
     */
    public final String toString() {
        String ret = "PWALDrivercontrolEvent ";
        if (this.innerexp != null) {
            ret += "[" + this.innerexp.toString() + "]";
        }
        
        if (this.innermsg != null) {
            ret += "[" + this.innermsg + "]";
        }
        
        if (this.type != null) {
            ret += "(" + this.type + ")";
        }
        return ret;
    }

    /**
     * Sets the internal exception.
     *
     * @param e -    the internal <code>Exception</code> to set
    *
    * @since        PWAL 0.1.0
     */
    public final void setInnerException(final Exception e) {
        this.innerexp = e;
    }

    /**
     * Sets the internal message.
     *
     * @param msg -    the message <code>String</code> to set
    *
    * @since        PWAL 0.1.0
     */
    public final void setInnerMessage(final String msg) {
        this.innermsg = msg;
    }

    /**
     * Sets the type of event control.
     *
     * @param dbug - the <code>ControlEventType</code> to set
    *
    * @since        PWAL 0.1.0
     */
    public final void setType(final ControlEventType dbug) {
        this.type = dbug;
    }

}
