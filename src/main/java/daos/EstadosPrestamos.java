package daos;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

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
}
