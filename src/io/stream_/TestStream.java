package io.stream_;

import java.io.*;
/**
 * 以Stream的方式写string
 * @author Administrator
 *
读取ByteTxt.txt：
按字节读取：-28,-67,-96,-27,-91,-67,104,101,108,108,111,
你好hello
按字符读取：你,好,h,e,l,l,o,
你好hello
你好
读取CharTxt.txt：
按字节读取：-28,-67,-96,-27,-91,-67,104,101,108,108,111,
你好hello
按字符读取：你,好,h,e,l,l,o,
你好hello

 */
public class TestStream {
 public static void main(String[] args) throws Exception {
  
  String str="你好hello"; //用于写入文件的字符串
  byte[] b1=str.getBytes(); //将字符串内容装入字节数组
  byte[] b2=new byte[1024];
  char[] c1=str.toCharArray();//将字符串内容装入字符数组
  char[] c2=new char[1024];
  
  FileOutputStream fsOut=new FileOutputStream("ByteTxt.txt"); //按字节写入ByteTxt.txt
  FileWriter fwOut=new FileWriter("CharTxt.txt");  //按字符写入CharTxt.txt
  fsOut.write(b1);
  fsOut.close();
  fwOut.write(c1);
  fwOut.close();
  
  System.out.println("读取ByteTxt.txt：");
  FileInputStream fsIn1=new FileInputStream("ByteTxt.txt");
  FileReader frIn1= new FileReader("ByteTxt.txt");
  
  System.out.print("按字节读取：");
  int len=fsIn1.read(b2); //将读入的内容全部存入字节数组中
  for(int i=0;i<len;i++){
   System.out.print(b2[i]+",");//b2[i]中存放的是字节码，需将其转换为字符格式
  }
  System.out.println();
  System.out.println(new String(b2,0,len)); //将读取的所有字节转换为String
  
  System.out.print("按字符读取：");
  len=frIn1.read(c2);   //将读入的内容全部存入字符数组中
  for(int i=0;i<len;i++){
   System.out.print(c2[i]+",");
  }
  System.out.println();
  System.out.println(new String(c2,0,len));
  System.out.println(new String(c2,0,2));
  
  System.out.println("读取CharTxt.txt：");
  FileInputStream fsIn2=new FileInputStream("CharTxt.txt");
  FileReader frIn2= new FileReader("CharTxt.txt");
  
  System.out.print("按字节读取：");
  len=fsIn2.read(b2);
  for(int i=0;i<len;i++){
   System.out.print(b2[i]+",");//b2[i]中存放的是字节码，需将其转换为字符格式
  }
  System.out.println();
  System.out.println(new String(b2,0,len));//将读取的所有字符转换为String
  
  System.out.print("按字符读取：");
  len=frIn2.read(c2);
  for(int i=0;i<len;i++){
   System.out.print(c2[i]+",");
  }
  System.out.println();
  System.out.println(new String(c2,0,len));
 }
}