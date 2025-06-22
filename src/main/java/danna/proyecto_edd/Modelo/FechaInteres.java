package danna.proyecto_edd.Modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class FechaInteres implements Serializable {
    private String descripcion;
    private LocalDate fecha;

    public FechaInteres(String descripcion, LocalDate fecha) {
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}
