package cl.bootcampAndroidTD2024.DAO;

import cl.bootcampAndroidTD2024.DTO.CuentaBancaria;
import cl.bootcampAndroidTD2024.DTO.Sesion;
import cl.bootcampAndroidTD2024.DTO.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase contiene una lista de usuarios con sus métodos respectivos para agregar, modificar, eliminar y
 * obtener un usuario o la lista completa.
 * Pir defecto ya se incluyen dos usuarios para poder realizar pruebas en la aplicación.
 * @author Fabiola Díaz <a href="https://github.com/fabyDiaz/EvaluacionIntegradoraM2TD">Github Fabiola Díaz</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public class ListaUsuariosImpl implements IListaUsuarios {
    private List<Usuario> listaUsuarios = new ArrayList();

    /**
     * Creamos una lista de de usuarios con algunos datos ingresados a modo de prueba para simular una base de datos.
     */
    public ListaUsuariosImpl() {
        this.listaUsuarios.add(new Usuario("11111111-2","Homero","Simpson","987654321", new CuentaBancaria(2,"HOMERO SIMPSON", "Vista"),new Sesion("homero@correo.cl","homero123")));
        this.listaUsuarios.add(new Usuario("11111111-1","Admin","Ejemplo","912345678", new CuentaBancaria(3, "ADMIN EJEMPLO", "Vista"),new Sesion("admin@correo.cl","admin123")));
    }

    /**
     * Permite agregar un usuario a la lista
     * @param usuario
     */
    @Override
    public void agregarUsuario(Usuario usuario) {
        listaUsuarios.add(usuario);
    }

    /**
     * Permite actualizar los datos del usuario de la lista
     * @param usuarioActualizado
     */
    @Override
    public void actualizarUsuario(Usuario usuarioActualizado) {
        for (Usuario u : listaUsuarios) {
            if (u.getRutUsuario()== usuarioActualizado.getRutUsuario()) {
                u.setNombreUsuario(usuarioActualizado.getNombreUsuario());
                //Agregar el resto
                break;
            }
        }
    }

    /**
     * Permite eliminar un Usuario de la lista
     * @param rut
     */
    @Override
    public void eliminarUsuario(String rut) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getRutUsuario().equals(rut)) {
                listaUsuarios.remove(i);
                break;
            }
        }
    }

    /**
     * Permite Obtener un Usuario de la lista, comparando el rut y el correo
     * @param rut
     * @param correo
     * @return un usuario
     */
    public Usuario obtenerUsuario(String rut, String correo) {
        for (Usuario u : listaUsuarios) {
            if (u.getRutUsuario().equals(rut) || u.getSesion().getEmailUsuario().equals(correo)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Permite obtener un usuario de la lista considerando solo el correo.
     * @param correo
     * @return un uaurio
     */
    @Override
    public Usuario obtenerUsuario(String correo) {
        for (Usuario u : listaUsuarios) {
            if (u.getSesion().getEmailUsuario().equals(correo)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Permite obtener todos los elementos de la lista
     * @return la lista completa de usuarios
     */
    @Override
    public List<Usuario> obtenerTodosUsuarios() {
        return listaUsuarios;
    }

    @Override
    public String toString() {
        return "UsuarioDAO{" +
                "listaUsuarios=" + listaUsuarios +
                '}';
    }
}
