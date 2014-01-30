package eu.ebbits.pwal.impl.driver.test;

import org.osgi.service.component.ComponentContext;

import eu.ebbits.pwal.api.annotations.PWALServiceAnnotation;
import eu.ebbits.pwal.api.model.PWALControlEvent;
import eu.ebbits.pwal.impl.driver.PWALServicesDelegateImpl;

/**
 * 
 * This is the service delegate of the DummyDriver. It is reflective i.e. it exposes all the methods tagged with the {@link PWALServiceAnnotation} annotation.
 * It contains a number of methods with different features and tags and it is threadsafe.
 * 
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
public class TestServicesDelegate extends PWALServicesDelegateImpl {

    public TestServicesDelegate(TestDriver driver) {
        super(driver);
    }
    
    //TODO maybe we can modify the PWALService annotation to provide other information about the methods themselved ! e.g. we should check with requirements to decide which annotations are better with this.
    // FIXME for instance we should annotate whether this is mutable or not! At the moment, though, we don't consider mutable drivers
    
    /** Internal variable just to count the calls to the relative method */
    public int calls_MyVoidServiceWithNoArguments=0;

    /** Simple method to be used for tests */
    @PWALServiceAnnotation (exposed = true)
    synchronized public void MyVoidServiceWithNoArguments() {
        calls_MyVoidServiceWithNoArguments++;
    }

    /** Internal variable just to count the calls to the relative method */
    public int calls_MyIntServiceWithNoArguments=0;
    
    /** Simple method to be used for tests */
    @PWALServiceAnnotation (exposed = true)
    synchronized public int MyIntServiceWithNoArguments() {
        calls_MyIntServiceWithNoArguments++;
        return 17;
    }

    /** Internal variable just to count the calls to the relative method */
    public int calls_MyIntServiceWithOneArguments=0;
    
    /** Simple method to be used for tests */
    @PWALServiceAnnotation (exposed = true)
    synchronized public int MyIntServiceWithOneArguments(int a) {
        calls_MyIntServiceWithOneArguments++;
        return a;
    }
    
    /** Internal variable just to count the calls to the relative method */
    public int calls_MyIntServiceWithTwoArguments=0;

    /** Simple method to be used for tests */
    @PWALServiceAnnotation (exposed = true)
    synchronized public int MyIntServiceWithTwoArguments(int a, int b) {
        calls_MyIntServiceWithTwoArguments++;
        return a + b;
    }
    
    /** Internal variable just to count the calls to the relative method */
    public int calls_MyIntServiceWithThreeArguments=0;

    /** Simple method to be used for tests */
    @PWALServiceAnnotation (exposed = true)
    synchronized public int MyIntServiceWithThreeArguments(int a,int b, int c) {
        calls_MyIntServiceWithThreeArguments++;
        return a + b + c;
    }
    
    /** Internal variable just to count the calls to the relative method */
    public int calls_MyFloatServiceWithThreeArguments=0;

    
    /** Simple method to be used for tests */
    @PWALServiceAnnotation (exposed = true)
    synchronized public double MyFloatServiceWithThreeArguments(double d, double e, double f) {
        calls_MyFloatServiceWithThreeArguments++;
        return d * e * f;
    }
    
    /** Internal variable just to count the calls to the relative method */
    public int calls_MyServiceWithEvenAnArray=0;

    /** Simple method to be used for tests */
    @PWALServiceAnnotation (exposed = true)
    synchronized public int [] MyServiceWithEvenAnArray(int [] argarray) {
        calls_MyServiceWithEvenAnArray++;
        int [] ret = new int[argarray.length]; 
        
        int j=0;
        for (int v : argarray) {
            ret[j++] = 2*v;
        }
        return ret;
    }
    
    /** Internal variable just to count the calls to the relative method */
    public int calls_ThisMethodShallNotPass=0;

    /** Simple method to be used for tests: it should not be exposed externally */
    @PWALServiceAnnotation (exposed = false)
    synchronized public int ThisMethodShallNotPass() {
        calls_ThisMethodShallNotPass++;
        return -1;
        
    }

    /** Internal variable just to count the calls to the relative method */
    public int calls_ThisMethodShallNotPassNeither=0;

    /** Simple method to be used for tests: it should not be exposed externally */
    synchronized public int ThisMethodShallNotPassNeither() {
        calls_ThisMethodShallNotPassNeither++;
        return -1;
        
    }

    @Override
    public void updatePWALServicesCollection() {
        //TODO implement updatePWALServicesCollection()
    }
    
    /**
     * Method used to generate debug events
     */
    public void my_gen_debug() {
        this.signalDebugEvent(new PWALControlEvent(PWALControlEvent.ControlEventType.DEFAULT_DEBUG));
    }

    /**
     * Method used to generate info events
     */
    public void my_gen_info() {
        this.signalInfoEvent(new PWALControlEvent(PWALControlEvent.ControlEventType.DEFAULT_INFO));
        
    }

    /**
     * Method used to generate warnig events
     */
    public void my_gen_warning() {
        this.signalWarningEvent(new PWALControlEvent(PWALControlEvent.ControlEventType.DEFAULT_WARNING));
    }

    /**
     * Method used to generate error events
     */
    public void my_gen_error() {
        this.signalErrorEvent(new PWALControlEvent(PWALControlEvent.ControlEventType.DEFAULT_ERROR));
        
    }
    
    /**
     * Method used to generate critical events
     */
    public void my_gen_critical() {
        this.signalCriticalEvent(new PWALControlEvent(PWALControlEvent.ControlEventType.DEFAULT_CRITICAL));
        
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void init(ComponentContext context) {
        // TODO Auto-generated method stub
    }
}
