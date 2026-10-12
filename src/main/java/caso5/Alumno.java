/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caso5;

/**
 *
 * @author eidan
 */
public class Alumno {
    
    private String nombre;
    private double notaParcial1;
    private double notaParcial2;
    private double examenFinal;
    private double notaOriginal;
    private double notaFinal;
    
    public Alumno (String nombre, double notaParcial1, double notaParcial2, double examenFinal){
    this.nombre = nombre;
    this.notaParcial1 = notaParcial1;
    this.notaParcial2 = notaParcial2;
    this.examenFinal = examenFinal;
    this.notaOriginal = calcularNotaOriginal();
    
    this.notaFinal = notaOriginal;
    }
    
    
    public String getNombre(){
        return nombre;
    }
    
    public double getNotaOriginal(){
        return notaOriginal;
    }
    
    public double getNotaFinal(){
        return notaFinal;
    }
    
    
    // METODOS
    
    public double calcularNotaOriginal(){
        return notaParcial1 * 0.3 + notaParcial2 * 0.3 + examenFinal * 0.4;
    }
    
    public void aplicarCurvaPuntos(double puntos) {
        notaFinal = Math.min(notaFinal + puntos , 10);
    }
    
    public void aplicarCurvaPorcentual(double porcentaje) {
        double incremento = notaFinal * porcentaje/100;
        notaFinal = Math.min(notaFinal + incremento, 10);
    }
    
    public boolean estaAprobado(){
        return notaFinal >= 6;
    }
}