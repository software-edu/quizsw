package br.org.uesb.quizsw.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.org.uesb.quizsw.util.Conexao;
import br.org.uesb.quizsw.util.DAO;

public class QuizDAO implements DAO<Quiz> {

	@Override
	public int insert(Quiz obj) {
		return insert(obj, null);
	}
	
	public int insert(Quiz obj, Connection connection) {
		boolean isConnectionNull = connection==null;
		try {
			if(isConnectionNull) {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false);
			}
			
			PreparedStatement pstmt = connection.prepareStatement("INSERT INTO quiz (cd_quiz, nm_quiz, qtd_tempo, qtd_erro)"
																+ " VALUES (?, ?, ?, ?)");
			pstmt.setInt(1, Conexao.getCode("quiz"));
			pstmt.setString(2, obj.getNmQuiz());
			pstmt.setInt(3, obj.getQtdTempo());
			pstmt.setInt(4, obj.getQtdErro());
			
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
	public int update(Quiz obj) {
		return update(obj, null);
	}
	
	public int update(Quiz obj, Connection connection) {
		boolean isConnectionNull = connection==null;
		try {
			if(isConnectionNull) {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false);
			}
			
			PreparedStatement pstmt = connection.prepareStatement("UPDATE quiz (cd_quiz, nm_quiz, qtd_tempo, qtd_erro)"
																+ " VALUES (?, ?, ?, ?)");
			pstmt.setInt(1, obj.getCdQuiz());
			pstmt.setString(2, obj.getNmQuiz());
			pstmt.setInt(3, obj.getQtdTempo());
			pstmt.setInt(4, obj.getQtdErro());
			
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
	public int delete(Quiz obj) {
		return delete(obj, null);
	}	
	
	public int delete(Quiz obj, Connection connection) {
		boolean isConnectionNull = connection==null;
		try {
			if(isConnectionNull) {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false);
			}
			
			PreparedStatement pstmt = connection.prepareStatement("DELETE FROM quiz WHERE cd_quiz=?");
			pstmt.setInt(1, obj.getCdQuiz());
			
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
			
			PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM quiz "
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

	@Override
	public ResultSet selectAll() {
		return select(null);
	}
	
	public ResultSet selectAll(Connection connection) {
		return select(null, connection);
	}

	public Quiz get(int cdQuiz) {
		return get(cdQuiz, null);
	}
	
	public Quiz get(int cdQuiz, Connection connection) {
		boolean isConnectionNull = connection==null;
		try {
			if(isConnectionNull) {
				connection = Conexao.getConnection();
			}
			
			ResultSet rs = select(" AND cd_quiz="+cdQuiz, connection);

			Quiz quiz = null;
			if(rs.next()) {
				quiz = new Quiz(rs.getInt("cd_quiz"), rs.getString("nm_quiz"), rs.getInt("qtd_tempo"), rs.getInt("qtd_erro"));
			}
			
			return quiz;
			
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
