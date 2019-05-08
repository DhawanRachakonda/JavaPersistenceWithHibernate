package com.helloworld;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
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
		metaDataSources.addAnnotatedClass(Bid.class);
		metaDataSources.addAnnotatedClass(User.class);
		MetadataBuilder metaBuilder = metaDataSources.getMetadataBuilder();
		Metadata metaData = metaBuilder.build();
		return metaData.buildSessionFactory();
	}

	private static void listBidsForItem(Session session) {
		session.getTransaction().begin();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Item> item = criteriaBuilder.createQuery(Item.class);
		/*Root<Item> root = item.from(Item.class);
		item.where(criteriaBuilder.equal(root.get("name"), "Yamaha RX 100"));*/
		List<Item> itemList = session.createQuery(item).getResultList();
		for(Item anItem : itemList) {
			System.out.println("Bids For Item : "+anItem.getName());
			if(anItem.getBids().size() > 0) {
				for(Bid aBid : anItem.getBids()) {
					System.out.println("Bid Amount "+aBid.getAmount());
				}
			}else {
				System.out.println("No Bids For This Item.");
			}
			System.out.println("-------------------------------------");
		}
	}
	
	private static void removeItem(Session session) {
		session.getTransaction().begin();
		Item it = (Item)session.find(Item.class, 1L);
		session.remove(it);
		session.getTransaction().commit();
	}
	
	private static void removeBid(Session session) {
		session.getTransaction().begin();
		User user = session.find(User.class, 2L);
		Item it = session.find(Item.class, 2L);
		Bid firstBid = it.getBids().iterator().next();
		session.remove(firstBid);
		System.out.println(user.getBids().size());
		session.getTransaction().commit();
	}

	public static void main(String[] args) {
		 Session session =loadConfiguration().getCurrentSession();
		 /*session.getTransaction().begin();
		 Item it = new Item("Yamaha RX 100");
		 session.persist(it);
		 User user = new User("Dhawan");
		 User user2 = new User("Badri");
		 Bid bid1 = new Bid(it, 40000,user);
		 it.getBids().add(bid1);
		 Bid bid2 = new Bid(it, 60000,user);
		 it.getBids().add(bid2);
		 Bid bid3 = new Bid(it, 30000,user);
		 it.getBids().add(bid3);
		 Bid bid4 = new Bid(it, 80000,user);
		 it.getBids().add(bid4);
		 user.getBids().add(bid1);
		 user.getBids().add(bid2);
		 user.getBids().add(bid3);
		 user.getBids().add(bid4);
		 session.persist(user);
		 session.persist(user2);
		 session.getTransaction().commit();*/
		 //listBidsForItem(session);
		 //removeItem(session);
		 removeBid(session);
	}
}
