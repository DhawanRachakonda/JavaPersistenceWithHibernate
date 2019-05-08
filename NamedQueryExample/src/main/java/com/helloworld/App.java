package com.helloworld;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.TransactionManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.transform.AliasToBeanConstructorResultTransformer;

import javassist.SerialVersionUID;

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
		serviceRegistryBuilder.applySetting("hbm2ddl.auto", "update");
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
		List<Message> messageList = session.getNamedQuery("listMessages").setResultTransformer(new AliasToBeanConstructorResultTransformer(Message.class.getConstructors()[1]))
				.list();
		for (Message m : messageList) {
			System.out.println(String.format("ID : %d , Text : %s", m.getId(), m.getText()));
		}
	}

	public static void main(String[] args) {
		/*
		 * Session session =loadConfiguration().getCurrentSession();
		 * session.getTransaction().begin(); Message m = new Message();
		 * m.setText("Hello World2!"); session.save(m);
		 * session.getTransaction().commit(); System.out.println(
		 * "Hello World!2" );
		 */
		listMessages();
	}
}
