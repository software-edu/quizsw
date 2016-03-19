package br.org.uesb.quizsw.control;

public class Pergunta {
	
	private int cdPergunta;
	private String txtPergunta;
	private int cdAssunto;
	private int cdNivel;
	
	public Pergunta() {
		super();
	}

	public Pergunta(int cdPergunta, String txtPergunta, int cdAssunto, int cdNivel) {
		super();
		this.cdPergunta = cdPergunta;
		this.txtPergunta = txtPergunta;
		this.cdAssunto = cdAssunto;
		this.cdNivel = cdNivel;
	}

	public int getCdPergunta() {
		return cdPergunta;
	}

	public void setCdPergunta(int cdPergunta) {
		this.cdPergunta = cdPergunta;
	}

	public String getTxtPergunta() {
		return txtPergunta;
	}

	public void setTxtPergunta(String txtPergunta) {
		this.txtPergunta = txtPergunta;
	}

	public int getCdAssunto() {
		return cdAssunto;
	}

	public void setCdAssunto(int cdAssunto) {
		this.cdAssunto = cdAssunto;
	}

	public int getCdNivel() {
		return cdNivel;
	}

	public void setCdNivel(int cdNivel) {
		this.cdNivel = cdNivel;
	}
	
	

}
