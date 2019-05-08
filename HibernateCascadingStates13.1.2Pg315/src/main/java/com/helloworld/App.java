package com.helloworld;


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
		metaDataSources.addAnnotatedClass(Bid.class);
		MetadataBuilder metaBuilder = metaDataSources.getMetadataBuilder();
		Metadata metaData = metaBuilder.build();
		return metaData.buildSessionFactory();
	}

	private static void detachItem(Session session) {
		session.getTransaction().begin();
		Item item = session.find(Item.class, 1L);
		System.out.println("Bid size : "+item.getBids().size());
		session.detach(item);
		item.setName("Yamaha");
		Bid newBid = new Bid(84_000);
		newBid.setItem(item);
		item.getBids().add(newBid);
		session.merge(item);
		session.getTransaction().commit();
	}
	
	private static void addBidToItem(Session session) {
		session.getTransaction().begin();
		Item item = session.find(Item.class, 1L);
		Bid newBid = new Bid(70_000);
		newBid.setItem(item);
		item.getBids().add(newBid);
		session.persist(item);
		session.getTransaction().commit();
	}

	public static void main(String[] args) {
		 SessionFactory sessionFactory =loadConfiguration();
		 /*Session session = sessionFactory.getCurrentSession();
		 session.getTransaction().begin();
		 Item someItem = new Item("Yamaha Rx 100");
		 Bid someBid = new Bid(50000);
		 someBid.setItem(someItem);
		 someItem.getBids().add(someBid);
		 Bid someBid1 = new Bid(60000);
		 someBid1.setItem(someItem);
		 someItem.getBids().add(someBid1);
		 session.persist(someItem);
		 session.getTransaction().commit();
		 addBidToItem(sessionFactory.getCurrentSession());*/
		 detachItem(sessionFactory.getCurrentSession());
	}
}
