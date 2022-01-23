package eus.ehu.adsi.arkanoid.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import eus.ehu.adsi.arkanoid.Arkanoid;

//	import modelo.CatalogoSudokus;
//	import modelo.JuegoActual;
//	import modelo.Jugador;
//	import modelo.ListaCasillas;
//	import principal.ProgramaPrincipal;
//	import vista.Login.FondoPanel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CambiarContrasena extends JFrame implements ActionListener {
    private static CambiarContrasena mCambiarContrasena;
    private JPanel contentPane;
    private JPasswordField password;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CambiarContrasena frame = new CambiarContrasena();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static CambiarContrasena getMCont() {
        if (CambiarContrasena.mCambiarContrasena == null) {
            CambiarContrasena.mCambiarContrasena = new CambiarContrasena();
        }
        return CambiarContrasena.mCambiarContrasena;
    }

    private CambiarContrasena() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 420);
        //POP UP CENTRADPO
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setOpaque(true);

        JLabel l1 = new JLabel("Cambiar Contraseña");
        l1.setFont(new Font("Lucida Sans", Font.BOLD, 20));
        l1.setForeground(Color.BLACK);
        l1.setBounds(70, 40, 1000, 23);
        contentPane.add(l1);


        password = new JPasswordField(20);
        password.setBounds(120,180,150,20);
        contentPane.add(password);

        JLabel passwordLabel = new JLabel("Nueva Contraseña");
        passwordLabel.setFont(new Font("Lucida Sans", Font.BOLD, 12));
        passwordLabel.setForeground(Color.BLACK);
        passwordLabel.setBounds(120, 150, 1000, 23);
        contentPane.add(passwordLabel);

        Button iniciar = new Button("Restaurar Contraseña");
        iniciar.setBounds(110, 300, 170, 50);
        iniciar.addActionListener(this);
        iniciar.setBackground(new Color(255, 255, 255));
        contentPane.add(iniciar);

    }

    public void actionPerformed(ActionEvent e) {
        String contrasena = password.getText();
        Arkanoid.cambiarContrasenaUsuarioIniciado(contrasena);
    }
}
