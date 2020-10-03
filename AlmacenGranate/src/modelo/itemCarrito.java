package modelo;

public class itemCarrito {

	private Articulo articulo;
	private int cantidad;
	public itemCarrito(Articulo articulo, int cantidad) {
		super();
		this.articulo = articulo;
		this.cantidad = cantidad;
	}
	public Articulo getArticulo() {
		return articulo;
	}
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	@Override
	public String toString() {
		return "\nitem: " + articulo + " cantidad= " + cantidad+ "\n";
	}
	
	
	
}
