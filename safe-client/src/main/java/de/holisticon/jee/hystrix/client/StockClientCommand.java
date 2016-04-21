package de.holisticon.jee.hystrix.client;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

import rx.Observable;

/**
 * Hystrix command encapsulating the WS request.
 * 
 * @author Simon Zambrovski
 */
public class StockClientCommand extends HystrixCommand<String> {

  // tolerate 2 second duration.
  private static final HystrixCommand.Setter CACHED_SETTER = Setter
      .withGroupKey(HystrixCommandGroupKey.Factory.asKey("BrokenService"))
      .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(17000));

  private final StockService stock;
  private String quote;

  public StockClientCommand(final String quote) {
    super(CACHED_SETTER);
    this.quote = quote;
    stock = new StockService();
  }

  @Override
  protected String run() throws Exception {
    
    final StockRequest stockRequest = new StockRequest();
    stockRequest.setName(quote);
    // execution
    final StockResponse response = stock.getStockPort().getStock(stockRequest);
    return response.toString();
  }
  
}
