package com.revolut.assessment.dao;

import java.math.BigDecimal;

import com.revolut.assessment.model.Account;

public interface AcoountDao {

  void create(String number, BigDecimal balance, String currency, boolean status, BigDecimal limit);

  Account findAccountByNumber(String number);

  void updateAccountBalance(Account account1, Account account2);

}
