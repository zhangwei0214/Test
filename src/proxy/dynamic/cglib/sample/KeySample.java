package proxy.dynamic.cglib.sample;

import net.sf.cglib.core.KeyFactory;

/**
 * KeyFactory 用来创建key -> 用于Maps and Sets
 * 不同的KeyFactory 创建的key hashCode永远不相等
 * @author Administrator
 *
 */
public class KeySample {
	//KeyFactory 只能创造有newInstance方法的 interface实例
    private interface MyFactory {
        public Object newInstance(int a, char[] b, String d);
    }
    public static void main(String[] args) {
        MyFactory f = (MyFactory)KeyFactory.create(MyFactory.class);
        Object key1 = f.newInstance(20, new char[]{ 'a', 'b' }, "hello");
        Object key2 = f.newInstance(20, new char[]{ 'a', 'b' }, "hello");
        Object key3 = f.newInstance(20, new char[]{ 'a', '_' }, "hello");
        
        
        System.out.println(key1.equals(key2));
        System.out.println(key2.equals(key3));
        
        //-------------------------------
        MyFactory f2 = (MyFactory)KeyFactory.create(MyFactory.class);
        Object key4 = f2.newInstance(20, new char[]{ 'a', '_' }, "hello");
        System.out.println("f==f2:"+(f==f2));//不同的key factory
        System.out.println("不同的keyFactory创建的key equals:" + key3.equals(key4));
        System.out.println("key3.hash:"+key3.hashCode());
        System.out.println("key4.hash:"+key4.hashCode());
        System.out.println("不同的keyFactory创建的key hashCode equals:" + (key3.hashCode() == key4.hashCode()));	//true 与jdk描述的不同，不用的
        
        //
//        true
//        false
//        不同的keyFactory创建的key equals:true
//        key3.hash:1030362443
//        key4.hash:1030362443
//        不同的keyFactory创建的key hashCode equals:true
    }
}
