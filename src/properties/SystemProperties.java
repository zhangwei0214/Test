package properties;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class SystemProperties {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties pt = System.getProperties();
		for (Enumeration e = pt.propertyNames(); e.hasMoreElements();) {  
		    String key = (String) e.nextElement();  
		    System.out.println(key + ":" + pt.getProperty(key));  
		}  
		System.out.println("-------------------------");  
	}

}
