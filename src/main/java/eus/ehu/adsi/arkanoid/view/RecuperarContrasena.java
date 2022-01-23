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

public class RecuperarContrasena extends JFrame implements ActionListener {
    private static RecuperarContrasena mRecuperarContrasena;
    private JPanel contentPane;
    private JTextField user;
    private JPasswordField password;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RecuperarContrasena frame = new RecuperarContrasena();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static RecuperarContrasena getMCont() {
        if (RecuperarContrasena.mRecuperarContrasena == null) {
            RecuperarContrasena.mRecuperarContrasena = new RecuperarContrasena();
        }
        return RecuperarContrasena.mRecuperarContrasena;
    }

    private RecuperarContrasena() {

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

        JLabel l1 = new JLabel("Recuperar Contraseña");
        l1.setFont(new Font("Lucida Sans", Font.BOLD, 20));
        l1.setForeground(Color.BLACK);
        l1.setBounds(70, 40, 1000, 23);
        contentPane.add(l1);

        JLabel userLabel = new JLabel("Nombre de usuario");
        userLabel.setFont(new Font("Lucida Sans", Font.BOLD, 12));
        userLabel.setForeground(Color.BLACK);
        userLabel.setBounds(120, 90, 1000, 23);
        contentPane.add(userLabel);

        user = new JTextField(20);
        user.setBounds(120,120,150,20);
        contentPane.add(user);

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
        String usuario = user.getText();
        String contrasena = password.getText();
        Arkanoid.modificarContrasena(usuario,contrasena);
    }
}
