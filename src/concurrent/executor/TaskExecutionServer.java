package concurrent.executor;


import java.io.IOException;  
import java.net.ServerSocket;  
import java.net.Socket;  
import java.util.concurrent.Executor;  
import java.util.concurrent.Executors;  

/** 
* 基于Executor的服务器 
* @author zhy 
* @see http://blog.csdn.net/lmj623565791/article/details/26938985
*/  
public class TaskExecutionServer  
{  
  private static final int THREAD_COUNT = 100;  
  private static final Executor exec = Executors  
          .newFixedThreadPool(THREAD_COUNT);  

  public static void main(String[] args) throws IOException  
  {  
      ServerSocket server = new ServerSocket(9911);  
      while (true)  
      {  
          final Socket client = server.accept();  
          Runnable task = new Runnable()  
          {  
              @Override  
              public void run()  
              {  
                  handleReq(client);  
              }
          };
          exec.execute(task);  
      }  

  }  

  protected static void handleReq(Socket client)  
  {  

  }  
}
