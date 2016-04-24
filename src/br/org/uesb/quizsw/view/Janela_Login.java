package br.org.uesb.quizsw.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.org.uesb.quizsw.control.UsuarioServices;
import br.org.uesb.quizsw.util.Result;

public class Janela_Login extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JPasswordField passwordFieldSenha = new JPasswordField();
	private JLabel lblUsuario = new JLabel("USUARIO");
	private JLabel lblSenha = new JLabel("SENHA");
	private JButton btnConfirmar = new JButton("LOGIN");

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
		setBounds(100, 100, 325, 175);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(10, 25, 289, 20);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		textFieldUsuario.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					btnConfirmarOnClick(null);
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});		
		
		lblUsuario.setBounds(10, 11, 58, 14);
		contentPane.add(lblUsuario);
		
		
		lblSenha.setBounds(10, 56, 52, 14);
		contentPane.add(lblSenha);
		
		
		passwordFieldSenha.setBounds(10, 72, 289, 20);
		contentPane.add(passwordFieldSenha);
		passwordFieldSenha.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					btnConfirmarOnClick(null);
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});		
		
		
		btnConfirmar.setBounds(229, 103, 70, 23);
		contentPane.add(btnConfirmar);
		btnConfirmar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				btnConfirmarOnClick(e);
			}
		});
	}

	public void btnConfirmarOnClick(ActionEvent e){
		
		if(textFieldUsuario.getText()==null || textFieldUsuario.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Nenhum usuário digitado", "Alerta", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		Result result = new UsuarioServices().login(textFieldUsuario.getText(), new String(passwordFieldSenha.getPassword()));
		
		if(result.getCode()>0) {
			JOptionPane.showMessageDialog(this, result.getMessage(), "", JOptionPane.DEFAULT_OPTION);
			
			Janela_Inicio main = new Janela_Inicio();
			main.TP_PERMISSAO_LOGADA = (int)result.getObjects().get("tp_permissao");
			main.setVisible(true);
			
			this.setVisible(false);
			this.dispose();
		}
		else if(result.getCode()==-2) {
			JOptionPane.showMessageDialog(this, result.getMessage(), "Acesso Negado", JOptionPane.WARNING_MESSAGE);
			return;
		}
		else {
			JOptionPane.showMessageDialog(this, result.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			this.setVisible(false);
			this.dispose();
		}
	}

}
