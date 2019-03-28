package com.revolut.assessment.dao;

import com.revolut.assessment.model.Account;

public interface AcoountDao {

  void create(String number, Long balance, String currency, boolean status, Long limit);

  Account findAccountByNumber(String number);

  void updateAccountBalance(Account account1, Account account2);

}
