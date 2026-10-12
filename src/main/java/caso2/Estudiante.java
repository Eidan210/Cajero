/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caso2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author eidan
 */
public class Estudiante {

    private String nombre;
    private ArrayList<Asignatura> asignaturas = new ArrayList<>();
    private double promedio;
    private int asistencia;

    public Estudiante(String nombre, int asistencia) {
        this.nombre = nombre;
        this.asistencia = asistencia;
    }

    public static Estudiante[] leerEstudiantes(Scanner sc) {
        int cantidadEstudiantes = leerEntero(sc, "Pon la cantidad de estudiantes a Añadir: ");
        Estudiante[] estudiantes = new Estudiante[cantidadEstudiantes];

        for (int i = 0; i < cantidadEstudiantes; i++) {
            System.out.print("Ingrese Su Nombre: ");
            String nombre = sc.next();
            int asistencia = leerEntero(sc, "Cuantas faltas tuvo: ");
            Estudiante e = new Estudiante(nombre, asistencia);

            int cantidadAsignaturas = leerEntero(sc, "Cuantas Asignaturas: ");

            for (int n = 0; n < cantidadAsignaturas; n++) {
                System.out.print("Ingrese el nombre de la asignatura " + (n + 1) + ": ");
                String nombreAsignatura = sc.next();

                double nota = leerNota(sc, "nota: ");

                e.agregarAsignatura(new Asignatura(nombreAsignatura, nota));
            }
            estudiantes[i] = e;
        }
        return estudiantes;
    }

    private static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int valor = sc.nextInt();
                if (valor >= 0) {
                    return valor;
                }
                System.out.println("Debe ser un numero mayor o igual a 0");
            } catch (InputMismatchException e) {
                System.out.println("Eso no es un número entero, intente de nuevo");
                sc.next();
            }
        }
    }

    private static double leerNota(Scanner sc, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                double nota = sc.nextDouble();
                if (nota >= 0 && nota <= 10) {
                    return nota;
                }
                System.out.println("La nota debe estar entre 0 y 10");
            } catch (InputMismatchException e) {
                System.out.println("Eso no es un número, use coma para los decimales (ej: 4,5)");
                sc.next();
            }
        }
    }

    public void agregarAsignatura(Asignatura a) {
        asignaturas.add(a);
        calcularPromedio();
    }
    
    public boolean modificarNota(String nombreAsignatura, double nuevaNota){
        for(Asignatura a : asignaturas){
            if(a.getNombre().equalsIgnoreCase(nombreAsignatura)){
                a.setNotaFinal(nuevaNota);
                calcularPromedio();
                return true;
            }
        }
        return false;
    }

    public void calcularPromedio() {
        double sumaNotas = 0;
        if (asignaturas.isEmpty()) {
            promedio = 0;
            return;
        }
        for (Asignatura a : asignaturas) {
            sumaNotas += a.getNotaFinal();
        }
        promedio = sumaNotas / asignaturas.size();
    }

    public boolean aprobo() {
        return promedio >= 6 && asistencia < 10;
    }
        // getters
    public String getNombre() {
        return nombre;
    }

    public double getPromedio() {
        return promedio;
    }

    public int getAsistencia() {
        return asistencia;
    }
}