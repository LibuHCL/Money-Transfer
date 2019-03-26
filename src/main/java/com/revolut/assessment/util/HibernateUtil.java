package com.revolut.assessment.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

  private static SessionFactory sessionFactory;

  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
      Configuration configuration = new Configuration().configure();
      ServiceRegistry serviceRegistry
          = new StandardServiceRegistryBuilder()
          .configure("hibernate.cfg.xml").build();
      Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
      sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    return sessionFactory;
  }

  public static Transaction getTransaction() throws Exception {
    Session s = getSessionFactory().getCurrentSession();
    Transaction transaction = s.beginTransaction();
    transaction.setTimeout(50);
    return transaction;
  }

  public static void shutdown() {
    getSessionFactory().close();
  }
}


