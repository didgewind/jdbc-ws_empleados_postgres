/**
 * 
 */
package jdbc.empleados.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.dbcp2.BasicDataSource;

import jdbc.empleados.model.Empleado;

/**
 * Clase DAO que ataca la base de datos
 * @author Administrador
 *
 */
public class DAOEmpBd implements DAOEmpleados {

	private BasicDataSource ds;

	private interface ConstantesSQL {
		
		String URL = "jdbc:postgresql://localhost/empleados_simple";
		String SELECT_EMPLEADOS = "SELECT * FROM empleados";
		String SELECT_EMPLEADO = "SELECT * FROM empleados WHERE cif=?";
		String DELETE_EMPLEADO = "DELETE FROM empleados WHERE cif=?";
		String INSERTA_EMPLEADO = "INSERT INTO empleados (cif, nombre, apellidos, edad) VALUES(?, ?, ?, ?)";
		
	}
	
	public static void main(String[] args) {
		DAOEmpBd dao = new DAOEmpBd();
		System.out.println(dao.getEmpleados());
	}
	

	/* *** Connection a través de driver *** */
	
/*	public DAOEmpBd() {
		try	{
			Class.forName("org.postgresql.Driver");
	  	} catch (Exception e) {
	  		System.out.println(e);
	  		e.printStackTrace(System.out);
	  	}
	}
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(ConstantesSQL.URL, "postgres", "a");
	}*/
	
	/* *** Connection a través de ds *** */
	
	public DAOEmpBd() {
		try	{
			ds = new BasicDataSource();
	        ds.setDriverClassName("org.postgresql.Driver");
	        ds.setUsername("postgres");
	        ds.setPassword("a");
	        ds.setUrl(ConstantesSQL.URL);
	  	} catch (Exception e) {
	  		System.out.println(e);
	  		e.printStackTrace(System.out);
	  	}
	}
	
	private Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	
	/* (non-Javadoc)
	 * @see profe.daos.DAOEmpleados#getEmpleados()
	 */
	@Override
	public Set<Empleado> getEmpleados() {
		Set<Empleado> sReturn = new HashSet<Empleado>();
		Connection con = null;
		try {
			con = this.getConnection();
			// Crear statement
			Statement st = con.createStatement();
			// Ejecutar statement
			ResultSet rs = st.executeQuery(ConstantesSQL.SELECT_EMPLEADOS);
			 while (rs.next()) {
				// Añadir empleado al conjunto
				 sReturn.add(rellenaEmpleado(rs));
			 }
		} catch (SQLException e) {
			System.out.println("error al recuperar los empleados");
			e.printStackTrace();
			throw new EmpDAOException(e);
		} finally {
			cierraConexion(con);
		}
		return sReturn;
	}

	private Empleado rellenaEmpleado(ResultSet rs) throws SQLException {
		// Crear empleado a partir de campos del registro
		 String cif = rs.getString("cif");
		 String nombre = rs.getString("nombre");
		 String apellidos = rs.getString("apellidos");
		 int edad = rs.getInt("edad");
		 return new Empleado(cif, nombre, apellidos, edad);
	}
	
	/* (non-Javadoc)
	 * @see profe.daos.DAOEmpleados#insertaEmpleado(profe.model.Empleado)
	 */
	@Override
	public boolean insertaEmpleado(Empleado emp) {
		boolean bReturn = true;
		Connection con = null;
		try {
			con = this.getConnection();
			// Crear statement
			PreparedStatement pst = con.prepareStatement(ConstantesSQL.INSERTA_EMPLEADO);
			pst.setString(1, emp.getCif());
			pst.setString(2, emp.getNombre());
			pst.setString(3, emp.getApellidos());
			pst.setInt(4, emp.getEdad());
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error al insertar el empleado con cif " + emp.getCif());
			e.printStackTrace();
			throw new EmpDAOException(e);
		} finally {
			cierraConexion(con);
		}
		return bReturn;
	}

	/* (non-Javadoc)
	 * @see profe.daos.DAOEmpleados#eliminaEmpleado(java.lang.String)
	 */
	@Override
	public boolean eliminaEmpleado(String cif) {
		boolean bReturn = true;
		Connection con = null;
		try {
			con = this.getConnection();
			// Crear statement
			PreparedStatement pst = con.prepareStatement(ConstantesSQL.DELETE_EMPLEADO);
			pst.setString(1, cif);
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error al eliminar el empleado con cif " + cif);
			e.printStackTrace();
			throw new EmpDAOException(e);
		} finally {
			cierraConexion(con);
		}
		return bReturn;
	}

	@Override
	public Empleado getEmpleado(String cif) {
		Empleado eReturn = null;
		Connection con = null;
		try {
			con = this.getConnection();
			// Crear statement
			PreparedStatement pst = con.prepareStatement(ConstantesSQL.SELECT_EMPLEADO);
			pst.setString(1, cif);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				eReturn = rellenaEmpleado(rs);
			}
		} catch (SQLException e) {
			System.out.println("error al recuperar el empleado con cif " + cif);
			e.printStackTrace();
			throw new EmpDAOException(e);
		} finally {
			cierraConexion(con);
		}
		return eReturn;
	}
	
	private void cierraConexion(Connection con) {
		if (con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("error al cerrar la conexión");
			}
		}
	}

}
