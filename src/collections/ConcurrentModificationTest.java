package collections;

import java.util.ArrayList;
import java.util.Iterator;

public class ConcurrentModificationTest {
	/**
	 * 使用while(iterator.hasNext())的方式测试ConcurrentModificationException
	 * 
	 * @param args
	 */
	public static void main(String[] args)  {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            Integer integer = iterator.next();
            System.out.println(integer);
            if(integer==2){
            	/*
            	 * 这里使用的是list进行remove， 这样iterator并不知道底层数据已经被改变
            	 * iterator.next()的时候发现底层collection数据发生改变 报错
            	 *  java.util.ConcurrentModificationException
            	 */
            	//1)数据被外部修改
                //list.remove(integer);
            	
            	
            	//2)使用iterator.remove	//单线程OK  多线程check ConcurrentModificationTest3
            	iterator.remove();
            }
        }
    }
}
