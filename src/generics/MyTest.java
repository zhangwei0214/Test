package generics;

import java.util.*;

public class MyTest {

	//List<?> 就是 List<Object>
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//List<?> objList = new ArrayList<Object>();
		List<String> strList = new ArrayList<String>();
		strList.add("str1");
		strList.add("str2");
		
		System.out.println(strList);
	}

}
