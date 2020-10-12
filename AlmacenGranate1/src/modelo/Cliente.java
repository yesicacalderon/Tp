package modelo;

public class Cliente extends Actor {
	
	private String apellido;
	private String nombre;
	private Long dni;
	private Boolean sexo; // false: Femenino, true: Masculino
	
	public Cliente(int id, Contacto contacto) {
		super(id, contacto);
	}

	public Cliente(int id, Contacto contacto, String nombre, String apellido, int dni, Boolean sexo) throws Exception{
		super(id, contacto);
		this.nombre = nombre;
		this.apellido = apellido;
		setDni(dni);
		this.sexo = sexo;
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

	public Boolean getSexo(){
		return this.sexo;
	}

	public void setSexo(Boolean sexo){
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return "Cliente: Apellido=" + this.apellido + ", Nombre=" + this.nombre + ", DNI=" + this.dni + this.contacto;
	}

	protected boolean validarIdentificadorUnico(long identificador) throws Exception{		
		if (identificador<99999999 && identificador>999999) {
			
			return true;
		}		
		throw new Exception("Dni no valido!!");		
	}
}
