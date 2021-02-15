package com.hmz.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


class Test {

	public static final int EOF = -1;
	
	public Test() {
		try {
			exp15();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void exp01() {
		System.out.println("FILE OBJECT MANIPULATION\n"); // DESCRIPTION
		File file = new File("test.txt");
		// SHOW INFORMATIONS
		if(file.exists()) {
			System.out.println("Absolut Path = " + file.getAbsolutePath());
			System.out.println("Path = " + file.getPath());
			System.out.println("Filename = " + file.getName());
			System.out.println("Is it a directory ? = " + file.isDirectory());
			System.out.println("Is it a normal file ? = " + file.isFile());
			System.out.println("Is it hidden ? = " + file.isHidden());
		} else {
			System.err.println("File not exists :(");
		}
	}
	
	void exp02() throws Exception {
		System.out.println("FILE INPUT AND OUTPUT OPERATIONS\n"); // DESCRIPTION
		// SETTING UP INPUT AND OUTPUT OBJECTS
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			// STARING OPERATIONS
			in = new FileInputStream(new File("test.txt"));
			out = new FileOutputStream(new File("out.txt"));
			// BUFFER READER
			byte [] buffer = new byte[8];
			int c;
			// READING DATA
			while((c = in.read(buffer)) != EOF) {
				// WRITING DATA
				out.write(buffer);
				// DISPLAYING DATA
				System.out.print("INTEGER["+c+"] =>");
				for(byte b : buffer) System.out.println("NUMERIC = " + b + " CHARACTER = " + (char)b);
				System.out.println("");
				// REINITIALIZING BUFFER
				buffer = new byte[8];
			}
			System.out.println("DATA IS COPIED TO THE FILE");
		} finally {
			// CLOSING OBJECTS AFTER FINNISHING OPERTAIONS
			if(in != null) in.close();
			if(out != null) out.close();
		}
	}
	
	void exp03() throws Exception {
		System.out.println("WRITE DATA TO THE FILE\n"); // DESCRIPTION
		FileOutputStream out = null;
		try {
			String data = "there is a lot to say";
			out = new FileOutputStream(new File("test.txt"));
			// BUFFER
			byte[] buffer = data.getBytes();
			out.write(buffer);
			System.out.println("Data Successfully written");
		} finally {
			if(out != null) out.close();
		}
	}
	
	void exp04() throws Exception {
		System.out.println("DISPALY DATA FROM THE FILE\n"); // DESCRIPTION
		FileInputStream in = null;
		try {
			in = new FileInputStream(new File("test.txt"));
			// BUFFER
			byte[] buffer = new byte[10];
			while(in.read(buffer) != EOF) {
				for(byte b : buffer) System.out.print((char)b);
				buffer = new byte[10];
			}
		} finally {
			if(in != null) in.close();
		}
	}
	
	void exp05() throws Exception {
		System.out.println("FILE INPUT STREAM VS BUFFERED INPUT STREAM\n"); // DESCRIPTION
		// READING
		FileInputStream fis = null;
		BufferedInputStream bis =  null;
		long start_time = 0;
		try {
			fis = new FileInputStream(new File("dictionnaire.txt"));
			bis = new BufferedInputStream(new FileInputStream(new File("test.txt")));
			// TIMING FOR THE FILE INPUT STREAM
			byte [] buffer = new byte[8];
			start_time = System.currentTimeMillis();
			while(fis.read(buffer) != EOF);
			System.out.println(System.currentTimeMillis() - start_time);
			// TIMING FOR THE BUFFERED INPUT STREAM
			start_time = System.currentTimeMillis();
			while(bis.read(buffer) != EOF);
			System.out.println(System.currentTimeMillis() - start_time);
			// ENDED
			System.out.println("ENDED");
		} finally {
			if(fis != null) fis.close();
			if(bis != null) bis.close();
		}
	}
	
	void exp06() throws Exception {
		System.out.println("FILE INPUT STREAM VS BUFFERED INPUT STREAM\n"); // DESCRIPTION
		// WRITING
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		try {
			// SOURCE
			fis = new FileInputStream(new File("dictionnaire.txt"));
			bis = new BufferedInputStream(new FileInputStream(new File("dictionnaire.txt")));
			// DESTINATION
			fos = new FileOutputStream(new File("out.txt"));
			bos = new BufferedOutputStream(new FileOutputStream(new File("out.txt")));
			// BUFFER
			byte [] buffer = new byte[10];
			long start_time = System.currentTimeMillis();
			// READING AND WRITING FILE INPUT STREAM
			while(fis.read(buffer) != EOF) fos.write(buffer);
			System.out.println(System.currentTimeMillis() - start_time);
			start_time = System.currentTimeMillis();
			// READING AND WRITING BUFFERED INPUT STREAM
			while(bis.read(buffer) != EOF) bos.write(buffer);
			System.out.println(System.currentTimeMillis() - start_time);
			System.out.println("ENDED");
		} finally {
			if(fis != null) fis.close();
			if(bis != null) bis.close();
			if(fos != null) fos.close();
			if(bos != null) bos.close();
		}
	}
	
	void exp07() throws Exception {
		System.out.println("DATA INPUT STREAM\n"); // DESCRIPTION
		DataInputStream dis = null;
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("test.txt"))));
			// TESTING PRIMITIF DATA OPERATIONS
			// WRITING
			//dos.writeBoolean(true);
			//dos.writeInt(1265);
			//dos.writeChars("hello everyone");
			dos.writeUTF("now you see");
			dos.close();
			// READING
			dis = new DataInputStream(new BufferedInputStream(new FileInputStream(new File("test.txt"))));
			//System.out.println(dis.readBoolean());
			//System.out.println(dis.readInt());
			//System.out.println(dis.readChar()); 
			//System.out.println(dis.readChar());
			//System.out.println(dis.readChar());
			//System.out.println(dis.readChar());
			//System.out.println(dis.readChar());
			System.out.println(dis.readUTF());
			dis.close();
		} finally {}
	}
	
	void exp08() throws Exception {
		System.out.println("OBJECT INPUT/OUTPUT STREAMS SERIALISATION\n"); // DESCRIPTION
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("test.txt"))));
			// WRITING OBJECTS SERIALIZING
			oos.writeObject(new Game("tomb raider", "action", 120));
			oos.writeObject(new Game("far cry", "adventure", 900));
			oos.writeObject(new Game("naruto", "anime", 600));
			oos.close();
			// READING OBJECTS DESERIALIZING
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("test.txt"))));
			System.out.println((Game)ois.readObject());
			System.out.println((Game)ois.readObject());
			System.out.println((Game)ois.readObject());
			ois.close();
		} finally {}
	}
	
	void exp09() throws Exception {
		System.out.println("CHAR ARRAY WRITER/READER\n"); // DESCRIPTION
		// WRITING
		CharArrayWriter writer = new CharArrayWriter();
		writer.write("hello world");
		// READING
		CharArrayReader reader = new CharArrayReader(writer.toCharArray());
		int c;
		while((c = reader.read()) != EOF) {
			System.out.print((char)c);
		}
	}
	
	void exp10() throws Exception {
		System.out.println("STRING WRITER/READER\n"); // DESCRIPTION
		// WRITING
		StringWriter writer = new StringWriter();
		writer.write("hello world");
		// READING
		StringReader reader = new StringReader(writer.toString());
		int c;
		while((c = reader.read()) != EOF) {
			System.out.print((char)c);
		}
	}
	
	void exp11() throws Exception {
		System.out.println("FILE WRITER/READER I/O FOR TEXT FILES\n"); // DESCRIPTION
		// WRITING
		FileWriter fw = new FileWriter(new File("out.txt"));
		fw.write("hello world");
		fw.close();
		// READING
		FileReader fr = new FileReader(new File("out.txt"));
		int c;
		while((c = fr.read()) != EOF) System.out.print((char)c);
		fr.close();
	}
	
	void exp12() throws Exception {
		System.out.println("PRINT WRITER/READER I/O FOR TEXT FILES\n"); // DESCRIPTION
		// WRITING
		PrintWriter pw = new PrintWriter(new File("out.txt"));
		pw.write("now you'll see");
		pw.close();
		// READING
		FileReader pr = new FileReader(new File("out.txt"));
		int c;
		while((c = pr.read()) != EOF) System.out.print((char)c);
		pr.close();
	}
	
	void exp13() throws Exception {
		System.out.println("CLASSES NEW I/O VS BUFFERED STREAMS\n"); // DESCRIPTION
		BufferedInputStream bis = null;
		FileInputStream fis = null;
		FileChannel ch = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(new File("dictionnaire.txt")));
			long star_time = System.currentTimeMillis();
			// BUFFRERED READING
			while(bis.read() != EOF);
			System.out.println(System.currentTimeMillis() - star_time);
			// CHANNEL READING
			fis = new FileInputStream(new File("dictionnaire.txt"));
			ch = fis.getChannel();
			int size = (int)ch.size();
			ByteBuffer bbuffer = ByteBuffer.allocate(size);
			star_time = System.currentTimeMillis();
			ch.read(bbuffer);
			bbuffer.flip();
			System.out.println(System.currentTimeMillis() -  star_time);
			System.out.println("ENDED");
		} finally {
			if(bis != null) bis.close();
			if(fis != null) fis.close();
		}
	}
	
	void exp14() throws Exception {
		System.out.println("CLASSES NEW I/O 2 AT COPYING FILES\n"); // DESCRIPTION
		Path file = Paths.get("test.txt");
		// READING
		ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(file)));
		System.out.println((Game)is.readObject());
		is.close();
		// Copying
		Path dest = Paths.get("out.txt");
		Files.copy(file, dest, StandardCopyOption.REPLACE_EXISTING);
	}
	
	void exp15() throws Exception {
		File file = new File("out.txt");
		FileManager manager = new BinaryFileManager();
		manager.write(file, "hello everyone");
		manager.append(file, " today we'll talk about");
		System.out.println(manager.read(new File("out.txt")));
	}
	
	public static void main(String[] args) {
		new Test();
	}
}
