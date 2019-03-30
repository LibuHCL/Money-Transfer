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

import com.revolut.assessment.dao.AcoountDao;
import com.revolut.assessment.model.Account;
import com.revolut.assessment.model.User;
import com.revolut.assessment.util.HibernateUtil;

public class AccountDaoImpl implements AcoountDao {

  private static Logger LOG = LoggerFactory.getLogger(AccountDaoImpl.class);

  @Override
  public void create(final String number, final Long balance, final String currency, final boolean status, final Long limit) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      User user = new User();
      user.setEmail("test");
      user.setFirstName("Libu");
      user.setLastName("Mathew");
      Account account = new Account();
      account.setNumber(number);
      account.setBalance(balance);
      account.setCurrency(currency);
      account.setStatus(status);
      account.setLimit(limit);
      account.setUser(user);
      session.save(account);
      transaction.commit();
    } catch (HibernateException hbe) {
      transaction.rollback();
      hbe.printStackTrace();
    }
  }

  @Override
  public Account findAccountByNumber(final String number) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      CriteriaBuilder builder = session.getCriteriaBuilder();
      CriteriaQuery<Account> accountCriteriaQuery = builder.createQuery(Account.class);
      Root<Account> root = accountCriteriaQuery.from(Account.class);
      accountCriteriaQuery.select(root).where(builder.equal(root.get("number"), number));
      Query<Account> q = session.createQuery(accountCriteriaQuery);
      Account account = q.getSingleResult();
      transaction.commit();
      return account;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void updateAccountBalance(final Account account1, final Account account2) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      String hql = "UPDATE Account set balance = :balance " + "WHERE number = :number";
      Query updateAccountBalanceQuery = session.createQuery(hql);
      updateAccountBalanceQuery.setParameter("balance", account1.getBalance());
      updateAccountBalanceQuery.setParameter("number", account1.getNumber());
      updateAccountBalanceQuery.executeUpdate();
      updateAccountBalanceQuery.setParameter("balance", account2.getBalance());
      updateAccountBalanceQuery.setParameter("number", account2.getNumber());
      updateAccountBalanceQuery.executeUpdate();
      transaction.commit();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }
}
