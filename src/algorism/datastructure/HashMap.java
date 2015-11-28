package algorism.datastructure;

/**
 * 实现简单的HashMap
 * @author Administrator
 * @see http://blog.csdn.net/kimylrong/article/details/21654405
 * Hash算法:
 * Hash的过程利用了Object的hashcode()，也参考了算法导论的乘法Hash算法
 */
public class HashMap<K, V> {  
    private static int DEFAULT_CAPACITY = 16;  
    private static double A = (Math.pow(5, 0.5) - 1) / 2;  
  
    private int capacity;  
    private int size = 0;  
  
    private Node<K, V>[] buckets;  
  
    public HashMap() {  
        this(DEFAULT_CAPACITY);  
    }  
  
    @SuppressWarnings("unchecked")  
    public HashMap(int capacity) {  
        if (capacity <= 0) {  
            throw new IllegalArgumentException(  
                    "capacity can not be negative or zero");  
        }  
  
        // 保证 capacity 是2的n次方  
        int temp = 1;  
        while (temp < capacity) {  
            temp <<= 2;  
        }  
        this.capacity = temp;  
  
        buckets = new Node[this.capacity];  
    }  
  
    public void insert(K key, V value) {  
        if (key == null) {  
            throw new IllegalArgumentException("key can not be null");  
        }  
  
        int position = index(key);  
  
        Node<K, V> node = new Node<K, V>(key, value);  
        if (buckets[position] != null) {  
            node.setNext(buckets[position]);  
        }  
  
        buckets[position] = node;  
        size++;  
    }  
  
    public void put(K key, V value) {  
        if (key == null) {  
            throw new IllegalArgumentException("key can not be null");  
        }  
  
        int position = index(key);  
  
        Node<K, V> node = buckets[position];  
  
        while (node != null) {  
            if (node.key.equals(key)) {  
                node.value = value;  
                return;  
            }  
  
            node = node.next;  
        }  
  
        Node<K, V> newNode = new Node<K, V>(key, value);  
        if (buckets[position] != null) {  
            newNode.setNext(buckets[position]);  
        }  
  
        buckets[position] = newNode;  
        size++;  
    }  
  
    public void delete(K key) {  
        if (key == null) {  
            throw new IllegalArgumentException("key can not be null");  
        }  
  
        int position = index(key);  
        Node<K, V> node = buckets[position];  
  
        if (node == null) {  
            return;  
        }  
  
        if (node.key.equals(key)) {  
            buckets[position] = node.next;  
            size--;  
        }  
  
        while (node.next != null) {  
            if (node.next.key.equals(key)) {  
                node.next = node.next.next;  
                size--;  
                break;  
            }  
  
            node = node.next;  
        }  
    }  
  
    public V search(K key) {  
        if (key == null) {  
            throw new IllegalArgumentException("key can not be null");  
        }  
  
        int position = index(key);  
        Node<K, V> node = buckets[position];  
  
        while (node != null) {  
            if (node.key.equals(key)) {  
                return node.value;  
            }  
  
            node = node.next;  
        }  
  
        return null;  
    }  
  
    public int size() {  
        return size;  
    }  
  
    public boolean isEmpty() {  
        return size == 0;  
    }  
  
    @Override  
    public String toString() {  
        StringBuffer buffer = new StringBuffer();  
        buffer.append("{");  
  
        for (int i = 0; i < capacity; i++) {  
            Node<K, V> node = buckets[i];  
            while (node != null) {  
                buffer.append(node.key + ":" + node.value + ", ");  
                node = node.next;  
            }  
        }  
  
        if (buffer.length() > 1) {  
            buffer.delete(buffer.length() - 2, buffer.length());  
        }  
  
        buffer.append("}");  
  
        return buffer.toString();  
    }  
  
    private int index(K key) {  
        int hashCode = key.hashCode();  
  
        double temp = hashCode * A;  
        double digit = temp - Math.floor(temp);  
  
        return (int) Math.floor(digit * capacity);  
    }  
  
    static class Node<K, V> {  
        private final K key;  
        private V value;  
        private Node<K, V> next;  	//在hash(K) 碰撞的时候用链表来存储
  
        public Node(K key, V value) {  
            this.key = key;  
            this.value = value;  
        }  
  
        public V getValue() {  
            return value;  
        }  
  
        public void setValue(V value) {  
            this.value = value;  
        }  
  
        public Node<K, V> getNext() {  
            return next;  
        }  
  
        public void setNext(Node<K, V> next) {  
            this.next = next;  
        }  
  
        public K getKey() {  
            return key;  
        }  
    }  
  
    public static void main(String[] args) {  
        HashMap<String, String> map = new HashMap<String, String>();  
        map.put("001", "James");  
        map.put("002", "Antony");  
        map.put("003", "Bosh");  
        map.put("004", "Wade");  
        map.put("004", "WestBrook");  
  
        System.out.println(map);  
        System.out.println(map.size());  
        System.out.println(map.search("004"));  
    }  
}
