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
		metaDataSources.addAnnotatedClass(Item.class);
		metaDataSources.addAnnotatedClass(Category.class);
		MetadataBuilder metaBuilder = metaDataSources.getMetadataBuilder();
		Metadata metaData = metaBuilder.build();
		return metaData.buildSessionFactory();
	}

	private static void addCategory(Session session) {
		session.getTransaction().begin();
		Category category = new Category();
		category.setCategoryName("Bikes");
		session.persist(category);
		session.getTransaction().commit();
	}
	
	private static void addItem(Session session) {
		session.getTransaction().begin();
		Item item = new Item();
		item.setItemName("Yamaha Rx 100");
		session.persist(item);
		session.getTransaction().commit();
	}
	
	private static void linkItemCategory(Session session) {
		session.getTransaction().begin();
		Category category = session.find(Category.class, 1L);
		Item item = session.find(Item.class,1L);
		category.getItems().add(item);
		session.persist(item);
		session.getTransaction().commit();
	}
	
	public static void main(String[] args) {
		SessionFactory sessionFactory = loadConfiguration();
		addCategory(sessionFactory.getCurrentSession());
		addItem(sessionFactory.getCurrentSession());
		linkItemCategory(sessionFactory.getCurrentSession());
	}
}
