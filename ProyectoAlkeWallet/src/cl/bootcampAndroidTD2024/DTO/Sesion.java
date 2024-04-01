package cl.bootcampAndroidTD2024.DTO;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sesion {
    private String emailUsuario;
    private String contrasena;

    public Sesion() {
    }

    public Sesion(String emailUsuario, String contrasena) {
        this.emailUsuario = emailUsuario;
        this.contrasena = contrasena;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

   // Scanner scanner = new Scanner(System.in);
    /**
     * Devuelve verdadero su la contraseña ingresada por el usuario en la aplicación coincide con la encontró en la lista
     *
     * @param contrasena
     * @return
     */
    public boolean validarContrasena(String contrasena) {

        return this.contrasena.equals(contrasena);
    }
    /**
     * Valida que usuario ingrese un correo con el formato correcto, de lo contrario lo volverá a pedir.
     */
    public void valiarEmail(Scanner scanner) {
        // Patrón para validar el email
        Matcher matcher;
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        do {
            System.out.println("EMAIL:");
            this.emailUsuario = scanner.nextLine();
            matcher = pattern.matcher(emailUsuario);
            if (!matcher.matches()) {
                System.out.println("El correo electrónico no es válido.");
            }

        } while (!matcher.matches());

    }
    /**
     * Permite al Usuario crear una nueva cuenta de Usuario.
     * Solicita correo y contraseña.
     *
     * @return Sesion
     */
    public Sesion crearCorreoyContrasena(Scanner scanner) {
        String c;
        Boolean valido=false;
        valiarEmail(scanner);
        do {
            System.out.println("Ingrese una contraseña (debe tener al menos 6 caracteres y contener al menos una letra y un número):");
            c = scanner.nextLine();
            if(c.length() >= 8 && c.matches(".*[a-zA-Z]+.*") && c.matches(".*\\d+.*")){
                valido = true;
            }
        } while (!valido);
        return new Sesion(this.emailUsuario, c);
    }

    public Sesion prueba() {
        return new Sesion("email@correo.cl", "123");
    }

    @Override
    public String toString() {
        return "Sesion{" +
                "emailUsuario='" + emailUsuario + '\'' +
                ", contrasena='" + contrasena + '\'' +
                '}';
    }
}
