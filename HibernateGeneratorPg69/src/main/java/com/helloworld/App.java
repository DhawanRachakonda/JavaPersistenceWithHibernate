package com.helloworld;

import java.util.List;
import java.util.Set;

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
		metaDataSources.addAnnotatedClass(Message.class);
		MetadataBuilder metaBuilder = metaDataSources.getMetadataBuilder();
		Metadata metaData = metaBuilder.build();
		return metaData.buildSessionFactory();
	}

	@SuppressWarnings("unchecked")
	public static void listMessages() {
		Session session = loadConfiguration().getCurrentSession();
		session.beginTransaction();
		List<Message> messageList = session.createCriteria(Message.class)
				.list();
		for (Message m : messageList) {
			System.out.println(String.format("ID : %d , Text : %s, threechars : %s", m.getId(), m.getText(), m.firstThreetextChars));
		}
	}

	public static void main(String[] args) {
		
		 Session session =loadConfiguration().getCurrentSession();
		 session.getTransaction().begin(); 
		 Message m = new Message("Helwith sequence 1 with Immutable class with final fields and final class");
		 ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		 Validator validator = factory.getValidator();
		 Set<ConstraintViolation<Message>> violations = validator.validate(m);
		 for(ConstraintViolation<Message> message : violations) {
			 System.out.println("Validations failed : "+message.getMessage());
		 }
		 try {
			 session.persist(m);
			 m.getId();
			 session.getTransaction().commit();
		 } catch(ConstraintViolationException e) {
			 
		 }
		 
		 //System.out.println("Hello World!2" );
		 /*for(Constructor<?> c : Message.class.getConstructors()) {
			 System.out.println(c.getParameterCount());
		 }*/
		listMessages();
	}
}
