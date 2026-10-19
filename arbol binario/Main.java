package sdsdw;

// Prueba de NodoArbol: crea una raíz con dos hijos y muestra sus datos
public class Main {
    public static void main(String[] args) {

        // Crear los nodos
        NodoArbol<String> raiz = new NodoArbol<>("Raiz (Nivel 0)");
        NodoArbol<String> hijoIzquierdo = new NodoArbol<>("Hijo Izquierdo (Nivel 1)");
        NodoArbol<String> hijoDerecho = new NodoArbol<>("Hijo Derecho (Nivel 1)");

        // Conectar los hijos a la raíz
        raiz.setIzquierdo(hijoIzquierdo);
        raiz.setDerecho(hijoDerecho);

        // Mostrar los datos
        System.out.println("--- PRUEBA DE NODO ARBOL ---");
        System.out.println("Dato en la Raiz: " + raiz.getDato());
        System.out.println("Dato en el hijo izquierdo: " + raiz.getIzquierdo().getDato());
        System.out.println("Dato en el hijo derecho: " + raiz.getDerecho().getDato());
    }
}
