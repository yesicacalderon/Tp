package test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import modelo.Articulo;
import modelo.Carrito;
import modelo.Cliente;
import modelo.Comercio;
import modelo.Contacto;
import modelo.DiaRetiro;
import modelo.Envio;
import modelo.Ubicacion;
import modelo.itemCarrito;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//Crear Contacto y su ubicación.
				Ubicacion ubicacion=new Ubicacion(-34.7667, -58.5659);
				Contacto contact1=new Contacto("bj_000@hotmail.com", "1522613210" , ubicacion);

				//Crear Cliente con el contacto creado.		
				Cliente cliente1= new Cliente(1, contact1);		
				cliente1.setNombre("Yesica");
				cliente1.setApellido("Calderon");
				cliente1.setDni(35158028);
				System.out.println("****Datos del Cliente 1****");
				System.out.println(cliente1);

				//Creamos comercio (Id, Contacto null)
				Comercio comercio1=new Comercio(1,null);
				comercio1.setCuit(27351580289l);

				/* RETIROS */
				//Creamos objeto del dia de retiro. (id, Jueves, LocalTime Hora desde 9am, LocalTime hora hasta 4pm, intervalo 15).		
				DiaRetiro retiro1=new DiaRetiro(1,4,LocalTime.of(9, 00),LocalTime.of(16, 00),15);
				//Creamos lista de dias de retiro.
				List<DiaRetiro>lstDiaRetiro= new ArrayList <DiaRetiro>();
				lstDiaRetiro.add(retiro1);

				//Verificamos con un bloque try&catch que el turno esté disponible y no exista en el comercio.
				comercio1.setLstDiaRetiro(lstDiaRetiro);
				try {
					comercio1.agregarDiaRetiro(4,LocalTime.of(10, 00),LocalTime.of(16, 00),15);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Lista dia de retiro: " + comercio1.getLstDiaRetiro());
				
				//Creamos Articulos
				System.out.println("***Articulos en Gondola***");
				Articulo articulo1=new Articulo();
				articulo1.setId(1);
				articulo1.setNombre("Arroz");
				articulo1.setCodBarras("7790325647012");
				articulo1.setPrecio(40);
				System.out.println(articulo1);
				//Segundo articulo
				Articulo articulo2=new Articulo(2, "Lata de Atún", "7790325564033", 70);
				System.out.println(articulo2);
				//Tercer articulo
				Articulo articulo3=new Articulo(3, "Cerveza", "7790132464024", 130);
				System.out.println(articulo3);
				System.out.println("******\n");

				//Agregamos 2 de cada item creando itemCarrito (Esto seria como tenerlos en la mano, ahora hay que meterlos adentro del carrito)
				//Creamos una List de itemCarritos.
				System.out.println("***Articulos en itemCarrito***");
				List<itemCarrito>lstItemCarrito = new ArrayList <itemCarrito>();
				lstItemCarrito.add(new itemCarrito(articulo1, 2));
				lstItemCarrito.add(new itemCarrito(articulo2, 3));
				//Mostramos contenido de la lista
				System.out.println(lstItemCarrito.get(0));
				System.out.println(lstItemCarrito.get(1));
				System.out.println("******\n");

				//Creamos carrito con la lista de items
				Carrito carrito1 = new Carrito(1, LocalDate.now(), LocalTime.now(), true, 0, cliente1, lstItemCarrito,null);
				List<Carrito> listaCarrito=new ArrayList<Carrito>();
				listaCarrito.add(carrito1);
				comercio1.setLstCarrito(listaCarrito);
				//toString de Carrito devuelve "Hay N items en el carrito" donde N es el size() de la lista de itemCarrito
				System.out.println(carrito1);

				carrito1.agregar(articulo2, 3);
				System.out.println("Agregado*****\n");
				System.out.println(carrito1);
				//Subtotal del carrito
				System.out.println("\n*** Subtotal ***");
				System.out.println("$"+carrito1.calcularSubtotalItem()+"\n");
				
				//Creamos ubicaciones de origen y destino de envio.
				Ubicacion ubicacion1=new Ubicacion(-34.7667, -58.5659);
				Ubicacion ubicacion2=new Ubicacion(-34.7133, -58.3711);

				//Creamos el envio y le cargamos el origen y destino creados.
				Envio envio1=new Envio(1, LocalDate.now(), false, LocalTime.now(), LocalTime.now(), ubicacion1);		

				envio1.setCosto(ubicacion2, 400, 30);
				System.out.println(envio1);		

				//Calculamos el total del carrito con envio.
				System.out.println("\n*** Total Carrito + Envio: $" + Math.round(carrito1.calcularTotalCarrito(envio1)));
				
			}



}
