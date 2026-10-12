/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caso1;

/**
 *
 * @author eidan
 */
public class CuentaBancaria extends Cuenta {

    public CuentaBancaria(String titular, String pin, double saldo) {
        super(titular, pin, saldo);
    }

    @Override
    public String getTipo() {
        return "Cuenta Bancaria";
    }
}