package br.org.uesb.quizsw.view;

import javax.swing.JFrame;

public class Muda_Questao {
	
	static String ri_questao = null;
	static String op1 = null;
	static String op2 = null;
	static String op3 = null;
	static String op4 = null;
	static int palavradeordem = 0;
	
	static Bio_Quiz quiz;
	static Questao questao_cl;
	static String akt_questao;
	
	//paga p;
	
	public Muda_Questao(){
		
		quiz = new Bio_Quiz();
		novaQuestao();
		
	}

	public static void novaQuestao(){
		questao_cl = new Questao();
		akt_questao = questao_cl.questao;
		
		analisa();
		mudaTexto();
		
	}
	
	
	public static void analisa(){
		
		String[] questao = akt_questao.split("\\$");
		
		for (int x = 0; x<questao.length;x++){
			
			switch(x){
			
			case 0:
				ri_questao = questao[x];
				break;
				
			case 1:
				op1 = questao[x];
				break;
				
			case 2:
				op2 = questao[x];
				break;
				
			case 3:
				op3 = questao[x];
				break;
				
			case 4:
				op4 = questao[x];
				break;
				
			case 5:
				palavradeordem = Integer.parseInt(questao[x]);
				break;
			}
		}
		
		
		
		
	}
	
	public static void mudaTexto(){
		
		quiz.mudaTextoLabel(ri_questao);
		quiz.palavradeordem = palavradeordem;
		quiz.mudaTextoBotao(1, op1);
		quiz.mudaTextoBotao(2, op2);
		quiz.mudaTextoBotao(3, op3);
		quiz.mudaTextoBotao(4, op4);
		
	}
	
}
