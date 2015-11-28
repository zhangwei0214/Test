package thread.interrupt;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 自己写的使用socket.close() 关闭socket服务器
 * BUG:
 * 这里面的accept由于测试不了真实的socket client连接 , 真实的情况是服务器主线程会结束 但是子线程不会结束
 * @author Administrator
 *
 */
public class MySocketServerTest extends Thread {
	private volatile ServerSocket serverSocket;
	
	public void run(){
		try {
			serverSocket = new ServerSocket(8080);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("服务器启动失败, 端口8080无法监听");
			return;
		}
		while(true){
			try {
				final Socket clientSocket = serverSocket.accept();
				new Thread(){
					public void run(){
						handleRequest(clientSocket);
					}
					public void handleRequest(Socket client){
						//todo handle socket communication with client socket
						try {
							Thread.sleep(1000*1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	//1000s 
					}
				}.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("接收客户端连接失败  服务器主线程结束");
				break;
			}
		}
	}
	
	public void close(){
		try {
			this.serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("关闭服务器失败");
			return;
		}
		System.out.println("关闭服务器成功");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("application starting...");
		MySocketServerTest server = new MySocketServerTest();
		server.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("interrupt application...");
		//server.interrupt();
		server.close();
		System.out.println("application closed");
	}

}
