package jdbc.empleados.ws.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jdbc.empleados.daos.DAOEmpBd;
import jdbc.empleados.daos.DAOEmpleados;
import jdbc.empleados.model.Empleado;

@Path("/empleados") 
public class EmpleadosRestService {

	private DAOEmpleados dao = new DAOEmpBd();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Empleado> getAllEmpleados() {
		return new ArrayList<Empleado>(dao.getEmpleados());
	}

	@GET
	@Path("/{cif}")
	@Produces(MediaType.APPLICATION_JSON)
	public Empleado getEmpleado(@PathParam(value="cif") String cif) {
		return dao.getEmpleado(cif);
	}

	@DELETE
	@Path("/{cif}")
	@Produces(MediaType.APPLICATION_JSON)
	public void eliminaEmpleado(@PathParam(value="cif") String cif) {
		dao.eliminaEmpleado(cif);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void insertaEmpleado(Empleado emp) {
		dao.insertaEmpleado(emp);
	}

}
