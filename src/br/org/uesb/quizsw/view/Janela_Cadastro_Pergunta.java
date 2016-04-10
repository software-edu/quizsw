package br.org.uesb.quizsw.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class Janela_Cadastro_Pergunta extends JFrame {

	private JPanel contentPane;
	private JTextField tfAlternativa1;
	private JTextField tfAlternativa2;
	private JTextField tfAlternativa3;
	private JTextField tfAlternativa4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela_Cadastro_Pergunta frame = new Janela_Cadastro_Pergunta();
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
	public Janela_Cadastro_Pergunta() {
		setIconImage(new ImageIcon(getClass().getResource("/images/BioGame_Icon.png")).getImage());
		setTitle("Pergunta");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 567, 606);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPergunta = new JLabel("Pergunta:");
		lblPergunta.setBounds(10, 11, 83, 14);
		contentPane.add(lblPergunta);
		
		JTextArea taPerguntas = new JTextArea();
		taPerguntas.setWrapStyleWord(true);
		taPerguntas.setLineWrap(true);
		taPerguntas.setBounds(10, 36, 527, 85);
		contentPane.add(taPerguntas);
		
		JRadioButton rdbtn1 = new JRadioButton("1");
		rdbtn1.setBounds(10, 166, 41, 23);
		contentPane.add(rdbtn1);
		
		JRadioButton rdbtn2 = new JRadioButton("2");
		rdbtn2.setBounds(10, 192, 41, 23);
		contentPane.add(rdbtn2);
		
		JRadioButton rdbtn3 = new JRadioButton("3");
		rdbtn3.setBounds(10, 218, 41, 23);
		contentPane.add(rdbtn3);
		
		JRadioButton rdbtn4 = new JRadioButton("4");
		rdbtn4.setBounds(10, 244, 41, 23);
		contentPane.add(rdbtn4);
		
		//cria um ButtonGroup
		ButtonGroup bgAlternativas = new ButtonGroup();
		
		//adiciona radiobuttons ao grupo
		bgAlternativas.add(rdbtn1);
		bgAlternativas.add(rdbtn2);
		bgAlternativas.add(rdbtn3);
		bgAlternativas.add(rdbtn4);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(336, 533, 89, 23);
		contentPane.add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(448, 533, 89, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblAlternativas = new JLabel("Alternativas:");
		lblAlternativas.setBounds(10, 145, 117, 14);
		contentPane.add(lblAlternativas);
		
		tfAlternativa1 = new JTextField();
		tfAlternativa1.setBounds(57, 167, 480, 20);
		contentPane.add(tfAlternativa1);
		tfAlternativa1.setColumns(10);
		
		tfAlternativa2 = new JTextField();
		tfAlternativa2.setBounds(57, 193, 480, 20);
		contentPane.add(tfAlternativa2);
		tfAlternativa2.setColumns(10);
		
		tfAlternativa3 = new JTextField();
		tfAlternativa3.setBounds(57, 219, 480, 20);
		contentPane.add(tfAlternativa3);
		tfAlternativa3.setColumns(10);
		
		tfAlternativa4 = new JTextField();
		tfAlternativa4.setBounds(57, 245, 480, 20);
		contentPane.add(tfAlternativa4);
		tfAlternativa4.setColumns(10);
		
		JLabel lblNivel = new JLabel("Nivel:");
		lblNivel.setBounds(396, 299, 68, 14);
		contentPane.add(lblNivel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(396, 334, 141, 20);
		contentPane.add(comboBox);
		
		JLabel lblAssunto = new JLabel("Assunto:");
		lblAssunto.setBounds(10, 299, 83, 14);
		contentPane.add(lblAssunto);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(10, 334, 283, 20);
		contentPane.add(comboBox_2);
		
		JLabel lblImagem = new JLabel("Imagem:");
		lblImagem.setBounds(10, 414, 68, 14);
		contentPane.add(lblImagem);
	}
}
