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
    private String validarEmail(Scanner scanner) {
        // Patrón para validar el email
        Matcher matcher;
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        String email;
        do {
            System.out.println("EMAIL:");
            email = scanner.nextLine();
            if (email.equalsIgnoreCase("s")) {
                return null; // Devuelve null si se ingresa "s"
            }
            matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                System.out.println("El correo electrónico no es válido.");
            }

        } while (!matcher.matches());
        return email;
    }
    /**
     * Permite al Usuario crear una nueva cuenta de Usuario.
     * Solicita correo y contraseña.
     *
     * @return Sesion
     */
    private String validarContrasena(Scanner scanner) {
        String contrasena;
        do {
            System.out.println("CONTRASEÑA:");
            contrasena = scanner.nextLine();
            if (contrasena.equalsIgnoreCase("s")) {
                return null; // Devuelve null si se ingresa "s"
            }
            // Validación adicional de la contraseña según tus criterios
            if (contrasena.length() >= 6 && contrasena.matches(".*[a-zA-Z]+.*") && contrasena.matches(".*\\d+.*")) {
                return contrasena;
            } else {
                System.out.println("La contraseña debe tener al menos 6 caracteres.");
            }
        } while (true);
    }

    public Sesion nuevaSesion(Scanner scanner){
        String email = validarEmail(scanner);
        String contrasena = validarContrasena(scanner);

        if(email==null || contrasena==null){
            return null;
        }else{
            return new Sesion(email, contrasena);
        }
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
