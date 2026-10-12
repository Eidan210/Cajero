/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caso1;

import java.util.*;

/**
 *
 * @author eidan
 */
public abstract class Cuenta {

    private String titular;
    private String pin;
    private double saldo;
    private int cantidadRetiros;
    private int cantidadDepositos;
    private double totalRetirado;
    private double totalDepositado;
    private ArrayList<String> historial = new ArrayList<>();

    public Cuenta(String titular, String pin, double saldo) {
        this.titular = titular;
        this.pin = pin;
        this.saldo = saldo;
        this.cantidadRetiros = 0;
        this.cantidadDepositos = 0;
        this.totalDepositado = 0;
        this.totalRetirado = 0;
    }

    public abstract String getTipo();

    protected double getDisponible() {
        return saldo;
    }

    protected double calcularInteres(double monto) {
        return 0;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public int getcantidadRetiros() {
        return cantidadRetiros;
    }

    public int getcantidaDepositos() {
        return cantidadDepositos;
    }

    public double gettotalDepositado() {
        return totalDepositado;
    }

    public double gettotalRetirado() {
        return totalRetirado;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setcantidadRetiros(int cantidadRetiros) {
        this.cantidadRetiros = cantidadRetiros;
    }

    public void settotalRetirado(double totalRetirado) {
        this.totalRetirado = totalRetirado;
    }

    public void settotalDepositado(double totalDepositado) {
        this.totalDepositado = totalDepositado;
    }

    public boolean validarPin(String pinIngresado) {
        return pin.equals(pinIngresado);
    }

    public boolean depositar(double monto) {
        if (monto > 0) {
            double interes = calcularInteres(monto);
            saldo += monto + interes;
            cantidadDepositos++;
            totalDepositado += monto;
            if (interes > 0) {
                registrar("Deposito: +$" + monto + " (+$" + interes + " de interes)");
            } else {
                registrar("Deposito: +$" + monto);
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean retirar(double monto) {
        if (monto <= getDisponible() && monto > 0) {
            saldo -= monto;
            cantidadRetiros++;
            totalRetirado += monto;
            registrar("Retiro: -$" + monto);
            return true;
        } else {
            return false;
        }
    }

    public boolean transferir(Cuenta destino, double monto) {
        if (destino == this || monto <= 0 || monto > getDisponible()) {
            return false;
        }
        saldo -= monto;
        destino.saldo += monto;
        registrar("Transferencia a " + destino.titular + ": -$" + monto);
        destino.registrar("Transferencia de " + titular + ": +$" + monto);
        return true;
    }

    protected void registrar(String texto) {
        historial.add(texto);
        if (historial.size() > 5) {
            historial.remove(0);
        }
    }

    public void mostrarHistorial() {
        if (historial.isEmpty()) {
            System.out.println("Aun no hay movimientos");
            return;
        }
        System.out.println("Ultimos movimientos:");
        for (String m : historial) {
            System.out.println("- " + m);
        }
    }

    public void mostrarEstadisticas() {
        System.out.println("\nEstadisticas Movimientos");
        System.out.println("- Cantidad Depositos: " + cantidadDepositos);
        System.out.println("- Cantidad Retiros: " + cantidadRetiros);
        System.out.println("- Total Depositados: " + totalDepositado);
        System.out.println("- Total Retirado: " + totalRetirado);
    }
}