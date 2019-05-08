package com.helloworld;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App 
{
    public static void main( String[] args )
    {
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("HelloWorldPU");
    	EntityManager em = emf.createEntityManager();
    	em.getTransaction().begin();
    	Message m = new Message();
    	m.setText("Hello World!");
    	em.persist(m);
    	em.getTransaction().commit();
    	em.close();
        System.out.println( "Hello World!" );
    }
}
