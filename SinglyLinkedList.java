import java.util.NoSuchElementException;

/**
 * A class that provides the ability to manipulate
 * ListNodes, with features such as clear, add, set, get, etc.
 *
 * @author Atul Ummaneni
 * @since May 2, 2022
 */
public class SinglyLinkedList<E extends Comparable<E>> {
	/* global fields */
	private ListNode<E> head, tail; // head and tail pointers

	/**
	 * No-args Constructors
	 */
	public SinglyLinkedList() {
		head = null;
		tail = null;
	}

	/**
	 * Copy constructor
	 */
	public SinglyLinkedList(SinglyLinkedList<E> oldList) {
		ListNode<E> tmp = oldList.head;
		while (tmp.getNext() != null) {
			add(tmp.getNext().getValue());
			tmp = tmp.getNext();
		}
	}

	/**
	 * Clears list
	 */
	public void clear() {
		head = null; // setting the head and tail to null
		tail = null;
	}

	/**
	 * Adds obj to end of List
	 * 
	 * @param obj obj to add
	 * @return true if successful
	 */
	public boolean add(E obj) {
		ListNode<E> x = new ListNode<E>(obj); // new node carrying the object parameter

		if (head == null) {
			head = x;
			tail = x;
			return true;
		} else {
			tail.setNext(x);
			tail = x;
			return true;
		}

	}

	/**
	 * Add the object at the index given
	 * 
	 * @param index the index to add the object
	 * @param obj   the object to add
	 * @return true if successful; false otherwise
	 * @throws NoSuchElementException if index does not exist
	 */
	public boolean add(int index, E obj) {
		ListNode<E> x = head;
		int size = size();
		int count = 0;
		ListNode<E> cur = new ListNode<E>(obj);
		if (index < 0 || index > size) {
			throw new NoSuchElementException();
		} else if (index == 0) {
			if (head == null)
				add(obj);
			else {
				cur.setNext(head);
				head = cur;
			}
		} else {
			if (index == size)
				add(obj);
			else {
				while (count < index - 1) {
					x = x.getNext();
					count++;
				}
				cur.setNext(x.getNext());
				x.setNext(cur);
			}
		}
		return true;

	}

	/** @return size of list */
	public int size() {

		int size = 1;
		ListNode<E> x = head;
		if (head == null)
			return 0;

		while (x.getNext() != null) {
			size++;
			x = x.getNext();
		}
		return size;
	}

	/**
	 * Return the ListNode at the given index
	 * 
	 * @param index given index
	 * @return the ListNode at the given index
	 * @throws NoSuchElementException if index doesn't exist
	 */
	public ListNode<E> get(int index) {
		int size = size();
		int count = 0;
		ListNode<E> x = head;

		if (index >= size || index < 0)
			throw new NoSuchElementException();

		if (index == 0)
			return x;

		while (count < index - 1) {
			x = x.getNext();
			count++;
		}
		return x.getNext();
	}

	/**
	 * Replaces object at the given index
	 * 
	 * @param index the index of the object
	 * @param obj   the object given to replace with
	 * @return replaced obj
	 * @throws NoSuchElementException if index doesn't exist
	 */
	public E set(int index, E obj) {
		int size = size();
		int count = 0;
		ListNode<E> x = head;

		if (index >= size || index < 0)
			throw new NoSuchElementException();

		while (count < index - 1) {
			x = x.getNext();
			count++;
		}

		E y = x.getNext().getValue();
		x.getNext().setValue(obj);
		return y;
	}

	/**
	 * Remove Node at the given index
	 * 
	 * @param index the index of the element
	 * @return removed obj
	 * @throws NoSuchElementException if index doesn't exist
	 */
	public E remove(int index) {
		int size = size();
		int count = 0;
		ListNode<E> x = head;

		if (index >= size || index < 0 || head == null)
			throw new NoSuchElementException();

		if (index == 0) {
			ListNode<E> tmp = head;
			E y1 = head.getValue();
			head = head.getNext();
			tmp.setNext(null);
			return y1;
		}

		while (count < index - 1) {
			x = x.getNext();
			count++;
		}

		E y = x.getNext().getValue();
		x.setNext(x.getNext().getNext());
		return y;
	}

	/**
	 * @return true if list is empty
	 */
	public boolean isEmpty() {
		if (head == null)
			return true;
		return false;
	}

	/**
	 * Checks list to see if the object given is present
	 * 
	 * @param object obj to find
	 * @return true if the object is present
	 */
	public boolean contains(E object) {
		ListNode<E> tmp = head;
		if (head == null)
			return false;

		while (tmp.getNext() != null) {
			if (tmp.getValue().equals(object))
				return true;
			tmp = tmp.getNext();
		}
		if (tmp.getValue().equals(object))
			return true;
		return false;
	}

	/**
	 * Returns the first index of given element
	 * 
	 * @param element element to find
	 * @return index of obj, else return -1
	 */
	public int indexOf(E element) {
		if (head == null)
			return -1;

		ListNode<E> tmp = head;
		int index = 0;

		if (tmp.getValue().equals(element))
			return index;
		while (tmp.getNext() != null) {
			if (tmp.getNext().getValue().equals(element)) {
				index++;
				return index;
			}
			tmp = tmp.getNext();
			index++;
		}
		return -1;
	}

	/**
	 * Prints the list
	 */
	public void printList() {
		ListNode<E> ptr = head;
		while (ptr != null) {
			System.out.print(ptr.getValue() + "; ");
			ptr = ptr.getNext();
		}
	}

}