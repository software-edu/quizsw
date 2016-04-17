package br.org.uesb.quizsw.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.org.uesb.quizsw.control.AssuntoServices;
import br.org.uesb.quizsw.control.NivelServices;
import br.org.uesb.quizsw.control.Pergunta;
import br.org.uesb.quizsw.control.PerguntaServices;
import br.org.uesb.quizsw.control.Resposta;
import br.org.uesb.quizsw.util.Result;

public class Janela_Cadastro_Pergunta extends JFrame {

	private JPanel contentPane;
	private JTextField tfAlternativa1;
	private JTextField tfAlternativa2;
	private JTextField tfAlternativa3;
	private JTextField tfAlternativa4;

	private JTextArea taPerguntas = new JTextArea();
	//cria um ButtonGroup
	private ButtonGroup bgAlternativas = new ButtonGroup();
	private JRadioButton rdbtn1 = new JRadioButton("1");
	private JRadioButton rdbtn2 = new JRadioButton("2");
	private JRadioButton rdbtn3 = new JRadioButton("3");
	private JRadioButton rdbtn4 = new JRadioButton("4");
	private JComboBox<String> cbNivel = new JComboBox<>();
	private JComboBox<String> cbAssunto = new JComboBox<>();
	private JButton btnSalvar = new JButton("Salvar");
	private JButton btnCancelar = new JButton("Cancelar");
	
	public int cdPergunta = 0;

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
		
		taPerguntas.setWrapStyleWord(true);
		taPerguntas.setLineWrap(true);
		taPerguntas.setBounds(10, 36, 527, 85);
		contentPane.add(taPerguntas);
		
		rdbtn1.setBounds(10, 166, 41, 23);
		contentPane.add(rdbtn1);
		
		rdbtn2.setBounds(10, 192, 41, 23);
		contentPane.add(rdbtn2);
		
		rdbtn3.setBounds(10, 218, 41, 23);
		contentPane.add(rdbtn3);
		
		rdbtn4.setBounds(10, 244, 41, 23);
		contentPane.add(rdbtn4);
		
		//adiciona radiobuttons ao grupo
		bgAlternativas.add(rdbtn1);
		bgAlternativas.add(rdbtn2);
		bgAlternativas.add(rdbtn3);
		bgAlternativas.add(rdbtn4);
		
		btnSalvar.setBounds(336, 533, 89, 23);
		contentPane.add(btnSalvar);
		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSalvarOnClick(e);
				
			}
		});
		
		btnCancelar.setBounds(448, 533, 89, 23);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnCancelarOnClick(e);
				
			}
		});
		
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
		
		cbNivel.setBounds(396, 334, 141, 20);
		contentPane.add(cbNivel);
		
		JLabel lblAssunto = new JLabel("Assunto:");
		lblAssunto.setBounds(10, 299, 83, 14);
		contentPane.add(lblAssunto);
		
		cbAssunto.setBounds(10, 334, 283, 20);
		contentPane.add(cbAssunto);
		
		JLabel lblImagem = new JLabel("Imagem:");
		lblImagem.setBounds(10, 414, 68, 14);
		contentPane.add(lblImagem);
		
		loadAssunto();
		loadNivel();
	}
	
	private ArrayList<HashMap<String, Object>> listAssunto;
	private void loadAssunto() {
		listAssunto = new AssuntoServices().getAll();
		
		cbAssunto.setModel(new DefaultComboBoxModel<String>());
		for (HashMap<String, Object> assunto : listAssunto) {
			cbAssunto.addItem((String)assunto.get("nm_assunto"));
		}
	}
	
	private ArrayList<HashMap<String, Object>> listNivel;
	private void loadNivel(){
		listNivel = new NivelServices().getAll();
		
		cbNivel.setModel(new DefaultComboBoxModel<String>());
		for (HashMap<String, Object> nivel : listNivel) {
			cbNivel.addItem((String)nivel.get("nm_nivel"));
		}
	}

	private void btnSalvarOnClick(ActionEvent e) {
		if(taPerguntas.getText().trim().equals(""))
			return;
		
		if(bgAlternativas.getSelection()==null)
			return;
		
		if(tfAlternativa1.getText().trim().equals("") ||
				tfAlternativa2.getText().trim().equals("") ||
				tfAlternativa3.getText().trim().equals("") ||
				tfAlternativa4.getText().trim().equals(""))
			return;
		
		HashMap<String, Object> content = new HashMap<>();
		
		Pergunta pergunta = new Pergunta(cdPergunta, taPerguntas.getText().trim(), 
				(int)listAssunto.get(cbAssunto.getSelectedIndex()).get("cd_assunto"), 
				(int)listNivel.get(cbNivel.getSelectedIndex()).get("cd_nivel"));
		content.put("pergunta", pergunta);
		
		Resposta[] respostas = new Resposta[4];
		respostas[0] = new Resposta(1, cdPergunta, tfAlternativa1.getText().trim(), rdbtn1.isSelected()?1:0, null);
		respostas[1] = new Resposta(2, cdPergunta, tfAlternativa2.getText().trim(), rdbtn2.isSelected()?1:0, null);
		respostas[2] = new Resposta(3, cdPergunta, tfAlternativa3.getText().trim(), rdbtn3.isSelected()?1:0, null);
		respostas[3] = new Resposta(4, cdPergunta, tfAlternativa4.getText().trim(), rdbtn4.isSelected()?1:0, null);
		content.put("respostas", respostas);
		
		Result result = new PerguntaServices().save(content);
		if(result.getCode()<=0) {
			JOptionPane.showMessageDialog(this, result.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(this, result.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}

	private void btnCancelarOnClick(ActionEvent e) {
		this.setVisible(false);
		this.dispose();
	}
}
