import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Métodos para medir un árbol general (Guía 2).
 *
 * Convenciones usadas en todo el proyecto:
 *  - La raíz está en el nivel 0 y su profundidad es 0.
 *  - Nivel = profundidad.
 *  - Una hoja tiene altura 0.
 *  - Altura, profundidad y caminos se miden en ARISTAS (enlaces padre-hijo).
 *  - Cantidad de niveles = altura del árbol + 1.
 */
public class MetricasArbol {

    // ==================================================================
    // ALTURA  (algoritmo recursivo pedido por la guía)
    // ==================================================================

    /**
     * ALTURA de un nodo: longitud del camino más largo hacia una hoja.
     *
     * Funcionamiento:
     *  1. Caso base: si el nodo no tiene hijos es una hoja y su altura es 0.
     *  2. Caso recursivo: se calcula la altura de CADA hijo y se queda con la mayor.
     *  3. Se suma 1 por la arista que baja del nodo hasta ese hijo.
     *
     * @param nodo nodo desde el cual se mide
     * @return altura del nodo
     */
    public static int altura(NodoGeneral<?> nodo) {
        // Caso base: una hoja no tiene camino hacia abajo
        if (nodo.getHijos().isEmpty()) {
            return 0;
        }

        int max = 0; // Altura del hijo más profundo encontrado hasta ahora
        for (NodoGeneral<?> hijo : nodo.getHijos()) {
            // Llamada recursiva: cada hijo calcula su propia altura
            max = Math.max(max, altura(hijo));
        }

        // +1 por la arista entre este nodo y su hijo más profundo
        return 1 + max;
    }

    // ==================================================================
    // PROFUNDIDAD, NIVEL Y GRADO
    // ==================================================================

    /**
     * PROFUNDIDAD de un nodo: número de aristas desde la raíz hasta él.
     * Se sube de padre en padre contando escalones.
     *
     * @param nodo nodo a medir
     * @return profundidad (igual al nivel)
     */
    public static int profundidad(NodoGeneral<?> nodo) {
        int contador = 0;
        NodoGeneral<?> actual = nodo;
        while (actual.getPadre() != null) { // Mientras no lleguemos a la raíz
            contador++;
            actual = actual.getPadre();
        }
        return contador;
    }

    /**
     * GRADO de un nodo: cantidad de hijos directos.
     *
     * @param nodo nodo a medir
     * @return número de hijos
     */
    public static int gradoNodo(NodoGeneral<?> nodo) {
        return nodo.getHijos().size();
    }

    /**
     * GRADO DEL ÁRBOL: el mayor grado de todos sus nodos.
     *
     * @param raiz raíz del árbol (o de un subárbol)
     * @return grado máximo
     */
    public static int gradoArbol(NodoGeneral<?> raiz) {
        int max = gradoNodo(raiz); // Empieza con el grado de este nodo
        for (NodoGeneral<?> hijo : raiz.getHijos()) {
            max = Math.max(max, gradoArbol(hijo)); // Compara con los de abajo
        }
        return max;
    }

    // ==================================================================
    // CONTEOS Y CLASIFICACIÓN
    // ==================================================================

    /**
     * Cuenta todos los nodos del árbol.
     *
     * @param raiz raíz del árbol
     * @return total de nodos
     */
    public static int contarNodos(NodoGeneral<?> raiz) {
        int total = 1; // Este nodo
        for (NodoGeneral<?> hijo : raiz.getHijos()) {
            total += contarNodos(hijo); // Más los de cada subárbol
        }
        return total;
    }

    /**
     * Cuenta las hojas (nodos sin hijos).
     *
     * @param raiz raíz del árbol
     * @return total de hojas
     */
    public static int contarHojas(NodoGeneral<?> raiz) {
        if (raiz.esHoja()) {
            return 1; // Caso base: esta hoja cuenta como 1
        }
        int total = 0;
        for (NodoGeneral<?> hijo : raiz.getHijos()) {
            total += contarHojas(hijo);
        }
        return total;
    }

    /**
     * Devuelve el tipo de nodo como texto.
     * Un nodo interno es el que tiene al menos un hijo y no es la raíz.
     *
     * @param nodo nodo a clasificar
     * @return "RAÍZ", "INTERNO" o "HOJA"
     */
    public static String tipoNodo(NodoGeneral<?> nodo) {
        if (nodo.esRaiz()) return "RAÍZ";
        if (nodo.esHoja()) return "HOJA";
        return "INTERNO";
    }

    /**
     * Recorre el árbol (preorden) y guarda todos los nodos en una lista.
     *
     * @param nodo  nodo actual
     * @param lista lista donde se acumulan los nodos
     * @param <T>   tipo de dato de los nodos
     */
    public static <T> void recolectar(NodoGeneral<T> nodo, List<NodoGeneral<T>> lista) {
        lista.add(nodo); // Primero este nodo...
        for (NodoGeneral<T> hijo : nodo.getHijos()) {
            recolectar(hijo, lista); // ...y luego cada subárbol
        }
    }

    // ==================================================================
    // RELACIONES DE PARENTESCO
    // ==================================================================

