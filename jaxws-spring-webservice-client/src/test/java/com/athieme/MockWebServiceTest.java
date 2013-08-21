/**
 *
 */
package com.athieme;

import junitx.framework.Assert;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author athieme
 */
@Slf4j
public class MockWebServiceTest {

    @Before
    public void before() throws IOException {
        this.endpoint = Endpoint.publish("http://localhost:9999/webservice/mock", new MockWebServiceImpl());
    }

    @After
    public void after() {
        this.endpoint.stop();
    }

    @Test
    public void wsdl() {
        // HACK uses RestTemplate until I generate the SOAP client
        final RestTemplate restTemplate = new RestTemplate();
        final Map<String, String> vars = new HashMap<String, String>();
        final String response = restTemplate.getForObject("http://localhost:9999/webservice/mock?wsdl", String.class, vars);
        log.error("response = {}", response);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.contains("<message name=\"reverse\">"));
        Assert.assertTrue(response.contains("<message name=\"echo\">"));
    }

    @Test
    public void echo() throws MalformedURLException {
        final MockWebService port = getMockWebService();
        Assert.assertEquals("this is some text", port.echo("this is some text"));
    }

    @Test
    public void reverse() throws MalformedURLException {
        final MockWebService port = getMockWebService();
        Assert.assertEquals("txet emos si siht", port.reverse("this is some text"));
    }

    private MockWebService getMockWebService() throws MalformedURLException {
        final URL url = new URL(String.format("http://localhost:%s/webservice/mock", 9999));
        final QName qname = new QName("http://athieme.com/", "MockWebServiceImplService");
        final Service service = Service.create(url, qname);
        return service.getPort(MockWebService.class);
    }

    private Endpoint endpoint;
}
