# Taller: «Anatomía de un árbol»

**Asignatura:** Estructura de Datos II · **Programa:** Ingeniería de Software · **Semestre:** IV
**Parte de:** Guía 2 – Anatomía y propiedades de un árbol
**Evidencia:** taller resuelto + árbol diseñado en Draw.io (`organigrama_tecnopc.drawio`)

## Conceptos y convenciones

| Concepto | Definición | Cómo se calcula |
|---|---|---|
| Raíz | Nodo sin padre | Es único |
| Padre / Hijos | Relación directa de arriba hacia abajo | Un nodo tiene un padre y cero o más hijos |
| Hermanos | Nodos con el mismo padre | Otros hijos de mi padre |
| Hoja | Nodo sin hijos | Grado 0 |
| Nodo interno | Nodo con al menos un hijo | Incluye la raíz si tiene hijos |
| Subárbol | Un nodo y todos sus descendientes | Se nombra por su raíz |
| Camino | Secuencia de nodos conectados | Su longitud es el número de aristas (nodos − 1) |
| Grado | Número de hijos de un nodo | El grado del árbol es el mayor grado de sus nodos |
| Nivel | Posición del nodo por capas | Se cuenta desde 0 en la raíz |
| Profundidad | Aristas desde la raíz hasta el nodo | Igual al nivel con esta convención |
| Altura | Aristas del camino más largo desde el nodo hasta una hoja | Hoja = 0; altura del árbol = altura de la raíz |

**Convención usada:** la raíz está en el nivel 0, así que nivel = profundidad y el número de niveles es la altura + 1.
Si tu docente cuenta los niveles desde 1, suma 1 a la columna «Nivel».

## Parte 1. Análisis de árboles dados

### Árbol 1

```text
A
├── B
│   ├── E
│   └── F
│       └── J
├── C
└── D
    ├── G
    ├── H
    └── I
```

**Preguntas resueltas**

1. **Raíz:** A (no tiene padre).
2. **Hojas:** {E, J, C, G, H, I} → 6 hojas.
3. **Nodos internos:** {A, B, F, D} → 4 nodos internos (incluye la raíz).
4. **Padre, hijos y hermanos:**
   - Padre de **G**: D.
   - Hijos de **D**: {G, H, I}.
   - Hermanos de **E**: {F}.
5. **Subárbol con raíz en B:** {B, E, F, J} → 4 nodos y altura 2.
6. **Camino de la raíz a J:** A → B → F → J → longitud **3** aristas (4 nodos).
7. **Camino más largo (raíz a una hoja):** A → B → F → J → longitud **3** aristas, igual a la altura del árbol.
8. **Grado:** el grado de **D** es 3; el grado del árbol es **3** (el mayor grado entre todos los nodos).
9. **Nivel, profundidad y altura de F:** nivel 2, profundidad 2 y altura 1.
10. **Altura del árbol:** 3 · **Número de niveles:** 4.

**Tabla completa de métricas**

| Nodo | Tipo | Padre | Hijos | Hermanos | Grado | Nivel | Profundidad | Altura |
|---|---|---|---|---|---|---|---|---|
| A | Raíz | — | B, C, D | — | 3 | 0 | 0 | 3 |
| B | Interno | A | E, F | C, D | 2 | 1 | 1 | 2 |
| E | Hoja | B | — | F | 0 | 2 | 2 | 0 |
| F | Interno | B | J | E | 1 | 2 | 2 | 1 |
| J | Hoja | F | — | — | 0 | 3 | 3 | 0 |
| C | Hoja | A | — | B, D | 0 | 1 | 1 | 0 |
| D | Interno | A | G, H, I | B, C | 3 | 1 | 1 | 1 |
| G | Hoja | D | — | H, I | 0 | 2 | 2 | 0 |
| H | Hoja | D | — | G, I | 0 | 2 | 2 | 0 |
| I | Hoja | D | — | G, H | 0 | 2 | 2 | 0 |

*Comprobación:* la suma de los grados es 9 = n − 1 = 9.

### Árbol 2

```text
R
├── S
│   ├── U
│   └── V
│       ├── X
│       └── Y
└── T
    └── W
        └── Z
```

**Preguntas resueltas**

1. **Raíz:** R (no tiene padre).
2. **Hojas:** {U, X, Y, Z} → 4 hojas.
3. **Nodos internos:** {R, S, V, T, W} → 5 nodos internos (incluye la raíz).
4. **Padre, hijos y hermanos:**
   - Padre de **X**: V.
   - Hijos de **S**: {U, V}.
   - Hermanos de **U**: {V}.
