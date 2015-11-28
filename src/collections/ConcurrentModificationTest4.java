package collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Administrator
 *
 */
public class ConcurrentModificationTest4 {
	
	
	/**
	 * 多线程环境下  使用CopyOnWriteArrayList解决冲突问题
	 * 
	 * result:
		Thread2遍历1
		Thread1遍历1
		Thread2遍历2
		Thread1遍历2
		Thread2遍历3
		Thread1遍历3
		Thread2遍历4
		Thread1遍历4
		Thread2遍历5
		Thread1遍历5
		Thread2List:[1, 3, 4, 5]
		Thread1List:[1, 3, 4, 5]
	 */
	private static List<Integer> list = new CopyOnWriteArrayList<Integer>();
	    public static void main(String[] args)  {
	        list.add(1);
	        list.add(2);
	        list.add(3);
	        list.add(4);
	        list.add(5);
	        Thread thread1 = new Thread("Thread1"){
	            public void run() {
	                Iterator<Integer> iterator = list.iterator();
	                //让thread2先运行
	            	 try {
	                        Thread.sleep(100);	
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                while(iterator.hasNext()){
	                    Integer integer = iterator.next();
	                    System.out.println(Thread.currentThread().getName() + "遍历" + integer);
	                    try {
	                        Thread.sleep(100);
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                }
	                
	                System.out.println(Thread.currentThread().getName() + "List:" + list);
	            };
	        };
	        Thread thread2 = new Thread("Thread2"){
	            public void run() {
	                Iterator<Integer> iterator = list.iterator();
	                while(iterator.hasNext()){
	                    Integer integer = iterator.next();
	                    System.out.println(Thread.currentThread().getName() + "遍历" + integer);
	                    if(integer==2)
	                        //iterator.remove(); 	//CopyOnWriteArrayList 不支持remove()操作
	                    	list.remove(integer);	//直接使用原collection上面的remove操作
	                    try {
	                        Thread.sleep(100);
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                }
	                
	                System.out.println(Thread.currentThread().getName() + "List:" + list);
	            };
	        };
	        thread1.start();
	        thread2.start();
	    }
}
