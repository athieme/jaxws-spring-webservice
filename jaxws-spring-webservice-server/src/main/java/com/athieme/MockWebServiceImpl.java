/**
 *
 */
package com.athieme;

import lombok.extern.slf4j.Slf4j;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name="MockWebService")
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)
@Slf4j
public class MockWebServiceImpl {

    @WebMethod(operationName = "echo")
    public String echo(@WebParam(name = "value") final String value) {
        System.out.println("value = " + value);
        MockWebServiceImpl.log.error("value = {}, returning = {}", value, value);
        return value;
    }

    @WebMethod(operationName = "reverse")
    public String reverse(@WebParam(name = "value") final String value) {
        final String retval = new StringBuilder(value).reverse().toString();
        MockWebServiceImpl.log.error("value = {}, returning = {}", value, retval);
        return retval;
    }
}