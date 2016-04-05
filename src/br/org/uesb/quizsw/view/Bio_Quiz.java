package br.org.uesb.quizsw.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;



public class Bio_Quiz extends JFrame implements ActionListener {
	
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	JLabel questao;
	JButton opcoes[] = new JButton[4];
	
	Questao questao2;
	Muda_Questao muda;
	Object[] opt ={"OK"};
	JFrame janela;
	JProgressBar barradeprogresso;
	
	String titulo;
	int palavradeordem;
	int direcao;


	public Bio_Quiz(){		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
		janela = new JFrame();
		janela.getContentPane().setLayout(null);		
		janela.setIconImage(new ImageIcon(getClass().getResource("/images/BioGame_Icon.png")).getImage());
		
		 
	    //janela.setLocationRelativeTo(null); //centraliza a tela, antes de mostrar
	    //janela.setResizable(false);//não permite o redimensionamento da tela
	    		
		janela.setSize(616, 400);
		janela.setLocation((dim.width-this.getSize().width)/2,   
                (dim.height-this.getSize().height)/2);
		//janela.setLocation((int)(dim.getWidth()-616)/2, (int)(dim.getHeight()-400/2));
		janela.setVisible(true);
		
		
		

		
		
		
		//questao = new JLabel();
		
		opcoes[0] = new JButton(" - ");
		opcoes[0].setBounds(52,90,120,40);
		opcoes[0].addActionListener(this);
		janela.getContentPane().add(opcoes[0]);
		
		opcoes[1] = new JButton(" - ");
		opcoes[1].setBounds(52,160,120,40);
		opcoes[1].addActionListener(this);
		janela.getContentPane().add(opcoes[1]);
		
		opcoes[2] = new JButton(" - ");
		opcoes[2].setBounds(220,90,120,40);
		opcoes[2].addActionListener(this);
		janela.getContentPane().add(opcoes[2]);
		
		opcoes[3] = new JButton(" - ");
		opcoes[3].setBounds(220,160,120,40);
		opcoes[3].addActionListener(this);
		janela.getContentPane().add(opcoes[3]);
		
		
		questao = new JLabel ("A QUESTAO APARECE AQUI");
		questao.setBounds(10,15,580,63);
		janela.getContentPane().add(questao);
		
		barradeprogresso = new JProgressBar(0,100);
		barradeprogresso.setBounds(167,283,285,25);
		barradeprogresso.setValue(0);
		barradeprogresso.setStringPainted(true);
		janela.getContentPane().add(barradeprogresso);
		

		
	}
	
	public static void main(String[] args) {
		
		new Muda_Questao();		

	}
	
	public void actionPerformed(ActionEvent evento){
		
	    

		if(evento.getSource()== opcoes[0]){
			
			if (palavradeordem == 1){
				
				
				Mensagem("Correto!","Continue assim!!");
				
				Porcento();
				Muda_Questao.novaQuestao();
				
				
			}else{
				
				Mensagem("Errado","Releia sobre o assunto!");
				
			}
			}
		
		
		
		if(evento.getSource()== opcoes[1]){
			

			
			
			if (palavradeordem == 2){
				
				Mensagem("Correto!","Continue assim!!");
				
				Porcento();
				
				Muda_Questao.novaQuestao();
				
			}else{
				Mensagem("Errado!","Releia sobre o assunto!");
			}
		}
		
		
		
		if(evento.getSource() == opcoes[2]){
			
			if (palavradeordem == 3){
				
				Mensagem("Correto!","Continue assim!!");
				
				Porcento();
				
				Muda_Questao.novaQuestao();
				
			}else{
				
				Mensagem("Errado!","Releia sobre o assunto!");
			}
		}
		
		
		if(evento.getSource() == opcoes[3]){
			
			if (palavradeordem == 4){
				
				Mensagem("Correto!","Continue assim!!");
				
				
				Porcento();
				
				Muda_Questao.novaQuestao();
				
			}else{
				Mensagem("Errado!","Releia sobre o assunto!");
			}
		}
		
		
			}
	
	 public void mudaTextoBotao (int botao, String texto){
		 
		 opcoes[botao-1].setText(texto);
		 
	 }

	 public void mudaTextoLabel (String texto){
		 
		 questao.setText(texto);
		 
	 }
	 
	 public void Porcentagem(int numero,int global){
			barradeprogresso.setValue((int) 100/global*numero);
		}
		
	public void Mensagem(String afirmacao,String informacao){
			JOptionPane.showOptionDialog(null, afirmacao, informacao,

			        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,

			        null, opt, opt[0]);
		}
		
		
	public void Porcento (){
			
			if (direcao <= 7 ){
				
				direcao += 1;
				Porcentagem(direcao, 8);
			}else{
				Mensagem("Super!","PARABÉNS :)");
				System.exit(0);
				
			}
}
}

