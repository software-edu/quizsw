package br.org.uesb.quizsw.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import br.org.uesb.quizsw.util.Timer;

import javax.swing.JButton;

public class Janela_Quiz extends JFrame {

	private JPanel contentPane;
	private JLabel lbNmQuiz = new JLabel("");
	private JTextPane tpTxtPergunta = new JTextPane();
	private JPanel pnResposta = new JPanel();
	private JLabel lbNrPergunta = new JLabel("");
	private JButton btnOk = new JButton("OK");
	private ButtonGroup bgResposta = new ButtonGroup();
	private JRadioButton[] rbResposta;
	
	private HashMap<String, Object> quiz;
	private HashMap<String, Object> pergunta;
	private ArrayList<HashMap<String, Object>> respostas;
	private int index = 0;
	private int nrErros = 0;
	private int nrAcertos = 0;
	
	private Timer t;

	/**
	 * Create the frame.
	 */
	public Janela_Quiz(HashMap<String, Object> quiz) {
		this.quiz = quiz;
		
		setTitle("Quiz");
		setIconImage(new ImageIcon(getClass().getResource("/images/BioGame_Icon.png")).getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lbNmQuiz.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbNmQuiz.setBounds(10, 11, 564, 14);
		contentPane.add(lbNmQuiz);
		lbNmQuiz.setText((String)quiz.get("nm_quiz"));
		
		tpTxtPergunta.setBounds(31, 54, 543, 70);
		tpTxtPergunta.setEditable(false);
		contentPane.add(tpTxtPergunta);
		
		pnResposta.setBounds(31, 135, 543, 160);
		pnResposta.setLayout(new BoxLayout(pnResposta, BoxLayout.Y_AXIS));
		contentPane.add(pnResposta);
		
		lbNrPergunta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbNrPergunta.setBounds(10, 54, 18, 14);
		
		contentPane.add(lbNrPergunta);
		
		btnOk.setBounds(485, 306, 89, 23);
		contentPane.add(btnOk);
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnOkOnClick(e);
			}
		});
		
		loadPergunta();
		
		t = new Timer((int)quiz.get("qtd_tempo"), this);
		t.start();
	}
	

	private void loadPergunta() {
		if(index>=((ArrayList<HashMap<String,Object>>)quiz.get("perguntas")).size()) {
			endQuiz("Fim");
		}
		else if(nrErros>(int)quiz.get("qtd_erro")) {
			endQuiz("Limite de erros atingido");
		}
		else {
			pergunta = ((ArrayList<HashMap<String,Object>>)quiz.get("perguntas")).get(index);
			
			lbNrPergunta.setText(Integer.toString((int)pergunta.get("nr_ordem")));
			tpTxtPergunta.setText((String)pergunta.get("txt_pergunta"));
			
			respostas = (ArrayList<HashMap<String, Object>>) pergunta.get("respostas");
			
			rbResposta = new JRadioButton[respostas.size()];
			for (int i=0; i<respostas.size(); i++) {
				rbResposta[i] = new JRadioButton((String)respostas.get(i).get("txt_resposta"));
				
				bgResposta.add(rbResposta[i]);
				
				pnResposta.add(rbResposta[i]);
			}
		}
	}

	private void btnOkOnClick(ActionEvent e) {
		for (int i=0; i<rbResposta.length; i++) {
			if(rbResposta[i].isSelected()) {
				if((int)respostas.get(i).get("lg_correto")==1) {
					nrAcertos++;
					index++;
				}
				else {
					nrErros++;
					JOptionPane.showMessageDialog(this, "Tente novamente", "", JOptionPane.WARNING_MESSAGE);
				}
				clear();
				loadPergunta();
			}
		}
		
	}
	
	public void endQuiz(String message) {
		clear();
		JOptionPane.showMessageDialog(this, (message!=null?message+"\n" : "")+
				"Acertos: "+nrAcertos+"\nErros: "+nrErros);
		t.interrupt();
		
		this.setVisible(false);
		this.dispose();
	}
		
	private void clear() {
		lbNrPergunta.setText("");
		tpTxtPergunta.setText(null);
		pnResposta.removeAll();
		pnResposta.repaint();
	}
}
