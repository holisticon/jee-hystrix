package de.holisticon.jee.hystrix.config;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class Producers {

  /**
   * Producer for SLF4J Logger.
   * 
   * @param injectionPoint
   *          injection point.
   * @return logger.
   */
  @Produces
  public Logger produceLogger(final InjectionPoint injectionPoint) {
    return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass().getSimpleName());
  }
}
