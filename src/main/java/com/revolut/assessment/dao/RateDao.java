package com.revolut.assessment.dao;

import com.revolut.assessment.model.Rate;

import java.util.List;

public interface RateDao {

  void create(long id, String currencyConversion, String currencyRate);

  List<Rate> getAllCurrencyRates();
}
