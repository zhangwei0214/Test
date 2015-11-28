package concurrent.executor;

public class MyTest2 {

	/**
	 * 这个测试显示  主线程可以结束  但是 子线程还是会继续执行到结束
	 * 通常我们会在代码里面使主线程等待子线程结束(join)
	 * @param args
	 * 
	 * @result:
	 * 
	main thread finished running
	(10s waiting)
	sub thread finished running
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Thread(){
			public void run(){
					try {
						Thread.currentThread().sleep(1000 * 10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				System.out.println("sub thread finished running");
			}
		}.start();
		
		System.out.println("main thread finished running");
	}

}
