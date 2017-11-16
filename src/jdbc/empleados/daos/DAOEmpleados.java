/**
 * 
 */
package jdbc.empleados.daos;

import java.util.Set;

import jdbc.empleados.model.Empleado;

/**
 * @author Administrador
 *
 * Interfaz para acceder a los empleados
 */
public interface DAOEmpleados {

	/**
	 * Devuelve el conjunto de empleados
	 * @return
	 * @throws EmpDAOException en caso de error de acceso a los datos
	 */
	public abstract Set<Empleado> getEmpleados() 
			throws EmpDAOException;
	
	public abstract Empleado getEmpleado(String cif);
	
	/**
	 * Inserta el empleado emp. Devuelve true si todo ha sido correcto, y
	 * false en caso contrario. En caso de que el empleado exista, se deja la decisión de sustituirlo
	 * o no a la clase de implementación
	 * @param emp
	 * @return
	 */
	public abstract boolean insertaEmpleado(Empleado emp);
	
	public abstract boolean eliminaEmpleado(String cif);
}
