package controladores;

import daos.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class inicio {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
		EntityManager em = emf.createEntityManager();
		
		// TEST
		
		Usuario us = new Usuario();
		us.insert(em, "q", null, null, null, null, null, false, null, null, null, null);
		us.select(em);
		us.update(em, 2, "w", null, null, null, null, null, false, null, null, null, null);
		us.select(em);
		us.delete(em, 1);
		
		if(em.isOpen()) {
			em.close();
		}
	}
}