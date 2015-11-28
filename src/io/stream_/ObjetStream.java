package io.stream_;

import java.io.*;  

/*
 * @see http://blog.csdn.net/yczz/article/details/38761237
 * 注意学习对象钝化(serialization 到磁盘里面之后的形式 student.txt -> 二进制  不可读)
 */
public class ObjetStream {  
   /** 
    * @param args 
    */  
   public static void main(String[] args) {  
      // TODO自动生成的方法存根  
      ObjectOutputStream oos=null;  
      ObjectInputStream ois=null;  
       
      try {  
    	  //oos=new ObjectOutputStream(new FileOutputStream("D:/David/Java/java 高级进阶/files/student.txt"));
    	  //注意，一个文件可以存放多个object
    	  oos=new ObjectOutputStream(new FileOutputStream("student.txt"));  
    	  oos.writeObject(new Student("gg", 22));  
    	  oos.writeObject(new Student("tt", 18));  
    	  oos.writeObject(new Student("rr", 17));  
    	  //ois=new ObjectInputStream(new FileInputStream("D:/David/Java/java 高级进阶/files/student.txt"));
    	  ois=new ObjectInputStream(new FileInputStream("student.txt"));  
         for (int i = 0; i < 3; i++) {  
            System.out.println(ois.readObject());  
         }  
      } catch (IOException | ClassNotFoundException e) {  
         // TODO自动生成的 catch 块  
         e.printStackTrace();  
      }finally{  
         try {  
        	 ois.close();  
        	 oos.close();  
         } catch (IOException e) {  
            // TODO自动生成的 catch 块  
            e.printStackTrace();  
         }  
          
      }  
       
   }  
   
}  
/*
 * need to implement Serializable, otherwise -> java.io.NotSerializableException occurs
 */
class Student implements Serializable{  
   private String name;  
   private int age;  
    
   public Student(String name, int age) {  
      super();  
      this.name = name;  
      this.age = age;  
   }  
   
   @Override  
   public String toString() {  
      return "Student [name=" + name + ", age=" + age + "]";  
   }  
    
    
}  
/*
result:
Student [name=gg, age=22]
Student [name=tt, age=18]
Student [name=rr, age=17]

*/