package proxy.dynamic.jdk;

/**
 * @author Administrator
 *
 * @see http://www.cnblogs.com/jqyp/archive/2010/08/20/1805041.html
 * 
 */
public class TestProxy {  
	  
    public static void main(String[] args) {  
        BookFacadeProxy proxy = new BookFacadeProxy();  
        BookFacade bookProxy = (BookFacade) proxy.bind(new BookFacadeImpl());  
        bookProxy.addBook();  
    }  
  
}