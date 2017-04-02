package App.dao;
import App.Acceso;
import interfaces.DAOAcceso;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Shadow on 3/31/2017.
 */
public class DAOAccesoImpl extends Conexion implements DAOAcceso {
    public void registrar(Acceso acc) throws SQLException {
        try{
            this.conectar();
            PreparedStatement pst = this.conexion.prepareStatement("INSERT INTO accesos (id, fecha, dentro) VALUES(?,?,?)");
            pst.setString(1,acc.getId());
            pst.setDate(2,acc.getFecha());
            pst.setBoolean(3,acc.isDentro());
            pst.executeUpdate();
        }catch(SQLException e){
            throw e;
        }finally {
            this.cerrar();
        }
    }


}
