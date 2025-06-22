package danna.proyecto_edd.Modelo;

import java.io.Serializable;

public class Atributo implements Serializable {
    private String nombre;
    private String valor;

    public Atributo(String nombre, String valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return nombre + ": " + valor;
    }
}