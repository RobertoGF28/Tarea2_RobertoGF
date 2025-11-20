package modelo;

import java.time.LocalDate;

public class Coordinacion extends Persona{
	private Long idCoord;
	private boolean senior=false;
	private LocalDate fechasenior=null;
	

	public Coordinacion(long id, String email, String nombre, String nacionalidad, long id_credenciales, Long idCoord,
			boolean senior, LocalDate fechasenior) {
		super(id, email, nombre, nacionalidad, id_credenciales);
		this.idCoord = idCoord;
		this.senior = senior;
		this.fechasenior = fechasenior;
	}


	public Long getIdCoord() {
		return idCoord;
	}


	public void setIdCoord(Long idCoord) {
		this.idCoord = idCoord;
	}


	public boolean isSenior() {
		return senior;
	}


	public void setSenior(boolean senior) {
		this.senior = senior;
	}


	public LocalDate getFechasenior() {
		return fechasenior;
	}


	public void setFechasenior(LocalDate fechasenior) {
		this.fechasenior = fechasenior;
	}
}
