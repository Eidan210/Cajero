/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caso1;

/**
 *
 * @author eidan
 */
public class CuentaAhorro extends CuentaBancaria {

    private static final double TASA_INTERES = 0.01;

    public CuentaAhorro(String titular, String pin, double saldo) {
        super(titular, pin, saldo);
    }

    @Override
    protected double calcularInteres(double monto) {
        return monto * TASA_INTERES;
    }

    @Override
    public String getTipo() {
        return "Cuenta de Ahorro";
    }
}