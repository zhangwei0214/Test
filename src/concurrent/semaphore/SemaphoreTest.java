package concurrent.semaphore;

import java.util.concurrent.Semaphore;

/**
 * 这个例子是自己写的  用来
 * @author Administrator
 *
 */
public class SemaphoreTest {
	private static Semaphore mutex = new Semaphore(2);
	private static Semaphore done = new Semaphore(10);	//10个线程  都执行完的时候主线程得到通知
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			done.acquire(10);	//这里不可能失败
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		
		
		//ThreadGroup group = new ThreadGroup("我的group");
		for(int i=0;i<10;i++){
			new Thread("Thread " + (i+1)){
				public void run(){
					try {
						mutex.acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("获取信号资源失败");
						return;
					}
					System.out.println(this.getName() + " 执行打印");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(this.getName() + " 打印完成");
					mutex.release();
					done.release();
				}
			}.start();
		}
		
		//主线程重新等待子线程计数器到10
		try {
			done.acquire(10);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("子线程没有全部执行完成， 主线程异常结束");
			return;
		}
		
		//这里怎么做到所有子线程全部完成的时候才结束主线程?
		System.out.println("子线程全部执行完成, 主线程执行完毕!");
	}

}
