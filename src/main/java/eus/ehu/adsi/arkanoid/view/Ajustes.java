package eus.ehu.adsi.arkanoid.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import eus.ehu.adsi.arkanoid.Arkanoid;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

public class Ajustes extends JFrame implements ActionListener {
	private static Ajustes misAjustes;
	private String nUser;
	private JPanel contentPane = new JPanel();
	private JComboBox colorFondo = new JComboBox();
	private JComboBox colorLadrillo = new JComboBox();
	private JComboBox colorPaddle = new JComboBox();
	private JComboBox colorBola = new JComboBox();

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ajustes frame = new Ajustes();
					frame.setTitle("Ajustes");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static Ajustes getMisAjustes() {
		if (Ajustes.misAjustes==null) {
			Ajustes.misAjustes = new Ajustes();
		}
		return Ajustes.misAjustes;
	}
	
	public Ajustes() {
		
		String datos = Arkanoid.obtenerAjustes("anegda");
		String[] datos2 = datos.split(" ");
		String fondo = datos2[0];
		System.out.println(fondo);
		String ladrillo = datos2[1];
		System.out.println(fondo);
		String bola = datos2[2];
		System.out.println(fondo);
		String paddle = datos2[3];
		System.out.println(fondo);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		setLocationRelativeTo(null);
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblColorDeFondo = new JLabel("Color de fondo:");
		lblColorDeFondo.setForeground(Color.WHITE);
		lblColorDeFondo.setBounds(105, 44, 93, 16);
		contentPane.add(lblColorDeFondo);
		
		colorFondo.setModel(new DefaultComboBoxModel(new String[] {"Negro", "Amarillo", "Rojo ", "Azul"}));
		colorFondo.setBounds(249, 41, 88, 22);
		if(fondo.equalsIgnoreCase("Amarillo")) {
			colorFondo.setSelectedIndex(1);
		} else if(fondo.equalsIgnoreCase("Rojo")) {
			colorFondo.setSelectedIndex(2);
		} else if(fondo.equalsIgnoreCase("Azul")) {
			colorFondo.setSelectedIndex(3);
		} else {
			colorFondo.setSelectedIndex(0);
		}
		contentPane.add(colorFondo);
		
		JLabel lblColorDelLadrillo = new JLabel("Color del ladrillo:");
		lblColorDelLadrillo.setForeground(Color.WHITE);
		lblColorDelLadrillo.setBounds(105, 73, 108, 16);
		contentPane.add(lblColorDelLadrillo);
		
		colorLadrillo.setModel(new DefaultComboBoxModel(new String[] {"Negro", "Amarillo", "Rojo ", "Azul"}));
		colorLadrillo.setBounds(249, 70, 88, 22);
		if(ladrillo.equalsIgnoreCase("Negro")) {
			colorLadrillo.setSelectedIndex(0);
		} else if(ladrillo.equalsIgnoreCase("Amarillo")) {
			colorLadrillo.setSelectedIndex(1);
		} else if(ladrillo.equalsIgnoreCase("Rojo")) {
			colorLadrillo.setSelectedIndex(2);
		} else {
			colorLadrillo.setSelectedIndex(3);
		}
		contentPane.add(colorLadrillo);
		
		JLabel lblColorDeLaBola = new JLabel("Color de la bola:");
		lblColorDeLaBola.setForeground(Color.WHITE);
		lblColorDeLaBola.setBounds(105, 131, 108, 16);
		contentPane.add(lblColorDeLaBola);
		
		JLabel lblColorDelPaddle = new JLabel("Color del paddle:");
		lblColorDelPaddle.setForeground(Color.WHITE);
		lblColorDelPaddle.setBounds(105, 102, 108, 16);
		contentPane.add(lblColorDelPaddle);
		
		colorPaddle.setModel(new DefaultComboBoxModel(new String[] {"Negro", "Amarillo", "Rojo ", "Azul"}));
		colorPaddle.setBounds(249, 99, 88, 22);
		if(paddle.equalsIgnoreCase("Negro")) {
			colorPaddle.setSelectedIndex(0);
		} else if(paddle.equalsIgnoreCase("Amarillo")) {
			colorPaddle.setSelectedIndex(1);
		} else if(paddle.equalsIgnoreCase("Azul")) {
			colorPaddle.setSelectedIndex(3);
		} else {
			colorPaddle.setSelectedIndex(2);
		}
		contentPane.add(colorPaddle);
		
		colorBola.setModel(new DefaultComboBoxModel(new String[] {"Negro", "Amarillo", "Rojo ", "Azul"}));
		colorBola.setBounds(249, 128, 88, 22);
		if(bola.equalsIgnoreCase("Negro")) {
			colorBola.setSelectedIndex(0);
		} else if(bola.equalsIgnoreCase("Rojo")) {
			colorBola.setSelectedIndex(2);
		} else if(bola.equalsIgnoreCase("Azul")) {
			colorBola.setSelectedIndex(3);
		} else {
			colorBola.setSelectedIndex(1);
		}
		contentPane.add(colorBola);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(163, 175, 102, 25);
		btnSalir.addActionListener(this);
		contentPane.add(btnSalir);
		
	}
	
	public void actionPerformed(ActionEvent e) {		
		JButton btn = (JButton)e.getSource();		
		if(btn.getText().equals("Salir")) {
			String fondo = colorFondo.getSelectedItem().toString();
			String ladrillo = colorLadrillo.getSelectedItem().toString();
			String bola = colorBola.getSelectedItem().toString();
			String paddle = colorPaddle.getSelectedItem().toString();
			Arkanoid.cambiarColores(nUser,fondo,ladrillo,bola,paddle);
//			String[] arg = new String[1];
//			arg[0] = nUser;
			MenuPrincipal2.getMiMenuPrincipal2().main(null);;
			Ajustes.this.dispose();	
		} 		
	}
}
