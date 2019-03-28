package com.revolut.assessment.model;

import com.revolut.assessment.dao.AcoountDao;

public class Transfer {

  private final AcoountDao acoountDao;
  private final Account fromAccount;
  private final Account toAccount;
  private final Long fromAmount;
  private final Long toAmount;

  public Transfer(final AcoountDao acoountDao, final Account fromAccount, final Account toAccount, final Long fromAmount, final Long toAmount) {
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
