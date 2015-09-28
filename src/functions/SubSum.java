package functions;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLongArray;

public class SubSum implements Callable<Integer>{
	AtomicLongArray array;
	int startIndex;
	int endIndex;
	
	//takes in the array to be filled and the indices to fill, only works for sets that are monotonically increasing from 0 to
	//n where n is the length of the array
	public SubSum(AtomicLongArray array,int startIndex, int endIndex){
		this.array = array;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}
	
	@Override
	public Integer call() {
		for(long x=startIndex;x<endIndex;x++){
			long sum = x*(x+1)/2;//triangles the number for the index
			array.set((int) x, sum);//sets the index to the appropriate number
		}
		return 1;
	}

}
