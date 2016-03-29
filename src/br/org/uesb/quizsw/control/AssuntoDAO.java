package br.org.uesb.quizsw.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.org.uesb.quizsw.util.Conexao;
import br.org.uesb.quizsw.util.DAO;

public class AssuntoDAO implements DAO<Assunto> {

	@Override
	public int insert(Assunto obj) {
		return insert(obj, null);
	}
	
	public int insert(Assunto obj, Connection connection) {
		boolean isConnectionNull = connection==null;
		try {
			if(isConnectionNull) {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false);
			}
			
			PreparedStatement pstmt = connection.prepareStatement("INSERT INTO assunto (cd_assunto, nm_assunto, cd_assunto_superior)"
																+ " VALUES (?, ?, ?)");
			pstmt.setInt(1, Conexao.getCode("assunto"));
			pstmt.setString(2, obj.getNmAssunto());
			pstmt.setInt(3, obj.getCdAssuntoSuperior());
			
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
	public int update(Assunto obj) {
		return update(obj, null);
	}
	
	public int update(Assunto obj, Connection connection) {
		boolean isConnectionNull = connection==null;
		try {
			if(isConnectionNull) {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false);
			}
			
			PreparedStatement pstmt = connection.prepareStatement("UPDATE assunto (cd_assunto, nm_assunto, cd_assunto_superior)"
																+ " VALUES (?, ?, ?)");
			pstmt.setInt(1, obj.getCdAssunto());
			pstmt.setString(2, obj.getNmAssunto());
			pstmt.setInt(3, obj.getCdAssuntoSuperior());
			
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
	public int delete(Assunto obj) {
		return delete(obj, null);
	}
	
	public int delete(Assunto obj, Connection connection) {
		boolean isConnectionNull = connection==null;
		try {
			if(isConnectionNull) {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false);
			}
			
			PreparedStatement pstmt = connection.prepareStatement("DELETE FROM assunto WHERE cd_assunto=?");
			pstmt.setInt(1, obj.getCdAssunto());
			
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
			
			PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM assunto "
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

	public Assunto get(int cdAssunto) {
		return get(cdAssunto, null);
	}
	
	public Assunto get(int cdAssunto, Connection connection) {
		boolean isConnectionNull = connection==null;
		try {
			if(isConnectionNull) {
				connection = Conexao.getConnection();
			}
			
			ResultSet rs = select(" AND cd_assunto="+cdAssunto, connection);

			Assunto assunto = null;
			if(rs.next()) {
				assunto = new Assunto(rs.getInt("cd_assunto"), rs.getString("nm_assunto"), rs.getInt("cd_assunto_superior"));
			}
			
			return assunto;
			
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
