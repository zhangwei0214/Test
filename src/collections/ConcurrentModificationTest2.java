package collections;

import java.util.ArrayList;
import java.util.Iterator;

public class ConcurrentModificationTest2 {
	/**
	 * 使用for each的方式测试ConcurrentModificationException
	 * 这里一样会报错
	 * @param args
	 */
	public static void main(String[] args)  {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        //Iterator<Integer> iterator = list.iterator();
        for(Integer integer : list)
            if(integer==2){
            	/*
            	 * 这里使用的是list进行remove， 这样iterator并不知道底层数据已经被改变
            	 * iterator.next()的时候发现底层collection数据发生改变 报错
            	 *  java.util.ConcurrentModificationException
            	 */
            	//1)数据被外部修改
                list.remove(integer);
            }
        }
}
