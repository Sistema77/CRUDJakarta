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
@Table(name="generos", schema="gbp_operacional")
public class Generos {
	//ATRIBUTOS
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_genero", nullable=false)
	private long id_genero;
	
	@Column(name="nombre_genero", nullable=false)
	private String nombre_genero;
	
	@Column(name="descripcion_genero", nullable=false)
	private String descripcion_genero;
	
	@OneToMany(mappedBy="generos")
	List<Libros> generosdelLibros;
	
	 // Constructor
    public Generos() {
    }

    public Generos(String nombre_genero, String descripcion_genero) {
        this.nombre_genero = nombre_genero;
        this.descripcion_genero = descripcion_genero;
    }

    // Getters y setters 
    public long getId_genero() {
        return id_genero;
    }

    public void setId_genero(long id_genero) {
        this.id_genero = id_genero;
    }

    public String getNombre_genero() {
        return nombre_genero;
    }

    public void setNombre_genero(String nombre_genero) {
        this.nombre_genero = nombre_genero;
    }

    public String getDescripcion_genero() {
        return descripcion_genero;
    }

    public void setDescripcion_genero(String descripcion_genero) {
        this.descripcion_genero = descripcion_genero;
    }

    // METODOS
    public static List<Generos> selectAll(EntityManager em) {
        String jpql = "SELECT g FROM Generos g";
        TypedQuery<Generos> query = em.createQuery(jpql, Generos.class);
        List<Generos> generos = query.getResultList();
        return generos;
    }

    public void select(EntityManager em) {
        List<Generos> generos = Generos.selectAll(em);

        for (Generos genero : generos) {
            System.out.println(
                "\nID Género: " + genero.getId_genero() + "\n" +
                "Nombre Género: " + genero.getNombre_genero() + "\n" +
                "Descripción Género: " + genero.getDescripcion_genero()
            );
        }
    }
    
    public void insert(EntityManager em, String nombre_genero, String descripcion_genero) {
        Generos genero = new Generos(nombre_genero, descripcion_genero);
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.persist(genero);
        tx.commit();
    }

    public void update(EntityManager em, long id, String nuevoNombreGenero, String nuevaDescripcionGenero) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Generos genero = em.find(Generos.class, id);

        if (genero != null) {
            genero.setNombre_genero(nuevoNombreGenero);
            genero.setDescripcion_genero(nuevaDescripcionGenero);
            em.merge(genero);
        } else {
            System.out.print(false);
        }

        tx.commit();
    }

    public void delete(EntityManager em, long id) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Generos genero = em.find(Generos.class, id);

        if (genero != null) {
            em.remove(genero);
        } else {
            System.out.print("No se ha encontrado el elemento");
        }

        tx.commit();
    }
}
