package functions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLongArray;


//does prefix summing in parallel for monotonically increasing sets starting at 0
public class ParallelPrefixSum {
	public static void main(String[] args){
		//base lining information to test speed
		
		long time = System.currentTimeMillis();//track the time
		int[] array = new int[1000000];
		//fill in the array
		for(int x=0;x<array.length;x++){
			array[x] = x;
		}
		
		//calculate the cumulative sum
		long[] summed = new long[1000000];
		summed[0] = 0;
		for(int x=1;x<summed.length;x++){
			summed[x] = array[x]+summed[x-1];
		}
		//bas time
		long baseTime = System.currentTimeMillis()-time;
		System.out.println("linear time = " + baseTime + "ms");
		
		//start timing again
		time = System.currentTimeMillis();
		
		//set up our thread pool
		ExecutorService exec = Executors.newCachedThreadPool();
		
		int quarter = array.length/4;
		AtomicLongArray summed2 = new AtomicLongArray(1000000);
		List<Future<Integer>> futures = new ArrayList<Future<Integer>>();
		
		//spawns a thread and future object for each quarter of the list
		for(int x=0;x<quarter*3;x+=quarter){
			futures.add(exec.submit(new SubSum(summed2,x,x+quarter)));
		}
		futures.add(exec.submit(new SubSum(summed2,quarter*3,summed2.length())));//last quarter includes any remainder
		futures.stream().forEach(f -> f.isDone());//wait for them to finish
		exec.shutdown();
		//error checking and time comparison
		System.out.println("Parallel Time = " + (System.currentTimeMillis()-time) + "ms");//parallel processing becomes faster between n = 10 million and n = 50 million
		for(int x= 0;x<summed.length;x++){
			if (summed[x] !=summed2.get(x)){
				System.out.println(x);
				System.out.println("wrong calculation");
				break;
			}
		}
	}
}
