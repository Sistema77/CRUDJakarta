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

		//acceso.delete(em, 1);
		//acceso.select(em);
		acceso.insert(em, "qewqqw", "ewqeq");
		/*em.getTransaction().begin();
		
		em.persist(acceso);
		em.getTransaction().commit();*/
		
		if(em.isOpen()) {
			em.close();
		}
	}
}