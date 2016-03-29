package br.org.uesb.quizsw.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.org.uesb.quizsw.util.Conexao;
import br.org.uesb.quizsw.util.DAO;

public class RespostaDAO implements DAO<Resposta> {

	@Override
	public int insert(Resposta obj) {
		return insert(obj, null);
	}
	
	public int insert(Resposta obj, Connection connection) {
		boolean isConnectionNull = connection==null;
		try {
			if(isConnectionNull) {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false);
			}
			
			PreparedStatement pstmt = connection.prepareStatement("INSERT INTO resposta (cd_pergunta, cd_resposta, txt_resposta, lg_correto, blb_imagem)"
																+ " VALUES (?, ?, ?, ?, ?)");
			//TODO: gerar chave composta
			pstmt.setInt(1, obj.getCdPergunta());
			pstmt.setInt(2, obj.getCdResposta());
			pstmt.setString(3, obj.getTxtResposta());
			pstmt.setInt(4, obj.getLgCorreto());
			pstmt.setBytes(5, obj.getBlbImagem());
			
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
	public int update(Resposta obj) {
		return update(obj, null);
	}
	
	public int update(Resposta obj, Connection connection) {
		boolean isConnectionNull = connection==null;
		try {
			if(isConnectionNull) {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false);
			}
			
			PreparedStatement pstmt = connection.prepareStatement("UPDATE resposta (cd_pergunta, cd_resposta, txt_resposta, lg_correto, blb_imagem)"
																+ " VALUES (?, ?, ?, ?, ?)");
			pstmt.setInt(1, obj.getCdPergunta());
			pstmt.setInt(2, obj.getCdResposta());
			pstmt.setString(3, obj.getTxtResposta());
			pstmt.setInt(4, obj.getLgCorreto());
			pstmt.setBytes(5, obj.getBlbImagem());
			
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
	public int delete(Resposta obj) {
		return delete(obj, null);
	}
	
	public int delete(Resposta obj, Connection connection) {
		boolean isConnectionNull = connection==null;
		try {
			if(isConnectionNull) {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false);
			}
			
			PreparedStatement pstmt = connection.prepareStatement("DELETE FROM resposta WHERE cd_pergunta=? AND cd_resposta=?");
			pstmt.setInt(1, obj.getCdPergunta());
			pstmt.setInt(2, obj.getCdResposta());
			
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
			
			PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM resposta "
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

	public Resposta get(int cdPergunta, int cdResposta) {
		return get(cdPergunta, cdResposta, null);
	}
	
	public Resposta get(int cdPergunta, int cdResposta, Connection connection) {
		boolean isConnectionNull = connection==null;
		try {
			if(isConnectionNull) {
				connection = Conexao.getConnection();
			}
			
			ResultSet rs = select(" AND cd_pergunta="+cdPergunta+
								  " AND cd_resposta="+cdResposta, connection);

			Resposta resposta = null;
			if(rs.next()) {
				resposta = new Resposta(rs.getInt("cd_pergunta"), rs.getInt("cd_resposta"), rs.getString("txt_resposta"), rs.getInt("lg_correto"), rs.getBytes("blb_imagem"));
			}
			
			return resposta;
			
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
