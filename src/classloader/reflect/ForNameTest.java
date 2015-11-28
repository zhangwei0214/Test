package classloader.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ForNameTest {
	public void test(String param){
		System.out.println("test() called! param = " + param);
	}
	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * 
	 * @see java应用.pdf
	 * 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		Class clazz =  Class.forName("classloader.reflect.ForNameTest");
		Method method = clazz.getMethod("test", String.class);
		Object obj = clazz.newInstance();
		
		method.invoke(obj, "str1");
		
	}

}
