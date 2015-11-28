package generics;

import java.util.*;
interface Generator<T> { T next(); }

public class CoffeeGenerator implements Generator<Coffee>, Iterable<Coffee> {
	private Class[] types = { Latte.class, Mocha.class, Cappuccino.class,
			Americano.class, Breve.class, };
	private static Random rand = new Random(47);

	public CoffeeGenerator() {
	}

	// For iteration:
	private int size = 0;

	public CoffeeGenerator(int sz) {
		size = sz;
	}

	public Coffee next() {
		try {
			return (Coffee) types[rand.nextInt(types.length)].newInstance();
			// Report programmer errors at run time:
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	class CoffeeIterator implements Iterator<Coffee> {
		int count = size;

		public boolean hasNext() {
			return count > 0;
		}

		public Coffee next() {
			count--;
			return CoffeeGenerator.this.next();
		}

		public void remove() { // Not implemented	//没有实现的方法直接return UnsupportedOperationException()
			throw new UnsupportedOperationException();
		}
	};

	public Iterator<Coffee> iterator() {
		return new CoffeeIterator();
	}

	public static void main(String[] args) {
		CoffeeGenerator gen = new CoffeeGenerator();
		for (int i = 0; i < 5; i++)
			System.out.println(gen.next());
		for (Coffee c : new CoffeeGenerator(5))
			System.out.println(c);
	}
}

class Coffee {
private static long counter = 0;
private final long id = counter++;
public String toString() {
return getClass().getSimpleName() + " " + id;
}
}
class Latte extends Coffee {}
class Mocha extends Coffee {}
class Cappuccino extends Coffee {}
class Americano extends Coffee {}
class Breve extends Coffee {}
