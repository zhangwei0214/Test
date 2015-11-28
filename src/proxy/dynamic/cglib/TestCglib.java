package proxy.dynamic.cglib;


/**
 * 
 * @see http://www.cnblogs.com/jqyp/archive/2010/08/20/1805041.html
 * @author Administrator
 * 这种方法不需要被代理类实现任何接口
 */
public class TestCglib {  
      
    public static void main(String[] args) {  
        BookFacadeCglib cglib=new BookFacadeCglib();  
        BookFacadeImpl bookCglib=(BookFacadeImpl)cglib.getInstance(new BookFacadeImpl());  
        bookCglib.addBook();  
    }  
}
