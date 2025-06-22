package danna.proyecto_edd.Estructura;

import java.util.Iterator;

public class IteradorListaDobleCircular<T> implements Iterator<T> {
    private NodoDoble<T> actual;
    private final NodoDoble<T> inicio;
    private boolean primeraPasada;

    public IteradorListaDobleCircular(ListaDobleCircular<T> lista) {
        this.actual = lista.getCabeza();
        this.inicio = actual;
        this.primeraPasada = true;
    }

    @Override
    public boolean hasNext() {
        return actual != null && (primeraPasada || actual != inicio);
    }

    @Override
    public T next() {
        T dato = actual.dato;
        actual = actual.siguiente;
        primeraPasada = false;
        return dato;
    }
}
