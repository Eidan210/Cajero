# Cajero – Práctica Java (25 ejercicios de expansión)

Programa de consola en Java que reúne **5 casos prácticos** en un solo menú. Cada caso tiene su propio paquete y sus 5 ejercicios de expansión de la guía *"Continuación Práctica Java"*.

```
----------- MENU OPCIONES -----------
1. Cajero Automatico
2. Registro Academico
3. Simulación de Tienda Online
4. Agenda de Contactos
5. Sistema de Notas con Curva
6. Salir
```

## Requisitos

- JDK 26 (el que trae Apache NetBeans)
- Maven (incluido en NetBeans)

## Cómo ejecutarlo

**NetBeans:** abrir el proyecto → *Clean and Build* (Shift+F11) → *Run* (F6).

**Terminal:**

```bash
mvn compile
java -cp target/classes com.mycompany.cajero.Cajero
```

> Los decimales se escriben con **coma** (ej. `4,5`), según la configuración regional de Colombia. Si se escribe un punto o texto, el programa avisa y vuelve a preguntar.

## Estructura

| Paquete | Caso | Clases |
|---|---|---|
| `com.mycompany.cajero` | Menú principal | `Cajero` |
| `caso1` | Cajero Automático | `Cuenta` (abstracta), `CuentaBancaria`, `CuentaAhorro`, `CuentaCorriente`, `Banco`, `Menu` |
| `caso2` | Registro Académico | `Estudiante`, `Asignatura`, `Curso` |
| `caso3` | Tienda Online | `Producto`, `ProductoAlimenticio`, `ProductoElectronico`, `Cliente`, `Carrito`, `Inventario` |
| `caso4` | Agenda de Contactos | `Agenda`, `Contacto` |
| `caso5` | Notas con Curva | `Alumno` |

## Ejercicios resueltos

### Caso 1 – Cajero Automático
1. **Transferencias:** `transferir(destino, monto)` valida el saldo y registra el movimiento en ambas cuentas.
2. **Historial:** `ArrayList<String>` con las últimas 5 transacciones (opción *Consultar Movimientos*).
3. **Múltiples cuentas:** se pide el titular antes del PIN para saber con qué cuenta operar.
4. **Clase Banco:** contiene el `ArrayList<CuentaBancaria>` con `buscarCuenta(titular)` y `validarPin(titular, pin)`.
5. **Herencia:** `Cuenta` abstracta → `CuentaBancaria` → `CuentaAhorro` (1% de interés al depositar) y `CuentaCorriente` (límite de sobregiro).

Cuentas de prueba:

| Titular | PIN | Tipo | Saldo inicial |
|---|---|---|---|
| Ana | 1234 | Cuenta de Ahorro | 500000 |
| Luis | 4321 | Cuenta Corriente (sobregiro 200000) | 300000 |
| Sofia | 1111 | Cuenta Bancaria | 800000 |

### Caso 2 – Registro Académico
6. **Asignaturas múltiples:** `Estudiante` usa `ArrayList<Asignatura>`; el promedio es el de todas sus asignaturas.
7. **Clase Curso:** promedio general, aprobados/reprobados, mayor y menor promedio.
8. **Asistencia:** aprueba con promedio ≥ 6 **y** menos de 10 faltas.
9. **Reporte en archivo:** `guardarReporte()` genera `Reporte.txt`.
10. **Búsqueda y modificación:** buscar estudiante y modificar la nota de una asignatura.

### Caso 3 – Tienda Online
11. **Categorías:** cada producto tiene categoría y se puede filtrar por ella.
12. **Descuentos:** 10% si el subtotal supera $100.000.
13. **Herencia:** `ProductoAlimenticio` (fecha de caducidad) y `ProductoElectronico` (meses de garantía).
14. **Clientes:** el carrito pertenece a un `Cliente` y la factura muestra su nombre y email.
15. **Inventario centralizado:** `Carrito` valida y descuenta el stock a través de `Inventario`.

### Caso 4 – Agenda de Contactos
16. **ArrayList:** sin límite de contactos.
17. **Validación:** teléfono de 10 dígitos numéricos, email con `@` y `.`, sin duplicados.
18. **Búsqueda parcial:** encuentra contactos que contengan el texto (sin importar mayúsculas).
19. **Grupos:** cada contacto tiene grupo y se puede listar por grupo.
20. **Guardar y cargar:** la agenda se guarda en `Contactos.txt` al salir y se carga al entrar.

### Caso 5 – Notas con Curva
21. **Curva porcentual:** aumenta la nota en un porcentaje, sin pasar de 10.
22. **Curva por el mejor alumno:** suma a todos `10 - nota más alta`.
23. **Ponderaciones:** parcial 1 (30%), parcial 2 (30%) y examen final (40%).
24. **Excepciones:** `try-catch` de `InputMismatchException`; si se escribe texto, se vuelve a pedir la nota.
25. **Estadísticas:** nueva media, desviación estándar y cuántos pasaron de reprobado a aprobado.

> La curva se aplica solo si el promedio del curso es menor a 6.

## Autor

**Eidan Alexander Carreño Cuadros** – [@Eidan210](https://github.com/Eidan210)
Campuslands, Bucaramanga
