import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal para gestionar estudiantes y sus calificaciones.
 * Incluye funcionalidades para agregar, consultar, calificar y eliminar estudiantes.
 * Además, ofrece un menú interactivo para los usuarios.
 *
 * @author RGB Team
 * @version 1.0
 */

public class GestorEstudiantes {

    /**
     * Método principal que inicia el programa.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        GestorEstudiantes gestor = new GestorEstudiantes();
        gestor.mostrarMenu();
    }

    /**
     * Clase interna para manejar excepciones específicas relacionadas con estudiantes.
     */
    static class EstudianteExcepcion extends Exception {
        /**
         * Constructor para la excepción personalizada.
         *
         * @param message Mensaje de error.
         */
        public EstudianteExcepcion(String message) {
            super(message);
        }
    }

    /**
     * Clase interna para representar un estudiante.
     */
    static class Estudiante {
        private int id;
        private String nombre;
        private List<Double> listaCalificaciones;

        /**
         * Constructor para inicializar un estudiante con su ID y nombre.
         *
         * @param id     Identificador único del estudiante.
         * @param nombre Nombre del estudiante.
         */
        public Estudiante(int id, String nombre) {
            this.id = id;
            this.nombre = nombre;
            this.listaCalificaciones = new ArrayList<>();
        }

        /**
         * Agrega una calificación al estudiante.
         *
         * @param calificacion Calificación a agregar (debe estar entre 0 y 100).
         * @throws EstudianteExcepcion Si la calificación está fuera del rango permitido.
         */
        public void agregarCalificacion(double calificacion) throws EstudianteExcepcion {
            if (calificacion < 0 || calificacion > 100) {
                throw new EstudianteExcepcion("Calificación no válida. Debe estar entre 0 y 100.");
            }
            listaCalificaciones.add(calificacion);
        }

        /**
         * Calcula el promedio de las calificaciones del estudiante.
         *
         * @return Promedio de las calificaciones o 0 si no hay calificaciones.
         */
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

        /**
         * Obtiene el ID del estudiante.
         *
         * @return ID del estudiante.
         */
        public int getId() {
            return id;
        }

        /**
         * Obtiene el nombre del estudiante.
         *
         * @return Nombre del estudiante.
         */
        public String getNombre() {
            return nombre;
        }
    }

    private List<Estudiante> estudiantes;

    /**
     * Constructor para inicializar la lista de estudiantes.
     */
    public GestorEstudiantes() {
        estudiantes = new ArrayList<>();
    }

    /**
     * Agrega un estudiante a la lista.
     *
     * @param id     Identificador único del estudiante.
     * @param nombre Nombre del estudiante.
     * @throws EstudianteExcepcion Si el ID ya está en uso.
     */
    public void agregarEstudiante(int id, String nombre) throws EstudianteExcepcion {
        for (Estudiante e : estudiantes) {
            if (e.getId() == id) {
                throw new EstudianteExcepcion("El estudiante con ID " + id + " ya existe.");
            }
        }
        estudiantes.add(new Estudiante(id, nombre));
        System.out.println("Estudiante agregado correctamente.");
    }

    /**
     * Ingresa una calificación para un estudiante específico.
     *
     * @param id          Identificador del estudiante.
     * @param calificacion Calificación a agregar.
     * @throws EstudianteExcepcion Si el estudiante no existe o la calificación es inválida.
     */
    public void ingresarCalificacion(int id, double calificacion) throws EstudianteExcepcion {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getId() == id) {
                estudiante.agregarCalificacion(calificacion);
                System.out.println("Calificación agregada correctamente.");
                return;
            }
        }
        throw new EstudianteExcepcion("Estudiante no encontrado.");
    }

    /**
     * Consulta y muestra la lista de estudiantes junto con sus promedios.
     */
    public void consultarEstudiantes() {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        for (Estudiante estudiante : estudiantes) {
            double promedio = estudiante.calcularPromedio();
            System.out.println("ID: " + estudiante.getId() + ", Nombre: " + estudiante.getNombre() + ", Promedio: " + promedio);
        }
    }

    /**
     * Elimina un estudiante de la lista.
     *
     * @param id Identificador del estudiante a eliminar.
     * @throws EstudianteExcepcion Si el estudiante no existe.
     */
    public void eliminarEstudiante(int id) throws EstudianteExcepcion {
        boolean eliminado = estudiantes.removeIf(estudiante -> estudiante.getId() == id);
        if (!eliminado) {
            throw new EstudianteExcepcion("El estudiante con ID " + id + " no existe.");
        }
        System.out.println("Estudiante eliminado correctamente.");
    }

    /**
     * Muestra información sobre el equipo de desarrollo.
     */
    public void mostrarFichaTecnica() {
        System.out.println("Equipo:");
        System.out.println("Integrante 1: Diego Nicolas Torres, Owner");
        System.out.println("Integrante 2: Isaac Josue Gonzales, Scrum Master");
        System.out.println("Integrante 3: Eider Fabian Nanrique ,Development Team");
        System.out.println("Integrante 2: Jose Omar Leal Miranda, Development Team");

    }

    /**
     * Muestra el menú interactivo para el usuario.
     */
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("\nMenú:");
                System.out.println("1. Agregar estudiante");
                System.out.println("2. Ingresar calificaciones");
                System.out.println("3. Consultar estudiantes y promedios");
                System.out.println("4. Eliminar estudiante");
                System.out.println("5. Acerca de");
                System.out.println("6. Salir");
                System.out.print("Seleccione una opción:\n");

                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                switch (opcion) {
                    case 1 -> {
                        System.out.print("Ingrese ID del estudiante: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Consumir el salto de línea
                        System.out.print("Ingrese nombre del estudiante: ");
                        String nombre = scanner.nextLine();
                        agregarEstudiante(id, nombre);
                    }
                    case 2 -> {
                        System.out.print("Ingrese ID del estudiante: ");
                        int id = scanner.nextInt();
                        System.out.print("Ingrese calificación: ");
                        double calificacion = scanner.nextDouble();
                        ingresarCalificacion(id, calificacion);
                    }
                    case 3 -> consultarEstudiantes();
                    case 4 -> {
                        System.out.print("Ingrese ID del estudiante a eliminar: ");
                        int id = scanner.nextInt();
                        eliminarEstudiante(id);
                    }
                    case 5 -> mostrarFichaTecnica();
                    case 6 -> {
                        System.out.println("Saliendo...");
                        return;
                    }
                    default -> System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpiar el buffer
            } catch (EstudianteExcepcion e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

