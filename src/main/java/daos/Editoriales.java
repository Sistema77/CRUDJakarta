package daos;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.TypedQuery;

@Entity
@Table(name="editoriales", schema="gbp_operacional")
public class Editoriales {
	//ATRIBUTOS
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="id_editorial", nullable=false)
		private long id_editorial;
		
		@Column(name="nombre_editorial", nullable=false)
		private String nombre_editorial;
		
		@OneToMany(mappedBy="editoriales")
		List<Libros> EditorialdelLibros;
		
		// CONSTRUCTOR
	    public Editoriales() {
	    }


	    public Editoriales(String nombre_editorial) {
	        this.nombre_editorial = nombre_editorial;
	    }

	    // Getters y setters 
	    public long getId_editorial() {
	        return id_editorial;
	    }

	    public void setId_editorial(long id_editorial) {
	        this.id_editorial = id_editorial;
	    }

	    public String getNombre_editorial() {
	        return nombre_editorial;
	    }

	    public void setNombre_editorial(String nombre_editorial) {
	        this.nombre_editorial = nombre_editorial;
	    }

	    // METODOS
	    public static List<Editoriales> selectAll(EntityManager em) {
	        String jpql = "SELECT e FROM Editoriales e";
	        TypedQuery<Editoriales> query = em.createQuery(jpql, Editoriales.class);
	        List<Editoriales> editoriales = query.getResultList();
	        return editoriales;
	    }
	    
	    public void select(EntityManager em) {
	        List<Editoriales> editoriales = Editoriales.selectAll(em);

	        for (Editoriales editorial : editoriales) {
	            System.out.println(
	                "\nID Editorial: " + editorial.getId_editorial() + "\n" +
	                "Nombre Editorial: " + editorial.getNombre_editorial()
	            );
	        }
	    }

	    public void insert(EntityManager em, String nombre_editorial) {
	        Editoriales editorial = new Editoriales(nombre_editorial);
	        EntityTransaction tx = em.getTransaction();

	        tx.begin();
	        em.persist(editorial);
	        tx.commit();
	    }

	    public void update(EntityManager em, long id, String nuevoNombreEditorial) {
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();

	        Editoriales editorial = em.find(Editoriales.class, id);

	        if (editorial != null) {
	            editorial.setNombre_editorial(nuevoNombreEditorial);
	            em.merge(editorial);
	        } else {
	            System.out.print(false);
	        }

	        tx.commit();
	    }

	    public void delete(EntityManager em, long id) {
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();

	        Editoriales editorial = em.find(Editoriales.class, id);

	        if (editorial != null) {
	            em.remove(editorial);
	        } else {
	            System.out.print("No se ha encontrado el elemento");
	        }

	        tx.commit();
	    }
}
