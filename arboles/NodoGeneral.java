import java.util.ArrayList;
import java.util.List;

/**
 * Nodo de un árbol general (cada nodo puede tener cualquier número de hijos).
 *
 * @param <T> tipo del dato que guarda el nodo (String, Integer, etc.)
 */
public class NodoGeneral<T> {

    // Dato almacenado en el nodo (por ejemplo, el nombre "Universidad").
    private T dato;

    // Referencia al padre. La raíz tiene padre = null.
    // Sirve para calcular la profundidad subiendo hasta la raíz.
    private NodoGeneral<T> padre;

    // Lista de hijos. Si está vacía, el nodo es una hoja.
    private List<NodoGeneral<T>> hijos;

    /**
     * Constructor: crea un nodo sin padre y sin hijos.
     *
     * @param dato valor que guardará el nodo
     */
    public NodoGeneral(T dato) {
        this.dato = dato;
        this.padre = null;               // Todavía no pertenece a ningún árbol
        this.hijos = new ArrayList<>();  // Lista vacía: por ahora es hoja
    }

    /**
     * Agrega un hijo a este nodo y registra a este nodo como su padre.
     *
     * @param hijo nodo que se agregará como hijo
     * @return el hijo agregado (permite encadenar llamadas)
     */
    public NodoGeneral<T> agregarHijo(NodoGeneral<T> hijo) {
        hijo.padre = this;   // El hijo "sabe" quién es su padre
        hijos.add(hijo);     // El padre "sabe" quiénes son sus hijos
        return hijo;
    }

    /** @return el dato guardado en el nodo */
    public T getDato() {
        return dato;
    }

    /** @return el padre del nodo (null si es la raíz) */
    public NodoGeneral<T> getPadre() {
        return padre;
    }

    /** @return la lista de hijos (vacía si es hoja) */
    public List<NodoGeneral<T>> getHijos() {
        return hijos;
    }

    /** @return true si el nodo no tiene padre (es la raíz) */
    public boolean esRaiz() {
        return padre == null;
    }

    /** @return true si el nodo no tiene hijos (es una hoja) */
    public boolean esHoja() {
        return hijos.isEmpty();
    }

    /** @return el nombre del nodo, útil al imprimir */
    @Override
    public String toString() {
        return String.valueOf(dato);
    }
}
