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
	private List<itemCarrito> lstItemCarrito;
	private Entrega entrega;
	public Carrito(int id, LocalDate fecha, LocalTime hora, boolean cerrado, Cliente cliente,
			List<itemCarrito> lstItemCarrito) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.cerrado = cerrado;
		this.cliente = cliente;
		this.lstItemCarrito = lstItemCarrito;
	}

	//Constructor Carrito sin implementacion de entrega.
	public Carrito(int id, LocalDate fecha, LocalTime hora, boolean cerrado, double descuento, Cliente cliente,
			List<itemCarrito> lstItemCarrito) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.cerrado = cerrado;
		this.descuento = descuento;
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
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<itemCarrito> getLstItemCarrito() {
		return lstItemCarrito;
	}
	public void setLstItemCarrito(List<itemCarrito> lstItemCarrito) {
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

	//El total de todos los articulos agregados al carro. Sin descuesnto alguno.
	public Double calcularSubtotal() throws Exception {
		//Corroboramos que hayan items en el carrito chequeando que la lista de items no esté vacia.
		if (this.lstItemCarrito.get(0) == null){
			throw new Exception("Error! El carrito está vacio");
		}
		//Sumamos los precios
		int index = 0;
		double subtotal = 0;
		while (index < this.lstItemCarrito.size()) {
			subtotal = subtotal + this.lstItemCarrito.get(index).getArticulo().getPrecio() * this.lstItemCarrito.get(index).getCantidad();
			index++;
		}

		return subtotal;
	}

	//Calcular total: Subtotal + Descuentos del dia o si paga en efectivo + Costo del envio. RECIBE: Envio y carrito.
	public Double calcularTotalCarrito(Envio envio) throws Exception {
		
		double subtotal = this.calcularSubtotal();
		//Convertir fecha del carrito a numero.
		Integer diaSemana = this.fecha.getDayOfWeek().getValue();
		//Convertir fecha del descuento a numero.
		Integer diaDescuento = 3;
		Double descuento = 0.0;

		if (diaSemana == diaDescuento) {
			descuento = subtotal * 0.20; //Pedir el valor de Comercio.porcentajeDescuentoDia

		}
		else if (entrega.efectivo == true) {
			descuento = subtotal * 0.10; //Pedir el valor de porcentajeDescuentoEfectivo
		}
		
		return subtotal - descuento + envio.getCosto();
	}
		
}
