package concurrent.executor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
			ServerSocket server = new ServerSocket(8080);
			while(true){
				final Socket client = server.accept();
				new Thread(){
					public void run(){
						handle(client);
					}
				}.start();
			}
	}
	
	public static void handle(Socket client){
		
	}
}
