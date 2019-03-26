package com.revolut.assessment.model;

import java.math.BigDecimal;

import com.revolut.assessment.dao.AcoountDao;

public class Transfer {

  private final AcoountDao acoountDao;
  private final Account fromAccount;
  private final Account toAccount;
  private final BigDecimal fromAmount;
  private final BigDecimal toAmount;

  public Transfer(final AcoountDao acoountDao, final Account fromAccount, final Account toAccount, final BigDecimal fromAmount, final BigDecimal toAmount) {
    this.acoountDao = acoountDao;
    this.fromAccount = fromAccount;
    this.toAccount = toAccount;
    this.fromAmount = fromAmount;
    this.toAmount = toAmount;
  }

  public void send() {
    if (fromAccount.greater(toAccount)) {
      synchronized (fromAccount) {
        synchronized (toAccount) {
          transfer();
        }
      }
    } else {
      synchronized (toAccount) {
        synchronized (fromAccount) {
          transfer();
        }
      }
    }
  }

  private void transfer() {
    fromAccount.checkInsufficientBalance(fromAmount);
    fromAccount.debit(fromAmount);
    toAccount.credit(toAmount);
    acoountDao.updateAccountBalance(fromAccount, toAccount);
  }
}
