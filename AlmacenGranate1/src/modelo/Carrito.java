package modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Carrito {

	private int id;
	private LocalDate fecha;
	private LocalTime hora;
	private boolean cerrado;
	private double descuento;
	private Cliente cliente;
	private List<ItemCarrito> lstItemCarrito;
	private Entrega entrega;
	
	private LocalDate fechaActual= LocalDate.now();
	public Carrito(int id, LocalDate fecha, LocalTime hora, boolean cerrado, Cliente cliente,
			List<ItemCarrito> lstItemCarrito, Entrega entrega) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.cerrado = cerrado;
		this.cliente = cliente;
		this.lstItemCarrito = lstItemCarrito;
		this.entrega = entrega;
	}

	// Constructor Carrito sin implementacion de entrega.
	public Carrito(int id, LocalDate fecha, LocalTime hora, boolean cerrado, Cliente cliente,
			List<ItemCarrito> lstItemCarrito) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.cerrado = cerrado;
		this.cliente = cliente;
		this.lstItemCarrito = lstItemCarrito;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public boolean isCerrado() {
		return cerrado;
	}

	public void setCerrado(boolean cerrado) {
		this.cerrado = cerrado;
	}

	public double getDescuento() {
		return descuento;
	}

	protected void setDescuento(double descuento) {

		this.descuento = descuento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemCarrito> getLstItemCarrito() {
		return lstItemCarrito;
	}

	public void setLstItemCarrito(List<ItemCarrito> lstItemCarrito) {
		this.lstItemCarrito = lstItemCarrito;
	}

	public Entrega getEntrega() {
		return entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}

	@Override
	public String toString() {
		return "Carrito: Hay " + lstItemCarrito.size() + " items en el carrito.";
	}

	public double calcularTotalCarrito() throws Exception {

		if (this.lstItemCarrito.get(0) == null) {
			throw new Exception("Error! El carrito esta vacio");
		}

		int index = 0;
		double total = 0;
		while (index < this.lstItemCarrito.size()) {

			total = total+subtotalItem(this.lstItemCarrito.get(index));
			index++;
		}

		return total;
	}

	// Calcula el subtotal de cada item-- cantidad * precio
	public double subtotalItem(ItemCarrito item) {
		double subtotalItem=0;
		try {
			subtotalItem=item.getCantidad() * item.getArticulo().getPrecio();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subtotalItem;
	}

//	//Calcular total: Subtotal + Descuentos del dia o si paga en efectivo + Costo del envio. RECIBE: Envio y carrito.
//	public Double calcularTotalCarrito(Envio envio) throws Exception {
//		
//		double subtotal = this.calcularSubtotal();
//		/*
//		Convertir fecha del carrito a numero.
//		DayOfWeek diaSemana = this.fecha.getDayOfWeek();
//		Convertir fecha del descuento a numero.
//		DayOfWeek diaDescuento = DayOfWeek 3;
//		System.out.println(numero.getValue());
//		*/
//		Integer diaSemana = 2; //Fecha Martes
//		Integer diaDescuento = 3; //Descuento Miercoles
//
//		if (diaSemana == diaDescuento) {
//			Double descuento = subtotal * 0.20; //Pedir el valor de Comercio.porcentajeDescuentoDia
//			return subtotal - descuento + envio.getCosto();
//		}
//		else (entrega.efectivo) {
//			Double descuento = subtotal * 0.10; //Pedir el valor de porcentajeDescuentoEfectivo
//			return subtotal - descuento + envio.getCosto(); //Pedir el valor de Entrega.efectivo
//		}
//		
//		return subtotal + envio.getCosto();
//	}

	public boolean cantidadRepetida(ItemCarrito item) {

		return item.getCantidad() > 1 ? true : false;
	}

	public double calcularDescuentoDia(int diaDescuento, int porcentajeDescuentoDia) {
		int index = 0;
		double totalDescuento = 0;
		
		int diaSemana=this.fechaActual.getDayOfWeek().getValue();
		if(diaSemana==diaDescuento) {
		while (index < this.lstItemCarrito.size()) {
			double descuentoArticulo = 0;
			int cantidadArtRepetido = 0;
			
			if (cantidadRepetida(this.lstItemCarrito.get(index))) {

				cantidadArtRepetido = this.lstItemCarrito.get(index).getCantidad() / 2;
				descuentoArticulo = cantidadArtRepetido * this.lstItemCarrito.get(index).getArticulo().getPrecio()
						* porcentajeDescuentoDia / 100;
			}

			totalDescuento = totalDescuento + descuentoArticulo;
			index++;
		}
		}

		return totalDescuento;
	}

	public double calcularDescuentoEfectivo(int porcentajeDescuentoEfectivo) {
		double descuento = 0;

		if (this.entrega.efectivo)
			try {
				descuento = calcularTotalCarrito() * porcentajeDescuentoEfectivo / 100;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return descuento;

	}

	public double calcularDescuentoCarrito(int diaDescuento, int porcentajeDescuento,
			int porcentajeDescuentoEfectivo) {
		double descuentoMayor = 0;

		double descuentoEfectivo = calcularDescuentoEfectivo(porcentajeDescuentoEfectivo);
		double descuentoArticulo = calcularDescuentoDia(diaDescuento, porcentajeDescuento);

		if (descuentoEfectivo > descuentoArticulo) {

			descuentoMayor = descuentoEfectivo;
		} else if (descuentoEfectivo < descuentoArticulo) {
			descuentoMayor = descuentoArticulo;

		} else {

			descuentoMayor = descuentoEfectivo;

		}

		// seteo el descuento
		this.setDescuento(descuentoMayor);
		return descuentoMayor;
	}
	
	public double totalAPagarCarrito() {
		double total=0;
		
			
			try {
				total=calcularTotalCarrito()-this.descuento;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return total;
	}
}
