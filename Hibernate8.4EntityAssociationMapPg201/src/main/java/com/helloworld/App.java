package com.helloworld;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
		metaDataSources.addAnnotatedClass(Category.class);
		metaDataSources.addAnnotatedClass(Item.class);
		metaDataSources.addAnnotatedClass(User.class);
		MetadataBuilder metaBuilder = metaDataSources.getMetadataBuilder();
		Metadata metaData = metaBuilder.build();
		return metaData.buildSessionFactory();
	}
	
	private static void addCategory(Session session) {
		session.getTransaction().begin();
		Category someCategory = new Category("Some Category");
		Category otherCategory = new Category("Other Category");
		session.persist(someCategory);
		session.persist(otherCategory);
		session.getTransaction().commit();
	}
	
	private static void addItem(Session session) {
		session.getTransaction().begin();
		Item someItem = new Item("someItem");
		Item otherItem = new Item("Other Item");
		session.persist(someItem);
		session.persist(otherItem);
		session.getTransaction().commit();
	}
	
	private static void addUser(Session session) {
		session.getTransaction().begin();
		User user = new User("Dhawan");
		session.persist(user);
		User user1 = new User("Karthik");
		session.persist(user1);
		session.getTransaction().commit();
	}
	
	private static void linkCategoryItem(Session session) {
		session.getTransaction().begin();
		Item someItem = session.find(Item.class, 1L);
		Item otherItem = session.find(Item.class, 2L);
		User someUser = session.find(User.class, 1L);
		User otherUser = session.find(User.class, 2L);
		Category someCategory = session.find(Category.class, 1L);
		Category otherCategory = session.find(Category.class, 2L);
		someCategory.getItemAddedBy().put(someItem, someUser);
		someCategory.getItemAddedBy().put(someItem, otherUser);
		otherCategory.getItemAddedBy().put(otherItem, otherUser);
		session.getTransaction().commit();
	}

	public static void main(String[] args) {
		 SessionFactory sessionFactory =loadConfiguration();
		 addCategory(sessionFactory.getCurrentSession());
		 addItem(sessionFactory.getCurrentSession());
		 addUser(sessionFactory.getCurrentSession());
		 linkCategoryItem(sessionFactory.getCurrentSession());
	}
}
