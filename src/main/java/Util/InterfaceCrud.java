package Util;

import jakarta.persistence.EntityManager;

public interface InterfaceCrud {
	public static void select(EntityManager em, OpcionesEnum clase);
	public static void create(EntityManager em, OpcionesEnum clase);
	public static void update(EntityManager em, OpcionesEnum clase);
	public static void delete(EntityManager em, OpcionesEnum clase);
}