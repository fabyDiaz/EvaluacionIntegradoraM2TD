package cl.bootcampAndroidTD2024.DTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AplicacionTest {

    Aplicacion aplicacionTest;
    Usuario usuariotest;
    @BeforeEach
    public void configuracion(){
        aplicacionTest = new Aplicacion();
        usuariotest = new Usuario("12345678-6","Admin","Ejemplo","+5612345678", new CuentaBancaria(),new Sesion("admin@correo.cl","admin123"));// Crear un usuario con las credenciales válidas
    }

    @Test
    public void testValidarCredenciales_CredencialesCorrectas() {
        String correo = "admin@correo.cl";
        String contrasena = "admin123";

        Usuario usuarioValido= usuariotest;

        Usuario usuariotest = aplicacionTest.validarCredenciales(correo, contrasena, usuarioValido); // Validar las credenciales

        assertNotNull(usuarioValido); // Verificar que se devuelve un usuario válido
        assertEquals(correo, usuarioValido.getSesion().getEmailUsuario()); // Verificar que el correo es correcto
        assertTrue(usuarioValido.getSesion().validarContrasena(contrasena)); // Verificar que la contraseña es correcta
    }

    @Test
    public void testValidarCredenciales_CorreoIncorrecto() {
        String correo = "admin";
        String contrasena = "admin123";

        Usuario usuarioValido= usuariotest;

        Usuario usuariotest = aplicacionTest.validarCredenciales(correo, contrasena, usuarioValido); // Validar las credenciales

        assertNotNull(usuarioValido); // Verificar que se devuelve un usuario válido
        assertNotEquals(correo, usuarioValido.getSesion().getEmailUsuario()); // Verificar que el correo es correcto
        assertTrue(usuarioValido.getSesion().validarContrasena(contrasena)); // Verificar que la contraseña es correcta
    }

    @Test
    public void testValidarCredenciales_ContraseñaIncorrecto() {
        String correo = "admin@correo.cl";
        String contrasena = "123";

        Usuario usuarioValido= usuariotest;

        Usuario usuariotest = aplicacionTest.validarCredenciales(correo, contrasena, usuarioValido); // Validar las credenciales

        assertNotNull(usuarioValido); // Verificar que se devuelve un usuario válido
        assertEquals(correo, usuarioValido.getSesion().getEmailUsuario()); // Verificar que el correo es correcto
        assertFalse(usuarioValido.getSesion().validarContrasena(contrasena)); // Verificar que la contraseña es correcta
    }

    @Test
    public void testValidarCredenciales_CredencialesIncorrectas() {
        String correo = "admin.cl";
        String contrasena = "123";

        Usuario usuarioValido= usuariotest;

        Usuario usuariotest = aplicacionTest.validarCredenciales(correo, contrasena, usuarioValido); // Validar las credenciales

        assertNotNull(usuarioValido); // Verificar que se devuelve un usuario válido
        assertNotEquals(correo, usuarioValido.getSesion().getEmailUsuario()); // Verificar que el correo es correcto
        assertFalse(usuarioValido.getSesion().validarContrasena(contrasena)); // Verificar que la contraseña es correcta
    }

}