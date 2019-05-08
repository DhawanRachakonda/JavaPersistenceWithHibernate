package com.helloworld;

import java.util.HashMap;
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
		 session.getTransaction().begin();
		 Item it = new Item();
		 it.setImageDescription("Karthik BDay");
		 Map<FileName,Image> imageList = new HashMap<>();
		 imageList.put(new FileName("image_1", "jpg"), new Image("title 1",200, 300));
		 imageList.put(new FileName("image_2", "jpg"),new Image("title 2", 200, 300));
		 imageList.put(new FileName("image_3", "jpg"),new Image("title 3", 200, 300));
		 imageList.put(new FileName("image_4", "jpg"),new Image("title 4", 200, 300));
		 imageList.put(new FileName("image_5", "jpg"),new Image("title 5", 200, 300));
		 it.setImageList(imageList);
		 session.save(it);
		 session.getTransaction().commit();
		 //listImages(session);
	}
}
