package jdbc.empleados.ws.server;

import java.util.Set;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import jdbc.empleados.model.Empleado;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface EmpleadosService {

	/**
	 * Devuelve el conjunto de empleados
	 * @return
	 */
	@WebMethod
	public abstract Empleado[] getEmpleados();
	
	@WebMethod
	public abstract Set<Empleado> getAllEmpleados();
	
	@WebMethod
	public abstract Empleado getEmpleado(String cif);
	
	/**
	 * Inserta el empleado emp. Devuelve true si todo ha sido correcto, y
	 * false en caso contrario. En caso de que el empleado exista, se deja la decisión de sustituirlo
	 * o no a la clase de implementación
	 * @param emp
	 * @return
	 */
	@WebMethod
	public abstract boolean insertaEmpleado(Empleado emp);
	
	@WebMethod
	public abstract boolean eliminaEmpleado(String cif);
}
