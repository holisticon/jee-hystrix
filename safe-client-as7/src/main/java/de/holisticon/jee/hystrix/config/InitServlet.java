package de.holisticon.jee.hystrix.config;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;

import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;

/**
 * Initialization of the hystrix strategy.
 * 
 * @author Simon Zambrovski
 *
 */
@WebServlet(name = "Init Servlet", loadOnStartup = 1)
public class InitServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Inject
  private Logger logger;

  @Inject
  private HystrixConcurrencyStrategy hystrixStrategy;

  @Override
  public void init(final ServletConfig config) throws ServletException {
    super.init(config);
    initializeHystrix();
  }

  private void initializeHystrix() {
    logger.info("Initilizing Hystrix...");
    HystrixPlugins.getInstance().registerConcurrencyStrategy(hystrixStrategy);
    logger.info("Hystrix initialized.");
  }

}
