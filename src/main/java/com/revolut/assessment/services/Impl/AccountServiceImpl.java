package com.revolut.assessment.services.Impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revolut.assessment.dao.AcoountDao;
import com.revolut.assessment.model.Account;
import com.revolut.assessment.services.AccountService;
import com.revolut.assessment.services.RateService;

public class AccountServiceImpl implements AccountService {

  private static Logger LOG = LoggerFactory.getLogger(AccountServiceImpl.class);

  private AcoountDao acoountDao;
  private RateService rateService;

  public AccountServiceImpl(AcoountDao acoountDao) {
    this.acoountDao=acoountDao;
  }

  public AccountServiceImpl(final AcoountDao acoountDao, RateService rateService) {
    this.acoountDao = acoountDao;
    this.rateService = rateService;
  }

  @Override
  public void create(final String number, final Long balance, final String currency, final boolean status, final Long limit) {
    acoountDao.create(number, balance, currency, status, limit);
  }

  @Override
  public Account find(final String number) {
    return acoountDao.findAccountByNumber(number);
  }

  @Override
  public void transfer(final String from, final String to, final String currency, final Long amount) {
    Account fromAccount = acoountDao.findAccountByNumber(from);
    if (fromAccount.isActive()) {
      if (fromAccount.hasLimitedAmount(amount)) {
        Account toAccount = acoountDao.findAccountByNumber(to);
        if (toAccount.isActive()) {
          BigDecimal fromAmount = rateService.converter(currency, fromAccount.getCurrency(), amount);
          BigDecimal toAmount = rateService.converter(currency, toAccount.getCurrency(), amount);

        }
      }

    }

  }
}
