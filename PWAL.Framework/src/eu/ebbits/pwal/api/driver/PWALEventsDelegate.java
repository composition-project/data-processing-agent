package eu.ebbits.pwal.api.driver;

import java.util.Collection;

import org.osgi.service.event.Event;


/**
 * Interface for controlling <code>PWALDriver</code>'s 
 * <code>EventsDelegate</code>.
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 *
 * @author     ISMB
 * @version    %I%, %G%
 * @see         PWALDelegate
 * @see         eu.ebbits.pwal.impl.driver.framework.EventsDelegate
 * @since      PWAL 0.1.0
 */
public interface PWALEventsDelegate extends PWALDelegate {
    
    /** 
     * Retrieves the Event, if available, given its name.
     *
     * @param name - a <code>String</code> with the event's name to get
     * 
     * @return        the <code>Event</code> if available,
     *                 <code>null</code> otherwise
     *  
     * @since        PWAL 0.1.0
     */
    Event getPWALEvent(String name);

    /** 
     * Retrieves a collection of <code>Events</code>s, if available, 
     * given their names.
     *
     * @param names    - a <code>String</code> collection with the events names to
     *                 get
     * 
     * @return        the collection of available Events or
     *                 <code>null</code> values where the given name does not exist
     *  
     * @since        PWAL 0.1.0
     */
    Collection<Event> getPWALEvents(Collection<String> names);

    /** 
     * Retrieves the size of the current Events collection.
     *
     * @return        the number of available Events
     *  
     * @since        PWAL 0.1.0
     */
    int getPWALEventsCollectionSize();

    /** 
     * Retrieves the all available Events.
     *
     * @return        the collection of available <code>Event</code>s or
     *                 <code>null</code> if the collection is empty
     *                 <!-- TODO check if the latter is true --> 
     *  
     * @since        PWAL 0.1.0
     */
    Collection<Event> getPWALEventsCollection();

    /** 
     * Browses the physical world and updates the current list of available 
     * <code>Event</code>s, adding new discovered physical world events
     * and removing those not reachable any longer.
     *
     * @since        PWAL 0.1.0
     */
    void updatePWALEventsCollection();


    /**
     * Sends an event using the EventAdmin service provided by OSGi
     * 
     * @param e - event to send
     * 
     * @since        PWAL 0.2.0
     */
    void sendEvent(Event e);
}