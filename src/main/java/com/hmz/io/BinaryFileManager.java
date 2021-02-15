package com.hmz.io;

import java.io.*;

public class BinaryFileManager implements FileManager {

	public void write(File file, Object data) throws Exception {
		// WRITING DATA
		BufferedOutputStream writer = null;
		try {
			writer = new BufferedOutputStream(new FileOutputStream(file));
			byte [] buffer = data.toString().getBytes();
			writer.write(buffer);
		} finally {
			writer.close();
		}
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
		// READING DATA
		BufferedInputStream reader = null;
		try {
			reader = new BufferedInputStream(new FileInputStream(file));
			byte [] buffer = new byte[10];
			String data = "";
			while(reader.read(buffer) != EOF) {
				for(byte b: buffer) data += (char)b;
				buffer = new byte[10];
			}
			ret = data;
		} finally {
			reader.close();
		}
		return ret;
	}
	
	public void copy(File dest, File src) throws Exception {
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(src));
			out = new BufferedOutputStream(new FileOutputStream(dest));
			byte[] buffer = new byte[10];
			int length;
			while((length = in.read(buffer)) != EOF) {
				out.write(buffer, 0, length);
				buffer = new byte[10];
			}
		} finally {
			if(in != null) in.close();
			if(out != null) out.close();
		}
	}
}
