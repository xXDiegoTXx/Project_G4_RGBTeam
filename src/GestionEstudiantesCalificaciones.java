import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Clase para manejar excepciones específicas del estudiante
class EstudianteExcepcion extends Exception {
    public EstudianteExcepcion(String message) {
        super(message);
    }
}


// Clase Estudiante
class Estudiante {
    private int id;
    private String nombre;
    private List<Double> listaCalificaciones;

    public Estudiante(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.listaCalificaciones = new ArrayList<>();
    }
    public static void main(String[] args) {
        GestorEstudiantes gestor = new GestorEstudiantes();
        gestor.mostrarMenu();
    }

    public void agregarCalificacion(double calificacion) throws EstudianteExcepcion {
        if (calificacion < 0 || calificacion > 100) {
            throw new EstudianteExcepcion("Calificación no válida. Debe estar entre 0 y 100.");
        }
        listaCalificaciones.add(calificacion);
    }

    public double calcularPromedio() {
        if (listaCalificaciones.isEmpty()) {
            return 0;
        }
        double sum = 0;
        for (double calificacion : listaCalificaciones) {
            sum += calificacion;
        }
        return sum / listaCalificaciones.size();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Double> getListaCalificaciones() {
        return listaCalificaciones;
    }
}

// Clase GestorEstudiantes
class GestorEstudiantes {
    private List<Estudiante> estudiantes;

    public GestorEstudiantes() {
        estudiantes = new ArrayList<>();
    }

    public void agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    public void ingresarCalificaciones(int id, double calificacion) throws EstudianteExcepcion {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getId() == id) {
                estudiante.agregarCalificacion(calificacion);
                return;
            }
        }
        throw new EstudianteExcepcion("Estudiante no encontrado.");
    }

    public void consultarEstudiantes() {
        for (Estudiante estudiante : estudiantes) {
            double promedio = estudiante.calcularPromedio();
            System.out.println("ID: " + estudiante.getId() + ", Nombre: " + estudiante.getNombre() + ", Promedio: " + promedio);
        }
    }

    public void eliminarEstudiante(int id) throws EstudianteExcepcion {
        boolean removed = estudiantes.removeIf(estudiante -> estudiante.getId() == id);
        if (!removed) {
            throw new EstudianteExcepcion("Estudiante no encontrado.");
        }
    }

    public void mostrarFichaTecnica() {
        System.out.println("Equipo: \nIntegrante 1:\nDiego Nicolás Torres Vega, Owner\nIntegrante 2:\nISAAC JOSUE GONZALEZ PALMAR, Scrum Master\nIntegrante 3:\nJOSE LEAL OMAR MIRANDA, Developer\nIntegrante 4:\nEIDER MANRIQUE FABIAN SEPULVEDA, Developer\nEslogan:\n<Ingresar eslogan>.");
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenú:");
            System.out.println("1. Agregar estudiante");
            System.out.println("2. Ingresar calificaciones");
            System.out.println("3. Consultar estudiantes y promedios");
            System.out.println("4. Eliminar estudiante");
            System.out.println("5. Acerca de");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción:\n");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea

            try {
                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese ID del estudiante: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();  // Consumir el salto de línea
                        System.out.print("Ingrese nombre del estudiante: ");
                        String nombre = scanner.nextLine();
                        agregarEstudiante(new Estudiante(id, nombre));
                        break;
                    case 2:
                        System.out.print("Ingrese ID del estudiante: ");
                        int idCal = scanner.nextInt();
                        System.out.print("Ingrese calificación: ");
                        double calificacion = scanner.nextDouble();
                        ingresarCalificaciones(idCal, calificacion);
                        break;
                    case 3:
                        consultarEstudiantes();
                        break;
                    case 4:
                        System.out.print("Ingrese ID del estudiante a eliminar: ");
                        int idEliminar = scanner.nextInt();
                        eliminarEstudiante(idEliminar);
                        break;
                    case 5:
                        mostrarFichaTecnica();
                        break;
                    case 6:
                        System.out.println("Saliendo...");
                        return;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (EstudianteExcepcion e) {
                System.out.println(e.getMessage());
            }
        }
    }
}