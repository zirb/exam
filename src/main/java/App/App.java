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
       AgregarSaldo.main(args);

        SerialCon serial = new SerialCon();
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
        System.out.println("Empezó");
        //System.out.print(Autorizar_acceso.insert_access("3"));
    }

    public static java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }
}
