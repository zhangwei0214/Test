package reflection;

import java.lang.reflect.Method;


public class CallPrivateMethod {	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class clazzPerson = Class.forName("reflection.Person");
			Object person = clazzPerson.newInstance();
			Method setIdMethod = clazzPerson.getDeclaredMethod("setId",String.class);
			//setIdMethod.setAccessible(true);	//如果是private方法  还是能够通过setAccessible获得访问权限
			Method getIdMethod = clazzPerson.getDeclaredMethod("getId");
			//如果setId方法是private 的话  那么这里invoke报错 (如果是protected是可以调用的,同一个包下面)
			//java.lang.IllegalAccessException: 
			//Class reflection.CallPrivateMethod can not access a member of class reflection.Person with modifiers "private"
			setIdMethod.invoke(person, "111");
			String id = (String)getIdMethod.invoke(person);
			System.out.println("person's id = " + id);
			//output person's id = 111
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


class Person{
	private String id;
	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		return id;
	}
}

