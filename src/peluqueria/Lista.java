package peluqueria;

/**
 * @author Gabriela Orozco Garcia
 */
public class Lista {
    private int cuenta;
    private Nodo primero;
    private Nodo ultimo;

    public Lista() {
        cuenta = 0;
        primero = null;
        ultimo = null;
    }

    public boolean esVacia() {
        return cuenta == 0;
    }

    // Agregar al final
    public void agregar(Object dato) throws Exception {
        if (dato == null) throw new Exception("El dato no puede ser nulo");
        Nodo objetivo = new Nodo(dato);
        if (esVacia()) {
            objetivo.setIzquierda(null);
            objetivo.setDerecha(null);
            primero = objetivo;
            ultimo = objetivo;
        } else {
            ultimo.setDerecha(objetivo);
            objetivo.setIzquierda(ultimo);
            objetivo.setDerecha(null);
            ultimo = objetivo;
        }
        ++cuenta;
    }

    // Agregar al inicio
    public void agregarAlInicio(Object dato) throws Exception {
        if (dato == null) throw new Exception("El dato no puede ser nulo");
        Nodo objetivo = new Nodo(dato);
        if (esVacia()) {
            primero = objetivo;
            ultimo = objetivo;
        } else {
            objetivo.setDerecha(primero);
            primero.setIzquierda(objetivo);
            objetivo.setIzquierda(null);
            primero = objetivo;
        }
        ++cuenta;
    }

    // Agregar en posicion
    public void agregarEnPosicion(int indice, Object dato) throws Exception {
        if (dato == null) throw new Exception("El dato no puede ser nulo");
        if (indice < 0 || indice > cuenta) throw new Exception("Indice fuera de rango");
        if (indice == 0) { agregarAlInicio(dato); return; }
        if (indice == cuenta) { agregar(dato); return; }
        Nodo anterior = buscar(indice - 1);
        Nodo siguiente = anterior.getDerecha();
        Nodo nuevo = new Nodo(dato);
        anterior.setDerecha(nuevo);
        nuevo.setIzquierda(anterior);
        nuevo.setDerecha(siguiente);
        if (siguiente != null) siguiente.setIzquierda(nuevo);
        ++cuenta;
    }

    // Eliminar primero
    public Object eliminarPrimero() throws Exception {
        if (esVacia()) throw new Exception("La lista esta vacia");
        Object dato = primero.getDato();
        primero = primero.getDerecha();
        if (primero != null) primero.setIzquierda(null);
        else ultimo = null;
        --cuenta;
        return dato;
    }

    // Eliminar ultimo
    public Object eliminarUltimo() throws Exception {
        if (esVacia()) throw new Exception("La lista esta vacia");
        Object dato = ultimo.getDato();
        ultimo = ultimo.getIzquierda();
        if (ultimo != null) ultimo.setDerecha(null);
        else primero = null;
        --cuenta;
        return dato;
    }

    // Eliminar en posicion
    public Object eliminarEnPosicion(int indice) throws Exception {
        if (esVacia()) throw new Exception("La lista esta vacia");
        if (indice < 0 || indice >= cuenta) throw new Exception("Indice fuera de rango");
        if (indice == 0) return eliminarPrimero();
        if (indice == cuenta - 1) return eliminarUltimo();
        Nodo objetivo = buscar(indice);
        Nodo anterior = objetivo.getIzquierda();
        Nodo siguiente = objetivo.getDerecha();
        anterior.setDerecha(siguiente);
        if (siguiente != null) siguiente.setIzquierda(anterior);
        --cuenta;
        return objetivo.getDato();
    }

    // Buscar nodo por indice (privado)
    private Nodo buscar(int indice) throws Exception {
        if (esVacia()) throw new Exception("La lista esta vacia");
        if (indice < 0 || indice >= cuenta)
            throw new Exception("La posicion " + indice + " esta fuera del rango 0 - " + (cuenta - 1));
        Nodo actual = primero;
        for (int i = 0; i < cuenta; i++) {
            if (indice == i) break;
            actual = actual.getDerecha();
        }
        return actual;
    }

    // Buscar dato por indice (publico)
    public Object buscarDato(int indice) throws Exception {
        return buscar(indice).getDato();
    }

    // Buscar indice por dato (publico)
    public int buscarDato(Object dato) throws Exception {
        if (esVacia()) throw new Exception("La lista esta vacia");
        Nodo actual = primero;
        for (int i = 0; i < cuenta; i++) {
            if (actual.getDato().equals(dato)) return i;
            actual = actual.getDerecha();
        }
        throw new Exception("El elemento no esta en la lista");
    }

    // Contiene
    public boolean contiene(Object dato) {
        Nodo actual = primero;
        while (actual != null) {
            if (actual.getDato().equals(dato)) return true;
            actual = actual.getDerecha();
        }
        return false;
    }

    // Cuenta elementos
    public int cuentaElementos() {
        return cuenta;
    }

    // Limpiar
    public void limpiar() {
        primero = null;
        ultimo = null;
        cuenta = 0;
    }

    // Mostrar de adelante hacia atras
    public void mostrarAdelante() {
        if (esVacia()) {
            System.out.println("  [Lista vacia]");
            return;
        }
        Nodo actual = primero;
        int i = 0;
        while (actual != null) {
            System.out.println("  [" + i + "] " + actual.getDato());
            actual = actual.getDerecha();
            i++;
        }
    }

    // Mostrar de atras hacia adelante
    public void mostrarAtras() {
        if (esVacia()) {
            System.out.println("  [Lista vacia]");
            return;
        }
        Nodo actual = ultimo;
        int i = cuenta - 1;
        while (actual != null) {
            System.out.println("  [" + i + "] " + actual.getDato());
            actual = actual.getIzquierda();
            i--;
        }
    }
}
