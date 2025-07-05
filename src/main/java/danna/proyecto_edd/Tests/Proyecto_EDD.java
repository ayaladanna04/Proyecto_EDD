/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package danna.proyecto_edd.Tests;

/**
 *
 * @author danna
 */
import danna.proyecto_edd.Modelo.*;
import danna.proyecto_edd.Persistencia.GestorArchivos;
import danna.proyecto_edd.Persistencia.GestorContacto;
import danna.proyecto_edd.Util.Validar;
import danna.proyecto_edd.inicioSesion.ValidadorLogin;
import danna.proyecto_edd.Estructura.*;
import java.io.IOException;
import java.util.Scanner;

public class Proyecto_EDD {
    private static final Scanner sc = new Scanner(System.in);
    private static ListaDobleCircular<Contacto> contactos;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ValidadorLogin validador = new ValidadorLogin("/Users/joeybustamante/Documents/GitHub/Proyecto_EDD/src/main/java/danna/proyecto_edd/inicioSesion/Credenciales.txt");

        System.out.print("Ingrese usuario: ");
        String usuario = sc.nextLine();

        System.out.print("Ingrese contraseña: ");
        String contrasena = sc.nextLine();

        if (validador.validar(usuario, contrasena)) {
            System.out.println(" Inicio de sesión exitoso.");

            try{
                contactos= GestorArchivos.cargarLista(usuario+".dat");
                System.out.println("Lista cargada desde archivo.");
                }catch(IOException|ClassNotFoundException e){
                // System.out.println("No existe una lista");
                contactos= new ListaDobleCircular<>();
                // Pasamos la lista cargada o nueva al gestor
                    
            }
            GestorContacto.setContactos(contactos);
        
            int opcion;
            do {
                GestorContacto.mostrarMenu();
                opcion=Validar.validarNumero(sc);
                switch (opcion) {
                    case 1 -> GestorContacto.crearPersona();
                    case 2 -> GestorContacto.crearEmpresa();
                    case 3 -> GestorContacto.listarContactos();
                    case 4 -> GestorContacto.editarContacto();
                    case 5 -> GestorContacto. eliminarContacto();
                    case 6 -> GestorContacto.agregarRelacion();
                    case 7 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción inválida.");
                }
            } while (opcion != 7);


        } else {
            System.out.println(" Usuario o contraseña incorrectos.");
        }



        
    }

   
        

      

    

    }

