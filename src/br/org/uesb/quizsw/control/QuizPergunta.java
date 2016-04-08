package br.org.uesb.quizsw.control;

public class QuizPergunta {
	
	private int cdQuiz;
	private int cdPergunta;
	private int nrOrdem;
	
	public QuizPergunta() {
		super();
	}

	public QuizPergunta(int cdQuiz, int cdPergunta, int nrOrdem) {
		super();
		this.cdQuiz = cdQuiz;
		this.cdPergunta = cdPergunta;
		this.nrOrdem = nrOrdem;
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

	public int getNrOrdem() {
		return nrOrdem;
	}

	public void setNrOrdem(int nrOrdem) {
		this.nrOrdem = nrOrdem;
	}
	
	

}
