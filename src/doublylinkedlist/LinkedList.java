package doublylinkedlist;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * = doubly linked lists and circularly linked lists =
 * 
 * - the singly linked list does not efficiently support some important operations.
 * 
 *   - For instance, although it is easy to go to the front of the list, 
 *     it is time consuming to go to the end.
 *     
 *   - Although we can easily advance via advance, implementing retreat cannot be done efficiently
 *     with only a next link.
 *   - In some applications that might be crucial.
 *   
 *     - For instance, when designing a text editor, we can maintain the internal image of the file as a  linked list of lines.
 *     - We want to be able to move up just as easily as down in the list,
 *       to insert both before and after a line rather than just after,
 *       and to be able to get to the last line quickly.
 *       
 *     - A moment's thought suggests that to implement this procedure efficiently we should have each node maintain 2 link:
 *     
 *       one to the next node in the list and one to the previous node.
 *       - Then, to make everything symmetric, we should have not only a header
 *         but also a tail.
 *         -> A linked list that allows bidirectional traversal by
 *            sorting 2 links per node is called a doublely linked list.
 *         -> Each node now has 2 links (next and prev),
 *            and searching and moving can easily be performed in both directions.
 *         -> Obviously, there are some important changes from the singly linked list.
 *         
 * 
 * - First, an empty list now consists of a head and tail.
 * - Note that head.prev and tail.next are not needed in the algorithms
 *   and are not initialized.
 *   
 *   - The test for emptiness is now:
 *   
 *     head.next == tail
 *     
 *     or
 *     
 *     tail.prev == head
 *     
 *     - We no longer use null to decide whether an advance has taken us past the end of the list.
 *     - Instead, we have gone past the end if current is either head or tail(recall that we can go in either direction).
 *     - The retreat operation can be implemented by
 *     
 *       current = current.prev
 *       
 *       A empty doubly linked list:
 *       
 *         head -> tail 
 *              <-
 *   
 * - Before describing some of the additional operations that are available,
 *   let us consider how the insertion and removal operation change.
 * - Naturally, we can now do both insertBefore and insertAfter.
 * - Twice as many link moves are involved for insertAfter with doubly linked list as with singly linked lists.
 * - If we write each statement explicitly, we obtain
 * 
 *   newNode = new DoublyLinkedListNode(x);
 *   newNode.prev = current;      // Set x's prev link
 *   newNode.next = current.next; // Set x's next link
 *   newNode.prev.next = newNode; // Set a's next link
 *   newNode.next.prev = newNode; // Set b's prev link
 *   current = newNode;           // Set b's prev link
 *   
 *   
 *   Insertion in a doubly linked list by getting new node and then changing pointers in the order indicated
 *     3      2
 *   a ->  x  ->  b
 *     <-     <-
 *     1      4
 *     
 *     
 * - To do a complete doubly linked list implementation, we need to decide which operations to support.
 * - We can reasonably expect twice as many operations as in the linked list.
 * - Each individual procedure is similar to the linked list routines;
 *   only the dynamic operations involve additional link moves.
 * - Moreover, for many of the routines, the code is dominated by error checks.
 * 
 * - Although some of the checks will change(e.g., we do not test against null),
 *   they certainly do not become any more complex.
 * - We use a doubly linked list to implement the Collections API linked list class,
 *   along with its associated iterators.
 * - There are lots of routines, but most are short.
 * 
 * - A popular convention is to create a circularly linked list,
 *   in which the last cell's next link references first, which can be done with or without a header.
 *   
 *   - Typically, it is done without a header because the header's main purpose
 *     is to ensure that every node has a previous node,
 *     which is already true for a nonempty circularly linked list.
 *     
 * - Without a header, we have only the empty list as a special case.
 * - We maintain a reference to the first node, but that is not the same as a header node.
 * - We can use circularly linked lists and doubly linked lists simultaneously.
 * - The circular list is useful when we want to searching to allow wraparound,
 *   as is the case for some text editors.
 *   
 * = implementing the collections api LinkedList class =
 * 
 * - We need a class to store the basic list node, a class for the iterator, and a class for the list itself.
 * - LinkedList implements the List and Queue interfaces and, as usual, it extends AbstractCollection.
 * 
 * - The iterator pattern was used in the ArrayList implementation.
 * - This list class keeps track of its size in a data member.
 * - We use this approach so that the size method can be performed in constant time.
 * - modCount is used by the iterators to determine if the list has changed
 *   while an iteration is in progress; the same idea was used in ArrayList.
 * 
 * - beginMarker and endMarker correspond to head and tail.
 * 
 *  size()
 *  contains()
 *  
 *  - which is trivial because it calls the private findPos routine that does all the work.
 *  
 *  findPos()
 *  - findPos deals with null values; otherwise, it would be four line of code.
 *  
 *  add()
 *  - shows the various add methods.
 *  - All of these eventually funnel into the last add method,
 *    which splices into the doubly linked list. 
 *  - It requires a private routine, getNode.
 *  
 *  getNode()
 *  - getNode returns a reference to the various get methods, plus a set method.
 *  - There is little special in any of those routines.
 *  - The element method from the Queue interface is not shown.
 *  - The three-parameter version is needed specifically for add and the LinkListIterator constructor;
 *    the more common one-parameter version is used for all other calls to getNode.
 *  - If the index represents a  node in the first half of the list, we strp through the linked list, in the forward direction.
 *  - Otherwise, we go backwards, starting at the end.
 *  
 * remove()
 * - The remove methods funnel through a private remove method.
 *   
 *     
 * iterator factory method
 * - Both return a freshly constructed LinkedListIterator object.
 *          
 */

