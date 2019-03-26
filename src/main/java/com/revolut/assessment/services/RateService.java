package com.revolut.assessment.services;

import java.math.BigDecimal;

public interface RateService {

  void create(long id, String currencyConversion, String currencyRate);

  void synchronize();

  BigDecimal converter(String from, String to, BigDecimal amount);

}
