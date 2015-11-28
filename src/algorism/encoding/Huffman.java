package algorism.encoding;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Huffman 编码  按照字符出现的频率, 对字符串进行编码, 出现频率高的字符用短编码，出现频率低的用长编码
 * 目的: 最终字符串的二进制编码长度有效缩短

Original sstring: Huffman codes compress data very effectively: savings of 20% to 90% are typical, depending on the characteristics of the data being compressed. 中华崛起
Huffman encoed binary string: 0101010010011111101111011110001100110111010110000111111001011110101100001111000010001111000101110111101111111100100111001011101000011111010001110100111101111010010110100100001101000010100101000111000011101011111001101000000110111101010111101000111101101100010010000001011110110010001101010100010000001011110111001111000110110011000110100000000110110001001001010011011111100101000001110111111100001101111010110100011101110110011110010011010110111001110011110110001101001001111100000011110010000011001111010001111011011001111001001101111111100100111001010101100001000011011110101101011000011110000100011110001011101110011111101011011010101011010011010000101000101
decoded string from binariy string: Huffman codes compress data very effectively: savings of 20% to 90% are typical, depending on the characteristics of the data being compressed. 中华崛起
binary string of UTF-8: 010010000111010101100110011001100110110101100001011011100010000001100011011011110110010001100101011100110010000001100011011011110110110101110000011100100110010101110011011100110010000001100100011000010111010001100001001000000111011001100101011100100111100100100000011001010110011001100110011001010110001101110100011010010111011001100101011011000111100100111010001000000111001101100001011101100110100101101110011001110111001100100000011011110110011000100000001100100011000000100101001000000111010001101111001000000011100100110000001001010010000001100001011100100110010100100000011101000111100101110000011010010110001101100001011011000010110000100000011001000110010101110000011001010110111001100100011010010110111001100111001000000110111101101110001000000111010001101000011001010010000001100011011010000110000101110010011000010110001101110100011001010111001001101001011100110111010001101001011000110111001100100000011011110110011000100000011101000110100001100101001000000110010001100001011101000110000100100000011000100110010101101001011011100110011100100000011000110110111101101101011100000111001001100101011100110111001101100101011001000010111000100000111001001011100010101101111001011000110110001110111001011011010010011011111010001011010110110111
binary string of UTF-16: 11111110111111110000000001001000000000000111010100000000011001100000000001100110000000000110110100000000011000010000000001101110000000000010000000000000011000110000000001101111000000000110010000000000011001010000000001110011000000000010000000000000011000110000000001101111000000000110110100000000011100000000000001110010000000000110010100000000011100110000000001110011000000000010000000000000011001000000000001100001000000000111010000000000011000010000000000100000000000000111011000000000011001010000000001110010000000000111100100000000001000000000000001100101000000000110011000000000011001100000000001100101000000000110001100000000011101000000000001101001000000000111011000000000011001010000000001101100000000000111100100000000001110100000000000100000000000000111001100000000011000010000000001110110000000000110100100000000011011100000000001100111000000000111001100000000001000000000000001101111000000000110011000000000001000000000000000110010000000000011000000000000001001010000000000100000000000000111010000000000011011110000000000100000000000000011100100000000001100000000000000100101000000000010000000000000011000010000000001110010000000000110010100000000001000000000000001110100000000000111100100000000011100000000000001101001000000000110001100000000011000010000000001101100000000000010110000000000001000000000000001100100000000000110010100000000011100000000000001100101000000000110111000000000011001000000000001101001000000000110111000000000011001110000000000100000000000000110111100000000011011100000000000100000000000000111010000000000011010000000000001100101000000000010000000000000011000110000000001101000000000000110000100000000011100100000000001100001000000000110001100000000011101000000000001100101000000000111001000000000011010010000000001110011000000000111010000000000011010010000000001100011000000000111001100000000001000000000000001101111000000000110011000000000001000000000000001110100000000000110100000000000011001010000000000100000000000000110010000000000011000010000000001110100000000000110000100000000001000000000000001100010000000000110010100000000011010010000000001101110000000000110011100000000001000000000000001100011000000000110111100000000011011010000000001110000000000000111001000000000011001010000000001110011000000000111001100000000011001010000000001100100000000000010111000000000001000000100111000101101010100110100111001011101000110111000110101110111
binary string of US-ASCII: 01001000011101010110011001100110011011010110000101101110001000000110001101101111011001000110010101110011001000000110001101101111011011010111000001110010011001010111001101110011001000000110010001100001011101000110000100100000011101100110010101110010011110010010000001100101011001100110011001100101011000110111010001101001011101100110010101101100011110010011101000100000011100110110000101110110011010010110111001100111011100110010000001101111011001100010000000110010001100000010010100100000011101000110111100100000001110010011000000100101001000000110000101110010011001010010000001110100011110010111000001101001011000110110000101101100001011000010000001100100011001010111000001100101011011100110010001101001011011100110011100100000011011110110111000100000011101000110100001100101001000000110001101101000011000010111001001100001011000110111010001100101011100100110100101110011011101000110100101100011011100110010000001101111011001100010000001110100011010000110010100100000011001000110000101110100011000010010000001100010011001010110100101101110011001110010000001100011011011110110110101110000011100100110010101110011011100110110010101100100001011100010000000111111001111110011111100111111
binary string of GB2312: 0100100001110101011001100110011001101101011000010110111000100000011000110110111101100100011001010111001100100000011000110110111101101101011100000111001001100101011100110111001100100000011001000110000101110100011000010010000001110110011001010111001001111001001000000110010101100110011001100110010101100011011101000110100101110110011001010110110001111001001110100010000001110011011000010111011001101001011011100110011101110011001000000110111101100110001000000011001000110000001001010010000001110100011011110010000000111001001100000010010100100000011000010111001001100101001000000111010001111001011100000110100101100011011000010110110000101100001000000110010001100101011100000110010101101110011001000110100101101110011001110010000001101111011011100010000001110100011010000110010100100000011000110110100001100001011100100110000101100011011101000110010101110010011010010111001101110100011010010110001101110011001000000110111101100110001000000111010001101000011001010010000001100100011000010111010001100001001000000110001001100101011010010110111001100111001000000110001101101111011011010111000001110010011001010111001101110011011001010110010000101110001000001101011011010000101110111010101011100001110010001100011011110000

 * @author Administrator
 *
 */
