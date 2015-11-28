package io.stream_;

import java.io.File;  

import java.io.FileInputStream;  
import java.io.IOException;  
import java.io.InputStream;  

/*
 * @see http://blog.csdn.net/yczz/article/details/38761237
 * 注意使用buffer/不适用buffer的区别
 * 
 */
public class FileSize {  
   /** 
    * 我们写一个检测文件长度的小程序，别看这个程序挺长的，你忽略try catch块后发现也就那么几行而已。 
    */  
   public static void main(String[] args) {  
      //TODO 自动生成的方法存根  
      int count=0;  //统计文件字节长度  
      InputStream is = null;   //文件输入流  
      try{  
          //is=new FileInputStream(new File("D:/David/Java/java 高级进阶/files/tiger.jpg"));  
    	  is=new FileInputStream(new File("IO.jpg")); 
          /*1.new File()里面的文件地址也可以写成D:\\David\\Java\\java 高级进阶\\files\\tiger.jpg,前一个\是用来对后一个 
           * 进行转换的，FileInputStream是有缓冲区的，所以用完之后必须关闭，否则可能导致内存占满，数据丢失。 
          */  
    	  /*
          while(is.read()!=-1) {  //读取文件字节，并递增指针到下一个字节  
             count++;  
          }  
          */
    	  //使用buffer减少IO次数
    	  byte[] buffer=new byte[100];
    	  int readLength = 0;
    	  while((readLength = is.read(buffer))!=-1) {  //读取文件字节，并递增指针到下一个字节
              count+=readLength;  
           } 
          System.out.println("文件Size是： "+count+" 字节");  
      }catch (IOException e) {  
          //TODO 自动生成的 catch 块  
          e.printStackTrace();  
      }finally{  
          try{  
        	  is.close();  
          }catch (IOException e) {  
             //TODO 自动生成的 catch 块  
             e.printStackTrace();  
          }  
      }  
   }
   // result: 文件Size是： 51597 字节
   
}  