package br.org.uesb.quizsw.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.org.uesb.quizsw.util.Conexao;
import br.org.uesb.quizsw.util.DAO;

public class QuizPerguntaDAO implements DAO<QuizPergunta> {

	@Override
	public int insert(QuizPergunta obj) {
		return insert(obj, null);
	}
	
	public int insert(QuizPergunta obj, Connection connection) {
		boolean isConnectionNull = connection==null;
		try {
			if(isConnectionNull) {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false);
			}
			
			PreparedStatement pstmt = connection.prepareStatement("INSERT INTO quiz_pergunta (cd_quiz, cd_pergunta, nr_ordem)"
																+ " VALUES (?, ?, ?)");
			pstmt.setInt(1, obj.getCdQuiz());
			pstmt.setInt(2, obj.getCdPergunta());
			pstmt.setInt(3, obj.getNrOrdem());
			
			pstmt.executeUpdate();
			if(isConnectionNull)
				connection.commit();
			
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return -1;
		} finally {
			if(isConnectionNull) {
				Conexao.closeConnection(connection);
			}
		}
	}

	@Override
	public int update(QuizPergunta obj) {
		return update(obj, null);
	}
	
	public int update(QuizPergunta obj, Connection connection) {
		boolean isConnectionNull = connection==null;
		try {
			if(isConnectionNull) {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false);
			}
			
			PreparedStatement pstmt = connection.prepareStatement("UPDATE quiz_pergunta SET cd_quiz=?, "
																+ "cd_pergunta=?, "
																+ "nr_ordem=? "
																+ " WHERE cd_quiz=? AND cd_pergunta=?");
			pstmt.setInt(1, obj.getCdQuiz());
			pstmt.setInt(2, obj.getCdPergunta());
			pstmt.setInt(3, obj.getNrOrdem());
			pstmt.setInt(4, obj.getCdQuiz());
			pstmt.setInt(5, obj.getCdPergunta());
			
			pstmt.executeUpdate();
			if(isConnectionNull)
				connection.commit();
			
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return -1;
		} finally {
			if(isConnectionNull) {
				Conexao.closeConnection(connection);
			}
		}
	}

	@Override
	public int delete(QuizPergunta obj) {
		return delete(obj, null);
	}
	
	public int delete(QuizPergunta obj, Connection connection) {
		boolean isConnectionNull = connection==null;
		try {
			if(isConnectionNull) {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false);
			}
			
			PreparedStatement pstmt = connection.prepareStatement("DELETE FROM quiz_pergunta WHERE cd_quiz=? AND cd_pergunta=?");
			pstmt.setInt(1, obj.getCdQuiz());
			pstmt.setInt(1, obj.getCdPergunta());
			
			pstmt.executeUpdate();
			if(isConnectionNull)
				connection.commit();
			
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return -1;
		} finally {
			if(isConnectionNull) {
				Conexao.closeConnection(connection);
			}
		}
	}

	@Override
	public ResultSet select(String criterias) {
		return select(criterias, null);
	}
	
	public ResultSet select(String criterias, Connection connection) {
		boolean isConnectionNull = connection==null;
		try {
			if(isConnectionNull) {
				connection = Conexao.getConnection();
			}
			
			PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM quiz_pergunta "
																+ " WHERE 1=1 "
																+ criterias);
			
			return pstmt.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if(isConnectionNull) {
				Conexao.closeConnection(connection);
			}
		}
	}

	public QuizPergunta get(int cdQuiz, int cdPergunta) {
		return get(cdQuiz, cdPergunta, null);
	}
	
	public QuizPergunta get(int cdQuiz, int cdPergunta, Connection connection) {
		boolean isConnectionNull = connection==null;
		try {
			if(isConnectionNull) {
				connection = Conexao.getConnection();
			}
			
			ResultSet rs = select(" AND cd_quiz="+cdQuiz +
								  " AND cd_pergunta="+cdPergunta, connection);

			QuizPergunta quizPergunta = null;
			if(rs.next()) {
				quizPergunta = new QuizPergunta(rs.getInt("cd_quiz"), rs.getInt("cd_pergunta"), rs.getInt("nr_ordem"));
			}
			
			return quizPergunta;
			
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if(isConnectionNull) {
				Conexao.closeConnection(connection);
			}
		}
	}

}
