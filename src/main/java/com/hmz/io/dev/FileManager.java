package com.hmz.io.dev;

import java.io.*;

/** FILE OBJECT WITH READ WRITE COPY SERVICES
 * 
 * @author hamza
 *
 */
public class FileManager extends File {
	
	private static final long serialVersionUID = 1L;
	
	public static final int EOF = -1;
	public static final int MAX_BUFFER_SIZE = 10*1024; // 10KO
	public static final int BUFFER_ROOM = 10;
	
	private BufferedInputStream in =  null;
	private BufferedOutputStream out = null;
	
	public FileManager(String parent) {
		super(parent);
	}
	
	public FileManager(String parent, String child) {
		super(parent, child);
	}
	
	
	public FileManager(File file) {
		super(file, "");
	}

	// TEXT READ AND WRITE
	// WRITE TEXT DATA TO THIS FILE
	/** WRITE TEXT DATA TO THIS FILE
	 * 
	 * @param data
	 * @throws Exception
	 */
	public void write(String data) throws Exception {
		try {
			out = new BufferedOutputStream(new FileOutputStream(this), MAX_BUFFER_SIZE);
			byte[] data_bytes = data.getBytes();
			out.write(data_bytes, 0, data_bytes.length);
		} finally {
			if(out != null) out.close();
		} 
	}
	
	// READ TEXT DATA FROM THIS FILE
	/** READ TEXT DATA FROM THIS FILE
	 * 
	 * @return
	 * @throws Exception
	 */
	public String read() throws Exception {
		String ret = "";
		try {
			in = new BufferedInputStream(new FileInputStream(this), MAX_BUFFER_SIZE);
			byte[] data_bytes = new byte[BUFFER_ROOM];
			while(in.read(data_bytes) != EOF) {
				for(byte b : data_bytes) ret += (char)b;
				data_bytes = new byte[BUFFER_ROOM];
			}
		} finally {
			if(in != null) in.close();
		}
		return ret.trim();
	}
	
	// APPEND TEXT DATA TO THIS FILE
	/** APPEND TEXT DATA TO THIS FILE
	 * 
	 * @param data
	 * @throws Exception
	 */
	public void append(String data) throws Exception {
		try {
			write(read() + data);
		} catch (FileNotFoundException e) {
			// IF THE FILE IS NOT EXIST THEN CREATE A VOID FILE AND APEND DATA AGAIN
			write("");
			append(data);
		}
	}
	
	/** DEFAULT SOURCED COPY METHOD
	 * 
	 * @param file
	 */
	public void copy(File file) throws Exception {
		copy(file, true);
	}
	
	/** COPYING WITH FILE PARAMETERS
	 * 
	 * @param file
	 * @param isSource IF TRUE THEN THE PARAMETER FILE IS A SOURCE OTHERWISE IT IS A DESTINATION
	 */
	public void copy(File file, boolean isSource) throws Exception {
		if(isSource) {
			FileManager src = new FileManager(file);
			copy(src.getIn());
		} else {
			FileManager dest = new FileManager(file);
			copy(dest.getOut());
		}
	}
	
	// DATA COPYING CONTENTS
	// COPY DATA FROM A PARAM SOURCE TO THIS FILE
	/** COPY DATA FROM A PARAM SOURCE TO THIS FILE
	 * 
	 * @param is
	 * @throws Exception
	 */
	public void copy(InputStream is) throws Exception {
		try {
			in = new BufferedInputStream(is, MAX_BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(this), MAX_BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_ROOM];
			int length;
			while((length = in.read(buffer)) != EOF) {
				out.write(buffer, 0, length);
				buffer = new byte[BUFFER_ROOM];
			}
		} finally {
			if(in != null) in.close();
			if(out != null) out.close();
		}
	}
	
	// COPY DATA FROM THIS FILE TO A PARAM SOURCE
	/** COPY DATA FROM THIS FILE TO A PARAM SOURCE
	 * 
	 * @param os
	 * @throws Exception
	 */
	public void copy(OutputStream os) throws Exception {
		try {
			in = new BufferedInputStream(new FileInputStream(this), MAX_BUFFER_SIZE);
			out = new BufferedOutputStream(os, MAX_BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_ROOM];
			int length;
			while((length = in.read(buffer)) != EOF) {
				out.write(buffer, 0, length);
				buffer = new byte[BUFFER_ROOM];
			}
		} finally {
			if(in != null) in.close();
			if(out != null) out.close();
		}
	}
	
	// GET THIS FILE INPUT STREAM
	/** GET THIS FILE INPUT STREAM
	 * 
	 * @return
	 * @throws Exception
	 */
	public InputStream getIn() throws Exception {
		return new FileInputStream(this);
	}
	
	// GET THIS FILE OUTPUT STREAM
	/** GET THIS FILE OUTPUT STREAM
	 * 
	 * @return
	 * @throws Exception
	 */
	public OutputStream getOut() throws Exception {
		return new FileOutputStream(this);
	}
	
	// COPY DATA FROM A PARAM SOURCE TO ANOTHER PARAM SOURCE
	/** COPY DATA FROM A PARAM SOURCE TO ANOTHER PARAM SOURCE
	 * 
	 * @param is
	 * @param os
	 * @throws Exception
	 */
	public static void copy(OutputStream os, InputStream is) throws Exception {
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			in = new BufferedInputStream(is, MAX_BUFFER_SIZE);
			out = new BufferedOutputStream(os, MAX_BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_ROOM];
			int length;
			while((length = in.read(buffer)) != EOF) {
				out.write(buffer, 0, length);
				buffer = new byte[BUFFER_ROOM];
			}
		} finally {
			if(in != null) in.close();
			if(out != null) out.close();
		}
	}
	
	public static void copy(File dest, File src) throws Exception {
		FileManager fdest = new FileManager(dest);
		FileManager fsrc = new FileManager(src);
		copy(fdest.getOut(), fsrc.getIn());
	}
	
	// GET THE FILE EXTENSION
	/** GET THE FILE EXTENSION
	 * 
	 * @param filename
	 * @return
	 */
	public static String getExtension(String filename) {
		String ret = null;
		if(!filename.equals(null) && !filename.isEmpty()) {
			ret = filename.substring(filename.lastIndexOf(".") + 1).trim();
		}
		return ret;
	}
	
}

