package concurrent.executor;

import java.io.IOException;  
import java.net.ServerSocket;  
import java.net.Socket;  
/** 
 * 为每个请求分配一个线程 
 * @author zhy 
 * @see http://blog.csdn.net/lmj623565791/article/details/26938985
 */  
public class TaskPerThreadServer  
{  
    public static void main(String[] args) throws IOException  
    {  
        ServerSocket server = new ServerSocket(8811);  
        while (true)  
        {  
            final Socket client = server.accept();  
            new Thread()  
            {  
                public void run()  
                {  
                    handleReq(client);  
                };  
            }.start();  
        }  
    }  
  
    protected static void handleReq(Socket client)  
    {  
          
    }  
}  