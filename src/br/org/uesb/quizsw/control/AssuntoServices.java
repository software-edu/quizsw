package br.org.uesb.quizsw.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import br.org.uesb.quizsw.util.Conexao;
import br.org.uesb.quizsw.util.Result;
import br.org.uesb.quizsw.util.Service;
import br.org.uesb.quizsw.util.Util;

public class AssuntoServices implements Service<Assunto> {

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
			
			Assunto assunto = (Assunto)content.get("assunto");
			if(assunto==null)
				return new Result(-1, "Erro ao salvar. Assunto é nulo.", null);
			
			int r;
			if(assunto.getCdAssunto()==0) {
				r = new AssuntoDAO().insert(assunto, connection);
				assunto.setCdAssunto(r);
			}
			else
				r = new AssuntoDAO().update(assunto, connection);
			
			if(r<=0) {
				Conexao.rollback(connection);
				return new Result(r, "Erro ao salvar assunto.", null);
			}
			else if (isConnectionNull)
				connection.commit();
			
			content = new HashMap<>();
			content.put("assunto", assunto);
			
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
	public Result remove(Assunto object, boolean cascade) {
		return remove(object, cascade, null);
	}
	
	public Result remove(Assunto object, boolean cascade, Connection connection) {
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
				retorno = new AssuntoDAO().delete(object, connection);
			
			if(retorno<=0){
				Conexao.rollback(connection);
				return new Result(-2, "Erro ao excluir assunto", null);
			}
			else if (isConnectionNull)
				connection.commit();
			
			return new Result(1, "Assunto excluído com sucesso.", null);
		}
		catch(Exception e){
			e.printStackTrace();
			if (isConnectionNull)
				Conexao.rollback(connection);
			return new Result(-1, "Erro ao excluir assunto!", null);
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
					"SELECT A.*, B.nm_assunto AS nm_assunto_superior "
					+ " FROM assunto A "
					+ " LEFT OUTER JOIN assunto B ON (A.cd_assunto_superior = B.cd_assunto)"
					+ " WHERE 1=1 "
					+ (criterios!=null ? criterios : "")
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
	
	public ArrayList<HashMap<String, Object>> getAllBySuperior(int cdAssuntoSuperior) {
		return getAllBySuperior(cdAssuntoSuperior, null);
	}
	
	public ArrayList<HashMap<String, Object>> getAllBySuperior(int cdAssuntoSuperior, Connection connection) {
		String ctr = "AND A.cd_assunto_superior"+(cdAssuntoSuperior==0 ? " IS NULL" : "="+cdAssuntoSuperior);
		
		return find(ctr, connection);
	}
	
	public Assunto getSuperior(int cdAssunto) {
		return getSuperior(cdAssunto, null);
	}
	
	public Assunto getSuperior(int cdAssunto, Connection connection) {
		String ctr = "AND A.cd_assunto="+cdAssunto;
		
		ArrayList<HashMap<String, Object>> r = find(ctr, connection);
		if(r.isEmpty())
			return null;
		
		if(r.get(0).get("cd_assunto_superior")==null || (int)r.get(0).get("cd_assunto_superior")==0)
			return null;
		else
			return new AssuntoDAO().get((int)r.get(0).get("cd_assunto_superior"), connection);
	}
	
	public ArrayList<HashMap<String, Object>> getAll(boolean hierarquia) {
		if(hierarquia) {
			ArrayList<HashMap<String, Object>> list =  getAll(null);
			
			for (HashMap<String, Object> hashMap : list) {
				int level = this.getLevel((int)hashMap.get("cd_assunto"));
				
				String space = "";
				for(int i=0; i<level; i++)
					space += "---";
				
				String value = space + (String)hashMap.get("nm_assunto");
				hashMap.put("ds_assunto", value);
			}
			
			return list;
		}
		else
			return getAll();
	}
	
	private int getLevel(int cdAssunto) {
		int level = 0;
		
		while(this.getSuperior(cdAssunto)!=null){
			level++;
			cdAssunto = this.getSuperior(cdAssunto).getCdAssuntoSuperior();
		}
		
		return level++;
	}
}
