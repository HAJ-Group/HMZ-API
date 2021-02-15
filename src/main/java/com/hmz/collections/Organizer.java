package com.hmz.collections;

import com.hmz.operations.Random;

import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Perform Sorting Randoming Resversing Organising Operations On Collections
 * 
 * @author hamza
 *
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class Organizer<T> extends Vecrray<T> {
	
	private static final long serialVersionUID = 1L;
	private boolean randomize;
	
	// CONSTRUCTORS
	
	public Organizer(T ...items) {
		this(true, items);
	}
	
	public Organizer(boolean randomize, T ...items) {
		super(items);
		this.randomize = randomize;
	}

	public Organizer(Collection<? extends T> collection) {
		this(true, collection);
	}
	
	public Organizer(boolean randomize, Collection<? extends T> collection) {
		super(collection);
		this.randomize = randomize;
	}
	
	// SORTING
	
	public Organizer<T> sort() {
		return sort(false);
	}
	
	public synchronized Organizer<T> sort(boolean desc) {
		Organizer<T> ret = new Organizer<>(this);
		ret.sort(new CollectionComparator<>());
		if(desc) return ret.reverse();
		return ret;
	}
	
	// REVERSING
	
	public synchronized Organizer<T> reverse() {
		Organizer<T> ret = new Organizer<>();
		for (int i = size()-1; i >= 0; i--) {
			ret.add(get(i));
		}
		return ret;
	}
	
	// ORGANIZING
	
	public synchronized Organizer<String> organize(String ...organisations) {
		Organizer<String> ret = new Organizer<>();
		Organizer<Organizer<T>> parts = partitions(organisations.length);
		for (int i = 0; i < organisations.length; i++) {
			ret.add(organisations[i] + " : " + parts.get(i));
		}
		return ret;
	}
	
	// ASSOCIATING
	
	public synchronized Organizer<String> associate(String ...associations){
		Organizer<String> ret = new Organizer<>();
		Organizer<Organizer<T>> parts = partitions(associations.length);
		Organizer<Integer> randomValues = getRandomizedValues(0, associations.length);
		for (int i = 0; i < associations.length; i++) {
			ret.add(parts.get(i) + " Associated to " + associations[randomValues.get(i)]);
		}
		return ret;
	}
	
	public synchronized Organizer<String> associate(Organizer<String> organized, String ...associations){
		Organizer<String> ret = new Organizer<>();
		Organizer<Integer> randomValues = getRandomizedValues(0, associations.length);
		for (int i = 0; i < associations.length; i++) {
			ret.add(organized.get(i) + " Associated to " + associations[randomValues.get(i)]);
		}
		return ret;
	}
	
	public synchronized Organizer<String> associate(String [] associations, String ...organisations){
		Organizer<String> ret = new Organizer<>();
		if(associations.length == organisations.length) {
			Organizer<Organizer<T>> parts = partitions(organisations.length);
			Organizer<Integer> randomValues = getRandomizedValues(0, associations.length);
			for (int i = 0; i < organisations.length; i++) {
				ret.add(organisations[i] + " : " + parts.get(i) + " Associated to " + associations[randomValues.get(i)]);
			}
		}
		return ret;
	}
	
	// PARTITIONS 
	
	public synchronized Organizer<Organizer<T>> partitions(int partitionsNumber){
		if(partitionsNumber == 0 || partitionsNumber > size()) return null;
		Organizer<Organizer<T>> ret = new Organizer<>();
		Organizer<Integer> indices = devide(size(), partitionsNumber);
		Organizer<Integer> valueData = getIntervalValues(0, size());
		if(randomize) valueData = getRandomizedValues(0, size());
		Organizer<T> part = new Organizer<>();
		for (int i = 0, j = 0; i < size() && j < indices.size(); i++) {
			part.add(get(valueData.get(i)));
			if(i == indices.get(j) - 1) {
				j++;
				if(j < indices.size()) {
					indices.set(j, indices.get(j-1) + indices.get(j));
				}
				ret.add(part);
				part = new Organizer<>();
			}
		}
		return ret;
	}
	
	// RANDOMIZING
	
	public synchronized Organizer<T> randomize(){
		Organizer<T> ret = new Organizer<>();
		Organizer<Integer> randomIndices = getRandomizedValues(0, size());
		for (int i = 0; i < size(); i++) {
			T data = get(randomIndices.get(i));
			ret.add(data);
		}
		return ret;
	}
	
	public synchronized T getRandomValue() {
		return get(new Random(0, size()).getIntRandom());
	}
	
	public void setRandomize(boolean randomize) {
		this.randomize = randomize;
	}
	
	public boolean isRandomize() {
		return randomize;
	}
	
	// STATIC METHODS
	
	public static Organizer<Integer> devide(int size, int devisions) {
		Organizer<Integer> ret = new Organizer<>();
		int cal = size;
		cal  /= devisions;
		for (int i = 0; i < devisions; i++) ret.add(cal);
		if(size % devisions != 0) ret.set(ret.size() - 1, ret.get(ret.size() - 1) + (size % devisions));
		return ret;
	}
	
	public static Organizer<Integer> getRandomizedValues(int start, int end) {
		Organizer<Integer> ret = new Organizer<>(); 
		for (int i = start; i < end; i++) {
			int val = ThreadLocalRandom.current().nextInt(start, end);
			if(!ret.contains(val)) {
				ret.add(val);
			} else i--;
		}
		return ret;
	}
	
	public static Organizer<Integer> getIntervalValues(int start, int end) {
		Organizer<Integer> ret = new Organizer<>();
		for (int i = start; i < end; i++) {
			ret.add(i);
		}
		return ret;
	}
	
}
