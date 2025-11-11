package modelo;

public class Numero {
	private Long idNum;
	private int orden;
	private String nombre;
	private double duracion;
	
	
	public Numero(Long idNum, int orden, String nombre, double duracion) {
		super();
		this.idNum = idNum;
		this.orden = orden;
		this.nombre = nombre;
		this.duracion = duracion;
	}


	public Long getIdNum() {
		return idNum;
	}


	public void setIdNum(Long idNum) {
		this.idNum = idNum;
	}


	public int getOrden() {
		return orden;
	}


	public void setOrden(int orden) {
		this.orden = orden;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public double getDuracion() {
		return duracion;
	}


	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}
}
