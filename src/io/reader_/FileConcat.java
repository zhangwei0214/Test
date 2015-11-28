package io.reader_;

import java.io.BufferedReader;  
import java.io.BufferedWriter;  
import java.io.FileReader;  
import java.io.FileWriter;  
import java.io.IOException;  
import java.util.Iterator;  
   
public class FileConcat {  
   
  /** 
   * 包装类进行文件级联操作 
   */  
  public static void main(String[] args) {  
     // TODO自动生成的方法存根  
     try {  
        //concennateFile(args);  
    	 concennateFile("concat1.txt", "concat2.txt");  
     } catch (IOException e) {  
        // TODO自动生成的 catch 块  
        e.printStackTrace();  
     }  
  }  
  public static void concennateFile(String...fileName) throws IOException{  
     String str;  
     //构建对该文件您的输入流  
     //BufferedWriter writer=new BufferedWriter(new FileWriter("D:/David/Java/java 高级进阶/files/copy2.txt"));  
     BufferedWriter writer=new BufferedWriter(new FileWriter("concat_result.txt")); 
     for(String name: fileName){  
        BufferedReader reader=new BufferedReader(new FileReader(name));  
         
        while ((str=reader.readLine())!=null) {  
           writer.write(str);  
           writer.newLine();  
        }  
        //关闭reader , 会级联关闭包含的资源
        reader.close();
     }  
     
     //关闭writer , 会级联关闭包含的资源
     writer.close();
  }  
   
} 