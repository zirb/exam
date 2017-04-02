package App;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Enumeration;

import App.dao.DAOAccesoImpl;
import App.dao.DAOUsuarioImpl;
import com.mysql.cj.jdbc.exceptions.MySQLQueryInterruptedException;
import gnu.io.*;
import interfaces.DAOAcceso;
import interfaces.DAOUsuario;

public class SerialCon implements SerialPortEventListener {
    SerialPort serialPort;
    /** The port we're normally going to use. */
    private static final String PORT_NAMES[] = {
            "/dev/tty.usbserial-A9007UX1", // Mac OS X
            "/dev/ttyACM0", // Raspberry Pi
            "/dev/ttyUSB0", // Linux
            "COM3", // Windows
    };
    /**
     * A BufferedReader which will be fed by a InputStreamReader
     * converting the bytes into characters
     * making the displayed results codepage independent
     */
    private BufferedReader input;
    /** The output stream to the port */
    private OutputStream output;
    /** Milliseconds to block while waiting for port open */
    private static final int TIME_OUT = 2000;
    /** Default bits per second for COM port. */
    private static final int DATA_RATE = 9600;

    public void initialize() {
        // the next line is for Raspberry Pi and
        // gets us into the while loop and was suggested here was suggested http://www.raspberrypi.org/phpBB3/viewtopic.php?f=81&t=32186
        //System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM0");

        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        //First, Find an instance of serial port as set in PORT_NAMES.
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            for (String portName : PORT_NAMES) {
                if (currPortId.getName().equals(portName)) {
                    portId = currPortId;
                    break;
                }
            }
        }
        if (portId == null) {
            System.out.println("No se pudo encontrar el puerto.");
            return;
        }

        try {
            // open serial port, and use class name for the appName.
            serialPort = (SerialPort) portId.open(this.getClass().getName(),
                    TIME_OUT);

            // set port parameters
            serialPort.setSerialPortParams(DATA_RATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            // open the streams
            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            output = serialPort.getOutputStream();

            // add event listeners
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    /**
     * This should be called when you stop using the port.
     * This will prevent port locking on platforms like Linux.
     */
    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    /**
     * Handle an event on the serial port. Read the data and print it.
     */
    public synchronized void serialEvent(SerialPortEvent oEvent) {
        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                String inputLine=input.readLine();
                System.out.println(inputLine);
                Acceso acc = new Acceso();
                acc.setId(inputLine);
                acc.setFecha((Date) new java.util.Date());
                acc.setDentro(true);
                try{
                    DAOAcceso daoacc = new DAOAccesoImpl();
                    daoacc.registrar(acc);
                    System.out.println("Acceso autorizado");
                    try {
                        DAOUsuario daousr = new DAOUsuarioImpl();
                        Usuario user = new Usuario();
                        user=daousr.listar(acc.getId());
                        Correos.manda(user.getCorreo(),"Su saldo es de "+user.getSaldo()+" pesos");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }catch (SQLException s){
                    System.out.println("Acceso no autorizado");
                }
                //if(SQLFunctions.insertaAcceso(SQLFunctions.getConnection(),"wq",1)>0){System.out.println("Acceso autorizado");}

            } catch (IOException e) {
                System.err.println(e.toString());
            }
        }
        // Ignore all the other eventTypes, but you should consider the other ones.
    }

    /*public static void main(String[] args) throws Exception {
        App.SerialCon main = new App.SerialCon();
        AgregarSaldo ventana= new AgregarSaldo();

        ventana.main(null);


        main.initialize();
        Thread t=new Thread() {
            public void run() {
                //the following line will keep this app alive for 1000 seconds,
                //waiting for events to occur and responding to them (printing incoming messages to console).
                try {Thread.sleep(1000000);}
                catch (InterruptedException ie) {}
            }
        };
        t.start();
        System.out.println("Empezó");
    }*/
}