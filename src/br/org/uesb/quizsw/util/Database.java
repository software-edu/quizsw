package br.org.uesb.quizsw.util;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Database {
	
	private String dbms;
	private String driver;
	private String url;
	private String user;
	private String pass;

	public String getDbms() {
		return dbms;
	}
	@XmlElement
	public void setDbms(String dbms) {
		this.dbms = dbms;
	}

	public String getDriver() {
		return driver;
	}
	@XmlElement
	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}
	@XmlElement
	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}
	@XmlElement
	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}
	@XmlElement
	public void setPass(String pass) {
		this.pass = pass;
	}
	@Override
	public String toString() {
		return "Database [dbms=" + dbms + ", driver=" + driver + ", url=" + url + ", user=" + user + ", pass=" + pass
				+ "]";
	}
}
