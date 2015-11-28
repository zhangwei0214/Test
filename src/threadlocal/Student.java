package threadlocal;

/**
 * Created by IntelliJ IDEA.
 * User: leizhimin
 * Date: 2007-11-23
 * Time: 10:45:02
 * 创建一个Bean，通过不同的线程对象设置Bean属性，保证各个线程Bean对象的独立性
 */
public class Student {
    private int age = 0;   //年龄
 
    public int getAge() {
        return this.age;
    }
 
    public void setAge(int age) {
        this.age = age;
    }
}