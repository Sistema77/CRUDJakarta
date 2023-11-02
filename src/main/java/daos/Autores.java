package daos;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="autores", schema="gbp_operacional")
public class Autores {
			// ATRIBUTOS
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_autor", nullable=false)
	private long id_autor;
	
	@Column(name="nombre_autor", nullable=false)
	private String nombre_autor;
	
	@ManyToMany(mappedBy="librosAutores")
	List<Libros> autoresLibros;
}
