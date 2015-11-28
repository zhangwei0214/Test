package io.reader_;

import java.io.*;           
/*
 * 控制台输入输出(通过reader)
 * @see http://www.cnblogs.com/pepcod/archive/2013/01/20/2913435.html
 * 
 */
public class ConsoleEcho {                              
  public static void main(String[] args) {       
    BufferedReader in =                          
      new BufferedReader(                        
        new InputStreamReader(System.in));       //System.in -> InputStream
    String s;                                    
    try {                                        
      while((s = in.readLine()).length() != 0)   
        System.out.println(s);                   
      // An empty line terminates the program    
    } catch(IOException e) {                     
      e.printStackTrace();                       
    }                                            
  }                                              
}                                                
