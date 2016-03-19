package br.org.uesb.quizsw.control;

public class Quiz {
	
	private int cdQuiz;
	private String nmQuiz;
	private int qtdTempo;
	private int qtdErro;
	
	public Quiz() {
		super();
	}

	public Quiz(int cdQuiz, String nmQuiz, int qtdTempo, int qtdErro) {
		super();
		this.cdQuiz = cdQuiz;
		this.nmQuiz = nmQuiz;
		this.qtdTempo = qtdTempo;
		this.qtdErro = qtdErro;
	}

	public int getCdQuiz() {
		return cdQuiz;
	}

	public void setCdQuiz(int cdQuiz) {
		this.cdQuiz = cdQuiz;
	}

	public String getNmQuiz() {
		return nmQuiz;
	}

	public void setNmQuiz(String nmQuiz) {
		this.nmQuiz = nmQuiz;
	}

	public int getQtdTempo() {
		return qtdTempo;
	}

	public void setQtdTempo(int qtdTempo) {
		this.qtdTempo = qtdTempo;
	}

	public int getQtdErro() {
		return qtdErro;
	}

	public void setQtdErro(int qtdErro) {
		this.qtdErro = qtdErro;
	}
	
	

}
