package danna.proyecto_edd.Persistencia;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import danna.proyecto_edd.Estructura.ListaDobleCircular;
import danna.proyecto_edd.Modelo.*;
import danna.proyecto_edd.Util.Validar;
import danna.proyecto_edd.Util.Comparadores;

public class GestorContacto {
    private static final Scanner sc = new Scanner(System.in);
    private static ListaDobleCircular<Contacto> contactos;
    private static String nombreUsuario;

    // Menu contactos
    public static void mostrarMenu() {
        System.out.println("\n-- Menú Gestión de Contactos --");
        System.out.println("1. Crear contacto persona");
        System.out.println("2. Crear contacto empresa");
        System.out.println("3. Listar contactos");
        System.out.println("4. Editar contacto");
        System.out.println("5. Eliminar contacto por nombre");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }

    // Establecer lista de contactos
    public static void setContactos(ListaDobleCircular<Contacto> lista) {
        contactos = lista;
    }

    // Establecer nombre de usuario (para guardar archivo)
    public static void setNombreUsuario(String usuario) {
        nombreUsuario = usuario;
    }

    // Crear Persona nueva
    public static void crearPersona() {
        System.out.print("Nombre: ");
        String nombre = Validar.validarTexto(sc);
        System.out.print("Apellido: ");
        String apellido = Validar.validarTexto(sc);
        Persona p = new Persona(nombre, apellido);

        System.out.print("Cantidad de atributos a ingresar: ");
        int n = Validar.validarNumero(sc);
        for (int i = 0; i < n; i++) {
            System.out.print("Nombre del atributo: ");
            String nomAttr = Validar.validarTexto(sc);
            System.out.print("Valor del atributo: ");
            String valAttr = sc.nextLine();
            p.getAtributos().agregar(new Atributo(nomAttr, valAttr));
        }

        System.out.println("Cantidad de fotos:");
        int m;
        do {
            m = Validar.validarNumero(sc);
            if (m < 1) {
                System.out.println("Por favor ingrese al menos una foto.");
            }
        } while (m < 1);

        for (int i = 0; i < m; i++) {
            System.out.print("Nombre del archivo de foto " + (i + 1) + ": ");
            String foto = Validar.validarTexto(sc);
            p.getFotos().agregar(new Foto(foto));
        }

        contactos.agregar(p);

        System.out.println("Cabeza actual: " + contactos.getCabeza().dato.getNombre());

    // Guardar cambios y demás...
    try {
        GestorArchivos.guardarLista(nombreUsuario + ".dat", contactos);
        System.out.println("¡Lista actualizada exitosamente!");
    } catch (IOException e) {
        System.err.println("No se pudo guardar el contacto.");
        e.printStackTrace();
    }
        
    System.out.println("Persona agregada exitosamente.\n");
}

    // Crear Empresa nueva
    public static void crearEmpresa() {
        System.out.print("Nombre comercial: ");
        String nombre = Validar.validarTexto(sc);
        System.out.print("Razón social: ");
        String razon = Validar.validarTexto(sc);
        Empresa e = new Empresa(nombre, razon);

        System.out.print("Cantidad de atributos a ingresar: ");
        int n = Validar.validarNumero(sc);
        for (int i = 0; i < n; i++) {
            System.out.print("Nombre del atributo: ");
            String nomAttr = Validar.validarTexto(sc);
            System.out.print("Valor del atributo: ");
            String valAttr = sc.nextLine();
            e.getAtributos().agregar(new Atributo(nomAttr, valAttr));
        }

        System.out.println("Cantidad de fotos:");
        int m;
        do {
            m = Validar.validarNumero(sc);
            if (m < 1) {
                System.out.println("Por favor ingrese al menos una foto.");
            }
        } while (m < 1);

        for (int i = 0; i < m; i++) {
            System.out.print("Nombre del archivo de foto " + (i + 1) + ": ");
            String foto = Validar.validarTexto(sc);
            e.getFotos().agregar(new Foto(foto));
        }

        contactos.agregar(e);

        try {
            GestorArchivos.guardarLista(nombreUsuario + ".dat", contactos);
            System.out.println("¡Lista actualizada exitosamente!");
        } catch (IOException ex) {
            System.err.println("No se pudo guardar el contacto.");
            ex.printStackTrace();
        }

        System.out.println("Empresa agregada exitosamente.\n");
    }

