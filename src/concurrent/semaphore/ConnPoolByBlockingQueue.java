package concurrent.semaphore;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 自己写的利用BlockingQueue的锁机制实现的连接池
 * 避免了ConnPoolBySemaphore 中对connectionList的synchronized操作
 * @author Administrator
 *
 */
public class ConnPoolByBlockingQueue {
	 private BlockingQueue<Conn> pool = null;
	
	 public ConnPoolByBlockingQueue(int size){
		 pool = new ArrayBlockingQueue(size);
		 //初始化连接池
		 for(int i=0;i<size;i++){
			 pool.offer(new Conn("Conn " + (i+1)));
		 }
	 }
	 
	 public Conn getConnection(){
		 try {
			return pool.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("获取连接失败");
			e.printStackTrace();
		}
		return null;
	 }
	 
	 public void releaseConnection(Conn conn){
		 pool.offer(conn);
	 }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final ConnPoolByBlockingQueue pool = new ConnPoolByBlockingQueue(3);
		for(int i=0;i<10;i++){
			new Thread("Thread " + (i+1)){
				public void run(){
					Conn conn = pool.getConnection();
					System.out.println("线程: " + Thread.currentThread().getName() + " 正在使用连接:  " + conn.getName());
					conn.select();
					System.out.println("线程: " + Thread.currentThread().getName() + " 释放连接:  " + conn.getName());
					pool.releaseConnection(conn);
				}
			}.start();
		}
	}

}
