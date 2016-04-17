package br.org.uesb.quizsw.control;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import br.org.uesb.quizsw.util.Conexao;
import br.org.uesb.quizsw.util.Result;
import br.org.uesb.quizsw.util.Service;

public class RespostaServices implements Service<Resposta> {

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
			
			Resposta resposta = (Resposta)content.get("resposta");
			if(resposta==null)
				return new Result(-1, "Erro ao salvar. Resposta é nulo.", null);
			
			int r;
			if(new RespostaDAO().get(resposta.getCdPergunta(), resposta.getCdResposta(), connection)==null) {
				r = new RespostaDAO().insert(resposta, connection);
				resposta.setCdResposta(r);
			}
			else
				r = new RespostaDAO().update(resposta, connection);
			
			if(r<=0) {
				Conexao.rollback(connection);
				return new Result(r, "Erro ao salvar resposta.", null);
			}
			else if (isConnectionNull)
				connection.commit();
			
			content = new HashMap<>();
			content.put("resposta", resposta);
			
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<HashMap<String, Object>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result remove(Resposta object, boolean cascade) {
		// TODO Auto-generated method stub
		return null;
	}

}
