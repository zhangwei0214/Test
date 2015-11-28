package misc;

/**
 * java里面有几个标记性接口，表明某种身份但是没有method定义
 * 
java.io.Serializable
未实现此接口的类将无法使其任何状态序列化或反序列化.为保证 serialVersionUID 值跨不同 java 编译器实现的一致性,序列化类必须声明一个明确的 serialVersionUID 值.
java.lang.Cloneable
表明Object.clone()方法可以合法地对该类实例进行按字段复制.实现此接口的类应该使用公共方法重写 Object.clone（它是受保护的）.如果在没有实现 Cloneable 接口的实例上调用 Object 的 clone 方法,则会导致抛出 CloneNotSupportedException 异常.
java.util.RandomAccess
用来表明其支持快速（通常是固定时间）随机访问.此接口的主要目的是允许一般的算法更改其行为,从而在将其应用到随机或连续访问列表时能提供良好的性能.
java.rmi.Remote                            
Remote 接口用于标识其方法可以从非本地虚拟机上调用的接口.任何远程对象都必须直接或间接实现此接口.只有在“远程接口”（扩展 java.rmi.Remote 的接口）中指定的这些方法才可远程使用.
 *
 */
class A implements Cloneable{
	public A copy() throws CloneNotSupportedException{
		return (A)super.clone();	//which is OB
	}
}

public class CloneTest {
	public static void main(String[] args) throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		//只有A自身能够调用父类Object的clone()方法
		//new A().clone();	//The method clone() from the type Object is not visible
		
		//如果A没有implements Clonable 接口，将抛出以下异常
		// java.lang.CloneNotSupportedException: misc.A
		new A().copy();
	}

}
