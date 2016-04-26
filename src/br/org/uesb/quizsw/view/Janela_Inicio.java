package br.org.uesb.quizsw.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.org.uesb.quizsw.control.UsuarioServices;

public class Janela_Inicio extends JFrame {

	private JPanel contentPane;
	private int tpPermissao;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela_Inicio frame = new Janela_Inicio(UsuarioServices.TP_PERMISSAO_ADMINISTRADOR);
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
	public Janela_Inicio(int tpPermissao) {
		
		this.tpPermissao = tpPermissao;
		
		setResizable(false);
		setUndecorated(true);//retira barra de titulos
		setIconImage(new ImageIcon(getClass().getResource("/images/BioGame_Icon.png")).getImage());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 575, 417);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("MENU");
		menuBar.add(mnMenu);
		
		JMenuItem mntmSairDoJogo = new JMenuItem("Sair do Jogo");
		
		//sai do jogo
		mntmSairDoJogo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evento){
				System.exit(0);
			}
		});
				
		mnMenu.add(mntmSairDoJogo);
		
		JMenu mnQuiz = new JMenu("QUIZ");
		menuBar.add(mnQuiz);
		
		JMenuItem mntmNovo = new JMenuItem("Novo");
		//encaminha para a tela de cadastro de novo quiz
		mntmNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Janela_Cadastro_Quiz cQuiz = new Janela_Cadastro_Quiz();
				cQuiz.setVisible(true);
			}
		});
		mnQuiz.add(mntmNovo);
		mntmNovo.setVisible(tpPermissao==UsuarioServices.TP_PERMISSAO_ADMINISTRADOR);
		
		JMenuItem mntmBuscar = new JMenuItem("Buscar");
		mnQuiz.add(mntmBuscar);
		mntmBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Janela_Busca_Quiz jbq = new  Janela_Busca_Quiz();
				jbq.setVisible(true);
			}
		});
		
		//menu de cadastros
		JMenu mnCadastros = new JMenu("CADASTROS");
		menuBar.add(mnCadastros);
		mnCadastros.setVisible(tpPermissao==UsuarioServices.TP_PERMISSAO_ADMINISTRADOR);
		
		//cadastro de assuntos
		//encaminha para a tela Cadastro de Assuntos
		JMenuItem mntmAssunto = new JMenuItem("Assunto");
		mntmAssunto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Janela_Cadastro_Assunto assunto = new Janela_Cadastro_Assunto();
				assunto.setVisible(true);
			}
		});		
		mnCadastros.add(mntmAssunto);
		
		//cadastro de niveis
		//encaminha para a tela de Cadastro de Niveis
		JMenuItem mntmNvel = new JMenuItem("Nível");
		mntmNvel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Janela_Cadastro_Niveis Niveis = new Janela_Cadastro_Niveis();
				Niveis.setVisible(true);
			}
		});
		mnCadastros.add(mntmNvel);
		
		//cadastro de perguntas
		JMenuItem mntmPergunta = new JMenuItem("Pergunta");
		mntmPergunta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Janela_Cadastro_Pergunta Perguntas = new Janela_Cadastro_Pergunta(null);
				Perguntas.setVisible(true);
			}
		});
		mnCadastros.add(mntmPergunta);
		
		//cadastro de usuarios
		//encaminha para a tela de cadastro de usuarios
		JMenuItem mntmUsurio = new JMenuItem("Usuário");
		mntmUsurio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Janela_Cadastro_Usuario Usuario = new Janela_Cadastro_Usuario();
				Usuario.setVisible(true);
			}
		});
		mnCadastros.add(mntmUsurio);
		
		//menu Ajuda
		JMenu mnAjuda = new JMenu("AJUDA");
		menuBar.add(mnAjuda);
		
		//sub-menu Sobre
		JMenuItem mntmSobreOJogo = new JMenuItem("Sobre o Jogo");
		mnAjuda.add(mntmSobreOJogo);
		
		//encaminha para a tela sobre
		mntmSobreOJogo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent eventoSobre){
				Janela_Sobre Sobre = new Janela_Sobre();
				Sobre.setVisible(true);
			}
		});
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnIniciar = new JButton("INICIAR");
		
		//encaminha para a tela do quiz
		btnIniciar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent eventoIniciaQuiz){
				Bio_Quiz Quiz = new Bio_Quiz();
				Quiz.setVisible(true);
			}
		});
		btnIniciar.setBounds(209, 269, 136, 51);
		contentPane.add(btnIniciar);
		
		JLabel lblLogoGame = new JLabel("");
		lblLogoGame.setIcon( new ImageIcon(getClass().getResource("/images/BioGame_Logo1.png")));
		lblLogoGame.setBounds(138, 64, 325, 142);
		contentPane.add(lblLogoGame);
	}
}
