package br.org.uesb.quizsw.util;

import java.sql.ResultSet;

/**
 * Interface para implementação do padrão DAO (Data Access Object)
 * 
 * @author Maurício
 *
 */
public interface DAO<T> {
	
	public int insert(T obj);
	public int update(T obj);
	public int delete(T obj);
	public ResultSet select(String criterias);
	public ResultSet selectAll();

}
