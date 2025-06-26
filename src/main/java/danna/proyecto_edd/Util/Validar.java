package danna.proyecto_edd.Util;

import java.util.Scanner;

public class Validar {
            private static final Scanner sc = new Scanner(System.in);


    //  Validar datos ingresados por el usuario
    public static String validarTexto(Scanner sc){ 
        //cadena ingresada
        String input;
        do{
            //.trim() para eliminar espacios 
            input= sc.nextLine().trim();
            if(input.isEmpty()|| input.matches(".*\\d.*")){
                System.out.println("Datos ingresados no válidos.Vuelva a ingresar:");
            }
            // repetir lo mismo hasta que se ingresen los valores válidos
        } while (input.isEmpty()|| input.matches(".*\\d.*"));
        return input;
    }


    public static int validarNumero(Scanner sc) {
    while (true) {
        // Eliminatr espacios
        String input = sc.nextLine().trim();
        try {
            //Convertir valor a int
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Dato no válido. Por favor ingrese un número.");
        }
    }
}


    }


