package com.hmz.main;

import com.hmz.dao.MySQLSIDDAO;
import com.hmz.dao.SIDDAO;
import com.hmz.dao.repository.MemoryRepository;
import com.hmz.dao.repository.Repository;
import com.hmz.models.User;
import com.hmz.test.Tester;

import java.util.HashMap;
import java.util.Map;

class DAOTester extends Tester {

	public DAOTester(int testIndex) {
		super(testIndex);
	}
	
	void test1() {
		SIDDAO<User> dao = new MySQLSIDDAO<>(User.class, "hmzdbtest");
		/*
		 * List<User> users = new Vecrray<User>( new User(1, "hamza", "hamza@"), new
		 * User(2, "said", "said@"), new User(3, "khalid", "khalid@"), new User(4,
		 * "majid", "majid@") );
		 */
		dao.delete(4);
		System.out.println(dao.select());
	}

	void test2() {
		System.out.println("Testing new repository");
		Map<Integer, User> users = new HashMap<>();
		users.put(1, new User(1, "hamza", "h@"));
		users.put(2, new User(2, "said", "h@"));
		users.put(3, new User(3, "rachid", "h@"));
		users.put(4, new User(4, "khalid", "h@"));
		Repository<User> userRepository = new MemoryRepository<>(users);
		System.out.println(userRepository.findAll());
	}

}
