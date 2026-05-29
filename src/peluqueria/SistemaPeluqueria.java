package peluqueria;

/**
 * Sistema de gestion de peluqueria.
 * Usa Lista (registro general), Cola (pendientes FIFO) y Pila (historial LIFO).
 * @author Gabriela Orozco Garcia
 */
public class SistemaPeluqueria {

    private Lista listaGeneral;       // todos los clientes registrados
    private Cola colaPendientes;      // clientes esperando ser atendidos
    private Pila historialProcesados; // clientes ya atendidos

    public SistemaPeluqueria() {
        listaGeneral = new Lista();
        colaPendientes = new Cola();
        historialProcesados = new Pila();
    }

    // 1. Registrar cliente: va a la lista general Y a la cola de pendientes
    public void registrarCliente(String turno, String nombre, String servicio) throws Exception {
        ClienteServicio cliente = new ClienteServicio(turno, nombre, servicio);
        listaGeneral.agregar(cliente);
        colaPendientes.encolar(cliente);
        System.out.println("\n  ✔ Cliente registrado exitosamente.");
        System.out.println("  " + cliente);
    }

    // 2. Ver todos los clientes registrados (lista general)
    public void verTodosLosClientes() {
        System.out.println("\n  === LISTA GENERAL DE CLIENTES (" + listaGeneral.cuentaElementos() + ") ===");
        listaGeneral.mostrarAdelante();
    }

    // 3. Ver clientes pendientes (cola)
    public void verPendientes() {
        System.out.println("\n  === CLIENTES PENDIENTES EN COLA (" + colaPendientes.tamanio() + ") ===");
        colaPendientes.mostrar();
    }

    // 4. Procesar siguiente cliente pendiente
    public void procesarSiguiente() throws Exception {
        if (colaPendientes.esVacia()) {
            System.out.println("\n  No hay clientes pendientes en la cola.");
            return;
        }
        ClienteServicio procesado = (ClienteServicio) colaPendientes.desencolar();
        procesado.setEstado("ATENDIDO");
        historialProcesados.apilar(procesado);
        System.out.println("\n  ✔ Cliente atendido: " + procesado);
    }

    // 5. Ver historial de procesados (pila)
    public void verHistorial() {
        System.out.println("\n  === HISTORIAL DE CLIENTES ATENDIDOS (" + historialProcesados.tamanio() + ") ===");
        historialProcesados.mostrar();
    }

    // 6. Buscar cliente por numero de turno en la lista general
    public void buscarPorTurno(String turno) throws Exception {
        ClienteServicio busqueda = new ClienteServicio(turno, "", "");
        int indice = listaGeneral.buscarDato(busqueda);
        ClienteServicio encontrado = (ClienteServicio) listaGeneral.buscarDato(indice);
        System.out.println("\n  ✔ Cliente encontrado en posicion [" + indice + "]:");
        System.out.println("  " + encontrado);
    }

    // 7. Cancelar cliente pendiente usando cola auxiliar
    public void cancelarPendiente(String turno) throws Exception {
        if (colaPendientes.esVacia()) {
            System.out.println("\n  No hay clientes pendientes para cancelar.");
            return;
        }

        ClienteServicio busqueda = new ClienteServicio(turno, "", "");
        if (!colaPendientes.contiene(busqueda)) {
            System.out.println("\n  El turno " + turno + " no esta en la cola de pendientes.");
            return;
        }

        // Reconstruir la cola sin el cliente cancelado usando cola auxiliar
        Cola colaAuxiliar = new Cola();
        ClienteServicio cancelado = null;

        while (!colaPendientes.esVacia()) {
            ClienteServicio actual = (ClienteServicio) colaPendientes.desencolar();
            if (actual.equals(busqueda)) {
                cancelado = actual;
                cancelado.setEstado("CANCELADO");
            } else {
                colaAuxiliar.encolar(actual);
            }
        }

        // Restaurar la cola desde la auxiliar
        while (!colaAuxiliar.esVacia()) {
            colaPendientes.encolar(colaAuxiliar.desencolar());
        }

        System.out.println("\n  ✔ Turno cancelado: " + cancelado);
    }

    // 8. Deshacer ultimo procesamiento: sacar de la pila y volver a la cola
    public void deshacerUltimoProcesamiento() throws Exception {
        if (historialProcesados.esVacia()) {
            System.out.println("\n  No hay operaciones para deshacer en el historial.");
            return;
        }
        ClienteServicio ultimo = (ClienteServicio) historialProcesados.desapilar();
        ultimo.setEstado("PENDIENTE");
        colaPendientes.encolar(ultimo);
        System.out.println("\n  ✔ Ultimo procesamiento deshecho. Cliente devuelto a la cola:");
        System.out.println("  " + ultimo);
    }

    // 9. Ver cantidades
    public void verCantidades() {
        System.out.println("\n  === CANTIDADES ===");
        System.out.println("  Total registrados : " + listaGeneral.cuentaElementos());
        System.out.println("  Pendientes en cola: " + colaPendientes.tamanio());
        System.out.println("  Atendidos (historial): " + historialProcesados.tamanio());
    }
}
