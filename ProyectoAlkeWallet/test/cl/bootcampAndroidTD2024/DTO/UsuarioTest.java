package cl.bootcampAndroidTD2024.DTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    Usuario usuariotest;

    @BeforeEach
    public void configuracion(){
        usuariotest = new Usuario("11111111-1","Homero","Simpson","+56963268745", new CuentaBancaria(),new Sesion("homero@correo.cl","homero123")); // Crear un usuario con las credenciales válidas
    }

    @Test
    public void testValidarRut_CasoExitoso() {
        // Arrange
        String rut = "11111111-1";
        // Act
        boolean resultado = usuariotest.validarRut(rut);

        // Assert
        assertTrue(resultado);
    }

    @Test
    public void testValidarRut_CasoFallido() {
        // Arrange
        String rut = "111-1";
        // Act
        boolean resultado = usuariotest.validarRut(rut);

        // Assert
        assertFalse(resultado);
    }


}