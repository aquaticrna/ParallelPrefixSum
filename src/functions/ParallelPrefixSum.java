package functions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLongArray;



public class ParallelPrefixSum {
	public static void main(String[] args){
		int[] array = new int[1000000];
		for(int x=0;x<array.length;x++){
			array[x] = x;
		}
		long time = System.currentTimeMillis();
		
		long[] summed = new long[1000000];
		summed[0] = 0;
		for(int x=1;x<summed.length;x++){
			summed[x] = array[x]+summed[x-1];
		}
		long baseTime = System.currentTimeMillis()-time;
		System.out.println(baseTime);
		time = System.currentTimeMillis();
		ExecutorService exec = Executors.newCachedThreadPool();
		int quarter = array.length/4;
		AtomicLongArray summed2 = new AtomicLongArray(1000000); 
		List<Future<Integer>> futures = new ArrayList<Future<Integer>>();
		for(int x=0;x<quarter*3;x+=quarter){
			futures.add(exec.submit(new SubSum(summed2,x,x+quarter)));
		}
		
		futures.add(exec.submit(new SubSum(summed2,quarter*3,summed2.length())));
		futures.stream().forEach(f -> f.isDone());
		exec.shutdown();
		System.out.println((System.currentTimeMillis()-time));//check out guava
		for(int x= 0;x<array.length;x++){
			if (summed[x] !=summed2.get(x)){
				System.out.println(x);
				System.out.println("wrong calculation");
				break;
			}
		}
	}
}
