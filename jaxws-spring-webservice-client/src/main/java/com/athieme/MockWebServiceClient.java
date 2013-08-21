/**
 *
 */
package com.athieme;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

/**
 * @author athieme
 */
@Slf4j
public class MockWebServiceClient {

    public static void main(final String[] args) {
        final MockWebService service = new MockWebServiceService().getPort(MockWebService.class);
        final String reversed = service.reverse("this is some long text");
        log.error("reversed = {}", reversed);
    }
}