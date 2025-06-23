package danna.proyecto_edd.Util;

import java.util.Comparator;
import danna.proyecto_edd.Modelo.*;

public class Comparadores {
    public static Comparator<Contacto> porNombre() {
        return (c1, c2) -> c1.getNombre().compareToIgnoreCase(c2.getNombre());
    }

    public static Comparator<Contacto> porCantidadAtributos() {
        return (c1, c2) -> Integer.compare(c2.getAtributos().tamanio(), c1.getAtributos().tamanio());
    }

    public static Comparator<Contacto> porApellidoPersona() {
        return (c1, c2) -> {
            if (c1 instanceof Persona && c2 instanceof Persona) {
                Persona p1 = (Persona) c1;
                Persona p2 = (Persona) c2;
                return p1.getApellido().compareToIgnoreCase(p2.getApellido());
            }
            return 0;
        };
    }

    public static Comparator<Contacto> porApellidoYprimerNombre () {
        return (c1, c2) -> {
            if (c1 instanceof Persona && c2 instanceof Persona) {
                Persona p1 = (Persona) c1;
                Persona p2 = (Persona) c2;
                int result = p1.getApellido().compareToIgnoreCase(p2.getApellido());
                if(result!=0){
                    return result;
                }
                return p1.getNombre().compareToIgnoreCase(p2.getNombre());
            }
            return 0;
        };
    }
   
}

