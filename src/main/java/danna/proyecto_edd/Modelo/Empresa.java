package danna.proyecto_edd.Modelo;
public class Empresa extends Contacto {
    private String razonSocial;

    public Empresa(String nombre, String razonSocial) {
        super(nombre);
        this.razonSocial = razonSocial;
    }

    public String getRazonSocial() {
        return razonSocial;
    }
}