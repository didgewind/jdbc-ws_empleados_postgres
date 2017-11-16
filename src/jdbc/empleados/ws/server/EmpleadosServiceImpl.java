package jdbc.empleados.ws.server;

import java.util.ArrayList;
import java.util.Set;

import javax.jws.WebService;

import jdbc.empleados.daos.DAOEmpBd;
import jdbc.empleados.daos.DAOEmpleados;
import jdbc.empleados.model.Empleado;

@WebService(endpointInterface = "jdbc.empleados.ws.server.EmpleadosService")  
public class EmpleadosServiceImpl implements EmpleadosService {

	private DAOEmpleados dao = new DAOEmpBd();
	
	@Override
//	@XmlElement(type=HashSet.class)
	public Empleado[] getEmpleados() {
		Set<Empleado> sEmpleados = dao.getEmpleados();
		Empleado[] eReturn = new Empleado[sEmpleados.size()];
		int index = 0;
		for (Empleado emp : sEmpleados) {
			eReturn[index++] = emp;
		}
		return eReturn;
	}

	@Override
	public Empleado getEmpleado(String cif) {
		return dao.getEmpleado(cif);
	}

	@Override
	public boolean insertaEmpleado(Empleado emp) {
		return dao.insertaEmpleado(emp);
	}

	@Override
	public boolean eliminaEmpleado(String cif) {
		return dao.eliminaEmpleado(cif);
	}

	@Override
	public Set<Empleado> getAllEmpleados() {
		return dao.getEmpleados();
	}

}
