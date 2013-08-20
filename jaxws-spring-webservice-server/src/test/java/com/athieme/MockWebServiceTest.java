/**
 *
 */
package com.athieme;

import junitx.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.Endpoint;
import java.util.HashMap;
import java.util.Map;

/**
 * @author athieme
 */
public class MockWebServiceTest {

    @Before
    public void before() {
        Endpoint.publish("http://localhost:9999/webservice/mock", new MockWebService());
    }

    @Test
    public void wsdl() {
        // HACK uses RestTemplate until I generate the SOAP client
        final RestTemplate restTemplate = new RestTemplate();
        final Map<String, String> vars = new HashMap<String, String>();
        final String response = restTemplate.getForObject("http://localhost:9999/webservice/mock?wsdl", String.class, vars);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.contains("<message name=\"reverse\">"));
        Assert.assertTrue(response.contains("<message name=\"echo\">"));
    }

    @Test
    public void echo() {

    }

    @Test
    public void reverse() {

    }
}
