package jdbc.empleados.ws.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import jdbc.empleados.model.Empleado;
import jdbc.empleados.ws.server.EmpleadosService;

/*
 * Cliente del ws publicado con el publisher básico del jdk
 * (clase jdbc.empleados.ws.server.EmpleadosSoapPublisher)
 * o desplegado en tomcat
 */
public class EmpleadosSoapClient {

	public static void main(String[] args) throws MalformedURLException {
		/* wsdl servidor jdk */
//		URL wsdlURL = new URL("http://localhost:8880/ws/empleados?wsdl");
		/* wsdl tomcat */
		URL wsdlURL = new URL("http://localhost:8080/empleados-ws/empleadosWS?wsdl");
		//check above URL in browser, you should see WSDL file
		
		//creating QName using targetNamespace and name
		QName qname = new QName("http://server.ws.empleados.jdbc/", "EmpleadosServiceImplService"); 
		
		Service service = Service.create(wsdlURL, qname);  
		
		//We need to pass interface and model beans to client
		EmpleadosService empleadosService = service.getPort(EmpleadosService.class);
		
//		listaEmpleados(empleadosService.getEmpleados());

		System.out.println("* Apellidos de un empleado:");
		System.out.println(empleadosService.getEmpleado("34334789H").getApellidos());

//		System.out.println("* Lista de empleados a partir de un set");
//		System.out.println(empleadosService.getAllEmpleados());
		
	}
	
	private static void listaEmpleados(Empleado[] empleados) {
		System.out.println("* Lista de empleados a partir de un array");
		for (Empleado emp : empleados) {
			System.out.println(emp);
		}
	}

}
