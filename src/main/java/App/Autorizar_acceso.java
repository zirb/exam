package App;

import App.dao.DAOAccesoImpl;
import App.dao.DAOUsuarioImpl;
import interfaces.DAOAcceso;
import interfaces.DAOUsuario;

import javax.swing.*;
import java.sql.SQLException;

import static App.App.convertJavaDateToSqlDate;

/**
 * Created by Shadow on 4/2/2017.
 */
public class Autorizar_acceso {
    public static int insert_access (String id) {
        int res=0;
        Acceso acc = new Acceso();
        acc.setId(id);
        java.util.Date utilDate = new java.util.Date();
        acc.setFecha(convertJavaDateToSqlDate(utilDate));
        acc.setDentro(true);
        try {
            DAOAcceso daoacc = new DAOAccesoImpl();
            daoacc.registrar(acc);
            System.out.println("Acceso autorizado");

            res=1;
            try {
                DAOUsuario daousr = new DAOUsuarioImpl();
                Usuario user = null;
                user = daousr.listar(acc.getId());
                //Correos.manda(user.getCorreo(), "Hola " + user.getNombre() + ". Su saldo es de " + user.getSaldo() + " pesos");
                try{}catch (Exception e){}
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"La dirección de correo no es valida","Error",JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException s) {
            if (s.getErrorCode() == 1644) {
                System.out.println("No tiene suficiente saldo");
            }else{
                System.out.println("Esta tarjeta no es válida");
            }
            System.out.println("Acceso no autorizado");
            res=0;
        }finally {
            return res;
        }
    }
}

