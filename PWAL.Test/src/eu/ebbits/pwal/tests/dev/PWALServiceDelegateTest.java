package eu.ebbits.pwal.tests.dev;

import java.lang.reflect.Method;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import eu.ebbits.pwal.api.annotations.PWALServiceAnnotation;
import eu.ebbits.pwal.api.model.PWALService;
import eu.ebbits.pwal.impl.driver.PWALServicesDelegateImpl;
import eu.ebbits.pwal.impl.driver.test.TestDriver;
import eu.ebbits.pwal.impl.driver.test.TestServicesDelegate;


/**
 * Services delegate tests, used to verify how driver can expose services.
 * This test is based on the {@link TestDriver} class.
 *  
 * <p> 
 * Copyright (c) 2010-2013 the ebbits project. All Rights Reserved.
 *
 * @see eu.ebbits.pwal
 * @since PWAL 0.1.0
 */
public class PWALServiceDelegateTest extends TestCase {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /*
     * All test in this case assume the use of local_driver
     * */
    private TestDriver local_driver;
    private PWALServicesDelegateImpl local_delegate;

    /**
     * To void time- or order-related issues, each case uses its own instance of local_driver.
     * (According to the JUnit workflow, the setUp class is run before each test in the TestCase)
     * */
    @Before
    public void setUp() throws Exception {
        this.local_driver = new TestDriver();
        this.local_delegate = (PWALServicesDelegateImpl) this.local_driver.getServicesDelegate();
    }

    /**
     * To void time- or order-related issues, each case uses its own instance of local_driver, which is cleaned up in this function.
     * (According to the JUnit workflow, the tearDown class is run after each test in the TestCase)
     * */
    @After
    public void tearDown() throws Exception {
        this.local_driver = null;
        this.local_delegate = null;
        System.gc();
    }

    /**
     * This test checks whether services are exposed correctly.
     * The {@link TestServicesDelegate} is reflective. This means that it contains a set of 7 well-known functions which are tagged with the {@link PWALServiceAnnotation} to expose them.
     * The reflection-based mechanism of the driver assures they can be discovered and detected outside.
     * 
     * Using the methods of reflective delegates, it is also possible to check, beyond the name of the service, the number and type of the arguments and the return value type.
     */
    @Test
    public final void testServicesExposure() {
        // The DummyDriver exposes 7 methods, with different arguments

        /*
         * synchronized public void MyVoidServiceWithNoArguments() synchronized
         * public int MyIntServiceWithNoArguments() synchronized public int
         * MyIntServiceWithOneArguments(int a) synchronized public int
         * MyIntServiceWithTwoArguments(int a, int b) synchronized public int
         * MyIntServiceWithThreeArguments(int a,int b, int c) synchronized
         * public float MyFloatServiceWithThreeArguments(float a, float b, float
         * c) synchronized public int [] MyServiceWithEvenAnArray(int []
         * argarray)
         */

        // it also have other methods which should not be counted in there

        Assert.assertEquals(7, this.local_delegate.getPWALServicesCollectionSize());

        for (PWALService g : this.local_delegate.getPWALServicesCollection()) {
            
            Method m = g.getMethod();

            if (m.getName().equals("MyVoidServiceWithNoArguments")) {
                Assert.assertEquals(void.class, m.getReturnType());
                Assert.assertEquals(m.getParameterTypes().length,0);
            } else if (m.getName().equals("MyIntServiceWithNoArguments")) {
                Assert.assertEquals(int.class, m.getReturnType());
                Assert.assertEquals(m.getParameterTypes().length,0);
            } else if (m.getName().equals("MyIntServiceWithOneArguments")) {
                Assert.assertEquals(int.class, m.getReturnType());
                Assert.assertEquals(m.getParameterTypes().length,1);
                Assert.assertEquals(m.getParameterTypes()[0],int.class);
            } else if (m.getName().equals("MyIntServiceWithTwoArguments")) {
                Assert.assertEquals(int.class, m.getReturnType());
                Assert.assertEquals(m.getParameterTypes().length,2);
                Assert.assertEquals(m.getParameterTypes()[0],int.class);
                Assert.assertEquals(m.getParameterTypes()[1],int.class);
            } else if (m.getName().equals("MyIntServiceWithThreeArguments")) {
                Assert.assertEquals(int.class, m.getReturnType());
                Assert.assertEquals(m.getParameterTypes().length,3);
                Assert.assertEquals(m.getParameterTypes()[0],int.class);
                Assert.assertEquals(m.getParameterTypes()[1],int.class);
                Assert.assertEquals(m.getParameterTypes()[2],int.class);
            } else if (m.getName().equals("MyFloatServiceWithThreeArguments")) {
                Assert.assertEquals(double.class, m.getReturnType());
                Assert.assertEquals(m.getParameterTypes().length,3);
                Assert.assertEquals(m.getParameterTypes()[0],double.class);
                Assert.assertEquals(m.getParameterTypes()[1],double.class);
                Assert.assertEquals(m.getParameterTypes()[2],double.class);
            } else if (m.getName().equals("MyServiceWithEvenAnArray")) {
                System.out.println(m.getReturnType().toString());
                Assert.assertEquals(int[].class, m.getReturnType());
                Assert.assertEquals(m.getParameterTypes().length,1);
                Assert.assertEquals(m.getParameterTypes()[0],int[].class);
            } else {
                fail("Unexpected method name: " + m.getName());
            }
        }
    }

