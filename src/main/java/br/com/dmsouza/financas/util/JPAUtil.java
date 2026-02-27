package br.com.dmsouza.financas.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("financas");
	
	public static EntityManager getEntityManager() {
		return FACTORY.createEntityManager();
	}
	
}
