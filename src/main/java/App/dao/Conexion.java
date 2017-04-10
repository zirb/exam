package App.dao;

import com.mysql.cj.jdbc.ConnectionImpl;

import java.sql.*;

/**
 * Created by Shadow on 3/31/2017.
 */
public class Conexion {
    protected Connection conexion;
    private final  String URL ="jdbc:mysql://localhost:3306/acceso?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final  String Username ="root";
    private final  String Password ="";
    public void conectar() throws SQLException {
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            this.conexion = DriverManager.getConnection(URL,Username,Password);
        }catch (SQLException e){
            throw e;
        }
    }
    public void cerrar() throws SQLException {
       if (this.conexion!=null){
           if (!this.conexion.isClosed()){
               this.conexion.close();
           }
       }

    }
}
