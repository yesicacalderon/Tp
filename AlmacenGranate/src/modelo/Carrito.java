package modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
	public Carrito(int id, LocalDate fecha, LocalTime hora, boolean cerrado, double descuento, Cliente cliente,
			List<itemCarrito> lstItemCarrito, Entrega entrega) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.cerrado = cerrado;
		this.descuento = descuento;
		this.cliente = cliente;
		this.lstItemCarrito = lstItemCarrito;
		this.entrega = entrega;
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
		return "Carrito id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", cerrado=" + cerrado + ", descuento="
				+ descuento + ", cliente=" + cliente + " Lista del Carrito= " + lstItemCarrito + ", entrega=" + entrega
				+ "\n";
	}
	public Double calcularSubtotalItem() throws Exception {
		//Corroboramos que hayan items en el carrito chequeando que la lista de items no esta vacia.
		if (this.lstItemCarrito.get(0) == null){
			throw new Exception("Error! El carrito se encuentra vacio");
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

	public Double calcularTotalCarrito(Envio envio) throws Exception {
		double subtotal = this.calcularSubtotalItem();
		double total = subtotal + envio.getCosto();

		return total;
	}
	
	public boolean agregar(Articulo articulo, int cantidad) {
		int sumar=0;
		int index=0;
		boolean agregado=false;
		while(index<this.lstItemCarrito.size()) {
			
			if(lstItemCarrito.get(index).getArticulo().equals(articulo)) {
				
				sumar=cantidad+lstItemCarrito.get(index).getCantidad();
				lstItemCarrito.get(index).setCantidad(sumar);
				agregado=true;
			}
			
			index++;
		}
		
		if(agregado==false) {
			
			this.lstItemCarrito.add(new itemCarrito(articulo, cantidad));
			agregado=true;
		}
		
		
		return true;
	}

}
