package test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import modelo.Articulo;
import modelo.Cliente;
import modelo.Comercio;
import modelo.Contacto;
import modelo.DiaRetiro;
import modelo.Envio;
import modelo.Entrega;
import modelo.Ubicacion;
import modelo.ItemCarrito;
import modelo.RetiroLocal;
import modelo.Carrito;

public class Test {

	 
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		 Carrito carrito1;
		
		RetiroLocal retiroLocal = new RetiroLocal(1,  LocalDate.now(), false, LocalTime.now());
		

		//Crear Contacto y su ubicación.
		Ubicacion ubicacion=new Ubicacion(-34.7667, -58.5659);
		Envio envio= new Envio(2,LocalDate.now(),true,LocalTime.of(18,00),LocalTime.of(9,00),ubicacion);
		Contacto contact1=new Contacto("bj_000@hotmail.com", "1522613210" , ubicacion);

		//Crear Cliente con el contacto creado.		
		Cliente cliente1= new Cliente(1, contact1);		
		cliente1.setNombre("Yesica");
		cliente1.setApellido("Calderon");
		cliente1.setDni(35158028);
		System.out.println("****Datos del Cliente 1****");
		System.out.println(cliente1);
		
		Cliente cliente2= new Cliente(2, contact1, "Emiliano", "Vargas", 12345678, true);
		System.out.println("****Datos del Cliente 2****");
		System.out.println(cliente2);

		//Creamos comercio (Id, Contacto null)
		Comercio comercio1=new Comercio(1,null);
		comercio1.setCuit(27351580289l);
		comercio1.setDiaDescuento(2);
		comercio1.setPorcentajeDescuentoDia(20);
		comercio1.setPorcentajeDescuentoEfectivo(10);
		comercio1.setCostoFijo(400);
		comercio1.setCostoPorKm(100);

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
		Articulo articulo2=new Articulo(2, "Lata de Atun", "7790325564033", 70);
		System.out.println(articulo2);
		//Tercer articulo
		Articulo articulo3=new Articulo(3, "Cerveza", "7790132464024", 130);
		System.out.println(articulo3);
		System.out.println("******\n");

		//Agregamos 2 de cada item creando itemCarrito (Esto seria como tenerlos en la mano, ahora hay que meterlos adentro del carrito)
		//Creamos una List de itemCarritos.
		System.out.println("***Articulos en itemCarrito***");
		List<ItemCarrito>lstItemCarrito = new ArrayList <ItemCarrito>();
		List<ItemCarrito>lstItemCarrito1 = new ArrayList <ItemCarrito>();
		lstItemCarrito.add(new ItemCarrito(articulo1, 2));
		lstItemCarrito.add(new ItemCarrito(articulo2, 3));
		lstItemCarrito.add(new ItemCarrito(articulo3, 12));
		lstItemCarrito1.add(new ItemCarrito(articulo2,3));
		lstItemCarrito1.add(new ItemCarrito(articulo3,3));
	
		//Creamos carrito con la lista de items
		Carrito carrito = new Carrito(1, LocalDate.now(), LocalTime.now(), false, cliente1, lstItemCarrito,retiroLocal);
		Carrito carrito2=new Carrito(2,LocalDate.now(),LocalTime.now(),false,cliente2,lstItemCarrito1,envio);
		//toString de Carrito devuelve "Hay N items en el carrito" donde N es el size() de la lista de itemCarrito
		System.out.println(carrito);
		
		//Mostramos contenido de la lista
		int index = 0;
		while (index < lstItemCarrito.size()) {
			System.out.println(lstItemCarrito.get(index)+" .........Subtotal item: $" +carrito.subtotalItem(lstItemCarrito.get(index)));
			//System.out.println("Articulo : " + lstItemCarrito.get(index).getArticulo().getNombre()+));
			
			System.out.println();
 
			
			index++;
		}

		//Subtotal del carrito
		System.out.println("\n*** calcularTotalCarrito ***");
		System.out.println("$"+carrito.calcularTotalCarrito()+"\n");
		//descuento
		
		System.out.println("******\n");
		
		System.out.println("Descuento: $"+ carrito.calcularDescuentoCarrito(comercio1.getDiaDescuento(), comercio1.getPorcentajeDescuentoDia(), comercio1.getPorcentajeDescuentoEfectivo()));
		
		System.out.println("\n******\n");
		
		//total a pagar- retira en local
		System.out.println("Total a pagar: $"+carrito.totalAPagarCarrito());
		
		
		//cliente2
		System.out.println("\n******Cliente con envio*******\n");
		System.out.println(carrito2);
		System.out.println("\n*****item del carrito******\n");
		int index1=0;
		while (index1 < lstItemCarrito1.size()) {
			System.out.println(lstItemCarrito1.get(index1)+" .........Subtotal item: $" +carrito.subtotalItem(lstItemCarrito1.get(index1)));
			 
			index1++;
		}
		
		System.out.println("\n*** calcularTotalCarrito ***");
		System.out.println("$"+carrito2.calcularTotalCarrito()+"\n");
        System.out.println("******\n");
		
		System.out.println("Descuento: $"+ carrito2.calcularDescuentoCarrito(comercio1.getDiaDescuento(), comercio1.getPorcentajeDescuentoDia(), comercio1.getPorcentajeDescuentoEfectivo()));
		

		System.out.println("\n******\n");
		System.out.println("***Total a pagar Carrito: $"+carrito2.totalAPagarCarrito());
		envio.setCosto(ubicacion, comercio1.getCostoFijo(), comercio1.getCostoPorKm());
		System.out.println("\n***Costo de envio: $"+envio.getCosto());
		
		
		
			
	}
}
