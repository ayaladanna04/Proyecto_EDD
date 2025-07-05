/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package danna.proyecto_edd.Modelo;

/**
 *
 * @author danna
 */
import java.io.Serializable;
import danna.proyecto_edd.Estructura.*;
import danna.proyecto_edd.Modelo.*;

public abstract class Contacto implements Serializable {
    protected String nombre;
    protected ListaDobleCircular<Atributo> atributos;
    protected ListaDobleCircular<Foto> fotos;
    protected ListaDobleCircular<FechaInteres> fechas;
    protected ListaDobleCircular<ContactoRelacionado> relacionados;

    public Contacto(String nombre) {
        this.nombre = nombre;
        this.atributos = new ListaDobleCircular<>();
        this.fotos = new ListaDobleCircular<>();
        this.fechas = new ListaDobleCircular<>();
        this.relacionados = new ListaDobleCircular<>();
    }

    public String getNombre() {
        return nombre;
    }

    public ListaDobleCircular<Atributo> getAtributos() {
        return atributos;
    }

    public ListaDobleCircular<Foto> getFotos() {
        return fotos;
    }

    public ListaDobleCircular<FechaInteres> getFechas() {
        return fechas;
    }

    public ListaDobleCircular<ContactoRelacionado> getRelacionados() {
        return relacionados;
    }
}
