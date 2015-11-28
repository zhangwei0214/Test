package concurrent.semaphore;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;


/**
 * 自己写的一个线程池  使用信号量来控制资源数量
 * @author Administrator
 *
 */
public class ConnPoolBySemaphore {
	private Semaphore connectionSemaphore = new Semaphore(3);	//只提供3个连接
	private List<Conn> connectionList = new ArrayList<Conn>();
	
	private ConnPoolBySemaphore(){
		for(int i=0;i<3;i++){
			connectionList.add(new Conn("Conn " + (i+1)));
		}
	}
	
	public Conn getConnection(){
		try {
			connectionSemaphore.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("获取连接失败");
			return null;
		}
		
		synchronized(connectionList){
			return connectionList.remove(0);	//这里这里有并发问题所以使用synchronized 关键字出来
		}
	}
	
	public void releaseConnection(Conn conn){
		synchronized(connectionList){
			connectionList.add(conn);
		}
		connectionSemaphore.release();	//释放信号量
	}
	
	public static void main(String[] args){
		final ConnPoolBySemaphore pool = new ConnPoolBySemaphore();
		
		for(int i =0;i<10;i++){
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
