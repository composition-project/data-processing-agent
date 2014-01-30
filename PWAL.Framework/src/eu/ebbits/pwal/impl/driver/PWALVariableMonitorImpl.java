package eu.ebbits.pwal.impl.driver;

import org.apache.log4j.Logger;

import eu.ebbits.pwal.api.model.PWALVariable;

/**
 * This class is imagined as part of the new ebbits DDK: it will thus undergo significant modifications in the ebbits iterative process. 
 * it will be thus documented during the final release after all needed adapters are available.
 * 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 *
 * @author     ISMB
 * @version    %I%, %G%
 * @see        eu.ebbits.pwal.impl.driver.PWALDriverImpl
 * @since      PWAL 0.2.0
 */
public class PWALVariableMonitorImpl implements Runnable  {
    
    private PWALVariablesDelegateImpl father;
    private PWALVariable variable;
    private boolean isMonitored;
    private Thread thread;
    private long timeout;
    
    /** A <code>org.apache.log4j.Logger</code> instance */
    private Logger log = Logger.getLogger(PWALVariableMonitorImpl.class.getName());
    
    /**
     * Constructor of teh PWALVariableMonitorImpl.
     * 
     * @param father - the <code>PWALVariablesDelegateImpl</code> that holds the monitor
     * @param variable - the <code>PWALVariable</code> monitored
     * 
     */
    public PWALVariableMonitorImpl(PWALVariablesDelegateImpl father, PWALVariable variable) {
        this.father = father;
        this.variable = variable;
    }
    
    /**
     * Retrieves the variable monitored
     * 
     * @return <code>PWALVariable</code> monitored
     * 
     * @since      PWAL 0.2.0
     */
    public PWALVariable getVariable() {
        return variable;
    }
    
    /**
     * Retrieves the priority of this monitor
     * 
     * @return <code>int</code> priority of the monitor
     * 
     * @since      PWAL 0.2.0
     */
    public int getPriority() {
        return this.thread.getPriority();
    }
    
    /**
     * Sets the priority of the monitor
     * 
     * @param priority -    priority to assign to the monitor
     * 
     * @since      PWAL 0.2.0
     */
    public void setPriority(int priority) {
        this.thread.setPriority(priority);
    }
    
    /**
     * Joins the monitor
     * 
     * @param wait    -    time to wait in milliseconds
     * 
     * @throws InterruptedException - if something goes wrong waiting
     * 
     * @since      PWAL 0.2.0
     */
    public void join(long wait) throws InterruptedException {
        this.thread.join(wait);
    }
    
    /**
     * Starts the monitor for the variable
     * 
     * @param samplingPeriod -    Sampling period in ms for the monitor in milliseconds
     * @param priority            -    Priority for this monitor
     * 
     * @since      PWAL 0.2.0
     */
    public void start(long samplingPeriod, int priority) {
        this.timeout = samplingPeriod;
        this.isMonitored = true;
        this.thread = new Thread(this);
        this.thread.setPriority(priority);
        this.thread.start();
    }

    /**
     * Stops the monitor
     * 
     * @since      PWAL 0.2.0
     */
    public void stop() {
        this.isMonitored = false;
    }
    
    @Override
    public void run() {
        try {
            do {
                father.readNow(this.variable);
                Thread.sleep(this.timeout);
            } while (this.isMonitored && (this.timeout > 0));
        } catch (Exception e) {
            log.error("Error monitoring a variable", e);
        }
    }    
}