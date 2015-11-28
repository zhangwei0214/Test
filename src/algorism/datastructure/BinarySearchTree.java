package algorism.datastructure;

/**
 * 二分查找树 Binary Search Tree
 * 
 * 二叉搜索树(Binary Search Tree)，是最基础，且相对简单的一种数据结构，
 * 支持Insert，Delete，Search，Min，Max，Successor，Predecessor等操作。
 * 最大的特点是每一个节点有不超过两个子节点，并且左子节点小于或者等于父节点，而右节点大于或者等于父节点。
 * 说它基础，是因为很多其它树形数据结构以它为原型而扩展，比如红黑树，B树。
 * @author Administrator
 *
 * @see http://blog.csdn.net/kimylrong/article/details/21872909
 */
public class BinarySearchTree<T extends Comparable<T>> {
	private Node<T> root;

	public void insert(T element) {
		if (element == null) {
			throw new IllegalArgumentException("element can not be null");
		}

		if (root == null) {
			root = new Node<T>(null, element);
		} else {
			Node<T> node = root;
			while (true) {
				if (element.compareTo(node.value) <= 0) {
					if (node.left == null) {
						Node<T> newNode = new Node<T>(node, element);
						node.left = newNode;
						break;
					} else {
						node = node.left;
					}
				} else {
					if (node.right == null) {
						Node<T> newNode = new Node<T>(node, element);
						node.right = newNode;
						break;
					} else {
						node = node.right;
					}
				}
			}
		}
	}

	private int childCount(Node<T> node) {
		if (node == null) {
			throw new IllegalArgumentException("node can not be null");
		}

		int count = 0;

		if (node.left != null) {
			count++;
		}

		if (node.right != null) {
			count++;
		}

		return count;
	}
	

	public void delete(Node<T> node) {
		if (node == null) {
			throw new IllegalArgumentException("node can not be null");
		}

		int childCount = childCount(node);
		Node<T> parentNode = node.parent;

		if (childCount == 0) {
			if (parentNode == null) {
				// node is root
				root = null;
			} else {
				if (node == parentNode.left) {
					parentNode.left = null;
				} else {
					parentNode.right = null;
				}
			}
		} else if (childCount == 1) {
			if (parentNode == null) {
				// node is root, set child of node to be new root
				if (node.left != null) {
					root = node.left;
					node.left.parent = null;
				} else {
					root = node.right;
					node.right.parent = null;
				}
			} else {
				if (node == parentNode.left) {
					if (node.left != null) {
						parentNode.left = node.left;
						node.left.parent = parentNode;
					} else {
						parentNode.left = node.right;
						node.right.parent = parentNode;
					}
				} else {
					if (node.left != null) {
						parentNode.right = node.left;
						node.left.parent = parentNode;
					} else {
						parentNode.right = node.right;
						node.right.parent = parentNode;
					}
				}
			}
		} else {
			// successor has no left child
			Node<T> successor = min(node);

			if (successor != node.right) {
				transplant(successor, successor.right);

				successor.right = node.right;
				node.right.parent = successor;
			}

			transplant(node, successor);

			successor.left = node.left;
			node.left.parent = successor;
		}
	}

	private void transplant(Node<T> u, Node<T> v) {
		if (u == null) {
			throw new IllegalArgumentException("node can not be null");
		}

		if (u.parent == null) {
			root = v;
		} else if (u == u.parent.left) {
			u.parent.left = v;
		} else {
			u.parent.right = v;
		}

		if (v != null) {
			v.parent = u.parent;
		}
	}

	public Node<T> search(T element) {
		if (element == null) {
			throw new IllegalArgumentException("element can not be null");
		}

		Node<T> node = root;
		while (node != null) {
			if (node.value.equals(element)) {
				return node;
			} else if (element.compareTo(node.value) < 0) {
				node = node.left;
			} else {
				node = node.right;
			}
		}

		return null;
	}

	public Node<T> min(Node<T> rootNode) {
		if (rootNode == null) {
			throw new IllegalArgumentException("node can not be null");
		}

		Node<T> node = rootNode;
		while (node.left != null) {
			node = node.left;
		}

		return node;
	}

	public Node<T> min() {
		if (root != null) {
			return min(root);
		} else {
			return null;
		}
	}

	public Node<T> max(Node<T> rootNode) {
		if (rootNode == null) {
			throw new IllegalArgumentException("node can not be null");
		}

		Node<T> node = rootNode;
		while (node.right != null) {
			node = node.right;
		}

		return node;
	}

	public Node<T> max() {
		if (root != null) {
			return max(root);
		} else {
			return null;
		}
	}

	public Node<T> successor(Node<T> node) {
		if (node == null) {
			throw new IllegalArgumentException("node can not be null");
		}

		if (node.right != null) {
			return min(node.right);
		}

		Node<T> processNode = node;
		Node<T> parent = processNode.parent;
		while (parent != null && processNode == parent.right) {
			processNode = parent;
			parent = processNode.parent;
		}

		return parent;
	}

	public Node<T> predecesssor(Node<T> node) {
		if (node == null) {
			throw new IllegalArgumentException("node can not be null");
		}

		if (node.left != null) {
			return max(node.left);
		}

		Node<T> processNode = node;
		Node<T> parent = processNode.parent;
		while (parent != null && processNode == parent.left) {
			processNode = parent;
			parent = processNode.parent;
		}

		return parent;
	}

	public void print() {
		print(root);
	}

	public void print(Node<T> node) {
		if (node == null) {
			return;
		}

		print(node.left);
		System.out.print("  " + node.value.toString() + "  ");
		print(node.right);
	}

	public static class Node<T extends Comparable<T>> {
		private Node<T> parent;
		private Node<T> left;
		private Node<T> right;

		private T value;

		public Node(Node<T> parent, T value) {
			this.parent = parent;
			this.value = value;
		}

		public Node<T> getParent() {
			return parent;
		}

		public void setParent(Node<T> parent) {
			this.parent = parent;
		}

		public Node<T> getLeft() {
			return left;
		}

		public void setLeft(Node<T> left) {
			this.left = left;
		}

		public Node<T> getRight() {
			return right;
		}

		public void setRight(Node<T> right) {
			this.right = right;
		}

		public T getValue() {
			return value;
		}

		public void setValue(T value) {
			this.value = value;
		}
	}

	/**
	  Hello    Money    World  
	  Money
	  Like    Money
	  Hello    Like    World
	 * @param args
	 * 注意 这个二叉查找树并不是平衡树, 算法相对比较直接简单 (B树是平衡树，所有叶子节点都在一个level)
	 */
	public static void main(String[] args) {
		BinarySearchTree<String> tree = new BinarySearchTree<String>();

		tree.insert("Hello");
		tree.insert("World");
		tree.insert("Money");

		tree.print();
		System.out.println();

		Node<String> moneyNode = tree.search("Money");
		tree.print(moneyNode);
		System.out.println();

		tree.insert("Like");
		tree.print(moneyNode);
		System.out.println();
		//删除节点只删除自己，child节点会进行transplant
		tree.delete(moneyNode);
		tree.print();
		System.out.println();
	}
}
