package com.hmz.dao;

import java.util.List;

/**
 * 
 * @author hamza
 *
 */
public interface SIDDAO<M> {
	
	/**
	 * Select All from the associated table
	 * @return
	 */
	List<M> select();
	/**
	 * Select an object from associated table
	 * @param id
	 * @return
	 */
	M select(int id);
	/**
	 * Insert an object into associated table
	 * @param object
	 * @return
	 */
	int insert(M object);
	int insert(M object, boolean update);
	/**
	 * Delete all from associated table
	 * @return
	 */
	int delete();
	/**
	 * Delete an object from associated table
	 * @param id
	 * @return
	 */
	int delete(int id);
	
}
