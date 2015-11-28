package generics;

import java.util.*;

/**
 * The utility of wildcards in the type system comes partially from the fact that generic types are not covariant.
通配符在类型系统中的作用部分来自其不会发生协变（covariant）这一特性

 * @author Administrator
 *
 */
public class GenericsAndCovariance {
	public static void main(String[] args) {
		// Wildcards allow covariance:
		List<? extends Fruit> flist = new ArrayList<Apple>();
		// Compile Error: can’t add any type of object:
		// flist.add(new Apple());
		// flist.add(new Fruit());
		// flist.add(new Object());
		flist.add(null); // Legal but uninteresting
		// We know that it returns at least Fruit:
		Fruit f = flist.get(0);
	}
}
