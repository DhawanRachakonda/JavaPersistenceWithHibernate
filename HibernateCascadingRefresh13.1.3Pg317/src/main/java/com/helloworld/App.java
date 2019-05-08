package com.helloworld;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class App 
{
	static SessionFactory loadConfiguration() {
		StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
		serviceRegistryBuilder.applySetting("hibernate.connection.url", "jdbc:mysql://localhost/helloworldjpa");
		serviceRegistryBuilder.applySetting("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		serviceRegistryBuilder.applySetting("hibernate.connection.username", "root");
		serviceRegistryBuilder.applySetting("hibernate.connection.password", "root");
		serviceRegistryBuilder.applySetting("hibernate.archive.autodetection", "class");
		serviceRegistryBuilder.applySetting("hibernate.show_sql", "true");
		serviceRegistryBuilder.applySetting("hibernate.format_sql", "true");
		serviceRegistryBuilder.applySetting("hibernate.hbm2ddl.auto", "update");
		serviceRegistryBuilder.applySetting("hibernate.current_session_context_class", "thread");
		ServiceRegistry serviceReistry = serviceRegistryBuilder.build();
		MetadataSources metaDataSources = new MetadataSources(serviceReistry);
		metaDataSources.addAnnotatedClass(User.class);
		metaDataSources.addAnnotatedClass(BillingDetails.class);
		MetadataBuilder metaBuilder = metaDataSources.getMetadataBuilder();
		Metadata metaData = metaBuilder.build();
		return metaData.buildSessionFactory();
	}
	
	private static void refreshUser(Session session) {
		session.getTransaction().begin();
		User user = session.get(User.class, 1L);
		user.getBills().size();
		session.refresh(user);
		session.getTransaction().commit();
	}
	
	private static void addUerAndBill(Session session) {
		session.getTransaction().begin();
		User user = new User();
		BillingDetails billingDetails1 = new BillingDetails();
		billingDetails1.setAmount(new Double(4000));
		//billingDetails1.setUser(user);
		user.getBills().add(billingDetails1);
		BillingDetails billingDetails2 = new BillingDetails();
		//billingDetails2.setUser(user);
		billingDetails2.setAmount(new Double(5000));
		user.getBills().add(billingDetails2);
		session.persist(user);
		session.getTransaction().commit();
	}
	
    public static void main( String[] args )
    {
    	SessionFactory sessionFactory =loadConfiguration();
    	//addUerAndBill(sessionFactory.getCurrentSession());
    	refreshUser(sessionFactory.getCurrentSession());
    }
}
