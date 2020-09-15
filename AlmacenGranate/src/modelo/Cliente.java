package modelo;

public class Cliente extends Actor {
	
	private String apellido;
	private String nombre;
	private int dni;
	
	public Cliente(int id, Contacto contacto) {
		super(id, contacto);
		// TODO Auto-generated constructor stub
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	@Override
	public String toString() {
		return "Cliente [apellido=" + apellido + ", nombre=" + nombre + ", dni=" + dni + ", contacto=" + contacto + "]";
	}
	
	
}
