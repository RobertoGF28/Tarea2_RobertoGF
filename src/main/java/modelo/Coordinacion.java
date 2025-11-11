package modelo;

import java.time.LocalDate;

public class Coordinacion extends Persona{
	private Long idCoord;
	private boolean senior=false;
	private LocalDate fechasenior=null;
	
	
	


	public Coordinacion(long id, String email, String nombre, String nacionalidad) {
		super(id, email, nombre, nacionalidad);
		// TODO Auto-generated constructor stub
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
