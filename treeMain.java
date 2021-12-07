import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

//Tree class
public class treeMain {
	// private variable
	private static Node root;
	private static ArrayList<Integer> result = new ArrayList<Integer>();

	// Node Class
	class Node {
		public double dData;
		public int iData;
		public Node leftChild;
		public Node rightChild;

		public void displayNode() {
			System.out.print(iData + ", " + dData);
		}
	}

	// Constructor
	public treeMain() {
		root = null;
	}
	

	// in-order traversal
	public void InOrder(Node Localroot) {
		if (Localroot != null) {
			InOrder(Localroot.leftChild);
			System.out.print(Localroot.iData + " ");
			InOrder(Localroot.rightChild);
		}
	}

	// In-order Traversal2 to return the result in an arrayList
	public ArrayList<Integer> InOrder2(Node Localroot) {
		if (Localroot != null) {
			InOrder2(Localroot.leftChild);
			result.add(Localroot.iData);
			InOrder2(Localroot.rightChild);
		}
		return result;
	}

	// Methods
	// A. Find Keys
	public Node findNode(int key) {
		Node current = root;
		while (current.iData != key) {
			if (key < current.iData)
				current = current.leftChild;
			else
				current = current.rightChild;
			if (current == null)
				return null;
		}
		return current;
	}

	// B. Insert A Node
	public void insert(int id, double dd) {
		Node newNode = new Node();
		newNode.iData = id;
		newNode.dData = dd;

		if (root == null)
			root = newNode;
		else {
			Node current = root;
			Node parent;

			while (true) {
				parent = current;
				if (newNode.iData < current.iData) {
					current = current.leftChild;
					if (current == null) {
						parent.leftChild = newNode;
						return;
					}
				} else {
					current = current.rightChild;
					if (current == null) {
						parent.rightChild = newNode;
						return;
					}
				}
			}
		}
	}

	// C.Delete a key
	// assumes the list is not empty
	public boolean delete(int key) {
		Node current = root;
		Node parent = root;

		// keep track of the target position
		boolean isLeftChild = true;

		while (current.iData != key) {
			parent = current;
			// left subtree
			if (key < current.iData) {
				current = current.leftChild;
				isLeftChild = true;
			}
			// right subtree
			else {
				current = current.rightChild;
				isLeftChild = false;
			}
			// last node
			if (current == null)
				return false;
		}

		// if no children, simply delete it
		if (current.leftChild == null && current.rightChild == null) {
			if (current == root)
				root = null;

			else if (isLeftChild)
				parent.leftChild = null;
			else
				parent.rightChild = null;
		}

		// if no right child, replace with left subtree
		else if (current.rightChild == null)
			if (current == root)
				root = current.leftChild;
			else if (isLeftChild)
				parent.leftChild = current.leftChild;
			else
				parent.rightChild = current.leftChild;

		// if no left child, replace with right subtree
		else if (current.leftChild == null)
			if (current == root)
				root = current.rightChild;
			else if (isLeftChild)
				parent.leftChild = current.rightChild;
			else
				parent.rightChild = current.rightChild;

		else // two children, so replace with in-order successor
		{
			// get successor of node to delete
			Node successor = getSuccessor(current);

			// connect parent of current to successor instead
			if (current == root)
				root = successor;
			else if (isLeftChild)
				parent.leftChild = successor;
			else
				parent.rightChild = successor;

			// connect successor to current's left child
			successor.leftChild = current.leftChild;
		}

		return true;
	}

	private static Node getSuccessor(Node delNode) {
		Node successorParent = delNode;
		Node successor = delNode;
		Node current = delNode.rightChild;
		while (current != null) {
			successorParent = successor;
			successor = current;
			current = current.leftChild;
		}

		if (successor != delNode.rightChild) {
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		return successor;
	}
	// end delete()

	// isBST Method
	public static boolean isBST(treeMain testTree) {
		ArrayList<Integer> InOrder = testTree.InOrder2(root);
		int counter=0;
		for (int i = 0; i < InOrder.size() - 1; i++) {
			if (InOrder.get(i) < InOrder.get(i + 1)) {
				counter++;
			} 			
		}
		if(counter+1==InOrder.size()) {
			return true;
		}
		else {
			return false;
		}
		
	}

	// revisit the sample source code just to visualized the tree
	public void displayTree() {
		Stack globalStack = new Stack();
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false;
		System.out.println("......................................................");
		while (isRowEmpty == false) {
			Stack localStack = new Stack();
			isRowEmpty = true;

			for (int j = 0; j < nBlanks; j++)
				System.out.print(' ');

			while (globalStack.isEmpty() == false) {
				Node temp = (Node) globalStack.pop();
				if (temp != null) {
					System.out.print(temp.iData);
					localStack.push(temp.leftChild);
					localStack.push(temp.rightChild);

					if (temp.leftChild != null || temp.rightChild != null)
						isRowEmpty = false;
				} else {
					System.out.print("--");
					localStack.push(null);
					localStack.push(null);
				}
				for (int j = 0; j < nBlanks * 2 - 2; j++)
					System.out.print(' ');
			} // end while globalStack not empty
			System.out.println();
			nBlanks /= 2;
			while (localStack.isEmpty() == false)
				globalStack.push(localStack.pop());
		} // end while isRowEmpty is false
		System.out.println("......................................................");
	}

	public static void main(String[] args) {
		// test cases
		treeMain Tree = new treeMain();

		Tree.insert(15, 1.5);
		Tree.insert(8, 8.8);
		Tree.insert(20, 2.2);
		Tree.insert(2, 1.2);
		Tree.insert(6, 1.6);
		Tree.insert(3, 1.3);
		Tree.insert(7, 1.7);
		Tree.insert(11, 1.1);
		Tree.insert(10, 12.0);
		Tree.insert(12, 0.2);
		Tree.insert(14, 3.4);
		Tree.insert(27, 2.7);
		Tree.insert(22, 4.2);
		Tree.insert(30, 3.0);

		System.out.println("Display Tree");
		Tree.displayTree();
		System.out.println(" ");

		System.out.println("InOder Traversal");
		Tree.InOrder(root);

		System.out.println(" ");
		System.out.println(" ");

		System.out.println("IsBST?");
		System.out.println(isBST(Tree));
		System.out.println(" ");
		
		//assume when use displayNode, the node is still in the tree
		System.out.println("Find Target Node");
		Tree.findNode(2).displayNode();
		System.out.println(" ");

		System.out.println("Delete Target Node");
		System.out.println(Tree.delete(2));
		System.out.println("After Deletion");
		Tree.displayTree();
		
		
	}

}
