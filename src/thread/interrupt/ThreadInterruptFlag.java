package thread.interrupt;

/**
 * 线程的终止不是简单的事情，使用interrupt来设置中断状态 线程自己判断interrupt标志是最友好的结束线程的方式
 * http://blog.csdn.net/anhuidelinger/article/details/11746365
 */
public class ThreadInterruptFlag extends Thread {
	private boolean isInterrupted = false;
	int count = 0;

	public void interrupt() {
		isInterrupted = true;
		super.interrupt();
	}

	public void run() {
		System.out.println(getName() + "将要运行...");
		while (!isInterrupted) {
			System.out.println(getName() + "运行中" + count++);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(getName() + "从阻塞中退出...");
				System.out.println("this.isInterrupted()="
						+ this.isInterrupted());

			}
		}
		System.out.println(getName() + "已经终止!");
	}

	public static void main(String[] args) {
		ThreadInterruptFlag thread = new ThreadInterruptFlag();
		thread.start();
		try {
			Thread.sleep(10 * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("中断线程");
		thread.interrupt();
		try {
			thread.join();
			System.out.println("主线程正常结束");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("主线程异常结束");
		}

	}
}