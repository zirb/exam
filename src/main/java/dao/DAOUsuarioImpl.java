package dao;

import App.Usuario;
import interfaces.DAOUsuario;

import java.sql.PreparedStatement;

/**
 * Created by Shadow on 3/31/2017.
 */
public class DAOUsuarioImpl extends Conexion implements DAOUsuario {
    public void registrar(Usuario user) throws Exception {
        try{
            this.conectar();
            PreparedStatement pst = this.conexion.prepareStatement("INSERT INTO Users(nombre) VALUES(?)");
            pst.setString(1,user.getNombre());
            pst.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally {
            this.cerrar();
        }
    }

    public void modificar(Usuario user) throws Exception {
        try{
            this.conectar();
            PreparedStatement pst = this.conexion.prepareStatement("UPDATE Users set nombre = ? where id = ?");
            pst.setString(1,user.getNombre());
            pst.setString(2,user.getId());
            pst.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally {
            this.cerrar();
        }
    }

    public void eliminar(Usuario user) throws Exception {
        try{
            this.conectar();
            PreparedStatement pst = this.conexion.prepareStatement("Delete from Users where id = ?");
            pst.setString(1,user.getId());
            pst.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally {
            this.cerrar();
        }
    }
}
