package modelo;

public class Cliente extends Actor {
	
	private String apellido;
	private String nombre;
	private Long dni;
	
	
	public Cliente(int id, Contacto contacto) {
		super(id, contacto);
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

	public long getDni() {
		return dni;
	}

	public void setDni(long dni) throws Exception {
		if(validarIdentificadorUnico(dni)) {
			
			this.dni = dni;
		}
		
	}

	@Override
	public String toString() {
		return "Cliente: Apellido=" + apellido + ", Nombre=" + nombre + ", DNI=" + dni + contacto +"\n";
	}

	protected boolean validarIdentificadorUnico(long identificador) throws Exception{
		
		if (identificador<99999999 && identificador>999999) {
			
			return true;
		}
		
		throw new Exception("Dni no valido!!");
		
	}
	

	
	
		
	
}
