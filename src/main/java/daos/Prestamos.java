package daos;

import java.util.Calendar;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

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

}
