package com.helloworld;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class App 
{

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
		MetadataBuilder metaBuilder = metaDataSources.getMetadataBuilder();
		Metadata metaData = metaBuilder.build();
		return metaData.buildSessionFactory();
	}
	
	private static void getUer() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("HelloWorldPU");
		EntityManager em = emf.createEntityManager();
		TypedQuery<User> query = em.createQuery("select u from User u where u.id = :id and u.userAge = :userAge", User.class).setParameter("id", 1L).setParameter("userAge", 30);
		User user = query.getSingleResult();
		System.out.println("User Name : "+user.getUserName());
	}
	
	private static void getUserByCriteria() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("HelloWorldPU");
		EntityManager em = emf.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
		Root<User> i = criteriaQuery.from(User.class);
		criteriaQuery.select(i).where(cb.equal(i.get("id"), 1L));
		TypedQuery<User> typedUser = em.createQuery(criteriaQuery);
		User user = typedUser.getSingleResult();
		System.out.println("User Name : "+user.getUserName());
	}
	
	private static void addUser() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("HelloWorldPU");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		User u = new User();
		u.setUserName("Dhawan");
		u.setUserAge(30);
		em.persist(u);
		em.getTransaction().commit();
	}
	
    public static void main( String[] args )
    {
    	//loadConfiguration();
    	//addUser();
    	getUer();
    	getUserByCriteria();
    }
}
