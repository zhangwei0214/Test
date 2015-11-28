package oom;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestHeap {  
	  
    public void testHeap(){  
        for(;;){  
        	  try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
              Double[] doubleList = new Double[100000];  
              System.out.println("list.size() = "+ doubleList.length);
          }  
    } 
      
    public static void main(String[] args){  
        TestHeap  t  = new TestHeap ();  
        t.testHeap(); 
    }  
}
