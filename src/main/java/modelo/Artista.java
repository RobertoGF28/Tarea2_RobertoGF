package modelo;

public class Artista extends Persona{
	private Long idArt;
	private String apodoString;
	private Especialidades Especialidad[];
	
	


	public Artista(long id, String email, String nombre, String nacionalidad, Long idArt, String apodoString,
			Especialidades[] especialidad) {
		super(id, email, nombre, nacionalidad, idArt);
		this.idArt = idArt;
		this.apodoString = apodoString;
		Especialidad = especialidad;
	}


	public Long getIdArt() {
		return idArt;
	}


	public void setIdArt(Long idArt) {
		this.idArt = idArt;
	}


	public String getApodoString() {
		return apodoString;
	}


	public void setApodoString(String apodoString) {
		this.apodoString = apodoString;
	}


	public Especialidades[] getEspecialidad() {
		return Especialidad;
	}


	public void setEspecialidad(Especialidades[] especialidad) {
		Especialidad = especialidad;
	}
}
