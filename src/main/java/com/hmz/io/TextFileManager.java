package com.hmz.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

public class TextFileManager implements FileManager {

	public void write(File file, Object data) throws Exception {
		// WRITING DATA TO THE FILE
		PrintWriter writer = new PrintWriter(file);
		writer.write(data.toString());
		writer.close();
	}
	
	public void append(File file, Object data) throws Exception {
		// ADDING DATA TO THE FILE
		try {
			write(file, read(file).toString().trim() + data);
		} catch (FileNotFoundException e) {
			// IF THE FILE IS NOT EXIST THEN CREATE A VOID FILE AND APEND DATA AGAIN
			write(file, "");
			append(file, data);
		}
	}

	public Object read(File file) throws Exception {
		Object ret = null;
		// READING DATA FROM THE FILE
		FileReader reader = new FileReader(file);
		int c;
		String data = "";
		while((c = reader.read()) != EOF) data += (char)c;
		ret = data;
		reader.close();
		return ret;
	}

	public void copy(File dest, File src) throws Exception {
		write(dest, read(src));
	}

}
