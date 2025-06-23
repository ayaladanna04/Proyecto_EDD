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
import danna.proyecto_edd.Estructura.*;
import java.io.IOException;
import java.util.Scanner;

public class Proyecto_EDD {
    private static final Scanner sc = new Scanner(System.in);
    private static  ListaDobleCircular<Contacto> contactos;

    public static void main(String[] args) {
            // almacenar los datos ingresados en la lista "contactos"
        try{
            contactos= GestorArchivos.cargarLista("contactos.dat");
            System.out.println("Lista cargada desde archivo.");
        }catch(IOException|ClassNotFoundException e){
            // System.out.println("No existe una lista");
           
            contactos= new ListaDobleCircular<>();
        }
         int opcion;
        do {
            mostrarMenu();
            opcion = Integer.parseInt(sc.nextLine());
            switch (opcion) {
                case 1 -> crearPersona();
                case 2 -> crearEmpresa();
                case 3 -> listarContactos();
                case 4 -> editarContacto();
                case 5 -> eliminarContacto();
                case 6 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 6);
    }

    private static void mostrarMenu() {
        System.out.println("\n-- Menú Gestión de Contactos --");
        System.out.println("1. Crear contacto persona");
        System.out.println("2. Crear contacto empresa");
        System.out.println("3. Listar contactos");
        System.out.println("4. Editar contacto");
        System.out.println("5. Eliminar contacto por nombre");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void crearPersona() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellido: ");
        String apellido = sc.nextLine();
        Persona p = new Persona(nombre, apellido);

        System.out.print("Cantidad de atributos a ingresar: ");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            System.out.print("Nombre del atributo: ");
            String nomAttr = sc.nextLine();
            System.out.print("Valor del atributo: ");
            String valAttr = sc.nextLine();
            p.getAtributos().agregar(new Atributo(nomAttr, valAttr));
        }

        System.out.print("Cantidad de fotos: ");
        int m = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < m; i++) {
            System.out.print("Nombre del archivo de foto: ");
            String foto = sc.nextLine();
            p.getFotos().agregar(new Foto(foto));
        }

        contactos.agregar(p);
        try {
        GestorArchivos.guardarLista("contactos.dat", contactos);
        System.out.println("¡Lista actualizada exitosamente!");
    } catch (IOException e) {
        System.err.println("No se pudo guardar el contacto.");
        e.printStackTrace();
    }
        
        System.out.println("Persona agregada exitosamente.\n");
    }

    private static void crearEmpresa() {
        System.out.print("Nombre comercial: ");
        String nombre = sc.nextLine();
        System.out.print("Razón social: ");
        String razon = sc.nextLine();
        Empresa e = new Empresa(nombre, razon);

        System.out.print("Cantidad de atributos a ingresar: ");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            System.out.print("Nombre del atributo: ");
            String nomAttr = sc.nextLine();
            System.out.print("Valor del atributo: ");
            String valAttr = sc.nextLine();
            e.getAtributos().agregar(new Atributo(nomAttr, valAttr));
        }

        System.out.print("Cantidad de fotos: ");
        int m = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < m; i++) {
            System.out.print("Nombre del archivo de foto: ");
            String foto = sc.nextLine();
            e.getFotos().agregar(new Foto(foto));
        }

        contactos.agregar(e);
        System.out.println("Empresa agregada exitosamente.\n");
    }

    private static void listarContactos() {
        if (contactos.estaVacia()) {
            System.out.println("No hay contactos registrados.\n");
            return;
        }

        System.out.println("\n--- Contactos registrados ---");
        for (Contacto c : contactos) {
            System.out.println("Nombre: " + c.getNombre());
            if (c instanceof Persona p) {
                System.out.println("Apellido: " + p.getApellido());
            } else if (c instanceof Empresa e) {
                System.out.println("Razón Social: " + e.getRazonSocial());
            }
            System.out.println("Atributos:");
            for (Atributo a : c.getAtributos()) {
                System.out.println(" - " + a);
            }
            System.out.println("Fotos:");
            for (Foto f : c.getFotos()) {
                System.out.println(" - " + f);
            }
            System.out.println();
        }
    }

    private static void eliminarContacto() {
        if (contactos.estaVacia()) {
            System.out.println("No hay contactos para eliminar.\n");
            return;
        }

        System.out.print("Ingrese el nombre del contacto a eliminar: ");
        String nombreEliminar = sc.nextLine();

        for (Contacto c : contactos) {
            if (c.getNombre().equalsIgnoreCase(nombreEliminar)) {
                contactos.eliminar(c);
                System.out.println("Contacto eliminado exitosamente.\n");
                return;
            }
        }
        System.out.println("Contacto no encontrado.\n");
    }
    // terminar 
     private static void editarContacto() {
        //verificar contenido de la lista
         if (contactos.estaVacia()) {
        System.out.println("No hay contactos para editar.");
        return;
        }
        //Opciones de edición  : crear, eliminar y editar atributo
        System.out.print("Ingrese el nombre del contacto a editar: ");
        //ingreso de contacto a modificar
        String nombreBuscar = sc.nextLine();
        Contacto contactoEditar = null;
        for (Contacto c : contactos) {
            if (c.getNombre().equalsIgnoreCase(nombreBuscar)) {
                contactoEditar = c;
                break;
            }
        }
        if (contactoEditar == null) {
            System.out.println("Contacto no encontrado.");
            return;
        }
        
        int op;
        mostrarMenuAtributos();
            op = Integer.parseInt(sc.nextLine());
            switch (op) {
                case 1 -> crearAtributo();
                case 2 -> editarAtributo();
                case 3 -> removerAtributo();
                case 4 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        while (op != 4);  
     }

      private static void mostrarMenuAtributos() {
        System.out.println("\n-- Menú Gestión de Atributos --");
        System.out.println("1. Crear Atributo persona");
        System.out.println("2. Editar Atributo persona");
        System.out.println("3. Eliminar Atributo persona");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }

     private static void crearAtributo(){

     }
     private static void editarAtributo(){

     }
     private static void removerAtributo(){

     }

    }
