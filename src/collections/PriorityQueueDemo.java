package collections;

import java.util.*;

public class PriorityQueueDemo {
	public static void main(String[] args) {
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>();
		Random rand = new Random(47);
		for (int i = 0; i < 10; i++)
			priorityQueue.offer(rand.nextInt(i + 10));
		QueueDemo.printQ(priorityQueue); //值随机  但是都有顺序	//0 1 1 1 1 1 3 5 8 14  
		List<Integer> ints = Arrays.asList(25, 22, 20, 18, 14, 9, 3, 1, 1, 2,
				3, 9, 14, 18, 21, 23, 25);
		priorityQueue = new PriorityQueue<Integer>(ints);
		QueueDemo.printQ(priorityQueue); //1 1 2 3 3 9 9 14 14 18 18 20 21 22 23 25 25 
		priorityQueue = new PriorityQueue<Integer>(ints.size(),
				Collections.reverseOrder());	//倒序comparator
		priorityQueue.addAll(ints);
		QueueDemo.printQ(priorityQueue);//25 25 23 22 21 20 18 18 14 14 9 9 3 3 2 1 1 
		String fact = "EDUCATION SHOULD ESCHEW OBFUSCATION";
		List<String> strings = Arrays.asList(fact.split(" "));
		PriorityQueue<String> stringPQ = new PriorityQueue<String>(strings);
		QueueDemo.printQ(stringPQ); //EDUCATION ESCHEW OBFUSCATION SHOULD 
		stringPQ = new PriorityQueue<String>(strings.size(),
				Collections.reverseOrder());
		stringPQ.addAll(strings);
		QueueDemo.printQ(stringPQ);	//SHOULD OBFUSCATION ESCHEW EDUCATION 
		Set<Character> charSet = new HashSet<Character>();	//去掉重复值
		for (char c : fact.toCharArray())
			charSet.add(c); // Autoboxing
		PriorityQueue<Character> characterPQ = new PriorityQueue<Character>(
				charSet);
		QueueDemo.printQ(characterPQ);	//  A B C D E F H I L N O S T U W 
	}

}
