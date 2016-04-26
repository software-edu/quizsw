package br.org.uesb.quizsw.view;

import java.awt.Button;
//import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import br.org.uesb.quizsw.control.Pergunta;
import br.org.uesb.quizsw.control.Quiz;
import br.org.uesb.quizsw.control.QuizServices;
import br.org.uesb.quizsw.util.Result;

public class Janela_Cadastro_Quiz extends JFrame {

	private JPanel contentPane;
	private JTextField tfTitulo = new JTextField();
	private JLabel lblErros = new JLabel("Erros");
	private DefaultListModel listadePerguntas = new DefaultListModel();
	//ArrayList
	private ArrayList<String> arrayPerguntas = new	ArrayList();
	private JLabel lblTitulo = new JLabel("Titulo do Quiz");
	private JLabel lblTempo = new JLabel("Tempo");		
	private Button btnSalvar = new Button("Salvar");
	private Button btnAdicionarPerguntas = new Button("Adicionar Nova Pergunta");
	private Button btnBuscarPerguntaExistente = new Button("Buscar Pergunta Existente");
	private JScrollPane spListaPerguntas = new JScrollPane();
	private JList lfPerguntas = new JList(listadePerguntas);
	private JSpinner spinTempo = new JSpinner();
	private JSpinner spinErros = new JSpinner();
	private JLabel lblPerguntas = new JLabel("Perguntas");
	private JLabel lblMin = new JLabel("min.");

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
		setBounds(100, 100, 741, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitulo.setBounds(20, 11, 46, 14);
		contentPane.add(lblTitulo);
		
		tfTitulo.setBounds(20, 32, 522, 20);
		contentPane.add(tfTitulo);
		tfTitulo.setColumns(10);
		
		lblTempo.setBounds(554, 11, 46, 14);
		contentPane.add(lblTempo);
		
		lblErros.setBounds(666, 11, 46, 14);
		contentPane.add(lblErros);
		
		btnSalvar.setBounds(615, 428, 100, 22);
		contentPane.add(btnSalvar);
		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSalvarOnClick(e);
				
			}
		});
		
		btnAdicionarPerguntas.setBounds(548, 82, 167, 25);
		contentPane.add(btnAdicionarPerguntas);
		btnAdicionarPerguntas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPerguntaOnClick(e);
			}
		});
		
		spListaPerguntas.setBounds(20, 82, 522, 340);
		contentPane.add(spListaPerguntas);
		
		spListaPerguntas.setViewportView(lfPerguntas);
		lfPerguntas.setVisibleRowCount(5);
		
		spinTempo.setModel(new SpinnerNumberModel(0, 0, 100, 1));//o spinner tem mínimo 0 e máximo 100
		spinTempo.setBounds(554, 32, 46, 20);
		contentPane.add(spinTempo);
		
		spinErros.setModel(new SpinnerNumberModel(0, 0, 5, 1)); //o spinner tem minimo 0 e maximo 5
		spinErros.setBounds(666, 32, 46, 20);
		contentPane.add(spinErros);
		
		lblPerguntas.setBounds(20, 63, 100, 14);
		contentPane.add(lblPerguntas);
		
		lblMin.setBounds(610, 35, 46, 14);
		contentPane.add(lblMin);
		
		
		btnBuscarPerguntaExistente.setBounds(548, 119, 164, 23);
		contentPane.add(btnBuscarPerguntaExistente);
		//encaminha para a tela de busca de perguntas existentes em outros quiz
		btnBuscarPerguntaExistente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarPerguntaOnClick(e);
			}
		});

	}
	
	private void buscarPerguntaOnClick(ActionEvent e) {
		Janela_BuscarPerguntas_Quiz Busca = new Janela_BuscarPerguntas_Quiz(this);
		Busca.setVisible(true);
	}
	
	private void addPerguntaOnClick(ActionEvent e) {
		Janela_Cadastro_Pergunta Pergunta = new Janela_Cadastro_Pergunta(this);
		Pergunta.setVisible(true);
	}

	private ArrayList<Pergunta> listPergunta = new ArrayList<>();	
	protected void addPerguntaCallback(Pergunta pergunta) {
		listPergunta.add(pergunta);
		
		DefaultListModel<String> jListModel = new DefaultListModel<>();
		for(int i=0; i<listPergunta.size(); i++) {
			jListModel.addElement((String)listPergunta.get(i).getTxtPergunta());
		}
		
		lfPerguntas.setModel(jListModel);
	}

	private void btnSalvarOnClick(ActionEvent e) {
		HashMap<String, Object> content = new HashMap<>();
		
		Quiz quiz = new Quiz(0, tfTitulo.getText().trim(), (int)spinTempo.getValue(), (int)spinErros.getValue(), null);
		
		content.put("quiz", quiz);
		content.put("perguntas", listPergunta);
		
		Result result = new QuizServices().save(content);
		
		if(result.getCode()<=0) {
			JOptionPane.showMessageDialog(this, result.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(this, result.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
}
