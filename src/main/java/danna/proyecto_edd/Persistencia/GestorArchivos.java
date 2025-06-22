package danna.proyecto_edd.Persistencia;

import java.io.*;
import danna.proyecto_edd.Estructura.*;;

public class GestorArchivos {

    public static <T> void guardarLista(String ruta, ListaDobleCircular<T> lista) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(lista);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> ListaDobleCircular<T> cargarLista(String ruta) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            return (ListaDobleCircular<T>) ois.readObject();
        }
    }
}
