package br.org.uesb.quizsw.util;

import java.sql.ResultSet;
import java.util.HashMap;

/**
 * Interface para classes de serviço (classes que são acessadas diretamente pela
 * view)
 * 
 * @author Maurício
 */

public interface Service<T> {
	
	public Result save(HashMap<String, Object> content);
	public Result remove(T object, boolean cascade);
	public ResultSet find(String criterios);
	public ResultSet getAll();

}
