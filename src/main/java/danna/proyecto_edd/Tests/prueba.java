package danna.proyecto_edd.Tests;

import java.util.Scanner;

import danna.proyecto_edd.inicioSesion.ValidadorLogin;

public class prueba {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        ValidadorLogin validador = new ValidadorLogin("/Users/joeybustamante/Documents/GitHub/Proyecto_EDD/src/main/java/danna/proyecto_edd/inicioSesion/Credenciales.txt");

        System.out.print("Ingrese usuario: ");
        String usuario = sc.nextLine();

        System.out.print("Ingrese contraseña: ");
        String contrasena = sc.nextLine();

        if (validador.validar(usuario, contrasena)) {
            System.out.println(" Inicio de sesión exitoso.");
        } else {
            System.out.println(" Usuario o contraseña incorrectos.");
        }

        sc.close();


    }
}
