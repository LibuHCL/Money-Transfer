package com.revolut.assessment.services;

import com.revolut.assessment.model.Account;

public interface AccountService {

  void create(String number, Long balance, String currency, boolean status, Long limit);

  Account find(String number);

  void transfer(String from, String to, String currency, Long amount);

}
