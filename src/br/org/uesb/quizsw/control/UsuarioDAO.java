package br.org.uesb.quizsw.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.org.uesb.quizsw.util.Conexao;
import br.org.uesb.quizsw.util.DAO;

public class UsuarioDAO implements DAO<Usuario> {

	@Override
	public int insert(Usuario obj) {
		return insert(obj, null);
	}
	
	public int insert(Usuario obj, Connection connection) {
		boolean isConnectionNull = connection==null;
		try {
			if(isConnectionNull) {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false);
			}
			
			PreparedStatement pstmt = connection.prepareStatement("INSERT INTO usuario (cd_usuario, nm_login, nm_senha, tp_permissao)"
																+ " VALUES (?, ?, ?, ?)");
			pstmt.setInt(1, Conexao.getCode("usuario"));
			pstmt.setString(2, obj.getNmLogin());
			pstmt.setString(3, obj.getNmSenha());
			pstmt.setInt(4, obj.getTpPermissao());
			
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
	public int update(Usuario obj) {
		return update(obj, null);
	}
	
	public int update(Usuario obj, Connection connection) {
		boolean isConnectionNull = connection==null;
		try {
			if(isConnectionNull) {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false);
			}
			
			PreparedStatement pstmt = connection.prepareStatement("UPDATE usuario (cd_usuario, nm_login, nm_senha, tp_permissao)"
																+ " VALUES (?, ?, ?, ?)");
			pstmt.setInt(1, obj.getCdUsuario());
			pstmt.setString(2, obj.getNmLogin());
			pstmt.setString(3, obj.getNmSenha());
			pstmt.setInt(3, obj.getTpPermissao());
			
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
	public int delete(Usuario obj) {
		return delete(obj, null);
	}
	
	public int delete(Usuario obj, Connection connection) {
		boolean isConnectionNull = connection==null;
		try {
			if(isConnectionNull) {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false);
			}
			
			PreparedStatement pstmt = connection.prepareStatement("DELETE FROM usuario WHERE cd_usuario=?");
			pstmt.setInt(1, obj.getCdUsuario());
			
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
			
			PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM usuario "
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

	public Usuario get(int cdUsuario) {
		return get(cdUsuario, null);
	}
	
	public Usuario get(int cdUsuario, Connection connection) {
		boolean isConnectionNull = connection==null;
		try {
			if(isConnectionNull) {
				connection = Conexao.getConnection();
			}
			
			ResultSet rs = select(" AND cd_usuario="+cdUsuario, connection);

			Usuario usuario = null;
			if(rs.next()) {
				usuario = new Usuario(rs.getInt("cd_usuario"), rs.getString("nm_login"), rs.getString("nm_senha"), rs.getInt("tp_permissao"));
			}
			
			return usuario;
			
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
