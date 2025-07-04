package danna.proyecto_edd.Estructura;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListaDobleCircular<T> implements Iterable<T>, Serializable {
    private NodoDoble<T> cabeza;
    private int size;

    public ListaDobleCircular() {
        cabeza = null;
        size = 0;
    }

    public void agregar(T dato) {
    NodoDoble<T> nuevo = new NodoDoble<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
            cabeza.siguiente = cabeza;
            cabeza.anterior = cabeza;
        } else {
            NodoDoble<T> ultimo = cabeza.anterior;
            nuevo.siguiente = cabeza;
            nuevo.anterior = ultimo;
            cabeza.anterior = nuevo;
            ultimo.siguiente = nuevo;
            cabeza = nuevo; // 👈 ESTA línea es la clave
        }
        size++;
    }



    public boolean estaVacia() {
        return size == 0;
    }

    public int tamanio() {
        return size;
    }

    public NodoDoble<T> getCabeza() {
        return cabeza;
    }

    public void eliminar(T dato) {
        if (estaVacia()) return;
        NodoDoble<T> actual = cabeza;
        for (int i = 0; i < size; i++) {
            if (actual.dato.equals(dato)) {
                if (size == 1) {
                    cabeza = null;
                } else {
                    actual.anterior.siguiente = actual.siguiente;
                    actual.siguiente.anterior = actual.anterior;
                    if (actual == cabeza) cabeza = actual.siguiente;
                }
                size--;
                return;
            }
            actual = actual.siguiente;
        }
    }

    // Método para convertir la lista a un ArrayList
    public List<T> aLista() {
        List<T> lista = new ArrayList<>();
        if (cabeza == null) return lista;

        NodoDoble<T> actual = cabeza;
        for (int i = 0; i < size; i++) {
            lista.add(actual.dato);
            actual = actual.siguiente;
        }
        return lista;
    }

    // Método para ordenar la lista según un Comparator
    public void ordenar(Comparator<T> comparador) {
        List<T> lista = aLista();
        Collections.sort(lista, comparador);

        // Vaciar la lista original
        cabeza = null;
        size = 0;

        // Agregar los elementos ordenados
        for (T elem : lista) {
            agregar(elem);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new IteradorListaDobleCircular<>(this);
    }


    public java.util.List<T> aListaDesdeCabeza() {
    java.util.List<T> lista = new java.util.ArrayList<>();
    if (cabeza == null) return lista;

    NodoDoble<T> actual = cabeza;
    do {
        lista.add(actual.dato);
        actual = actual.siguiente;
    } while (actual != cabeza);

    return lista;
}

}
