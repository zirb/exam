package App.dao;

import App.Usuario;
import interfaces.DAOUsuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shadow on 3/31/2017.
 */
public class DAOUsuarioImpl extends Conexion implements DAOUsuario {
    public void registrar(Usuario user) throws SQLException {
        try{
            this.conectar();
            PreparedStatement pst = this.conexion.prepareStatement("INSERT INTO Users(id,nombre,correo,saldo) VALUES(?,?,?,?)");
            pst.setString(1,user.getId());
            pst.setString(2,user.getNombre());
            pst.setString(3,user.getCorreo());
            pst.setFloat(4,user.getSaldo());
            pst.executeUpdate();
        }catch(SQLException e){
            throw e;
        }finally {
            this.cerrar();
        }
    }

    public void modificar(Usuario user) throws SQLException {
        try{
            this.conectar();
            PreparedStatement pst = this.conexion.prepareStatement("UPDATE Users set saldo = saldo + ? where nombre = ?");
            pst.setFloat(1,user.getSaldo());
            pst.setString(2,user.getNombre());
            Integer i = pst.executeUpdate();
            if(i==0){
                SQLException e2 = new SQLException();
                throw e2;
            }
        }catch(SQLException e){
            throw e;
        }finally {
            this.cerrar();
        }

    }

    public void eliminar(Usuario user) throws SQLException {
        try{
            this.conectar();
            PreparedStatement pst = this.conexion.prepareStatement("Delete from Users where id = ?");
            pst.setString(1,user.getId());
            pst.executeUpdate();
        }catch(SQLException e){
            throw e;
        }finally {
            this.cerrar();
        }
    }
    public Usuario listar(String id) throws SQLException {
        Usuario user = new Usuario();
        try{
            this.conectar();
            PreparedStatement pst = this.conexion.prepareStatement("Select * from Users where id = ?");
            pst.setString(1,id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                user.setId(rs.getString("id"));
                user.setNombre(rs.getString("nombre"));
                user.setCorreo(rs.getString("correo"));
                user.setSaldo(rs.getFloat("saldo"));
            }
        }catch(SQLException e){
            throw e;
        }finally {
            this.cerrar();
        }
        return user;
    }
}
