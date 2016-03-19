package br.org.uesb.quizsw.control;

import java.sql.ResultSet;

import br.org.uesb.quizsw.util.DAO;

public class QuizDAO implements DAO<Quiz> {

	@Override
	public int insert(Quiz obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Quiz obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Quiz obj) {
		// TODO Auto-generated method stub
		return 0;
	}	

	@Override
	public ResultSet select(String criterias) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet selectAll() {
		return select(null);
	}


}
