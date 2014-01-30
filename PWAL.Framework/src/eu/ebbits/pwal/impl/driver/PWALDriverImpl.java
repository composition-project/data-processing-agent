package eu.ebbits.pwal.impl.driver;

import org.apache.log4j.Logger;
import org.osgi.service.component.ComponentContext;

import eu.ebbits.pwal.api.PWALDriverPort;
import eu.ebbits.pwal.api.driver.PWALDriver;
import eu.ebbits.pwal.api.driver.PWALEventsDelegate;
import eu.ebbits.pwal.api.driver.PWALServicesDelegate;
import eu.ebbits.pwal.api.driver.PWALVariablesDelegate;

/**
 * This class is imagined as part of the new ebbits DDK: it will thus undergo significant modifications in the ebbits iterative process.  it will be thus documented during the final release after all needed adapters are available. Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 * @author        ISMB
 * @version        %I%, %G%
 * @see         eu.ebbits.pwal.impl.driver.PWALDriverImpl
 * @since        PWAL 0.1.0
 */
public abstract class PWALDriverImpl extends PWALDelegateImpl implements PWALDriver {

    /** A <code>org.apache.log4j.Logger</code> instance */
    private Logger log = Logger.getLogger(PWALDriver.class.getName());

    // TODO Can we get this from the info put in the plug-in info (MANIFEST.MF) somehow
    private long driverID;
    private String driverName;
    private String driverVersion;

    private PWALEventsDelegate eventsDelegate;
    private PWALServicesDelegate servicesDelegate;
    private PWALVariablesDelegate variablesDelegate;

    /**
     *  OSGI activation method for PWAL service
     *  
     *  @param    context            A <code>org.osgi.service.component.ComponentContext</code> object,
     *                          with the current OSGI context
     */
    protected final void activate(ComponentContext context) {
        log.debug("Starting " + context.getBundleContext().getBundle().getSymbolicName() + "...");
        
        this.driverID = context.getBundleContext().getBundle().getBundleId();
        this.driverName = context.getBundleContext().getBundle().getSymbolicName();
        this.driverVersion = context.getBundleContext().getBundle().getVersion().toString();

        PWALDriverPort pwal = (PWALDriverPort) context.locateService(PWALDriverPort.class.getSimpleName());
        pwal.registerDriver(this);
        
        this.init(context);
        
        this.eventsDelegate = initEventsDelegate();
        this.eventsDelegate.init(context);
        this.eventsDelegate.start();
        
        this.servicesDelegate = initServicesDelegate();
        this.servicesDelegate.init(context);
        this.servicesDelegate.start();
        
        this.variablesDelegate = initVariablesDelegate();
        this.variablesDelegate.init(context);
        this.variablesDelegate.start();
        
        this.start();
        
        log.debug("Started " + context.getBundleContext().getBundle().getSymbolicName());
    }

    /**
     *  OSGI de-activation method for PWAL service
     *  
     *  @param    context            A <code>org.osgi.service.component.ComponentContext</code> object,
     *                          with the current OSGI context
     */
    protected final void deactivate(ComponentContext context) {
        log.debug("Stopping " + context.getBundleContext().getBundle().getSymbolicName() + "...");
        
        this.stop();
        
        PWALDriverPort pwal = (PWALDriverPort) context.locateService(PWALDriverPort.class.getSimpleName());
        pwal.unregisterDriver(this);
        
        log.debug("Stopped " + context.getBundleContext().getBundle().getSymbolicName());
    }

    protected abstract PWALEventsDelegate initEventsDelegate();
    protected abstract PWALServicesDelegate initServicesDelegate();
    protected abstract PWALVariablesDelegate initVariablesDelegate();
    
    @Override
    public String getDriverID() {
        return Long.toString(this.driverID);
    }
    
    @Override
    public String getDriverName() {
        return this.driverName;
    }
    
    /**
     * Sets the drive name.
     * 
     * @param name - name to assign to the driver
     * 
     */
    public void setDriverName(String name) {
        this.driverName = name;
    }
    
    @Override
    public String getDriverVersion() {
        return this.driverVersion;
    }
    
    /**
     * Sets the driver version.
     * 
     * @param version - version to assign to the driver
     */
    public void setDriverVersion(String version) {
        this.driverVersion = version;
    }

    @Override
    public PWALEventsDelegate getEventsDelegate() {
        return this.eventsDelegate;
    }
    
    @Override
    public PWALServicesDelegate getServicesDelegate() {
        return this.servicesDelegate;
    }
    
    @Override
    public PWALVariablesDelegate getVariablesDelegate() {
        return this.variablesDelegate;
    }
}
