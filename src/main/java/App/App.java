package App;

/**
 * Created by Shadow on 3/31/2017.
 */
public class App {
    public static void main(String[] args) {
       AgregarSaldo ventana= new AgregarSaldo();

        SerialCon serial = new SerialCon();
        serial.initialize();
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
    }
}