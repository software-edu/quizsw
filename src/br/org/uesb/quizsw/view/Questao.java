package br.org.uesb.quizsw.view;

public class Questao {
	
	int aleatorio;
	String questoes[] = new String[20];
	static String questao;

	public Questao(){
		
		questoes[1] = "Sobre as funções dos tipos de retículo endoplasmático, pode-se afirmar que:$O rugoso está relacionado com o processo de síntese de esteróides$O liso tem como função a síntese de proteínas$O liso é responsável pela formação do acrossomo dos espermatozóides$O rugoso está ligado à síntese de proteína$4";
		questao = questoes[aleatorio(1,2)]; 
	}
	
   public int aleatorio(int min, int max){
	   aleatorio = (int)((max-min)*Math.random()+min);
	   return aleatorio;
   }
    
    
}
