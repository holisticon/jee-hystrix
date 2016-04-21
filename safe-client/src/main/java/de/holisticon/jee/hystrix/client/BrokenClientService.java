package de.holisticon.jee.hystrix.client;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.slf4j.Logger;

/**
 * Broken client, synchronous service call.
 * 
 * @author Simon Zambrovski
 */
@Stateless
@LocalBean
@WebService(targetNamespace = "http://brokenclient.holisticon.de/stock/2016/", portName = "BrokenStockClientPort", serviceName = "BrokenStockClientService", name = "StockClient")
public class BrokenClientService {

  @Inject
  Logger logger;

  @WebMethod(action = "getStock", operationName = "getStock")
  public @WebResult(name = "stockResponse") String executeStockCall() {

    final long start = System.currentTimeMillis();

    final StockService stock = new StockService();
    final StockRequest stockRequest = new StockRequest();
    stockRequest.setName("FB");
    final StockResponse response = stock.getStockPort().getStock(stockRequest);

    final long end = System.currentTimeMillis();
    logger.info("Duration: {} ms", end - start);

    return response.toString();
  }
}
