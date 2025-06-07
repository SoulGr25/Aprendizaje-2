package Persona;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            List<Persona> listaPersonas = new ArrayList<>();
            int opcion;

            do {
                System.out.println("\n-Menú-");
                System.out.println("1. Registrar Persona");
                System.out.println("2. Lista de Personas");
                System.out.println("3. Editar un registro");
                System.out.println("4. Mostrar promedio de edades");
                System.out.println("5. Contar Personas M");
                System.out.println("6. Contar Personas F");
                System.out.println("7. Salir");
                System.out.print("Seleccione una opción: ");

                while (!scanner.hasNextInt()) {
                    System.out.println("Error. Por favor ingrese un número.");
                    scanner.next();
                }
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        // Registrar una persona
                        System.out.println("\nIngrese los datos de la persona:");

                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine();

                        System.out.print("Apellido: ");
                        String apellido = scanner.nextLine();

                        String genero;
                        while (true) {
                            System.out.print("Género (M/F): ");
                            genero = scanner.nextLine();
                            if (genero.equalsIgnoreCase("M") || genero.equalsIgnoreCase("F")) {
                                break;
                            } else {
                                System.out.println("Error. Ingrese 'M' o 'F'.");
                            }
                        }

                        int año = 0, mes = 0, día = 0;

                        System.out.println("Ingrese la fecha de nacimiento:");
                        while (true) {
                            System.out.print("Año (ej. 2000): ");
                            if (scanner.hasNextInt()) {
                                año = scanner.nextInt();
                                if (año > 1900 && año <= LocalDate.now().getYear()) break;
                            } else {
                                System.out.println("Error, intente nuevamente.");
                                scanner.next();
                            }
                        }

                        while (true) {
                            System.out.print("Mes (1-12): ");
                            if (scanner.hasNextInt()) {
                                mes = scanner.nextInt();
                                if (mes >= 1 && mes <= 12) break;
                            } else {
                                System.out.println("Error, intente nuevamente.");
                                scanner.next();
                            }
                        }

                        while (true) {
                            System.out.print("Día (1-31): ");
                            if (scanner.hasNextInt()) {
                                día = scanner.nextInt();
                                if (día >= 1 && día <= 31) break;
                            } else {
                                System.out.println("Error, intente nuevamente.");
                                scanner.next();
                            }
                        }
                        scanner.nextLine();

                        try {
                            LocalDate fechaNacimiento = LocalDate.of(año, mes, día);
                            Persona persona = new Persona(nombre, apellido, genero, fechaNacimiento);
                            listaPersonas.add(persona);
                            System.out.println("¡Registro agregado con éxito!");
                        } catch (Exception e) {
                            System.out.println("Fecha inválida. No se agregó la persona.");
                        }
                        break;

                    case 2:
                        // Mostrar la lista de personas
                        if (listaPersonas.isEmpty()) {
                            System.out.println("No hay registros para mostrar.");
                        } else {
                            System.out.println("Lista de Personas:");
                            for (int i = 0; i < listaPersonas.size(); i++) {
                                System.out.println((i + 1) + ". " + listaPersonas.get(i));
                            }
                        }
                        break;

                    case 3:
                        // Editar un registro
                        if (listaPersonas.isEmpty()) {
                            System.out.println("No hay registros para editar.");
                            break;
                        }
                        System.out.println("Ingrese la posición del registro a editar (1 - " + listaPersonas.size() + "):");
                        int indexEditar;
                        while (!scanner.hasNextInt()) {
                            System.out.println("Error. Ingrese un número.");
                            scanner.next();
                        }
                        indexEditar = scanner.nextInt();
                        scanner.nextLine();

                        if (indexEditar < 1 || indexEditar > listaPersonas.size()) {
                            System.out.println("Índice fuera de rango.");
                            break;
                        }

                        Persona personaEditar = listaPersonas.get(indexEditar - 1);
                        System.out.println("Editando la persona en posición " + indexEditar);

                        System.out.print("Nuevo nombre (" + personaEditar.getNombre() + "): ");
                        String nuevoNombre = scanner.nextLine();
                        if (!nuevoNombre.trim().isEmpty()) {
                            personaEditar.setNombre(nuevoNombre);
                        }

                        System.out.print("Nuevo apellido (" + personaEditar.getApellido() + "): ");
                        String nuevoApellido = scanner.nextLine();
                        if (!nuevoApellido.trim().isEmpty()) {
                            personaEditar.setApellido(nuevoApellido);
                        }

                        String nuevoGenero;
                        while (true) {
                            System.out.print("Nuevo género (" + (personaEditar.getGenero().equalsIgnoreCase("M") ? "M" : "F") + ", M/F): ");
                            nuevoGenero = scanner.nextLine();
                            if (nuevoGenero.isEmpty() || nuevoGenero.equalsIgnoreCase("M") || nuevoGenero.equalsIgnoreCase("F")) {
                                break;
                            } else {
                                System.out.println("Género inválido.");
                            }
                        }
                        if (!nuevoGenero.trim().isEmpty()) {
                            personaEditar.setGenero(nuevoGenero);
                        }

                        System.out.print("Nueva fecha de nacimiento (Año-Mes-Día) o dejar vacío para no cambiar: ");
                        String fechaStr = scanner.nextLine();

                        if (!fechaStr.trim().isEmpty()) {
                            try {
                                String[] parts = fechaStr.trim().split("-");
                                if (parts.length == 3) {
                                    int a = Integer.parseInt(parts[0]);
                                    int m = Integer.parseInt(parts[1]);
                                    int d = Integer.parseInt(parts[2]);
                                    LocalDate nuevaFecha = LocalDate.of(a, m, d);
                                    personaEditar.setFechaNacimiento(nuevaFecha);
                                } else {
                                    System.out.println("Formato incorrecto de la fecha.");
                                }
                            } catch (NumberFormatException | DateTimeException e) {
                                System.out.println("Error al parsear la fecha. No se hizo cambio.");
                            }
                        }
                        System.out.println("Registro actualizado.");
                        break;

                    case 4:
                        System.out.println("Promedio de edades: " + calcularPromedioEdades(listaPersonas));
                        break;

                    case 5:
                        System.out.println("Cantidad de personas masculinas: " + contarGenero(listaPersonas, "M"));
                        break;

                    case 6:
                        System.out.println("Cantidad de personas femeninas: " + contarGenero(listaPersonas, "F"));
                        break;

                    case 7:
                        System.out.println("Saliendo...");
                        break;

                    default:
                        System.out.println("Opción inválida, intente nuevamente.");
                }
            } while (opcion != 7);
        }
    }

    // Método para calcular promedio de edades
    public static double calcularPromedioEdades(List<Persona> personas) {
        if (personas.isEmpty()) return 0;
        int suma = personas.stream().mapToInt(Persona::getEdad).sum();
        return (double) suma / personas.size();
    }

    // Método para contar personas por género
    public static int contarGenero(List<Persona> personas, String genero) {
        return (int) personas.stream()
                .filter(p -> p.getGenero().equalsIgnoreCase(genero))
                .count();
    }
}