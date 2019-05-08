package com.helloworld;

import java.lang.reflect.Constructor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.TransactionManager;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;


public class App {
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
		metaDataSources.addAnnotatedClass(BillingDetails.class);
		metaDataSources.addAnnotatedClass(CreditCard.class);
		metaDataSources.addAnnotatedClass(BankAccount.class);
		MetadataBuilder metaBuilder = metaDataSources.getMetadataBuilder();
		Metadata metaData = metaBuilder.build();
		return metaData.buildSessionFactory();
	}

	private static void listBills(Session session) {
		session.getTransaction().begin();
		CriteriaQuery<BillingDetails> billsQuery = session.getCriteriaBuilder().createQuery(BillingDetails.class);
		billsQuery.from(BillingDetails.class);
		List<BillingDetails> billList = session.createQuery(billsQuery).getResultList();
		for(BillingDetails bill : billList) {
			if(bill instanceof CreditCard){
				System.out.println("Credit Card : "+((CreditCard) bill).getCardNumber());
			}
			if(bill instanceof BankAccount){
				System.out.println("Bank Account : "+((BankAccount) bill).getSwift());
			}
		}
	}

	public static void main(String[] args) {
		 Session session =loadConfiguration().getCurrentSession();
		 /*session.getTransaction().begin();
		 CreditCard c = new CreditCard("Sai","1124358855", "07", "2020");
		 BankAccount b = new BankAccount("Ravi","1ss777554789", "SBI", "574sdad");
		 session.save(c);
		 session.save(b);
		 session.getTransaction().commit();*/
		 listBills(session);
	}
}
