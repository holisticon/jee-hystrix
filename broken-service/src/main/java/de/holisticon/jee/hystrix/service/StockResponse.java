package de.holisticon.jee.hystrix.service;

import java.io.Serializable;

public class StockResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private long value;
  private String currency;
  private String name;

  public long getValue() {
    return value;
  }

  public void setValue(long value) {
    this.value = value;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
