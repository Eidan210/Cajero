/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caso2;

import java.util.*;
import java.io.*;

/**
 *
 * @author eidan
 */
public class Curso {

    private ArrayList<Estudiante> estudiantes = new ArrayList<>();

    public void agregarEstudiante(Estudiante e) {
        estudiantes.add(e);
    }

    public void guardarReporte(String nombreArchivo) {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes para el reporte");
            return;
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter(nombreArchivo))) {
            pw.println("--- Reporte del Curso ---");
            for (Estudiante e : estudiantes) {
                pw.printf("%s | Promedio: %.2f%n", e.getNombre(), e.getPromedio());
            }
            pw.println("--- Estadisticas Generales ---");
            int aprobados = cantidadAprobados();
            int reprobados = estudiantes.size() - aprobados;
            Estudiante mayor = mayorPromedio();
            Estudiante menor = menorPromedio();

            pw.printf("Promedio general: %.2f%n", promedioGeneral());
            pw.println("Aprobados: " + aprobados);
            pw.println("Reprobados: " + reprobados);
            pw.printf("Mayor promedio: %s (%.2f)%n", mayor.getNombre(), mayor.getPromedio());
            pw.printf("Menor promedio: %s (%.2f)%n", menor.getNombre(), menor.getPromedio());

            System.out.println("Reporte Guardado en " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar el reporte: " + e.getMessage());
        }
    }

    public boolean modificarNota(String nombreEstudiante, String nombreAsignatura, double notaNueva) {
        Estudiante e = buscarEstudiante(nombreEstudiante);
        if (e == null) {
            return false;
        }
        return e.modificarNota(nombreAsignatura, notaNueva);
    }

    public Estudiante buscarEstudiante(String nombre) {
        for (Estudiante e : estudiantes) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                return e;
            }
        }
        return null;
    }

    public double promedioGeneral() {
        double suma = 0;
        for (Estudiante e : estudiantes) {
            suma += e.getPromedio();
        }
        return suma / estudiantes.size();
    }

    public int cantidadAprobados() {
        int aprobados = 0;
        for (Estudiante e : estudiantes) {
            if (e.aprobo()) {
                aprobados++;
            }
        }
        return aprobados;
    }

    public Estudiante mayorPromedio() {
        Estudiante mayor = estudiantes.get(0);
        for (Estudiante e : estudiantes) {
            if (e.getPromedio() > mayor.getPromedio()) {
                mayor = e;
            }
        }
        return mayor;
    }

    public Estudiante menorPromedio() {
        Estudiante menor = estudiantes.get(0);
        for (Estudiante e : estudiantes) {
            if (e.getPromedio() < menor.getPromedio()) {
                menor = e;
            }
        }
        return menor;
    }

    public void mostrarEstadisticas() {
        if (estudiantes.size() == 0) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        int aprobados = cantidadAprobados();
        int reprobados = estudiantes.size() - aprobados;
        Estudiante mayor = mayorPromedio();
        Estudiante menor = menorPromedio();

        System.out.println("\n--- Estadisticas del grupo ---");
        System.out.printf("Promedio general: %.2f%n", promedioGeneral());
        System.out.println("Aprobados: " + aprobados);
        System.out.println("Reprobados: " + reprobados);
        System.out.printf("Mayor promedio: %s (%.2f)%n", mayor.getNombre(), mayor.getPromedio());
        System.out.printf("Menor promedio: %s (%.2f)%n", menor.getNombre(), menor.getPromedio());
        System.out.println("--------------------------------\n");
    }
}
