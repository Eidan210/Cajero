package caso1;

import java.util.Scanner;

/**
 *
 * @author eidan
 */
public class Menu {

    private Scanner sc;
    private Banco banco;
    private CuentaBancaria cuenta;

    public Menu(Banco banco, Scanner sc) {
        this.banco = banco;
        this.sc = sc;
    }

    public boolean iniciarSesion() {
        System.out.println("        Cajero Electronico       ");
        System.out.print("Ingrese el titular: ");
        String titular = sc.next();

        if (banco.buscarCuenta(titular) == null) {
            System.out.println("No existe una cuenta para ese titular");
            return false;
        }

        for (int i = 1; i <= 3; i++) {
            System.out.print("Ingrese su pin: ");
            String pin = sc.next();

            if (banco.validarPin(titular, pin)) {
                cuenta = banco.buscarCuenta(titular);
                System.out.println("Acceso Permitido. Bienvenido " + cuenta.getTitular()
                        + " (" + cuenta.getTipo() + ")");
                return true;
            }

            System.out.println("Pin Incorrecto");
            System.out.println("Intentos restantes: " + (3 - i));
        }

        return false;
    }

    private double leerMonto(String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextDouble()) {
            System.out.println("Ingrese un numero valido (use coma para decimales, ej: 1500,50)");
            sc.next();
            System.out.print(mensaje);
        }
        return sc.nextDouble();
    }

    public void iniciar() {
        if (!iniciarSesion()) {
            return;
        }

        int opcion;

        do {
            System.out.println("\n ----- MENU CAJERO -----");
            System.out.println("1. Consultar Saldo\n"
                    + "2. Depositar\n"
                    + "3. Retirar\n"
                    + "4. Transferir\n"
                    + "5. Estadisticas\n"
                    + "6. Consultar Movimientos\n"
                    + "7. Salir");
            System.out.println("================================\n");

            while (!sc.hasNextInt()) {
                System.err.println("La Opcion Debe Ser Un Numero.");
                sc.next();
                System.out.println("Selecciona una Opcion: ");
            }
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("================================");
                    System.out.println("Su saldo es: " + cuenta.getSaldo());
                    System.out.println("================================\n");
                    break;
                case 2:
                    System.out.println("================================");
                    double monto = leerMonto("Ingrese el monto a depositar: ");
                    double saldoAntes = cuenta.getSaldo();
                    if (cuenta.depositar(monto)) {
                        System.out.println("Deposito Realizado Correctamente");
                        double interes = cuenta.getSaldo() - saldoAntes - monto;
                        if (interes > 0.005) {
                            System.out.printf("Interes ganado: +$%.2f%n", interes);
                        }
                        System.out.println("Nuevo saldo: " + cuenta.getSaldo());
                    } else {
                        System.out.println("Monto no valido");
                    }
                    System.out.println("================================\n");
                    break;
                case 3:
                    System.out.println("================================");
                    double montoRetirar = leerMonto("Cuanto desea Retirar: ");
                    if (cuenta.retirar(montoRetirar)) {
                        System.out.println("Retiro Exitoso");
                        System.out.println("Nuevo saldo: " + cuenta.getSaldo());
                    } else {
                        System.out.println("Monto Invalido Verifique su Saldo..");
                    }
                    System.out.println("================================\n");
                    break;
                case 4:
                    System.out.println("================================");
                    System.out.print("Titular de la cuenta destino: ");
                    String nombreDestino = sc.next();
                    CuentaBancaria destino = banco.buscarCuenta(nombreDestino);

                    if (destino == null) {
                        System.out.println("No existe una cuenta para ese titular");
                    } else if (destino == cuenta) {
                        System.out.println("No puedes transferirte a tu propia cuenta");
                    } else {
                        double montoTransferir = leerMonto("Monto a transferir: ");
                        if (cuenta.transferir(destino, montoTransferir)) {
                            System.out.println("Transferencia Exitosa a " + destino.getTitular());
                            System.out.println("Nuevo saldo: " + cuenta.getSaldo());
                        } else {
                            System.out.println("Monto Invalido Verifique su Saldo..");
                        }
                    }
                    System.out.println("================================\n");
                    break;
                case 5:
                    System.out.println("================================");
                    cuenta.mostrarEstadisticas();
                    System.out.println("================================\n");
                    break;
                case 6:
                    System.out.println("================================");
                    cuenta.mostrarHistorial();
                    System.out.println("================================\n");
                    break;
                case 7:
                    System.out.println("Hasta Luego...");
                    return;
                default:
                    System.out.println("Opciones Incorrectas");
                    System.out.println("Seleccione una opcion del 1 al 7");
            }

        } while (opcion != 7);
    }
}