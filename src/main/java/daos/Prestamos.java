package daos;

import java.util.Calendar;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.TypedQuery;

@Entity
@Table(name="prestamos", schema="gbp_operacional")
public class Prestamos {
	//ATRIBUTOS
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_prestamos", nullable=false)
	private long id_prestamos;
	
	@Column(name="fch_inicio_prestamo") 
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fch_inicio_prestamo;
	
	@Column(name="fch_fin_prestamo") 
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fch_fin_prestamo;
	
	@Column(name="fch_entrega_prestamo") 
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fch_entrega_prestamo;
	
	 @ManyToOne
	 @JoinColumn(name="id_usuario")
	 Usuario usuarioPrestamos;
	 
	 @ManyToOne
	 @JoinColumn(name="id_estado_prestamo")
	 EstadosPrestamos EstadosPrestamos;
	 
	 @ManyToMany(mappedBy="librosPrestamos")
	 List<Libros> librosPrestamos;

	// Constructor 
	    public Prestamos() {}

	    public Prestamos(Calendar fch_inicio_prestamo, Calendar fch_fin_prestamo, Calendar fch_entrega_prestamo) {
	        this.fch_inicio_prestamo = fch_inicio_prestamo;
	        this.fch_fin_prestamo = fch_fin_prestamo;
	        this.fch_entrega_prestamo = fch_entrega_prestamo;
	    }

	    // Getters y setters 
	    public long getId_prestamos() {
	        return id_prestamos;
	    }

	    public void setId_prestamos(long id_prestamos) {
	        this.id_prestamos = id_prestamos;
	    }

	    public Calendar getFch_inicio_prestamo() {
	        return fch_inicio_prestamo;
	    }

	    public void setFch_inicio_prestamo(Calendar fch_inicio_prestamo) {
	        this.fch_inicio_prestamo = fch_inicio_prestamo;
	    }

	    public Calendar getFch_fin_prestamo() {
	        return fch_fin_prestamo;
	    }

	    public void setFch_fin_prestamo(Calendar fch_fin_prestamo) {
	        this.fch_fin_prestamo = fch_fin_prestamo;
	    }

	    public Calendar getFch_entrega_prestamo() {
	        return fch_entrega_prestamo;
	    }

	    public void setFch_entrega_prestamo(Calendar fch_entrega_prestamo) {
	        this.fch_entrega_prestamo = fch_entrega_prestamo;
	    }

	    // METODOS
	    public static List<Prestamos> selectAll(EntityManager em) {
	        String jpql = "SELECT p FROM Prestamos p";
	        TypedQuery<Prestamos> query = em.createQuery(jpql, Prestamos.class);
	        List<Prestamos> prestamos = query.getResultList();
	        return prestamos;
	    }

	    public void select(EntityManager em) {
	        List<Prestamos> prestamos = Prestamos.selectAll(em);

	        for (Prestamos prestamo : prestamos) {
	            System.out.println(
	                "\nID de Préstamo: " + prestamo.getId_prestamos() + "\n" +
	                "Fecha de Inicio: " + prestamo.getFch_inicio_prestamo() + "\n" +
	                "Fecha de Fin: " + prestamo.getFch_fin_prestamo() + "\n" +
	                "Fecha de Entrega: " + prestamo.getFch_entrega_prestamo()
	            );
	        }
	    }
	    
	    public void insert(EntityManager em, Calendar fch_inicio_prestamo, Calendar fch_fin_prestamo, Calendar fch_entrega_prestamo) {
	        Prestamos prestamo = new Prestamos(fch_inicio_prestamo, fch_fin_prestamo, fch_entrega_prestamo);
	        EntityTransaction tx = em.getTransaction();

	        tx.begin();
	        em.persist(prestamo);
	        tx.commit();
	    }

	    public void update(EntityManager em, long id, Calendar nuevaFchInicioPrestamo, Calendar nuevaFchFinPrestamo, Calendar nuevaFchEntregaPrestamo) {
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();

	        Prestamos prestamo = em.find(Prestamos.class, id);

	        if (prestamo != null) {
	            prestamo.setFch_inicio_prestamo(nuevaFchInicioPrestamo);
	            prestamo.setFch_fin_prestamo(nuevaFchFinPrestamo);
	            prestamo.setFch_entrega_prestamo(nuevaFchEntregaPrestamo);
	            em.merge(prestamo);
	        } else {
	            System.out.print(false);
	        }

	        tx.commit();
	    }

	    public void delete(EntityManager em, long id) {
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();

	        Prestamos prestamo = em.find(Prestamos.class, id);

	        if (prestamo != null) {
	            em.remove(prestamo);
	        } else {
	            System.out.print("No se ha encontrado el elemento");
	        }

	        tx.commit();
	    }
}
