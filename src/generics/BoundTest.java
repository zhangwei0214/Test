package generics;

class Parent{
	public void print(){
		System.out.println("this is parent");
	}
}

class Child extends Parent{
	public void print(){
		System.out.println("this is child");
	}
}

class PrintHelper<T extends Parent>{
	T item;
	public PrintHelper(T item){
		this.item=item;
	}
	public void print(){
		item.print();
	}
}

/**
 * 通过 ,T extends Parent, 可以指定容器里面的类型为Parant或者其子类
 * @author Administrator
 *
 */
public class BoundTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new PrintHelper(new Parent()).print();
		new PrintHelper(new Child()).print();
//		this is parent
//		this is child

	}

}
