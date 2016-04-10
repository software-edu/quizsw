package br.org.uesb.quizsw.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Janela_Cadastro_Niveis extends JFrame {

	private JPanel contentPane;
	private JTextField tfNivel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela_Cadastro_Niveis frame = new Janela_Cadastro_Niveis();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Janela_Cadastro_Niveis() {
		setTitle("N\u00EDveis");
		setIconImage(new ImageIcon(getClass().getResource("/images/BioGame_Icon.png")).getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 592, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPaneNiveis = new JScrollPane();
		scrollPaneNiveis.setBounds(10, 11, 240, 205);
		contentPane.add(scrollPaneNiveis);
		
		JList list = new JList();
		list.setVisibleRowCount(5);
		scrollPaneNiveis.setRowHeaderView(list);
		
		JButton btnNovoNivel = new JButton("Novo");
		btnNovoNivel.setBounds(10, 227, 240, 23);
		contentPane.add(btnNovoNivel);
		
		JLabel lblNvel = new JLabel("N\u00EDvel");
		lblNvel.setBounds(301, 11, 46, 14);
		contentPane.add(lblNvel);
		
		tfNivel = new JTextField();
		tfNivel.setBounds(301, 36, 265, 20);
		contentPane.add(tfNivel);
		tfNivel.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(365, 227, 89, 23);
		contentPane.add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(477, 227, 89, 23);
		contentPane.add(btnCancelar);
	}

}
