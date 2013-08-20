/**
 *
 */
package com.athieme;

import org.junit.Before;
import org.junit.Test;

import javax.xml.ws.Endpoint;

/**
 * @author athieme
 */
public class MockWebServiceTest {

    @Before
    public void before() {
        Endpoint.publish("http://localhost:9999/webservice/mock", new MockWebService());
    }

    @Test
    public void echo() {

    }

    @Test
    public void reverse() {

    }
}
