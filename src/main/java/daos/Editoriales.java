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
@Table(name="editoriales", schema="gbp_operacional")
public class Editoriales {
	//ATRIBUTOS
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="id_editorial", nullable=false)
		private long id_editorial;
		
		@Column(name="nombre_editorial", nullable=false)
		private String nombre_editorial;
		
		@OneToMany(mappedBy="editoriales")
		List<Libros> EditorialdelLibros;
}
