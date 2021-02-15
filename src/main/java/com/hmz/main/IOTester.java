package com.hmz.main;

import com.hmz.io.BinaryFileManager;
import com.hmz.io.TextFileManager;
import com.hmz.io.dev.FileManager;
import com.hmz.test.Tester;

import java.io.File;


class IOTester extends Tester {

	public static final String PATH = "resources/test.txt";
	
	public IOTester(int testIndex) {
		super(testIndex);
	}
	
	void test1() {
		//File file = new File(PATH);
		FileManager file = new FileManager(PATH);
		BinaryFileManager m = new BinaryFileManager();
		try {
			m.write(file, "Hello World");
			file.write("A NEW DATA");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void test2() {
		//File file = new File(PATH);
		FileManager file = new FileManager(PATH);
		BinaryFileManager m = new BinaryFileManager();
		try {
			m.append(file, " Ismaili Alaoui");
			file.append(" Hamza");
			//System.out.println(m.read(file));
			System.out.println(file.read());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void test3() {
		//File file = new File(PATH);
		FileManager file = new FileManager(PATH);
		TextFileManager m = new TextFileManager();
		try {
			m.write(file, "Hello World");
			file.write("A NEW DATA");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void test4() {
		File file = new File(PATH);
		TextFileManager m = new TextFileManager();
		try {
			m.append(file, " hahahah");
			System.out.println(m.read(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void test5() {
		//File dest = new File("resources/test2.txt");
		FileManager src = new FileManager(PATH);
		FileManager dest = new FileManager("resources/test2.txt");
		BinaryFileManager m = new BinaryFileManager();
		try {
			m.copy(dest, src);
			//dest.copy(src);
			//System.out.println(m.read(dest));
			//dest.write("HEY EVERYONE");
			dest.copy(src, false);
			System.out.println(dest.read());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void test6() {
		File dest = new File("resources/test2.txt");
		File src = new File(PATH);
		TextFileManager m = new TextFileManager();
		try {
			//m.copy(dest, src);
			m.write(src, "HAMZA");
			FileManager.copy(dest, src);
			//System.out.println(m.read(dest));
			System.out.println(new FileManager(dest).read());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void test7() {
		File f = new File(PATH);
		File t = new File(f, "");
		BinaryFileManager m = new BinaryFileManager();
		try {
			m.write(t, "test");
			System.out.println(t.getAbsolutePath());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
