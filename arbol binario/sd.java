package sdsdw;

/**
 * Laboratorio: "Mi primer árbol binario"
 * Estructura de Datos II
 *
 * Torneo de Mortal Kombat usando un árbol binario:
 * las hojas son los luchadores y los nodos internos son las rondas.
 */
public class sd {

    // Imprime el árbol girado 90 grados (la raíz queda a la izquierda).
    // "nivel" controla cuántos espacios de sangría lleva cada nodo.
    static void mostrar(NodoArbol<String> nodo, int nivel) {
        if (nodo == null) {   // caso base: no hay nodo
            return;
        }

        // Primero se imprime el hijo derecho (queda arriba).
        mostrar(nodo.getDerecho(), nivel + 1);

        // Después se imprime el nodo actual con su sangría.
        System.out.println("    ".repeat(nivel) + nodo.getDato());

        // Finalmente se imprime el hijo izquierdo (queda abajo).
        mostrar(nodo.getIzquierdo(), nivel + 1);
    }

    // Cuenta las hojas del árbol (cada hoja es un luchador).
    static int contarLuchadores(NodoArbol<String> nodo) {
        if (nodo == null) {   // caso base: árbol vacío
            return 0;
        }

        if (nodo.esHoja()) {  // caso base: una hoja cuenta como 1
            return 1;
        }

        // Si no es hoja, se suman las hojas de ambos lados.
        return contarLuchadores(nodo.getIzquierdo())
                + contarLuchadores(nodo.getDerecho());
    }

    // Calcula la altura del árbol en niveles (cada nivel es una ronda).
    static int altura(NodoArbol<String> nodo) {
        if (nodo == null) {   // caso base: árbol vacío tiene altura 0
            return 0;
        }

        // 1 (este nodo) + la altura del lado más profundo.
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
        // La final tiene dos semifinales, y cada semifinal tiene dos luchadores.
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
                + contarLuchadores(finalTorneo)   // debe dar 4
        );

        System.out.println(
                "Rondas (altura): " + altura(finalTorneo)   // debe dar 3
        );

        System.out.println(
                "¿Scorpion es hoja? "
                + semi1.getIzquierdo().esHoja()   // true: no tiene hijos
        );

        System.out.println(
                "¿Semifinal 1 es hoja? " + semi1.esHoja()   // false: tiene dos hijos
        );

        // PASO 5: simular los resultados de las semifinales.
        // El ganador de cada semifinal pasa a ocupar el nodo de la ronda.
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
