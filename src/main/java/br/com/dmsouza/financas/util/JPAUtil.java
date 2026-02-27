package br.com.dmsouza.financas.util;

import jakarta.persistence.*;

public class JPAUtil {
	
	private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("financas");
	
	public static EntityManager getEntityManager() {
		return FACTORY.createEntityManager();
	}
	
}
