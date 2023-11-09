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
@Table(name="Estados_Prestamos", schema="gbp_operacional")
public class EstadosPrestamos {
	//ATRIBUTOS
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_estado_prestamo", nullable=false)
	private long id_estado_prestamo;
	
	@Column(name="codigo_estado_prestamo", nullable=false)
	private String codigo_estado_prestamo;
	
	@Column(name="descripcion_estado_prestamo", nullable=false)
	private String descripcion_estado_prestamo;
	
    @OneToMany(mappedBy="EstadosPrestamos")
    List<Prestamos> prestamo;
    
    // CONSTRUCTOR
    public EstadosPrestamos() {
    }

    public EstadosPrestamos(String codigo_estado_prestamo, String descripcion_estado_prestamo) {
        this.codigo_estado_prestamo = codigo_estado_prestamo;
        this.descripcion_estado_prestamo = descripcion_estado_prestamo;
    }

    // Getters y setters
    public long getId_estado_prestamo() {
        return id_estado_prestamo;
    }

    public void setId_estado_prestamo(long id_estado_prestamo) {
        this.id_estado_prestamo = id_estado_prestamo;
    }

    public String getCodigo_estado_prestamo() {
        return codigo_estado_prestamo;
    }

    public void setCodigo_estado_prestamo(String codigo_estado_prestamo) {
        this.codigo_estado_prestamo = codigo_estado_prestamo;
    }

    public String getDescripcion_estado_prestamo() {
        return descripcion_estado_prestamo;
    }

    public void setDescripcion_estado_prestamo(String descripcion_estado_prestamo) {
        this.descripcion_estado_prestamo = descripcion_estado_prestamo;
    }

    // METODOS
    public static List<EstadosPrestamos> selectAll(EntityManager em) {
        String jpql = "SELECT e FROM EstadosPrestamos e";
        TypedQuery<EstadosPrestamos> query = em.createQuery(jpql, EstadosPrestamos.class);
        List<EstadosPrestamos> estadosPrestamos = query.getResultList();
        return estadosPrestamos;
    }

    public void select(EntityManager em) {
        List<EstadosPrestamos> estadosPrestamos = EstadosPrestamos.selectAll(em);

        for (EstadosPrestamos estadoPrestamo : estadosPrestamos) {
            System.out.println(
                "\nID Estado de Prestamo: " + estadoPrestamo.getId_estado_prestamo() + "\n" +
                "Codigo Estado de Prestamo: " + estadoPrestamo.getCodigo_estado_prestamo() + "\n" +
                "Descripcion Estado de Prestamo: " + estadoPrestamo.getDescripcion_estado_prestamo()
            );
        }
    }
    
    public void insert(EntityManager em, String codigo_estado_prestamo, String descripcion_estado_prestamo) {
        EstadosPrestamos estadoPrestamo = new EstadosPrestamos(codigo_estado_prestamo, descripcion_estado_prestamo);
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.persist(estadoPrestamo);
        tx.commit();
    }

    public void update(EntityManager em, long id, String nuevoCodigoEstadoPrestamo, String nuevaDescripcionEstadoPrestamo) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        EstadosPrestamos estadoPrestamo = em.find(EstadosPrestamos.class, id);

        if (estadoPrestamo != null) {
            estadoPrestamo.setCodigo_estado_prestamo(nuevoCodigoEstadoPrestamo);
            estadoPrestamo.setDescripcion_estado_prestamo(nuevaDescripcionEstadoPrestamo);
            em.merge(estadoPrestamo);
        } else {
            System.out.print("No se a podido Actualizar");
        }

        tx.commit();
    }

    public void delete(EntityManager em, long id) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        EstadosPrestamos estadoPrestamo = em.find(EstadosPrestamos.class, id);

        if (estadoPrestamo != null) {
            em.remove(estadoPrestamo);
        } else {
            System.out.print("No se ha encontrado el elemento");
        }

        tx.commit();
    }
}
