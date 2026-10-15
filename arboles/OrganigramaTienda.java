/**
 * Taller "Anatomía de un árbol" - parte final: árbol de estructura organizacional.
 * Reutiliza NodoGeneral y MetricasArbol para verificar las métricas calculadas a mano.
 *
 * Cada nodo es un cargo de la tienda TecnoPC y cada arista indica "reporta a".
 */
public class OrganigramaTienda {

    public static void main(String[] args) {

        // 1. Crear un nodo por cada cargo
        NodoGeneral<String> nGerenteGeneral = new NodoGeneral<>("Gerente General");
        NodoGeneral<String> nDirectorComercial = new NodoGeneral<>("Director Comercial");
        NodoGeneral<String> nJefeDeVentas = new NodoGeneral<>("Jefe de Ventas");
        NodoGeneral<String> nAsesorDeVentas = new NodoGeneral<>("Asesor de Ventas");
        NodoGeneral<String> nCajero = new NodoGeneral<>("Cajero");
        NodoGeneral<String> nJefeDeMercadeo = new NodoGeneral<>("Jefe de Mercadeo");
        NodoGeneral<String> nDirectorTecnico = new NodoGeneral<>("Director Técnico");
        NodoGeneral<String> nJefeDeSoporte = new NodoGeneral<>("Jefe de Soporte");
        NodoGeneral<String> nTecnicoDeSoporte = new NodoGeneral<>("Técnico de Soporte");
        NodoGeneral<String> nJefeDeEnsamble = new NodoGeneral<>("Jefe de Ensamble");
        NodoGeneral<String> nTecnicoEnsamblador = new NodoGeneral<>("Técnico Ensamblador");
        NodoGeneral<String> nDirectorAdministrativo = new NodoGeneral<>("Director Administrativo");
        NodoGeneral<String> nContador = new NodoGeneral<>("Contador");
        NodoGeneral<String> nJefeDeBodega = new NodoGeneral<>("Jefe de Bodega");
        NodoGeneral<String> nAuxiliarDeBodega = new NodoGeneral<>("Auxiliar de Bodega");

        // 2. Enlazar la jerarquía: el padre es el jefe y los hijos son sus subordinados
        nGerenteGeneral.agregarHijo(nDirectorComercial);
        nGerenteGeneral.agregarHijo(nDirectorTecnico);
        nGerenteGeneral.agregarHijo(nDirectorAdministrativo);
        nDirectorComercial.agregarHijo(nJefeDeVentas);
        nDirectorComercial.agregarHijo(nJefeDeMercadeo);
        nJefeDeVentas.agregarHijo(nAsesorDeVentas);
        nJefeDeVentas.agregarHijo(nCajero);
        nDirectorTecnico.agregarHijo(nJefeDeSoporte);
        nDirectorTecnico.agregarHijo(nJefeDeEnsamble);
        nJefeDeSoporte.agregarHijo(nTecnicoDeSoporte);
        nJefeDeEnsamble.agregarHijo(nTecnicoEnsamblador);
        nDirectorAdministrativo.agregarHijo(nContador);
        nDirectorAdministrativo.agregarHijo(nJefeDeBodega);
        nJefeDeBodega.agregarHijo(nAuxiliarDeBodega);

        // 3. Mostrar el organigrama rotulado
        System.out.println("=== ORGANIGRAMA TECNOPC ===");
        MetricasArbol.mostrarArbol(nGerenteGeneral);

        // 4. Propiedades generales
        System.out.println("\n=== PROPIEDADES ===");
        System.out.println("Total de cargos (nodos): " + MetricasArbol.contarNodos(nGerenteGeneral));
        System.out.println("Hojas: " + MetricasArbol.contarHojas(nGerenteGeneral));
        System.out.println("Grado del árbol: " + MetricasArbol.gradoArbol(nGerenteGeneral));
        System.out.println("Altura: " + MetricasArbol.altura(nGerenteGeneral));
        System.out.println("Niveles: " + (MetricasArbol.altura(nGerenteGeneral) + 1));

        // 5. Tabla completa de métricas
        System.out.println("\n=== TABLA DE MÉTRICAS ===");
        MetricasArbol.mostrarTablaMetricas(nGerenteGeneral);

        // 6. Camino de mando desde la gerencia hasta un técnico
        System.out.println("\n=== LÍNEA DE MANDO ===");
        java.util.List<NodoGeneral<String>> ruta = MetricasArbol.camino(nTecnicoDeSoporte);
        System.out.println(MetricasArbol.rutaComoTexto(ruta));
        System.out.println("Longitud: " + MetricasArbol.longitudCamino(ruta) + " aristas");
    }
}
