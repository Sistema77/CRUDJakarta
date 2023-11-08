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

@Entity //Es una Entidad
@Table(name="accesos", schema="gbp_operacional") // Crea la tabla
public class Acceso {

	                           // ATRIBUTOS
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

	                        // CONSTRUCTORES
	public Acceso() {
		super();
	}

	public Acceso(String codigo_acceso, String descripcion_acceso) {
		super();

		this.codigo_acceso = codigo_acceso;
		this.descripcion_acceso = descripcion_acceso;
	}
	
							// METODOS

	public void select(EntityManager em) {
		// Seleciona y muestra por el id
		List<Acceso> accesos = Acceso.selectAll(em);

        for (Acceso acceso : accesos) {
            System.out.println(
            		"\nCodigo Acceso: " + acceso.getCodigo_acceso() + "\n" +
            		"Descripcion Acceso: " + acceso.getdescripcion_acceso()
            		);
        }


	}
	
	public static List<Acceso> selectAll(EntityManager em) {
		 // Creao un metodo para Hacer un SELECT *
	     String jpql = "SELECT a FROM Acceso a";
	     TypedQuery<Acceso> query = em.createQuery(jpql, Acceso.class);

	     List<Acceso> accesos = query.getResultList();

	     return accesos;
	}
	
	public void update(EntityManager em, long id, String nuevoCodigo, String nuevaDescripcion) {
        // Actualiza los datos de un valor ya existente
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Acceso acceso = em.find(Acceso.class, id);

        if (acceso != null) {
            
            acceso.setCodigo_acceso(nuevoCodigo);
            acceso.setDescripcion_acceso(nuevaDescripcion);

            em.merge(acceso);
        }else {
        	System.out.print(false);
        }

        // Finaliza la transacción
        tx.commit();
    }
	

							// Setter 
	public void setCodigo_acceso(String codigo_acceso) {
		this.codigo_acceso = codigo_acceso;
	}
	
	public void setDescripcion_acceso(String descripcion_acceso) {
		this.descripcion_acceso = descripcion_acceso;
	}
							// Getter
	public String getCodigo_acceso() {
		return this.codigo_acceso;
	}
	public String getdescripcion_acceso() {
		return this.descripcion_acceso;
	}
}