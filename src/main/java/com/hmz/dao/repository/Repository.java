package com.hmz.dao.repository;

import java.util.List;

public interface Repository<T> {

	List<T> findAll();
	T findById(int id);
	T save(T data);
	void deleteAll();
	void deleteById(int id);
	public String getError_message();
	
}
