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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.TypedQuery;

@Entity
@Table(name="usuarios", schema="gbp_operacional")
public class Usuario {	
	
	//ATRIBUTOS
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario", nullable=false)
	private long idUsuario;
	
	@Column(name="dni_usuario", nullable=false)
	private String dniUsuario;
	
	@Column(name="nombre_usuario")
	private String nombreUsuario;
	
	@Column(name="apellidos_usuario")
	private String apellidosUsuario;
	
	@Column(name="tlf_usuario")
	private String tlfUsuario;
	
	@Column(name="email_usuario")
	private String emailUsuario;
	
	@Column(name="clave_usuario")
	private String claveUsuario;
	
	@Column(name="estaBloqueado_usuario")
	private boolean estaBloqueadoUsuario;
	
	@Column(name="fch_fin_bloqueo")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fchFinBloqueo;
	
	@Column(name="fch_alta_usuario")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fchAltaUsuario;
	
	@Column(name="fch_baja_usuario")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fchBajaUsuario;
	
    @ManyToOne
    @JoinColumn(name="acceso")
    Acceso acceso;
	
    @OneToMany(mappedBy = "usuarioPrestamos")
    List<Prestamos> prestamos;

	//CONSTRUCTORES

	public Usuario() {
		super();
	}

	public Usuario( String dniUsuario, String nombreUsuario, String apellidosUsuario, String tlfUsuario,
			String emailUsuario, String claveUsuario, boolean estaBloqueadoUsuario, Calendar fchFinBloqueo,
			Calendar fchAltaUsuario, Calendar fchBajaUsuario, Acceso acceso) {
		super();
		this.dniUsuario = dniUsuario;
		this.nombreUsuario = nombreUsuario;
		this.apellidosUsuario = apellidosUsuario;
		this.tlfUsuario = tlfUsuario;
		this.emailUsuario = emailUsuario;
		this.claveUsuario = claveUsuario;
		this.estaBloqueadoUsuario = estaBloqueadoUsuario;
		this.fchFinBloqueo = fchFinBloqueo;
		this.fchAltaUsuario = fchAltaUsuario;
		this.fchBajaUsuario = fchBajaUsuario;
		this.acceso = acceso;
	}	
	
	// GETTER / SETTER
	
	public long getIdUsuario() {
	    return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
	    this.idUsuario = idUsuario;
	}

	public String getDniUsuario() {
	    return dniUsuario;
	}

	public void setDniUsuario(String dniUsuario) {
	    this.dniUsuario = dniUsuario;
	}

	public String getNombreUsuario() {
	    return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
	    this.nombreUsuario = nombreUsuario;
	}

	public String getApellidosUsuario() {
	    return apellidosUsuario;
	}

	public void setApellidosUsuario(String apellidosUsuario) {
	    this.apellidosUsuario = apellidosUsuario;
	}

	public String getTlfUsuario() {
	    return tlfUsuario;
	}

	public void setTlfUsuario(String tlfUsuario) {
	    this.tlfUsuario = tlfUsuario;
	}

	public String getEmailUsuario() {
	    return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
	    this.emailUsuario = emailUsuario;
	}

	public String getClaveUsuario() {
	    return claveUsuario;
	}

	public void setClaveUsuario(String claveUsuario) {
	    this.claveUsuario = claveUsuario;
	}

	public boolean isEstaBloqueadoUsuario() {
	    return estaBloqueadoUsuario;
	}

	public void setEstaBloqueadoUsuario(boolean estaBloqueadoUsuario) {
	    this.estaBloqueadoUsuario = estaBloqueadoUsuario;
	}

	public Calendar getFchFinBloqueo() {
	    return fchFinBloqueo;
	}

	public void setFchFinBloqueo(Calendar fchFinBloqueo) {
	    this.fchFinBloqueo = fchFinBloqueo;
	}

	public Calendar getFchAltaUsuario() {
	    return fchAltaUsuario;
	}

	public void setFchAltaUsuario(Calendar fchAltaUsuario) {
	    this.fchAltaUsuario = fchAltaUsuario;
	}

	public Calendar getFchBajaUsuario() {
	    return fchBajaUsuario;
	}

	public void setFchBajaUsuario(Calendar fchBajaUsuario) {
	    this.fchBajaUsuario = fchBajaUsuario;
	}

	public Acceso getAcceso() {
	    return acceso;
	}

	public void setAcceso(Acceso acceso) {
	    this.acceso = acceso;
	}

	
	// METODOS
	
	public static List<Usuario> selectAll(EntityManager em) {
        String jpql = "SELECT u FROM Usuario u";
        TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
        List<Usuario> usuarios = query.getResultList();
        return usuarios;
    }

    public void insert(EntityManager em, String dniUsuario, String nombreUsuario,
    		String apellidosUsuario, String tlfUsuario,
            String emailUsuario, String claveUsuario, boolean estaBloqueadoUsuario, 
            Calendar fchFinBloqueo,Calendar fchAltaUsuario, Calendar fchBajaUsuario, Acceso acceso) {
    	
        Usuario usuario = new Usuario(dniUsuario, nombreUsuario, apellidosUsuario, tlfUsuario, emailUsuario, claveUsuario,
                estaBloqueadoUsuario, fchFinBloqueo, fchAltaUsuario, fchBajaUsuario, acceso);
        
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.persist(usuario);
        tx.commit();
    }

    public void update(EntityManager em, long id, String nuevoDniUsuario, String nuevoNombreUsuario, String nuevosApellidosUsuario,
            String nuevoTlfUsuario, String nuevoEmailUsuario, String nuevaClaveUsuario, boolean nuevoEstaBloqueadoUsuario,
            Calendar nuevoFchFinBloqueo, Calendar nuevoFchAltaUsuario, Calendar nuevoFchBajaUsuario, Acceso nuevoAcceso) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Usuario usuario = em.find(Usuario.class, id);

        if (usuario != null) {
            usuario.setDniUsuario(nuevoDniUsuario);
            usuario.setNombreUsuario(nuevoNombreUsuario);
            usuario.setApellidosUsuario(nuevosApellidosUsuario);
            usuario.setTlfUsuario(nuevoTlfUsuario);
            usuario.setEmailUsuario(nuevoEmailUsuario);
            usuario.setClaveUsuario(nuevaClaveUsuario);
            usuario.setEstaBloqueadoUsuario(nuevoEstaBloqueadoUsuario);
            usuario.setFchFinBloqueo(nuevoFchFinBloqueo);
            usuario.setFchAltaUsuario(nuevoFchAltaUsuario);
            usuario.setFchBajaUsuario(nuevoFchBajaUsuario);
            usuario.setAcceso(nuevoAcceso);
            em.merge(usuario);
        } else {
            System.out.print(false);
        }

        tx.commit();
    }

    public void delete(EntityManager em, long id) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Usuario usuario = em.find(Usuario.class, id);

        if (usuario != null) {
            em.remove(usuario);
        } else {
            System.out.print("No se ha encontrado el elemento");
        }

        tx.commit();
    }

    public void select(EntityManager em) {
        List<Usuario> usuarios = Usuario.selectAll(em);

        for (Usuario usuario : usuarios) {
            System.out.println(
                "\nID Usuario: " + usuario.getIdUsuario() + "\n" +
                "DNI: " + usuario.getDniUsuario() + "\n" +
                "Nombre: " + usuario.getNombreUsuario() + "\n" +
                "Apellidos: " + usuario.getApellidosUsuario() + "\n" +
                "Teléfono: " + usuario.getTlfUsuario() + "\n" +
                "Email: " + usuario.getEmailUsuario()
            );
        }
    }
}