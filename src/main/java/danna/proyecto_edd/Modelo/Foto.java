package danna.proyecto_edd.Modelo;

import java.io.Serializable;

public class Foto implements Serializable {
    private String url;

    public Foto(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return url;
    }
}