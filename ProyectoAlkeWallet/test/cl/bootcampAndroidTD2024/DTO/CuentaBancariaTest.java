package cl.bootcampAndroidTD2024.DTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CuentaBancariaTest {
    CuentaBancaria cuentaBancaria;

    @BeforeEach
    public void configuracion(){
        cuentaBancaria= new CuentaBancaria();
        cuentaBancaria.setSaldo(5000);
    }

    /**
     * Simulamos que ingresmos una cantidad válida de dinero.
     * Comprobamos que el saldo es el esperado
     */
    @Test
    public void testIngresoDinero() {
        double saldoInicial = cuentaBancaria.getSaldo(); // Obtiene el saldo inicial
        double cantidadIngresada = 100.0; // Cantidad a ingresar
        cuentaBancaria.ingresoDinero(cantidadIngresada,1); // Llama al método para ingresar dinero
        double saldoEsperado = saldoInicial + cantidadIngresada; // Calcula el saldo esperado
        assertEquals(saldoEsperado, cuentaBancaria.getSaldo(), 0.001); // Comprueba que el saldo es el esperado
    }

    /**
     * Simulamos que ingresmos una cantidad no válida de dinero, en este caso una cantidad negativa.
     * Verifica que el saldo no cambie después de intentar ingresar una cantidad negativa
     */
    @Test
    public void testIngresoDineroCantidadNegativa() {
        double saldoInicial = cuentaBancaria.getSaldo(); // Saldo inicial antes del ingreso
        double cantidadIngresada = -50.0; // Cantidad a ingresar (negativa)
        cuentaBancaria.ingresoDinero(cantidadIngresada,1); // Intenta ingresar una cantidad negativa
        // Verifica que el saldo no cambie después de intentar ingresar una cantidad negativa
        assertEquals(saldoInicial, cuentaBancaria.getSaldo(), 0.001);
    }

    /**
     * Simulamos que ingresmos una cantidad válida de dinero que queremos retirar
     * Verifica que el saldo sea el esperado
     */
    @Test
    public void testRetiroDineroSaldoSuficiente() {
        double saldoInicial = cuentaBancaria.getSaldo(); // Supongamos que el saldo inicial es 1000
        cuentaBancaria.setSaldo(saldoInicial); // Establece el saldo inicial
        double cantidadRetirada = 500.0; // Cantidad a retirar
        cuentaBancaria.retiroDinero(cantidadRetirada,1); // Realiza el retiro de dinero
        double saldoEsperado = saldoInicial - cantidadRetirada; // Saldo esperado después del retiro
        assertEquals(saldoEsperado, cuentaBancaria.getSaldo(), 0.001); // Verifica que el saldo sea el esperado
    }

    /**
     * Simulamos que ingresmos una cantidad no válida de dinero, en este caso retirar un cantidad superior al saldo actual
     * Verificamos que el saldo no cambie después de intentar retirar más dinero del disponible
     */
    @Test
    public void testRetiroDineroSaldoInsuficiente() {
        double saldoInicial = 1000.0; // Supongamos que el saldo inicial es 1000
        cuentaBancaria.setSaldo(saldoInicial); // Establece el saldo inicial
        double cantidadRetirada = 1500.0; // Cantidad a retirar (mayor que el saldo inicial)
        cuentaBancaria.retiroDinero(cantidadRetirada,2); // Intenta retirar una cantidad mayor que el saldo
        // Verifica que el saldo no cambie después de intentar retirar más dinero del disponible
        assertEquals(saldoInicial, cuentaBancaria.getSaldo(), 0.001);
    }

    /**
     * Simulamos que ingresmos una cantidad no válida de dinero, en este caso retirar un cantidad negativa de dinero
     * Verificamos que el saldo no cambie después de intentar retirar una cantidad negativa de dinero
     */
    @Test
    public void testRetiroDineroSaldNegativo() {
        double saldoInicial = 1000.0; // Supongamos que el saldo inicial es 1000
        cuentaBancaria.setSaldo(saldoInicial); // Establece el saldo inicial
        double cantidadRetirada = -500.0; // Cantidad a retirar (mayor que el saldo inicial)
        cuentaBancaria.retiroDinero(cantidadRetirada,2); // Intenta retirar una cantidad mayor que el saldo
        // Verifica que el saldo no cambie después de intentar retirar más dinero del disponible
        assertEquals(saldoInicial, cuentaBancaria.getSaldo(), 0.001);
    }

    /**
     * Verificamos que el resultado coincida con el formato esperado en pesos chilenos
     */
    @Test
    public void testFormatearMoneda() {
        double monto = 123456.78; //supongamos un monto en double
        String resultado = cuentaBancaria.formatearMoneda(monto); //llamamos al método e ingresamoe el monto
        // Verificamos que el resultado coincida con el formato esperado en pesos chilenos
        assertEquals("$123.457", resultado);
    }


}