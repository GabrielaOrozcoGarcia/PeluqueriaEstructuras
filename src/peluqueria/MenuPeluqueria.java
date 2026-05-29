package peluqueria;

import java.util.Scanner;

/**
 * Clase principal con menu de consola para el sistema de peluqueria.
 * Caso 21 - Peluqueria | Entidad: ClienteServicio | ID: Numero de turno
 *
 * @author Gabriela Orozco Garcia
 */
public class MenuPeluqueria {

    private static SistemaPeluqueria sistema = new SistemaPeluqueria();
    private static Scanner scanner = new Scanner(System.in);
    private static int contadorTurno = 1;

    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("   SISTEMA DE GESTION - PELUQUERIA");
        System.out.println("==============================================");

        int opcion = 0;
        do {
            mostrarMenu();
            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
                ejecutar(opcion);
            } catch (NumberFormatException e) {
                System.out.println("\n  Opcion invalida. Ingrese un numero del 1 al 10.");
            } catch (Exception e) {
                System.out.println("\n  Error: " + e.getMessage());
            }
        } while (opcion != 10);
    }

    private static void mostrarMenu() {
        System.out.println("\n----------------------------------------------");
        System.out.println("  1. Registrar cliente");
        System.out.println("  2. Ver todos los clientes registrados");
        System.out.println("  3. Ver clientes pendientes");
        System.out.println("  4. Procesar siguiente cliente");
        System.out.println("  5. Ver historial de clientes atendidos");
        System.out.println("  6. Buscar cliente por numero de turno");
        System.out.println("  7. Cancelar cliente pendiente");
        System.out.println("  8. Deshacer ultimo procesamiento");
        System.out.println("  9. Ver cantidades");
        System.out.println(" 10. Salir");
        System.out.println("----------------------------------------------");
        System.out.print("  Seleccione una opcion: ");
    }

    private static void ejecutar(int opcion) throws Exception {
        switch (opcion) {
            case 1:
                registrarCliente();
                break;
            case 2:
                sistema.verTodosLosClientes();
                break;
            case 3:
                sistema.verPendientes();
                break;
            case 4:
                sistema.procesarSiguiente();
                break;
            case 5:
                sistema.verHistorial();
                break;
            case 6:
                buscarCliente();
                break;
            case 7:
                cancelarCliente();
                break;
            case 8:
                sistema.deshacerUltimoProcesamiento();
                break;
            case 9:
                sistema.verCantidades();
                break;
            case 10:
                System.out.println("\n  Sistema cerrado. Hasta luego.");
                break;
            default:
                System.out.println("\n  Opcion no valida. Elija entre 1 y 10.");
        }
    }

    private static void registrarCliente() throws Exception {
        System.out.println("\n  --- REGISTRAR CLIENTE ---");

        System.out.print("  Nombre del cliente: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) throw new Exception("El nombre no puede estar vacio");

        System.out.println("  Servicios disponibles:");
        System.out.println("    1. Corte de cabello");
        System.out.println("    2. Tintura");
        System.out.println("    3. Peinado");
        System.out.println("    4. Barba");
        System.out.println("    5. Tratamiento capilar");
        System.out.print("  Seleccione servicio (1-5): ");
        String opServicio = scanner.nextLine().trim();

        String servicio;
        switch (opServicio) {
            case "1": servicio = "Corte de cabello"; break;
            case "2": servicio = "Tintura"; break;
            case "3": servicio = "Peinado"; break;
            case "4": servicio = "Barba"; break;
            case "5": servicio = "Tratamiento capilar"; break;
            default:  servicio = "Corte de cabello"; break;
        }

        String turno = "T-" + String.format("%03d", contadorTurno++);
        sistema.registrarCliente(turno, nombre, servicio);
    }

    private static void buscarCliente() throws Exception {
        System.out.print("\n  Ingrese el numero de turno a buscar (ej: T-001): ");
        String turno = scanner.nextLine().trim();
        if (turno.isEmpty()) throw new Exception("El numero de turno no puede estar vacio");
        sistema.buscarPorTurno(turno);
    }

    private static void cancelarCliente() throws Exception {
        System.out.print("\n  Ingrese el numero de turno a cancelar (ej: T-001): ");
        String turno = scanner.nextLine().trim();
        if (turno.isEmpty()) throw new Exception("El numero de turno no puede estar vacio");
        sistema.cancelarPendiente(turno);
    }
}
