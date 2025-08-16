import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

public class JavaApplication1 {
    private static Vector<Personaje> personajes = new Vector<>();
    private static Vector<Pelea> historialPeleas = new Vector<>();
    private static Scanner scanner = new Scanner(System.in);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    static class Personaje {
        int id;
        String nombre;
        String arma;
        String[] habilidades = new String[5];
        int nivelPoder;

        public Personaje(int id, String nombre, String arma, String[] habilidades, int nivelPoder) {
            this.id = id;
            this.nombre = nombre;
            this.arma = arma;
            this.habilidades = habilidades;
            this.nivelPoder = nivelPoder;
        }
    }

    static class Pelea {
        int idPersonaje1;
        int idPersonaje2;
        String fechaHora;

        public Pelea(int idPersonaje1, int idPersonaje2, String fechaHora) {
            this.idPersonaje1 = idPersonaje1;
            this.idPersonaje2 = idPersonaje2;
            this.fechaHora = fechaHora;
        }
    }

    public static void main(String[] args) {
        mostrarMenuPrincipal();
    }

    private static void mostrarMenuPrincipal() {
        while (true) {
            System.out.println("\n=== Sistema de Gestión de Personajes ===");
            System.out.println("1. Agregar Personaje");
            System.out.println("2. Modificar Personaje");
            System.out.println("3. Eliminar Personaje");
            System.out.println("4. Ver Datos de un Personaje");
            System.out.println("5. Ver Listado de Personajes");
            System.out.println("6. Realizar Pelea entre Personajes");
            System.out.println("7. Ver Historial de Peleas");
            System.out.println("8. Ver Datos de Estudiante");
            System.out.println("9. Salir");
            System.out.print("Elige una opción: ");

            try {
                int opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        agregarPersonaje();
                        break;
                    case 2:
                        modificarPersonaje();
                        break;
                    case 3:
                        eliminarPersonaje();
                        break;
                    case 4:
                        verDatosPersonaje();
                        break;
                    case 5:
                        verListadoPersonajes();
                        break;
                    case 6:
                        realizarPelea();
                        break;
                    case 7:
                        verHistorialPeleas();
                        break;
                    case 8:
                        verDatosEstudiante();
                        break;
                    case 9:
                        System.out.println("Saliendo del sistema...");
                        return;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            }
        }
    }

