/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.cajero;

import caso1.Menu;
import caso2.Curso;
import caso1.Banco;
import caso2.Estudiante;
import caso1.CuentaAhorro;
import caso1.CuentaBancaria;
import caso1.CuentaCorriente;
import caso3.Carrito;
import caso3.Cliente;
import caso3.Inventario;
import caso3.Producto;
import caso4.Agenda;
import caso4.Contacto;
import caso5.Alumno;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import java.io.*;

/**
 *
 * @author eidan
 */
public class Cajero {

    public static void main(String[] args) {
        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        Banco banco = new Banco();
        banco.agregarCuenta(new CuentaAhorro("Ana", "1234", 500000));
        banco.agregarCuenta(new CuentaCorriente("Luis", "4321", 300000, 200000));
        banco.agregarCuenta(new CuentaBancaria("Sofia", "1111", 800000));

        while (opcion != 6) {
            System.out.println("\n----------- MENU OPCIONES -----------");
            System.out.println("1. Cajero Automatico\n"
                    + "2. Registro Academico\n"
                    + "3. Simulación de Tienda Online\n"
                    + "4. Agenda de Contactos\n"
                    + "5. Sistema de Notas con Curva\n"
                    + "6. Salir");
            System.out.println("======================================");
            System.out.println("Elija una opcion: ");

            while (!sc.hasNextInt()) {
                System.out.println("\nElija una Opcion Valida");
                sc.next();
                System.out.println("Elija una opcion: ");
            }
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println();
                    Menu menu = new Menu(banco, sc);
                    menu.iniciar();
                    break;

                case 2:
                    System.out.println();
                    Estudiante[] estudiantes = Estudiante.leerEstudiantes(sc);

                    Curso curso = new Curso();
                    for (Estudiante e : estudiantes) {
                        curso.agregarEstudiante(e);
                    }
                    curso.mostrarEstadisticas();
                    curso.guardarReporte("Reporte.txt");

                    //probar modificarNota
                    System.out.print("\n¿Desea modificar una nota? (s/n): ");
                    String respuesta = sc.next();

                    if (respuesta.equalsIgnoreCase("s")) {
                        System.out.print("Nombre del estudiante: ");
                        String nombreEst = sc.next();
                        System.out.print("Nombre de la asignatura: ");
                        String nombreAsig = sc.next();
                        double notaNueva = leerDouble(sc, "Nota nueva: ");

                        if (curso.modificarNota(nombreEst, nombreAsig, notaNueva)) {
                            System.out.println("Nota modificada");
                            curso.mostrarEstadisticas();          // para ver el promedio actualizado
                        } else {
                            System.out.println("No se encontró el estudiante o la asignatura");
                        }
                    }
                    break;

                case 3:
                    tiendaOnline(sc);
                    break;

                case 4:

                    agendaContactos(sc);
                    break;
                case 5:

                    notasConCurva(sc);
                    break;
                case 6:

                    System.out.println("\nSaliendo del programa...\n");
                    break;
                default:

                    System.out.println("\nOpcion Erronea");
                    break;
            }
        }
    }

    public static void tiendaOnline(Scanner sc) {
        Inventario inventario = new Inventario();
        inventario.agregarProducto(new Producto("Camiseta", 30000, 5, "Superiores"));
        inventario.agregarProducto(new Producto("Polo", 30000, 9, "Superiores"));
        inventario.agregarProducto(new Producto("cargo", 70000, 6, "Pantalones"));

        System.out.print("Nombre: ");
        String nombre = sc.next();
        System.out.print("Email: ");
        String email = sc.next();

        Cliente cliente = new Cliente(nombre, email);

        Carrito carrito = new Carrito(cliente, inventario);

        System.out.println();
        inventario.mostrarCatalogo();

        int opcion = 0;

        while (opcion != 5) {
            System.out.println("\n----------- TIENDA ONLINE  -----------");
            System.out.println("1. Catalogo Productos\n"
                    + "2. Comprar Productos\n"
                    + "3. Buscar por Categoria\n"
                    + "4. Factura Final\n"
                    + "5. Salir");
            System.out.println("======================================");
            System.out.println("Elija una opcion: ");

            while (!sc.hasNextInt()) {
                System.out.println("\nElija una Opcion Valida");
                sc.next();
                System.out.println("Elija una opcion: ");
            }
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println();
                    inventario.mostrarCatalogo();
                    break;
                case 2:
                    System.out.print("\nIngrese el numero del producto a comprar: ");
                    while (!sc.hasNextInt()) {
                        System.out.println("Ingrese un numero valido");
                        sc.next();
                    }
                    int compra = sc.nextInt();

                    if (compra >= 1 && compra <= inventario.cantidadProductos()) {
                        System.out.print("Cantidad a comprar del producto: ");
                        while (!sc.hasNextInt()) {
                            System.out.println("Ingrese un numero valido");
                            sc.next();
                        }
                        int cantidad = sc.nextInt();
                        System.out.println();
                        carrito.agregarProducto(inventario.getProducto(compra - 1), cantidad);
                    } else {
                        System.out.println("\nEl producto no existe");
                    }
                    break;
                case 3:
                    System.out.println("\n Ingrese la categoria a filtrar");
                    String categoria = sc.next();
                    System.out.println();

                    boolean hay = false;
                    for (int i = 0; i < inventario.cantidadProductos(); i++) {
                        Producto p = inventario.getProducto(i);
                        if (p.getCategoria().equalsIgnoreCase(categoria)) {
                            System.out.println((i + 1) + " " + p.getNombre());
                            hay = true;
                        }
                    }
                    if (!hay) {
                        System.out.println("No hay productos de esa categoria");
                    }
                    break;
                case 4:
                    System.out.println();
                    carrito.mostrarFactura();
                    break;
                case 5:
                    System.out.println("\nVuelva Pronto a la tienda Online...");
                    break;
                default:
                    System.out.println("\nOpcion Incorrecta/invalida");
            }
        }
    }

    public static void notasConCurva(Scanner sc) {
        int cantidadAlumnos;
        do {
            cantidadAlumnos = leerInt(sc, "\nPon la cantidad de estudiantes a Añadir: ");
            if (cantidadAlumnos < 1) {
                System.out.println("Debe ser al menos 1 estudiante");
            }
        } while (cantidadAlumnos < 1);

        Alumno[] alumnos = new Alumno[cantidadAlumnos];

        for (int i = 0; i < cantidadAlumnos; i++) {

            System.out.print("\nIngrese el nombre del alumno: ");
            String nombre = sc.next();
            double notaParcial1 = leerDouble(sc, "nota del parcial 1: ");

            double notaParcial2 = leerDouble(sc, "nota del parcial 2: ");

            double notaExamen = leerDouble(sc, "nota del Examen: ");

            alumnos[i] = new Alumno(nombre, notaParcial1, notaParcial2, notaExamen);
        }
        double suma = 0.0;

        for (Alumno a : alumnos) {
            suma += a.getNotaOriginal();
        }

        double promedio = suma / cantidadAlumnos;

        System.out.printf("%nPromedio del curso: %.2f%n", promedio);

        double masAlta = alumnos[0].getNotaOriginal();

        if (promedio < 6) {
            int opcion;
            do {
                opcion = leerInt(sc, "Tipo de curva (1 = puntos según el mejor alumno, 2 = porcentual): ");
                if (opcion != 1 && opcion != 2) {
                    System.out.println("Opción inválida, intenta de nuevo");
                }
            } while (opcion != 1 && opcion != 2);
            if (opcion == 1) {
                for (int i = 0; i < cantidadAlumnos; i++) {
                    if (alumnos[i].getNotaOriginal() > masAlta) {
                        masAlta = alumnos[i].getNotaOriginal();
                    }
                }
                double curva = 10 - masAlta;
                System.out.println("Se aplica una curva de +" + curva + " puntos a todos");

                for (Alumno a : alumnos) {
                    a.aplicarCurvaPuntos(curva);
                }
            } else if (opcion == 2) {
                double porcentaje = leerDouble(sc, "Porcentaje de curva: ");

                System.out.println("Se aplica un aumento de nota del " + porcentaje + "% a todos");

                for (Alumno a : alumnos) {
                    a.aplicarCurvaPorcentual(porcentaje);
                }
            }
        }
        double nuevaSuma = 0.0;
        for (Alumno a : alumnos) {
            nuevaSuma += a.getNotaFinal();
        }
        double nuevoPromedio = nuevaSuma / cantidadAlumnos;
        System.out.printf("Nueva media del curso: %.2f%n", nuevoPromedio);

        String estado;

        double sumaCuadrados = 0.0;
        for (Alumno a : alumnos) {
            double diferencia = a.getNotaFinal() - nuevoPromedio;
            sumaCuadrados += diferencia * diferencia;
        }
        double desviacion = Math.sqrt(sumaCuadrados / cantidadAlumnos);
        System.out.printf("Desviación estándar: %.2f%n", desviacion);

        int pasaronAAprobado = 0;

        for (Alumno a : alumnos) {
            if (a.getNotaOriginal() < 6 && a.estaAprobado()) {
                pasaronAAprobado++;
            }
        }
        System.out.println("Pasaron de reprobado a aprobado: " + pasaronAAprobado);

        System.out.println();

        for (Alumno a : alumnos) {
            if (a.estaAprobado()) {
                estado = "APROBÓ";
            } else {
                estado = "REPROBÓ";
            }
            System.out.println("Nombre: " + a.getNombre()
                    + " | Original: " + a.getNotaOriginal()
                    + " | Final: " + a.getNotaFinal()
                    + " | " + estado);
        }
    }

    public static double leerDouble(Scanner sc, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Eso no es un número, use coma para los decimales (ej: 4,5)");
                sc.next();
            }
        }
    }

    public static int leerInt(Scanner sc, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Eso no es un número, intente de nuevo");
                sc.next();
            }
        }
    }

    public static void agendaContactos(Scanner sc) {
        Agenda agenda = new Agenda();
        agenda.cargarDesdeArchivo();
        int opcion = 0;

        while (opcion != 6) {
            System.out.println("\n----- AGENDA -----");
            System.out.println("1. Registrar contacto\n"
                    + "2. Buscar contacto\n"
                    + "3. Listar contactos\n"
                    + "4. Eliminar contacto\n"
                    + "5. Buscar por grupo\n"
                    + "6. Salir");
            System.out.println("==================");
            System.out.println("Elija una opcion: ");
            while (!sc.hasNextInt()) {
                System.out.println("\nElija una Opcion Valida");
                sc.next();
                System.out.println("Elija una opcion: ");
            }
            opcion = sc.nextInt();

            switch (opcion) {
                case 1: {
                    System.out.println("\nIngrese el nombre, telefono, email y grupo del contacto a añadir");

                    String nombre = sc.next();
                    String telefono = sc.next();
                    String email = sc.next();
                    String grupo = sc.next();

                    Contacto c = new Contacto(nombre, telefono, email, grupo);
                    System.out.println();
                    agenda.agregarContacto(c);
                    break;
                }
                case 2: {
                    System.out.print("\nIngrese el nombre para buscar el contacto: ");

                    String nombre = sc.next();

                    ArrayList<Contacto> encontrado = agenda.buscarContacto(nombre);
                    System.out.println();
                    if (encontrado.isEmpty()) {
                        System.out.println("No se encontro el contacto");
                    } else {
                        for (Contacto c : encontrado) {
                            c.mostrarInfo();
                        }
                    }
                    break;
                }
                case 3: {
                    System.out.println();
                    agenda.mostrarTodos();
                    break;
                }
                case 4: {
                    System.out.print("\nIngrese el nombre para eliminar el contacto: ");

                    String nombre = sc.next();

                    System.out.println();
                    agenda.eliminarContacto(nombre);

                    break;
                }
                case 5: {
                    System.out.print("\n Ingrese el grupo a buscar: ");
                    String grupo = sc.next();
                    System.out.println();

                    agenda.mostrarContactosPorGrupo(grupo);
                    break;
                }
                case 6: {
                    agenda.guardarEnArchivo();
                    System.out.println("\nSaliendo...");
                    break;
                }
                default:
                    System.out.println("\nEsa opcion no es valida...");
            }
        }
    }
}