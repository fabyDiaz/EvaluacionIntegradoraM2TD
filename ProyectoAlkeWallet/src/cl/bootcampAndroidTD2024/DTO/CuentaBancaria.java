package cl.bootcampAndroidTD2024.DTO;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Esta clase contiene la cuenta bancaria del cliente. La cuenta tiene un número de cuenta, el titular, el tipo de cuenta
 * que por defecto es una cuenta Vista, el saldo y los movimientos de ingreso y egreso de dinero.
 * @author Fabiola Díaz <a href="https://github.com/fabyDiaz/EvaluacionIntegradoraM2TD">Github Fabiola Díaz</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public class CuentaBancaria {
    private long numeroCuenta;
    private String titular;
    private String tipoCuenta;
    private double saldo;
    private List<MovimientosBancarios> movimientos = new ArrayList<>();

    public CuentaBancaria() {
    }
    public CuentaBancaria(long numeroCuenta, String titular, String tipoCuenta) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.tipoCuenta=tipoCuenta;
        this.saldo = 0;
    }

    public long getNumeroDeCuenta() {
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
        Random random = new Random();
        int numero = 100000 + random.nextInt(900000); // Número de cuenta de 5 dígitos
        numeroCuenta = numero;
        return new CuentaBancaria(numeroCuenta,nombreCompleto,"Vista");
    }

    /**
     * Muestra los datos relacionados con la cuenta Bancaria
     */
    public void mostrarDatosCuenta(){
        System.out.println("N° CUENTA: "+ numeroCuenta);
        System.out.println("TITULAR: "+ titular);
        System.out.println("SALDO: "+ formatearMoneda(saldo));
    }

    /**
     * Método que permite ingresar dinero a la cuenta bancaria
     * Además crea un movimiento bancario donde guarda la fecha del depósito, el tipo y el dinero ingresado.
     * Finalmente muestre el saldo actualizado.
     * @param dinero
     */
    public void ingresoDinero(double dinero, int tipoDeMovimiento){;
        if(dinero<0){
            System.out.println("No puede ingresar números negativos");
        }else{
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date fechaActual = new Date();
            movimientos.add(new MovimientosBancarios(formato.format(fechaActual),tipoIngresoSalidaDinero(tipoDeMovimiento),dinero));
            this.saldo += dinero;
        }
    }


    /**
     * Método que permite reitrar dinero a la cuenta bancaria
     * Además crea un movimiento bancario donde guarda la fecha del retiro,el tipo y el dinero que quiere retirar.
     * Finalmente muestre el saldo actualizado.
     * @param dinero
     */
    public void retiroDinero(double dinero, int tipoDeMovimiento){
        if(dinero>saldo){
            System.out.println("No tiene saldo suficiente");
        }else if(dinero<0) {
            System.out.println("No puede ingresar un monto negativo");
        }else{
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date fechaActual = new Date();
            this.movimientos.add(new MovimientosBancarios(formato.format(fechaActual),tipoIngresoSalidaDinero(tipoDeMovimiento),dinero*(-1)));
            this.saldo-=dinero;
        }
    }

    /**
     * Muesntra el tipo de movimiento dependideno del número inresado
     * 1. Deósito, 2. Retiro, 3. Transferencia
     * @param tipoDeMovimiento
     * @return
     */
    private String tipoIngresoSalidaDinero (int tipoDeMovimiento){
        if(tipoDeMovimiento== 1){
            return "Depósito";
        }
        if(tipoDeMovimiento== 2){
            return "Retiro";
        }
        if(tipoDeMovimiento== 3){
            return "Transferencia";
        }
        return null;
    }

    /**
     * Muestra los ingresos y retiros realizados con su fecha correspondiente
     */
    public void verMovimientosBancarios(){
        // Mostrar la lista de movimientos bancarios
        System.out.println("------------------------------------------------");
        System.out.println("SALDO ACTUAL: " + formatearMoneda(saldo));
        System.out.println("------------------------------------------------");
        System.out.println("Movimientos Bancarios:");
        System.out.printf("%-20s| %-15s| %-10s\n", "Fecha","Tipo","Monto");
        System.out.println("------------------------------------------------");
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