    private static void agregarPersonaje() {
        System.out.println("\n--- Agregar Personaje ---");
        
        int nuevoId = personajes.size() + 1;
        
        String nombre;
        while (true) {
            System.out.print("Nombre del personaje: ");
            nombre = scanner.nextLine().trim();
            
            if (nombre.isEmpty()) {
                System.out.println("Error: El nombre no puede estar vacío.");
                continue;
            }
            
            if (nombreExiste(nombre)) {
                System.out.println("Error: Ya existe un personaje con ese nombre.");
            } else {
                break;
            }
        }
        
        String arma;
        while (true) {
            System.out.print("Arma del personaje: ");
            arma = scanner.nextLine().trim();
            
            if (arma.isEmpty()) {
                System.out.println("Error: El arma no puede estar vacía.");
            } else {
                break;
            }
        }
        
        String[] habilidades = new String[5];
        System.out.println("Ingrese hasta 5 habilidades (deje en blanco para terminar):");
        
        for (int i = 0; i < 5; i++) {
            System.out.print("Habilidad " + (i+1) + ": ");
            String habilidad = scanner.nextLine().trim();
            
            if (habilidad.isEmpty()) {
                break;
            }
            
            habilidades[i] = habilidad;
        }
        
        int nivelPoder;
        while (true) {
            try {
                System.out.print("Nivel de poder (1-100): ");
                nivelPoder = Integer.parseInt(scanner.nextLine());
                
                if (nivelPoder < 1 || nivelPoder > 100) {
                    System.out.println("Error: El nivel de poder debe estar entre 1 y 100.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            }
        }
        
        Personaje nuevoPersonaje = new Personaje(nuevoId, nombre, arma, habilidades, nivelPoder);
        personajes.add(nuevoPersonaje);
        
        System.out.println("\nPersonaje agregado exitosamente!");
        System.out.println("ID: " + nuevoId);
        System.out.println("Nombre: " + nombre);
    }

    private static boolean nombreExiste(String nombre) {
        for (Personaje p : personajes) {
            if (p.nombre.equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    private static void modificarPersonaje() {
        if (personajes.isEmpty()) {
            System.out.println("\nNo hay personajes registrados para modificar.");
            return;
        }
        
        System.out.println("\n--- Modificar Personaje ---");
        verListadoPersonajes();
        
        int id;
        while (true) {
            try {
                System.out.print("\nIngrese el ID del personaje a modificar: ");
                id = Integer.parseInt(scanner.nextLine());
                
                if (id < 1 || id > personajes.size()) {
                    System.out.println("Error: ID no válido. Intente nuevamente.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            }
        }
        
        Personaje personaje = personajes.get(id - 1);
        
        System.out.println("\nDatos actuales del personaje:");
        System.out.println("1. Nombre: " + personaje.nombre);
        System.out.println("2. Arma: " + personaje.arma);
        System.out.print("3. Habilidades: ");
        for (String h : personaje.habilidades) {
            if (h != null) System.out.print(h + ", ");
        }
        System.out.println("\n4. Nivel de poder: " + personaje.nivelPoder);
        
        System.out.println("\n¿Qué desea modificar?");
        System.out.println("1. Nombre");
        System.out.println("2. Arma");
        System.out.println("3. Habilidades");
        System.out.println("4. Nivel de poder");
        System.out.println("5. Cancelar");
        System.out.print("Elige una opción: ");
        
        try {
            int opcion = Integer.parseInt(scanner.nextLine());
            
            switch (opcion) {
                case 1: 
                    String nuevoNombre;
                    while (true) {
                        System.out.print("Nuevo nombre: ");
                        nuevoNombre = scanner.nextLine().trim();
                        
                        if (nuevoNombre.isEmpty()) {
                            System.out.println("Error: El nombre no puede estar vacío.");
                            continue;
                        }
                        
                        if (nombreExiste(nuevoNombre) && !nuevoNombre.equalsIgnoreCase(personaje.nombre)) {
                            System.out.println("Error: Ya existe un personaje con ese nombre.");
                        } else {
                            personaje.nombre = nuevoNombre;
                            System.out.println("Nombre actualizado correctamente.");
                            break;
                        }
                    }
                    break;
                    
                case 2:
                    System.out.print("Nueva arma: ");
                    String nuevaArma = scanner.nextLine().trim();
                    if (!nuevaArma.isEmpty()) {
                        personaje.arma = nuevaArma;
                        System.out.println("Arma actualizada correctamente.");
                    } else {
                        System.out.println("Error: El arma no puede estar vacía.");
                    }
                    break;
                    
                case 3:
                    String[] nuevasHabilidades = new String[5];
                    System.out.println("Ingrese hasta 5 nuevas habilidades (deje en blanco para terminar):");
                    
                    for (int i = 0; i < 5; i++) {
                        System.out.print("Habilidad " + (i+1) + ": ");
                        String habilidad = scanner.nextLine().trim();
                        
                        if (habilidad.isEmpty()) {
                            break;
                        }
                        
                        nuevasHabilidades[i] = habilidad;
                    }
                    
                    personaje.habilidades = nuevasHabilidades;
                    System.out.println("Habilidades actualizadas correctamente.");
                    break;
                    
                case 4:
                    int nuevoNivel;
                    while (true) {
                        try {
                            System.out.print("Nuevo nivel de poder (1-100): ");
                            nuevoNivel = Integer.parseInt(scanner.nextLine());
                            
                            if (nuevoNivel < 1 || nuevoNivel > 100) {
                                System.out.println("Error: El nivel de poder debe estar entre 1 y 100.");
                            } else {
                                personaje.nivelPoder = nuevoNivel;
                                System.out.println("Nivel de poder actualizado correctamente.");
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Debe ingresar un número válido.");
                        }
                    }
                    break;
                    
                case 5:
                    System.out.println("Modificación cancelada.");
                    break;
                    
                default:
                    System.out.println("Opción no válida.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número válido.");
        }
    }

    private static void eliminarPersonaje() {
        if (personajes.isEmpty()) {
            System.out.println("\nNo hay personajes registrados para eliminar.");
            return;
        }
        
        System.out.println("\n--- Eliminar Personaje ---");
        verListadoPersonajes();
        
        int id;
        while (true) {
            try {
                System.out.print("\nIngrese el ID del personaje a eliminar: ");
                id = Integer.parseInt(scanner.nextLine());
                
                if (id < 1 || id > personajes.size()) {
                    System.out.println("Error: ID no válido. Intente nuevamente.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            }
        }
        
        System.out.print("¿Está seguro que desea eliminar al personaje " + personajes.get(id-1).nombre + "? (S/N): ");
        String confirmacion = scanner.nextLine().trim().toUpperCase();
        
        if (confirmacion.equals("S")) {
            String nombreEliminado = personajes.get(id-1).nombre;
            personajes.remove(id-1);
            
            for (int i = 0; i < personajes.size(); i++) {
                personajes.get(i).id = i + 1;
            }
            
            System.out.println("Personaje '" + nombreEliminado + "' eliminado correctamente.");
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }

    private static void verDatosPersonaje() {
        if (personajes.isEmpty()) {
            System.out.println("\nNo hay personajes registrados para mostrar.");
            return;
        }
        
        System.out.println("\n--- Ver Datos de Personaje ---");
        verListadoPersonajes();
        
        int id;
        while (true) {
            try {
                System.out.print("\nIngrese el ID del personaje a visualizar: ");
                id = Integer.parseInt(scanner.nextLine());
                
                if (id < 1 || id > personajes.size()) {
                    System.out.println("Error: ID no válido. Intente nuevamente.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            }
        }
        
        Personaje personaje = personajes.get(id - 1);
        
        System.out.println("\n=== Datos completos del personaje ===");
        System.out.println("ID: " + personaje.id);
        System.out.println("Nombre: " + personaje.nombre);
        System.out.println("Arma: " + personaje.arma);
        
        System.out.print("Habilidades: ");
        boolean tieneHabilidades = false;
        for (String h : personaje.habilidades) {
            if (h != null && !h.isEmpty()) {
                System.out.print(h + ", ");
                tieneHabilidades = true;
            }
        }
        if (!tieneHabilidades) {
            System.out.print("No tiene habilidades registradas");
        }
        System.out.println();
        
        System.out.println("Nivel de poder: " + personaje.nivelPoder);
    }

    private static void verListadoPersonajes() {
        if (personajes.isEmpty()) {
            System.out.println("\nNo hay personajes registrados en el sistema.");
            return;
        }
        
        System.out.println("\n--- Listado de Personajes ---");
        System.out.println("ID\tNombre\t\tNivel de Poder");
        System.out.println("--------------------------------");
        
        for (Personaje p : personajes) {
            System.out.println(p.id + "\t" + p.nombre + "\t\t" + p.nivelPoder);
        }
    }

    private static void realizarPelea() {
        if (personajes.size() < 2) {
            System.out.println("\nSe necesitan al menos 2 personajes registrados para realizar una pelea.");
            return;
        }
        
        System.out.println("\n--- Realizar Pelea entre Personajes ---");
        verListadoPersonajes();
        
        int id1;
        while (true) {
            try {
                System.out.print("\nIngrese el ID del primer personaje: ");
                id1 = Integer.parseInt(scanner.nextLine());
                
                if (id1 < 1 || id1 > personajes.size()) {
                    System.out.println("Error: ID no válido. Intente nuevamente.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            }
        }
        
        int id2;
        while (true) {
            try {
                System.out.print("Ingrese el ID del segundo personaje: ");
                id2 = Integer.parseInt(scanner.nextLine());
                
                if (id2 < 1 || id2 > personajes.size()) {
                    System.out.println("Error: ID no válido. Intente nuevamente.");
                } else if (id2 == id1) {
                    System.out.println("Error: No puede pelear contra sí mismo. Intente nuevamente.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            }
        }
        
        String fechaHora = dateFormat.format(new Date());
        Pelea nuevaPelea = new Pelea(id1, id2, fechaHora);
        historialPeleas.add(nuevaPelea);
        
        System.out.println("\nPelea registrada exitosamente!");
        System.out.println(personajes.get(id1-1).nombre + " vs " + personajes.get(id2-1).nombre);
        System.out.println("Fecha y hora: " + fechaHora);
    }

    private static void verHistorialPeleas() {
        if (historialPeleas.isEmpty()) {
            System.out.println("\nNo hay peleas registradas en el historial.");
            return;
        }
        
        System.out.println("\n--- Historial de Peleas ---");
        System.out.println("Fecha y Hora\t\t\tCombate");
        System.out.println("------------------------------------------------");
        
        for (Pelea p : historialPeleas) {
            String nombre1 = personajes.get(p.idPersonaje1 - 1).nombre;
            String nombre2 = personajes.get(p.idPersonaje2 - 1).nombre;
            System.out.println(p.fechaHora + "\t" + nombre1 + " vs " + nombre2);
        }
    }

    private static void verDatosEstudiante() {
        System.out.println("\n--- Datos del Estudiante ---");
        System.out.println("Nombre: Yenner Alexander Reynoso León");
        System.out.println("Carné: 202113899");
        System.out.println("Curso: Introducción a la Programación y Computación 1");
        System.out.println("Sección: D");
        System.out.println("Fecha: " + dateFormat.format(new Date()));
    }
}