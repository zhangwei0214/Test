package proxy.dynamic.cglib.sample;

import java.beans.*;
import java.lang.reflect.*;
import net.sf.cglib.proxy.*;

/**
 *
 * @author  baliuka
 */
public class Beans implements MethodInterceptor {
    
    private PropertyChangeSupport propertySupport;
   
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        
        propertySupport.addPropertyChangeListener(listener);
        
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
    
    public static  Object newInstance( Class clazz ){
        try{
            Beans interceptor = new Beans();
            Enhancer e = new Enhancer();
            e.setSuperclass(clazz);
            //所有对e的方法调用都变成 interceptor -> intercept方法的调用 (super原方法的调用可以在 intercept中调用)
            e.setCallback(interceptor);
            Object bean = e.create();
            interceptor.propertySupport = new PropertyChangeSupport( bean );
            return bean;
        }catch( Throwable e ){
            e.printStackTrace();
            throw new Error(e.getMessage());
        }
        
    }
    
    static final Class C[] = new Class[0];
    static final Object emptyArgs [] = new Object[0];

    /**
     * All generated proxied methods call this method instead of the original method.
     * The original method may either be invoked by normal reflection using the Method object,
     * or by using the MethodProxy (faster).
     * @param obj "this", the enhanced object
     * @param method intercepted Method
     * @param args argument array; primitive types are wrapped
     * @param proxy used to invoke super (non-intercepted method); may be called
     * as many times as needed
     * @throws Throwable any exception may be thrown; if so, super method will not be invoked
     * @return any value compatible with the signature of the proxied method. Method returning void will ignore this value.
     * @see MethodProxy
     */    
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Object retValFromSuper = null;
        try {
        	//如果不是是抽象方法,直接调用super的方法
            if (!Modifier.isAbstract(method.getModifiers())) {
                retValFromSuper = proxy.invokeSuper(obj, args);
            }
        } finally {
            String name = method.getName();
            if( name.equals("addPropertyChangeListener")) {
                addPropertyChangeListener((PropertyChangeListener)args[0]);
            }else if ( name.equals( "removePropertyChangeListener" ) ){
                removePropertyChangeListener((PropertyChangeListener)args[0]);
            }
            //如果是set方法, 则触发propertyChange时间
            if( name.startsWith("set") &&
                args.length == 1 &&
                method.getReturnType() == Void.TYPE ){
            
                char propName[] = name.substring("set".length()).toCharArray();
            
                propName[0] = Character.toLowerCase( propName[0] );
                propertySupport.firePropertyChange( new String( propName ) , null , args[0]);
            
            }
        }
        return retValFromSuper;
    }
    
    public static void main( String args[] ){
        
        Bean  bean =  (Bean)newInstance( Bean.class );
        
        bean.addPropertyChangeListener(
	        new PropertyChangeListener(){
	            public void propertyChange(PropertyChangeEvent evt){
	                System.out.println(evt);
	            }
	        }
        );
        
        bean.setSampleProperty("TEST");
        
        
    }
    
    
}