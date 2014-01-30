package eu.ebbits.pwal.impl.driver;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

import eu.ebbits.pwal.api.driver.PWALEventsDelegate;


/**
 * This package is imagined as part of the new ebbits DDK: it will thus undergo significant modifications in the ebbits iterative process. 
 * it will be thus documented during the final release after all needed adapters are available.
 * 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 *
 * @author     ISMB
 * @version    %I%, %G%
 * @see        eu.ebbits.pwal.impl.driver.PWALDriverImpl
 * @since      PWAL 0.1.0
 */
public abstract class PWALEventsDelegateImpl extends PWALDelegateImpl implements PWALEventsDelegate {

    private Map<String, Event> events;
    private BundleContext context;
    private EventAdmin eventAdmin;
    
    /**
     * Constructor for the Events Delegate
     * 
     * @param driver - driver that uses the delegate
     */
    public PWALEventsDelegateImpl(PWALDriverImpl driver) {
        super(driver);
        this.events = new HashMap<String, Event>();
        context = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
        ServiceReference ref = context.getServiceReference(EventAdmin.class.getName());
        eventAdmin = (EventAdmin) context.getService(ref);
    }

    /**
     * Adds a new event to the list
     * 
     * @param topic        - topic where the event will be published
     * @param event        - description of the event
     * 
     * @since PWAL 0.2.0
     */
    public void registerEvent(String topic, Event event) {
        events.put(topic,event);
    }
    
    @Override
    public Event getPWALEvent(String eventName) {
        return events.get(eventName);
    }
    
    @Override
    public Collection<Event> getPWALEvents(Collection<String> eventsName) {
        if (!eventsName.isEmpty()) {
            Collection<Event> result = new HashSet<Event>();
            for(String eventName : eventsName) {
                result.add(this.getPWALEvent(eventName));
            }
            return result;
        } else {
            return null;
        }
    }
    
    @Override
    public int getPWALEventsCollectionSize() {
        return this.events.size();
    }

    @Override
    public Collection<Event> getPWALEventsCollection() {
        return this.events.values();
    }
    
    @Override
    public synchronized void sendEvent(Event e) {
        eventAdmin.sendEvent(e);
    }
}
