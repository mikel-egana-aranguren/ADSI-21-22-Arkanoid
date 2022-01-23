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
import java.util.Arrays;
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

import org.apache.logging.log4j.util.PropertySource.Comparator;


		public class Ranking extends JFrame implements ActionListener {
			private static Ranking mRanking;
			private JPanel contentPane;
		    private JPanel fondo ;
			private JPanel centro;	
			private static JLabel titulo = new JLabel("iniciando...");
			private static JPanel textArea = new JPanel();

		    
			public static void main(String[] args) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Ranking frame = new Ranking();
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
			
			public static Ranking getMiRanking() {
				if(Ranking.mRanking==null) {
					Ranking.mRanking = new Ranking();
				}
				return Ranking.mRanking;
			}
			
			
			private Ranking() {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(100, 100, 600, 600);
				//POP UP CENTRADO
				setLocationRelativeTo(null);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane);
				contentPane.setLayout(new GridLayout(1, 1));  //1x1
				contentPane.add(getFondo());
				//Ranking.postearDatos("urko#2#200#10-10-2020@juan#3#2030#10-12-2120@elpuma#1#20#10-10-2000");
				//Ranking.cambiarConcepto("titulo prueba");
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
				
				textArea.setLayout(null);
								
				JLabel titUser = new JLabel("Jugador");
				titUser.setFont(new Font("Arial", Font.BOLD, 15));
				titUser.setBounds(80, 0, 1000, 23);
				
				JLabel titLvl = new JLabel("Nivel");
				titLvl.setFont(new Font("Arial", Font.BOLD, 15));
				titLvl.setBounds(200, 0, 1000, 23);
				
				JLabel titPts = new JLabel("Puntos");
				titPts.setFont(new Font("Arial", Font.BOLD, 15));
				titPts.setBounds(310, 0, 1000, 23);
				
				JLabel titFecha = new JLabel("Fecha");
				titFecha.setFont(new Font("Arial", Font.BOLD, 15));
				titFecha.setBounds(420, 0, 1000, 23);
				
				textArea.add(titUser);
				textArea.add(titLvl);
				textArea.add(titPts);
				textArea.add(titFecha);	
				
				JScrollPane sp = new JScrollPane(textArea);
				
				JPanel etiqueta = new JPanel();
				etiqueta.setLayout(new FlowLayout());
				titulo.setFont(new Font("Lucida Sans", Font.BOLD, 30));
				titulo.setForeground(Color.WHITE);
				etiqueta.add(titulo);
				
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
				
////////////////////////////////------RELLENO------////////////////////////////////
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
////////////////////////////////------------------////////////////////////////////
				
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
			

////////////////////////////////////////RELLENO DE  DATOS ///////////////////////////////////////////////////

			public static void cambiarConcepto(String concepto) { //titulo del ranking
				titulo.setText(concepto);
			}
			
			public static void postearDatos(String datos) { 
				
				//Pasar string de resultado SQL a array de arrays
				//FORMATO: user#lvl#ptos#fecha$[...]
				
				String[] split = datos.split("@");
				int salto = 20;
				String[] split2;
	        	String pNom,pLvl,pPts,pFecha;
	        	
		        for (int i=0; i<split.length; i++) {		   
		        	String aux =split[i];
		        	split2 = aux.split("#");
		        	pNom = split2[0];
		        	pLvl = split2[1];
		        	pPts = split2[2];
		        	pFecha = split2[3];
		        	
		        	JLabel titUser = new JLabel(pNom);
					JLabel titLvl = new JLabel(pLvl);
					JLabel titPts = new JLabel(pPts);
					JLabel titFecha = new JLabel(pFecha);
					
					titUser.setBounds(80, salto, 1000, 23);
					titLvl.setBounds(200, salto, 1000, 23);
					titPts.setBounds(310, salto, 1000, 23);
					titFecha.setBounds(420, salto, 1000, 23);
					
					textArea.add(titUser);
					textArea.add(titLvl);
					textArea.add(titPts);
					textArea.add(titFecha);
					
					salto+=20;
		        	
		        			
		        }
		    }
	        
////////////////////////////////////////////////////////////////////////////////////////////////////


			   
//////////////////////////////////////// BOTONES ///////////////////////////////////////////////////
			
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton)e.getSource();
				//ayudatxt.setText(null);
				if (btn.getText().equals("VOLVER"))
				{						
					this.setVisible(false);
					EleccionRnkng.main(null);
				}
				else if (btn.getText().equals("SALIR"))
				{
					System.exit(getDefaultCloseOperation());
				}
		}
////////////////////////////////////////////////////////////////////////////////////////////////////

	}

