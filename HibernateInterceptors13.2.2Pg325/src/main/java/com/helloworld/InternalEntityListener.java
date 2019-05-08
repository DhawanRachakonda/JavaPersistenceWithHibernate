package com.helloworld;

import javax.persistence.PostPersist;

public class InternalEntityListener {

	public InternalEntityListener() {}
	
	@PostPersist
	public void sendAlert(Object object) {
		if(object instanceof User) {
			System.out.println("is user");
			User user = (User)object;
			System.out.println("======================================================");
			System.out.println("User Added");
			System.out.println("Id : "+ user.getId());
		}
	}
}
