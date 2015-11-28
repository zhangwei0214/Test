package concurrent.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MyTest {
	private FutureTask<String> futureTask = new FutureTask<String>(
				new Callable<String>(){
					public String call(){
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("future执行完成");
						return "called";
					}
				}
			);
	private Thread thread = new Thread(futureTask);
	
	public void start(){
		thread.start();
	}
	
	public String get(){
		try {
			return futureTask.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyTest test = new MyTest();
		System.out.println("开始执行futureTask");
		test.start();
		try {
			Thread.sleep(10*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	//主线程等待10秒  此时 futureTask需要的5秒已经走完
		System.out.println("主线程等待了10秒");
		String result = test.get();
		System.out.println("结果result = "  + result);
		
	}

}
