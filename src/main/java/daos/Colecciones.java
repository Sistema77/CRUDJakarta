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
@Table(name="colecciones", schema="gbp_operacional")
public class Colecciones {
	//ATRIBUTOS
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_colecciones", nullable=false)
	private long id_colecciones;
	
	@Column(name="nombre_coleccion", nullable=false)
	private String nombre_coleccion;
	
	@OneToMany(mappedBy="colecciones")
	List<Libros> coleccionesdelLibros;
	
	  // CONSTRUCTOR
	
    public Colecciones() {
    }


    public Colecciones(String nombre_coleccion) {
        this.nombre_coleccion = nombre_coleccion;
    }

    // Getters y setters 
    
    public long getId_colecciones() {
        return id_colecciones;
    }

    public void setId_colecciones(long id_colecciones) {
        this.id_colecciones = id_colecciones;
    }

    public String getNombre_coleccion() {
        return nombre_coleccion;
    }

    public void setNombre_coleccion(String nombre_coleccion) {
        this.nombre_coleccion = nombre_coleccion;
    }

    // METODOS
    
    public static List<Colecciones> selectAll(EntityManager em) {
        String jpql = "SELECT c FROM Colecciones c";
        TypedQuery<Colecciones> query = em.createQuery(jpql, Colecciones.class);
        List<Colecciones> colecciones = query.getResultList();
        return colecciones;
    }

    public void select(EntityManager em) {
        List<Colecciones> colecciones = Colecciones.selectAll(em);

        for (Colecciones coleccion : colecciones) {
            System.out.println(
                "\nID Coleccion: " + coleccion.getId_colecciones() + "\n" +
                "Nombre Coleccion: " + coleccion.getNombre_coleccion()
            );
        }
    }
    
    public void insert(EntityManager em, String nombre_coleccion) {
        Colecciones coleccion = new Colecciones(nombre_coleccion);
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.persist(coleccion);
        tx.commit();
    }

    public void update(EntityManager em, long id, String nuevoNombreColeccion) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Colecciones coleccion = em.find(Colecciones.class, id);

        if (coleccion != null) {
            coleccion.setNombre_coleccion(nuevoNombreColeccion);
            em.merge(coleccion);
        } else {
            System.out.print(false);
        }

        tx.commit();
    }

    public void delete(EntityManager em, long id) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Colecciones coleccion = em.find(Colecciones.class, id);

        if (coleccion != null) {
            em.remove(coleccion);
        } else {
            System.out.print("No se ha encontrado el elemento");
        }

        tx.commit();
    }
}
