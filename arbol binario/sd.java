package sdsdw;

/**
 * Laboratorio: "Mi primer árbol binario"
 * Estructura de Datos II
 *
 * Torneo de Mortal Kombat usando un árbol binario.
 */
public class sd {

    // Imprime el árbol girado 90 grados.
    static void mostrar(NodoArbol<String> nodo, int nivel) {
        if (nodo == null) {
            return;
        }

        // Primero se imprime el hijo derecho.
        mostrar(nodo.getDerecho(), nivel + 1);

        // Después se imprime el nodo actual.
        System.out.println("    ".repeat(nivel) + nodo.getDato());

        // Finalmente se imprime el hijo izquierdo.
        mostrar(nodo.getIzquierdo(), nivel + 1);
    }

    // Cuenta las hojas del árbol.
    static int contarLuchadores(NodoArbol<String> nodo) {
        if (nodo == null) {
            return 0;
        }

        if (nodo.esHoja()) {
            return 1;
        }

        return contarLuchadores(nodo.getIzquierdo())
                + contarLuchadores(nodo.getDerecho());
    }

    // Calcula la altura del árbol en niveles.
    static int altura(NodoArbol<String> nodo) {
        if (nodo == null) {
            return 0;
        }

        return 1 + Math.max(
                altura(nodo.getIzquierdo()),
                altura(nodo.getDerecho())
        );
    }

    public static void main(String[] args) {

        // PASO 1: crear los nodos.
        NodoArbol<String> finalTorneo =
                new NodoArbol<>("Final");

        NodoArbol<String> semi1 =
                new NodoArbol<>("Semifinal 1");

        NodoArbol<String> semi2 =
                new NodoArbol<>("Semifinal 2");

        // PASO 2: conectar los nodos.
        finalTorneo.setIzquierdo(semi1);
        finalTorneo.setDerecho(semi2);

        semi1.setIzquierdo(new NodoArbol<>("Scorpion"));
        semi1.setDerecho(new NodoArbol<>("Sub-Zero"));

        semi2.setIzquierdo(new NodoArbol<>("Liu Kang"));
        semi2.setDerecho(new NodoArbol<>("Raiden"));

        // PASO 3: mostrar el árbol.
        System.out.println("=== TORNEO MORTAL KOMBAT ===");
        mostrar(finalTorneo, 0);

        // PASO 4: probar los métodos.
        System.out.println();
        System.out.println(
                "Luchadores inscritos: "
                + contarLuchadores(finalTorneo)
        );

        System.out.println(
                "Rondas (altura): " + altura(finalTorneo)
        );

        System.out.println(
                "¿Scorpion es hoja? "
                + semi1.getIzquierdo().esHoja()
        );

        System.out.println(
                "¿Semifinal 1 es hoja? " + semi1.esHoja()
        );

        // PASO 5: simular los resultados de las semifinales.
        semi1.setDato("Scorpion");
        semi2.setDato("Liu Kang");

        finalTorneo.setDato("Final: Scorpion vs Liu Kang");

        System.out.println();
        System.out.println("=== DESPUÉS DE LAS SEMIFINALES ===");
        mostrar(finalTorneo, 0);

        System.out.println();
        System.out.println(
                "¡FINISH HIM! Campeón del torneo: Liu Kang"
        );
    }
}
