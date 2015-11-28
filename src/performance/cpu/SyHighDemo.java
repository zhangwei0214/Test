package performance.cpu;

import java.util.Random;


/**
 * 
 * @see java应用.pdf 5.1.1  使用jstack -l [pid]  -》dump stack的详细信息,
 * @author Administrator
 *
 */
public class SyHighDemo {

	private static int threadCount = 1;
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SyHighDemo demo = new SyHighDemo();
		demo.runTest();
	}
	
	private Random random = new Random();
	private Object[] locks;
	
	private void runTest() throws Exception{
		locks = new Object[threadCount];
		for(int i = 0 ; i < threadCount; i ++){
			locks[i] = new Object();
		}
		for(int i = 0; i < threadCount; i++){
			new Thread(new ATask(i)).start();
			new Thread(new BTask(i)).start();
		}
	}
	
	class ATask implements Runnable{
		private Object lockObject = null;
		public ATask(int i){
			lockObject = locks[i];
		}
		
		@Override
		public void run(){
			while(true){
				try{
					synchronized(lockObject){
						lockObject.wait(random.nextInt(10));
					}
				}
				catch(Exception e){
					;
				}
			}
		}
	}
	
	class BTask implements Runnable{
		private Object lockObject = null;
		public BTask(int i){
			lockObject = locks[i];
		}
		
		@Override
		public void run(){
			while(true){
				synchronized(lockObject){
					lockObject.notifyAll();
				}
				try{
					Thread.sleep(random.nextInt(10));
				}
				catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
	}
}
