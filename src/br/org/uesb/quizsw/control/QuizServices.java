package br.org.uesb.quizsw.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;

import br.org.uesb.quizsw.util.Conexao;
import br.org.uesb.quizsw.util.Result;
import br.org.uesb.quizsw.util.Service;
import br.org.uesb.quizsw.util.Util;

public class QuizServices implements Service<Quiz> {

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

			Quiz quiz = (Quiz)content.get("quiz");
			if(quiz==null)
				return new Result(-1, "Erro ao salvar. Pergunta é nulo.", null);
			
			int r;
			if(quiz.getCdQuiz()==0) {
				r = new QuizDAO().insert(quiz, connection);
				quiz.setCdQuiz(r);
			}
			else
				r = new QuizDAO().update(quiz, connection);
			
			if(r<=0) {
				Conexao.rollback(connection);
				return new Result(r, "Erro ao salvar quiz.", null);
			}
			
			//TODO: salvar perguntaQuiz
			ArrayList<Pergunta> perguntas = (ArrayList<Pergunta>)content.get("perguntas");
			for (int i=0; i<perguntas.size(); i++) {
				HashMap<String, Object> contentAux = new HashMap<>();
				Pergunta p = perguntas.get(i);
				QuizPergunta quizPergunta = new QuizPergunta(quiz.getCdQuiz(), p.getCdPergunta(), i+1);
				
				contentAux.put("quizPergunta", quizPergunta);
				r = new QuizPerguntaServices().save(contentAux, connection).getCode();
				
				if(r<=0) {
					Conexao.rollback(connection);
					return new Result(r, "Erro ao salvar pergunta.", null);
				}
			}
			
			if(r<=0) {
				Conexao.rollback(connection);
				return new Result(r, "Erro ao salvar quiz.", null);
			}
			else if (isConnectionNull)
				connection.commit();
			
			content = new HashMap<>();
			content.put("quiz", quiz);
			
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
					+ " FROM quiz A "
					+ " WHERE 1=1 "
					+ (criterios!=null ? criterios : "")
			);
			
			ArrayList<HashMap<String, Object>> rsl = Util.resultSetToList(pstmt.executeQuery());
			
			for (HashMap<String, Object> register : rsl) {
				ArrayList<HashMap<String, Object>> rslPergunta = 
						new QuizPerguntaServices().find(" AND A.cd_quiz = "+(int)register.get("cd_quiz"), connection);
				
				for (HashMap<String, Object> registerPergunta : rslPergunta) {
					ArrayList<HashMap<String, Object>> rslResposta = 
							new RespostaServices().find(" AND A.cd_pergunta="+(int)registerPergunta.get("cd_pergunta"), connection);
					
					registerPergunta.put("respostas", rslResposta);
				}
				
				register.put("perguntas", rslPergunta);
				
			}
			
			return rsl;
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
	public Result remove(Quiz object, boolean cascade) {
		// TODO Auto-generated method stub
		return null;
	}

}
