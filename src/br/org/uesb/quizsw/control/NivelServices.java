package br.org.uesb.quizsw.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import br.org.uesb.quizsw.util.Conexao;
import br.org.uesb.quizsw.util.Result;
import br.org.uesb.quizsw.util.Service;

public class NivelServices implements Service<Nivel> {

	@Override
	public Result save(HashMap<String, Object> content) {
		return save(content, null);
	}
	
	public Result save(HashMap<String, Object> content, Connection connection) {
		boolean isConnectionNull = connection==null;
		try {
			if (isConnectionNull) {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false);
			}
			
			Nivel nivel = (Nivel)content.get("nivel");
			if(nivel==null)
				return new Result(-1, "Erro ao salvar. Nível é nulo.", null);
			
			int r;
			if(nivel.getCdNivel()==0) {
				r = new NivelDAO().insert(nivel, connection);
				nivel.setCdNivel(r);
			}
			else
				r = new NivelDAO().update(nivel, connection);
			
			if(r<=0) {
				Conexao.rollback(connection);
				return new Result(r, "Erro ao salvar nível.", null);
			}
			else if (isConnectionNull)
				connection.commit();
			
			content = new HashMap<>();
			content.put("nivel", nivel);
			
			return new Result(r, "Salvo com sucesso.", content);
		}
		catch(Exception e){
			e.printStackTrace();
			if (isConnectionNull)
				Conexao.rollback(connection);
			return new Result(-1, e.getMessage(), null);
		}
		finally{
			if (isConnectionNull)
				Conexao.closeConnection(connection);
		}
	}

	@Override
	public ResultSet find(String criterios) {
		return find(criterios, null);
	}
	
	public ResultSet find(String criterios, Connection connection) {
		boolean isConnectionNull = connection==null;
		if (isConnectionNull)
			connection = Conexao.getConnection();
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement(
					"SELECT A.* "
					+ " FROM nivel A "
					+ " WHERE 1=1 "
					+ (criterios!=null ? criterios : "")
			);
			return pstmt.executeQuery();
		}
		catch(Exception e) {
			e.printStackTrace(System.out);
			return null;
		}
		finally {
			if (isConnectionNull)
				Conexao.closeConnection(connection);
		}
	}

	@Override
	public ResultSet getAll() {
		return getAll(null);
	}
	
	public ResultSet getAll(Connection connection) {
		return find(null, connection);
	}

	@Override
	public Result remove(Nivel object, boolean cascade) {
		return remove(object, cascade, null);
	}
	
	public Result remove(Nivel object, boolean cascade, Connection connection) {
		boolean isConnectionNull = connection==null;
		try {
			if (isConnectionNull) {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false);
			}
			
			int retorno = 0;
			
			if(cascade){
				/** SE HOUVER, REMOVER TABELAS ASSOCIADAS **/
				retorno = 1;
			}
				
			if(!cascade || retorno>0)
				retorno = new NivelDAO().delete(object, connection);
			
			if(retorno<=0){
				Conexao.rollback(connection);
				return new Result(-2, "Erro ao excluir nível", null);
			}
			else if (isConnectionNull)
				connection.commit();
			
			return new Result(1, "Nível excluído com sucesso.", null);
		}
		catch(Exception e){
			e.printStackTrace();
			if (isConnectionNull)
				Conexao.rollback(connection);
			return new Result(-1, "Erro ao excluir nível!", null);
		}
		finally{
			if (isConnectionNull)
				Conexao.closeConnection(connection);
		}
	}

}
