package generics;

/**
 * (generic)泛型method <T> primitive类型会自动boxing成wrapper类型
 * 
 * @author Administrator
 * 
 */
public class GenericMethod {
	public <T> void f(T x) {
		System.out.println(x.getClass().getName());
	}

	public static void main(String[] args) {
		GenericMethod gm = new GenericMethod();
		gm.f("");	//java.lang.String
		gm.f(1);		//java.lang.Integer
		gm.f(1.0);	//java.lang.Double	->浮点数默认是double类型
		gm.f(1.0F);	//java.lang.Float
		gm.f('c');	//java.lang.Character
		gm.f(gm);	//generics.GenericMethod
	}
}