package socketio;


import java.io.BufferedReader;  
import java.io.BufferedWriter;
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.OutputStreamWriter;
import java.io.PrintWriter;  
import java.net.ServerSocket;  
import java.net.Socket;  
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
class ClientHandler implements Runnable  
{  
    Socket sk = null;  
    BufferedReader rdr = null;  
    PrintWriter wtr = null;  
    public ClientHandler(Socket sk)  
    {  
        this.sk = sk;  
    }  
    public void run()  
    {  
    	System.out.println("已链接");  
        
        InputStreamReader inSR = null;  
        OutputStreamWriter outSW = null;  
        try {  
            //读取数据  
            inSR = new InputStreamReader(sk.getInputStream(), "UTF-8");  
            BufferedReader br = new BufferedReader(inSR);
              
            outSW = new OutputStreamWriter(sk.getOutputStream(), "UTF-8");  
            BufferedWriter bw = new BufferedWriter(outSW);
              
            String str = "";  
            while((str = br.readLine()) != null) {  
                str = str.trim();  
                System.out.println("收到客户端消息：" + str);  
                  
                bw.write("已收到信息：" + str + " \n"); //向客户端反馈消息，加上分行符以便客户端接收  
                bw.flush();  
            }  
          //System.out.println("Cleaning up connection: " + client);  
            inSR.close();  
            outSW.close();  
            sk.close();  
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {  
            
        }  
        System.out.println("已断开");
    } 
} 

//http://lupingzi.iteye.com/blog/509177
public class SocketServer extends Thread  
{  

  ServerSocket server = null;  
  Socket sk = null;

  public SocketServer()  
  {  
      try  
      {  
          server = new ServerSocket(1987);  
      }  
      catch (IOException e)  
      {  
          e.printStackTrace();  
      }  
  }  

  public void run()  
  {  
	  ExecutorService exec = Executors.newFixedThreadPool(10);
	  System.out.println("Listenning...");  
      while (true)  
          {  
              try  
              {  
//                每个请求交给一个线程去处理  
                  sk = server.accept();  
                  exec.execute(new ClientHandler(sk));
              }catch(SocketException e){
            	  System.out.println(e.getMessage());
              }
              catch (Exception e)  
              {  
                  e.printStackTrace();  
              }  
                
          }  
  }  

  public static void main(String [] args)  
  {  
      new SocketServer().start();  
  }
}  