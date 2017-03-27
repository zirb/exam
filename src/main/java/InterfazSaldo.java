import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by Shadow on 3/25/2017.
 */
public class InterfazSaldo extends JFrame {

    private JPanel panel1;
    private JTextField textField1;
    private JButton agregarSaldoButton;
    private JTextField textField2;
    private String id;
    private Float saldo;

    public InterfazSaldo() {
        agregarSaldoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                   id=textField1.getText();
                   saldo= Float.parseFloat(textField2.getText());
                    try {
                        Integer rs=SQLFunctions.insertaSaldo(SQLFunctions.getConnection(),id, saldo);
                        if(rs>0){
                        JOptionPane.showMessageDialog(null, "Operacion Correcta");
                        }
                    }catch (SQLException s){
                        JOptionPane.showMessageDialog(null, "Operacion incorrecta, intente de nuevo con otro id", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "El valor de saldo no es válido", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

    }

    public static void main(String[] args) {
        JFrame ventana = new JFrame();
        ventana.setContentPane(new InterfazSaldo().panel1);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.pack();
        ventana.setVisible(true);
    }
}
