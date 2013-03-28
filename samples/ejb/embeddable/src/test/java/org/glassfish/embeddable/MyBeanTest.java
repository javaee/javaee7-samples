/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glassfish.embeddable;

import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author arungup
 */
public class MyBeanTest {
    
    public MyBeanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of sayHello method, of class MyBean.
     */
    @org.junit.Test
    public void testSayHello() throws Exception {
        System.out.println("sayHello");
        String name = "Duke";
        try (EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer()) {
            MyBean instance = (MyBean)container.getContext().lookup("java:global/classes/MyBean");
            String expResult = "Hello " + name;
            String result = instance.sayHello(name);
            assertEquals(expResult, result);
        }
    }
}