package de.holisticon.jee.hystrix.client;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.slf4j.Logger;

/**
 * Hystrix client service.
 * 
 * @author Simon Zambrovski
 */
@Stateless
@LocalBean
@WebService(targetNamespace = "http://safeclient.holisticon.de/stock/2016/", portName = "SafeStockClientPort", serviceName = "SafeStockClientService", name = "SafeStockClient")
public class SafeClientService {

  @Inject
  Logger logger;

  @WebMethod(action = "getStock", operationName = "getStock")
  public @WebResult(name = "stockResponse") String executeStockCall() {
    final long start = System.currentTimeMillis();
    final StockClientCommand command = new StockClientCommand("FB");
    // thread boundary
    final String result = command.execute();
    // thread boundary
    final long end = System.currentTimeMillis();
    logger.info("Duration: {} ms", end - start);
    return result;
  }
}
