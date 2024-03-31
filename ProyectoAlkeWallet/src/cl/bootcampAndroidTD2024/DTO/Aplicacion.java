package cl.bootcampAndroidTD2024.DTO;

import cl.bootcampAndroidTD2024.DAO.IListaUsuarios;
import cl.bootcampAndroidTD2024.DAO.ListaUsuariosImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

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
     * Método que muestra el menú de inicio de la App AlkWallet
     * En este menú se despliega la opciones de crear una cuenta o ingrear con una cuenta existente.
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
                System.out.println("    3. Mostrar lista clientes");
                System.out.println("    4. Salir");
                System.out.println("    Selecciona una opción");
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        nuevoUsuario=usuario.crearUsuario();
                        if(listaUsuarios.obtenerUsuario(nuevoUsuario.getRutUsuario(),nuevoUsuario.getSesion().getEmailUsuario())!=null){
                            System.out.println("\u001B[31m" +"El usuario ya se encuentra registrado ");
                            System.out.println("\u001B[0m");
                        }else{
                            listaUsuarios.agregarUsuario(nuevoUsuario);
                            System.out.println("\u001B[32m" +"Registro Exitoso!");
                            System.out.println("\u001B[0m");
                        }
                        scanner.nextLine();
                        break;
                    case 2:
                        menuSesionIniciada();
                        scanner.nextLine();
                        break;
                    case 3:
                        System.out.println(listaUsuarios.obtenerTodosUsuarios());
                        scanner.nextLine();
                        break;
                    case 4:
                        System.out.println("Hasta la proxima!");
                        entradaValida = false;
                        scanner.nextLine();
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
     *  Verifica que el correo y la contraseña ingresada ya se encuntra registrado.
     *  Si lo encuntra devuelve al Cliente
     * @return retorna el cliente luego de verificar que el correo y la contraseña ingresada sea correcta
     *
     */
    public boolean inicioSesion() {
        String correo, contrasena;
        int intentos = 0;
        boolean loginExitoso = false;

        while (intentos < 3 && !loginExitoso) {
            System.out.println("Ingrese su correo electrónico:");
            correo = scanner.nextLine();
            System.out.println("Ingrese su contraseña:");
            contrasena = scanner.nextLine();

            // Validar usuario y contraseña
            for (int i = 0; i < listaUsuarios.obtenerTodosUsuarios().size(); i++) {
                usuario = validarCredenciales(correo, contrasena, listaUsuarios.obtenerTodosUsuarios().get(i));
            }
            if (usuario != null) {
                loginExitoso = true;
                System.out.println("");
                System.out.println("=================================");
                System.out.println("\u001B[32m" +"Inicio de sesión exitoso. ¡Bienvenido, " + usuario.getNombreUsuario() + "!");
                System.out.println("\u001B[0m");
                System.out.println("Presione Enter para continuar...");
                scanner.nextLine();
            } else {
                intentos++;
                System.out.println("Correo electrónico o contraseña incorrectos. Intento " + intentos + " de 3.");
            }

        }
        return loginExitoso;
    }
    /**
     *Valida si el correo y contraseña ingresado coincide con el cliente de la lista.
     * En caso que coincidan, devuelve el cliente.
     * @param correo
     * @param contrasena
     * @param usuario
     * @return
     */
    public Usuario validarCredenciales(String correo, String contrasena, Usuario usuario) {
        if (correo.equals(usuario.getSesion().getEmailUsuario()) && usuario.getSesion().validarContrasena(contrasena)) {
            return usuario;
        }
        return null;
    }

    /**
     * Este es el menú que se muestra una vez que el cliente haya ingresado a su cuenta
     */
    public void menuSesionIniciada() {
        int opcion=0;
        boolean entradaValida = true;
        double dinero;
        scanner.nextLine();
        if(inicioSesion()==true){
            do {
                //Limpia la terinal
                System.out.print("\033[H\033[2J");
                System.out.flush();
                //Presentción del menú
                System.out.println("");
                System.out.println("---------------------------------");
                System.out.println("BIENVENIDO/A "+usuario.getNombreUsuario().toUpperCase());
                System.out.println("---------------------------------");
                System.out.println("SALDO: "+usuario.getCuentaUsuario().formatearMoneda(usuario.getCuentaUsuario().getSaldo()));
                System.out.println("---------------------------------");
                try {
                    System.out.println("Selecciona una opción:\n"+
                            "   1.-    Ver mis datos\n"+
                            "   2.-    Ver datos de mi cuenta\n"+
                            "   3.-    Ingresar dinero\n"+
                            "   4.-    Transferir dinero\n"+
                            "   5.-    Retirar dinero\n"+
                            "   6.-    Ver movimientos\n"+
                            "   7.-    Ir a conversor de Moneda\n"+
                            "   8.-    Salir");
                    opcion = scanner.nextInt();
                    scanner.nextLine();
                    switch (opcion) {
                        case 1:
                            usuario.mostrarDatosUsuario();
                            System.out.println("Presione Enter para continuar...");
                            scanner.nextLine();
                            break;
                        case 2:
                            usuario.getCuentaUsuario().MostrarDatosCuenta();
                            System.out.println("Presione Enter para continuar...");
                            scanner.nextLine();
                            break;
                        case 3:
                            System.out.println("Ingrese Monto: ");
                            while (!scanner.hasNextDouble()) {
                                System.out.println("Ingrese un monto válido");
                                scanner.next();
                            }
                            dinero=scanner.nextDouble();
                            scanner.nextLine();
                            usuario.getCuentaUsuario().ingresoDinero(dinero);
                            System.out.println("Presione Enter para continuar...");
                            scanner.nextLine();
                            break;
                        case 4:
                            System.out.println("aquí podrás transferir dinero");
                            System.out.println("Presione Enter para continuar...");
                            scanner.nextLine();
                            break;
                        case 5:
                            System.out.println("¿Cuánto vas a retirar?");
                            while (!scanner.hasNextDouble()) {
                                System.out.println("Ingrese un monto válido");
                                scanner.next();
                            }
                            dinero=scanner.nextDouble();
                            scanner.nextLine();
                            usuario.getCuentaUsuario().retiroDinero(dinero);
                            System.out.println("Presione Enter para continuar...");
                            scanner.nextLine();
                            break;
                        case 6:
                            usuario.getCuentaUsuario().verMovimientosBancarios();
                            System.out.println("Presione Enter para continuar...");
                            scanner.nextLine();
                            break;
                        case 7:
                            usuario.getCuentaUsuario().convertirMoneda();
                            System.out.println("Presione Enter para continuar...");
                            scanner.nextLine();
                            break;
                        case 8:
                            System.out.println("Has cerrado la sesión");
                            entradaValida = false;
                            System.out.println("Presione Enter para continuar...");
                            scanner.nextLine();
                            break;
                        default:
                            System.out.println("\u001B[31m" +"Opción no válida. Por favor, ingrese un número válido.");
                            System.out.println("\u001B[0m");
                            scanner.nextLine();
                            break;
                    }
                }catch (InputMismatchException e){
                    System.out.println("\u001B[31m" +"Error: Ingrese un número entero válido.");
                    System.out.println("\u001B[0m");
                    scanner.nextLine();
                }
            } while (entradaValida);
        }
    }
}
