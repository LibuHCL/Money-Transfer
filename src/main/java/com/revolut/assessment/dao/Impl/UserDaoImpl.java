package com.revolut.assessment.dao.Impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revolut.assessment.dao.UserDao;
import com.revolut.assessment.model.User;
import com.revolut.assessment.util.HibernateUtil;

public class UserDaoImpl implements UserDao {

  private static Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);

  @Override
  public void createUser(final Long id, final String email, final String firstName, final String lastName) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      User user = new User();
      user.setId(id);
      user.setEmail(email);
      user.setFirstName(firstName);
      user.setLastName(lastName);
      session.save(user);
      transaction.commit();
    } catch (HibernateException hbe) {
      transaction.rollback();
      hbe.printStackTrace();
    }
  }

  @Override
  public User findById(final Long id) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      CriteriaBuilder builder = session.getCriteriaBuilder();
      CriteriaQuery<User> userCriteriaQuery = builder.createQuery(User.class);
      Root<User> root = userCriteriaQuery.from(User.class);
      userCriteriaQuery.select(root).where(builder.equal(root.get("id"), id));
      Query<User> q = session.createQuery(userCriteriaQuery);
      User user = q.getSingleResult();
      transaction.commit();
      return user;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
