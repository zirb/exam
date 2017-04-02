package App;

import App.dao.DAOUsuarioImpl;
import interfaces.DAOUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static java.lang.Float.parseFloat;

/**
 * Created by Shadow on 3/25/2017.
 */
public class AgregarSaldo extends JFrame {

    private JPanel panel1;
    private JTextField textField1;
    private JButton agregarSaldoButton;
    private JTextField textField2;
    private String id;
    private Float saldo;

    public AgregarSaldo() {


        agregarSaldoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Usuario user = new Usuario();
                user.setId(textField1.getText());
                try {
                    user.setSaldo(parseFloat(textField2.getText()));
                }catch (Exception err){
                    JOptionPane.showMessageDialog(null,"El saldo no es un numero válido","Error",JOptionPane.ERROR_MESSAGE);
                }
                try{
                    DAOUsuario daousr = new DAOUsuarioImpl();
                    daousr.modificar(user);
                }catch (SQLException sqlerr){
                    JOptionPane.showMessageDialog(null,"El usuario no fue encontrado","Error",JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    public static void main(String[] args) {
        JFrame ventana = new JFrame();
        ventana.setContentPane(new AgregarSaldo().panel1);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.pack();
        ventana.setVisible(true);
    }
}
