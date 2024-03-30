package cl.bootcampAndroidTD2024.DAO;
import cl.bootcampAndroidTD2024.DTO.Usuario;

import java.util.List;

public interface IListaUsuarios {
    void agregarUsuario(Usuario usuario);
    void actualizarUsuario(Usuario usuario);
    void eliminarUsuario(int id);
    Usuario obtenerUsuario(int id);
    List<Usuario> obtenerTodosUsuarios();
}
