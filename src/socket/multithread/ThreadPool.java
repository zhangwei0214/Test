package socket.multithread;

//reference
//http://blog.csdn.net/turkeyzhou/article/details/5007125
/*
 * thread pool version of socket handling function
 */
import java.util.LinkedList;
public class ThreadPool extends ThreadGroup {
 private boolean isClosed = false;   //线程池是否关闭
 private LinkedList<Runnable> workQueue;  //工作队列
    private static int threadPoolID;   //表示线程池ID
    private int threadID;      //表示工作线程ID ,因为 WorkThread是内部类所以才可以这样
 
 public ThreadPool(int poolSize) {       //poolSize指定线程池中的工作线程数目
  super("ThreadPool-" + (threadPoolID ++));
  this.setDaemon(true);
  workQueue = new LinkedList<Runnable>();      //创建工作队列
  for(int i=0; i<poolSize; i++){
	  new WorkThread().start();                //创建并启动工作进程
  }
 }
 public WorkThread getWorkThread(){
  return new WorkThread();
 }
 /**
  * 向工作队列中加入一个新任务, 由工作进程去执行该任务
  */
 public synchronized void execute(Runnable task){
  if(isClosed){              //线程池被关闭则抛出IllegalStateException 异常
   throw new IllegalStateException();
  }
  if(task != null){
   workQueue.add(task);      //添加任务到工作队列中
   notify();                 //呼醒正在getTask()方法中等待任务的某一个工作线程,哪一个是随机的
  }
 }
 
 /**
  * @throws InterruptedException wait()中被interrupt()将产生这个异常
  * 从工作队列中取出一个任务, 工作线程会调用此方法
  */
 protected synchronized Runnable getTask() throws InterruptedException{
  while(workQueue.size() == 0){
   if(isClosed) return null;
   wait();   //如果工作队列中没有任务, 就等待任务, 对应execute()方法中的notify和join()方法中的notifyAll()
  }
  return workQueue.removeFirst();
 }
 
 /** 关闭线程池 */
 public synchronized void close(){
  if(!isClosed){
   isClosed = true;
   workQueue.clear();    //清空工作队列
   interrupt();                 //中断所有的的工作线程, 该方法继承自 ThreadGroup 类
  }
 }
 
 /** 等待工作线程把所有任务执行完 */
 public void join(){
  synchronized(this){
   isClosed = true;
   notifyAll();            //呼醒所有在getTask()方法中等待任务的工作线程
  }
  Thread[] threads = new Thread[this.activeCount()];
  //enumerate()方法继承自 ThreadGroup 类, 获得线程组中当前所有活着的工作进程 ,并把这些线程放到指定的Thread数组中
  int count = this.enumerate(threads);
  for(int i=0; i<count; i++){         //等待所有工作线程运行结束
   try{
    threads[i].join();          //等待工作进程运行结束
   }catch(InterruptedException ex){}
  }
 } 
  
    /** 内部类: 工作线程(注意, 在本类中, 内部类(不是static的)和外部类可以互相访问对方的所有成员变量和方法, 即使是private的方法和成员变量, 
     *                  本类内部访问限定符对内部类和外部类都没有影响, 就好像是在所有成员变量和方法视为一个同等的存在一样处理.
     *                  但是如果你是在其他类中就访问本类中的外部类和内部类的成员变量和方法, 那就会受到访问限定符的限制的.
     *                  最后还要注意, 不要在本类中的main() 中测试, 理由不用说了吧) */
 private class WorkThread extends Thread {
  public WorkThread() {
   // 加入到当前 ThreadPool 线程当中
   super(ThreadPool.this, "WorkThread-" + (threadID++)); 
  }
  public void run() {
   while(!isInterrupted()){       //isInterrupted() 方法继承自 Thread 类, 判断线程是否被中断
    Runnable task = null;
    try{
     task = getTask();   //取出任务
    }catch(InterruptedException e){}
    
    //如果getTask() 返回null, 表示线程池已经被关闭了, 结束此线程
    if(task == null) return;
    try{
     task.run();
    }catch(Throwable t){ //捕捉线程run()运行中产生所有的错误和异常,为了防止进程被结束
     t.printStackTrace();
    }
   }//while
  }//run
 }//WorkThread
}