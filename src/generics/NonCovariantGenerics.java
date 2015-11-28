package generics;

import java.util.ArrayList;
import java.util.List;

public class NonCovariantGenerics {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//编译报错 泛型的检查与数组严格， 将运行时错误 @see CovariantArrays.java
		// -> 转移到编译器
		//List<Fruit> flist = new ArrayList<Apple>();
	}

}
