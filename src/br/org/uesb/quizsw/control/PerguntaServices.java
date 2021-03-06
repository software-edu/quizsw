package br.org.uesb.quizsw.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;

import br.org.uesb.quizsw.util.Conexao;
import br.org.uesb.quizsw.util.Result;
import br.org.uesb.quizsw.util.Service;
import br.org.uesb.quizsw.util.Util;

public class PerguntaServices implements Service<Pergunta> {

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
			
			Pergunta pergunta = (Pergunta)content.get("pergunta");
			if(pergunta==null)
				return new Result(-1, "Erro ao salvar. Pergunta � nulo.", null);
			
			int r;
			if(pergunta.getCdPergunta()==0) {
				r = new PerguntaDAO().insert(pergunta, connection);
				pergunta.setCdPergunta(r);
			}
			else
				r = new PerguntaDAO().update(pergunta, connection);
			
			if(r<=0) {
				Conexao.rollback(connection);
				return new Result(r, "Erro ao salvar pergunta.", null);
			}
			
			for (Resposta resposta : (Resposta[])content.get("respostas")) {
				resposta.setCdPergunta(r);
				HashMap<String, Object> c = new HashMap<>();
				c.put("resposta", resposta);
				
				if(new RespostaServices().save(c, connection).getCode()<=0) {
					Conexao.rollback(connection);
					return new Result(-2, "Erro ao salvar resposta.", null);
				}
			}
			
			if(r<=0) {
				Conexao.rollback(connection);
				return new Result(r, "Erro ao salvar n�vel.", null);
			}
			else if (isConnectionNull)
				connection.commit();
			
			content = new HashMap<>();
			content.put("pergunta", pergunta);
			
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
	public ArrayList<HashMap<String, Object>> find(String criterios) {
		return find(criterios, null);
	}
	
	public ArrayList<HashMap<String, Object>> find(String criterios, Connection connection) {
		boolean isConnectionNull = connection==null;
		if (isConnectionNull)
			connection = Conexao.getConnection();
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement(
					"SELECT A.* "
					+ " FROM pergunta A "
					+ " WHERE 1=1 "
					+ (criterios!=null ? criterios : "")
			);
			return Util.resultSetToList(pstmt.executeQuery());
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
	public ArrayList<HashMap<String, Object>> getAll() {
		return getAll(null);
	}
	
	public ArrayList<HashMap<String, Object>> getAll(Connection connection) {
		return find(null, connection);
	}

	@Override
	public Result remove(Pergunta object, boolean cascade) {
		return remove(object, cascade, null);
	}
	
	public Result remove(Pergunta object, boolean cascade, Connection connection) {
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
				retorno = new PerguntaDAO().delete(object, connection);
			
			if(retorno<=0){
				Conexao.rollback(connection);
				return new Result(-2, "Erro ao excluir Pergunta", null);
			}
			else if (isConnectionNull)
				connection.commit();
			
			return new Result(1, "Pergunta exclu�da com sucesso.", null);
		}
		catch(Exception e){
			e.printStackTrace();
			if (isConnectionNull)
				Conexao.rollback(connection);
			return new Result(-1, "Erro ao excluir Pergunta!", null);
		}
		finally{
			if (isConnectionNull)
				Conexao.closeConnection(connection);
		}
	}

}
