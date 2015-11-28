package classloader.custom;

/** 
 * 测试类 
 * http://blog.csdn.net/chenjiazhan/article/details/37714401
 * 
输入任意字符进行热加载，直接敲回车键退出程序
1
彪悍的人生不需要解释是谁说的？

--这个时候替换Printer.class文件  改动餐开Printer.java中注释的部分

输入任意字符进行热加载，直接敲回车键退出程序
2
是谁说的？
输入任意字符进行热加载，直接敲回车键退出程序


思考: 可以动态的检测class文件有没有变化(MD5?) ,变化的话就重新load
 */  
public class Printer implements IPrinter {  
    @Override  
    public void print() {  
        System.out.println("彪悍的人生不需要解释是谁说的？");  
    	//System.out.println("是谁说的？");  
    }  
    
    public static void main(String[] args){
    	IPrinter printer = new Printer();
    	printer.print();
    }
}  