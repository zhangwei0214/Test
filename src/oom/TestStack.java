package oom;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Administrator
 * 要让Stack爆炸应该调试-Xss参数，  减小这个配置容易Stack溢出
 * 参考: http://visionsky.blog.51cto.com/733317/566844/
 */
public class TestStack {  
	  
    
    int num=1;  
    public void testStack(){  
        num++;  
        //double[] doubleList = new double[10000];  
        //System.out.println("list.size() = "+ doubleList.length);
        this.testStack();  
     }  
      
    public static void main(String[] args){  
        TestStack  t  = new TestStack ();  
        
        t.testStack();     
    }  
}
