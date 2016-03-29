package br.org.uesb.quizsw.util;

import java.util.HashMap;

public class Result {
	
	private int code;
	private String message;
	private HashMap<String, Object> objects;
	
	public Result(int code, String message, HashMap<String, Object> objects) {
		super();
		this.code = code;
		this.message = message;
		this.objects = objects;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HashMap<String, Object> getObjects() {
		return objects;
	}

	public void setObjects(HashMap<String, Object> objects) {
		this.objects = objects;
	}

}
