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
public class Banco {

    private ArrayList<CuentaBancaria> cuentas = new ArrayList<>();

    public void agregarCuenta(CuentaBancaria c) {
        cuentas.add(c);
    }

    public CuentaBancaria buscarCuenta(String titular) {
        for (CuentaBancaria c : cuentas) {
            if (c.getTitular().equalsIgnoreCase(titular)) {
                return c;
            }
        }
        return null;
    }

    public boolean validarPin(String titular, String pin) {
        CuentaBancaria c = buscarCuenta(titular);
        return c != null && c.validarPin(pin);
    }
}