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
}
