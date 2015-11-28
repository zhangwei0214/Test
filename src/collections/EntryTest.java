package collections;

import java.util.*;

public class EntryTest {
public static  void main(String args[]){
	Map<String, Integer> map = new HashMap<String, Integer>();  
	map.put("d", 2);  
	map.put("c", 1);  
	map.put("b", 1);  
	map.put("a", 3);  
	  
	List<Map.Entry<String, Integer>> infoIds =  
	    new ArrayList<Map.Entry<String, Integer>>(map.entrySet());  
	  
	//����ǰ  
	System.out.println("\n����ǰ:\n");
	for (int i = 0; i < infoIds.size(); i++) {  
	    String id = infoIds.get(i).toString();  
	    System.out.println(id);  
	}  
	//d 2  
	//c 1  
	//b 1  
	//a 3  
	  
	//����  
	Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {     
	    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {        
	        //return (o2.getValue() - o1.getValue());   
	        return (o1.getKey()).toString().compareTo(o2.getKey());  
	    }  
	});   
	  
	//�����  
	System.out.println("\n�����:\n");
	for (int i = 0; i < infoIds.size(); i++) {  
	    String id = infoIds.get(i).toString();  
	    System.out.println(id);  
	}  
}
}
