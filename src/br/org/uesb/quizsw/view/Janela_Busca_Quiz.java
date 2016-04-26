package br.org.uesb.quizsw.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.org.uesb.quizsw.control.QuizServices;

public class Janela_Busca_Quiz extends JFrame {

	private JPanel contentPane;
	private JTextField tfNmQuiz;
	private JButton btnBuscar;
	private JTable jtQuiz;
	private Object[] columns = {"Quiz"};
	private DefaultTableModel dtm = new DefaultTableModel(columns, 0);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela_Busca_Quiz frame = new Janela_Busca_Quiz();
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
	public Janela_Busca_Quiz() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbNmQuiz = new JLabel("Quiz");
		lbNmQuiz.setBounds(10, 11, 46, 14);
		contentPane.add(lbNmQuiz);
		
		tfNmQuiz = new JTextField();
		tfNmQuiz.setBounds(10, 27, 460, 24);
		contentPane.add(tfNmQuiz);
		tfNmQuiz.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(485, 28, 89, 23);
		contentPane.add(btnBuscar);
		
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnBuscarOnClick(e);
				
			}
		});
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 73, 564, 221);
		contentPane.add(scrollPane);
		
		jtQuiz = new JTable();
		scrollPane.setViewportView(jtQuiz);
		jtQuiz.setModel(dtm);
		
	}

	private ArrayList<HashMap<String, Object>> lstQuiz = null;
	private JScrollPane scrollPane;;
	private void btnBuscarOnClick(ActionEvent e) {
		clearTable();
		
		lstQuiz = new QuizServices().find(" AND nm_quiz LIKE '%"+tfNmQuiz.getText().trim()+"%'");
		
		for (HashMap<String, Object> reg : lstQuiz) {
			Object[] row = {reg.get("nm_quiz")};
			dtm.addRow(row);
		}
		
		jtQuiz.setModel(dtm);
	}
	
	private void clearTable() {
		if(lstQuiz!=null) {
			for(int i=lstQuiz.size()-1; i>=0; i--) {
				dtm.removeRow(i);
			}
			lstQuiz = null;
		}
	}
}
