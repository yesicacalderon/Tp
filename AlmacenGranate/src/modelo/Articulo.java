package modelo;

public class Articulo {

	private int id;
	private String nombre;
	private String codBarras;
	private double precio;

	public Articulo(int id, String nombre, String codBarras, double precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.codBarras = codBarras;
		this.precio = precio;
	}
	

	public Articulo() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodBarras() {
		return codBarras;
	}

	public void setCodBarras(String codBarras) throws Exception {
		if (validarCodBarras(codBarras)) {
			this.codBarras = codBarras;
		}

	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	
	@Override
	public String toString() {
		return "Articulo: id: " + id + ", Nombre: " + nombre + ", Codigo de Barras: " + codBarras + ", Precio: $" + precio;
	}


	public boolean validarCodBarras(String codBarras) throws Exception {

		boolean resultado = false;
		try {
			Long.parseLong(codBarras);
			resultado = true;
		} catch (NumberFormatException excepcion) {
			throw new Exception("El codigo de barras debe contener digitos");
		}

		if (codBarras.length() != 13)
			throw new Exception("El codigo de barras tiene que contener 13 digitos");
		if (!codBarras.substring(0, 3).equals("779"))
			throw new Exception("El codigo de barras ingresado no es argentino");

		return resultado;
	}
	
	

}
