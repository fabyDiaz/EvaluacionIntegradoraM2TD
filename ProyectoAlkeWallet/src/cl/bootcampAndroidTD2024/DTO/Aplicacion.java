package cl.bootcampAndroidTD2024.DTO;
import cl.bootcampAndroidTD2024.DAO.ListaUsuariosImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Esta clase contiene los menús en donde el usuario ineteractúa con la aplicación
 * @author Fabiola Díaz <a href="https://github.com/fabyDiaz/EvaluacionIntegradoraM2TD">Github Fabiola Díaz</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public class Aplicacion {
    Scanner scanner = new Scanner(System.in);
    ListaUsuariosImpl listaUsuarios = new ListaUsuariosImpl();
    Usuario usuario = new Usuario();

    /**
     * Mensaje de Bienvenida
     */
    public void bienvenida() {
        System.out.println("*************************************");
        System.out.println("*     Bienvenido a Alke Wallet      *");
        System.out.println("*  Tu solución digital financiera   *");
        System.out.println("*************************************");
    }

    /**
     * Mensaje que se muestra al querer realizar una transferencia bancaria una vez iniciada la sesión
     */
    public void mensajeTransferencias(){
        System.out.println("+______________________________________________________________________+");
        System.out.println("|               Aquí podrás transferir dinero                          |");
        System.out.println("|   Por el momento solo se puede transferir entre cuentas AlkeWallet   |");
        System.out.println("| Próximamente podrás realizar transferencia a otras cuentas bancarias |");
        System.out.println("+______________________________________________________________________+");
    }

    /**
     * Método que muestra el menú de inicio de la App AlkWallet
     * En este menú se despliega las opciones de crear una cuenta o ingrear con una cuenta existente.
     * Si decide ingresar ocn una cuenta exitente, tendrá un máximo de 3 intentos, de lo contrario se termina la ejecución.
     */
    public void menuInicioApp() {
        int opcion=0;
        Usuario nuevoUsuario=null;
        boolean entradaValida=true;
        do {
            try {
                bienvenida();
                System.out.println("    1. Registrar");
                System.out.println("    2. Iniciar Sesión");
                System.out.println("    3. Salir");
                System.out.println("    Selecciona una opción");
                opcion = scanner.nextInt();
                System.out.println("Presione Enter para continuar...");
                System.out.println("\n");
                scanner.nextLine();
                switch (opcion) {
                    case 1:
                        System.out.println("*************************************");
                        System.out.println("*       REGISTRO NUEVO USUARIO      *");
                        System.out.println("*************************************");
                        System.out.println("Presiona \"s\" si desea cancelar el registro");
                        nuevoUsuario=usuario.crearUsuario(scanner);
                       if(nuevoUsuario==null){
                           System.out.println("Registro cancelado.");
                           break;
                       }
                        if(listaUsuarios.obtenerUsuario(nuevoUsuario.getRutUsuario(),nuevoUsuario.getSesion().getEmailUsuario())!=null){
                            System.out.println("\u001B[31m" +"El usuario ya se encuentra registrado ");
                            System.out.println("\u001B[0m");
                        }else{
                            listaUsuarios.agregarUsuario(nuevoUsuario);
                            System.out.println("\u001B[32m" +"Registro Exitoso!");
                            System.out.println("\u001B[0m");
                        }
                        System.out.println("Presione Enter para continuar...");
                        scanner.nextLine();
                        break;
                    case 2:
                        menuSesionIniciada();
                        scanner.nextLine();
                        break;
                    case 3:
                        System.out.println("*************************************");
                        System.out.println("*         HASTA LA PRÓXIMA          *");
                        System.out.println("*************************************");
                        entradaValida = false;
                        break;
                    default:
                        System.out.println("\u001B[31m" +"Opción no válida. Por favor, ingrese un número válido.");
                        System.out.println("\u001B[0m");
                        scanner.nextLine();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\u001B[31m" +"Error: Ingrese un número entero válido.");
                System.out.println("\u001B[0m");
                scanner.nextLine();
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } while (entradaValida);
    }

    /**
     *  Verifica que el correo y la contraseña ingresada ya se encuentra registrado.
     *  Si lo encuentra devuelve al Cliente
     * @return TRUE si el usuario existe en la lista y el correo y la contraseña ingresada son correctas
     *
     */
    public boolean inicioSesion() {
        String correo, contrasena;
        int intentos = 0;
        boolean loginExitoso = false;
        System.out.println("*************************************");
        System.out.println("*          INICIO SESIÓN            *");
        System.out.println("*************************************");
        while (intentos < 3 && !loginExitoso) {
            System.out.println("Ingrese su correo electrónico:");
            correo = scanner.nextLine();
            System.out.println("");
            System.out.println("Ingrese su contraseña:");
            contrasena = scanner.nextLine();
            System.out.println("");

            // Validar usuario y contraseña
            for (int i = 0; i < listaUsuarios.obtenerTodosUsuarios().size(); i++) {
                usuario = validarCredenciales(correo, contrasena, listaUsuarios.obtenerTodosUsuarios().get(i));
                if (usuario != null) {
                    break; // Salir del bucle si encontramos un usuario válido
                }
            }
            if (usuario != null) {
                loginExitoso = true;
                System.out.println("");
                System.out.println("\u001B[32m" +"Inicio de sesión exitoso. ¡Bienvenido, " + usuario.getNombreUsuario() + "!");
                System.out.println("\u001B[0m");
                System.out.println("Presione Enter para continuar...");
                scanner.nextLine();
            } else {
                intentos++;
                System.out.println("Correo electrónico o contraseña incorrectos. Intento " + intentos + " de 3.");
            }

        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
        return loginExitoso;
    }
    /**
     *Valída si el correo y contraseña ingresado coincide con el cliente de la lista.
     * En caso de que coincidan, devuelve al Usuario.
     * @param correo
     * @param contrasena
     * @param usuario
     * @return Usuario
     */
    public Usuario validarCredenciales(String correo, String contrasena, Usuario usuario) {
        if (correo.equals(usuario.getSesion().getEmailUsuario()) && usuario.getSesion().validarContrasena(contrasena)) {
            return usuario;
        }
        return null;
    }

    /**
     * Este es el menú que se muestra una vez que el usuario haya ingresado a su cuenta
     */
    public void menuSesionIniciada() {
        int opcion=0;
        boolean entradaValida = true;
        double dinero;
        scanner.nextLine();
        if(inicioSesion()==true){
           // System.out.println("Presione Enter para continuar...");
            do {
                //Presentción del menú
                System.out.println("");
                System.out.println("*************************************");
                System.out.println("BIENVENIDO/A "+usuario.getNombreUsuario().toUpperCase());
                System.out.println("SALDO: "+usuario.getCuentaUsuario().formatearMoneda(usuario.getCuentaUsuario().getSaldo()));
                System.out.println("*************************************");
                try {
                    System.out.println("Selecciona una opción:\n"+
                            "   1.-    Ver mis datos\n"+
                            "   2.-    Ver datos de mi cuenta\n"+
                            "   3.-    Ingresar dinero\n"+
                            "   4.-    Transferir dinero\n"+
                            "   5.-    Retirar dinero\n"+
                            "   6.-    Ver movimientos\n"+
                            "   7.-    Conversor de moneda\n"+
                            "   8.-    Salir");
                    opcion = scanner.nextInt();
                    scanner.nextLine();
                    switch (opcion) {
                        case 1:
                            System.out.println("*************************************");
                            System.out.println("*              MIS DATOS            *");
                            System.out.println("*************************************");
                            usuario.mostrarDatosUsuario();
                            System.out.println("Presione Enter para continuar...");
                            scanner.nextLine();
                            break;
                        case 2:
                            System.out.println("*************************************");
                            System.out.println("*       DATOS DE MI CUENTA          *");
                            System.out.println("*************************************");
                            usuario.getCuentaUsuario().MostrarDatosCuenta();
                            System.out.println("Presione Enter para continuar...");
                            scanner.nextLine();
                            break;
                        case 3:
                            System.out.println("*************************************");
                            System.out.println("* INGRESO DE DINERO A MI CUENTA     *");
                            System.out.println("*************************************");
                            System.out.println("Ingrese Monto: ");
                            dinero=scanner.nextDouble();
                            scanner.nextLine();
                            usuario.getCuentaUsuario().ingresoDinero(dinero,1);
                            System.out.println("Tu saldo actual es "+ usuario.getCuentaUsuario().formatearMoneda(usuario.getCuentaUsuario().getSaldo()));
                            System.out.println("Presione Enter para continuar...");
                            scanner.nextLine();
                            break;
                        case 4:
                            System.out.println("*************************************");
                            System.out.println("*      TRANSFERENICA BANCARIA       *");
                            System.out.println("*************************************");
                            Usuario usuarioDestinatario;
                            mensajeTransferencias();
                            System.out.println("CORREO ELECTRÓNICO DE DESTINATARIO:");
                            String correoDestinatario = scanner.nextLine();
                            usuarioDestinatario=listaUsuarios.obtenerUsuario(correoDestinatario);
                            usuario.realizarTransferenciaBancaria(scanner, usuarioDestinatario);
                            System.out.println("Tu saldo actual es "+ usuario.getCuentaUsuario().formatearMoneda(usuario.getCuentaUsuario().getSaldo()));
                            System.out.println("Presione Enter para continuar...");
                            scanner.nextLine();
                            break;
                        case 5:
                            System.out.println("*************************************");
                            System.out.println("*   RETIRO DE DINERO DE MI CUENTA   *");
                            System.out.println("*************************************");
                            System.out.println("¿Cuánto vas a retirar?");
                            dinero=scanner.nextDouble();
                            scanner.nextLine();
                            usuario.getCuentaUsuario().retiroDinero(dinero,2);
                            System.out.println("Tu saldo actual es "+usuario.getCuentaUsuario().formatearMoneda(usuario.getCuentaUsuario().getSaldo()));
                            System.out.println("Presione Enter para continuar...");
                            scanner.nextLine();
                            break;
                        case 6:
                            System.out.println("*************************************");
                            System.out.println("*      VER MOVIMIENTOS BANCARIOS    *");
                            System.out.println("*************************************");
                            usuario.getCuentaUsuario().verMovimientosBancarios();
                            System.out.println("Presione Enter para continuar...");
                            scanner.nextLine();
                            break;
                        case 7:
                            System.out.println("*************************************");
                            System.out.println("*       CONVERSOR DE MONEDAS        *");
                            System.out.println("*************************************");
                            usuario.getCuentaUsuario().convertirMoneda();
                            System.out.println("Presione Enter para continuar...");
                            scanner.nextLine();
                            break;
                        case 8:
                            System.out.println("*************************************");
                            System.out.println("*        HAZ CERRADO SESIÓN         *");
                            System.out.println("*************************************");
                            System.out.println("Presione Enter para continuar...");
                            entradaValida = false;
                            break;
                        default:
                            System.out.println("\u001B[31m" +"Opción no válida. Por favor, ingrese un número válido.");
                            System.out.println("\u001B[0m");
                            System.out.println("Presione Enter para continuar...");
                            scanner.nextLine();
                            break;
                    }
                }catch (InputMismatchException e){
                    System.out.println("\u001B[31m" +"Error: Ingrese un número entero válido.");
                    System.out.println("\u001B[0m");
                    System.out.println("Presione Enter para continuar...");
                    scanner.nextLine();
                }
                //Limpia la terinal
                System.out.print("\033[H\033[2J");
                System.out.flush();

            } while (entradaValida);
        }
    }
}
