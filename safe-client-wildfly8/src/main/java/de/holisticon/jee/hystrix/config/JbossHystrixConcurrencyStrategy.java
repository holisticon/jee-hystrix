package de.holisticon.jee.hystrix.config;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutors;
import javax.enterprise.concurrent.ManagedThreadFactory;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.properties.HystrixProperty;

/**
 * Hystrix concurrency strategy working with Wildfly thread factory.
 * 
 * @author Simon Zambrovski
 */
public class JbossHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {

  @Resource
  private ManagedThreadFactory mtf;

  @Inject
  private Logger logger;

  @Override
  public <T> Callable<T> wrapCallable(Callable<T> callable) {
    return super.wrapCallable(ManagedExecutors.managedTask(callable, null));
  }

  public ThreadPoolExecutor getThreadPool(final HystrixThreadPoolKey threadPoolKey,
      HystrixProperty<Integer> corePoolSize, HystrixProperty<Integer> maximumPoolSize,
      HystrixProperty<Integer> keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
    // create executor
    ThreadPoolExecutor tpe = new ThreadPoolExecutor(corePoolSize.get(), maximumPoolSize.get(), keepAliveTime.get(),
        unit, workQueue, mtf);
    logger.info("Initialized thread pool executor for {}", threadPoolKey.name());
    return tpe;
  }
}
