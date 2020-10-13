package modelo;

import static java.time.temporal.ChronoUnit.MINUTES;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Comercio extends Actor {

	private String nombreComercio;
	private long cuit;
	private double costoFijo;
	private double costoPorKm;
	private int diaDescuento = 3;
	private int porcentajeDescuentoDia = 20;
	private int porcentajeDescuentoEfectivo = 10;
	private List<DiaRetiro> lstDiaRetiro;
	private List<Carrito> lstCarrito;

	private List<Turno> lstTurno = new ArrayList<Turno>();

	private static String TIPO_MASCULINO = "20";
	private static String TIPO_FEMENINO = "27";
	private static String TIPO_EMPRESA = "30";

	private static Integer[] SERIE_VALORES = { 5, 4, 3, 2, 7, 6, 5, 4, 3, 2 };

	public Comercio(int id, Contacto contacto, String nombreComercio, long cuit, double costoFijo, double costoPorKm,
			List<DiaRetiro> lstDiaRetiro, List<Carrito> lstCarrito) {
		super(id, contacto);
		this.nombreComercio = nombreComercio;
		this.cuit = cuit;
		this.costoFijo = costoFijo;
		this.costoPorKm = costoPorKm;
		this.lstDiaRetiro = lstDiaRetiro;
		this.lstCarrito = lstCarrito;
	}

	public Comercio(int id, Contacto contacto, long cuit) {
		super(id, contacto);
		this.cuit = cuit;
	}

	public Comercio(int id, Contacto contacto) {
		super(id, contacto);
	}

	public String getNombreComercio() {
		return nombreComercio;
	}

	public void setNombreComercio(String nombreComercio) {
		this.nombreComercio = nombreComercio;
	}

	public long getCuit() {
		return cuit;
	}

	public void setCuit(long cuit) throws Exception {
		if (validarIdentificadorUnico(cuit)) {

			this.cuit = cuit;
		}
	}

	public double getCostoFijo() {
		return costoFijo;
	}

	public void setCostoFijo(double costoFijo) {
		this.costoFijo = costoFijo;
	}

	public double getCostoPorKm() {
		return costoPorKm;
	}

	public void setCostoPorKm(double costoPorKm) {
		this.costoPorKm = costoPorKm;
	}

	public int getDiaDescuento() {
		return diaDescuento;
	}

	public void setDiaDescuento(int diaDescuento) {
		this.diaDescuento = diaDescuento;
	}

	public int getPorcentajeDescuentoDia() {
		return porcentajeDescuentoDia;
	}

	public void setPorcentajeDescuentoDia(int porcentajeDescuentoDia) {
		this.porcentajeDescuentoDia = porcentajeDescuentoDia;
	}

	public int getPorcentajeDescuentoEfectivo() {
		return porcentajeDescuentoEfectivo;
	}

	public void setPorcentajeDescuentoEfectivo(int porcentajeDescuentoEfectivo) {
		this.porcentajeDescuentoEfectivo = porcentajeDescuentoEfectivo;
	}

	public List<DiaRetiro> getLstDiaRetiro() {
		return lstDiaRetiro;
	}

	public void setLstDiaRetiro(List<DiaRetiro> lstDiaRetiro) {
		this.lstDiaRetiro = lstDiaRetiro;
	}

	public List<Carrito> getLstCarrito() {
		return lstCarrito;
	}

	public void setLstCarrito(List<Carrito> lstCarrito) {
		this.lstCarrito = lstCarrito;
	}

	protected boolean validarIdentificadorUnico(long identificador) throws Exception {
		boolean valido = false;

		String XY;

		int contador = 0;
		int resto = 0;
		int resultado = 0;
		int z = 0;

		String miCuit = converterLongToString(identificador);

		if (!lenghtCuit(miCuit))
			throw new Exception("La longitud del cuit debe ser 11");

		if (!tipoXY(miCuit))
			throw new Exception("Los dos primeros numeros del cuit no son validos");

		try {
			while (contador < miCuit.length() - 1) {
				resultado += Integer.parseInt(miCuit.substring(contador, contador + 1)) * SERIE_VALORES[contador];

				contador++;

			}
		} catch (Exception e) {
			throw new Exception("Ocurrio un error al multiplicar el cuit con la serie de valores");
		}

		resto = resultado % 11;

		if (resto == 0) {// si el r es 0, entonces z=0
			z = 0;
		} else if (resto == 1) {// si el resto es 1, se aplica la siguiente regla:
			if (esMasculino(miCuit.substring(0, 2))) {

				z = 9;
				XY = "23";
			} else {

				z = 4;
				XY = "23";

			}

		} else {
			z = 11 - resto;
			if (esMasculino(miCuit.substring(0, 2))) {// Si es mujer: z=4 y xy pasa a ser 23

				XY = "20";
			} else {

				XY = "27";
			}
		}

		if (Integer.parseInt(miCuit.substring(10, miCuit.length())) == z) {

			valido = true;

		} else
			throw new Exception("El cuit ingresado es invalido");

		return valido;
	}

	public boolean agregarDiaRetiro(int diaSemana, LocalTime horaDesde, LocalTime horaHasta, int intervalo)
			throws Exception {
		boolean agregado = false;
		int index = 0;
		int id = 0;
		// Consulta si hay una lista en Comercio. Si no hay crea el primer item.
		if (this.lstDiaRetiro == null) {
			this.lstDiaRetiro = new ArrayList<DiaRetiro>();
			this.lstDiaRetiro.add(new DiaRetiro(id, diaSemana, horaDesde, horaHasta, intervalo));
			// generarTurno(dia, hora, ocupado, new DiaRetiro(id, diaSemana, horaDesde,
			// horaHasta, intervalo));
			agregado = true;
		}
		// Buscamos si el dia de retiro recibido en los parametros ya se encuentran en
		// lstDiaRetiro haciendo un while y validando uno por uno.
		while (index < this.lstDiaRetiro.size() && agregado == false) {
			if (this.lstDiaRetiro.get(index).getHoraDesde().equals(horaDesde)
					&& this.lstDiaRetiro.get(index).getHoraHasta().equals(horaHasta)
					&& this.lstDiaRetiro.get(index).getDiaSemana() == diaSemana)
				throw new Exception("Error! Dia de la semana y hora no disponibles");
			index++;
		}
		// Verificamos el ultimo id agregado y le sumamos +1.
		if (agregado == false) {
			id = lstDiaRetiro.get(lstDiaRetiro.size() - 1).getId() + 1;
			this.lstDiaRetiro.add(new DiaRetiro(id, diaSemana, horaDesde, horaHasta, intervalo));
			agregado = true;
		}
		return agregado;
	}

	@Override
	public String toString() {
		return "Comercio: Nombre del comercio=" + nombreComercio + ", Cuit=" + cuit + ", Costo fijo=" + costoFijo
				+ ", Costo Por Km=" + costoPorKm + ", Dia descuento=" + diaDescuento + ", Porcentaje Descuento Dia="
				+ porcentajeDescuentoDia + ", Porcentaje Descuento Efectivo=" + porcentajeDescuentoEfectivo + " "
				+ lstDiaRetiro + "Lista de Carrito=" + lstCarrito;
	}

	public String converterLongToString(long data) throws Exception {
		String dato;
		try {
			dato = String.valueOf(data);
		} catch (Exception e) {
			throw new Exception("Hubo un error al convertir");
		}
		return dato;
	}

	public boolean lenghtCuit(String cuit) {

		return cuit.length() == 11 ? true : false;

	}

	public boolean tipoXY(String cuit) {

		String tipoXY1 = cuit.substring(0, 2);

		return (tipoXY1.equalsIgnoreCase(TIPO_EMPRESA) || tipoXY1.equalsIgnoreCase(TIPO_FEMENINO)
				|| tipoXY1.equalsIgnoreCase(TIPO_MASCULINO)) ? true : false;
	}

	public boolean esMasculino(String tipo) {

		return (tipo.equalsIgnoreCase(TIPO_MASCULINO)) ? true : false;
	}

//	public ArrayList<LocalTime> traerHoraRetiro(LocalDate fecha){
//		//Leer lista de carritos y obtener los que les coincida la fecha.
//		ArrayList<LocalTime> lstHoraRetiro = new ArrayList<LocalTime>();
//		int index = 0;
//		while (index < this.lstCarrito.size()){
//			Carrito carrito = lstCarrito.get(index);
//			if (carrito.getFecha() == fecha) {
//				Entrega entrega = carrito.getEntrega();
//				RetiroLocal horaEntrega = (RetiroLocal entrega.getEntrega()).getHoraEntrega();
//
//				RetiroLocal sub = new subclass();
//				superclass sup = (superclass) sub; 
//				subclass theSub = (subclass) sup;
//				theSub.displaySub();
//			}
//			index ++;
//		}
//		//De estos carritos sacamos sus horas de Retiro (si tienen entrega, sino excep)
//
//		return lstHoraRetiro;
//	}

	public ArrayList<Turno> generarAgenda(LocalDate fecha) {
		// Generamos los turnos disponibles
		// Determinar la fecha qué dia cae. Si es martes, buscar en los diaRetiro de los
		// martes cuantos turnos entran.
		Integer dia = fecha.getDayOfWeek().getValue();
		// Creamos el ArrayList que contendrá los turnos que crearemos.
		ArrayList<Turno> lstAgenda = new ArrayList<Turno>();
		// Ver en lstDiaRetiro que dias hay con el dia sacado de arriba.
		int index = 0;
		while (index < this.lstDiaRetiro.size()) {
			DiaRetiro diaRetiro = this.lstDiaRetiro.get(index);
			LocalTime horaDesde = diaRetiro.getHoraDesde();
			if (diaRetiro.getDiaSemana() == dia) {
				// si coincide un dia de retiro con un item de la lista de carritos, entonces es
				// porque ese turno está ocupado.
				int indexCarrito = 0;
				while (indexCarrito < this.lstCarrito.size()) {
					Carrito carrito = this.lstCarrito.get(indexCarrito);
					if (carrito.getFecha().getDayOfWeek().getValue() == dia) {
						Turno turno = new Turno(fecha, carrito.getHora(), true);
						lstAgenda.add(turno);
					} else {
						Turno turno = new Turno(fecha, horaDesde, false);
						lstAgenda.add(turno);
						horaDesde = horaDesde.plusMinutes(diaRetiro.getIntervalo());
					}
					indexCarrito++;
				}
			}
			index++;
		}

		return lstAgenda;
	}

	public Integer convertirFecha(LocalDate fecha) {
		Integer dia = fecha.getDayOfWeek().getValue();
		return dia;
	}

	public ArrayList<Turno> generarTurnosLibres(LocalDate fecha) {
		// Determinar la fecha qué dia cae. Si es martes, buscar en los diaRetiro de los
		// martes cuantos turnos entran.
		Integer dia = fecha.getDayOfWeek().getValue();
		// Creamos el ArrayList que contendrá los turnos que crearemos.
		ArrayList<Turno> lstTurno = new ArrayList<Turno>();
		// Ver en lstDiaRetiro que dias hay con el dia sacado de arriba.
		int index = 0;
		while (index < this.lstDiaRetiro.size()) {
			DiaRetiro diaRetiro = this.lstDiaRetiro.get(index);
			if (diaRetiro.getDiaSemana() == dia) {
				// Buscar cuantos turnos entran en el diaRetiro
				// Calcular cuantos minutos hay entre hora desde y hora hasta
				Long entre = MINUTES.between(diaRetiro.getHoraDesde(), diaRetiro.getHoraHasta());
				Integer minutosEntre = entre.intValue();
				// Dividir minutosEntre por intervalo. Nos dará la cantidad de turnos
				// disponibles para ese dia (redondear para abajo).
				Integer turnosDisponibles = minutosEntre / diaRetiro.getIntervalo();

				// Generaremos los N turnos.
				// Hacemos getHoraDesde del diaRetiro para almacenar la hora del primer turno.
				LocalTime horaDesde = diaRetiro.getHoraDesde();
				for (int i = 0; i < turnosDisponibles; i++) {
					// Creamos el turno con la fecha que recibimos al inicio de la función, la hora
					// desde, y seteamos el turno como desocupado (false).
					Turno turno = new Turno(fecha, horaDesde, false);
					// Agregamos el turno al ArrayList
					lstTurno.add(turno);
					// Sumamos los minutos del intervalo para el proximo turno.
					horaDesde = horaDesde.plusMinutes(diaRetiro.getIntervalo());
				}
			}
			index++;
		}
		return lstTurno;
	}

	public ArrayList<Turno> traerTurnosOcupados(LocalDate fecha) throws Exception {
		// Si la lista de carritos está vacia (No hay turnos dados) entonces arrojamos
		// excepcion.
		if (this.lstCarrito == null) {
			throw new Exception("No hay turnos ocupados.");
		}
		// Creamos el ArrayList que contendrá los turnos que crearemos.
		ArrayList<Turno> lstTurnosOcupados = new ArrayList<Turno>();
		// Ver en lstCarrito que carritos con el dia asignado con el dia sacado de
		// arriba.
		int index = 0;
		while (index < this.lstCarrito.size()) {
			Carrito carrito = this.lstCarrito.get(index);
			if (carrito.getFecha() == fecha) {
				// Analizamos las hora de los carritos para determinar los turnos ocupados.
				Turno turno = new Turno(fecha, carrito.getHora(), true);
				// Creamos el turno en base a la hora del carro para genererar el turno ocupado
				lstTurnosOcupados.add(turno);
			}
			index++;
		}
		return lstTurnosOcupados;
	}

}
