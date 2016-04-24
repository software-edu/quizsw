package br.org.uesb.quizsw.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;

import br.org.uesb.quizsw.util.Conexao;
import br.org.uesb.quizsw.util.Result;
import br.org.uesb.quizsw.util.Service;
import br.org.uesb.quizsw.util.Util;

public class UsuarioServices implements Service<Usuario> {
	
	public static final int TP_PERMISSAO_ADMINISTRADOR = 0;
	public static final int TP_PERMISSAO_OPERADOR = 1;

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
			
			Usuario usuario = (Usuario)content.get("usuario");
			if(usuario==null)
				return new Result(-1, "Erro ao salvar. Usuário é nulo.", null);
			
			if(!this.find("AND nm_login='"+usuario.getNmLogin()+"'").isEmpty()) {
				return new Result(-2, "Já existe usuário com esse login.", null);
			}
			
			int r;
			if(usuario.getCdUsuario()==0) {
				r = new UsuarioDAO().insert(usuario, connection);
				usuario.setCdUsuario(r);
			}
			else
				r = new UsuarioDAO().update(usuario, connection);
			
			if(r<=0) {
				Conexao.rollback(connection);
				return new Result(r, "Erro ao salvar usuário.", null);
			}
			else if (isConnectionNull)
				connection.commit();
			
			content = new HashMap<>();
			content.put("usuario", usuario);
			
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
					+ " FROM usuario A "
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

	@Override
	public Result remove(Usuario object, boolean cascade) {
		return remove(object, cascade, null);
	}
	
	public Result remove(Usuario object, boolean cascade, Connection connection) {
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
				retorno = new UsuarioDAO().delete(object, connection);
			
			if(retorno<=0){
				Conexao.rollback(connection);
				return new Result(-2, "Erro ao excluir usuário", null);
			}
			else if (isConnectionNull)
				connection.commit();
			
			return new Result(1, "Usuário excluído com sucesso.", null);
		}
		catch(Exception e){
			e.printStackTrace();
			if (isConnectionNull)
				Conexao.rollback(connection);
			return new Result(-1, "Erro ao excluir usuário!", null);
		}
		finally{
			if (isConnectionNull)
				Conexao.closeConnection(connection);
		}
	}
	
	public Result login(String user, String pass) {
		Connection connection = null;
		
		try {
			connection = Conexao.getConnection();
			
			if(user.equals("admin") && pass.equals("admin123")) {
				return new Result(2, "Usuário padrão");
			}
			
			String ctr = " AND nm_login LIKE '"+user+"'"
					   + " AND nm_senha LIKE '"+pass+"'";
			
			ArrayList<HashMap<String, Object>> lst = this.find(ctr, connection);
			if(lst.isEmpty())
				return new Result(-2, "Usuário ou senha inválido.");
			
			HashMap<String, Object> objects = new HashMap<>();
			objects.put("tp_permissao", (int)lst.get(0).get("tp_permissao"));
			
			return new Result(1, "", objects);
		}
		catch(Exception e){
			e.printStackTrace();
			Conexao.rollback(connection);
			return new Result(-1, "Erro ao autenticar!", null);
		}
		finally{
			Conexao.closeConnection(connection);
		}
	}

}
