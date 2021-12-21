package eus.ehu.adsi.arkanoid.view;
 
import javax.swing.JFrame;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class SelectNivel {

	private JFrame frame;
	private JPanel panelCentro;
	private JButton btnComenzar;
    private JButton btnCancelar;
	private JLabel lblNivel;
	private JComboBox<String> comboBoxNivel;
	
	public SelectNivel() {
		initialize();
	}
	
	
	private void initialize() {
		frame = new JFrame();
		frame.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		frame.setTitle("Seleccionar Nivel");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(getPanelCentro());
		frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
	}
	
	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new JPanel();
			panelCentro.setBounds(0, 0, 828, 477);
			GridBagLayout gbl_panelCentro = new GridBagLayout();
			gbl_panelCentro.columnWidths = new int[]{284, 72, 91, 63, 0, 0};
			gbl_panelCentro.rowHeights = new int[]{212, 0, 0, 19, 68, 0};
			gbl_panelCentro.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_panelCentro.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panelCentro.setLayout(gbl_panelCentro);
			GridBagConstraints gbc_lblNivel = new GridBagConstraints();
			gbc_lblNivel.anchor = GridBagConstraints.EAST;
			gbc_lblNivel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNivel.gridx = 1;
			gbc_lblNivel.gridy = 1;
			panelCentro.add(getLblNombre(), gbc_lblNivel);
			GridBagConstraints gbc_comboBoxNivel = new GridBagConstraints();
			gbc_comboBoxNivel.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxNivel.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBoxNivel.gridx = 1;
			gbc_comboBoxNivel.gridy = 2;
			panelCentro.add(getComboBoxNivel(), gbc_comboBoxNivel);
			GridBagConstraints gbc_btnComenzar = new GridBagConstraints();
			gbc_btnComenzar.insets = new Insets(0, 0, 0, 5);
			gbc_btnComenzar.gridx = 2;
			gbc_btnComenzar.gridy = 4;
			panelCentro.add(getBtnComenzar(), gbc_btnComenzar);
            GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
			gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
			gbc_btnCancelar.gridx = 1;
			gbc_btnCancelar.gridy = 4;
			panelCentro.add(getBtnCancelar(), gbc_btnCancelar);
          	}
		return panelCentro;
	}
	private JButton getBtnComenzar() {
		if (btnComenzar == null) {
			btnComenzar = new JButton("Comenzar");
			btnComenzar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
									
				}
			});
		}
		return btnComenzar;
	}

    private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
									
				}
			});
		}
		return btnCancelar;
	}
	private JLabel getLblNombre() {
		if (lblNivel == null) {
			lblNivel = new JLabel("Escoge un nivel");
			}
		return lblNivel;
	}

    private JComboBox<String> getComboBoxNivel() {
		if (comboBoxNivel == null) {
			comboBoxNivel = new JComboBox<String>();
			comboBoxNivel.setModel(new DefaultComboBoxModel<String>(new String[] {"F\u00E1cil", "Medio", "Dif\u00EDcil"}));
		}
		return comboBoxNivel;
	}
}