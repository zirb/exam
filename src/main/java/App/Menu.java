package App;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Shadow on 4/11/2017.
 */
public class Menu {
    private JButton cargarSaldoButton;
    private JButton añadirClienteButton;
    private JPanel panel1;

    public Menu() {
        añadirClienteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AnadirCliente.main(null);
            }
        });
        cargarSaldoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                AgregarSaldo.main(null);
            }
        });
    }
    public static void main(String[] args) {
        JFrame ventana = new JFrame();
        ventana.setContentPane(new Menu().panel1);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.pack();
        ventana.setVisible(true);
    }
}
