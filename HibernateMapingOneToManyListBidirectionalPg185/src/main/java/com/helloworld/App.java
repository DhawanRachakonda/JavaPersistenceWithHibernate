package com.helloworld;


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
		metaDataSources.addAnnotatedClass(Bid.class);
		metaDataSources.addAnnotatedClass(User.class);
		MetadataBuilder metaBuilder = metaDataSources.getMetadataBuilder();
		Metadata metaData = metaBuilder.build();
		return metaData.buildSessionFactory();
	}

	private static void addBidToItem(Session session) {
		session.getTransaction().begin();
		Item item = session.find(Item.class, 2L);
		Bid newBid = new Bid(70_000);
		System.out.println("Before Add");
		item.getBids().add(newBid);
		System.out.println("After Add");
		session.persist(newBid);
		session.getTransaction().commit();
	}
	
	private static void addUser(Session session) {
		session.getTransaction().begin();
		User user = new User();
		session.persist(user);
		session.getTransaction().commit();
	}
	
	private static void assignItemToUser(Session session) {
		session.getTransaction().begin();
		Item item = session.find(Item.class, 5L);
		Set<Item> boughtItems = new HashSet<>();
		boughtItems.add(item);
		User user = session.find(User.class, 1L);
		user.setBoughtItems(boughtItems);
		item.setBuyer(user);
		session.persist(user);
		session.getTransaction().commit();
	}

	public static void main(String[] args) {
		 Session session =loadConfiguration().getCurrentSession();
		 /*session.getTransaction().begin();
		 System.out.println("Adding Item");
		 Item someItem = new Item("Yamaha Rx 100");
		 Bid someBid = new Bid(50000);
		 someBid.setItem(someItem);
		 System.out.println("Adding Bid to Item");
		 someItem.getBids().add(someBid);
		 Bid someBid1 = new Bid(60000);
		 someBid1.setItem(someItem);
		 System.out.println("Adding Bid to Item");
		 someItem.getBids().add(someBid1);
		 session.persist(someItem);
		 session.getTransaction().commit();*/
		 //addBidToItem(session);
		 //addUser(session);
		 assignItemToUser(session);
	}
}
