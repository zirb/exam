package interfaces;
import App.Usuario;

import java.sql.SQLException;

/**
 * Created by Shadow on 3/31/2017.
 */
public interface DAOUsuario {
    void registrar(Usuario user) throws SQLException;
    void modificar(Usuario user) throws SQLException;
    void eliminar(Usuario user) throws SQLException;
    Usuario listar(String id)throws SQLException;
}
