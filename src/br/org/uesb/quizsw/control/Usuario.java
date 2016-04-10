package br.org.uesb.quizsw.control;

public class Usuario {
	
	private int cdUsuario;
	private String nmLogin;
	private String nmSenha;
	private int tpPermissao;
	
	public Usuario() {
		super();
	}
	
	public Usuario(int cdUsuario, String nmLogin, String nmSenha, int tpPermissao) {
		super();
		this.cdUsuario = cdUsuario;
		this.nmLogin = nmLogin;
		this.nmSenha = nmSenha;
		this.tpPermissao = tpPermissao;
	}

	public int getCdUsuario() {
		return cdUsuario;
	}

	public void setCdUsuario(int cdUsuario) {
		this.cdUsuario = cdUsuario;
	}

	public String getNmLogin() {
		return nmLogin;
	}

	public void setNmLogin(String nmLogin) {
		this.nmLogin = nmLogin;
	}

	public String getNmSenha() {
		return nmSenha;
	}

	public void setNmSenha(String nmSenha) {
		this.nmSenha = nmSenha;
	}

	public int getTpPermissao() {
		return tpPermissao;
	}

	public void setTpPermissao(int tpPermissao) {
		this.tpPermissao = tpPermissao;
	}

}
