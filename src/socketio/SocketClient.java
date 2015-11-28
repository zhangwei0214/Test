package socketio;

import java.io.BufferedReader;  
import java.io.BufferedWriter;  
import java.io.InputStreamReader;  
import java.io.OutputStreamWriter;  
import java.io.PrintWriter;  
import java.net.Socket;  

/**
 * 支持UTF-8
 * @author Administrator
 *
 */
public class SocketClient extends Thread  
{  
    Socket sk = null;  
  
    public SocketClient()  
    {  
       
        try  
        {  
            sk = new Socket("127.0.0.1", 1987);  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        } 
    }  
  
    public void run()  
    {  
        try
        {  
            BufferedReader reader = null;
            BufferedReader keyin = null;  
            keyin = new BufferedReader(new InputStreamReader(System.in));  
            InputStreamReader isr = new InputStreamReader(sk.getInputStream(),"UTF-8");
           // wtr = new PrintWriter(sk.getOutputStream());
            OutputStreamWriter osw = new OutputStreamWriter(sk.getOutputStream(),"UTF-8");
            while (true)  
            {  
            	String get = keyin.readLine();  
                if (get != null & get.length() > 0)  
                {  
                    //wtr.println(get);  
                	osw.write(get + "\n");
                	osw.flush();
//                  wtr.close();  
//                  System.out.println(get + "发送完毕");  
                }  
                if (reader != null)  
                {  
                    String line = reader.readLine();  
                    System.out.println("从服务器来的信息：" + line);  
                      
                }  
            }  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
    }  
  
    public static void main(String [] args)  
    {  
        new SocketClient().start();  
    }  
}  