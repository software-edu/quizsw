package br.org.uesb.quizsw.control;

public class Assunto {
	
	private int cdAssunto;
	private String nmAssunto;
	private int cdAssuntoSuperior;
	
	public Assunto() {
		super();
	}

	public Assunto(int cdAssunto, String nmAssunto, int cdAssuntoSuperior) {
		super();
		this.cdAssunto = cdAssunto;
		this.nmAssunto = nmAssunto;
		this.cdAssuntoSuperior = cdAssuntoSuperior;
	}

	public int getCdAssunto() {
		return cdAssunto;
	}

	public void setCdAssunto(int cdAssunto) {
		this.cdAssunto = cdAssunto;
	}

	public String getNmAssunto() {
		return nmAssunto;
	}

	public void setNmAssunto(String nmAssunto) {
		this.nmAssunto = nmAssunto;
	}

	public int getCdAssuntoSuperior() {
		return cdAssuntoSuperior;
	}

	public void setCdAssuntoSuperior(int cdAssuntoSuperior) {
		this.cdAssuntoSuperior = cdAssuntoSuperior;
	}
	
	

}