/**
 * 
 * - the LinkedListIterator, which is perhaps the trickiest part of the whole implementation.
 * 
 * - The iterator maintains a current position.
 * - current represents the node containing the item that is to be returned by a call to next.
 * - Observe that when current is positioned at the endmarker,
 *   a call to next is illegal, but the call to previous should give the first item, going backwards.
 *   
 * - As in the ArrayList, the iterator also maintains the modCount of the list
 *   it is iterating over, initialized at the time the iterator was constructed.
 * - This variable, expextedModCount, can change only if the iterator performs a remove.
 * - lastVisited is used to represent the last node that was visited;
 *   this is used by remove.
 * - If lastVisited is null, the remove is illegal.
 * - Finally, lastMoveWasPrev is true if the last movement of the iterator prior to remove was via previous;
 *   it is false if the last movement was via next.
 *   
 * hasNext()
 * hasPrevious()
 * - Both throw an exception if an external modification to the list has been detected.
 * 
 * next()
 * - The next method advances current after getting the value in the node that is to be returned.
 * - Data fileds lastVisited and lastMoveWasPrev are updated, respectively.
 * 
 * previous()
 * - The implementation of previous is not exactly symmetric, because for previous,
 *   we advance current prior to obtaining the value.
 * - This is evident when one considers that the initial state for backwards iteration
 *   is that current is at the endmarker.
 * 
 * remove()
 * - After the obligatory error checks,
 *   we use the LinkedList remove method to remove the lastVisited node.
 * - The explicit reference to the outer class is required because the iterator remove hides the list remove.
 * - After making lastVisited null, to disallow a second remove, 
 *   we check whether the last operation was next or previous.
 * - In the latter case, we adjust current, to its state prior to the previous/ remove combination.
 *
 */




/**
 * 
 * LinkedList class implements a doubly-linked list.
 *
 */

/**
 * LinkedList class implements a doubly-linked list.
 */
public class LinkedList<AnyType> extends AbstractCollection<AnyType> implements List<AnyType>, Queue<AnyType> {

	private final Node<AnyType> NOT_FOUND = null;

	private int theSize;
	private Node<AnyType> beginMarker;
	private Node<AnyType> endMarker;
	private int modCount = 0;

	/**
	 * Construct an empty LinkedList.
	 */
	public LinkedList() {
		clear();
	}

	/**
	 * Construct a LinkedList with same items as another Collection.
	 */
	public LinkedList(Collection<? extends AnyType> other) {
		clear();
		for (AnyType val : other)
			add(val);
	}

	/**
	 * Change the size of this collection to zero.
	 */
	public void clear() {
		beginMarker = new Node<AnyType>(null, null, null);
		endMarker = new Node<AnyType>(null, beginMarker, null);
		beginMarker.next = endMarker;

		theSize = 0;
		modCount++;
	}

	/**
	 * Returns the number of items in this collection.
	 * 
	 * @return the number of items in this collection.
	 */
	public int size() {
		return theSize;
	}

	/**
	 * Tests if some item is in this collection.
	 *   
	 * @param x any object.
	 * @return true if this collection contains an item equal to x.
	 */
	public boolean contains(Object x) {
		return findPos(x) != NOT_FOUND;
	}

