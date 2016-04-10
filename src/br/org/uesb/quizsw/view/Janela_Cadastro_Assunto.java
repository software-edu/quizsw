package br.org.uesb.quizsw.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class Janela_Cadastro_Assunto extends JFrame {

	private JPanel contentPane;
	private JTextField tfAssunto;

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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 224, 222);
		contentPane.add(scrollPane);
		
		JTree jtAssuntos = new JTree();
		jtAssuntos.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("JTree") {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("Assunto1");
						node_1.add(new DefaultMutableTreeNode("Assunto1.1"));
						node_1.add(new DefaultMutableTreeNode("Assunto1.2"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Assunto2");
						node_1.add(new DefaultMutableTreeNode("Assunto2.1"));
						node_1.add(new DefaultMutableTreeNode("Assunto2.2"));
					add(node_1);
				}
			}
		));
		scrollPane.setViewportView(jtAssuntos);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.setBounds(10, 244, 224, 23);
		contentPane.add(btnNovo);
		
		JLabel lblAssunto = new JLabel("Assunto");
		lblAssunto.setBounds(281, 11, 85, 14);
		contentPane.add(lblAssunto);
		
		tfAssunto = new JTextField();
		tfAssunto.setBounds(281, 36, 304, 20);
		contentPane.add(tfAssunto);
		tfAssunto.setColumns(10);
		
		JLabel lblAssuntoSuperior = new JLabel("Assunto Superior");
		lblAssuntoSuperior.setBounds(281, 108, 113, 14);
		contentPane.add(lblAssuntoSuperior);
		
		JComboBox cbAssuntoSuperior = new JComboBox();
		cbAssuntoSuperior.setBounds(281, 133, 304, 20);
		contentPane.add(cbAssuntoSuperior);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(277, 244, 89, 23);
		contentPane.add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(496, 244, 89, 23);
		contentPane.add(btnCancelar);
	}
}
