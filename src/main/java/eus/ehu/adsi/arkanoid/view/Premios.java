package eus.ehu.adsi.arkanoid.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import eus.ehu.adsi.arkanoid.Arkanoid;
import eus.ehu.adsi.arkanoid.core.Game;

public class Premios extends JFrame{
	private static Premios miPremios;
	private JPanel contentPane;
	private JPanel fondo;
	private JPanel sur;
    private JLabel titulo;
    private JButton salir;
    private JButton desc;
    private String usuarioAct;
    //Prueba
    //Controlador
    private Controlador controlador = null;
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Premios frame = new Premios();
					frame.setTitle("Premios");
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static Premios getMiPremios() {
		if(Premios.miPremios==null) {
			Premios.miPremios = new Premios();
		}
		return Premios.miPremios;
	}

	private Premios() {				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));  //1x1
		contentPane.setBackground(Color.black);
		
		titulo = new JLabel();
		titulo.setText("Premios:");
		titulo.setFont(new Font("Impact", Font.PLAIN, 40));
		titulo.setForeground(Color.YELLOW);
		contentPane.add(titulo, BorderLayout.NORTH);
		
		fondo = new JPanel();
		fondo.setLayout(new GridLayout(10,1));
		fondo.setOpaque(false);
		contentPane.add(fondo, BorderLayout.CENTER);
		JLabel obt = new JLabel();
		obt.setText("Premios obtenidos: ");
		obt.setFont(new Font("Consolas", Font.PLAIN, 20));
		obt.setForeground(Color.WHITE);
		String rs = Arkanoid.obtenerPremiosObtenidos(Arkanoid.getUsuarioIniciado());

		fondo.add(obt);
		String[] lineas = rs.split("8");
		for (String linea : lineas) {
			JLabel l = new JLabel();
			System.out.println(linea);
			l.setText(linea);
			l.setForeground(Color.BLUE);
			l.setFont(new Font("Consolas", Font.PLAIN, 16));
			fondo.add(l);
		}	

		JLabel nObt = new JLabel();
		nObt.setText("Premios no obtenidos: ");
		nObt.setFont(new Font("Consolas", Font.PLAIN, 20));
		nObt.setForeground(Color.WHITE);
		fondo.add(nObt);
		String rs2 = Arkanoid.obtenerPremiosNoObtenidos(Arkanoid.getUsuarioIniciado());

		String[] lineas2 = rs2.split("8");
		for (String linea2 : lineas2) {
			JLabel l2 = new JLabel(); 
			System.out.println(linea2);
			l2.setText(linea2);
			l2.setForeground(Color.RED);
			l2.setFont(new Font("Consolas", Font.PLAIN, 16));
			fondo.add(l2);
		}

		
		sur = new JPanel();
		sur.setLayout(new FlowLayout());
		sur.setOpaque(false);
		contentPane.add(sur, BorderLayout.SOUTH);
		
		desc = new JButton();
		desc.setText("DESCRIPCIONES");
		desc.addActionListener(getControlador());
		desc.setPreferredSize(new Dimension(150, 20));
		sur.add(desc);

		salir = new JButton();
		salir.addActionListener(getControlador());
		salir.setText("SALIR");
		salir.setPreferredSize(new Dimension(150, 20));
		sur.add(salir);
	}	
	
	//Controlador
	private Controlador getControlador() {
		if (controlador == null) {
			controlador = new Controlador();
		}
		return controlador;
	}
		
	private class Controlador implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton)e.getSource();
			if(btn.getText().equals("SALIR")) {
				Premios.this.dispose();
			}
			else{
				Descripciones.getMiDescripciones().main(null);
				Premios.this.dispose();
			}
		}
	}
}