5. **Subárbol con raíz en T:** {T, W, Z} → 3 nodos y altura 2.
6. **Camino de la raíz a Y:** R → S → V → Y → longitud **3** aristas (4 nodos).
7. **Camino más largo (raíz a una hoja):** R → S → V → X → longitud **3** aristas, igual a la altura del árbol.
8. **Grado:** el grado de **T** es 1; el grado del árbol es **2** (el mayor grado entre todos los nodos).
9. **Nivel, profundidad y altura de V:** nivel 2, profundidad 2 y altura 1.
10. **Altura del árbol:** 3 · **Número de niveles:** 4.

**Tabla completa de métricas**

| Nodo | Tipo | Padre | Hijos | Hermanos | Grado | Nivel | Profundidad | Altura |
|---|---|---|---|---|---|---|---|---|
| R | Raíz | — | S, T | — | 2 | 0 | 0 | 3 |
| S | Interno | R | U, V | T | 2 | 1 | 1 | 2 |
| U | Hoja | S | — | V | 0 | 2 | 2 | 0 |
| V | Interno | S | X, Y | U | 2 | 2 | 2 | 1 |
| X | Hoja | V | — | Y | 0 | 3 | 3 | 0 |
| Y | Hoja | V | — | X | 0 | 3 | 3 | 0 |
| T | Interno | R | W | S | 1 | 1 | 1 | 2 |
| W | Interno | T | Z | — | 1 | 2 | 2 | 1 |
| Z | Hoja | W | — | — | 0 | 3 | 3 | 0 |

*Comprobación:* la suma de los grados es 8 = n − 1 = 8.

### Árbol 3

```text
1
├── 2
├── 3
│   └── 6
│       └── 7
│           └── 8
├── 4
│   ├── 9
│   └── 10
└── 5
```

**Preguntas resueltas**

1. **Raíz:** 1 (no tiene padre).
2. **Hojas:** {2, 8, 9, 10, 5} → 5 hojas.
3. **Nodos internos:** {1, 3, 6, 7, 4} → 5 nodos internos (incluye la raíz).
4. **Padre, hijos y hermanos:**
   - Padre de **7**: 6.
   - Hijos de **1**: {2, 3, 4, 5}.
   - Hermanos de **4**: {2, 3, 5}.
5. **Subárbol con raíz en 3:** {3, 6, 7, 8} → 4 nodos y altura 3.
6. **Camino de la raíz a 8:** 1 → 3 → 6 → 7 → 8 → longitud **4** aristas (5 nodos).
7. **Camino más largo (raíz a una hoja):** 1 → 3 → 6 → 7 → 8 → longitud **4** aristas, igual a la altura del árbol.
8. **Grado:** el grado de **1** es 4; el grado del árbol es **4** (el mayor grado entre todos los nodos).
9. **Nivel, profundidad y altura de 6:** nivel 2, profundidad 2 y altura 2.
10. **Altura del árbol:** 4 · **Número de niveles:** 5.

**Tabla completa de métricas**

| Nodo | Tipo | Padre | Hijos | Hermanos | Grado | Nivel | Profundidad | Altura |
|---|---|---|---|---|---|---|---|---|
| 1 | Raíz | — | 2, 3, 4, 5 | — | 4 | 0 | 0 | 4 |
| 2 | Hoja | 1 | — | 3, 4, 5 | 0 | 1 | 1 | 0 |
| 3 | Interno | 1 | 6 | 2, 4, 5 | 1 | 1 | 1 | 3 |
| 6 | Interno | 3 | 7 | — | 1 | 2 | 2 | 2 |
| 7 | Interno | 6 | 8 | — | 1 | 3 | 3 | 1 |
| 8 | Hoja | 7 | — | — | 0 | 4 | 4 | 0 |
| 4 | Interno | 1 | 9, 10 | 2, 3, 5 | 2 | 1 | 1 | 1 |
| 9 | Hoja | 4 | — | 10 | 0 | 2 | 2 | 0 |
| 10 | Hoja | 4 | — | 9 | 0 | 2 | 2 | 0 |
| 5 | Hoja | 1 | — | 2, 3, 4 | 0 | 1 | 1 | 0 |

*Comprobación:* la suma de los grados es 9 = n − 1 = 9.

**Observación:** el Árbol 2 es binario (grado 2), el Árbol 1 es ancho (grado 3) y el Árbol 3 es el de mayor grado (4)
y el más profundo, con una rama larga que baja hasta el nivel 4.

## Parte 2. Árbol de estructura organizacional (TecnoPC)

Se diseñó el organigrama de una tienda de computadores. Cada nodo es un cargo y cada arista significa «reporta a».
El diseño está en **`organigrama_tecnopc.drawio`** (ábrelo en app.diagrams.net y exporta a PNG o PDF para Moodle).

