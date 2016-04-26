package br.org.uesb.quizsw.view;

import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class Janela_Quiz extends JFrame {

	private JPanel contentPane;
	private JLabel lbNmQuiz = new JLabel("");
	private JTextPane tpTxtPergunta = new JTextPane();
	private JPanel pnResposta = new JPanel();
	
	private HashMap<String, Object> quiz;
	private int index = 0;
	private final JLabel lbNrPergunta = new JLabel("");

	/**
	 * Create the frame.
	 */
	public Janela_Quiz(HashMap<String, Object> quiz) {
		this.quiz = quiz;
		
		setTitle("Quiz");
		setIconImage(new ImageIcon(getClass().getResource("/images/BioGame_Icon.png")).getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lbNmQuiz.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbNmQuiz.setBounds(10, 11, 564, 14);
		contentPane.add(lbNmQuiz);
		lbNmQuiz.setText((String)quiz.get("nm_quiz"));
		
		tpTxtPergunta.setBounds(31, 54, 543, 70);
		contentPane.add(tpTxtPergunta);
		
		pnResposta.setBounds(31, 135, 543, 160);
		pnResposta.setLayout(new BoxLayout(pnResposta, BoxLayout.Y_AXIS));
		contentPane.add(pnResposta);
		
		lbNrPergunta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbNrPergunta.setBounds(10, 54, 18, 14);
		
		contentPane.add(lbNrPergunta);
		
		loadPergunta();
	}
	
	private void loadPergunta() {
		HashMap<String, Object> pergunta = ((ArrayList<HashMap<String,Object>>)quiz.get("perguntas")).get(index);
		
		lbNrPergunta.setText(Integer.toString((int)pergunta.get("nr_ordem")));
		tpTxtPergunta.setText((String)pergunta.get("txt_pergunta"));
		
		ArrayList<HashMap<String, Object>> respostas = (ArrayList<HashMap<String, Object>>) pergunta.get("respostas");
		
		ButtonGroup bgResposta = new ButtonGroup();
		JRadioButton[] rbResposta = new JRadioButton[respostas.size()];
		for (int i=0; i<respostas.size(); i++) {
			rbResposta[i] = new JRadioButton((String)respostas.get(i).get("txt_resposta"));
			
			bgResposta.add(rbResposta[i]);
			
			pnResposta.add(rbResposta[i]);
		}
		
	}
	
}
