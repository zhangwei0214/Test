package override;

/*俺的菜鸟笔记 
Java 中的覆盖@Override注解 写与不写的一点点理解 
一般来说，写与不写没什么区别，JVM可以自识别 
写的情况下：即说明子类要覆盖基类的方法，基类必须存在方法 
                    （控制类型public,protected，返回值，参数列表类型）与子类方法完成一致的方法，否则会报错（找不到被Override的方法）。 
在不写@Override注解的情况下，当基类存在与子类各种条件都符合的方法是即实现覆盖； 
如果条件不符合时，则是当成新定义的方法使用。 
所以如果想覆盖基类方法时，最好还是写上@Override注解，这样有利于编译器帮助检查错误 
*/  
//示例：  
public class OverrideTest extends Test{  
@Override//此处写与不写都能覆盖基类的t(String)方法  
public void t(String s){  
    System.out.println("OverrideTest.t(String):" + s);  
}  
//此处不能写@Override注解，因为方法参数类型与基类的t2方法的参数类型不同  
//所以此处只能新定义了一个t2(float)方法，并不能实现覆盖  
public void t2(float f){  
    System.out.println("OverrideTest.t2(float):" + f);  
}  
public static void main(String[] args){  
    OverrideTest ot = new OverrideTest();  
    ot.t("china34420");  
    ot.t2(1.0f);  
    ot.t2(1);  
    ot.t3();  
}  
}  
/*输出： 
OverrideTest.t(String):china34420 
OverrideTest.t2(float):1.0 
Test.t2(int):1 
OverrideTest.t(String):override 
*/  

class Test{  
public void t(String s){  
    System.out.println("Test.t(String):" + s);  
}  
public void t2(int i){  
    System.out.println("Test.t2(int):" + i);      
}  
public void t3(){  
    t("override");    
}  
}  