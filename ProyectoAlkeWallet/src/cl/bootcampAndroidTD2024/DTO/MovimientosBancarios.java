package cl.bootcampAndroidTD2024.DTO;
/**
 * Esta clase contiene la información relacionada con los movimientos de ingreso y egreso de dinero.
 * Contiene los datos de la fecha y hora, el tipo de movimiento que puede ser: "Depósito", "Retiro" o "Transferencia"
 * y el monto de ese movimiento.
 * @author Fabiola Díaz <a href="https://github.com/fabyDiaz/EvaluacionIntegradoraM2TD">Github Fabiola Díaz</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public class MovimientosBancarios {
    private String fecha;
    private String tipo;
    private double monto;

    public MovimientosBancarios() {
    }

    public MovimientosBancarios(String fecha, String tipo, double monto) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.monto = monto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "MovimientosBancarios{" +
                "tipo='" + tipo + '\'' +
                ", fecha=" + fecha +
                ", monto=" + monto +
                '}';
    }
}
