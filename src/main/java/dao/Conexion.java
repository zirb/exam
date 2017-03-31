package dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Shadow on 3/31/2017.
 */
public class Conexion {
    protected Connection conexion;
    private final  String URL ="jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final  String Username ="root";
    private final  String Password ="";
    public void conectar() throws SQLException {
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            Connection con = DriverManager.getConnection(URL,Username,Password);
        }catch (SQLException e){
            throw e;
        }
    }
    public void cerrar() throws SQLException {
       if (conexion!=null){
           if (!conexion.isClosed()){
               conexion.close();
           }
       }

    }
}
