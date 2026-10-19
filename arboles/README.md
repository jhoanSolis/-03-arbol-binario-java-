# Guía 2 – Anatomía y propiedades de un árbol

**Asignatura:** Estructura de Datos II · **Programa:** Ingeniería de Software · **Semestre:** IV
**Ecosistema:** Java / GitHub
**Temática:** catálogo de una tienda de computadores (TecnoPC)

## Objetivo

Identificar y calcular raíz, hojas, nodos internos, grado, nivel, profundidad, altura y caminos
en un árbol de 15 nodos y 4 niveles, e implementar un método recursivo que calcule la altura.

## Convenciones

- La raíz está en el nivel 0 y su profundidad es 0 (nivel = profundidad).
- Una hoja tiene altura 0.
- Altura, profundidad y caminos se miden en **aristas**.
- Número de niveles = altura del árbol + 1.

## Árbol rotulado

```text
TecnoPC                               [RAÍZ,    nivel 0]
├── Computadores                      [INTERNO, nivel 1]
│   ├── Portátiles                    [INTERNO, nivel 2]
│   │   ├── Gamer                     [HOJA,    nivel 3]
│   │   └── Oficina                   [HOJA,    nivel 3]
│   └── Escritorio                    [HOJA,    nivel 2]
├── Componentes                       [INTERNO, nivel 1]
│   ├── Procesadores                  [INTERNO, nivel 2]
│   │   ├── Intel                     [HOJA,    nivel 3]
│   │   └── AMD                       [HOJA,    nivel 3]
│   └── Memoria RAM                   [HOJA,    nivel 2]
├── Periféricos                       [INTERNO, nivel 1]
│   └── Monitores                     [HOJA,    nivel 2]
└── Servicios                         [INTERNO, nivel 1]
    └── Soporte técnico               [HOJA,    nivel 2]
```

**Relaciones de parentesco (ejemplos):**

- Padre de Gamer: Portátiles · Hermano de Gamer: Oficina · Ancestros de Gamer: Portátiles, Computadores, TecnoPC.
- Hijos de Procesadores: Intel y AMD · Hermano de Procesadores: Memoria RAM.
- TecnoPC es la raíz: no tiene padre ni hermanos.

## Tabla de métricas

| Nodo | Tipo | Grado | Nivel | Profundidad | Altura |
|---|---|---|---|---|---|
| TecnoPC | Raíz | 4 | 0 | 0 | 3 |
| Computadores | Interno | 2 | 1 | 1 | 2 |
| Portátiles | Interno | 2 | 2 | 2 | 1 |
| Gamer | Hoja | 0 | 3 | 3 | 0 |
| Oficina | Hoja | 0 | 3 | 3 | 0 |
| Escritorio | Hoja | 0 | 2 | 2 | 0 |
| Componentes | Interno | 2 | 1 | 1 | 2 |
| Procesadores | Interno | 2 | 2 | 2 | 1 |
| Intel | Hoja | 0 | 3 | 3 | 0 |
| AMD | Hoja | 0 | 3 | 3 | 0 |
| Memoria RAM | Hoja | 0 | 2 | 2 | 0 |
| Periféricos | Interno | 1 | 1 | 1 | 1 |
| Monitores | Hoja | 0 | 2 | 2 | 0 |
| Servicios | Interno | 1 | 1 | 1 | 1 |
| Soporte técnico | Hoja | 0 | 2 | 2 | 0 |

**Resumen:** 15 nodos · 8 hojas · 7 nodos internos (incluye la raíz) · grado del árbol = 4 · altura = 3 · 4 niveles.
Comprobación: la suma de los grados es 14, que es igual a n − 1.

### Profundidad de cinco nodos

TecnoPC = 0 · Computadores = 1 · Portátiles = 2 · Gamer = 3 · Soporte técnico = 2

## Algoritmo de altura (recursivo)

```java
public static int altura(NodoGeneral<?> nodo) {
    if (nodo.getHijos().isEmpty()) return 0;   // caso base: hoja
    int max = 0;
    for (NodoGeneral<?> hijo : nodo.getHijos()) {
        max = Math.max(max, altura(hijo));     // altura del hijo más profundo
    }
    return 1 + max;                            // +1 por la arista hacia ese hijo
}
```

**Explicación:** una hoja no tiene camino hacia abajo, así que su altura es 0 (caso base).
Para cualquier otro nodo, cada hijo calcula su propia altura, se toma la mayor y se suma 1.

**Traza para `altura(Computadores)`:**

1. Computadores tiene dos hijos: Portátiles y Escritorio.
2. `altura(Escritorio)` = 0 porque es una hoja.
3. `altura(Portátiles)` mira a Gamer y Oficina, ambas con altura 0, y devuelve 1 + 0 = 1.
4. `max` = máx(1, 0) = 1, así que `altura(Computadores)` = 1 + 1 = **2**.

Para `altura(TecnoPC)`, el hijo más alto es Computadores (2), por lo que la altura del árbol es 1 + 2 = **3**.
La comparación manual y la calculada por Java coinciden: ver `salida_esperada.txt`.

## Ejercicios

**1. Profundidad de cada hoja**

| Hoja | Profundidad |
|---|---|
| Gamer, Oficina, Intel, AMD | 3 |
| Escritorio, Memoria RAM, Monitores, Soporte técnico | 2 |

**2. Dos caminos distintos**

- TecnoPC → Computadores → Portátiles → Gamer: longitud **3** aristas (4 nodos).
- TecnoPC → Servicios → Soporte técnico: longitud **2** aristas (3 nodos).

**3. Agregar un nuevo nivel**

Se agrega el producto **ASUS ROG Strix** como hijo de Gamer, que está en la rama más larga:

| Medida | Antes | Después |
|---|---|---|
| Altura de TecnoPC | 3 | 4 |
| Altura de Computadores | 2 | 3 |
| Altura de Servicios | 1 | 1 |
| Niveles | 4 | 5 |

La altura del árbol aumenta porque el nodo nuevo queda en el camino más largo. Cambia la altura de todos
los ancestros del nodo nuevo (Gamer, Portátiles, Computadores y TecnoPC). Servicios no cambia porque no es
ancestro de la rama modificada. Si el nodo se hubiera agregado bajo Soporte técnico (nivel 2), el nuevo nivel
sería el 3 y la altura del árbol seguiría en 3.

## Archivos

- `NodoGeneral.java`: nodo genérico con dato, padre e hijos.
- `MetricasArbol.java`: altura (recursiva), profundidad, grado, conteos, parentesco, caminos e impresión.
- `Main.java`: construcción del árbol y todas las pruebas.
- `Taller_Anatomia_Arbol.md`: taller resuelto (tres árboles dados + organigrama de TecnoPC).
- `organigrama_tecnopc.drawio`: organigrama diseñado en Draw.io.
- `OrganigramaTienda.java`: verifica en Java las métricas del organigrama (`salida_organigrama.txt`).
- `tabla_metricas_manual.csv`: tabla calculada a mano.
- `salida_esperada.txt`: salida verificada del programa.

## Compilar y ejecutar

```bash
javac NodoGeneral.java MetricasArbol.java Main.java
java Main
```
