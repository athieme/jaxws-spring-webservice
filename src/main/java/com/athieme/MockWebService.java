/**
 *
 */
package com.athieme;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService()
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)
public class MockWebService {

    @WebMethod(operationName = "echo")
    public String echo(@WebParam(name = "value") final String value) {
        return value;
    }

    @WebMethod(operationName = "reverse")
    public String reverse(@WebParam(name = "value") final String value) {
        return new StringBuilder(value).reverse().toString();
    }
}