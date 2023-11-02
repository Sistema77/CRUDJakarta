package controladores;

import daos.Acceso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class inicio {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
		EntityManager em = emf.createEntityManager();
		
		
		/*em.getTransaction().begin();
		Acceso acceso = new Acceso("Usu","Acceso usuarios biblioteca");
		em.persist(acceso);
		em.getTransaction().commit();*/
		em.close();
	}
}