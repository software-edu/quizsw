package br.org.uesb.quizsw.view;


import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.org.uesb.quizsw.control.Nivel;
import br.org.uesb.quizsw.control.NivelServices;
import br.org.uesb.quizsw.control.Quiz;
import br.org.uesb.quizsw.control.QuizServices;
import br.org.uesb.quizsw.util.Result;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Button;
//import java.awt.Dimension;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Janela_Cadastro_Quiz extends JFrame {

	private JPanel contentPane;
	private JTextField tfTitulo = new JTextField();;
	private JLabel lblErros;
	private JSpinner spinTempo = new JSpinner();
	private JSpinner spinErros = new JSpinner();
	private JList lfPerguntas = new JList(listadePerguntas);
	private Button btnSalvar = new Button("Salvar");
	private DefaultListModel listadePerguntas = new DefaultListModel();
	//ArrayList
	private ArrayList<String> arrayPerguntas = new	ArrayList();
	
	private int cdQuiz = 0;
	private int cdPergunta = 0;

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
		setBounds(100, 100, 526, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Titulo do Quiz");
		lblTitulo.setBounds(20, 11, 46, 14);
		contentPane.add(lblTitulo);
		
		
		tfTitulo.setBounds(20, 36, 404, 20);
		contentPane.add(tfTitulo);
		tfTitulo.setColumns(10);
		
		JLabel lblTempo = new JLabel("Tempo");
		lblTempo.setBounds(20, 86, 46, 14);
		contentPane.add(lblTempo);
		
		lblErros = new JLabel("Erros");
		lblErros.setBounds(115, 86, 46, 14);
		contentPane.add(lblErros);
				
		
		btnSalvar.setBounds(430, 319, 70, 22);
		contentPane.add(btnSalvar);
		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSalvarOnClick(e);
			}
		});
		
		Button btnAdicionarPerguntas = new Button("+");
		btnAdicionarPerguntas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Janela_Cadastro_Pergunta Pergunta = new Janela_Cadastro_Pergunta();
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
		});
		btnAdicionarPerguntas.setBounds(430, 190, 46, 22);
		contentPane.add(btnAdicionarPerguntas);
		
		JScrollPane spListaPerguntas = new JScrollPane();
		//spListaPerguntas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //mantem o scroll vertical sempre visivel 
		spListaPerguntas.setBounds(20, 190, 404, 119);
		contentPane.add(spListaPerguntas);
		
		
		spListaPerguntas.setViewportView(lfPerguntas);
		lfPerguntas.setVisibleRowCount(5);
		
		
		spinTempo.setModel(new SpinnerNumberModel(0, 0, 100, 1));//o spinner tem mínimo 0 e máximo 100
		spinTempo.setBounds(20, 111, 46, 31);
		contentPane.add(spinTempo);
		
		
		spinErros.setModel(new SpinnerNumberModel(0, 0, 5, 1)); //o spinner tem minimo 0 e maximo 5
		spinErros.setBounds(115, 111, 46, 31);
		contentPane.add(spinErros);
		
		JLabel lblPerguntas = new JLabel("Perguntas");
		lblPerguntas.setBounds(20, 165, 100, 14);
		contentPane.add(lblPerguntas);
		
		

	}
	
	private void btnSalvarOnClick(ActionEvent e) {
		cdQuiz = 0;
		cdPergunta = 0;
		
		if(!list.isSelectionEmpty()) {
			cdQuiz = (int)listQuiz.get(list.getSelectedIndex()).get("cd_quiz");
		}
		
		Quiz quiz = new Quiz(cdQuiz, tfTitulo.getText(),Integer.parseInt(spinTempo.getValue().toString()),Integer.parseInt(spinErros.getValue().toString()),"imagem");
		
		HashMap<String, Object> content = new HashMap<String, Object>();
		content.put("quiz", quiz);
		
		Result result = new QuizServices().save(content);
		
		if(result.getCode()<=0) {
			JOptionPane.showMessageDialog(this, result.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(this, result.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE);
			
			loadListQuiz();
		}
		
	}
	
	private ArrayList<HashMap<String, Object>> listQuiz = null;
	public void loadListQuiz() {
		listQuiz = new QuizServices().getAll();
		
		if(listQuiz==null)
			return;
		
		DefaultListModel<String> jListModel = new DefaultListModel<>();
		for(int i=0; i<listQuiz.size(); i++) {
			jListModel.addElement((String)listQuiz.get(i).get("nm_quiz"));
		}
		
		list.setModel(jListModel);
	}
}
