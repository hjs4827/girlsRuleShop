package com.jshan.girlsRule.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("baseDao")
public class BaseDaoImpl implements BaseDao {
	
	@Autowired
	@Resource(name="sqlSession")
	private SqlSession sqlSession;

	@SuppressWarnings("rawtypes")
	@Override
	public List getList(String statementName) {
		return sqlSession.selectList(statementName);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getList(String statementName, Object parameterObject) {
		return sqlSession.selectList(statementName, parameterObject);
	}

	@Override
	public Object getSingleRow(String statementName) {
		return sqlSession.selectOne(statementName);
	}

	@Override
	public Object getSingleRow(String statementName, Object parameterObject) {
		return sqlSession.selectOne(statementName, parameterObject);
	}

	@Override
	public int getTotalCnt(String statementName) {
		int totCnt = (Integer) sqlSession.selectOne(statementName);
		return totCnt;
	}

	@Override
	public int getTotalCnt(String statementName, Object parameterObject) {
		int totCnt = (Integer) sqlSession.selectOne(statementName, parameterObject);
		return totCnt;
	}

	@Override
	public void insert(String statementName) {
		sqlSession.insert(statementName);
	}

	@Override
	public void insert(String statementName, Object parameterObject) {
		sqlSession.insert(statementName, parameterObject);
	}

	@Override
	public int insertWithSeqNo(String statementName, Object parameterObject) {
		int insertId = 0;
		insertId = sqlSession.insert(statementName, parameterObject);
		return insertId;
	}

	@Override
	public void update(String statementName, Object parameterObject) {
		sqlSession.update(statementName, parameterObject);
	}

	@Override
	public int updateWithNo(String statementName, Object parameterObject) {
		return sqlSession.update(statementName, parameterObject);
	}

	@Override
	public void delete(String statementName, Object parameterObject) {
		sqlSession.delete(statementName, parameterObject);
	}

	@Override
	public int deleteWithNo(String statementName, Object parameterObject) {
		return sqlSession.delete(statementName, parameterObject);
	}

	@Override
	public Object getSalesScheduleCntByProduct(String statementName, Object parameterObject) {
		return sqlSession.selectOne(statementName, parameterObject);
	}
}
