package concurrent.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class TaskWithResult implements Callable<String>{
	int taskIndex;
	public TaskWithResult(int i){
		taskIndex=i;
	}
	/**
	 * @param
	 * @throws checked Exception if it can not finish job &return with result
	 */
	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		Thread.sleep(new Random().nextInt(10000));
		return taskIndex + "  completed";
	}
}
public class CallableTest {
	/**
	 * @param args
	 * @throws InterruptedException if awaitTermination was interrupted
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ExecutorService exec = Executors.newCachedThreadPool();
		List<Future<String>> fsList = new ArrayList<Future<String>>();
		for(int i=1; i<11;i++){
			fsList.add(exec.submit(new TaskWithResult(i)));
		}
				
		//1) 不接受新的请求  2) 原有的请求继续执行 3)shutdown 不会等待现有task执行，而是直接返回
		exec.shutdown();
		System.out.println("shutdown returned");
		exec.awaitTermination(10, TimeUnit.SECONDS);
		
		for(Future<String> fs: fsList){
			if(fs.isDone()){
				try {
					System.out.println(fs.get());
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				System.out.println("job is not down yet");
			}
		}
		
	}
}
