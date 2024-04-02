package cl.bootcampAndroidTD2024.DAO;
import cl.bootcampAndroidTD2024.DTO.Usuario;

import java.util.List;
/**
 * Interfaz para la lista de usuarios.
 * @author Fabiola Díaz <a href="https://github.com/fabyDiaz/EvaluacionIntegradoraM2TD">Github Fabiola Díaz</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IListaUsuarios {
    void agregarUsuario(Usuario usuario);
    void actualizarUsuario(Usuario usuario);
    void eliminarUsuario(String rut);
    Usuario obtenerUsuario(String rut);
    List<Usuario> obtenerTodosUsuarios();
}
