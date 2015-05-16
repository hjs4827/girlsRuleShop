package com.jshan.girlsRule.dao;

import java.util.List;

public interface BaseDao {
	/**
	 * Get list without parameters
	 * 
	 * @param statementName
	 * @return List
	 */
	@SuppressWarnings("rawtypes")
	public List getList(String statementName);
	
	/**
	 * Get list with parameter
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @return List
	 */
	@SuppressWarnings("rawtypes")
	public List getList(String statementName, Object parameterObject);
	
	/**
	 * Get single row without parameters
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @return Object
	 */
	public Object getSingleRow(String statementName);
	
	/**
	 * Get single row
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @return Object
	 */
	public Object getSingleRow(String statementName, Object parameterObject);
	
	/**
	 * Count total count without parameters
	 * 
	 * @param statementName
	 * @return int
	 */
	public int getTotalCnt(String statementName);
	
	/**
	 * Count total count
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @return int
	 */
	public int getTotalCnt(String statementName, Object parameterObject);
	
	/**
	 * Insert without return value
	 * 
	 * @param statementName
	 */
	public void insert(String statementName);
	
	/**
	 * Insert with parameter
	 * 
	 * @param statementName
	 * @param parameterObject
	 */
	public void insert(String statementName, Object parameterObject);
	
	/**
	 * Insert with return value of insert id
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @return int
	 */
	public int insertWithSeqNo(String statementName, Object parameterObject);
	
	/**
	 * Update without update count
	 * 
	 * @param statementName
	 * @param parameterObject
	 *            void
	 */
	public void update(String statementName, Object parameterObject);
	
	/**
	 * Update with return number of updated count
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @return int
	 */
	public int updateWithNo(String statementName, Object parameterObject);
	
	/**
	 * Delete
	 * 
	 * @param statementName
	 * @param parameterObject
	 */
	public void delete(String statementName, Object parameterObject);
	
	/**
	 * delete with return number of delete count
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @return int
	 */
	public int deleteWithNo(String statementName, Object parameterObject);
	
	/**
	 * delete with return number of delete count
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @return int
	 */
	public Object getSalesScheduleCntByProduct(String statementName, Object parameterObject);
}
