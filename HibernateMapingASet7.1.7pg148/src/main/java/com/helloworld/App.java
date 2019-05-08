package com.helloworld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaQuery;

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
		MetadataBuilder metaBuilder = metaDataSources.getMetadataBuilder();
		Metadata metaData = metaBuilder.build();
		return metaData.buildSessionFactory();
	}

	private static void listImages(Session session) {
		session.getTransaction().begin();
		CriteriaQuery<Item> criteriaQuery = session.getCriteriaBuilder().createQuery(Item.class);
		criteriaQuery.from(Item.class);
		session.createQuery(criteriaQuery).getResultList().forEach(System.out::println);
	}

	public static void main(String[] args) {
		 Session session =loadConfiguration().getCurrentSession();
		 /*session.getTransaction().begin();
		 Item it = new Item();
		 it.setImageDescription("Karthik BDay");
		 Map<String,String> imageList = new HashMap<>();
		 imageList.put("first Image","image_1.jpg");
		 imageList.put("Second Image","image_3.jpg");
		 imageList.put("Third Image","image_4.jpg");
		 imageList.put("Fourth Image","image_5.jpg");
		 it.setImageList(imageList);
		 session.save(it);
		 session.getTransaction().commit();*/
		 listImages(session);
	}
}