	/**
	 * Returns the position of first item matching x in this collection, or
	 * NOT_FOUND if not found.
	 * 
	 * @param x any object.
	 * @return the position of first item matching x in this collection, or
	 *         NOT_FOUND if not found.
	 */
	private Node<AnyType> findPos(Object x) {
		for (Node<AnyType> p = beginMarker.next; p != endMarker; p = p.next)
			if (x == null) {
				if (p.data == null)
					return p;
			} else if (x.equals(p.data))
				return p;

		return NOT_FOUND;
	}

	/**
	 * Adds an item to this collection, at the end.
	 * 
	 * @param x any object.
	 * @return true.
	 */
	public boolean add(AnyType x) {
		addLast(x);
		return true;
	}

	/**
	 * Adds an item to this collection, at specified position. Items at or after
	 * that position are slid one position higher.
	 * 
	 * @param x   any object.
	 * @param idx position to add at.
	 * @throws IndexOutOfBoundsException if idx is not between 0 and size(),
	 *                                   inclusive.
	 */
	
	/**
	 * 1. get the original node at the index that want to insert
	 * 2. Create a new node with element and prev and next link
	 * 3. set the prev next link and next prev link(p.prev)
	 */
	public void add(int idx, AnyType x) {
		Node<AnyType> p = getNode(idx, 0, size());               
		Node<AnyType> newNode = new Node<AnyType>(x, p.prev, p);
		newNode.prev.next = newNode;
		p.prev = newNode;
		theSize++;
		modCount++;
	}

	/**
	 * Adds an item to this collection, at front. Other items are slid one position
	 * higher.
	 * 
	 * @param x any object.
	 */
	public void addFirst(AnyType x) {
		add(0, x);
	}

	/**
	 * Adds an item to this collection, at end.
	 * 
	 * @param x any object.
	 */
	public void addLast(AnyType x) {
		add(size(), x);
	}

	/**
	 * Returns the front item in the list.
	 * 
	 * @throws NoSuchElementException if the list is empty.
	 */
	public AnyType element() {
		return getFirst();
	}

	/**
	 * Returns the first item in the list.
	 * 
	 * @throws NoSuchElementException if the list is empty.
	 */
	public AnyType getFirst() {
		if (isEmpty())
			throw new NoSuchElementException();
		return getNode(0).data;
	}

	/**
	 * Returns the last item in the list.
	 * 
	 * @throws NoSuchElementException if the list is empty.
	 */
	public AnyType getLast() {
		if (isEmpty())
			throw new NoSuchElementException();
		return getNode(size() - 1).data;
	}

	/**
	 * Returns the item at position idx.
	 * 
	 * @param idx the index to search in.
	 * @throws IndexOutOfBoundsException if index is out of range.
	 */
	public AnyType get(int idx) {
		return getNode(idx).data;
	}

	/**
	 * Changes the item at position idx.
	 * 
	 * @param idx    the index to change.
	 * @param newVal the new value.
	 * @return the old value.
	 * @throws IndexOutOfBoundsException if index is out of range.
	 */
	public AnyType set(int idx, AnyType newVal) {
		Node<AnyType> p = getNode(idx);
		AnyType oldVal = p.data;

		p.data = newVal;
		return oldVal;
	}

	/**
	 * Removes the front item in the queue.
	 * 
	 * @return the front item.
	 * @throws NoSuchElementException if the list is empty.
	 */
	public AnyType remove() {
		return removeFirst();
	}

	/**
	 * Removes the first item in the list.
	 * 
	 * @return the item was removed from the collection.
	 * @throws NoSuchElementException if the list is empty.
	 */
	public AnyType removeFirst() {
		if (isEmpty())
			throw new NoSuchElementException();
		return remove(getNode(0));
	}

	/**
	 * Removes the last item in the list.
	 * 
	 * @return the item was removed from the collection.
	 * @throws NoSuchElementException if the list is empty.
	 */
	public AnyType removeLast() {
		if (isEmpty())
			throw new NoSuchElementException();
		return remove(getNode(size() - 1));
	}

	/**
	 * Removes an item from this collection.
	 * 
	 * @param x any object.
	 * @return true if this item was removed from the collection.
	 */
	public boolean remove(Object x) {
		Node<AnyType> pos = findPos(x);

		if (pos == NOT_FOUND)
			return false;
		else {
			remove(pos);
			return true;
		}
	}

