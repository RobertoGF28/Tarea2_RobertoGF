package modelo;

public class Artista extends Persona{

    private Long idArt;
    private String apodoString;
    private Especialidades[] especialidad;

    public Artista(long idPersona, String email, String nombre, String nacionalidad,
                   Long idCredenciales, Long idArt,
                   String apodoString, Especialidades[] especialidad) {

        super(idPersona, email, nombre, nacionalidad, idCredenciales);

        this.idArt = idArt; 
        this.apodoString = apodoString;
        this.especialidad = especialidad;
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
		return especialidad;
	}


	public void setEspecialidad(Especialidades[] especialidad) {
		especialidad = especialidad;
	}
}
