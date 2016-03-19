package br.org.uesb.quizsw.control;

public class Resposta {
	
	private int cdResposta;
	private int cdPergunta;
	private String txtResposta;
	private int lgCorreto;
	private byte[] blbImagem;
	
	public Resposta() {
		super();
	}

	public Resposta(int cdResposta, int cdPergunta, String txtResposta, int lgCorreto, byte[] blbImagem) {
		super();
		this.cdResposta = cdResposta;
		this.cdPergunta = cdPergunta;
		this.txtResposta = txtResposta;
		this.lgCorreto = lgCorreto;
		this.blbImagem = blbImagem;
	}

	public int getCdResposta() {
		return cdResposta;
	}

	public void setCdResposta(int cdResposta) {
		this.cdResposta = cdResposta;
	}

	public int getCdPergunta() {
		return cdPergunta;
	}

	public void setCdPergunta(int cdPergunta) {
		this.cdPergunta = cdPergunta;
	}

	public String getTxtResposta() {
		return txtResposta;
	}

	public void setTxtResposta(String txtResposta) {
		this.txtResposta = txtResposta;
	}

	public int getLgCorreto() {
		return lgCorreto;
	}

	public void setLgCorreto(int lgCorreto) {
		this.lgCorreto = lgCorreto;
	}

	public byte[] getBlbImagem() {
		return blbImagem;
	}

	public void setBlbImagem(byte[] blbImagem) {
		this.blbImagem = blbImagem;
	}
	
	

}
