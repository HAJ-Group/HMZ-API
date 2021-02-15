package com.hmz.dao;

import com.hmz.collections.Vecrray;
import com.hmz.dao.annotations.FieldIgnore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.List;

/**
 * 
 * @author hamza
 *
 * @param <M>
 */
public class FlatFileLSDAO<M> implements LSDAO<M> {
	
	private final Class<M> model;
	private final String path;
	private final String source;
	
	public FlatFileLSDAO(Class<M> model, String path, String source) {
		if(!path.endsWith("/")) path += "/";
		this.model = model;
		this.path = path;
		this.source = source;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<M> load() {
		List<M> ret = new Vecrray<>();
		File filepath = new File(path, source);
		if(filepath.exists()) {
			try {
				BufferedReader in = new BufferedReader(new FileReader(filepath));
				String line;
				while ((line = in.readLine()) != null && !line.isEmpty()) {
					// SPLIT LINE VALUS INTO ARRAY
					String [] values = line.split(",");
					// CREATE MODEL INSTANCE TO STORE
					M instance = model.getDeclaredConstructor().newInstance();
					int i = 0;
					for(Field field : model.getDeclaredFields()) {
						if(!field.isAnnotationPresent(FieldIgnore.class)) {
							// OPEN ACCESS
							field.setAccessible(true);
							// SETTING FIELD VALUES
							if ("int".equals(field.getType().getName())) {
								field.setInt(instance, Integer.parseInt(values[i++]));
							} else if ("double".equals(field.getType().getName())) {
								field.setDouble(instance, Double.parseDouble(values[i++]));
							} else {
								field.set(instance, values[i++]);
							}
							// CLOSE ACCESS
							field.setAccessible(false);
						}
					}
					ret.add(instance);
				}
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	@Override
	public void save(List<M> data) {
		if(data != null && !data.isEmpty()) {
			try {
				File filepath = new File(path, source);
				PrintWriter out = new PrintWriter(filepath);
				for(M m : data) {
					StringBuilder line = new StringBuilder();
					for(Field field : model.getDeclaredFields()) {
						if(!field.isAnnotationPresent(FieldIgnore.class)) {
							// OPEN ACCESS
							field.setAccessible(true);
							// GETTING FIELD VALUES
							line.append(",").append(field.get(m));
							// CLOSE ACCESS
							field.setAccessible(false);
						}
					}
					out.println(line.substring(1));
				}
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
