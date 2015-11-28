package io.stream_;

import java.io.*;  
/*
 * @see http://blog.csdn.net/yczz/article/details/38761237
 * DataStream可以避免serialize整个object， 只存数据到文件, 注意，member.txt 同样是不可读的
 */
public class DataStreamDemo  
{  
  public static void main(String[]args)  
  {  
     Member[] members = {new Member("Justin",90),  
                        new Member("momor",95),  
                        new Member("Bush",88)};  
        try  
     {  
        //DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(args[0]));  
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("member.txt")); 
        for(Member member:members)  
        {  
            //写入UTF字符串 (String)
           dataOutputStream.writeUTF(member.getName());  
           //写入int数据  
           dataOutputStream.writeInt(member.getAge());  
        }  
   
        //所有数据至目的地   close() will call flush()
        //dataOutputStream.flush();  
        //关闭流 
        dataOutputStream.close();  
   
        //DataInputStream dataInputStream = new DataInputStream(new FileInputStream(args[0]));  
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream("member.txt"));
        //读出数据并还原为对象  
        for(int i=0;i<members.length;i++)  
        {  
           //读出UTF字符串  
           String name =dataInputStream.readUTF();  
           //读出int数据  
           int score =dataInputStream.readInt();  
           members[i] = new Member(name,score);  
        }  
   
        //关闭流  
        dataInputStream.close();  
   
        //显示还原后的数据  
        for(Member member : members)  
        {  
           System.out.printf("%s\t%d%n",member.getName(),member.getAge());  
        }  
     }  
     catch(IOException e)  
     {  
            e.printStackTrace();  
     }  
  }  
}  

class Member {  
    private String name;  
    private int age;  
    public Member() {  
    }  
   public Member(String name, int age) {  
        this.name = name;  
        this.age = age;  
    }  
    public void setName(String name){  
        this.name = name;  
    }  
    public void setAge(int age) {  
        this.age = age;  
    }  
    public String getName() {  
        return name;  
    }  
    public int getAge() {  
        return age;  
    }  
}  
