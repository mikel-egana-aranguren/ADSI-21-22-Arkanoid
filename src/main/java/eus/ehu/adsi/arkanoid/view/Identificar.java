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
import eus.ehu.adsi.arkanoid.GestorBD;

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

public class Identificar extends JFrame implements ActionListener {
    private static Identificar mIdentificar;
    private JPanel contentPane;
    private JTextField user;
    private JPasswordField password;
    private JButton iniciar;
    private JButton recuperar;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Identificar frame = new Identificar();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static Identificar getMCont() {
        if (Identificar.mIdentificar == null) {
            Identificar.mIdentificar = new Identificar();
        }
        return Identificar.mIdentificar;
    }

    private Identificar() {

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

        JLabel l1 = new JLabel("Identificarse");
        l1.setFont(new Font("Lucida Sans", Font.BOLD, 20));
        l1.setForeground(Color.BLACK);
        l1.setBounds(120, 40, 1000, 23);
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

        JLabel passwordLabel = new JLabel("Contraseña");
        passwordLabel.setFont(new Font("Lucida Sans", Font.BOLD, 12));
        passwordLabel.setForeground(Color.BLACK);
        passwordLabel.setBounds(120, 150, 1000, 23);
        contentPane.add(passwordLabel);

        iniciar = new JButton("Iniciar Sesion");
        iniciar.setBounds(120, 240, 170, 50);
        iniciar.addActionListener(this);
        iniciar.setBackground(new Color(255, 255, 255));
        contentPane.add(iniciar);

        recuperar = new JButton("Recuperar Contraseña");
        recuperar.setBounds(120, 300, 170, 50);
        recuperar.addActionListener(this);
        recuperar.setBackground(new Color(255, 255, 255));
        contentPane.add(recuperar);
    }

    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton)e.getSource();
        if (btn.equals(iniciar)) {
            String usuario = user.getText();
            String contrasena = password.getText();
            Arkanoid.iniciarSesion(usuario,contrasena);
        }
        else{
            System.out.println("Hola");
        }
    }
}
		
