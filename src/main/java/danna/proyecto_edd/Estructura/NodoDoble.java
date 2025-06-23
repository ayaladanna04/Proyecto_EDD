package danna.proyecto_edd.Estructura;

import java.io.Serializable;

public class NodoDoble<T> implements Serializable {
    T dato;
    NodoDoble<T> siguiente;
    NodoDoble<T> anterior;

    public NodoDoble(T dato) {
        this.dato = dato;
        this.siguiente = this.anterior = null;
    }
}
