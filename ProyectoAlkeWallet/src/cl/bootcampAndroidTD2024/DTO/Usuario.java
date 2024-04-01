package cl.bootcampAndroidTD2024.DTO;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Usuario {
    private String rutUsuario;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String telefonoUsuario;
    private CuentaBancaria cuentaUsuario;
    private Sesion sesion;

    public Usuario() {
    }

    public Usuario(String rutUsuario, String nombreUsuario, String apellidoUsuario, String telefonoUsuario, CuentaBancaria cuentaUsuario, Sesion sesion) {
        this.rutUsuario = rutUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.cuentaUsuario = cuentaUsuario;
        this.sesion = sesion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getRutUsuario() {
        return rutUsuario;
    }

    public void setRutUsuario(String rutUsuario) {
        this.rutUsuario = rutUsuario;
    }

    public String getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public void setTelefonoUsuario(String telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }

    public CuentaBancaria getCuentaUsuario() {
        return cuentaUsuario;
    }

    public void setCuentaUsuario(CuentaBancaria cuentaUsuario) {
        this.cuentaUsuario = cuentaUsuario;
    }

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public String nombreCompleto(){
        return this.nombreUsuario + " " + this.apellidoUsuario;
    }

    /**
     * Método que solicita los datos necesarios para crear un nuevo cliente
     * @return nuevo cliente
     */

    public Usuario crearUsuario(Scanner scanner) {
        Sesion sesion1= new Sesion();
        CuentaBancaria cuenta = new CuentaBancaria();
        Usuario usuario;
        System.out.println("Ingrese los siguientes datos");
        System.out.println("-------------------------------");
        validarNombre(scanner);
        if (nombreUsuario == null) {
            return null;
        }
        validarApellido(scanner);
        if(apellidoUsuario==null){
            return null;
        }
        do{
            System.out.println("RUT (en formato xxxxxxxx-x: ");
            this.rutUsuario= scanner.nextLine();
        }while(validarRut(rutUsuario)==false);
        if(rutUsuario==null){
            return null;
        }
        System.out.println("TELÉFONO");
        this.telefonoUsuario = scanner.nextLine();

        this.sesion = sesion1.crearCorreoyContrasena(scanner);

        this.cuentaUsuario = cuenta.crearCuenta(nombreCompleto().toUpperCase());

        usuario = new Usuario(rutUsuario, nombreUsuario, apellidoUsuario, telefonoUsuario, cuentaUsuario, sesion);

        return usuario;

    }

    /**
     * Muestra los datos relacionados con el Usuario
     */
    public void mostrarDatosUsuario(){
        System.out.println("===============================");
        System.out.println("Los datos son: ");
        System.out.println("------------------------------");
        System.out.println("NOMBRE: "+nombreCompleto());
        System.out.println(("RUT: "+ this.rutUsuario));
        System.out.println("CORREO: "+this.sesion.getEmailUsuario());
        System.out.println("TELÉFONO "+this.telefonoUsuario);
    }

    /**
     * Valida que al momento de ingresar un nombre se ingrese caracteres válidos
     */
    public void validarNombre(Scanner scanner){
        boolean valido = false;
        String nombre;
        do {
            System.out.println("NOMBRE:");
            nombre= scanner.nextLine();
            if(nombre.toLowerCase().equals("s")){
                this.nombreUsuario = null;
                return;
            }else{
                this.nombreUsuario=nombre;
                if (this.nombreUsuario.matches("[a-zA-ZáéíóúüñÁÉÍÓÚÜÑ\\s]{2,30}")){
                    valido = true;
                }else {
                    System.out.println("Formato incorrecto");
                }
            }
        }while (!valido);

    }

    /**
     * Valida que al momento de ingresar un apellido se ingrese caracteres válidos
     */
    public void validarApellido(Scanner scanner){
        boolean valido = false;
        String apellido;
        do {
            System.out.println("APELLIDO:");
            apellido= scanner.nextLine();
            if(apellido.toLowerCase().equals("s")){
                this.apellidoUsuario = null;
                return;
            }else{
                this.apellidoUsuario=apellido;
            }
            if (this.apellidoUsuario.matches("[a-zA-ZáéíóúüñÁÉÍÓÚÜÑ]{2,30}")){
                valido = true;
            }else {
                System.out.println("Formato incorrecto");
            }
        }while (!valido);
    }


    /**
     *  Valida rut de la forma XXXXXXXX-X
     */
    public boolean validarRut ( String rut ) {

        if (rut.toLowerCase().equals("s")){
            this.rutUsuario = null;
            return true;
        }else{
            Pattern pattern = Pattern.compile("^[0-9]+-[0-9kK]{1}$");
            Matcher matcher = pattern.matcher(rut);
            if ( matcher.matches() == false ) return false;
            String[] stringRut = rut.split("-");
            return stringRut[1].toLowerCase().equals(dv(stringRut[0]));
        }
    }

    /**
     * Valida el dígito verificador
     */
    public String dv ( String rut ) {
        Integer M=0,S=1,T=Integer.parseInt(rut);
        for (;T!=0;T=(int) Math.floor(T/=10))
            S=(S+T%10*(9-M++%6))%11;
        return ( S > 0 ) ? String.valueOf(S-1) : "k";
    }
    public void realizarTransferenciaBancaria(Scanner scanner, Usuario usuarioDestinatario){
        double montoDestinatario;
        String contrasena;
        if( usuarioDestinatario!=null){
            usuarioDestinatario.mostrarDatosUsuario();
            System.out.println("MONTO: ");
            montoDestinatario = scanner.nextDouble();
            scanner.nextLine();
            if(montoDestinatario>cuentaUsuario.getSaldo()){
                System.out.println("No tiene saldo suficiente");
            }else{
                System.out.println("INGRESA TU CONTRASEÑA PARA CONFIRMAR");
                contrasena = scanner.nextLine();
                if (this.sesion.validarContrasena(contrasena)==true){
                    usuarioDestinatario.getCuentaUsuario().ingresoDinero(montoDestinatario,3);
                    this.cuentaUsuario.retiroDinero(montoDestinatario,3);
                    System.out.println("\u001B[32m" +"Transferencia Exitosa!");
                    System.out.println("\u001B[0m");
                }else{
                    System.out.println("\u001B[31m" +"Contraseña incorrecta");
                    System.out.println("No se puedo realizar la transferencia");
                    System.out.println("\u001B[0m");
                }
            }

        }else{
            System.out.println("El correo ingresado no coincide con una cuenta existente en AlkeWallet");
        }
    }



    @Override
    public String toString() {
        return "Usuario{" +
                ", rutUsuario='" + rutUsuario + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", apellidoUsuario='" + apellidoUsuario + '\'' +
                ", telefonoUsuario='" + telefonoUsuario + '\'' +
                ", cuentaUsuario=" + cuentaUsuario +
                ", sesion=" + sesion +
                '}';
    }

}
