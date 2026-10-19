package sdsdw;

// Nodo de un árbol binario: guarda un dato y sus hijos izquierdo y derecho
public class NodoArbol<T> {

    private T dato;                   // valor guardado en el nodo
    private NodoArbol<T> izquierdo;   // hijo izquierdo (null si no tiene)
    private NodoArbol<T> derecho;     // hijo derecho (null si no tiene)

    // Constructor: crea un nodo sin hijos
    public NodoArbol(T dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
    }

    // Devuelve el dato del nodo
    public T getDato() {
        return dato;
    }

    // Cambia el dato del nodo
    public void setDato(T dato) {
        this.dato = dato;
    }

    // Devuelve el hijo izquierdo
    public NodoArbol<T> getIzquierdo() {
        return izquierdo;
    }

    // Asigna el hijo izquierdo
    public void setIzquierdo(NodoArbol<T> izquierdo) {
        this.izquierdo = izquierdo;
    }

    // Devuelve el hijo derecho
    public NodoArbol<T> getDerecho() {
        return derecho;
    }

    // Asigna el hijo derecho
    public void setDerecho(NodoArbol<T> derecho) {
        this.derecho = derecho;
    }

    // Un nodo es hoja si no tiene hijos.
    public boolean esHoja() {
        return izquierdo == null && derecho == null;
    }
}
