package interfaces;

import App.Acceso;

import java.sql.SQLException;

/**
 * Created by Shadow on 3/31/2017.
 */
public interface DAOAcceso {
    void registrar(Acceso acc) throws SQLException;
}
