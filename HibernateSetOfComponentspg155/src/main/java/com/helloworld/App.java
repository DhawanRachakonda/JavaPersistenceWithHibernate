package com.helloworld;

import java.util.HashSet;
import java.util.Set;

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
		//session.createQuery(criteriaQuery).getResultList().get(0).getImageList().clear();
		session.getTransaction().commit();
	}

	public static void main(String[] args) {
		 Session session =loadConfiguration().getCurrentSession();
		 /*session.getTransaction().begin();
		 Item it = new Item();
		 it.setImageDescription("Karthik BDay");
		 Set<Image> imageList = new HashSet<>();
		 imageList.add(new Image("title 1", "image_1.jpg", 200, 300));
		 imageList.add(new Image("title 2", "image_2.jpg", 200, 300));
		 imageList.add(new Image("title 3", "image_3.jpg", 200, 300));
		 imageList.add(new Image("title 4", "image_4.jpg", 200, 300));
		 imageList.add(new Image("title 5", "image_5.jpg", 200, 300));
		 it.setImageList(imageList);
		 session.save(it);
		 session.getTransaction().commit();*/
		 listImages(session);
	}
}
