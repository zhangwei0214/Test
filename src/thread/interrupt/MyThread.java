package thread.interrupt;

/**
 * http://blog.csdn.net/jiangwei0910410003/article/details/19962603
 * 
 * @author Administrator
 * 
 */
public class MyThread extends Thread {
	public void run() {
		while (!Thread.currentThread().isInterrupted()) { // 如果线程没有被中断就继续运行
			try {
				// 阻塞代码：sleep,wait等
				// 当其他线程，调用此线程的interrupt()方法时，会给此线程设置一个中断标志
				// sleep,wait等方法会检测这个标志位，同时会抛出InterruptedException，并清除线程的中断标志
				// 因此在异常段调用Thread.currentThread().isInterrupted()返回为false
				Thread.sleep(10 * 1000);

			} catch (InterruptedException e) {
				// 由于阻塞库函数，如：Object.wait,Thread.sleep除了抛出异常外，还会清除线程中断状态，因此可能在这里要保留线程的中断状态
				// 从我测试的情况来看,确实是这样
				System.out.println("中断catch: 线程中断标志: "
						+ Thread.currentThread().isInterrupted()
						+ "\t thread.isAlive() = " + isAlive());// 打印线程中断标志
				Thread.currentThread().interrupt();//从新设置线程的中断标志
			}
		}
	}

	public void cancel() {
		interrupt(); // 中断线程
	}

	public static void main(String[] args) {
		MyThread thread = new MyThread();
		System.out.println("线程开启");
		thread.start(); // 开启线程
		// ......
		// 等待子线程的run方法执行
		try {
			thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("中断线程..");
		thread.cancel(); // 中断线程

		System.out.println("线程中断标志: " + thread.isInterrupted()
				+ "\t thread.isAlive() = " + thread.isAlive());// 打印线程中断标志
	}
}