package threadlocal;

/**
 * 
 * 
 * @author wei.zhangw
 * @see http://blog.csdn.net/laorer/article/details/5906694
 result:
 
Thread-1 --> 1
Thread-1 --> 2
Thread-1 --> 3
Thread-0 --> 1
Thread-0 --> 2
Thread-0 --> 3
Thread-2 --> 1
Thread-2 --> 2
Thread-2 --> 3
 *
 *ThreadLocal的场景
 *1) 通过ThreadLocal在线程范围内共享变量， 减少参数传递
 *2) 最重要的用途:(如果一个对象是单例singleton的， 那么多线程会同时访问同一个对象， 即使对象中的成员不是static的， 依然存在多线程数据安全问题 (即有状态对象)， 
 *   使用ThreadLocal可以改造对象, 将有状态的对象改造成无状态， 成为线程安全对象.

Spring使用ThreadLocal解决线程安全问题
我们知道在一般情况下，只有无状态的Bean才可以在多线程环境下共享，在Spring中，绝大部分Bean都可以声明为singleton作用域。
就是因为Spring对一些Bean（如RequestContextHolder、TransactionSynchronizationManager、LocaleContextHolder等）中非线程安全状态采用ThreadLocal进行处理
让它们也成为线程安全的状态，因为有状态的Bean就可以在多线程中共享了。
 */
public class ThreadLocalTest {  
	  
	 private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){  
	  public Integer initialValue(){  
	   return 0;  
	  }  
	 };  
	   
	 public int getNextNum(){  
	  seqNum.set(seqNum.get() + 1);  
	  return seqNum.get();  
	 }  
	   
	 public static void main(String[] args){  
	  ThreadLocalTest sn = new ThreadLocalTest();  
	  TestClient t1  = new TestClient(sn);  
	  TestClient t2  = new TestClient(sn);  
	  TestClient t3  = new TestClient(sn);  
	    
	  t1.start();  
	  t2.start();  
	  t3.start();  
	    
	  //t1.print();  
	  //t2.print();  
	  //t3.print();  
	    
	    
	 }  
	   
	 private static class TestClient extends Thread{  
	  private ThreadLocalTest sn ;  
	  public TestClient(ThreadLocalTest sn ){  
	   this.sn = sn;  
	  }  
	    
	  public void run(){  
	   for(int i=0; i< 3; i++){  
	    System.out.println( Thread.currentThread().getName()  + " --> " + sn.getNextNum());  
	   }  
	  }  
	    
	  public void print(){  
	   for(int i=0; i< 3; i++){  
	    System.out.println( Thread.currentThread().getName()  + " --> " + sn.getNextNum());  
	   }  
	  }  
	 }  
	   
	}  