package danna.proyecto_edd.Modelo;


public class ContactoRelacionado {
    private Contacto contacto;
    private String tipoRelacion;

    public ContactoRelacionado(Contacto contacto, String tipoRelacion) {
        this.contacto = contacto;
        this.tipoRelacion = tipoRelacion;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public String getTipoRelacion() {
        return tipoRelacion;
    }
}
