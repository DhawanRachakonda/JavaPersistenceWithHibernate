package com.helloworld;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Session;
import org.hibernate.type.Type;

@SuppressWarnings("serial")
public class UserAuditLogInterceptor extends EmptyInterceptor {

	protected Session currentSession;
	protected Long currentUserId;
	protected Set<UserAudit> inserts = new HashSet<UserAudit>();
	protected Set<UserAudit> updates = new HashSet<UserAudit>();
	
	public Long getCurrentUserId() {
		return currentUserId;
	}

	public void setCurrentUserId(Long currentUserId) {
		this.currentUserId = currentUserId;
	}

	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if(entity instanceof User) {
			inserts.add((UserAudit)entity);
		}
		return true;
	}
	
	public Set<UserAudit> getInserts() {
		return inserts;
	}

	public void setInserts(Set<UserAudit> inserts) {
		this.inserts = inserts;
	}

	public Set<UserAudit> getUpdates() {
		return updates;
	}

	public void setUpdates(Set<UserAudit> updates) {
		this.updates = updates;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void postFlush(Iterator iterator) {
	      Session tempSession = currentSession.sessionWithOptions().transactionContext().connection().openSession();
	      try {
	    	  for(UserAudit entity : inserts) {
	    		  tempSession.persist(new UserAuditRecord("insert", entity.toString(), currentUserId));
	    	  }
	    	  
	    	  for(UserAudit entity : updates) {
	    		  tempSession.persist(new UserAuditRecord("update", entity.toString(), currentUserId));
	    	  }
	    	  tempSession.flush();
	      } finally {
	    	  tempSession.close();
	    	  inserts.clear();
	    	  updates.clear();
	      }
	}
}