package eu.ebbits.pwal.impl.driver.llrpreader;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.event.Event;

import eu.ebbits.pwal.impl.driver.PWALEventsDelegateImpl;

/**
 * <code>LLRPReaderDriver</code> events delegate.
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 *
 * @author    ISMB
 * @version    %I%, %G%
 * @since    PWAL 0.2.0
 */
public class LLRPReaderEventsDelegate extends PWALEventsDelegateImpl {

    /**
     * Constructor of the events delegate of the LLRP driver
     * 
     * @param driver - driver that uses the delegate as <codeLLRPReaderDriverImpl<code>
     */
    public LLRPReaderEventsDelegate(LLRPReaderDriverImpl driver) {
        super(driver);
    }

    @Override
    public void init(ComponentContext context) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updatePWALEventsCollection() {
        Dictionary<String, Class<?>> properties = new Hashtable<String, Class<?>>();
        properties.put("report", String.class);
        
        Event reportEvent = new Event("pwal/llrpreader/readreport", properties);
        this.registerEvent("pwal/llrpreader/readreport",reportEvent);
    }
}
