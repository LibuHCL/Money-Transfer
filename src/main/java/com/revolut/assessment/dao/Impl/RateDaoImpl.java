package com.revolut.assessment.dao.Impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revolut.assessment.dao.RateDao;
import com.revolut.assessment.model.Rate;
import com.revolut.assessment.util.HibernateUtil;

public class RateDaoImpl implements RateDao {

  private static Logger LOG = LoggerFactory.getLogger(RateDaoImpl.class);

  @Override
  public void create(final long id, final String currencyConversion, final String currencyRate) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      Rate rate = new Rate();
      rate.setId(id);
      rate.setCurrencyConversion(currencyConversion);
      rate.setCurrencyRate(currencyRate);
      session.save(rate);
      transaction.commit();
    } catch (HibernateException hbe) {
      transaction.rollback();
      hbe.printStackTrace();
    }
  }

  @Override
  public List<Rate> getAllCurrencyRates() {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      CriteriaBuilder builder = session.getCriteriaBuilder();
      CriteriaQuery<Rate> overallCurrencyRateQuery = builder.createQuery(Rate.class);
      Root<Rate> root = overallCurrencyRateQuery.from(Rate.class);
      overallCurrencyRateQuery.select(root);
      Query<Rate> q = session.createQuery(overallCurrencyRateQuery);
      List<Rate> currencyRates = q.getResultList();
      transaction.commit();
      return currencyRates;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
