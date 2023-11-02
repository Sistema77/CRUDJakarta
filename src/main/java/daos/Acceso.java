package daos;

import java.util.List;

import Util.InterfaceCrud;
import Util.OpcionesEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity //Es una Entidad
@Table(name="accesos", schema="gbp_operacional") // Crea la tabla
public class Acceso implements InterfaceCrud {

	//ATRIBUTOS
	@Id // Indica que es un ID
	@GeneratedValue(strategy=GenerationType.IDENTITY) // EL ID se Autoincrementa
	@Column(name="id_acceso", nullable=false) // crea la columna no null
	private long id_acceso;

	@Column(name="codigo_acceso", nullable=false)
	private String codigo_acceso;

	@Column(name="descripcion_acceso")
	private String descripcion_acceso;

    @OneToMany(mappedBy="acceso") //Es una relacion 1:N
    List<Usuario> usuariosConAcceso;  //La relacion es con Usuario y recive una lista de usuarios

	//CONSTRUCTORES
	public Acceso() {
		super();
	}

	public Acceso(String codigo_acceso, String descripcion_acceso) {
		super();

		this.codigo_acceso = codigo_acceso;
		this.descripcion_acceso = descripcion_acceso;
	}

		// METODOS
	@Override
	public OpcionesEnum select(EntityManager em, OpcionesEnum clase) {

	    Usuario usuario = em.find(Usuario.class, clase);
		
	    return usuario;
	}

	@Override
	public boolean create(EntityManager em, OpcionesEnum clase) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(EntityManager em, OpcionesEnum clase) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(EntityManager em, OpcionesEnum clase) {
		// TODO Auto-generated method stub
		return false;
	}	
}