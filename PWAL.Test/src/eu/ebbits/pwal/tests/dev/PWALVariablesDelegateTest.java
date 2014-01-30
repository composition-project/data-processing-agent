package eu.ebbits.pwal.tests.dev;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

import eu.ebbits.pwal.api.driver.PWALVariablesDelegate;
import eu.ebbits.pwal.api.exceptions.PWALWriteNotPossibleException;
import eu.ebbits.pwal.impl.driver.PWALVariablesDelegateImpl;
import eu.ebbits.pwal.impl.driver.test.TestDriver;

/**
 * Variables delegate tests, used to verify how driver can expose monitored variables.
 * 
 * Within the PWAL, variables are modeled as generic self-described parameters, provided with a type and a value. 
 * Two classes of variables can be specified: available variables (which can be queried on demand) and monitored variables, which are kept up to date by the PWAL.
 * 
 * This test is based on the {@link TestDriver} class.
 *  
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 *
 * @see eu.ebbits.pwal
 * @since PWAL 0.1.0
 */
public class PWALVariablesDelegateTest extends TestCase {
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /*
     * All test in this case assume the use of local_driver
     * */
    TestDriver local_driver = null;
    
    /**
     * To void time- or order-related issues, each case uses its own instance of local_driver.
     * (According to the JUnit workflow, the setUp class is run before each test in the TestCase)
     * */
    @Before
    public void setUp() throws Exception {
        this.local_driver = new TestDriver();
    }

    
    /**
     * To void time- or order-related issues, each case uses its own instance of local_driver, which is cleaned up in this function.
     * (According to the JUnit workflow, the tearDown class is run after each test in the TestCase)
     * */
    @After
    public void tearDown() throws Exception {
        this.local_driver = null;
        System.gc();
    }

    //TODO Maybe we can build as well a reflecive variabledelegate test.
    
    /**
     * This test checks whether variables are exposed correctly.
     * The {@link PWALVariablesDelegateTest} is NOT reflective. This means that it can register/unregister variables by external means.
     *  
     * This test is very similar to the testServicesExposure test in the {@link PWALServiceDelegateTest} suite, though with external variables registration.
     * 
     * This test tries to register, monitor and query user-defined variables in the PWAL.
     *  
     */
    @Test
    public final void testPWALVariablesDelegate() {
        
//        DriverControlInterface ctrl = this.local_driver;
        
        //check that the driver actually overrides the default name and version of the driver
//        Assert.assertEquals("DummyDriver",ctrl.getDriverName());
//        Assert.assertEquals("0.0",ctrl.getDriverVersion());

        //check that the driver is normally stopped
//        Assert.assertFalse(ctrl.isDriverStarted());
        
//        ctrl.startDriver();
//        Assert.assertTrue(ctrl.isDriverStarted());

        PWALVariablesDelegate vars = this.local_driver.getVariablesDelegate();
        PWALVariablesDelegateImpl vars_internal = (PWALVariablesDelegateImpl) this.local_driver.getVariablesDelegate();
        
        Assert.assertTrue(vars_internal.registerPWALVariable("var1", (float) 1.1));
        Assert.assertTrue(vars.isPWALVariableAvailable("var1"));
//        Assert.assertEquals(Float.class, vars.getVariable("var1").getClass());
        Assert.assertEquals((float) 1.1, vars.read("var1").toFloat());
        
        Assert.assertTrue(vars_internal.registerPWALVariable("var2", 2.2));
        Assert.assertTrue(vars_internal.isPWALVariableAvailable("var2"));
//        Assert.assertEquals(Double.class, vars.getVariable("var2").getClass());
        Assert.assertEquals(2.2, vars.read("var2").toDouble());
        
        Assert.assertTrue(vars.startMonitorPWALVariable("var1", 1000, 1));
        Assert.assertTrue(vars.isPWALVariableMonitored("var1"));
        
        Assert.assertTrue(vars.getMonitoredPWALVariablesNames().contains("var1"));
        try {
            Assert.assertTrue(vars.write("var1", (float) 1.0));
        } catch (PWALWriteNotPossibleException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        Assert.assertTrue(vars.stopMonitorPWALVariable("var1"));
        Assert.assertFalse(vars.isPWALVariableMonitored("var1"));
        
        
//        ctrl.stopDriver();
        //this should not be immediate
        //Assert.assertTrue(ctrl.isDriverStarted());

//        try {
//            ctrl.joinStop();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            fail("unexpected exception");
//        }
        
//        Assert.assertFalse(ctrl.isDriverStarted());
        
    }
}
