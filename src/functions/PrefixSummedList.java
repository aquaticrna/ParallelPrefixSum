package functions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class PrefixSummedList implements List<Integer> {
	int size;
	
	public PrefixSummedList(int size){
		this.size = size;
	}

	@Override
	public boolean add(Integer arg0) {
		//values cannot be added
		return false;
	}

	@Override
	public void add(int arg0, Integer arg1) {
		//values cannot be changed
		
	}

	@Override
	public boolean addAll(Collection<? extends Integer> arg0) {
		//values cannot be added
		return false;
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends Integer> arg1) {
		//values cannot be added
		return false;
	}

	@Override
	public void clear() {
		size = 0;
		
	}

	@Override
	public boolean contains(Object arg0) {
		if(!Number.class.isAssignableFrom(arg0.getClass())){
			return false;
		}
		double val = ((Number) arg0).doubleValue();
		int root = (int) (Math.sqrt(8*val+1)-1)/2;
		if(val%root!=0){
			return false;
		}
		return true;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		for(Object arg : arg0){
			if(!contains(arg)){
				return false;
			}
		}
		return true;
		
	}

	@Override
	public Integer get(int arg0) {
		return (arg0*(arg0+1))/2;
	}

	@Override
	public int indexOf(Object arg0) {
		if(!Number.class.isAssignableFrom(arg0.getClass())){
			return -1;
		}
		double val = ((Number) arg0).doubleValue();
		int root = (int) (Math.sqrt(8*val+1)-1)/2;
		if(val%root==0){
			return (int) root;
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		if(size!=0){
			return false;
		}
		return true;
	}

	@Override
	public Iterator<Integer> iterator() {
		//don't want to spend the time yet
		return null;
	}

	@Override
	public int lastIndexOf(Object arg0) {
		return indexOf(arg0);
	}

	@Override
	public ListIterator<Integer> listIterator() {
		//don't want to spend the time yet
		return null;
	}

	@Override
	public ListIterator<Integer> listIterator(int arg0) {
		//don't want to spend the time yet
		return null;
	}

	@Override
	public boolean remove(Object arg0) {
		//values can't be removed
		return false;
	}

	@Override
	public Integer remove(int arg0) {
		//values can't be removed
		return null;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		//values can't be removed
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		//values can't be removed
		return false;
	}

	@Override
	public Integer set(int arg0, Integer arg1) {
		//values can't be changed
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public List<Integer> subList(int arg0, int arg1) {
		List<Integer> list = new ArrayList<Integer>(arg1-arg0);
		for(int x=arg0;x<arg1;x++){
			list.add(get(x));
		}
		return list;
	}

	@Override
	public Object[] toArray() {
		Integer[] array = new Integer[size];
		for(int x=0;x<size;x++){
			array[x] = get(x);
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] arg0) {
		for(int x = 0;x<arg0.length;x++){
			arg0[x] = (T) get(x);
		}
		return arg0;
	}
}
