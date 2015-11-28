package classloader;

public class ClassLoaderDemo {

	/**
	 * reference java应用.pdf
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("ClassLoaderDemo.class.getClassLoader() : " + ClassLoaderDemo.class.getClassLoader());
		System.out.println("ClassLoaderDemo.class.getClassLoader().getParent() : " + ClassLoaderDemo.class.getClassLoader().getParent());
		System.out.println("ClassLoaderDemo.class.getClassLoader().getParent().getParent() : " + ClassLoaderDemo.class.getClassLoader().getParent().getParent());
		System.out.println("String.class.getClassLoader() : " + String.class.getClassLoader());
	}
}
