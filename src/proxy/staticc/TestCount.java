package proxy.staticc;

/** 
 *测试Count类 
 *  
 * @author Administrator 
 *  
 * @see http://www.cnblogs.com/jqyp/archive/2010/08/20/1805041.html
 */  
public class TestCount {  
    public static void main(String[] args) {  
        CountImpl countImpl = new CountImpl();  
        CountProxy countProxy = new CountProxy(countImpl);  
        countProxy.updateCount();  
        countProxy.queryCount();  
  
    }  
}

