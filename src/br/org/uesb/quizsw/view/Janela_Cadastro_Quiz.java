package br.org.uesb.quizsw.view;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import java.awt.Button;
//import java.awt.Dimension;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Janela_Cadastro_Quiz extends JFrame {

	private JPanel contentPane;
	private JTextField tfTitulo;
	private JLabel lblErros;
	private DefaultListModel listadePerguntas = new DefaultListModel();
	//ArrayList
	private ArrayList<String> arrayPerguntas = new	ArrayList();

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
		
		setIconImage(new ImageIcon(getClass().getResource("/images/BioGame_Icon.png")).getImage());
		setTitle("Quiz");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Titulo do Quiz");
		lblTitulo.setBounds(20, 11, 46, 14);
		contentPane.add(lblTitulo);
		
		tfTitulo = new JTextField();
		tfTitulo.setBounds(20, 32, 386, 20);
		contentPane.add(tfTitulo);
		tfTitulo.setColumns(10);
		
		JLabel lblTempo = new JLabel("Tempo");
		lblTempo.setBounds(413, 11, 46, 14);
		contentPane.add(lblTempo);
		
		lblErros = new JLabel("Erros");
		lblErros.setBounds(528, 11, 46, 14);
		contentPane.add(lblErros);
				
		Button btnSalvar = new Button("Salvar");
		btnSalvar.setBounds(504, 428, 70, 22);
		contentPane.add(btnSalvar);
		
		Button btnAdicionarPerguntas = new Button("+");
		btnAdicionarPerguntas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPerguntaOnClick(e);
			}
		});
		btnAdicionarPerguntas.setBounds(548, 82, 25, 25);
		contentPane.add(btnAdicionarPerguntas);
		
		JScrollPane spListaPerguntas = new JScrollPane();
		//spListaPerguntas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //mantem o scroll vertical sempre visivel 
		spListaPerguntas.setBounds(20, 82, 522, 340);
		contentPane.add(spListaPerguntas);
		
		JList lfPerguntas = new JList(listadePerguntas);
		spListaPerguntas.setViewportView(lfPerguntas);
		lfPerguntas.setVisibleRowCount(5);
		
		JSpinner spinTempo = new JSpinner();
		spinTempo.setModel(new SpinnerNumberModel(0, 0, 100, 1));//o spinner tem mínimo 0 e máximo 100
		spinTempo.setBounds(416, 32, 46, 20);
		contentPane.add(spinTempo);
		
		JSpinner spinErros = new JSpinner();
		spinErros.setModel(new SpinnerNumberModel(0, 0, 5, 1)); //o spinner tem minimo 0 e maximo 5
		spinErros.setBounds(528, 32, 46, 20);
		contentPane.add(spinErros);
		
		JLabel lblPerguntas = new JLabel("Perguntas");
		lblPerguntas.setBounds(20, 63, 100, 14);
		contentPane.add(lblPerguntas);
		
		JLabel lblMin = new JLabel("min.");
		lblMin.setBounds(472, 35, 46, 14);
		contentPane.add(lblMin);

	}
	

	private void addPerguntaOnClick(ActionEvent e) {
		Janela_Cadastro_Pergunta Pergunta = new Janela_Cadastro_Pergunta(this);
		Pergunta.setVisible(true);
		/**
		 * adiciona elementos ao jList
		 * 
		 * listadePerguntas.removeAllElements();
		arrayPerguntas.add(tfTitulo.getText());
		tfTitulo.setText("");
		for(String tempTitulo:arrayPerguntas){
			listadePerguntas.addElement(tempTitulo);
		}
		 */
		
		
		
	}
}
