package br.org.uesb.quizsw.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
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
	private DefaultTableModel dtm;

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
		setTitle("Busca de Quiz");
		setIconImage(new ImageIcon(getClass().getResource("/images/BioGame_Icon.png")).getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbNmQuiz = new JLabel("Quiz");
		lbNmQuiz.setBounds(10, 11, 46, 14);
		contentPane.add(lbNmQuiz);
		
		tfNmQuiz = new JTextField();
		tfNmQuiz.setBounds(10, 27, 465, 24);
		contentPane.add(tfNmQuiz);
		tfNmQuiz.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(485, 27, 89, 24);
		contentPane.add(btnBuscar);
		
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnBuscarOnClick(e);
				
			}
		});
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 73, 564, 243);
		contentPane.add(scrollPane);
		
		dtm = new DefaultTableModel(columns, 0);
		
		jtQuiz = new JTable();
		scrollPane.setViewportView(jtQuiz);
		jtQuiz.setModel(dtm);
		jtQuiz.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(jtQuiz.getSelectedRow()>-1) 
					btnAbrir.setEnabled(true);
			}
		});
		
		btnAbrir = new JButton("Abrir");
		btnAbrir.setBounds(485, 327, 89, 23);
		contentPane.add(btnAbrir);
		btnAbrir.setEnabled(false);
		btnAbrir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnAbrirOnClick(e);
				
			}
		});
		
	}

	private ArrayList<HashMap<String, Object>> lstQuiz = null;
	private JScrollPane scrollPane;
	private JButton btnAbrir;;
	private void btnBuscarOnClick(ActionEvent e) {
		clearTable();
		
		lstQuiz = new QuizServices().find(" AND nm_quiz LIKE '%"+tfNmQuiz.getText().trim()+"%'");
		
		for (HashMap<String, Object> reg : lstQuiz) {
			Object[] row = {reg.get("nm_quiz")};
			dtm.addRow(row);
		}
		
		jtQuiz.setModel(dtm);
	}

	private void btnAbrirOnClick(ActionEvent e) {
		Janela_Quiz quiz = new Janela_Quiz(lstQuiz.get(jtQuiz.getSelectedRow()));
		quiz.setVisible(true);
		
	}
	
	private void clearTable() {
		if(lstQuiz!=null) {
			for(int i=lstQuiz.size()-1; i>=0; i--) {
				dtm.removeRow(i);
			}
			lstQuiz = null;
			
			btnAbrir.setEnabled(false);
		}
	}
}
