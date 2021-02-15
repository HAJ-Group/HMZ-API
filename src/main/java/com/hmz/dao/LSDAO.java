package com.hmz.dao;

import java.util.List;

/**
 * Generic Data Access Object Interface
 * @author hamza
 *
 * @param <M>
 */
public interface LSDAO<M> {
	
	/**
	 * Load data from a specific source
	 * @return
	 */
	List<M> load();
	/**
	 * Save data to a specific source
	 * @param data
	 */
	void save(List<M> data);
	
}
