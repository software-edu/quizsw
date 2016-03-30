package br.org.uesb.quizsw.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import java.awt.Window.Type;

public class Janela_Inicio extends JFrame {

	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela_Inicio frame = new Janela_Inicio();
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
	public Janela_Inicio() {
		setResizable(false);
		setUndecorated(true);//retira barra de titulos
		setIconImage(new ImageIcon(getClass().getResource("/img/BioGame_Icon.png")).getImage());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 575, 417);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("MENU");
		menuBar.add(mnMenu);
		
		JMenuItem mntmSobreOJogo = new JMenuItem("Sobre o Jogo");
		
		//encaminha para a tela sobre
			mntmSobreOJogo.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent eventoSobre){
					Janela_Sobre Sobre = new Janela_Sobre();
					Sobre.setVisible(true);
				}
			});
		mnMenu.add(mntmSobreOJogo);
		
		JMenuItem mntmSairDoJogo = new JMenuItem("Sair do Jogo");
		
		//sai do jogo
				mntmSairDoJogo.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent evento){
						System.exit(0);
					}
				});
				
		mnMenu.add(mntmSairDoJogo);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnIniciar = new JButton("INICIAR");
		
		//encaminha para a tela sobre
		btnIniciar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent eventoIniciaQuiz){
				Bio_Quiz Quiz = new Bio_Quiz();
				Quiz.setVisible(true);
			}
		});
		btnIniciar.setBounds(209, 269, 136, 51);
		contentPane.add(btnIniciar);
		
		JLabel lblLogoGame = new JLabel("");
		lblLogoGame.setIcon( new ImageIcon(getClass().getResource("/img/BioGame_Logo1.png")));
		lblLogoGame.setBounds(138, 64, 325, 142);
		contentPane.add(lblLogoGame);
	}
}
