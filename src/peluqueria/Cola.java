package peluqueria;

/**
 * @author Gabriela Orozco Garcia
 */
public class Cola {
    private int cuenta;
    private Nodo frente;  // primer elemento (head)
    private Nodo fin;     // ultimo elemento (tail)

    public Cola() {
        cuenta = 0;
        frente = null;
        fin = null;
    }

    public boolean esVacia() {
        return cuenta == 0;
    }

    // Encolar (agregar al final)
    public void encolar(Object dato) throws Exception {
        if (dato == null) throw new Exception("El dato no puede ser nulo");
        Nodo nuevo = new Nodo(dato);
        if (esVacia()) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.setDerecha(nuevo);
            nuevo.setIzquierda(fin);
            fin = nuevo;
        }
        ++cuenta;
    }

    // Desencolar (eliminar al frente)
    public Object desencolar() throws Exception {
        if (esVacia()) throw new Exception("La cola esta vacia");
        Object dato = frente.getDato();
        frente = frente.getDerecha();
        if (frente != null) frente.setIzquierda(null);
        else fin = null;
        --cuenta;
        return dato;
    }

    // Consultar el frente sin eliminar
    public Object peek() throws Exception {
        if (esVacia()) throw new Exception("La cola esta vacia");
        return frente.getDato();
    }

    // Tamanio
    public int tamanio() {
        return cuenta;
    }

    // Contiene
    public boolean contiene(Object dato) {
        Nodo actual = frente;
        while (actual != null) {
            if (actual.getDato().equals(dato)) return true;
            actual = actual.getDerecha();
        }
        return false;
    }

    // Limpiar
    public void limpiar() {
        frente = null;
        fin = null;
        cuenta = 0;
    }

    // Mostrar
    public void mostrar() {
        if (esVacia()) {
            System.out.println("  [Cola vacia]");
            return;
        }
        Nodo actual = frente;
        int i = 0;
        while (actual != null) {
            System.out.println("  [" + i + "] " + actual.getDato());
            actual = actual.getDerecha();
            i++;
        }
    }
}
