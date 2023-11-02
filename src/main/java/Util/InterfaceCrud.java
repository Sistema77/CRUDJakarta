package Util;

import jakarta.persistence.EntityManager;

public interface InterfaceCrud {
	public Object select(EntityManager em, OpcionesEnum clase);
	public void create(EntityManager em, OpcionesEnum clase);
	public void update(EntityManager em, OpcionesEnum clase);
	public void delete(EntityManager em, OpcionesEnum clase);
}