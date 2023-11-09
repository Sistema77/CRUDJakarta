package daos;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.TypedQuery;

@Entity
@Table(name="autores", schema="gbp_operacional")
public class Autores {
			// ATRIBUTOS
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_autor", nullable=false)
	private long id_autor;
	
	@Column(name="nombre_autor", nullable=false)
	private String nombre_autor;
	
	@ManyToMany(mappedBy="librosAutores")
	List<Libros> autoresLibros;
	
	// METODOS
	
	 public static List<Autores> selectAll(EntityManager em) {
		 String jpql = "SELECT a FROM Autores a";
	     TypedQuery<Autores> query = em.createQuery(jpql, Autores.class);
	     List<Autores> autores = query.getResultList();
	     return autores;
	 }

	 public void insert(EntityManager em, String nombre_autor) {
		 Autores autor = new Autores(nombre_autor);
	     EntityTransaction tx = em.getTransaction();

	     tx.begin();
	     em.persist(autor);
	     tx.commit();
	 }

	 public void update(EntityManager em, long id, String nuevoNombreAutor) {
	     EntityTransaction tx = em.getTransaction();
	     tx.begin();

	     Autores autor = em.find(Autores.class, id);

	     if (autor != null) {
	     	autor.setNombre_autor(nuevoNombreAutor);
	        em.merge(autor);
	     } else {
	     	System.out.print(false);
	     }

	     tx.commit();
	}

	public void delete(EntityManager em, long id) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Autores autor = em.find(Autores.class, id);

		if (autor != null) {
	    	em.remove(autor);
	    	} else {
	            System.out.print("No se ha encontrado el elemento");
	    }

	    tx.commit();
	}

	public void select(EntityManager em) {
		List<Autores> autores = Autores.selectAll(em);

		for (Autores autor : autores) {
	    	System.out.println(
	    			"\nNombre del Autor: " + autor.getNombre_autor()
	        );
		}
	}
	
	// SETTING / Getter
	
	public String getNombre_autor() {
	    return nombre_autor;
	}

	public void setNombre_autor(String nombre_autor) {
	    this.nombre_autor = nombre_autor;
	}
	
	// CONSTRUCTOR
	
	public Autores() {}
	
	public Autores(String nombre_autor) {
		this.nombre_autor = nombre_autor;
	}
}
