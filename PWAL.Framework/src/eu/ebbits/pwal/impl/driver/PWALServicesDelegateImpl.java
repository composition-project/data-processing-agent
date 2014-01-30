package eu.ebbits.pwal.impl.driver;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import eu.ebbits.pwal.api.annotations.PWALServiceAnnotation;
import eu.ebbits.pwal.api.driver.PWALServicesDelegate;
import eu.ebbits.pwal.api.model.PWALService;

/**
 * This class is imagined as part of the new ebbits DDK: it will thus undergo significant modifications in the ebbits iterative process. 
 * it will be thus documented during the final release after all needed adapters are available.
 * 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 *
 * @author        ISMB
 * @version        %I%, %G%
 * @see            eu.ebbits.pwal.impl.driver.PWALDriverImpl
 * @since        PWAL 0.1.0
 */
public abstract class PWALServicesDelegateImpl extends PWALDelegateImpl implements PWALServicesDelegate {
    
    private Map<String, PWALService> methods;

    /**
     * Construction of the Services Delegate.
     * 
     * @param driver - driver that uses the delegate
     * 
     */
    public PWALServicesDelegateImpl(PWALDriverImpl driver) {
        super(driver);
        this.methods = new HashMap<String, PWALService>(); 
        this.registerPWALServices();
    }
    
    /**
     * Registers all the PWAL services using the annotations
     * 
     */
    public final void registerPWALServices() {
        for (Method m : this.getClass().getMethods()) {
            PWALServiceAnnotation a = m.getAnnotation(PWALServiceAnnotation.class);
            if(a != null && a.exposed()) {
                this.methods.put(m.getName(), new PWALService(m));
            }
        }
    }
    
    @Override
    public PWALService getPWALService(String serviceName) {
        return methods.get(serviceName);
    }

    @Override
    public Collection<PWALService> getPWALServices(Collection<String> servicesName) {
        if (!servicesName.isEmpty()) {
            Collection<PWALService> services = new HashSet<PWALService>();
            for(String serviceName : servicesName) {
                services.add(this.getPWALService(serviceName));
            }
            return services;
        } else {
            return null;
        }
    }
    
    @Override
    //@PWALServiceMetaAnnotation
    public int getPWALServicesCollectionSize() {
        return this.methods.size();
    } 
    
    @Override
    //@PWALServiceMetaAnnotation
    public Collection<PWALService> getPWALServicesCollection() {
        return this.methods.values();
    } 
}