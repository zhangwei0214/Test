package mail;

import java.io.BufferedReader;  
import java.io.BufferedWriter;  
import java.io.File;  
import java.io.FileReader;  
import java.io.FileWriter;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.OutputStreamWriter;  
import java.net.ServerSocket;  
import java.net.Socket;  
import java.util.HashMap;  
import java.util.Map; 

/** 
 * �򵥷������˵�ʵ�֡�<br> 
 * ���̡߳� 
 *  
 * @author ������(laozizhu.com) 
 */  

public class MailServer {  
	  public static void main(String[] args) throws IOException {  
	    ServerSocket server = null;  
	    try {  
	      // We assume that our service port number is 1234  
	      server = new ServerSocket(1234);  
	      while (true) {  
	        try {  
	          new MailServerThread(server.accept()).start();  
	        } catch (IOException e) {  
	          System.out.println("Accept failed: 1234");  
	        } catch (Exception e) {  
	          System.out.println("Server error:" + e.getMessage());  
	        }  
	      }  
	    } catch (IOException e) {  
	      System.out.println("Failed to listen on port: 1234");  
	    } finally {  
	      if (server != null) {  
	        server.close();  
	      }  
	    }  
	  }  
	}  
	class MailServerThread extends Thread {  
	  // common string  
	  private static final String OK = "250";  
	  private static final String ERROR = "200";  
	  private static final String BR = "/r/n";  
	  private static final String FILEPATH = "Mailbox and client information/";  
	  private static final String FILENAME_EXT = ".txt";  
	  private static final String FILENAME_CLIENTINFO = FILEPATH + "ClientInfo.txt";  
	  // command  
	  private static final String COMMAND_USERNAME = "#USERNAME";  
	  private static final String COMMAND_PASSWORD = "#PASSWORD";  
	  private static final String COMMAND_SENDTO = "#SENDTO";  
	  private static final String COMMAND_TITLE = "#TITLE";  
	  private static final String COMMAND_CONTENT = "#CONTENT";  
	  private static final String COMMAND_RECEIVE = "#RECEIVE";  
	  private static final String COMMAND_EXIT = "#EXIT";  
	  // message  
	  private static final String MESSAGE_EXIT = OK + " Exit ok" + BR;  
	  private static final String MESSAGE_COMMAND_NOT_FOUND = ERROR + " Command not found";  
	  private static final String MESSAGE_AUTH_NEED = ERROR + " User not logged in yet";  
	  private static final String MESSAGE_USERNAME_OK = OK + " Username ok";  
	  private static final String MESSAGE_USERNAME_NOT_EXISTS = ERROR + " Username is not exists";  
	  private static final String MESSAGE_USERNAME_COMMAND_FIRST = ERROR + " " + COMMAND_USERNAME  
	      + " first!";  
	  private static final String MESSAGE_USER_AUTH_OK = OK + " User authenticated";  
	  private static final String MESSAGE_USER_AUTH_ERROR = ERROR + " Authentication failure";  
	  private static final String MESSAGE_SENDTO_MISSION = ERROR + " Recipient error";  
	  private static final String MESSAGE_SENDTO_NOT_EXISTS = ERROR  
	      + " Recipient does not exist on the server";  
	  private static final String MESSAGE_SENDTO_OK = OK + " Recipient ok";  
	  private static final String MESSAGE_SENDTO_COMMAND_FIRST = ERROR + " " + COMMAND_SENDTO  
	      + " first";  
	  private static final String MESSAGE_TITLE_MISSING = ERROR + " Title is missing";  
	  private static final String MESSAGE_TITLE_OK = OK + " Title ok";  
	  private static final String MESSAGE_TITLE_COMMAND_FIRST = ERROR + " " + COMMAND_TITLE + " first";  
	  private static final String MESSAGE_CONTENT_MISSING = ERROR + " Content is missing";  
	  private static final String MESSAGE_CONTENT_OK = OK + " Content ok";  
	  private static final String MESSAGE_RECEIVE_EMPTY = ERROR + " Mailbox empty";  
	  private static final String MESSAGE_RECEIVE_SAVE_ERROR = ERROR + " server error";  
	  // property  
	  Socket client = null;  
	  BufferedReader inFromClient;  
	  BufferedWriter outToClient;  
	  private String username;  
	  private String password;  
	  private boolean auth = false;  
	  private String sendtoUsername;  
	  private String title;  
	  private String content;  
	  // constructer  
	  MailServerThread(Socket client) {  
	    this.client = client;  
	    try {  
	      inFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));  
	      outToClient = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));  
	    } catch (IOException e) {  
	      e.printStackTrace();  
	    }  
	  }  
	  public void run() {  
	    try {  
	      // tmp buffers one input line from the client's socket.  
	      String tmp = inFromClient.readLine();  
	      // The socket connection persists until the server receives a '#EXIT'  
	      while (tmp != null && !tmp.equals(COMMAND_EXIT)) {  
	        // find command  
	        int index = tmp.indexOf(" ");  
	        String str = index == -1 ? tmp : tmp.substring(0, index);  
	        // get command  
	        Command command = mapCommand.get(str);  
	        if (command == null) {  
	          str = MESSAGE_COMMAND_NOT_FOUND;  
	        } else {  
	          str = command.doIt(tmp);  
	        }  
	        outToClient.write(str + BR);  
	        outToClient.flush();  
	        tmp = inFromClient.readLine();  
	      }  
	      // #EXIT command  
	      outToClient.write(MESSAGE_EXIT);  
	      outToClient.flush();  
	    } catch (IOException e) {  
	      System.out.println("ServerThread error:" + e.getMessage());  
	    } finally {  
	      // close the client connection  
	      if (client != null && client.isConnected()) {  
	        try {  
	          client.close();  
	        } catch (IOException e) {  
	          e.printStackTrace();  
	        }  
	      }  
	    }  
	  }  
	  final Map<String, Command> mapCommand = new HashMap<String, Command>();  
	  {  
	    mapCommand.put(COMMAND_USERNAME, new UsernameCommand());  
	    mapCommand.put(COMMAND_PASSWORD, new PasswordCommand());  
	    mapCommand.put(COMMAND_SENDTO, new SendToCommand());  
	    mapCommand.put(COMMAND_TITLE, new TitleCommand());  
	    mapCommand.put(COMMAND_CONTENT, new ContentCommand());  
	    mapCommand.put(COMMAND_RECEIVE, new ReceiveCommand());  
	  }  
	  interface Command {  
	    public String doIt(String str);  
	  }  
	  class UsernameCommand implements Command {  
	    public String doIt(String str) {  
	      password = null;  
	      auth = false;  
	      sendtoUsername = null;  
	      title = null;  
	      content = null;  
	      username = str.substring(COMMAND_USERNAME.length()).trim();  
	      if (userMap.keySet().contains(username)) {  
	        password = null;  
	        auth = false;  
	        return MESSAGE_USERNAME_OK;  
	      }  
	      username = null;  
	      return MESSAGE_USERNAME_NOT_EXISTS;  
	    }  
	  }  
	  class PasswordCommand implements Command {  
	    public String doIt(String str) {  
	      if (username == null) {  
	        return MESSAGE_USERNAME_COMMAND_FIRST;  
	      }  
	      auth = false;  
	      sendtoUsername = null;  
	      title = null;  
	      content = null;  
	      password = str.substring(COMMAND_USERNAME.length()).trim();  
	      String pass = userMap.get(username);  
	      if (pass != null && pass.equals(password)) {  
	        auth = true;  
	        return MESSAGE_USER_AUTH_OK;  
	      } else {  
	        password = null;  
	        auth = false;  
	        return MESSAGE_USER_AUTH_ERROR;  
	      }  
	    }  
	  }  
	  class SendToCommand implements Command {  
	    public String doIt(String str) {  
	      if (!auth) {  
	        return MESSAGE_AUTH_NEED;  
	      }  
	      title = null;  
	      content = null;  
	      sendtoUsername = str.substring(COMMAND_SENDTO.length()).trim();  
	      if (sendtoUsername == null || sendtoUsername.length() == 0) {  
	        sendtoUsername = null;  
	        return MESSAGE_SENDTO_MISSION;  
	      }  
	      if (!userMap.keySet().contains(sendtoUsername)) {  
	        sendtoUsername = null;  
	        return MESSAGE_SENDTO_NOT_EXISTS;  
	      }  
	      return MESSAGE_SENDTO_OK;  
	    }  
	  }  
	  // #TITLE COMMAND  
	  class TitleCommand implements Command {  
	    public String doIt(String str) {  
	      if (!auth) {  
	        return MESSAGE_AUTH_NEED;  
	      }  
	      if (sendtoUsername == null) {  
	        return MESSAGE_SENDTO_COMMAND_FIRST;  
	      }  
	      content = null;  
	      title = str.substring(COMMAND_TITLE.length()).trim();  
	      if (title == null || title.length() == 0) {  
	        return MESSAGE_TITLE_MISSING;  
	      }  
	      return MESSAGE_TITLE_OK;  
	    }  
	  }  
	  class ContentCommand implements Command {  
	    public String doIt(String str) {  
	      if (!auth) {  
	        return MESSAGE_AUTH_NEED;  
	      }  
	      if (title == null) {  
	        return MESSAGE_TITLE_COMMAND_FIRST;  
	      }  
	      content = str.substring(COMMAND_CONTENT.length()).trim();  
	      if (content == null || content.length() == 0) {  
	        return MESSAGE_CONTENT_MISSING;  
	      }  
	      // save to file  
	      try {  
	        File file = new File(FILEPATH + sendtoUsername + FILENAME_EXT);  
	        if (!file.exists()) {  
	          file.createNewFile();  
	        }  
	        FileWriter fw = new FileWriter(file, true);  
	        fw.write("FROM " + username + BR + BR);  
	        fw.write("TITLE " + title + BR + BR);  
	        fw.write("CONTENT " + content + BR + BR);  
	        fw.flush();  
	        fw.close();  
	      } catch (Exception ex) {  
	        ex.printStackTrace();  
	      }  
	      return MESSAGE_CONTENT_OK;  
	    }  
	  }  
	  class ReceiveCommand implements Command {  
	    public String doIt(String str) {  
	      if (!auth) {  
	        return MESSAGE_AUTH_NEED;  
	      }  
	      StringBuilder builder = new StringBuilder();  
	      try {  
	        File file = new File(FILEPATH + username + FILENAME_EXT);  
	        if (file.exists()) {  
	          BufferedReader reader = new BufferedReader(new FileReader(file));  
	          String line = null;  
	          while ((line = reader.readLine()) != null) {  
	            builder.append(line);  
	            builder.append(BR);  
	          }  
	          reader.close();  
	          // delete file;  
	          file.delete();  
	        } else {  
	          builder.append(MESSAGE_RECEIVE_EMPTY);  
	        }  
	      } catch (Exception ex) {  
	        ex.printStackTrace();  
	        builder.append(MESSAGE_RECEIVE_SAVE_ERROR);  
	      }  
	      return builder.toString();  
	    }  
	  }  
	  static final Map<String, String> userMap = new HashMap<String, String>();  
	  /*
	  static {  
	    try {  
	      File file = new File(FILENAME_CLIENTINFO);  
	      BufferedReader bf = new BufferedReader(new FileReader(file));  
	      String username;  
	      String password;  
	      while ((username = bf.readLine()) != null) {  
	        bf.readLine();  
	        password = bf.readLine();  
	        userMap.put(username, password);  
	        System.out.println(username + "/" + password);  
	        bf.readLine();  
	      }  
	    } catch (Exception ex) {  
	      ex.printStackTrace();  
	      // init error, force to exit  
	      System.exit(1);  
	    }  
	  }  
	  */
	}  
	
