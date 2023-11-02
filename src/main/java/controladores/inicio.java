package controladores;

import daos.Acceso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class inicio {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
		EntityManager em = emf.createEntityManager();
		
		
		Acceso acceso = new Acceso("Usu","Acceso usuarios biblioteca");
		crud.select(em, acceso);
		/*em.getTransaction().begin();
		
		em.persist(acceso);
		em.getTransaction().commit();*/
		em.close();
	}
}