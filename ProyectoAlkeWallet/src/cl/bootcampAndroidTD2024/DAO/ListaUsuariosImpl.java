package cl.bootcampAndroidTD2024.DAO;

import cl.bootcampAndroidTD2024.DTO.CuentaBancaria;
import cl.bootcampAndroidTD2024.DTO.Sesion;
import cl.bootcampAndroidTD2024.DTO.Usuario;
import java.util.ArrayList;
import java.util.List;

public class ListaUsuariosImpl implements IListaUsuarios {
    private List<Usuario> listaUsuarios = new ArrayList();

    public ListaUsuariosImpl() {
        this.listaUsuarios.add(new Usuario(1,"Homero","Simpson","123648965-6","+56963268745", new CuentaBancaria(),new Sesion("homero@correo.cl","homero123")));
        this.listaUsuarios.add(new Usuario(2,"Admin","Ejemplo","11111111-1","+5612345678", new CuentaBancaria(),new Sesion("admin@correo.cl","admin123")));
    }

    @Override
    public void agregarUsuario(Usuario usuario) {
        listaUsuarios.add(usuario);
    }

    @Override
    public void actualizarUsuario(Usuario usuarioActualizado) {
        for (Usuario u : listaUsuarios) {
            if (u.getIdusuario()== usuarioActualizado.getIdusuario()) {
                u.setNombreUsuario(usuarioActualizado.getNombreUsuario());
                //Agregar el resto
                break;
            }
        }
    }

    @Override
    public void eliminarUsuario(int id) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getIdusuario() == id) {
                listaUsuarios.remove(i);
                break; // Termina el bucle después de eliminar el producto
            }
        }
    }

    @Override
    public Usuario obtenerUsuario(int id) {
        for (Usuario u : listaUsuarios) {
            if (u.getIdusuario() == id) {
                return u;
            }
        }
        return null;
    }

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
