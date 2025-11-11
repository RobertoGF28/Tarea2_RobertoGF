package modelo;

public class Sesion {
	Persona personas=null;
	Credenciales credencial=null;
	Perfiles perfilSesion;
	String nombrePerfil;
	
	
	
	
	


	public Sesion(Perfiles perfilSesion, String nombrePerfil) {
		super();
		this.perfilSesion = perfilSesion;
		this.nombrePerfil = nombrePerfil;
		this.credencial=new Credenciales(0L, "", "", Perfiles.INVITADO);
	}


	public Persona getPersonas() {
		return personas;
	}


	public void setPersonas(Persona personas) {
		this.personas = personas;
	}


	public Credenciales getCredencial() {
		return credencial;
	}


	public void setCredencial(Credenciales credencial) {
		this.credencial = credencial;
	}


	public Perfiles getPerfilSesion() {
		return perfilSesion;
	}


	public void setPerfilSesion(Perfiles perfilSesion) {
		this.perfilSesion = perfilSesion;
	}


	public String getNombrePerfil() {
		return nombrePerfil;
	}


	public void setNombrePerfil(String nombrePerfil) {
		this.nombrePerfil = nombrePerfil;
	}
}
