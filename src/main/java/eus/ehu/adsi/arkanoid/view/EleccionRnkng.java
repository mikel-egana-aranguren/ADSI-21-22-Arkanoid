package eus.ehu.adsi.arkanoid.view;

	import java.awt.BorderLayout;
	import java.awt.Color;
	import java.awt.Dimension;
	import java.awt.EventQueue;
	import java.awt.FlowLayout;
	import java.awt.Font;
	import java.awt.Graphics;
	import java.awt.Image;
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

	public class EleccionRnkng extends JFrame implements ActionListener {
		private static EleccionRnkng mEleccionRnkng;
		private JPanel contentPane;




		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						EleccionRnkng frame = new EleccionRnkng();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		public static EleccionRnkng getMCont() {
			if(EleccionRnkng.mEleccionRnkng==null) {
				EleccionRnkng.mEleccionRnkng = new EleccionRnkng();
			}
			return EleccionRnkng.mEleccionRnkng;
		}

		private EleccionRnkng() {
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 1000, 800, 200);
			//POP UP CENTRADPO
			setLocationRelativeTo(null);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			contentPane.setBackground(new Color(100,176,155));
			contentPane.setOpaque(true);

			JLabel l1 = new JLabel("¿Qué ranking quieres ver?");
			l1.setFont(new Font("Lucida Sans", Font.BOLD, 20));
			l1.setForeground(Color.BLACK);
			l1.setBounds(250, 40, 1000, 23);
			contentPane.add(l1);
			
			JRadioButton j1 = new JRadioButton("Personal-Absoluto");
			j1.setBounds(40, 90, 170, 50);
			j1.addActionListener(this);
			j1.setBackground(new Color(100,176,155));
			contentPane.add(j1);
			
			JRadioButton j2 = new JRadioButton("Personal-Nivel");
			j2.setBounds(200, 90, 110, 50);
			j2.addActionListener(this);
			j2.setBackground(new Color(100,176,155));
			contentPane.add(j2);
			
			JRadioButton j3 = new JRadioButton("Global-Absoluto");
			j3.setBounds(340, 90, 170, 50);
			j3.addActionListener(this);
			j3.setBackground(new Color(100,176,155));
			contentPane.add(j3);
			
			JRadioButton j4 = new JRadioButton("Global-Nivel");
			j4.setBounds(480, 90, 110, 50);
			j4.addActionListener(this);
			j4.setBackground(new Color(100,176,155));
			contentPane.add(j4);
			
			JRadioButton j5 = new JRadioButton("Salir");
			j5.setBounds(620, 90, 80, 50);
			j5.setForeground(Color.RED);
			j5.addActionListener(this);
			j5.setBackground(new Color(100,176,155));
			contentPane.add(j5);
		}
		
		public void actionPerformed(ActionEvent e) {

			JRadioButton btn = (JRadioButton)e.getSource();
			
			String usuario =Arkanoid.getUsuarioIniciado();
			int nivel= ScoreBoard.getNivelActual();
			
			if (btn.getText().equals("Personal-Absoluto")){
				String s = Arkanoid.obtenerRankingPA(usuario);
				Ranking.main(null);
				Ranking.postearDatos(s);
				Ranking.cambiarConcepto("Ranking Personal y Absoluto:");
				
			}else if (btn.getText().equals("Personal-Nivel")){
				String s = Arkanoid.obtenerRankingPN(usuario, nivel);
				Ranking.main(null);
				Ranking.postearDatos(s);
				Ranking.cambiarConcepto("Ranking Personal y Por Nivel:");
				
			}else if (btn.getText().equals("Global-Absoluto")){
				String s = Arkanoid.obtenerRankingGA();
				Ranking.main(null);
				Ranking.postearDatos(s);
				Ranking.cambiarConcepto("Ranking Global y Absoluto:");
			
			}else if (btn.getText().equals("Global-Nivel")){
				String s = Arkanoid.obtenerRankingGN(nivel);
				Ranking.main(null);
				Ranking.postearDatos(s);
				Ranking.cambiarConcepto("Ranking Global y Por Nivel:");

			}else if (btn.getText().equals("Salir")){
				System.exit(getDefaultCloseOperation());
			}
		}
		
}
