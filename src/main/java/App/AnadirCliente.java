package App;

import App.dao.DAOUsuarioImpl;
import interfaces.DAOUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static java.lang.Float.parseFloat;

/**
 * Created by Shadow on 3/26/2017.
 */
public class AnadirCliente extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JPanel panel1;
    private JButton añadirClienteButton;

    public AnadirCliente() {
        añadirClienteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Usuario user = new Usuario();
                user.setId(textField1.getText());
                user.setNombre(textField2.getText());
                user.setCorreo(textField3.getText());
                try {
                    user.setSaldo(parseFloat(textField4.getText()));
                }catch (Exception err){
                    JOptionPane.showMessageDialog(null,"El saldo no es un numero válido","Error",JOptionPane.ERROR_MESSAGE);
                }
                try{
                    DAOUsuario daousr = new DAOUsuarioImpl();
                    daousr.registrar(user);
                    JOptionPane.showMessageDialog(null,"Usuario registrado","Correcto",JOptionPane.INFORMATION_MESSAGE);
                }catch (SQLException sqlerr){
                    JOptionPane.showMessageDialog(null,"El usuario no fue encontrado","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame ventana = new JFrame();
        ventana.setContentPane(new AnadirCliente().panel1);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.pack();
        ventana.setVisible(true);
    }
}