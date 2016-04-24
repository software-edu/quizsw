package br.org.uesb.quizsw.control;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import br.org.uesb.quizsw.util.Conexao;
import br.org.uesb.quizsw.util.Result;
import br.org.uesb.quizsw.util.Service;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<HashMap<String, Object>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result remove(Quiz object, boolean cascade) {
		// TODO Auto-generated method stub
		return null;
	}

}
