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

public class Descripciones extends JFrame{
	private static Descripciones miDescripciones;
	
	private JPanel contentPane;
	private JPanel fondo;
	private JPanel sur;
    private JLabel titulo;
    private JButton salir;
    
    //Controlador
    private Controlador controlador = null;
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Descripciones frame = new Descripciones();
					frame.setTitle("Descripciones");
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    
	public static Descripciones getMiDescripciones() {
		if(Descripciones.miDescripciones==null) {
			Descripciones.miDescripciones = new Descripciones();
		}
		return Descripciones.miDescripciones;
	}
	
	private Descripciones() {				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));  //1x1
		contentPane.setBackground(Color.black);
		
		titulo = new JLabel();
		titulo.setText("Descripciones:");
		titulo.setFont(new Font("Impact", Font.PLAIN, 40));
		titulo.setForeground(Color.YELLOW);
		contentPane.add(titulo, BorderLayout.NORTH);
		
		fondo = new JPanel();
		fondo.setLayout(new GridLayout(5,2));
		fondo.setOpaque(false);
		contentPane.add(fondo, BorderLayout.CENTER);
		JLabel obt = new JLabel();
		
		sur = new JPanel();
		sur.setLayout(new FlowLayout());
		sur.setOpaque(false);
		contentPane.add(sur, BorderLayout.SOUTH);

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
				Descripciones.this.dispose();
			}
		}
	}
}
