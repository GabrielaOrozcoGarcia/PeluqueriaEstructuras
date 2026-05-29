package peluqueria;

/**
 * @author Gabriela Orozco Garcia
 */
public class Pila {
    private int cuenta;
    private Nodo cima;  // tope de la pila

    public Pila() {
        cuenta = 0;
        cima = null;
    }

    public boolean esVacia() {
        return cuenta == 0;
    }

    // Apilar (push)
    public void apilar(Object dato) throws Exception {
        if (dato == null) throw new Exception("El dato no puede ser nulo");
        Nodo nuevo = new Nodo(dato);
        if (esVacia()) {
            cima = nuevo;
        } else {
            nuevo.setIzquierda(cima);
            cima.setDerecha(nuevo);
            cima = nuevo;
        }
        ++cuenta;
    }

    // Desapilar (pop)
    public Object desapilar() throws Exception {
        if (esVacia()) throw new Exception("La pila esta vacia");
        Object dato = cima.getDato();
        cima = cima.getIzquierda();
        if (cima != null) cima.setDerecha(null);
        --cuenta;
        return dato;
    }

    // Consultar cima sin eliminar (peek)
    public Object peek() throws Exception {
        if (esVacia()) throw new Exception("La pila esta vacia");
        return cima.getDato();
    }

    // Tamanio
    public int tamanio() {
        return cuenta;
    }

    // Contiene
    public boolean contiene(Object dato) {
        Nodo actual = cima;
        while (actual != null) {
            if (actual.getDato().equals(dato)) return true;
            actual = actual.getIzquierda();
        }
        return false;
    }

    // Limpiar
    public void limpiar() {
        cima = null;
        cuenta = 0;
    }

    // Mostrar (de cima hacia abajo)
    public void mostrar() {
        if (esVacia()) {
            System.out.println("  [Historial vacio]");
            return;
        }
        Nodo actual = cima;
        int i = cuenta - 1;
        while (actual != null) {
            System.out.println("  [" + i + "] " + actual.getDato());
            actual = actual.getIzquierda();
            i--;
        }
    }

    // Buscar dato en la pila, retorna posicion desde la cima (0 = cima)
    public int buscar(Object dato) throws Exception {
        if (esVacia()) throw new Exception("La pila esta vacia");
        Nodo actual = cima;
        int pos = 0;
        while (actual != null) {
            if (actual.getDato().equals(dato)) return pos;
            actual = actual.getIzquierda();
            pos++;
        }
        throw new Exception("El elemento no esta en el historial");
    }
}
