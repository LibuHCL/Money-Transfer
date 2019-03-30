package com.revolut.assessment.dao;

import com.revolut.assessment.model.User;

public interface UserDao {

  void createUser(String email, String firstName, String lastName);

  User findById(Long id);

}
