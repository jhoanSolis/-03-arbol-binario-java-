import java.util.ArrayList;
import java.util.List;

/**
 * Guía 2 - Anatomía y propiedades de un árbol.
 * Construye un árbol de 12 nodos y 4 niveles (niveles 0 a 3) y calcula:
 * grado, profundidad, altura, hojas, nodos internos y caminos.
 *
 * Convención: la raíz está en el nivel 0 (profundidad 0).
 * Se miden las longitudes en ARISTAS (enlaces padre-hijo).
 */
public class Guia2Arboles {

    // ------------------------------------------------------------------
    // MÉTODOS DE MEDICIÓN
    // ------------------------------------------------------------------

    /**
     * ALTURA de un nodo: longitud del camino más largo desde el nodo hasta una hoja.
     * Algoritmo recursivo:
     *  - Caso base: una hoja tiene altura 0.
     *  - Caso recursivo: 1 + la mayor altura entre sus hijos.
     *
     * @param nodo nodo desde el cual se mide
     * @return altura del nodo
     */
    static int altura(NodoGeneral<?> nodo) {
        // Caso base: sin hijos -> no hay camino hacia abajo
        if (nodo.getHijos().isEmpty()) return 0;

        int max = 0; // Guardará la altura del hijo más "profundo"
        for (NodoGeneral<?> hijo : nodo.getHijos()) {
            // Calculamos la altura de cada hijo y nos quedamos con la mayor
            max = Math.max(max, altura(hijo));
        }
        // +1 por la arista que une este nodo con ese hijo
        return 1 + max;
    }

    /**
     * PROFUNDIDAD de un nodo: número de aristas desde la raíz hasta el nodo.
     * Se calcula subiendo por los padres hasta llegar a la raíz.
     *
     * @param nodo nodo a medir
     * @return profundidad (= nivel) del nodo
     */
    static int profundidad(NodoGeneral<?> nodo) {
        int prof = 0;
        NodoGeneral<?> actual = nodo;
        // Mientras exista un padre, subimos un escalón y contamos
        while (actual.getPadre() != null) {
            prof++;
            actual = actual.getPadre();
        }
        return prof;
    }

    /**
     * GRADO de un nodo: cantidad de hijos directos.
     *
     * @param nodo nodo a medir
     * @return número de hijos
     */
    static int grado(NodoGeneral<?> nodo) {
        return nodo.getHijos().size();
    }

    /**
     * GRADO DEL ÁRBOL: el mayor grado entre todos sus nodos.
     *
     * @param nodo raíz (o subárbol) a revisar
     * @return grado máximo encontrado
     */
    static int gradoArbol(NodoGeneral<?> nodo) {
        int max = grado(nodo); // Empezamos con el grado del nodo actual
        for (NodoGeneral<?> hijo : nodo.getHijos()) {
            max = Math.max(max, gradoArbol(hijo)); // Comparamos con los de abajo
        }
        return max;
    }

    /**
     * Recorre el árbol (preorden) y guarda todos los nodos en una lista.
     *
     * @param nodo  nodo actual
     * @param lista lista donde se acumulan los nodos
     */
    static void recolectar(NodoGeneral<String> nodo, List<NodoGeneral<String>> lista) {
        lista.add(nodo);                      // Primero el nodo...
        for (NodoGeneral<String> hijo : nodo.getHijos()) {
            recolectar(hijo, lista);          // ...luego cada subárbol
        }
    }

    /**
     * CAMINO desde la raíz hasta un nodo, escrito como "A -> B -> C".
     * Su longitud en aristas es (cantidad de nodos - 1) = profundidad del nodo.
     *
     * @param nodo nodo destino
     * @return texto con el camino
     */
    static String camino(NodoGeneral<?> nodo) {
        // Subimos hasta la raíz y vamos insertando al inicio del texto
        String ruta = String.valueOf(nodo.getDato());
        NodoGeneral<?> actual = nodo.getPadre();
        while (actual != null) {
            ruta = actual.getDato() + " -> " + ruta;
            actual = actual.getPadre();
        }
        return ruta;
    }

    /**
     * Imprime el árbol con sangría según el nivel, mostrando
     * nivel, grado, profundidad y altura de cada nodo.
     *
     * @param nodo  nodo actual
     * @param nivel nivel actual (la raíz empieza en 0)
     */
    static void imprimir(NodoGeneral<?> nodo, int nivel) {
        // "  " repetido 'nivel' veces genera la sangría
        String sangria = "    ".repeat(nivel);
        String tipo = nodo.esRaiz() ? "RAÍZ" : (nodo.esHoja() ? "HOJA" : "INTERNO");
        System.out.println(sangria + "- " + nodo.getDato()
                + "  [" + tipo + " | nivel=" + nivel
                + " | grado=" + grado(nodo)
                + " | prof=" + profundidad(nodo)
                + " | altura=" + altura(nodo) + "]");
        for (NodoGeneral<?> hijo : nodo.getHijos()) {
            imprimir(hijo, nivel + 1);
        }
    }

