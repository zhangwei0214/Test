package classloader;

class Base{
	public void print(){
		System.out.println("This is Base");
	}
}
public class Test2 extends Base{
	public void print(){
		System.out.println("This is Child");
	}
	
	/**
	 * @param args
	 * 
	 *  output:This is Child 会调用子类的print()方法
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		Test2 object = new Test2();
		
		Base base = (Base)object;
		base.print();
		*/
		
		//output SystemClassLoader sun.misc.Launcher$AppClassLoader@1fa1bb6
		System.out.println("SystemClassLoader "+  ClassLoader.getSystemClassLoader());
	}

}
