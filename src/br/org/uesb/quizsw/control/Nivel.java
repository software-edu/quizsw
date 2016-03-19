package br.org.uesb.quizsw.control;

public class Nivel {
	
	private int cdNivel;
	private String nmNivel;
	
	public Nivel() {
		super();
	}

	public Nivel(int cdNivel, String nmNivel) {
		super();
		this.cdNivel = cdNivel;
		this.nmNivel = nmNivel;
	}

	public int getCdNivel() {
		return cdNivel;
	}

	public void setCdNivel(int cdNivel) {
		this.cdNivel = cdNivel;
	}

	public String getNmNivel() {
		return nmNivel;
	}

	public void setNmNivel(String nmNivel) {
		this.nmNivel = nmNivel;
	}
	
	

}
