package cl.bootcampAndroidTD2024.DTO;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CuentaBancaria {
    private int numeroCuenta;
    private String titular;
    private double saldo;
    private List<MovimientosBancarios> movimientos = new ArrayList<>();

    public CuentaBancaria() {
    }
    public CuentaBancaria(int numeroCuenta, String titular) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = 0;
    }

    public int getNumeroDeCuenta() {
        return numeroCuenta;
    }

    public void setNumeroDeCuenta(int numeroDeCuenta) {
        this.numeroCuenta = numeroDeCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }


    /**
     * Este método permite crear una cuenta bancaria
     * @param nombreCompleto
     * @return CuentaBancaria
     */
    public CuentaBancaria crearCuenta(String nombreCompleto){
        int numeroCuenta = (int)Math.random()*100000;
        return new CuentaBancaria(numeroCuenta,nombreCompleto);
    }

    /**
     * Muestra los datos relacioandos con la cuenta Bancaria
     */
    public void MostrarDatosCuenta(){
        System.out.println("===============================");
        System.out.println("Los datos son: ");
        System.out.println("------------------------------");
        System.out.println("N° CUENTA: "+ numeroCuenta);
        System.out.println("TITULAR: "+ titular);
        System.out.println("SALDO: "+ formatearMoneda(saldo));
    }

    /**
     * Método que permite ingresar dinero a la cuenta bancaria
     * Además crea un movimiento bancario donde guarda la fecha del depósito,
     * el tipo "Depósito" y el dinero ingresado.
     * Finalmente muestre el saldo actualizado.
     * @param dinero
     */
    public void ingresoDinero(double dinero){;
        if(dinero<0){
            System.out.println("No puede ingresar números negativos");
        }else{
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date fechaActual = new Date();
            movimientos.add(new MovimientosBancarios(formato.format(fechaActual),"Depósito",dinero));
            this.saldo += dinero;
            System.out.println("Tu saldo actual es: "+formatearMoneda(saldo));
        }
    }


    /**
     * Método que permite reitrar dinero a la cuenta bancaria
     * Además crea un movimiento bancario donde guarda la fecha del retiro,
     * el tipo "Retiro" y el dinero que quiere retirar.
     * Finalmente muestre el saldo actualizado.
     * @param dinero
     */
    public void retiroDinero(double dinero){
        if(dinero>saldo){
            System.out.println("No tiene saldo suficiente");
        }else if(dinero<0) {
            System.out.println("No puede ingresar un monto negativo");
        }else{
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date fechaActual = new Date();
            this.movimientos.add(new MovimientosBancarios(formato.format(fechaActual),"Retiro",dinero*(-1)));
            this.saldo-=dinero;
            System.out.println("Tu saldo actual es: "+ formatearMoneda(saldo));
        }

    }

    /**
     * Muestra los ingresos y retiros realizados con su fecha correspondiente
     */
    public void verMovimientosBancarios(){
        // Mostrar la lista de movimientos bancarios
        System.out.println("---------------------------------");
        System.out.println("SALDO ACTUAL: " + formatearMoneda(saldo));
        System.out.println("---------------------------------");
        System.out.println("Movimientos Bancarios:");
        System.out.printf("%-20s| %-15s| %-10s\n", "Fecha","Tipo","Monto");
        System.out.println("-------------------------------------------------");
        for (MovimientosBancarios movimiento : movimientos) {
            System.out.printf("%-20s| %-15s| %-10s\n",movimiento.getFecha(), movimiento.getTipo(), formatearMoneda(movimiento.getMonto()));
        }
    }

    /**
     * Permite mostrar el dinero en formato de pesos chilenos
     * @param monto
     * @return montoFormateado
     */
    public String formatearMoneda(double monto) {
        NumberFormat formatoPesosChilenos = DecimalFormat.getCurrencyInstance(new Locale("es", "CL"));
        // Formatear el número en pesos chilenos
        String montoFormateado = formatoPesosChilenos.format(monto);
        return montoFormateado;
    }

    /**
     * Muestra el saldo, en Dólares, Euros o UF
     */
    public void convertirMoneda() {
        double DOLAR=976.95; //equivalencia de 1 dolar a pesos chilenos
        double EURO=1061.21; //equivalencia de 1 euro a pesos chilenos
        double UF=37029.16; //equivalencia de 1 UF a pesos chilenos
        double cambio; //varibale que mostrar el cambio a la moneda

        System.out.println("---------------------------------");
        System.out.println("SALDO ACTUAL: " + formatearMoneda(saldo));
        System.out.println("---------------------------------");

        // Formatear en dólares
        cambio=this.saldo/DOLAR;
        NumberFormat formatoDolar = NumberFormat.getCurrencyInstance(Locale.US);
        String montoEnDolares = formatoDolar.format(cambio);
        System.out.printf("%-20s: %-20s | %-20s: %-10s\n", "Saldo en Dólares", montoEnDolares, "Tipo de cambio", NumberFormat.getCurrencyInstance(Locale.US).format(DOLAR));

        // Formatear en euros
        cambio=this.saldo/EURO;
        NumberFormat formatoEuro = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        String montoEnEuros = formatoEuro.format(cambio);
        System.out.printf("%-20s: %-20s | %-20s: %-10s\n", "Saldo en Euros", montoEnEuros, "Tipo de cambio", NumberFormat.getCurrencyInstance(Locale.GERMANY).format(EURO));

        // Formatear en UF
        cambio= Math.round((this.saldo/UF) * 100.0) / 100.0;
        System.out.printf("%-20s: %-20s | %-20s: %-10s\n", "Saldo en UF", cambio + " UF", "Tipo de cambio", UF + " UF");
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "numeroCuenta=" + numeroCuenta +
                ", titular='" + titular + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}
