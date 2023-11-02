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
}
