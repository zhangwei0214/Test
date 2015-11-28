package collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 多线程操作同一个ArrayList,即使使用iterator.remove()  依然会报错
 * ArrayList 非线程安全容器
 * @author Administrator
 *
 */
public class ConcurrentModificationTest3 {
	
	/**
	 * 多线程操作同一个ArrayList,即使使用iterator.remove()  依然会报错
	 * ArrayList 非线程安全容器
	 */
	 //private static List<Integer> list = new ArrayList<Integer>();	//ConcurrentModificationEception
	
	/**
	 * 即使使用线程安全的vector, 依然会ConcurrentModificationEception ,
	 * 因为Iterator本身是AbstractList(非线程安全)的一部分
	 * 
	 * solution:
	 * 1) 通过引入synchronized关键字, 进行同步
	 * 2) 使用concurrent 包中的 CopyOnWriteArrayList -> ConcurrentModificationTest4
	 */
	private static List<Integer> list = new Vector<Integer>();
	
	    public static void main(String[] args)  {
	        list.add(1);
	        list.add(2);
	        list.add(3);
	        list.add(4);
	        list.add(5);
	        Thread thread1 = new Thread("Thread1"){
	            public void run() {
	            	//让thread2先运行
	            	 try {
	                        Thread.sleep(100);	
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	            	//使用synchronized关键字进行加锁操作
	            	//会造成性能瓶颈 // 因为读也要加锁
	            	synchronized(list){
		                Iterator<Integer> iterator = list.iterator();
		                while(iterator.hasNext()){
		                    Integer integer = iterator.next();
		                    System.out.println(Thread.currentThread().getName() + "遍历" + integer);
		                    try {
		                        Thread.sleep(100);	//sleep并不释放锁
		                    } catch (InterruptedException e) {
		                        e.printStackTrace();
		                    }
		                }
	            	}
	            };
	        };
	        Thread thread2 = new Thread("Thread2"){
	            public void run() {
	            	synchronized(list){
		                Iterator<Integer> iterator = list.iterator();
		                while(iterator.hasNext()){
		                    Integer integer = iterator.next();
		                    System.out.println(Thread.currentThread().getName() + "遍历" + integer);
		                    if(integer==2)
		                        iterator.remove();
		                }
	            	}
	            };
	        };
	        thread1.start();
	        thread2.start();
	    }
}
