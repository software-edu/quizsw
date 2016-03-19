package br.org.uesb.quizsw.control;

public class QuizPergunta {
	
	private int cdQuiz;
	private int cdPergunta;
	
	public QuizPergunta() {
		super();
	}

	public QuizPergunta(int cdQuiz, int cdPergunta) {
		super();
		this.cdQuiz = cdQuiz;
		this.cdPergunta = cdPergunta;
	}

	public int getCdQuiz() {
		return cdQuiz;
	}

	public void setCdQuiz(int cdQuiz) {
		this.cdQuiz = cdQuiz;
	}

	public int getCdPergunta() {
		return cdPergunta;
	}

	public void setCdPergunta(int cdPergunta) {
		this.cdPergunta = cdPergunta;
	}
	
	

}
