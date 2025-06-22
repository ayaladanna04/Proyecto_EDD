package danna.proyecto_edd.Estructura;

import java.io.Serializable;
import java.util.Iterator;

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

    @Override
    public Iterator<T> iterator() {
        return new IteradorListaDobleCircular<>(this);
    }
}
