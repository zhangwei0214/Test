package algorism.hash;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


class MyKey{
	public String str1="1";		//HashCodeBuilder调用String的hashCode
	public Integer int1;	//HashCodeBuilder调用Integer的hashCode
	public int[] intArray=new int[]{1,2,3};	//HashCodeBuilder调用int[]的hashCode
	/**
	 * hashCode
	 * HashCodeBuilder应该适用很多场景，但是要注意的是，如果计算的对象（MyKey）里面有自定义对象，
	 * 那么HashCodeBuilder会直接调用对象的hashCode 方法 -> 如果子对象hashCode没有重写->调用Object.hashCode
	 * ---> 这99%不是我们想要的
	 */
	@Override
	public int hashCode(){
		HashCodeBuilder builder = new HashCodeBuilder();
		return builder.append(str1).append(int1).append(intArray).toHashCode();
	}
	@Override
	public boolean equals(Object obj){
		MyKey key2 = (MyKey)obj;
		EqualsBuilder builder = new EqualsBuilder();
		builder.append(str1, key2.str1);
		builder.append(int1, key2.int1);
		builder.append(intArray, key2.intArray);
		//下面这个一句话完成上面所有的事情
		//return EqualsBuilder.reflectionEquals(this,obj);
		return builder.isEquals();
	}
}

/**
 * 使用apache common lang 的 EqualsBuilder & HashCodeBuilder 在我们的程序里面实现equals&hashCode重写
 * 目的:
 * 1)作为hashMap的Key的时候能够满足 equals & hashCode的约定
 * 2)hashCode借助apache实现的算法控制碰撞率
 * @author Administrator
 *
 */
public class HashCodeTest {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyKey key = new MyKey();
		System.out.println("hashCode:" + key.hashCode());
		
		MyKey key2 = new MyKey();
		key2.str1="1";
		key2.intArray= new int[]{1,2,3};
		System.out.println("equals:" + key.equals(key2));
	}
}
