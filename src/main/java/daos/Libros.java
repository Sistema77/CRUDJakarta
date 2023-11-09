package daos;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.TypedQuery;

@Entity
@Table(name="libros", schema="gbp_operacional")
public class Libros {

	//ATRIBUTOS
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_libros", nullable=false)
	private long id_libros;
	
	@Column(name="titulo_libro", nullable=false)
	private String titulo_libro;
	
	@Column(name="edicion_libro", nullable=false)
	private String edicion_libro;
	
	@Column(name="cantidad", nullable=false)
	private int cantidad;
	
	@ManyToOne
	@JoinColumn(name="id_editorial")
	Editoriales editoriales;
	
	@ManyToOne
	@JoinColumn(name="id_generos")
	Generos generos;
	
	@ManyToOne
	@JoinColumn(name="id_colecciones")
	Colecciones colecciones;
	
	@JoinTable(
			name="Rel_Autores_Libros", schema = "gbp_operacional",
			joinColumns = @JoinColumn(name = "id_libros"),
			inverseJoinColumns = @JoinColumn(name = "id_autor")
	)
	@ManyToMany
	List<Autores> librosAutores;
	
	@JoinTable(
			name="Rel_Prestamos_Libros", schema = "gbp_operacional",
			joinColumns = @JoinColumn(name = "id_libros"),
			inverseJoinColumns = @JoinColumn(name = "id_prestamo")
	)
	@ManyToMany
	List<Prestamos> librosPrestamos;
	
	 // Constructor por defecto
    public Libros() {
    }

    // Constructor con atributos
    public Libros(String titulo_libro, String edicion_libro, int cantidad) {
        this.titulo_libro = titulo_libro;
        this.edicion_libro = edicion_libro;
        this.cantidad = cantidad;
    }

    // Getters y setters para los atributos
    public long getId_libros() {
        return id_libros;
    }

    public void setId_libros(long id_libros) {
        this.id_libros = id_libros;
    }

    public String getTitulo_libro() {
        return titulo_libro;
    }

    public void setTitulo_libro(String titulo_libro) {
        this.titulo_libro = titulo_libro;
    }

    public String getEdicion_libro() {
        return edicion_libro;
    }

    public void setEdicion_libro(String edicion_libro) {
        this.edicion_libro = edicion_libro;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Métodos adaptados de Acceso
    public static List<Libros> selectAll(EntityManager em) {
        String jpql = "SELECT l FROM Libros l";
        TypedQuery<Libros> query = em.createQuery(jpql, Libros.class);
        List<Libros> libros = query.getResultList();
        return libros;
    }

    public void select(EntityManager em) {
        List<Libros> libros = Libros.selectAll(em);

        for (Libros libro : libros) {
            System.out.println(
                "\nID Libro: " + libro.getId_libros() + "\n" +
                "Título del Libro: " + libro.getTitulo_libro() + "\n" +
                "Edición del Libro: " + libro.getEdicion_libro() + "\n" +
                "Cantidad: " + libro.getCantidad()
            );
        }
    }

    public void insert(EntityManager em, String titulo_libro, String edicion_libro, int cantidad) {
        Libros libro = new Libros(titulo_libro, edicion_libro, cantidad);
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.persist(libro);
        tx.commit();
    }

    public void update(EntityManager em, long id, String nuevoTituloLibro, String nuevaEdicionLibro, int nuevaCantidad) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Libros libro = em.find(Libros.class, id);

        if (libro != null) {
            libro.setTitulo_libro(nuevoTituloLibro);
            libro.setEdicion_libro(nuevaEdicionLibro);
            libro.setCantidad(nuevaCantidad);
            em.merge(libro);
        } else {
            System.out.print(false);
        }

        tx.commit();
    }

    public void delete(EntityManager em, long id) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Libros libro = em.find(Libros.class, id);

        if (libro != null) {
            em.remove(libro);
        } else {
            System.out.print("No se ha encontrado el elemento");
        }

        tx.commit();
    }
}
