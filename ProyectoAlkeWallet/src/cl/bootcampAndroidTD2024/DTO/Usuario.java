package cl.bootcampAndroidTD2024.DTO;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Esta clase contiene al información la información personal del usuario, como el rut, nombre, apellido, teléfono,
 * se crea por defecto una cuenta con saldo cero y su usuario para poder iniciar sesión.
 * @author Fabiola Díaz <a href="https://github.com/fabyDiaz/EvaluacionIntegradoraM2TD">Github Fabiola Díaz</a>
 * @version 1.0.0
 * @since 1.0.0
 */
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

    /**
     *
     * @return nombreCompleto
     */
    public String nombreCompleto(){
        return this.nombreUsuario + " " + this.apellidoUsuario;
    }

    /**
     * Método que solicita los datos necesarios para crear un nuevo usuario en la app
     * @return nuevo usuario
     */
    public Usuario crearUsuario(Scanner scanner) {
        Sesion sesion1= new Sesion();
        CuentaBancaria cuenta = new CuentaBancaria();
        Usuario usuario;
        System.out.println("Ingrese los siguientes datos");
        System.out.println("-------------------------------");
        String nombre = validarNombre(scanner);
        if (nombre == null) {
            System.out.println("Creación de usuario cancelada.");
            return null;
        }
        this.nombreUsuario = nombre;
        String apellido = validarApellido(scanner);
        if (apellido == null) {
            System.out.println("Creación de usuario cancelada.");
            return null;
        }
        this.apellidoUsuario = apellido;
        do{
            System.out.println("RUT (en formato xxxxxxxx-x): ");
            this.rutUsuario= scanner.nextLine();
        }while(validarRut(rutUsuario)==false);
        if(rutUsuario==null){
            return null;
        }
        String telefono = validarTelefono(scanner);
        if (telefono == null) {
            System.out.println("Creación de usuario cancelada.");
            return null;
        }
        this.telefonoUsuario = telefono;
        Sesion nuevaSesion = sesion1.nuevaSesion(scanner);
        if (nuevaSesion == null) {
            System.out.println("Creación de usuario cancelada.");
            return null;
        }
        this.sesion = nuevaSesion;

        this.cuentaUsuario = cuenta.crearCuenta(nombreCompleto().toUpperCase());

        usuario = new Usuario(rutUsuario, nombreUsuario, apellidoUsuario, telefonoUsuario, cuentaUsuario, sesion);

        return usuario;
    }

    /**
     * Muestra los datos relacionados con el Usuario
     */
    public void mostrarDatosUsuario(){
        System.out.println("NOMBRE: "+nombreCompleto());
        System.out.println(("RUT: "+ this.rutUsuario));
        System.out.println("CORREO: "+this.sesion.getEmailUsuario());
        System.out.println("TELÉFONO "+this.telefonoUsuario);
    }

    /**
     * Valida que al ingresar el nombre se ingresen caracteres válidos con un minimo de 2 caracteres y un máximo de 30
     * Si el usuario no desea continuar con el registo debe presionar "s" y devuelve null
     * @param scanner
     * @return nombre del usuario
     */
    public String validarNombre(Scanner scanner) {
        String nombre;
        boolean valido = false;
        do {
            System.out.println("NOMBRE:");
            nombre = scanner.nextLine();
            if (nombre.equalsIgnoreCase("s")) {
                return null; // Si se ingresa "s", devuelve null
            } else {
                if (nombre.matches("[a-zA-ZáéíóúüñÁÉÍÓÚÜÑ\\s]{2,30}")) {
                    valido = true;
                } else {
                    System.out.println("Formato incorrecto");
                }
            }
        } while (!valido);
        return nombre;
    }

    /**
     * Valida que al ingresar el apellido se ingresen caracteres válidos con un minimo de 2 caracteres y un máximo de 30
     * Si el usuario no desea continuar con el registo debe presionar "s" y devuelve null
     * @param scanner
     * @return apellido del usuario
     */
    public String validarApellido(Scanner scanner) {
        String apellido;
        boolean valido = false;
        do {
            System.out.println("APELLIDO:");
            apellido = scanner.nextLine();
            if (apellido.equalsIgnoreCase("s")) {
                return null; // Si se ingresa "s", devuelve null
            } else {
                if (apellido.matches("[a-zA-ZáéíóúüñÁÉÍÓÚÜÑ\\s]{2,30}")) {
                    valido = true;
                } else {
                    System.out.println("Formato incorrecto");
                }
            }
        } while (!valido);
        return apellido;
    }

    /**
     * Valida rut de la forma XXXXXXXX-X y que se ingrese un digito verificador válido
     * @param rut
     * @return
     */
    public boolean validarRut ( String rut ) {

        if (rut.equalsIgnoreCase("s")){
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
     * Valida el dígito verificador válido
     * @param rut
     * @return
     */
    public String dv ( String rut ) {
        Integer M=0,S=1,T=Integer.parseInt(rut);
        for (;T!=0;T=(int) Math.floor(T/=10))
            S=(S+T%10*(9-M++%6))%11;
        return ( S > 0 ) ? String.valueOf(S-1) : "k";
    }

    /**
     * Valida que al ingresar el teléfono se ingresen números válidos con un máximo de 9 números que se alamacenan en un String
     * Si el usuario no desea continuar con el registo debe presionar "s" y devuelve null
     * @param scanner
     * @return núemro teléfonico del usuario
     */
    public String validarTelefono(Scanner scanner) {
        String telefono;
        boolean valido;
        do {
            System.out.println("TELÉFONO:");
            telefono = scanner.nextLine();
            if (telefono.equalsIgnoreCase("s")) {
                return null; // Devuelve null si se ingresa "s"
            } else if (telefono.matches("^[1-9][0-9]{8}$")) {
                valido = true;
            } else {
                System.out.println("Número de teléfono no válido. Debe tener 9 dígitos.");
                valido = false;
            }
        } while (!valido);
        return telefono;
    }

    /**
     * Si existe el usuario en la lista, mustra sus datos y solicita el Monto a transferir
     * Verifica que se ingrese un monto válido a transferir
     * Solicita nuevamente la clave para confirmar la transferencia
     * @param scanner, Usuario
     * @param usuarioDestinatario
     */
    public void realizarTransferenciaBancaria(Scanner scanner, Usuario usuarioDestinatario){
        double montoDestinatario;
        String contrasena;
        if( usuarioDestinatario!=null){
            usuarioDestinatario.mostrarDatosUsuario();
            System.out.println("\nMONTO A TRANSFERIR: ");
            montoDestinatario = scanner.nextDouble();
            scanner.nextLine();
            if(montoDestinatario>cuentaUsuario.getSaldo()){
                System.out.println("No tiene saldo suficiente");
            }else if (montoDestinatario<=0) {
                System.out.println("El monto ingresado no es válido. Debe ser un monto mayor a cero");
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
                    System.out.println("No se pudo realizar la transferencia");
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