    /**
     * Based on the services exposed by the previously tested description (i.e. in the testServicesExposure test), it is possible to invoke exposed methods.
     * This test checks that exposed methods are called correctly.
     */
    @Test
    public final void testServicesCalls() {
        
        Assert.assertEquals(0, ((TestServicesDelegate)this.local_delegate).calls_MyVoidServiceWithNoArguments);
        ((TestServicesDelegate)this.local_delegate).MyVoidServiceWithNoArguments();
        Assert.assertEquals(1,((TestServicesDelegate)this.local_delegate).calls_MyVoidServiceWithNoArguments);
        
        
        Assert.assertEquals(0,((TestServicesDelegate)this.local_delegate).calls_MyIntServiceWithNoArguments);
        int res_i = ((TestServicesDelegate)this.local_delegate).MyIntServiceWithNoArguments();
        Assert.assertEquals(17, res_i);
        Assert.assertEquals(1,((TestServicesDelegate)this.local_delegate).calls_MyIntServiceWithNoArguments);
        
        Assert.assertEquals(0, ((TestServicesDelegate)this.local_delegate).calls_MyIntServiceWithOneArguments);
        res_i =((TestServicesDelegate)this.local_delegate).MyIntServiceWithOneArguments(5);
        Assert.assertEquals(5, res_i);
        Assert.assertEquals(1,((TestServicesDelegate)this.local_delegate).calls_MyIntServiceWithOneArguments);
        
        Assert.assertEquals(0, ((TestServicesDelegate)this.local_delegate).calls_MyIntServiceWithTwoArguments);
        res_i =((TestServicesDelegate)this.local_delegate).MyIntServiceWithTwoArguments(6,7);
        Assert.assertEquals(13, res_i);
        Assert.assertEquals(1,((TestServicesDelegate)this.local_delegate).calls_MyIntServiceWithTwoArguments);
        
        Assert.assertEquals(0, ((TestServicesDelegate)this.local_delegate).calls_MyIntServiceWithThreeArguments);
        res_i =((TestServicesDelegate)this.local_delegate).MyIntServiceWithThreeArguments(8,9,10);
        Assert.assertEquals(27, res_i);
        Assert.assertEquals(1,((TestServicesDelegate)this.local_delegate).calls_MyIntServiceWithThreeArguments);
        
        Assert.assertEquals(0, ((TestServicesDelegate)this.local_delegate).calls_MyFloatServiceWithThreeArguments);
        double res_d =((TestServicesDelegate)this.local_delegate).MyFloatServiceWithThreeArguments(1.1, 1.2, 1.3);
        Assert.assertEquals(1.716, res_d,0.001);
        Assert.assertEquals(1,((TestServicesDelegate)this.local_delegate).calls_MyFloatServiceWithThreeArguments);
        
        int[] testarray = new int[10];
        for (int i=0;i<testarray.length;i++) {
            testarray[i]=i;
        }
        
        Assert.assertEquals(0, ((TestServicesDelegate)this.local_delegate).calls_MyServiceWithEvenAnArray);
        int[] res = ((TestServicesDelegate)this.local_delegate).MyServiceWithEvenAnArray(testarray);
        Assert.assertEquals(1,((TestServicesDelegate)this.local_delegate).calls_MyServiceWithEvenAnArray);
        
        for(int i=0;i< res.length;i++) {
            Assert.assertEquals(2*i, res[i]);
        } 
        
    }

}
