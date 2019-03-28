package com.revolut.assessment.services.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revolut.assessment.dao.UserDao;
import com.revolut.assessment.services.UserService;

public class UserServiceImpl implements UserService {

  private static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

  private final UserDao userDao;

  public UserServiceImpl(final UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public void create(final String email, final String firstName, final String lastName) {

  }
}