    // ------------------------------------------------------------------
    // PROGRAMA PRINCIPAL
    // ------------------------------------------------------------------
    @SuppressWarnings("unchecked") // Crear un arreglo genérico genera una advertencia inofensiva
    public static void main(String[] args) {

        // ---------- 1. CONSTRUCCIÓN DEL ÁRBOL (12 nodos) ----------
        // Nivel 0 (1 nodo)
        NodoGeneral<String> universidad = new NodoGeneral<>("Universidad");

        // Nivel 1 (3 nodos)
        NodoGeneral<String> ingenieria = universidad.agregarHijo(new NodoGeneral<>("Ingeniería"));
        NodoGeneral<String> artes      = universidad.agregarHijo(new NodoGeneral<>("Artes"));
        NodoGeneral<String> bienestar  = universidad.agregarHijo(new NodoGeneral<>("Bienestar"));

        // Nivel 2 (4 nodos)
        NodoGeneral<String> software = ingenieria.agregarHijo(new NodoGeneral<>("Software"));
        NodoGeneral<String> sistemas = ingenieria.agregarHijo(new NodoGeneral<>("Sistemas"));
        NodoGeneral<String> pintura  = artes.agregarHijo(new NodoGeneral<>("Pintura"));
        NodoGeneral<String> deportes = bienestar.agregarHijo(new NodoGeneral<>("Deportes"));

        // Nivel 3 (4 nodos)
        NodoGeneral<String> estructuras = software.agregarHijo(new NodoGeneral<>("Estructura de Datos II"));
        NodoGeneral<String> basesDatos  = software.agregarHijo(new NodoGeneral<>("Bases de Datos"));
        NodoGeneral<String> redes       = sistemas.agregarHijo(new NodoGeneral<>("Redes"));
        NodoGeneral<String> oleo        = pintura.agregarHijo(new NodoGeneral<>("Óleo"));

        // ---------- 2. ÁRBOL ROTULADO ----------
        System.out.println("=== ÁRBOL ORIGINAL ===");
        imprimir(universidad, 0);

        // ---------- 3. MÉTRICAS GENERALES ----------
        List<NodoGeneral<String>> todos = new ArrayList<>();
        recolectar(universidad, todos);

        int hojas = 0, internos = 0;
        for (NodoGeneral<String> n : todos) {
            if (n.esHoja()) hojas++; else internos++;
        }

        System.out.println("\n=== MÉTRICAS GENERALES ===");
        System.out.println("Total de nodos      : " + todos.size());
        System.out.println("Hojas               : " + hojas);
        System.out.println("Nodos internos      : " + internos + " (incluye la raíz)");
        System.out.println("Grado del árbol     : " + gradoArbol(universidad));
        System.out.println("Altura del árbol    : " + altura(universidad));

        // ---------- 4. PROFUNDIDAD DE CINCO NODOS ----------
        System.out.println("\n=== PROFUNDIDAD DE CINCO NODOS ===");
        NodoGeneral<String>[] cinco = new NodoGeneral[]{
            universidad, ingenieria, software, estructuras, deportes
        };
        for (NodoGeneral<String> n : cinco) {
            System.out.println(n.getDato() + " -> profundidad " + profundidad(n));
        }

        // ---------- 5. EJERCICIO: PROFUNDIDAD DE CADA HOJA ----------
        System.out.println("\n=== PROFUNDIDAD DE CADA HOJA ===");
        for (NodoGeneral<String> n : todos) {
            if (n.esHoja()) {
                System.out.println(n.getDato() + " -> " + profundidad(n));
            }
        }

        // ---------- 6. EJERCICIO: DOS CAMINOS DISTINTOS ----------
        System.out.println("\n=== DOS CAMINOS ===");
        System.out.println("Camino 1: " + camino(estructuras)
                + "  (longitud = " + profundidad(estructuras) + " aristas)");
        System.out.println("Camino 2: " + camino(deportes)
                + "  (longitud = " + profundidad(deportes) + " aristas)");

        // ---------- 7. EJERCICIO: AGREGAR UN NUEVO NIVEL ----------
        // Agregamos un hijo a una hoja del camino más largo -> aparece el nivel 4
        System.out.println("\n=== AGREGANDO UN NUEVO NIVEL ===");
        System.out.println("Altura ANTES : " + altura(universidad));
        estructuras.agregarHijo(new NodoGeneral<>("Árboles AVL"));
        System.out.println("Altura DESPUÉS: " + altura(universidad));
        System.out.println("Altura de Ingeniería ahora: " + altura(ingenieria));
        System.out.println("Altura de Artes (sin cambio): " + altura(artes));
    }
}
