package com.revolut.assessment.util;

import com.revolut.assessment.dao.AcoountDao;
import com.revolut.assessment.dao.Impl.AccountDaoImpl;
import com.revolut.assessment.dao.Impl.RateDaoImpl;
import com.revolut.assessment.dao.Impl.UserDaoImpl;
import com.revolut.assessment.dao.RateDao;
import com.revolut.assessment.dao.UserDao;
import com.revolut.assessment.services.AccountService;
import com.revolut.assessment.services.Impl.AccountServiceImpl;
import com.revolut.assessment.services.Impl.RateServiceImpl;
import com.revolut.assessment.services.Impl.UserServiceImpl;
import com.revolut.assessment.services.RateService;
import com.revolut.assessment.services.UserService;

public class ApplicationContextUtil {

  private static final AccountService accountService;
  private static final RateService rateService;
  private static final UserService userService;

  static {
    AcoountDao acoountDao = new AccountDaoImpl();
    RateDao rateDao = new RateDaoImpl();
    UserDao userDao = new UserDaoImpl();

    accountService = new AccountServiceImpl(acoountDao);
    rateService = new RateServiceImpl(rateDao);
    userService = new UserServiceImpl(userDao);
  }

  public static AccountService getAccountService() {
    return accountService;
  }

  public static RateService getRateService() {
    return rateService;
  }

  public static UserService getUserService() {
    return userService;
  }

}
