package socket.multithread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class EchoServer {
 
 private int port = 8000;
 private ServerSocket serverSocket;
 
 public EchoServer() throws IOException{
  serverSocket = new ServerSocket(8000);
  System.out.println("服务器启动");
 }
 
 public void service(){
  while(true){
   Socket socket = null;
   try{
    socket = serverSocket.accept();           //接收客户连接
    Thread workThread = new Thread(new Handler(socket));         //创建一个工作进程
    workThread.start();        //启动工作进程
      }catch(IOException e){
       e.printStackTrace();
      }
  }
 }
 
 
 /**
  * reference http://blog.csdn.net/turkeyzhou/article/details/5007125
  * @param args
  * @throws IOException
  */
 public static void main(String[] args) throws IOException {
  // TODO Auto-generated method stub
  new EchoServer().service();
 }
 
 class Handler implements Runnable{
  private Socket socket; 
  
  public Handler(Socket socket) {
   this.socket = socket;
  }
  
  /*
   * printWriter 支持print系列函数
   */
  private PrintWriter getWriter(Socket socket) throws IOException{
   OutputStream socketOut = socket.getOutputStream();
   //封装一个PrintWriter true -> autoFlush
   return new PrintWriter(socketOut,true);
  }
  
  private BufferedReader getReader(Socket socket) throws IOException{
   InputStream socketIn = socket.getInputStream();
   return new BufferedReader(new InputStreamReader(socketIn));
  }
  public String echo(String msg){
   return "echo:" + msg;
  }
  public void run() {
   // TODO Auto-generated method stub
   try{
    System.out.println("New connection accepted " 
      + socket.getInetAddress() + ":" + socket.getPort());
    BufferedReader br = getReader(socket);
    PrintWriter pw = getWriter(socket);
    
    String msg = null;
    while((msg = br.readLine()) != null){   //接收和发送数据, 直到通信结束
     System.out.println(msg);
     pw.println(echo(msg));
     if(msg.equals("bye")){
      break;
     }
     
    }
   }catch(IOException e){
    e.printStackTrace();
   }finally{
    try{
     if(socket != null) socket.close();             //断开连接
    }catch(IOException e){}
   }
  }
  
 }
}