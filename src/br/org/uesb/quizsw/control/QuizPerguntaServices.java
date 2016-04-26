package br.org.uesb.quizsw.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;

import br.org.uesb.quizsw.util.Conexao;
import br.org.uesb.quizsw.util.Result;
import br.org.uesb.quizsw.util.Service;
import br.org.uesb.quizsw.util.Util;

public class QuizPerguntaServices implements Service<QuizPergunta> {

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
			
			QuizPergunta quizPergunta = (QuizPergunta)content.get("quizPergunta");
			if(quizPergunta==null)
				return new Result(-1, "Erro ao salvar. QuizPergunta é nulo.", null);
			
			int r;
			if(new QuizPerguntaDAO().get(quizPergunta.getCdQuiz(), quizPergunta.getCdPergunta(), connection)==null) {
				r = new QuizPerguntaDAO().insert(quizPergunta, connection);
			}
			else
				r = new QuizPerguntaDAO().update(quizPergunta, connection);
			
			if(r<=0) {
				Conexao.rollback(connection);
				return new Result(r, "Erro ao salvar quizPergunta.", null);
			}
			else if (isConnectionNull)
				connection.commit();
			
			content = new HashMap<>();
			content.put("quizPergunta", quizPergunta);
			
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
					"SELECT A.*, B.txt_pergunta "
					+ " FROM quiz_pergunta A "
					+ " JOIN pergunta B ON (A.cd_pergunta = B.cd_pergunta)"
					+ " WHERE 1=1 "
					+ (criterios!=null ? criterios : "")
					+ " ORDER BY A.nr_ordem"
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
	public Result remove(QuizPergunta object, boolean cascade) {
		return remove(object, cascade, null);
	}
	
	public Result remove(QuizPergunta object, boolean cascade, Connection connection) {
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
				retorno = new QuizPerguntaDAO().delete(object, connection);
			
			if(retorno<=0){
				Conexao.rollback(connection);
				return new Result(-2, "Erro ao excluir QuizPergunta", null);
			}
			else if (isConnectionNull)
				connection.commit();
			
			return new Result(1, "QuizPergunta excluída com sucesso.", null);
		}
		catch(Exception e){
			e.printStackTrace();
			if (isConnectionNull)
				Conexao.rollback(connection);
			return new Result(-1, "Erro ao excluir Resposta!", null);
		}
		finally{
			if (isConnectionNull)
				Conexao.closeConnection(connection);
		}
	}

}
