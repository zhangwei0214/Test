package concurrent.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author wei.zhangw
 * @see 很详细 http://www.cnblogs.com/dolphin0520/p/3932921.html
 * 不推荐使用ThreadPoolExecutor，  使用executorService / executors 会更好， 不清楚为什么
 */
public class ThreadPoolExecutorTest {
    public static void main(String[] args) {   
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));
         
        //因为ArrayBlockingQueue 的size是5, 最大线程数是10 , 所以任务超过15就会抛reject异常
        for(int i=0;i<15;i++){
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，等待执行："+
            executor.getQueue().size()+"，已执行完："+executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }
}


class MyTask implements Runnable {
   private int taskNum;
    
   public MyTask(int num) {
       this.taskNum = num;
   }
    
   @Override
   public void run() {
       System.out.println("正在执行task "+taskNum);
       try {
           Thread.currentThread().sleep(4000);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       System.out.println("task "+taskNum+"执行完毕");
   }
}