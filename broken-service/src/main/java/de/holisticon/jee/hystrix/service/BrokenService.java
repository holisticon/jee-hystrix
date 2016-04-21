package de.holisticon.jee.hystrix.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@Stateless
@LocalBean
@WebService(targetNamespace = "http://brokenservice.holisticon.de/stock/2016/", portName = "StockPort", serviceName = "StockService", name = "Stock")
public class BrokenService {

  @WebMethod(action = "getStock", operationName = "getStock")
  public @WebResult(name = "stockResponse") StockResponse getStock(
      final @WebParam(name = "stockRequest") StockRequest request) {

    final StockResponse response = new StockResponse();

    if (request.getName().equals("FB")) {
      response.setName(request.getName());
      response.setCurrency("USD");
      Double randomValue = Math.random() * 150l;
      response.setValue(randomValue.longValue());

    }

    try {
      Thread.sleep((long) (1000 + 1000 * 20 * Math.random()));
    } catch (InterruptedException e) {
    }

    return response;
  }

}
