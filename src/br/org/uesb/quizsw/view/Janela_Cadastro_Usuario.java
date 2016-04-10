package br.org.uesb.quizsw.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

public class Janela_Cadastro_Usuario extends JFrame {

	private JPanel contentPane;
	private JTextField tfLogin;
	private JPasswordField pfSenha;
	private JPasswordField pfRepetirSenha;
	
	
	/**
	 * Launch the application.
	 */
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
		
		JScrollPane scrollPaneListaUsuarios = new JScrollPane();
		scrollPaneListaUsuarios.setBounds(23, 53, 172, 291);
		contentPane.add(scrollPaneListaUsuarios);
		
		JList listUsuario = new JList();
		scrollPaneListaUsuarios.setRowHeaderView(listUsuario);
		
		JButton btnNovoUsuario = new JButton("Novo Usuário");
		btnNovoUsuario.setBounds(23, 355, 172, 23);
		contentPane.add(btnNovoUsuario);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(262, 53, 46, 14);
		contentPane.add(lblLogin);
		
		tfLogin = new JTextField();
		tfLogin.setBounds(262, 78, 275, 20);
		contentPane.add(tfLogin);
		tfLogin.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(262, 128, 46, 14);
		contentPane.add(lblSenha);
		
		JLabel lblRepetirSenha = new JLabel("Repetir Senha");
		lblRepetirSenha.setBounds(262, 208, 108, 14);
		contentPane.add(lblRepetirSenha);
		
		pfSenha = new JPasswordField();
		pfSenha.setBounds(262, 153, 275, 20);
		contentPane.add(pfSenha);
		
		pfRepetirSenha = new JPasswordField();
		pfRepetirSenha.setBounds(262, 233, 275, 20);
		contentPane.add(pfRepetirSenha);
		
		JLabel lblPermissao = new JLabel("Permissão");
		lblPermissao.setBounds(262, 282, 78, 14);
		contentPane.add(lblPermissao);
		
		JRadioButton rdbtnAdministrador = new JRadioButton("Administrador");
		rdbtnAdministrador.setBounds(262, 321, 109, 23);
		contentPane.add(rdbtnAdministrador);
		
		JRadioButton rdbtnOperador = new JRadioButton("Operador");
		rdbtnOperador.setBounds(428, 321, 109, 23);
		contentPane.add(rdbtnOperador);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(262, 385, 89, 23);
		contentPane.add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(448, 385, 89, 23);
		contentPane.add(btnCancelar);
		
		//cria um ButtonGroup
		ButtonGroup bgPermissoes = new ButtonGroup();
		
		//adiciona radiobuttons ao grupo
		bgPermissoes.add(rdbtnOperador);
		bgPermissoes.add(rdbtnAdministrador);
	}
}
