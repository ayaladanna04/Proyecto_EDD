package danna.proyecto_edd.Persistencia;

import java.util.Scanner;

import danna.proyecto_edd.Estructura.ListaDobleCircular;
import danna.proyecto_edd.Modelo.Atributo;
import danna.proyecto_edd.Modelo.Contacto;
import danna.proyecto_edd.Modelo.Foto;
import danna.proyecto_edd.Util.Validar;

public class GestorAtributos {
    
     //Menú de atributos
     public static void mostrarMenuAtributos() {
        System.out.println("\n-- Menú Gestión de Atributos --");
        System.out.println("1. Crear Atributo persona");
        System.out.println("2. Editar Atributo persona");
        System.out.println("3. Eliminar Atributo persona");
        System.out.println("4. Eliminar Foto persona");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }
     public static void crearAtributo( Contacto c, Scanner sc){
          System.out.print("Cantidad de atributos a ingresar: ");
        int n = Validar.validarNumero(sc);
        for (int i = 0; i < n; i++) {
            System.out.print("Nombre del atributo: ");
            String nomAttr =Validar.validarTexto(sc);
            System.out.print("Valor del atributo: ");
            String valAttr =sc.nextLine();
            c.getAtributos().agregar(new Atributo(nomAttr, valAttr));
        }
        GestorContacto.guardarCambios();
     }
     public static void editarAtributo(Contacto c, Scanner sc){
        ListaDobleCircular<Atributo> lista = c.getAtributos();

          if (lista.estaVacia()) {
            System.out.println("Este contacto no tiene atributos para editar.\n");
            return;
          }
          while(true) {
             System.out.println("\n--- Atributos del contacto ---");
            for (Atributo a : lista) {
                System.out.println("- " + a.getNombre() + ": " + a.getValor());
            }

           System.out.print("Seleccione un atributo para editar: ");
           String atributoEditar = Validar.validarTexto(sc);

            for (Atributo a :lista ) {
                if (a.getNombre().equalsIgnoreCase(atributoEditar)) {
                //Solicitar atributo
                System.out.println("Ingrese el nuevo valor para :"+ a.getNombre());
                String valor= sc.nextLine();
                a.setValor(valor);
                System.out.println("Atributo editado exitosamente.\n");
                GestorContacto.guardarCambios();

                return;
                }
           }
               System.out.println("Atributo no encontrado.\n");


          }
        }
     
          
     public static void removerAtributo(Contacto c, Scanner sc){
          //Mostrar los atributos del contacto seleccionado
          ListaDobleCircular<Atributo> lista = c.getAtributos();
          if (lista.estaVacia()) {
          System.out.println("Este contacto no tiene atributos para eliminar.\n");
               return;
          }
          System.out.println("\n--- Atributos del contacto ---");
          for (Atributo a : lista) {
          System.out.println("- " + a.getNombre() + ": " + a.getValor());
          }
           System.out.print("Seleccione un atributo para eliminar: ");
           String atributoEliminar = Validar.validarTexto(sc);

        for (Atributo a :c.getAtributos() ) {
            if (a.getNombre().equalsIgnoreCase(atributoEliminar)) {
                c.getAtributos().eliminar(a);
                System.out.println("Contacto eliminado exitosamente.\n");
                 GestorContacto.guardarCambios();
                return;
            }
           }
               System.out.println("Atributo no encontrado.\n");

     }
     public static void removerFoto( Contacto c, Scanner sc){
          ListaDobleCircular<Foto> lista = c.getFotos();
          if (lista.estaVacia()) {
          System.out.println("Este contacto no tiene fotos para eliminar.\n");
               return;
          }
          // No se puede eliminar si el contacto cuenta con solo 1 foto
          if (lista.tamanio()==1){
            System.out.println("No se puede eliminar la  única foto.");
            return;
          }
          System.out.println("\n--- Fotos del contacto ---");
          for (Foto f : lista) {
          System.out.println("- " + f.getUrl());
          }
           System.out.print("Escriba el nombre de la foto a eliminar: ");
           String fotoEliminar = Validar.validarTexto(sc);

        for (Foto f : lista ) {
            if (f.getUrl().equalsIgnoreCase(fotoEliminar)) {
                lista.eliminar(f);
                System.out.println("Foto eliminado exitosamente.\n");
                 GestorContacto.guardarCambios();
                return;
            }
           }
               System.out.println("Atributo no encontrado.\n");

     }
               
}
