package interfaces;
import App.Usuario;

/**
 * Created by Shadow on 3/31/2017.
 */
public interface DAOUsuario {
    void registrar(Usuario user) throws Exception;
    void modificar(Usuario user) throws Exception;
    void eliminar(Usuario user) throws Exception;
}
