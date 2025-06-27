package danna.proyecto_edd.inicioSesion;

import danna.proyecto_edd.inicioSesion;

import java.io.*;
import java.util.*;

public class ValidadorLogin {

    private List<Usuario> usuariosRegistrados = new ArrayList<>();

    public ValidadorLogin(String rutaArchivo) {
        cargarUsuariosDesdeArchivo(rutaArchivo);
    }

    private void cargarUsuariosDesdeArchivo(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/joeybustamante/Documents/GitHub/Proyecto_EDD/src/main/java/danna/proyecto_edd/inicioSesion/Credenciales.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    usuariosRegistrados.add(new Usuario(partes[0].trim(), partes[1].trim()));
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo: " + e.getMessage());
        }
    }

    public boolean validar(String usuario, String contrasena) {
        for (Usuario u : usuariosRegistrados) {
            if (u.getUsuario().equals(usuario) && u.getContrasena().equals(contrasena)) {
                return true;
            }
        }
        return false;
    }
}