    /**
     * Hermanos de un nodo: los otros hijos de su mismo padre.
     *
     * @param nodo nodo consultado
     * @param <T>  tipo de dato
     * @return lista de hermanos (vacía si es raíz o hijo único)
     */
    public static <T> List<NodoGeneral<T>> hermanos(NodoGeneral<T> nodo) {
        List<NodoGeneral<T>> resultado = new ArrayList<>();
        if (nodo.getPadre() == null) {
            return resultado; // La raíz no tiene hermanos
        }
        for (NodoGeneral<T> otro : nodo.getPadre().getHijos()) {
            if (otro != nodo) { // Comparación por referencia: no incluirse a sí mismo
                resultado.add(otro);
            }
        }
        return resultado;
    }

    /**
     * Ancestros de un nodo: su padre, su abuelo... hasta la raíz.
     *
     * @param nodo nodo consultado
     * @param <T>  tipo de dato
     * @return lista de ancestros, del más cercano al más lejano
     */
    public static <T> List<NodoGeneral<T>> ancestros(NodoGeneral<T> nodo) {
        List<NodoGeneral<T>> resultado = new ArrayList<>();
        NodoGeneral<T> actual = nodo.getPadre();
        while (actual != null) {
            resultado.add(actual);
            actual = actual.getPadre();
        }
        return resultado;
    }

    /**
     * Descendientes de un nodo: todos los nodos que cuelgan de él (sin contarlo).
     *
     * @param nodo nodo consultado
     * @return cantidad de descendientes
     */
    public static int contarDescendientes(NodoGeneral<?> nodo) {
        return contarNodos(nodo) - 1; // Todos los nodos del subárbol menos el propio
    }

    // ==================================================================
    // CAMINOS
    // ==================================================================

    /**
     * Camino desde la raíz hasta un nodo, como lista ordenada de nodos.
     *
     * @param destino nodo final del camino
     * @param <T>     tipo de dato
     * @return lista [raíz, ..., destino]
     */
    public static <T> List<NodoGeneral<T>> camino(NodoGeneral<T> destino) {
        List<NodoGeneral<T>> ruta = new ArrayList<>();
        NodoGeneral<T> actual = destino;
        while (actual != null) { // Subimos hasta pasar la raíz
            ruta.add(actual);
            actual = actual.getPadre();
        }
        Collections.reverse(ruta); // Se construyó al revés: lo invertimos
        return ruta;
    }

    /**
     * Convierte un camino en texto: "A -> B -> C".
     *
     * @param ruta lista de nodos
     * @return texto del camino
     */
    public static String rutaComoTexto(List<? extends NodoGeneral<?>> ruta) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ruta.size(); i++) {
            if (i > 0) sb.append(" -> ");
            sb.append(ruta.get(i).getDato());
        }
        return sb.toString();
    }

    /**
     * Longitud de un camino en aristas = cantidad de nodos - 1.
     *
     * @param ruta lista de nodos del camino
     * @return número de aristas
     */
    public static int longitudCamino(List<?> ruta) {
        return ruta.size() - 1;
    }

    // ==================================================================
    // IMPRESIÓN
    // ==================================================================

    /**
     * Imprime el árbol con ramas dibujadas y el tipo de cada nodo.
     *
     * @param raiz raíz del árbol
     * @param <T>  tipo de dato
     */
    public static <T> void mostrarArbol(NodoGeneral<T> raiz) {
        System.out.println(raiz.getDato() + "  [" + tipoNodo(raiz) + ", nivel 0]");
        mostrarHijos(raiz, "");
    }

    // Imprime los hijos de un nodo; 'prefijo' arrastra las barras verticales
    private static <T> void mostrarHijos(NodoGeneral<T> nodo, String prefijo) {
        List<NodoGeneral<T>> hijos = nodo.getHijos();
        for (int i = 0; i < hijos.size(); i++) {
            NodoGeneral<T> hijo = hijos.get(i);
            boolean ultimo = (i == hijos.size() - 1); // El último hijo usa └──
            System.out.println(prefijo + (ultimo ? "└── " : "├── ")
                    + hijo.getDato() + "  [" + tipoNodo(hijo)
                    + ", nivel " + profundidad(hijo) + "]");
            // Los hijos de este nodo heredan el prefijo (con barra si hay más hermanos)
            mostrarHijos(hijo, prefijo + (ultimo ? "    " : "│   "));
        }
    }

    /**
     * Imprime una tabla con tipo, grado, profundidad y altura de TODOS los nodos.
     *
     * @param raiz raíz del árbol
     */
    public static void mostrarTablaMetricas(NodoGeneral<String> raiz) {
        List<NodoGeneral<String>> todos = new ArrayList<>();
        recolectar(raiz, todos);

        System.out.printf("%-24s %-8s %-6s %-12s %-6s%n",
                "Nodo", "Tipo", "Grado", "Profundidad", "Altura");
        System.out.println("-".repeat(60));
        for (NodoGeneral<String> n : todos) {
            System.out.printf("%-24s %-8s %-6d %-12d %-6d%n",
                    n.getDato(), tipoNodo(n), gradoNodo(n), profundidad(n), altura(n));
        }
    }
}
