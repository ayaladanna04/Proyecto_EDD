package danna.proyecto_edd.Util;

import java.util.function.Predicate;
import danna.proyecto_edd.Modelo.*;
import danna.proyecto_edd.Estructura.*;


public class Filtros {

    public static ListaDobleCircular<Contacto> filtrarPorPais(ListaDobleCircular<Contacto> lista, String pais) {
        ListaDobleCircular<Contacto> resultado = new ListaDobleCircular<>();
        for (Contacto c : lista) {
            for (Atributo a : c.getAtributos()) {
                if (a.getValor().toLowerCase().contains(pais.toLowerCase())) {
                    resultado.agregar(c);
                    break;
                }
            }
        }
        return resultado;
    }

    public static ListaDobleCircular<Contacto> filtrarPorTipo(ListaDobleCircular<Contacto> lista, String tipo) {
        ListaDobleCircular<Contacto> resultado = new ListaDobleCircular<>();
        for (Contacto c : lista) {
            if (tipo.equalsIgnoreCase("persona") && c instanceof Persona) {
                resultado.agregar(c);
            } else if (tipo.equalsIgnoreCase("empresa") && c instanceof Empresa) {
                resultado.agregar(c);
            }
        }
        return resultado;
    }

    public static ListaDobleCircular<Contacto> filtrarConInstagram(ListaDobleCircular<Contacto> lista) {
        ListaDobleCircular<Contacto> resultado = new ListaDobleCircular<>();
        for (Contacto c : lista) {
            for (Atributo a : c.getAtributos()) {
                if (a.getNombre().equalsIgnoreCase("instagram")) {
                    resultado.agregar(c);
                    break;
                }
            }
        }
        return resultado;
    }
}
