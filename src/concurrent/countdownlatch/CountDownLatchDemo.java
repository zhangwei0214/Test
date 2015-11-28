package concurrent.countdownlatch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
	
	final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub
		CountDownLatch latch = new CountDownLatch(2);//连个工人的协调
		Worker worker1 = new Worker("zhang san", 5000, latch);
		Worker worker2 = new Worker("li si", 8000, latch);
		worker1.start();
		worker2.start();
		latch.await();//等待所有工人完成工作
		System.out.println("all work down at " + sdf.format(new Date()));
	}
}

class Worker extends Thread{
	final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String workerName;
	int workTime;
	CountDownLatch latch;
	public Worker(String workerName, int workTime, CountDownLatch latch){
		this.workerName = workerName;
		this.workTime = workTime;
		this.latch = latch;
	}
	
	public void run(){  
        System.out.println("Worker "+workerName+" do work begin at "+sdf.format(new Date()));  
        doWork();//工作了  
        System.out.println("Worker "+workerName+" do work complete at "+sdf.format(new Date()));  
        latch.countDown();//工人完成工作，计数器减一  

    }  
      
    private void doWork(){  
        try {  
            Thread.sleep(workTime);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
    }
	
}
