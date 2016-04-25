package br.org.uesb.quizsw.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.org.uesb.quizsw.control.NivelServices;
import br.org.uesb.quizsw.control.Pergunta;
import br.org.uesb.quizsw.control.PerguntaServices;
import br.org.uesb.quizsw.util.Result;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JList;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Janela_BuscarPerguntas_Quiz extends JFrame {

	private JPanel contentPane;
	private JTextField tfPergunta;
	private JList listResultadoDasPerguntas;
	private Button btnUsarPergunta = new Button("Usar Pergunta");
	private Button btnBuscarPergunta = new Button("Buscar Pergunta");
	private Janela_Cadastro_Quiz parent;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela_BuscarPerguntas_Quiz frame = new Janela_BuscarPerguntas_Quiz(null);
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
	public Janela_BuscarPerguntas_Quiz(Janela_Cadastro_Quiz parent) {
		this.parent = parent;
		
		setIconImage(new ImageIcon(getClass().getResource("/images/BioGame_Icon.png")).getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 704, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPergunta = new JLabel("Pergunta");
		lblPergunta.setBounds(10, 11, 71, 14);
		contentPane.add(lblPergunta);
		
		tfPergunta = new JTextField();
		tfPergunta.setBounds(10, 34, 478, 20);
		contentPane.add(tfPergunta);
		tfPergunta.setColumns(10);
		
		JScrollPane spResultadosDasPerguntas = new JScrollPane();
		spResultadosDasPerguntas.setBounds(10, 99, 478, 311);
		contentPane.add(spResultadosDasPerguntas);
		
		listResultadoDasPerguntas = new JList();
		spResultadosDasPerguntas.setViewportView(listResultadoDasPerguntas);
		btnBuscarPergunta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//loadListBuscadePerguntas();
				
			}
		});
		
		
		btnBuscarPergunta.setBounds(548, 32, 104, 22);
		contentPane.add(btnBuscarPergunta);
		
		
		btnUsarPergunta.setBounds(558, 388, 94, 22);
		contentPane.add(btnUsarPergunta);
	}
	
	/*
	 * jList BuscarPerguntas
	 */
	private ArrayList<HashMap<String, Object>> listBuscarPerguntas = null;
	public void loadListBuscadePerguntas() {
		listBuscarPerguntas = new PerguntaServices().getAll();
		Pergunta pergunta = new Pergunta((int)listBuscarPerguntas.get(listResultadoDasPerguntas.getSelectedIndex()).get("cdPergunta"),(String)listBuscarPerguntas.get(listResultadoDasPerguntas.getSelectedIndex()).get("txtPergunta"), (int)listBuscarPerguntas.get(listResultadoDasPerguntas.getSelectedIndex()).get("cdAssunto"), (int)listBuscarPerguntas.get(listResultadoDasPerguntas.getSelectedIndex()).get("cdNivel"));

		
		//Nivel n = new Nivel((int)listNiveis.get(tabela.getSelectedIndex).get("cd_nivel"), (String)listNiveis.get(0).get("nm_nivel"));
		
		if(listBuscarPerguntas==null)
			return;
		
		DefaultListModel<String> jListModel = new DefaultListModel<>();
		for(int i=0; i<listBuscarPerguntas.size(); i++) {
			jListModel.addElement((String)listBuscarPerguntas.get(i).get("txtPergunta"));
		}
		
		listResultadoDasPerguntas.setModel(jListModel);
	}
	
	private void btnBuscarPergunta(ActionEvent e){
		String pergunta = tfPergunta.getText();
		
		ArrayList<HashMap<String,Object>> result = new PerguntaServices().find("AND A.txtPergunta LIKE '%"+pergunta+"%'");
		
	}
}
