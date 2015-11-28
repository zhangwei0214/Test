package concurrent.callable;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class TaskWithNoResult implements Runnable{
	int taskIndex;
	public TaskWithNoResult(int i){
		taskIndex=i;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(new Random().nextInt(5000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("thread:" + Thread.currentThread().getName() + "  task " + taskIndex + " finished");
	}
}

/**
 * 测试newFixedThreadPool(1) -> 单线程的线程池
 * @author Administrator
 *
 */
public class RunnableTest {

	/**
	 * @param args
	 * @throws InterruptedException is awaitTermination was interrupted
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//线程池中的线程不是deamon, 即使主线程结束，也会继续执行
		ExecutorService exec = Executors.newFixedThreadPool(1);	
		for(int i=1; i<11;i++){
			exec.execute(new TaskWithNoResult(i));
		}
		//call shutdown -> if tasks done in pool, thread will be reclaim, so program also will be close
		//if we don't call shutdown, pool will run forever as thread in pool always running.
		exec.shutdown();
		
		System.out.print("waiting.");
		while(!exec.isTerminated()){
			exec.awaitTermination(1, TimeUnit.SECONDS);
			System.out.print(".");
		}
		System.out.println("all jobs done");
	}

}
