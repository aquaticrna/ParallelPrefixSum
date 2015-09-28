package functions;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLongArray;

public class SubSum implements Callable<Integer>{
	AtomicLongArray array;
	int startIndex;
	int endIndex;
	
	public SubSum(AtomicLongArray array,int startIndex, int endIndex){
		this.array = array;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}

	@Override
	public Integer call() {
		for(long x=startIndex;x<endIndex;x++){
			long sum = x*(x+1)/2;
			array.set((int) x, sum);
		}
		return 1;
	}

}
