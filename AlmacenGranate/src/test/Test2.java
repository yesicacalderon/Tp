package test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import static java.time.temporal.ChronoUnit.MINUTES;

import modelo.Articulo;
import modelo.Cliente;
import modelo.Comercio;
import modelo.Contacto;
import modelo.DiaRetiro;
import modelo.Envio;
import modelo.Entrega;
import modelo.Ubicacion;
import modelo.itemCarrito;
import modelo.Carrito;
import modelo.Turno;

public class Test2 {

	public static void main(String[] args) throws Exception {
		//Crear el Comercio
		Ubicacion ubicacionComercio = new Ubicacion(-34.7667, -58.5659);
		Contacto contactoComercio = new Contacto("hola@almacengranate.com", "1142441122", ubicacionComercio);
		Comercio comercio = new Comercio(1, contactoComercio, "Almacen Granate", 27351580289l, 200, 30);
		comercio.agregarDiaRetiro(1, LocalTime.of(9, 00), LocalTime.of(10, 00), 15);
		/* Prueba agregar un diaRetiro existente */
		//comercio.agregarDiaRetiro(1, LocalTime.of(9, 00), LocalTime.of(10, 00), 60);
		comercio.agregarDiaRetiro(3, LocalTime.of(12, 00), LocalTime.of(13, 00), 15);
		comercio.agregarDiaRetiro(5, LocalTime.of(15, 00), LocalTime.of(16, 00), 15);
		System.out.println(comercio.getLstDiaRetiro());

		System.out.println("***Turnos disponibles para el dia Lunes***");
		ArrayList<Turno> listaDeTurnos = comercio.generarTurnosLibres(LocalDate.of(2020, 10, 12));

		int indexTurno = 0;
		while (indexTurno < listaDeTurnos.size()) {
			System.out.println(listaDeTurnos.get(indexTurno));
			indexTurno++;
		}

		//Crear los Clientes
		Ubicacion ubicacionContacto1 = new Ubicacion(-34.7937, -58.4291);
		Contacto contactoCliente1 = new Contacto("emivargas1998@gmail.com", "1111111111", ubicacionContacto1);
		Cliente cliente1= new Cliente(1, contactoCliente1, "Emiliano", "Vargas", 12345678, 'm');
		System.out.println(cliente1);

		//Crear los Articulos
		System.out.println("***Articulos en Gondola***");
		Articulo articulo1=new Articulo(1, "Arroz", "7790325647012", 40);
		System.out.println(articulo1);
		//Segundo articulo
		Articulo articulo2=new Articulo(2, "Lata de Atún", "7790325564033", 70);
		System.out.println(articulo2);
		//Tercer articulo
		Articulo articulo3=new Articulo(3, "Cerveza", "7790132464024", 130);
		System.out.println(articulo3);
		System.out.println("******\n");

		//Cliente "agarra" los Articulo
		System.out.println("***Eligiendo articulos...***");
		List<itemCarrito>lstItemCarrito = new ArrayList <itemCarrito>();
		lstItemCarrito.add(new itemCarrito(articulo1, 2));
		lstItemCarrito.add(new itemCarrito(articulo2, 3));
		//Mostramos contenido de la lista
		System.out.println("***El usuario eligió***");
		int index = 0;
		while (index < lstItemCarrito.size()) {
			System.out.println(lstItemCarrito.get(index));
			index++;
		}

		//Generamos Carrito para Cliente
		Carrito carritoCliente1 = new Carrito(1, LocalDate.now(), LocalTime.now(), false, cliente1, lstItemCarrito);

		//Elegir Entrega

		//Clientes cierran Carrito (Porque decidió que no va a comprar más) y se le calculan los descuentos

		//Calcular descuentos
	}
}
