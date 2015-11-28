package io.reader_;

import java.io.*;
public class ConsoleRead{

public static void main(String[] args){
   BufferedReader bf = null;/*BufferedReader相当于一个大桶，其实就是内存，这里实现了大量大量的读写 ，而不是读一个字节或字符就直接写如硬盘，加强了对硬盘的保护。*/
   try{
    while(true){ // while(true){}循环保证程序不会结束
    
       bf = new BufferedReader(new InputStreamReader(System.in));
       /*System.in 为标准输入，System.out为标准输出*/
       /*InputStreamReader用语将字节流到字符流的转化，这也就是处理流了
        *在这里相当与2个管道接在System.in与程序之间。
        *readLine()方法功能比较好用，也就通过处理流来实现更好功能。
        **/
     String line = bf.readLine();
     System.out.println(line);
    }  
   }catch(Exception e){
    e.printStackTrace();
   }finally{
    //一定要关闭流，用完后。最好放在filally 里面。  
    try{   
     if(bf!=null){
      bf.close();	//reader close 会抛出IOException
     }
    }catch(Exception e){
       e.printStackTrace();
    }  
   }  
}
}