package com.hmz.main;


class Main  {
	
	public Main() {
		try {
			//new CollectionTester(10);
			//new OperationTester(4);
			//new GuiTester(10);
			//new IOTester(6);
			//new JDBCTester(1);
			//new XMLTester(3);
			new DAOTester(2);
			//new RandomTester(1);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