public class Huffman {
	static class Tree {  
        private Node root;  
  
        public Node getRoot() {  
            return root;  
        }  
  
        public void setRoot(Node root) {  
            this.root = root;  
        }  
    }  
  
    static class Node implements Comparable<Node> {  
        private String chars = "";  
        private int frequence = 0;  
        private Node parent;  
        private Node leftNode;  
        private Node rightNode;  
  
        @Override  
        public int compareTo(Node n) {  
            return frequence - n.frequence;  
        }  
  
        public boolean isLeaf() {  
            return chars.length() == 1;  
        }  
  
        public boolean isRoot() {  
            return parent == null;  
        }  
  
        public boolean isLeftChild() {  
            return parent != null && this == parent.leftNode;  
        }  
  
        public int getFrequence() {  
            return frequence;  
        }  
  
        public void setFrequence(int frequence) {  
            this.frequence = frequence;  
        }  
  
        public String getChars() {  
            return chars;  
        }  
  
        public void setChars(String chars) {  
            this.chars = chars;  
        }  
  
        public Node getParent() {  
            return parent;  
        }  
  
        public void setParent(Node parent) {  
            this.parent = parent;  
        }  
  
        public Node getLeftNode() {  
            return leftNode;  
        }  
  
        public void setLeftNode(Node leftNode) {  
            this.leftNode = leftNode;  
        }  
  
        public Node getRightNode() {  
            return rightNode;  
        }  
  
        public void setRightNode(Node rightNode) {  
            this.rightNode = rightNode;  
        }  
    }
    
    public static Map<Character, Integer> statistics(char[] charArray) {  
        Map<Character, Integer> map = new HashMap<Character, Integer>();  
        for (char c : charArray) {  
            Character character = new Character(c);  
            if (map.containsKey(character)) {  
                map.put(character, map.get(character) + 1);  
            } else {  
                map.put(character, 1);  
            }  
        }  
  
        return map;  
    } 
    
    private static Tree buildTree(Map<Character, Integer> statistics,  
            List<Node> leafs) {  
        Character[] keys = statistics.keySet().toArray(new Character[0]);  
  
        PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>();  
        for (Character character : keys) {  
            Node node = new Node();  
            node.chars = character.toString();  
            node.frequence = statistics.get(character);  
            priorityQueue.add(node);  
            leafs.add(node);  
        }  
  
        int size = priorityQueue.size();  
        for (int i = 1; i <= size - 1; i++) {  
            Node node1 = priorityQueue.poll();  
            Node node2 = priorityQueue.poll();  
  
            Node sumNode = new Node();  
            sumNode.chars = node1.chars + node2.chars;  
            sumNode.frequence = node1.frequence + node2.frequence;  
  
            sumNode.leftNode = node1;  
            sumNode.rightNode = node2;  
  
            node1.parent = sumNode;  
            node2.parent = sumNode;  
  
            priorityQueue.add(sumNode);  
        }  
  
        Tree tree = new Tree();  
        tree.root = priorityQueue.poll();  
        return tree;  
    }  
    
