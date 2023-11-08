package controladores;

import daos.Acceso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class inicio {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
		EntityManager em = emf.createEntityManager();
		
		
		Acceso acceso = new Acceso();

		acceso.select(em);
		acceso.update(em, 1, "dasd", "sdasd");
		
		/*em.getTransaction().begin();
		
		em.persist(acceso);
		em.getTransaction().commit();*/
		
		if(em.isOpen()) {
			em.close();
		}
	}
}