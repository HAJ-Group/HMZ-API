package com.hmz.main;

import com.hmz.jdbc.DBManager;
import com.hmz.test.Tester;

import java.sql.ResultSet;

class JDBCTester extends Tester {

	public JDBCTester(int testIndex) {
		super(testIndex);
	}
	
	void test1() {
		DBManager dbm = new DBManager("hmz_jee", "root", "");
		dbm.connect();
		try {
			ResultSet result = dbm.selectAllFrom("user");
			while(result.next()) {
				System.out.println(result.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.closeConnection();
		}
	}

}