	/**
	 * Gets the Node at position idx, which must range from 0 to size( )-1.
	 * 
	 * @param idx index to search at.
	 * @return internal node corrsponding to idx.
	 * @throws IndexOutOfBoundsException if idx is not between 0 and size()-1,
	 *                                   inclusive.
	 */
	private Node<AnyType> getNode(int idx) {
		return getNode(idx, 0, size() - 1);
	}

	/**
	 * Gets the Node at position idx, which must range from lower to upper.
	 * 
	 * @param idx   index to search at.
	 * @param lower lowest valid index.
	 * @param upper highest valid index.
	 * @return internal node corrsponding to idx.
	 * @throws IndexOutOfBoundsException if idx is not between lower and upper,
	 *                                   inclusive.
	 */
	
	/**
	 * 1. Check the node is at the valid index
	 * 2. If the index is closer to the first index
	 *    -> search from the first
	 * 3. If the index is closer to the last index
	 *    -> search from the last
	 * 
	 */
	private Node<AnyType> getNode(int idx, int lower, int upper) {
		Node<AnyType> p;

		if (idx < lower || idx > upper)
			throw new IndexOutOfBoundsException("getNode index: " + idx + "; size: " + size());

		if (idx < size() / 2) {
			p = beginMarker.next;
			for (int i = 0; i < idx; i++)
				p = p.next;
		} else {
			p = endMarker;
			for (int i = size(); i > idx; i--)
				p = p.prev;
		}

		return p;
	}

	/**
	 * Removes an item from this collection.
	 * 
	 * @param idx the index of the object.
	 * @return the item was removed from the collection.
	 */
	public AnyType remove(int idx) {
		return remove(getNode(idx));
	}

	/**
	 * Removes the object contained in Node p.
	 * 
	 * @param p the Node containing the object.
	 * @return the item was removed from the collection.
	 */
	private AnyType remove(Node<AnyType> p) {
		p.next.prev = p.prev;
		p.prev.next = p.next;
		theSize--;
		modCount++;

		return p.data;
	}

	/**
	 * Obtains an Iterator object used to traverse the collection.
	 * 
	 * @return an iterator positioned prior to the first element.
	 */
	public Iterator<AnyType> iterator() {
		return new LinkedListIterator(0);
	}

	/**
	 * Obtains a ListIterator object used to traverse the collection
	 * bidirectionally.
	 * 
	 * @return an iterator positioned prior to the requested element.
	 * @param idx the index to start the iterator. Use size() to do complete reverse
	 *            traversal. Use 0 to do complete forward traversal.
	 * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
	 *                                   
	 */
	public ListIterator<AnyType> listIteraor(int idx) {

		return new LinkedListIterator(idx);
	}

	/**
	 * This is the implementation of the LinkedListIterator. It maintains a notion
	 * of a current position and of course the implicit reference to the LinkedList.
	 */
	private class LinkedListIterator implements ListIterator<AnyType> {
		private Node<AnyType> current;
		private Node<AnyType> lastVisited = null;
		private boolean lastMoveWasPrev = false;
		private int expectedModCount = modCount;

		public LinkedListIterator(int idx) {
			current = getNode(idx, 0, size());
		}

		public boolean hasNext() {
			if (expectedModCount != modCount)
				throw new ConcurrentModificationException();
			return current != endMarker;
		}

		public AnyType next() {
			if (!hasNext())
				throw new NoSuchElementException();

			AnyType nextItem = current.data;
			lastVisited = current;
			current = current.next;
			lastMoveWasPrev = false;
			return nextItem;
		}

		public void remove() {
			if (expectedModCount != modCount)
				throw new ConcurrentModificationException();
			if (lastVisited == null)
				throw new IllegalStateException();

			LinkedList.this.remove(lastVisited);
			lastVisited = null;
			if (lastMoveWasPrev)
				current = current.next;
			expectedModCount++;
		}

		public boolean hasPrevious() {
			if (expectedModCount != modCount)
				throw new ConcurrentModificationException();
			return current != beginMarker.next;
		}

		public AnyType previous() {
			if (expectedModCount != modCount)
				throw new ConcurrentModificationException();
			if (!hasPrevious())
				throw new NoSuchElementException();

			current = current.prev;
			lastVisited = current;
			lastMoveWasPrev = true;
			return current.data;
		}
	}

}
