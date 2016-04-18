package br.org.uesb.quizsw.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.istack.internal.logging.Logger;

import br.org.uesb.quizsw.util.Conexao;
import br.org.uesb.quizsw.util.Result;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
//import java.util.logging.Logger;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Janela_Login extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JPasswordField passwordFieldSenha = new JPasswordField();
	private JLabel lblUsuario = new JLabel("USUARIO");
	private JLabel lblSenha = new JLabel("SENHA");
	private JButton btnConfirmar = new JButton("CONFIRMAR");
	private Conexao conec = new Conexao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela_Login frame = new Janela_Login();
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
	public Janela_Login() {
		setIconImage(new ImageIcon(getClass().getResource("/images/BioGame_Icon.png")).getImage());
		setTitle("BIO GAME - Perfil");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(130, 78, 190, 20);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		
		lblUsuario.setBounds(198, 53, 58, 14);
		contentPane.add(lblUsuario);
		
		
		lblSenha.setBounds(204, 138, 52, 14);
		contentPane.add(lblSenha);
		
		
		passwordFieldSenha.setBounds(130, 163, 190, 20);
		contentPane.add(passwordFieldSenha);
		
		
		btnConfirmar.setBounds(173, 211, 106, 23);
		contentPane.add(btnConfirmar);
		btnConfirmar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					btnConfirmarOnClick(e);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
	
	
	}

public void btnConfirmarOnClick(ActionEvent e) throws SQLException{
	conec.getConnection();
	String SQL = "SELECT * from Usuario";
	//TEM UNS TRENS PARA SEREM FEITOS NESSE ESPAÇO MAS NÃO ENTENDI AINDA COMO FAZ
	
	if(textFieldUsuario.getText().equals("conec.resultset.getString(nm_login)") && passwordFieldSenha.getPassword().toString().equals("conec.resultset.getString(nm_senha)")){
		JOptionPane.showMessageDialog(null, "Logado");
		Janela_Inicio janelaInicio = new Janela_Inicio();
		janelaInicio.setVisible(true);
	}
	else{
		JOptionPane.showMessageDialog(null, "Login ou Senha INCORRETOS! Verifique seus dados!");
	}
}

}
