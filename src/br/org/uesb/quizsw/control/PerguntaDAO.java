package br.org.uesb.quizsw.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.org.uesb.quizsw.util.Conexao;
import br.org.uesb.quizsw.util.DAO;

public class PerguntaDAO implements DAO<Pergunta> {

	@Override
	public int insert(Pergunta obj) {
		return insert(obj, null);
	}
	
	public int insert(Pergunta obj, Connection connection) {
		boolean isConnectionNull = connection==null;
		try {
			if(isConnectionNull) {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false);
			}
			
			PreparedStatement pstmt = connection.prepareStatement("INSERT INTO pergunta (cd_pergunta, txt_pergunta, cd_assunto, cd_nivel)"
																+ " VALUES (?, ?, ?, ?)");
			pstmt.setInt(1, Conexao.getCode("pergunta"));
			pstmt.setString(2, obj.getTxtPergunta());
			pstmt.setInt(3, obj.getCdAssunto());
			pstmt.setInt(4, obj.getCdNivel());
			
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
	public int update(Pergunta obj) {
		return update(obj, null);
	}
	
	public int update(Pergunta obj, Connection connection) {
		boolean isConnectionNull = connection==null;
		try {
			if(isConnectionNull) {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false);
			}
			
			PreparedStatement pstmt = connection.prepareStatement("UPDATE pergunta (cd_pergunta, txt_pergunta, cd_assunto, cd_nivel)"
																+ " VALUES (?, ?, ?, ?)");
			pstmt.setInt(1, obj.getCdPergunta());
			pstmt.setString(2, obj.getTxtPergunta());
			pstmt.setInt(3, obj.getCdAssunto());
			pstmt.setInt(3, obj.getCdNivel());
			
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
	public int delete(Pergunta obj) {
		return delete(obj, null);
	}
	
	public int delete(Pergunta obj, Connection connection) {
		boolean isConnectionNull = connection==null;
		try {
			if(isConnectionNull) {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false);
			}
			
			PreparedStatement pstmt = connection.prepareStatement("DELETE FROM pergunta WHERE cd_pergunta=?");
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
			
			PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM pergunta "
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
		return selectAll(null);
	}
	
	public ResultSet selectAll(Connection connection) {
		return select(null, connection);
	}

	public Pergunta get(int cdPergunta) {
		return get(cdPergunta, null);
	}
	
	public Pergunta get(int cdPergunta, Connection connection) {
		boolean isConnectionNull = connection==null;
		try {
			if(isConnectionNull) {
				connection = Conexao.getConnection();
			}
			
			ResultSet rs = select(" AND cd_pergunta="+cdPergunta, connection);

			Pergunta pergunta = null;
			if(rs.next()) {
				pergunta = new Pergunta(rs.getInt("cd_pergunta"), rs.getString("txt_pergunta"), rs.getInt("cd_assunto"), rs.getInt("cd_nivel"));
			}
			
			return pergunta;
			
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
