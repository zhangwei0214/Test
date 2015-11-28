package thread.waitandnotify;

/**
 * 
 * @author Administrator
 *
 * @see http://www.cnblogs.com/dolphin0520/p/3920385.html
 * 
 * 
 */
public class WaitAndNotifyTest {

	 public static Object object = new Object();
	    public static void main(String[] args) {
	        Thread1 thread1 = new Thread1();
	        thread1.setName("Thread-1");
	        Thread2 thread2 = new Thread2();
	        thread2.setName("Thread-2");
	        thread1.start();
	         
	        try {
	            Thread.sleep(1000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	         
	        thread2.start();
	    }
	     
	    static class Thread1 extends Thread{
	        @Override
	        public void run() {
	            synchronized (object) {
	                try {
	                	System.out.println("Thread-1 waiting()");
	                	//wait()会释放当前线程持有的 object锁（由synchronized获得)
	                	//这很重要， 这样Thread2的notify（synchronize中）才能够被执行
	                    object.wait();	
	                } catch (InterruptedException e) {
	                	
	                }
	                System.out.println("线程"+Thread.currentThread().getName()+"获取到了锁");
	            }
	        }
	    }
	     
	    static class Thread2 extends Thread{
	        @Override
	        public void run() {
	            synchronized (object) {
	            	System.out.println("Thread-2 notifying()");
	                object.notify();
	                System.out.println("线程"+Thread.currentThread().getName()+"调用了object.notify()");
	            }
	            System.out.println("线程"+Thread.currentThread().getName()+"释放了锁");
	        }
	    }
}
