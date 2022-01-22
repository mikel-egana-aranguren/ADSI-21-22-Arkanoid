package eus.ehu.adsi.arkanoid.view;

	import java.awt.BorderLayout;
	import java.awt.Color;
	import java.awt.Dimension;
	import java.awt.EventQueue;
	import java.awt.FlowLayout;
	import java.awt.Font;
	import java.awt.GridLayout;
	import java.awt.Image;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.util.ArrayList;
	import java.util.Iterator;

	import javax.swing.DefaultComboBoxModel;
	import javax.swing.ImageIcon;
	import javax.swing.JButton;
	import javax.swing.JComboBox;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JPanel;
	import javax.swing.JScrollPane;
	import javax.swing.JTextArea;
	import javax.swing.border.EmptyBorder;


		public class RankingAbsoluto extends JFrame implements ActionListener {
			private static RankingAbsoluto mRanking;
			private JPanel contentPane;
		    private JPanel fondo ;
			private JPanel este;
			private JPanel centro;
//			private JComboBox nvl;
//			private JComboBox dfclt;
			private JTextArea ayudatxt= new JTextArea("\tNombre \tNivel\tPuntos \tFecha", 50, 60);

		    
			public static void main(String[] args) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							RankingAbsoluto frame = new RankingAbsoluto();
							frame.setTitle("Ranking de arkanoid");
							Image imagen = new ImageIcon(getClass().getResource("IconoRanking.png")).getImage();
							frame.setIconImage(imagen);
							frame.setVisible(true);
							frame.setResizable(false);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
			
			public static RankingAbsoluto getMiRankingAbsoluto() {
				if(RankingAbsoluto.mRanking==null) {
					RankingAbsoluto.mRanking = new RankingAbsoluto();
				}
				return RankingAbsoluto.mRanking;
			}

			private RankingAbsoluto() {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(100, 100, 800, 600);
				//POP UP CENTRADPO
				setLocationRelativeTo(null);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane);
				contentPane.setLayout(new GridLayout(1, 1));  //1x1
				contentPane.add(getFondo());
			
			}
			
			private JPanel getFondo() {
				if (fondo == null) {
					fondo = new JPanel();
					fondo.setLayout(new BorderLayout(0, 0));
					fondo.add(getCentro(), BorderLayout.CENTER);
				}
				fondo.setBackground(new Color(100,176,155));
				fondo.setOpaque(true);
				return fondo;
			}

			
			private JPanel getCentro() {
				if(centro==null) {
					centro = new JPanel();
					centro.setLayout(new BorderLayout()); //2x1
				}
				//Texto de Ranking 
				JPanel textArea = new JPanel();
				FlowLayout a = new FlowLayout();
				textArea.setLayout(a);
				ayudatxt.setEditable(false);
				ayudatxt.setLineWrap(true);
				ayudatxt.setPreferredSize(new Dimension(675, 20));
				textArea.add(ayudatxt);
				JScrollPane sp = new JScrollPane(textArea);
				
				JPanel etiqueta = new JPanel();
				etiqueta.setLayout(new FlowLayout());
				String s = "hola";
				JLabel txt= new JLabel(s);
				txt.setFont(new Font("Lucida Sans", Font.BOLD, 30));
				txt.setForeground(Color.WHITE);
				etiqueta.add(txt);
				
				JPanel botones = new JPanel();
				botones.setOpaque(false);
				botones.setLayout(new GridLayout(0,5));
				
				JPanel volver = new JPanel();
				volver.setOpaque(false);
				volver.setLayout(new FlowLayout());
				JButton bVolver = new JButton("VOLVER");
				bVolver.setPreferredSize(new Dimension(20, 20));
				bVolver.addActionListener(this);
				volver.add(bVolver);
				
				JPanel salir = new JPanel();
				salir.setOpaque(false);
				salir.setLayout(new FlowLayout());
				JButton bSalir = new JButton("SALIR");
				bSalir.setPreferredSize(new Dimension(20, 20));
				bSalir.addActionListener(this);
				salir.add(bSalir);
				
				JPanel e1 = new JPanel();
				e1.setLayout(new FlowLayout());
				JLabel relleno1= new JLabel(" ");
				e1.add(relleno1);
				
				JPanel e2 = new JPanel();
				e2.setLayout(new FlowLayout());
				JLabel relleno2= new JLabel(" ");
				e2.add(relleno2);
				
				JPanel e3 = new JPanel();
				e3.setLayout(new FlowLayout());
				JLabel relleno3= new JLabel(" ");
				e3.add(relleno3);
				
				
				botones.add(relleno1);
				botones.add(bVolver);
				botones.add(relleno2);
				botones.add(bSalir);
				botones.add(relleno3);


				centro.add(etiqueta, BorderLayout.NORTH);
				centro.add(sp, BorderLayout.CENTER);
				centro.add(botones,BorderLayout.SOUTH);
				
				etiqueta.setOpaque(false);
				botones.setOpaque(false);
				centro.setOpaque(false);
				
				return centro;
			}
			

			///////////////////////// BOTONES /////////////////////////////////
			
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton)e.getSource();
				ayudatxt.setText(null);
				if (btn.getText().equals("VOLVER"))
				{			
					
				}
				else if (btn.getText().equals("SALIR"))
				{
					System.exit(getDefaultCloseOperation());
				}
		}
	}

