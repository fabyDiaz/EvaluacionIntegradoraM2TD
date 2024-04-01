package cl.bootcampAndroidTD2024.DAO;

import cl.bootcampAndroidTD2024.DTO.CuentaBancaria;
import cl.bootcampAndroidTD2024.DTO.Sesion;
import cl.bootcampAndroidTD2024.DTO.Usuario;
import java.util.ArrayList;
import java.util.List;

public class ListaUsuariosImpl implements IListaUsuarios {
    private List<Usuario> listaUsuarios = new ArrayList();

    public ListaUsuariosImpl() {
        this.listaUsuarios.add(new Usuario("12222222-2","Homero","Simpson","+56963268745", new CuentaBancaria(),new Sesion("homero@correo.cl","homero123")));
        this.listaUsuarios.add(new Usuario("11111111-1","Admin","Ejemplo","+5612345678", new CuentaBancaria(),new Sesion("admin@correo.cl","admin123")));
    }

    @Override
    public void agregarUsuario(Usuario usuario) {
        listaUsuarios.add(usuario);
    }

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

    @Override
    public void eliminarUsuario(String rut) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getRutUsuario().equals(rut)) {
                listaUsuarios.remove(i);
                break; // Termina el bucle después de eliminar el producto
            }
        }
    }

    /*@Override
    public Usuario obtenerUsuario(String rut) {
        for (Usuario u : listaUsuarios) {
            if (u.getRutUsuario().equals(rut)) {
                return u;
            }
        }
        return null;
    }*/

    public Usuario obtenerUsuario(String rut, String correo) {
        for (Usuario u : listaUsuarios) {
            if (u.getRutUsuario().equals(rut)&& u.getSesion().getEmailUsuario().equals(correo)) {
                return u;
            }
        }
        return null;
    }
    @Override
    public Usuario obtenerUsuario(String correo) {
        for (Usuario u : listaUsuarios) {
            if (u.getSesion().getEmailUsuario().equals(correo)) {
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
