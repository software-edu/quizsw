package br.org.uesb.quizsw.view;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Janela_Escolha_Usuario extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela_Escolha_Usuario frame = new Janela_Escolha_Usuario();
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
	public Janela_Escolha_Usuario() {
		setIconImage(new ImageIcon(getClass().getResource("/resources/BioGame_Icon.png")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogo_Login = new JLabel("");
		lblLogo_Login.setIcon( new ImageIcon(getClass().getResource("/resources/BioGame_Logo1.png")));	
		lblLogo_Login.setBounds(83, 11, 284, 101);
		contentPane.add(lblLogo_Login);
		
		JButton btnProfessor = new JButton("Professor");
		
		//encaminha para a tela login
		btnProfessor.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent eventoLoginProfessor){
				Janela_Login LoginProfessor = new Janela_Login();
				LoginProfessor.setVisible(true);
			}
		});
		btnProfessor.setBounds(174, 161, 110, 23);
		contentPane.add(btnProfessor);
		
		JButton btnAluno = new JButton("Aluno");
		
		//encaminha para a tela login
		btnAluno.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent eventoLoginAluno){
				Janela_Login LoginAluno = new Janela_Login();
				LoginAluno.setVisible(true);
			}
		});
		btnAluno.setBounds(174, 206, 110, 23);
		contentPane.add(btnAluno);
	}

}
