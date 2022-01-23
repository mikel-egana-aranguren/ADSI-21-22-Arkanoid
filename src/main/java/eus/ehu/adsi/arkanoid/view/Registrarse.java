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

public class Registrarse extends JFrame implements ActionListener {
    private static Registrarse mRegistrarse;
    private JPanel contentPane;
    private JTextField user;
    private JTextField email;
    private JPasswordField password;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Registrarse frame = new Registrarse();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static Registrarse getMCont() {
        if (Registrarse.mRegistrarse == null) {
            Registrarse.mRegistrarse = new Registrarse();
        }
        return Registrarse.mRegistrarse;
    }

    private Registrarse() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 450);
        //POP UP CENTRADPO
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setOpaque(true);

        JLabel l1 = new JLabel("Registrarse");
        l1.setFont(new Font("Lucida Sans", Font.BOLD, 20));
        l1.setForeground(Color.BLACK);
        l1.setBounds(120, 40, 1000, 23);
        contentPane.add(l1);

        JLabel emailLable = new JLabel("Correo electronico");
        emailLable.setFont(new Font("Lucida Sans", Font.BOLD, 12));
        emailLable.setForeground(Color.BLACK);
        emailLable.setBounds(120, 90, 1000, 23);
        contentPane.add(emailLable);

        email = new JTextField(20);
        email.setBounds(120,120,150,20);
        contentPane.add(email);

        JLabel userLabel = new JLabel("Nombre de usuario");
        userLabel.setFont(new Font("Lucida Sans", Font.BOLD, 12));
        userLabel.setForeground(Color.BLACK);
        userLabel.setBounds(120, 150, 1000, 23);
        contentPane.add(userLabel);

        user = new JTextField(20);
        user.setBounds(120,180,150,20);
        contentPane.add(user);

        JLabel passwordLabel = new JLabel("Contraseña");
        passwordLabel.setFont(new Font("Lucida Sans", Font.BOLD, 12));
        passwordLabel.setForeground(Color.BLACK);
        passwordLabel.setBounds(120, 210, 1000, 23);
        contentPane.add(passwordLabel);

        password = new JPasswordField(20);
        password.setBounds(120,240,150,20);
        contentPane.add(password);



        Button registrarse = new Button("Registrarse");
        registrarse.setBounds(120, 300, 170, 50);
        registrarse.addActionListener(this);
        registrarse.setBackground(new Color(255, 255, 255));
        contentPane.add(registrarse);

    }

    public void actionPerformed(ActionEvent e) {
        String correo = email.getText();
        String usuario = user.getText();
        String contrasena = password.getText();
        Arkanoid.registrarse(correo,usuario,contrasena);
    }
}

