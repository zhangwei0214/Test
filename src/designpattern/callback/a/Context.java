package designpattern.callback.a;

import designpattern.callback.a.A.Callback;

/**
 * Callback设计模式
 * @author Administrator
 * @see http://cuishen.iteye.com/blog/438428
 */

class A {
    private final Callback callback;  //持有Context对象
    public static interface Callback {  
        public void begin();  
        public void end();  
    }  
    public A(Callback callback) {  
        this.callback = callback;  
    }  
    public void doIt() {  
        callback.begin();  
        System.out.println("do something ...");  
        callback.end();  
    }  
}

public class Context implements A.Callback {  
	  
    private A a;  //A对象
      
    public void begin() {  
        System.out.println("begin ...");  
    }  
  
    public void end() {  
        System.out.println("end ...");  
    }
    
    //Context 实现A.Callback的接口，  构造A的时候传入自己，这样Context 与 A 两个类的对象相互引用
    public Context() {  
        this.a = new A(this);  
    }  
      
    public void doSomething() {  
        this.a.doIt();  
    }  
      
    public static void main(String args[]) {  
        new Context().doSomething();  
    }  
}
