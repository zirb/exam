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
        String texto = "";
        String texto2 = "";
        try {
            DAOAcceso daoacc = new DAOAccesoImpl();
            daoacc.registrar(acc);
            System.out.println("Acceso autorizado");

            try {
                DAOUsuario daousr = new DAOUsuarioImpl();
                Usuario user = null;
                user = daousr.listar(acc.getId());
                texto = ""+user.getSaldo();
                        texto2="Hola "+user.getNombre()+ ", Su saldo es de " +  texto +" pesos.\n";
                Correos.manda(user.getCorreo(), "" + texto2);
                try{}catch (Exception e){}
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"La dirección de correo no es valida","Error",JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException s) {
            if (s.getErrorCode() == 1644) {
                texto=" 0\n";
                System.out.println(texto);
            }else{
                texto="-1\n";
                System.out.println(texto);
            }
            System.out.println("Acceso no autorizado");
            res=0;
        }finally {
            //if(res==1){
                SerialCon2 serial2 = new SerialCon2();
                System.out.println("Mandar al display:"+texto);
                serial2.initialize(texto+"\n");
                Thread t2=new Thread() {
                    public void run() {
                        //the following line will keep this app alive for 1000 seconds,
                        //waiting for events to occur and responding to them (printing incoming messages to console).
                        try {

                            Thread.sleep(250);

                        }
                        catch (InterruptedException ie) {
                            System.out.print(ie.toString());
                        }
                    }
                };
                t2.start();

            //}
            return res;
        }
    }
}

