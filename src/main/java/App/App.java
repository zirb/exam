package App;


import App.dao.DAOAccesoImpl;
import App.dao.DAOUsuarioImpl;
import interfaces.DAOAcceso;
import interfaces.DAOUsuario;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by Shadow on 3/31/2017.
 */
public class App {
    public static void main(String[] args) {
       AgregarSaldo ventana= new AgregarSaldo();
       ventana.main(args);
        /*SerialCon serial = new SerialCon();
        serial.initialize();
        Thread t=new Thread() {
            public void run() {
                //the following line will keep this app alive for 1000 seconds,
                //waiting for events to occur and responding to them (printing incoming messages to console).
                try {Thread.sleep(1000000);}
                catch (InterruptedException ie) {
                    System.out.print(ie.toString());
                }
            }
        };
        t.start();
        System.out.println("Empezó");*/
        Acceso acc = new Acceso();
        /*acc.setId("5");
        java.util.Date utilDate = new java.util.Date();
        acc.setFecha(convertJavaDateToSqlDate(utilDate));
        acc.setDentro(true);
        try{
            DAOAcceso daoacc = new DAOAccesoImpl();
            daoacc.registrar(acc);
            System.out.println("Acceso autorizado");
            try {
                DAOUsuario daousr = new DAOUsuarioImpl();
                Usuario user=null;
                user=daousr.listar(acc.getId());
                Correos.manda(user.getCorreo(),"Hola"+user.getNombre()+". Su saldo es de "+user.getSaldo()+" pesos");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (SQLException s){
            if(s.getErrorCode()==1644){
                System.out.println("No tiene suficiente saldo");
            }
            System.out.println("Acceso no autorizado");
        }*/
        //prueba();

    }
    public static void prueba(){
        System.out.print("Introduzca un id de usuario valido:");
        Scanner sc = new Scanner(System.in);
        String inputLine=sc.nextLine();
        System.out.println(inputLine);
        Acceso acc = new Acceso();
        acc.setId(inputLine);
        java.util.Date utilDate = new java.util.Date();
        acc.setFecha(convertJavaDateToSqlDate(utilDate));
        acc.setDentro(true);
        try{
            DAOAcceso daoacc = new DAOAccesoImpl();
            daoacc.registrar(acc);
            System.out.println("Acceso autorizado");
            try {
                DAOUsuario daousr = new DAOUsuarioImpl();
                Usuario user;
                user=daousr.listar(acc.getId());
                Correos.manda(user.getCorreo(),"Su saldo es de "+user.getSaldo()+" pesos");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (SQLException s){
            System.out.println("Acceso no autorizado");
        }

    }
    public static java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }
}
