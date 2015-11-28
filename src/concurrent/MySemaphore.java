package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


/**
 * 信号量在多线程情况下的使用
 * reference http://www.open-open.com/bbs/view/1320131360999/
 * @author wei.zhangw
 *
 */
public class MySemaphore extends Thread {
	public static void main(String args[]) throws InterruptedException{
	    ExecutorService executorService=Executors.newCachedThreadPool();
	    //厕所两个坑
	    Semaphore position=new Semaphore(2);
	    for(int i=0;i<10;i++){
	    	executorService.submit(new TioletTask(i+1,position));
	    }
	    executorService.shutdown();	//会等待已经submit的task执行完， shutdown调用之后拒绝新的任务
	    Thread.sleep(1000);
	    position.acquireUninterruptibly(2);	//等待信号量全部释放
	    System.out.println("使用完毕，需要清扫了availablePermits =  " + position.availablePermits());
	    position.release(2);
	    System.out.println("清理完毕  availablePermits =  " + position.availablePermits());
	}
}

class TioletTask extends Thread{
	Semaphore position;
	private int id;
	public TioletTask(int i,Semaphore s){
	    this.id=i;
	    this.position=s;
	}
	public void run(){
	    try{
	     if(position.availablePermits()>0){
	      System.out.println("顾客["+this.id+"]进入厕所，有空位");
	     }
	     else{
	      System.out.println("顾客["+this.id+"]进入厕所，没空位，排队");
	     }
	     position.acquire();
	     System.out.println("顾客["+this.id+"]获得坑位");
	     Thread.sleep((int)(Math.random()*10000));	//sleep并不释放信号资源
	     System.out.println("顾客["+this.id+"]使用完毕");
	     position.release();
	    }
	    catch(Exception e){
	     e.printStackTrace();
	    }
	}
}