    public static String encode(String originalStr,  
            Map<Character, Integer> statistics) {  
        if (originalStr == null || originalStr.equals("")) {  
            return "";  
        }  
  
        char[] charArray = originalStr.toCharArray();  
        List<Node> leafNodes = new ArrayList<Node>();  
        buildTree(statistics, leafNodes);  
        Map<Character, String> encodInfo = buildEncodingInfo(leafNodes);  
  
        StringBuffer buffer = new StringBuffer();  
        for (char c : charArray) {  
            Character character = new Character(c);  
            buffer.append(encodInfo.get(character));  
        }  
  
        return buffer.toString();  
    }  
    private static Map<Character, String> buildEncodingInfo(List<Node> leafNodes) {  
        Map<Character, String> codewords = new HashMap<Character, String>();  
        for (Node leafNode : leafNodes) {  
            Character character = new Character(leafNode.getChars().charAt(0));  
            String codeword = "";  
            Node currentNode = leafNode;  
  
            do {  
                if (currentNode.isLeftChild()) {  
                    codeword = "0" + codeword;  
                } else {  
                    codeword = "1" + codeword;  
                }  
  
                currentNode = currentNode.parent;  
            } while (currentNode.parent != null);  
  
            codewords.put(character, codeword);  
        }  
  
        return codewords;  
    }
    
    public static String decode(String binaryStr,  
            Map<Character, Integer> statistics) {  
        if (binaryStr == null || binaryStr.equals("")) {  
            return "";  
        }  
  
        char[] binaryCharArray = binaryStr.toCharArray();  
        LinkedList<Character> binaryList = new LinkedList<Character>();  
        int size = binaryCharArray.length;  
        for (int i = 0; i < size; i++) {  
            binaryList.addLast(new Character(binaryCharArray[i]));  
        }  
  
        List<Node> leafNodes = new ArrayList<Node>();  
        Tree tree = buildTree(statistics, leafNodes);  
  
        StringBuffer buffer = new StringBuffer();  
  
        while (binaryList.size() > 0) {  
            Node node = tree.root;  
  
            do {  
                Character c = binaryList.removeFirst();  
                if (c.charValue() == '0') {  
                    node = node.leftNode;  
                } else {  
                    node = node.rightNode;  
                }  
            } while (!node.isLeaf());  
  
            buffer.append(node.chars);  
        }  
  
        return buffer.toString();  
    } 
    
    public static void main(String[] args) {  
        String oriStr = "Huffman codes compress data very effectively: savings of 20% to 90% are typical, "  
                + "depending on the characteristics of the data being compressed. 中华崛起";  
        Map<Character, Integer> statistics = statistics(oriStr.toCharArray());  
        String encodedBinariStr = encode(oriStr, statistics);  
        String decodedStr = decode(encodedBinariStr, statistics);  
  
        System.out.println("Original sstring: " + oriStr);  
        System.out.println("Huffman encoed binary string: " + encodedBinariStr);  
        System.out.println("decoded string from binariy string: " + decodedStr);  
  
        System.out.println("binary string of UTF-8: "  
                + getStringOfByte(oriStr, Charset.forName("UTF-8")));  
        System.out.println("binary string of UTF-16: "  
                + getStringOfByte(oriStr, Charset.forName("UTF-16")));  
        System.out.println("binary string of US-ASCII: "  
                + getStringOfByte(oriStr, Charset.forName("US-ASCII")));  
        System.out.println("binary string of GB2312: "  
                + getStringOfByte(oriStr, Charset.forName("GB2312")));  
    }  
  
    public static String getStringOfByte(String str, Charset charset) {  
        if (str == null || str.equals("")) {  
            return "";  
        }  
  
        byte[] byteArray = str.getBytes(charset);  
        int size = byteArray.length;  
        StringBuffer buffer = new StringBuffer();  
        for (int i = 0; i < size; i++) {  
            byte temp = byteArray[i];  
            buffer.append(getStringOfByte(temp));  
        }  
  
        return buffer.toString();  
    }  
  
    public static String getStringOfByte(byte b) {  
        StringBuffer buffer = new StringBuffer();  
        for (int i = 7; i >= 0; i--) {  
            byte temp = (byte) ((b >> i) & 0x1);  
            buffer.append(String.valueOf(temp));  
        }  
  
        return buffer.toString();  
    }
    
}