    // Listar contactos con menú de ordenamiento
    public static void listarContactos() {
    if (contactos.estaVacia()) {
        System.out.println("No hay contactos registrados.\n");
        return;
    }

    System.out.println("\nSeleccione criterio de ordenamiento:");
    System.out.println("1. Por nombre");
    System.out.println("2. Por cantidad de atributos");
    System.out.println("3. Por apellido (solo personas)");
    System.out.println("4. Por apellido y nombre (solo personas)");
    System.out.println("5. Sin ordenamiento");
    System.out.print("Opción: ");
    int criterio = Validar.validarNumero(sc);

    // Generar lista ordenable
    List<Contacto> listaOrdenable = contactos.aListaDesdeCabeza();

    switch (criterio) {
        case 1 -> listaOrdenable.sort(Comparadores.porNombre());
        case 2 -> listaOrdenable.sort(Comparadores.porCantidadAtributos());
        case 3 -> listaOrdenable.sort(Comparadores.porApellidoPersona());
        case 4 -> listaOrdenable.sort(Comparadores.porApellidoYprimerNombre());
        case 5 -> System.out.println("Mostrando sin ordenar...");
        default -> {
            System.out.println("Opción inválida. Mostrando sin ordenar...");
        }
    }

    System.out.println("\n--- Contactos registrados ---");
    for (Contacto c : listaOrdenable) {
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


    // Eliminar contacto
    public static void eliminarContacto() {
        if (contactos.estaVacia()) {
            System.out.println("No hay contactos para eliminar.\n");
            return;
        }

        System.out.print("Ingrese el nombre del contacto a eliminar: ");
        String nombreEliminar = Validar.validarTexto(sc);

        for (Contacto c : contactos) {
            if (c.getNombre().equalsIgnoreCase(nombreEliminar)) {
                contactos.eliminar(c);

                try {
                    GestorArchivos.guardarLista(nombreUsuario + ".dat", contactos);
                    System.out.println("¡Lista actualizada exitosamente!");
                } catch (IOException e) {
                    System.err.println("No se pudo guardar la lista.");
                    e.printStackTrace();
                }

                System.out.println("Contacto eliminado exitosamente.\n");
                return;
            }
        }
        System.out.println("Contacto no encontrado.\n");
    }

    // Editar contacto
    public static void editarContacto() {
        if (contactos.estaVacia()) {
            System.out.println("No hay contactos para editar.");
            return;
        }

        System.out.println("\n--- Contactos ---");
        for (Contacto c : contactos) {
            System.out.println("- " + c.getNombre());
        }

        System.out.print("Ingrese el nombre del contacto a editar: ");
        String nombreBuscar = Validar.validarTexto(sc);

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
        do {
            GestorAtributos.mostrarMenuAtributos();
            op = Validar.validarNumero(sc);
            switch (op) {
                case 1 -> GestorAtributos.crearAtributo(contactoEditar, sc);
                case 2 -> GestorAtributos.editarAtributo(contactoEditar, sc);
                case 3 -> GestorAtributos.removerAtributo(contactoEditar, sc);
                case 4 -> GestorAtributos.removerFoto(contactoEditar, sc);
                case 5 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (op != 5);

        try {
            GestorArchivos.guardarLista(nombreUsuario + ".dat", contactos);
            System.out.println("¡Cambios guardados!");
        } catch (IOException e) {
            System.out.println("No se pudo guardar.");
            e.printStackTrace();
        }
    }

    // Guardar cambios manualmente (si quieres)
    public static void guardarCambios() {
        try {
            GestorArchivos.guardarLista(nombreUsuario + ".dat", contactos);
            System.out.println("¡Cambios guardados!");
        } catch (IOException e) {
            System.out.println("No se pudo guardar.");
        }
    }
}
