package com.revolut.assessment.services;

import java.math.BigDecimal;

import com.revolut.assessment.model.Account;

public interface AccountService {

  void create(String number, BigDecimal balance, String currency, boolean status, BigDecimal limit);

  Account find(String number);

  void transfer(String from, String to, String currency, BigDecimal amount);

}
