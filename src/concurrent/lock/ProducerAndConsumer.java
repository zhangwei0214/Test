package concurrent.lock;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @see http://www.cnblogs.com/dolphin0520/p/3920385.html
 * @author Administrator
 *
 */
public class ProducerAndConsumer {

	 private int queueSize = 10;
	    private PriorityQueue<Integer> queue = new PriorityQueue<Integer>(queueSize);
	    private Lock lock = new ReentrantLock();
	    private Condition notFull = lock.newCondition();
	    private Condition notEmpty = lock.newCondition();
	     
	    public static void main(String[] args)  {
	    	ProducerAndConsumer test = new ProducerAndConsumer();
	        Producer producer = test.new Producer();
	        Consumer consumer = test.new Consumer();
	          
	        producer.start();
	        consumer.start();
	    }
	      
	    class Consumer extends Thread{
	          
	        @Override
	        public void run() {
	            consume();
	        }
	          
	        private void consume() {
	            while(true){
	                lock.lock();
	                try {
	                    while(queue.size() == 0){
	                        try {
	                            System.out.println("队列空，等待数据");
	                            notEmpty.await();
	                        } catch (InterruptedException e) {
	                            e.printStackTrace();
	                        }
	                    }
	                    queue.poll();                //每次移走队首元素
	                    notFull.signal();
	                    System.out.println("从队列取走一个元素，个数 ： "+queue.size()+"个元素");
	                } finally{
	                    lock.unlock();
	                }
	            }
	        }
	    }
	      
	    class Producer extends Thread{
	          
	        @Override
	        public void run() {
	            produce();
	        }
	          
	        private void produce() {
	            while(true){
	                lock.lock();
	                try {
	                    while(queue.size() == queueSize){
	                        try {
	                            System.out.println("队列满，等待有空余空间");
	                            notFull.await();
	                        } catch (InterruptedException e) {
	                            e.printStackTrace();
	                        }
	                    }
	                    queue.offer(1);        //每次插入一个元素
	                    notEmpty.signal();
	                    System.out.println("向队列取中插入一个元素，个数 ："+queue.size());
	                } finally{
	                    lock.unlock();
	                }
	            }
	        }
	    }
}
