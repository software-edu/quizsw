package br.org.uesb.quizsw.view;

public class Questao {
	
	int aleatorio;
	String questoes[] = new String[20];
	static String questao;

	public Questao(){
		
		questoes[1] = "Sobre as fun��es dos tipos de ret�culo endoplasm�tico, pode-se afirmar que:$O rugoso est� relacionado com o processo de s�ntese de ester�ides$O liso tem como fun��o a s�ntese de prote�nas$O liso � respons�vel pela forma��o do acrossomo dos espermatoz�ides$O rugoso est� ligado � s�ntese de prote�na$4";
		questao = questoes[aleatorio(1,2)]; 
	}
	
   public int aleatorio(int min, int max){
	   aleatorio = (int)((max-min)*Math.random()+min);
	   return aleatorio;
   }
    
    
}
