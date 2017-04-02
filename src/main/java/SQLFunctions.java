
import java.sql.*;

/**
 * Created by Shadow on 3/18/2017.
 */
public class SQLFunctions {

    private static final  String URL ="jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final  String Username ="root";
    private static final  String Password ="";

    public static Connection getConnection() throws SQLException {

        Connection con=null;

        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);

            con = DriverManager.getConnection(URL,Username,Password);

            Statement statement=con.createStatement();
            statement.execute("use acceso;");
            if(!con.isClosed()){
                System.out.print("Conexion exitosa!!");

            }
            statement.close();

        }catch(SQLException e){
            System.out.print(e);
        }
        return con;
    }

    public static Integer insertaAcceso(Connection con,String id,Date fecha,boolean dentro)
            throws SQLException {
        String query = "INSERT INTO accesos (id, fecha, dentro)\n" +
                "VALUES (?,?,?);";
        PreparedStatement stmt = con.prepareStatement(query);
        int rs=0;
        try {
            stmt.setString(1, id);
            stmt.setDate(2, fecha);
            stmt.setBoolean(3, dentro);
            rs = stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.print(e.getSQLState());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            con.close();
            return rs;
        }

    }

    public static Integer insertaSaldo(Connection con,String id,float saldo)
            throws SQLException {
        String query = "UPDATE users  SET saldo=saldo + ? \n" +
                "WHERE id = ?;";
        PreparedStatement stmt = con.prepareStatement(query);
        int rs=0;
        try {
            stmt.setFloat(1, saldo);
            stmt.setString(2, id);
            rs = stmt.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            System.out.print(stmt);
            System.out.println(e.getSQLState());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            con.close();
            return rs;
        }
    }

}
