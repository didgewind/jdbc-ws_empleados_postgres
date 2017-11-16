package jdbc.empleados.ws.server;

import javax.xml.ws.Endpoint;

public class EmpleadosSoapPublisher {

	public static void main(String[] args) {
		System.out.println("Servicio publicado");
		 Endpoint.publish("http://localhost:8888/ws/empleados", new EmpleadosServiceImpl());  
	}

}