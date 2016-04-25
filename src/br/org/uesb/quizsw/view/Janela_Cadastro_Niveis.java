package br.org.uesb.quizsw.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.org.uesb.quizsw.control.Nivel;
import br.org.uesb.quizsw.control.NivelServices;
import br.org.uesb.quizsw.util.Result;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Janela_Cadastro_Niveis extends JFrame {

	private JPanel contentPane;
	
	private JScrollPane scrollPaneNiveis = new JScrollPane();
	private JList<String> list = new JList<String>();
	private JButton btnNovoNivel = new JButton("Novo");
	private JLabel lblNvel = new JLabel("N\u00EDvel");
	private JTextField tfNivel = new JTextField();
	private JButton btnSalvar = new JButton("Salvar");
	private JButton btnCancelar = new JButton("Cancelar");
	
	private int cdNivel = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela_Cadastro_Niveis frame = new Janela_Cadastro_Niveis();
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
	public Janela_Cadastro_Niveis() {
		setTitle("N\u00EDveis");
		setIconImage(new ImageIcon(getClass().getResource("/images/BioGame_Icon.png")).getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 592, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPaneNiveis.setBounds(10, 11, 240, 205);
		contentPane.add(scrollPaneNiveis);
		
		list.setVisibleRowCount(5);
		scrollPaneNiveis.setRowHeaderView(list);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				tfNivel.setText(list.getSelectedValue());
				
			}
		});
		
		btnNovoNivel.setBounds(10, 227, 240, 23);
		contentPane.add(btnNovoNivel);
		btnNovoNivel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cdNivel = 0;
				tfNivel.setText(null);
				
				list.clearSelection();
			}
		});
		
		lblNvel.setBounds(301, 11, 46, 14);
		contentPane.add(lblNvel);
		
		tfNivel.setBounds(301, 36, 265, 20);
		contentPane.add(tfNivel);
		tfNivel.setColumns(10);
		
		btnSalvar.setBounds(365, 227, 89, 23);
		contentPane.add(btnSalvar);
		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSalvarOnClick(e);
			}
		});
		
		btnCancelar.setBounds(477, 227, 89, 23);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnCancelarOnClick(e);
			}
		});
		
		
		/*
		 * LOADS
		 */
		loadListNivel();
	}
	
	private void btnSalvarOnClick(ActionEvent e) {
		cdNivel = 0;
		
		if(!list.isSelectionEmpty()) {
			cdNivel = (int)listNiveis.get(list.getSelectedIndex()).get("cd_nivel");
		}
		
		Nivel nivel = new Nivel(cdNivel, tfNivel.getText());
		HashMap<String, Object> content = new HashMap<String, Object>();
		content.put("nivel", nivel);
		
		Result result = new NivelServices().save(content);
		
		if(result.getCode()<=0) {
			JOptionPane.showMessageDialog(this, result.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(this, result.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE);
			
			loadListNivel();
		}
		
	}
	
	private void btnCancelarOnClick(ActionEvent e) {
		this.setVisible(false);
		this.dispose();
	}
	
	/*
	 * jList Nivel
	 */
	private ArrayList<HashMap<String, Object>> listNiveis = null;
	public void loadListNivel() {
		listNiveis = new NivelServices().getAll();
		
		//Nivel n = new Nivel((int)listNiveis.get(tabela.getSelectedIndex).get("cd_nivel"), (String)listNiveis.get(0).get("nm_nivel"));
		
		if(listNiveis==null)
			return;
		
		DefaultListModel<String> jListModel = new DefaultListModel<>();
		for(int i=0; i<listNiveis.size(); i++) {
			jListModel.addElement((String)listNiveis.get(i).get("nm_nivel"));
		}
		
		list.setModel(jListModel);
	}

}
