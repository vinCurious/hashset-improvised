
/* 
 * HashSetNew.java 
 *   
 */

import java.rmi.NotBoundException;
import java.util.*;

/**
 * This program is implementation of hashset
 * 
 * @author Vinay Vasant More
 *
 */

public class HashSetNew extends HashSet {

	Node[] nodes;
	int size;
	final static int capacity = 200;
	static Node saveCurrent;
	static boolean runOnce = true;

	HashSetNew() {
		nodes = new Node[capacity];
	}

	/**
	 * add method adds element into hashset
	 * 
	 * @param Object
	 *            arg0 element to be added
	 *
	 * @return boolean method returns true if element is added
	 *
	 */
	@Override
	public boolean add(Object arg0) {
		// TODO Auto-generated method stub
		int hash = arg0.hashCode();
		// System.out.println(arg0+"'s hashcode :"+hash);
		if (hash < 0)
			hash = -hash;
		hash = hash % nodes.length;
		// System.out.println(arg0+"'s hash :"+hash);
		Node current = nodes[hash];

		while (current != null) {
			if (current.value.equals(arg0)) {
				return false;
			}
			current = current.next;
		}
		Node newNode = new Node();
		newNode.value = arg0;
		newNode.next = nodes[hash];
		nodes[hash] = newNode;
		size++;

		return true;
	}

	/**
	 * clear method clears the whole hashset
	 * 
	 * @param None
	 *
	 * @return void method
	 *
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		for (int i = 0; i < capacity; i++)
			nodes[i] = null;
		size = 0;
	}

	/**
	 * contains method returns whether the element is there in hashset
	 * 
	 * @param Object
	 *            arg0 element to be searched
	 *
	 * @return boolean method returns true if element is found in hashset
	 *
	 */
	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		int hash = arg0.hashCode();
		if (hash < 0)
			hash = -hash;
		hash = hash % nodes.length;

		Node current = nodes[hash];
		while (current != null) {
			if (current.value.equals(arg0)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	/**
	 * isEmpty method returns whether the hashset is empty or not
	 * 
	 * @param None
	 *
	 * @return boolean method returns true if hashset is empty
	 *
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	/**
	 * remove method removes element from hashset
	 * 
	 * @param Object
	 *            arg0 element to be removed
	 *
	 * @return boolean method returns true if element is removed
	 *
	 */
	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		int hash = arg0.hashCode();
		// System.out.println(arg0+"'s hashCode :"+hash);
		if (hash < 0)
			hash = -hash;
		hash = hash % nodes.length;
		// System.out.println(arg0+"'s hash :"+hash);
		Node current = nodes[hash];

		if (current.value.equals(arg0)) {
			nodes[hash] = current.next;
			size = size - 1;
			return true;
		} else {
			while (current.next != null) {
				if (current.next.value.equals(arg0)) {
					Node tmp = current;
					tmp = tmp.next.next;
					current.next = tmp;
					size = size - 1;
					return true;
				}
				current = current.next;
			}
		}
		return false;
	}

	/**
	 * size method returns size of hashset
	 * 
	 * @param none
	 *
	 * @return returns integer size of the hashset
	 *
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	/**
	 * toString method prints hashset
	 * 
	 * @param None
	 *
	 * @return method returns String representation of hashset
	 *
	 */

	public String toString() {
		String str1 = "";
		for (int i = 0; i < capacity; i++) {
			str1 = str1 + "Node" + i + ": ";
			if (nodes[i] == null) {
				str1 = str1 + "null\n";
			} else {
				Node np = nodes[i];
				String str = "";
				while (np != null) {
					str = str + np.value + " ";
					np = np.next;
				}
				str1 = str1 + str + "\n";
			}
		}
		return str1;
	}

	/**
	 * iterator method iterates through the entire hashset
	 * 
	 * @param None
	 *
	 * @return Iterator generic type
	 *
	 */
	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return new IteratorNew();
	}

	/**
	 * This program is implementation of iterator
	 * 
	 * @author Vinay Vasant More
	 * @author Onkar Deorukhkar
	 *
	 */
	class IteratorNew implements Iterator {

		IteratorNew() {
			if (runOnce) {
				saveCurrent = nodes[0];
				runOnce = false;
			}
		}

		/**
		 * hasNext method decides whether next node is available in hashset
		 * 
		 * @param None
		 *
		 * @return boolean menthod returns true if next node is available in
		 *         hashset
		 *
		 */
		public boolean hasNext() {
			// TODO Auto-generated method stub

			if (saveCurrent.next == null) {
				int hash = saveCurrent.value.hashCode();
				if (hash < 0)
					hash = -hash;
				hash = hash % nodes.length;
				if (hash < capacity - 1) {
					while (hash < capacity - 2 && nodes[hash + 1] == null) {
						hash = hash + 1;
					}
					saveCurrent = nodes[hash + 1];
					return saveCurrent != null;
				}
			}
			return saveCurrent.next != null;
		}

		/**
		 * Next method returns next available node in hashset
		 * 
		 * @param None
		 *
		 * @return method returns next available Node in hashset
		 *
		 */
		public Node next() {
			// TODO Auto-generated method stub
			if (saveCurrent.next != null) {
				// System.out.println(saveCurrent.next.value);
				saveCurrent = saveCurrent.next;
			}
			// System.out.println(saveCurrent.value);
			return saveCurrent;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
		}
	}

	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments
	 */
	public static void main(String args[]) {
		HashSetNew hashSet = new HashSetNew();
		for (int i = 0; i < 200; i++)
			hashSet.add(i);
		System.out.println("HashSet :\n" + hashSet.toString() + "\nSize: " + hashSet.size());

		for (int i = 0; i < 200; i++) {
			System.out.println(hashSet.iterator().hasNext());
			hashSet.iterator().next();
		}

		hashSet.remove(99);
		hashSet.remove(100);
		System.out.println("HashSet :\n" + hashSet.toString() + "\nSize: " + hashSet.size());

	}
}