```text
Gerente General  [Raíz, nivel 0]
├── Director Comercial  [Interno, nivel 1]
│   ├── Jefe de Ventas  [Interno, nivel 2]
│   │   ├── Asesor de Ventas  [Hoja, nivel 3]
│   │   └── Cajero  [Hoja, nivel 3]
│   └── Jefe de Mercadeo  [Hoja, nivel 2]
├── Director Técnico  [Interno, nivel 1]
│   ├── Jefe de Soporte  [Interno, nivel 2]
│   │   └── Técnico de Soporte  [Hoja, nivel 3]
│   └── Jefe de Ensamble  [Interno, nivel 2]
│       └── Técnico Ensamblador  [Hoja, nivel 3]
└── Director Administrativo  [Interno, nivel 1]
    ├── Contador  [Hoja, nivel 2]
    └── Jefe de Bodega  [Interno, nivel 2]
        └── Auxiliar de Bodega  [Hoja, nivel 3]
```

### Identificación de elementos

- **Raíz:** Gerente General.
- **Hojas (7):** {Asesor de Ventas, Cajero, Jefe de Mercadeo, Técnico de Soporte, Técnico Ensamblador, Contador, Auxiliar de Bodega}.
- **Nodos internos (8):** {Gerente General, Director Comercial, Jefe de Ventas, Director Técnico, Jefe de Soporte, Jefe de Ensamble, Director Administrativo, Jefe de Bodega}.
- **Padre de Cajero:** Jefe de Ventas · **Hermanos de Cajero:** {Asesor de Ventas}.
- **Hijos de Director Técnico:** {Jefe de Soporte, Jefe de Ensamble}.
- **Subárbol de Director Comercial:** {Director Comercial, Jefe de Ventas, Asesor de Ventas, Cajero, Jefe de Mercadeo} → 5 nodos, altura 2.
- **Camino 1:** Gerente General → Director Técnico → Jefe de Soporte → Técnico de Soporte → longitud 3.
- **Camino 2:** Gerente General → Director Administrativo → Contador → longitud 2.
- **Grado del árbol:** 3 (el Gerente General tiene 3 directores a cargo).
- **Altura:** 3 · **Niveles:** 4.

### Tabla de métricas

| Cargo | Tipo | Padre | Grado | Nivel | Profundidad | Altura |
|---|---|---|---|---|---|---|
| Gerente General | Raíz | — | 3 | 0 | 0 | 3 |
| Director Comercial | Interno | Gerente General | 2 | 1 | 1 | 2 |
| Jefe de Ventas | Interno | Director Comercial | 2 | 2 | 2 | 1 |
| Asesor de Ventas | Hoja | Jefe de Ventas | 0 | 3 | 3 | 0 |
| Cajero | Hoja | Jefe de Ventas | 0 | 3 | 3 | 0 |
| Jefe de Mercadeo | Hoja | Director Comercial | 0 | 2 | 2 | 0 |
| Director Técnico | Interno | Gerente General | 2 | 1 | 1 | 2 |
| Jefe de Soporte | Interno | Director Técnico | 1 | 2 | 2 | 1 |
| Técnico de Soporte | Hoja | Jefe de Soporte | 0 | 3 | 3 | 0 |
| Jefe de Ensamble | Interno | Director Técnico | 1 | 2 | 2 | 1 |
| Técnico Ensamblador | Hoja | Jefe de Ensamble | 0 | 3 | 3 | 0 |
| Director Administrativo | Interno | Gerente General | 2 | 1 | 1 | 2 |
| Contador | Hoja | Director Administrativo | 0 | 2 | 2 | 0 |
| Jefe de Bodega | Interno | Director Administrativo | 1 | 2 | 2 | 1 |
| Auxiliar de Bodega | Hoja | Jefe de Bodega | 0 | 3 | 3 | 0 |

*Comprobación:* suma de grados = 14 = n − 1 = 14.

### Reflexión

- **¿Por qué es un árbol y no otra estructura?** Cada cargo tiene exactamente un jefe directo (excepto el Gerente General) y no hay ciclos.
- **¿Qué significa la altura?** Es el número máximo de relaciones de mando entre la gerencia y un cargo operativo (3 en este organigrama).
- **¿Qué pasa si se crea un cargo nuevo bajo Técnico de Soporte?** El árbol pasa a 5 niveles y la altura sube a 4; cambian las alturas de todos sus superiores (Jefe de Soporte, Director Técnico y Gerente General).

## Cómo hacer el diagrama en Draw.io

1. Abre app.diagrams.net y elige **Archivo → Abrir desde → Dispositivo** para cargar `organigrama_tecnopc.drawio`.
2. Revisa colores y niveles: rojo = raíz, azul = nodos internos, verde = hojas.
3. Exporta con **Archivo → Exportar como → PNG** (o PDF) y sube la imagen junto con el archivo `.drawio` a la carpeta del repositorio.

## Verificación con Java

`OrganigramaTienda.java` construye el mismo organigrama con `NodoGeneral` y calcula las métricas con `MetricasArbol`.
Los valores de la tabla coinciden con la salida del programa (`salida_organigrama.txt`).
