package modelo;

public class Persona {
	
	
	protected Long id;
	protected String email;
	protected String nombre;
	protected String nacionalidad;
	protected Long id_credenciales;
	
	
	public Persona(long id, String email, String nombre, String nacionalidad, long id_credenciales) {
		super();
		this.id = id;
		this.email = email;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.id_credenciales = id_credenciales;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getNacionalidad() {
		return nacionalidad;
	}


	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	
	


	public Long getId_credenciales() {
		return id_credenciales;
	}


	public void setId_credenciales(Long id_credenciales) {
		this.id_credenciales = id_credenciales;
	}


	@Override
	public String toString() {
		return "Persona [id=" + id + ", email=" + email + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad + "]";
	}
	
	
	
	
}
