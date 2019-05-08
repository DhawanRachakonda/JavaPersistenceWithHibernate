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
		metaDataSources.addAnnotatedClass(User.class);
		metaDataSources.addAnnotatedClass(Shipment.class);
		MetadataBuilder metaBuilder = metaDataSources.getMetadataBuilder();
		Metadata metaData = metaBuilder.build();
		return metaData.buildSessionFactory();
	}

	private static void assignShipmentToUser(Session session) {
		session.getTransaction().begin();
		User user = session.find(User.class, 1L);
		Set<Shipment> shipmentList = new HashSet<>();
		Shipment shipment = new Shipment();
		shipment.createdOn=new Date();
		shipmentList.add(shipment);
		user.getAddress().setDeliveries(shipmentList);
		session.persist(user);
		session.getTransaction().commit();
	}
	
	private static void addUser(Session session) {
		session.getTransaction().begin();
		User user = new User();
		user.setAddress(new Address("10", "500020", "Telangana"));
		session.persist(user);
		session.getTransaction().commit();
	}

	public static void main(String[] args) {
		 Session session =loadConfiguration().getCurrentSession();
		 //addUser(session);
		 assignShipmentToUser(session);
		 //addBidToItem(session);
		 //addUser(session);
	}
}
