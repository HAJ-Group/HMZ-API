package com.hmz.io;

import java.io.File;

public interface FileManager {
	final int EOF = -1;
	
	void write(File file, Object data) throws Exception;
	void append(File file, Object data) throws Exception;
	Object read(File file) throws Exception;
	void copy(File dest, File src) throws Exception;
}
