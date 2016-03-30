package br.org.uesb.quizsw.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.Button;

public class Janela_Cadastro_Quiz extends JFrame {

	private JPanel contentPane;
	private JTextField tfTitulo;
	private JLabel lblErros;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela_Cadastro_Quiz frame = new Janela_Cadastro_Quiz();
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
	public Janela_Cadastro_Quiz() {
		
		setIconImage(new ImageIcon(getClass().getResource("/resources/BioGame_Icon.png")).getImage());
		setTitle("Cadastro - Quiz");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 462, 326);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(20, 11, 46, 14);
		contentPane.add(lblTitulo);
		
		tfTitulo = new JTextField();
		tfTitulo.setBounds(20, 36, 404, 20);
		contentPane.add(tfTitulo);
		tfTitulo.setColumns(10);
		
		JLabel lblTempo = new JLabel("Tempo");
		lblTempo.setBounds(20, 80, 46, 14);
		contentPane.add(lblTempo);
		
		lblErros = new JLabel("Erros");
		lblErros.setBounds(116, 80, 46, 14);
		contentPane.add(lblErros);
		
		JList listPerguntas = new JList();
		listPerguntas.setBounds(20, 226, 351, -92);
		contentPane.add(listPerguntas);
		
		Button btnSalvar = new Button("Salvar");
		btnSalvar.setBounds(366, 246, 70, 22);
		contentPane.add(btnSalvar);
		
		Button btnAdicionarPerguntas = new Button("+");
		btnAdicionarPerguntas.setBounds(401, 133, 20, 22);
		contentPane.add(btnAdicionarPerguntas);
	}
}
