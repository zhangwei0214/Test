package classloader;

public class ClassNotFoundDemo {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		Class clazzA = Class.forName("classloader.A");
		A a = (A)clazzA.newInstance();
		a.print();
		
	}

}
