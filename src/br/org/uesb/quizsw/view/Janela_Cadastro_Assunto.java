package br.org.uesb.quizsw.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import br.org.uesb.quizsw.control.Assunto;
import br.org.uesb.quizsw.control.AssuntoServices;
import br.org.uesb.quizsw.util.Result;

public class Janela_Cadastro_Assunto extends JFrame {

	private JPanel contentPane;
	
	private JScrollPane scrollPane = new JScrollPane();
	private JTree jtAssuntos = new JTree();
	private JList<String> jlAssuntos = new JList<String>();
	private JButton btnNovo = new JButton("Novo");
	private JLabel lblAssunto = new JLabel("Assunto");
	private JTextField tfAssunto = new JTextField();
	private JLabel lblAssuntoSuperior = new JLabel("Assunto Superior");
	private JComboBox<String> cbAssuntoSuperior = new JComboBox<>();
	private JButton btnSalvar = new JButton("Salvar");
	private JButton btnCancelar = new JButton("Cancelar");
	
	private int cdAssunto = 0;
	private ArrayList<HashMap<String, Object>> listAssunto;
	private ArrayList<HashMap<String, Object>> listAssuntoSuperior;
	
	AssuntoServices service = new AssuntoServices();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela_Cadastro_Assunto frame = new Janela_Cadastro_Assunto();
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
	public Janela_Cadastro_Assunto() {
		setTitle("Assunto");
		setIconImage(new ImageIcon(getClass().getResource("/images/BioGame_Icon.png")).getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 611, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane.setBounds(10, 11, 224, 222);
		contentPane.add(scrollPane);
		
		scrollPane.setViewportView(jlAssuntos);
		jlAssuntos.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int index = jlAssuntos.getSelectedIndex();
				
				if(!jlAssuntos.isSelectionEmpty()) {
					tfAssunto.setText(jlAssuntos.getSelectedValue());
					
					System.out.println((int)listAssunto.get(jlAssuntos.getSelectedIndex()).get("cd_assunto"));
					
					cbAssuntoSuperior.setSelectedIndex(
							getSuperiorIndex((int)listAssunto.get(jlAssuntos.getSelectedIndex()).get("cd_assunto"))
					);
				}
			}

			private int getSuperiorIndex(int cdAssunto) {
				int index = -1;
				for (int i=0; i<listAssuntoSuperior.size(); i++) {
					if((int)listAssuntoSuperior.get(i).get("cd_assunto")==cdAssunto) {
						index = i;
						break;
					}
				}
				return index;
			}
		});
		
		btnNovo.setBounds(10, 244, 224, 23);
		contentPane.add(btnNovo);
		
		lblAssunto.setBounds(281, 11, 85, 14);
		contentPane.add(lblAssunto);
		
		tfAssunto.setBounds(281, 36, 304, 20);
		contentPane.add(tfAssunto);
		tfAssunto.setColumns(10);
		
		lblAssuntoSuperior.setBounds(281, 108, 113, 14);
		contentPane.add(lblAssuntoSuperior);
		
		cbAssuntoSuperior.setBounds(281, 133, 304, 20);
		contentPane.add(cbAssuntoSuperior);
		
		btnSalvar.setBounds(277, 244, 89, 23);
		contentPane.add(btnSalvar);
		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSalvarOnClick(e);
			}
		});
		
		btnCancelar.setBounds(496, 244, 89, 23);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnCancelarOnClick(e);
				
			}
		});
		
		loadAssuntoSuperior();
		loadAssunto();
	}
	
	

	private void btnSalvarOnClick(ActionEvent e) {
		if(tfAssunto.getText().equals(null) || tfAssunto.getText().equals(""))
			return;
		
		int cdAssuntoSuperior = 0;
		if(cbAssuntoSuperior.getSelectedItem()!=null) {
			cdAssuntoSuperior = (int)listAssuntoSuperior.get(cbAssuntoSuperior.getSelectedIndex()).get("cd_assunto");
		}
		
		if(!jlAssuntos.isSelectionEmpty()) {
			cdAssunto = (int)listAssunto.get(jlAssuntos.getSelectedIndex()).get("cd_assunto");
		}
		
		Assunto assunto = new Assunto(cdAssunto, tfAssunto.getText().trim(), cdAssuntoSuperior);
		
		HashMap<String, Object> content = new HashMap<>();
		content.put("assunto", assunto);
		
		Result result = service.save(content);
		
		if(result.getCode()<=0) {
			JOptionPane.showMessageDialog(this, result.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(this, result.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE);
			
			loadAssuntoSuperior();
			loadAssunto();
		}
	}

	private void btnCancelarOnClick(ActionEvent e) {
		this.setVisible(false);
		this.dispose();
	}
	
	private void loadAssuntoSuperior() {
		listAssuntoSuperior = service.getAll();
		
		cbAssuntoSuperior.setModel(new DefaultComboBoxModel<String>());
		for (HashMap<String, Object> assunto : listAssuntoSuperior) {
			cbAssuntoSuperior.addItem((String)assunto.get("nm_assunto"));
		}
		
		cbAssuntoSuperior.setSelectedIndex(-1);
	}
	
	private void loadAssunto() {
		ArrayList<HashMap<String, Object>> listAssunto = service.getAll();
		
		if(listAssunto==null)
			return;
		
		DefaultListModel<String> jListModel = new DefaultListModel<>();
		for(int i=0; i<listAssunto.size(); i++) {
			jListModel.addElement((String)listAssunto.get(i).get("nm_assunto"));
		}
		
		jlAssuntos.setModel(jListModel);

	}
}
