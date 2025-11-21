package modelo;

import java.time.LocalDate;

public class Espectaculo {

    private Long idEsp;
    private String nombre;
    private LocalDate fechaini;
    private LocalDate fechafin;
    private Long id_Coord;

    private Coordinacion coordinador;

    public Espectaculo(Long idEsp, String nombre, LocalDate fechaini, LocalDate fechafin, Coordinacion coordinador) {
        this.idEsp = idEsp;
        this.nombre = nombre;
        this.fechaini = fechaini;
        this.fechafin = fechafin;
        this.coordinador = coordinador;
        this.id_Coord =  coordinador.getIdCoord();
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
    

    public LocalDate getFechaInicio() {
        return fechaini;
    }

    public LocalDate getFechaFin() {
        return fechafin;
    }

    public Coordinacion getCoordinador() {
        return coordinador;
    }

    public Long getId_Coord() {
        return id_Coord;
    }


	public void setFechaInicio(LocalDate fechaini) {
		this.fechaini = fechaini;
	}



	public void setFechaFin(LocalDate fechafin) {
		this.fechafin = fechafin;
	}

	@Override
    public String toString() {
        return "Espectáculo: " + nombre +
                " | Inicio: " + fechaini +
                " | Fin: " + fechafin +
                " | Coordinador: " + coordinador.getNombre();
    }
}