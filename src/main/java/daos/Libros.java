package daos;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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
}
