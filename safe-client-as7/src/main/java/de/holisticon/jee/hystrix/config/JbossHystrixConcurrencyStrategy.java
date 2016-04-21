package de.holisticon.jee.hystrix.config;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.threads.JBossThreadFactory;
import org.slf4j.Logger;

import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.properties.HystrixProperty;

/**
 * Hystrix concurrency strategy working with JBoss thread factory.
 * 
 * @author Simon Zambrovski
 * @see https://groups.google.com/forum/#!topic/hystrixoss/cS9AchDxA-8
 */
@Named
public class JbossHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {

  @Inject
  private Logger logger;

  public ThreadPoolExecutor getThreadPool(HystrixThreadPoolKey threadPoolKey, HystrixProperty<Integer> corePoolSize,
      HystrixProperty<Integer> maximumPoolSize, HystrixProperty<Integer> keepAliveTime, TimeUnit unit,
      BlockingQueue<Runnable> workQueue) {

    // create thread factory
    final JBossThreadFactory tf = new JBossThreadFactory(new ThreadGroup("Hystrix-Group"), Boolean.FALSE, null,
        "%G - %t", null, null);

    // create executor
    final ThreadPoolExecutor tpe = new ThreadPoolExecutor(corePoolSize.get(), maximumPoolSize.get(),
        keepAliveTime.get(), unit, workQueue, tf);
    logger.info("Initialized thread pool {}.", threadPoolKey.name());
    return tpe;
  }
}
