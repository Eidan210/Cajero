/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caso1;

/**
 *
 * @author eidan
 */
public class CuentaCorriente extends CuentaBancaria {

    private double limiteSobregiro;

    public CuentaCorriente(String titular, String pin, double saldo, double limiteSobregiro) {
        super(titular, pin, saldo);
        this.limiteSobregiro = limiteSobregiro;
    }

    public double getLimiteSobregiro() {
        return limiteSobregiro;
    }

    @Override
    protected double getDisponible() {
        return getSaldo() + limiteSobregiro;
    }

    @Override
    public String getTipo() {
        return "Cuenta Corriente";
    }
}