package br.org.uesb.quizsw.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.org.uesb.quizsw.control.Usuario;
import br.org.uesb.quizsw.control.UsuarioServices;
import br.org.uesb.quizsw.util.Result;

public class Janela_Cadastro_Usuario extends JFrame {

	private JPanel contentPane;
	private JTextField tfLogin = new JTextField();
	private JPasswordField pfSenha = new JPasswordField();
	private JPasswordField pfRepetirSenha = new JPasswordField();
	private JList<String> listUsuario = new JList<String>();
	private JScrollPane scrollPaneListaUsuarios = new JScrollPane();
	private JButton btnSalvar = new JButton("Salvar");
	private JButton btnCancelar = new JButton("Cancelar");
	private JLabel lblLogin = new JLabel("Login");
	private JLabel lblSenha = new JLabel("Senha");
	private JLabel lblRepetirSenha = new JLabel("Repetir Senha");
	private JLabel lblPermissao = new JLabel("Permissão");
	private JButton btnNovoUsuario = new JButton("Novo Usuário");
	private JRadioButton rdbtnAdministrador = new JRadioButton("Administrador");
	private JRadioButton rdbtnOperador = new JRadioButton("Operador");
	private ButtonGroup bgPermissoes = new ButtonGroup(); //cria um ButtonGroup
	
	private int cdUsuario = 0;
	
	/**
	 * Launch the application.
	 */
	 //Teste
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela_Cadastro_Usuario frame = new Janela_Cadastro_Usuario();
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
	public Janela_Cadastro_Usuario() {
		setIconImage(new ImageIcon(getClass().getResource("/images/BioGame_Icon.png")).getImage());
		setTitle("Usuários");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 577, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		scrollPaneListaUsuarios.setBounds(23, 53, 172, 291);
		contentPane.add(scrollPaneListaUsuarios);
		
		listUsuario.setVisibleRowCount(5);
		scrollPaneListaUsuarios.setRowHeaderView(listUsuario);
		listUsuario.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int index = listUsuario.getSelectedIndex();
				
				if(!listUsuario.isSelectionEmpty()) {
					tfLogin.setText(listUsuario.getSelectedValue());
					pfSenha.setText((String)listUsuarios.get(index).get("nm_senha"));
					pfRepetirSenha.setText((String)listUsuarios.get(index).get("nm_senha"));
					
					rdbtnAdministrador.setSelected((int)listUsuarios.get(index).get("tp_permissao")==UsuarioServices.TP_PERMISSAO_ADMINISTRADOR);
					rdbtnOperador.setSelected((int)listUsuarios.get(index).get("tp_permissao")==UsuarioServices.TP_PERMISSAO_OPERADOR);
				}
			}
		});
		
		
		btnNovoUsuario.setBounds(23, 355, 172, 23);
		contentPane.add(btnNovoUsuario);
		btnNovoUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cdUsuario = 0;
				tfLogin.setText(null);
				pfSenha.setText(null);
				pfRepetirSenha.setText(null);
				
				bgPermissoes.clearSelection();
				
				listUsuario.clearSelection();
			}
		});
		
		
		
		lblLogin.setBounds(262, 53, 46, 14);
		contentPane.add(lblLogin);
		
		
		tfLogin.setBounds(262, 78, 275, 20);
		contentPane.add(tfLogin);
		tfLogin.setColumns(10);
		
		
		lblSenha.setBounds(262, 128, 46, 14);
		contentPane.add(lblSenha);
		
		
		lblRepetirSenha.setBounds(262, 208, 108, 14);
		contentPane.add(lblRepetirSenha);
		
		
		pfSenha.setBounds(262, 153, 275, 20);
		contentPane.add(pfSenha);
		
		
		pfRepetirSenha.setBounds(262, 233, 275, 20);
		contentPane.add(pfRepetirSenha);
		
		
		lblPermissao.setBounds(262, 282, 78, 14);
		contentPane.add(lblPermissao);
		
		
		
		rdbtnAdministrador.setBounds(262, 321, 109, 23);
		contentPane.add(rdbtnAdministrador);
		
		
		rdbtnOperador.setBounds(428, 321, 109, 23);
		contentPane.add(rdbtnOperador);
		
		
		btnSalvar.setBounds(262, 385, 89, 23);
		contentPane.add(btnSalvar);
		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSalvarOnClick(e);
			}
		});
		
		
		btnCancelar.setBounds(448, 385, 89, 23);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnCancelarOnClick(e);
			}
		});
		
		
		
		//adiciona radiobuttons ao grupo
		bgPermissoes.add(rdbtnOperador);
		bgPermissoes.add(rdbtnAdministrador);
		
		loadListUsuarios();
	}
	
	private void btnSalvarOnClick(ActionEvent e) {
		cdUsuario = 0;
		
		if(!new String(pfSenha.getPassword()).equals(new String(pfRepetirSenha.getPassword()))) {
			JOptionPane.showMessageDialog(this, "Senhas diferentes. Por favor reinsira sua senha!", "", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(!listUsuario.isSelectionEmpty()) {
			cdUsuario = (int)listUsuarios.get(listUsuario.getSelectedIndex()).get("cd_usuario");
		}
		
		Usuario usuario = new Usuario(cdUsuario, tfLogin.getText(), String.valueOf(pfSenha.getPassword()),verificaRadioButton(bgPermissoes));
		HashMap<String, Object> content = new HashMap<String, Object>();
		content.put("usuario", usuario);
		
		Result result = new UsuarioServices().save(content);
		
		if(result.getCode()<=0) {
			JOptionPane.showMessageDialog(this, result.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(this, result.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE);
			
			loadListUsuarios();
		}
	}
	
	private void btnCancelarOnClick(ActionEvent e) {
		this.setVisible(false);
		this.dispose();
	}
	
	private ArrayList<HashMap<String, Object>> listUsuarios = null;
	public void loadListUsuarios() {
		listUsuarios = new UsuarioServices().getAll();
		
		if(listUsuarios==null)
			return;
		
		DefaultListModel<String> jListModel = new DefaultListModel<>();
		for(int i=0; i<listUsuarios.size(); i++) {
			jListModel.addElement((String)listUsuarios.get(i).get("nm_login"));
		}
		
		listUsuario.setModel(jListModel);
	}
	
	private int verificaRadioButton(ButtonGroup bgPermissoes) {
		return rdbtnAdministrador.isSelected() ? 0 : 1;
	}
}
