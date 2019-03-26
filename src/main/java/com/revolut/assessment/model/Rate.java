package com.revolut.assessment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(name = "Rate", uniqueConstraints = {
    @UniqueConstraint(columnNames = "id")
})
public class Rate {

  @Column(name = "id")
  private Long id;

  @Column(name = "currencyRate")
  private String currencyRate;

  @Column(name = "currencyConversion")
  private String currencyConversion;

  public Rate() {
  }

  public Rate(final Long id, final String currencyRate, final String currencyConversion) {
    this.id = id;
    this.currencyRate = currencyRate;
    this.currencyConversion = currencyConversion;
  }

  public String getCurrencyRate() {
    return currencyRate;
  }

  public String getCurrencyConversion() {
    return currencyConversion;
  }
}
