package modelo;

import java.time.LocalDate;

public class Espectaculo {
	private Long idEsp;
	private String nombre;
	private LocalDate fechaini;
	private LocalDate fechafin;
	private Coordinacion coordinador;
	
	
	


	public Espectaculo(Long idEsp, String nombre, LocalDate fechaini, LocalDate fechafin, Coordinacion coordinador) {
		super();
		this.idEsp = idEsp;
		this.nombre = nombre;
		this.fechaini = fechaini;
		this.fechafin = fechafin;
		this.coordinador = coordinador;
	}


	public Long getIdEsp() {
		return idEsp;
	}


	public void setIdEsp(Long idEsp) {
		this.idEsp = idEsp;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public LocalDate getFechaini() {
		return fechaini;
	}


	public void setFechaini(LocalDate fechaini) {
		this.fechaini = fechaini;
	}


	public LocalDate getFechafin() {
		return fechafin;
	}


	public void setFechafin(LocalDate fechafin) {
		this.fechafin = fechafin;
	}


	public Coordinacion getCoordinador() {
		return coordinador;
	}


	public void setCoordinador(Coordinacion coordinador) {
		this.coordinador = coordinador;
	}


	@Override
	public String toString() {
		return "Espectaculo [idEsp=" + idEsp + ", nombre=" + nombre + ", fechaini=" + fechaini + ", fechafin="
				+ fechafin + "Coordinador: " + coordinador.getNombre() +"]";
	}
}
