package semaphore;

import java.util.concurrent.Semaphore;

public class Test {
	public static void  main(String[] args){
	Semaphore semaphore = new Semaphore(100);
	semaphore.release();
	semaphore.release();
	semaphore.release();
	semaphore.release();
	semaphore.release();
	
	//有副作用 count = 105
	System.out.println("有副作用 count = " + semaphore.availablePermits());
}
}
