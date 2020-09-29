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
import modelo.Ubicacion;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Ubicacion ubicacion=new Ubicacion(-34.7667, -58.5659);

		
		Contacto contact1=new Contacto("bj_000@hotmail.com", "1522613210" , ubicacion);
		Cliente cliente1= new Cliente(1, contact1);		
		cliente1.setNombre("Yesica");
		cliente1.setApellido("Calderon");
		cliente1.setDni(35158028);		
		
		DiaRetiro retiro1=new DiaRetiro(1,4,LocalTime.of(9, 00),LocalTime.of(16, 00),15);
		Comercio comercio1=new Comercio(12,null);
		comercio1.setCuit(27351580289l);
		List<DiaRetiro>lstDiaRetiro= new ArrayList <DiaRetiro>();
		lstDiaRetiro.add(retiro1);
		
		comercio1.setLstDiaRetiro(lstDiaRetiro);
		try {
			comercio1.agregarDiaRetiro(4,LocalTime.of(10, 00),LocalTime.of(16, 00),15);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Lista dia de retiro "+comercio1.getLstDiaRetiro());
		
		Articulo articulo1=new Articulo();
		articulo1.setId(2);
		articulo1.setNombre("Arroz");
		articulo1.setCodBarras("7790325647012");
		articulo1.setPrecio(40);
		System.out.println(articulo1);
		
		Ubicacion ubicacion1=new Ubicacion(-34.7667, -58.5659);
		Ubicacion ubicacion2=new Ubicacion(-34.7133, -58.3711);


		Envio envio1=new Envio(1, LocalDate.now(), false, LocalTime.now(), LocalTime.now(), ubicacion1);
		
		envio1.setCosto(ubicacion2, 400, 30);
		System.out.println("El costo de envio es de $"+Math.round(envio1.getCosto()));//realizo redondeo
		
	}

}
