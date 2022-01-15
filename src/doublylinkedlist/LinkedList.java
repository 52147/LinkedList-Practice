package doublylinkedlist;

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
 *          
 *          
 *          
 *         
 *      
 *   
 *     
 *   
 *       
 * 
 *
 */

/**
 * 
 * LinkedList class implements a doubly-linked list.
 *
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
		for(AnyType val: other)
			add(val);
	}
	
	/**
	 * Change the size of this collection to 0.
	 */
	public void clear() {
		beginMarker = new Node<AnyType>(null, null, null);
		endMarker = new Node<AnyType>(null, beginMarker, null);
		beginMarker.next = endMarker;
		
		theSize = 0;
		modCount++;
		
	}
	
	/**
	 * Return the number of items in this collection.
	 * @return the number of item in this collection.
	 */
	public int size() {
		return theSize;
	}
	
	/**
	 * Tests if some item is in this collection.
	 * @param x any object.
	 * @return true if this collection contains an item equal to x.
	 */
	public boolean contains(Object x) {
		return findPos(x) != NOT_FOUND;
	}
	
	/**
	 * Return the position of first item matching x in this collection,
	 * or NOT_FOUND if not found.
	 * @param x any object
	 * @return the position of first item matching x in this collection, or NOT_FOUND if not found.
	 */
	private Node<AnyType> findPos(Object x){
		
		for(Node<AnyType> p = beginMarker.next.next; p != endMarker; p = p.next) {
		
		if(x == null) {
			if(p.data == null)
				return p;
		}
		else if(x.equals(p.data))
			return p;
		}
		return NOT_FOUND;
	}
	
	/**
	 * Adds an item to this collection, at the end.
	 * @param x any objct.
	 * @return true.
	 */
	public boolean add(AnyType x) {
		addLast();
		return true;
	}
	
	/**
	 * Add an item to this collection, at specified position.
	 * Items at or after that position are slide one position higher.
	 * @param x any object.
	 * @param idx position to add at.
	 * @throws IndexOutBoundsException if idx is not between 0 and size(), inclusive.
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
	 * Adds an item to this collection, at front.
	 * Other items are slid one position higher.
	 * @param x any object.
	 */
	public void addFirst(AnyType x) {
		add(0, x);
	}
	
	/**
	 * Add an item to this collection, at end.
	 * @param x any object.
	 */
	public void addLast(AnyType x) {
		add(size(), x);
	}
	
	/**
	 * Returns the front item in the list.
	 * @throws NoSuchElementException if the list is empty.
	 */
	public AnyType element() {
		
		return getFirst();
	}
	
	/**
	 * Return the first item in the list.
	 * @throws NoSuchElementException if the list is empty.
	 */
	public AnyType getFirst() {
		if(isEmpty())
			throw new NoSuchElementException();
		return getNode(0).data;
	}
	
	/**
	 * Returns the last item in the list.
	 * @throws NoSuchElementException if the list is empty.
	 */
	public AnyType getLast() {
		
		if(isEmpty())
			throw new NoSuchElementException();
		return getNode(size() - 1).data;
	}
	
	/**
	 * Returns the item at position idx.
	 * @param idx the index to search in
	 * @throws IndexOutOfBoundsException if index is out of range.
	 */
	public AnyType get(int idx) {
		return getNode(idx).data;
	}
	
	/**
	 * Changes the item at position idx.
	 * @param idx the index to change.
	 * @param newVal the new value.
	 * @return the old value.
	 * @throws IndexOutOfBoundsException if index is out of range.
	 */
	public AnyType set(int idx, AnyType newVal) {
		Node<AnyType> p  = getNode(idx);
		
		AnyType oldVal = p.data;
		p.data = newVal;
		return oldVal;
	}
	
	/**
	 * Remove the front item in the queue.
	 * @return the front item.
	 * @throws NoSuchElementException if the list is empty.
	 */
	public AnyType remove() {
		return removeFirst();
	}
	
	/**
	 * Removes the first item in the list.
	 * @return the item was removed from the collection.
	 * @throws NoSuchElementException if the list is empty.
	 */
	public AnyType removeFirst() {
		if(isEmpty())
			throw new NoSuchElementException();
		return remove(getNode(0));
	}
	
	
	/**
	 * Removes the last item in the list.
	 * @return the item was removed from the collection.
	 * @throws NoSuchElementException if the list is empty.
	 */
	public AnyType removeLast() {
		
		if(isEmpty())
			throw new NoSuchElementException();
		return remove(getNode(size() - 1));
	}
	
	/**
	 * Removes an item from this collection.
	 * @param x any object.
	 * @return true if this item was removed from the collection.
	 */
	public boolean remove(Object x) {
		Node<AnyType> pos = findPos(x);
		
		if(pos == NOT_FOUND)
			return false;
		else 
		{
			remove(pos);
			return true;
		}
	}
	
	/**
	 * Gets the node at position idx, which must range from 0 to size()-1
	 * @param idx index to search at.
	 * @return internal node corresponding to idx.
	 * @throws IndexOutOfBoundsException if idx is not between 0 and size()-1, inclusive.
	 */
	private Node<AnyType> getNode(int idx){
		return getNode(idx, 0, size() - 1);
	}
	
	/**
	 * Gets the Node at position idx, which must range from lower to upper.
	 * @param idx index to search at.
	 * @param lower lowest valid index.
	 * @param upper highest  valid index.
	 * @return internal node corresponding to idx.
	 * @throws IndexOutOfBoundsException if idx is not between lower and upper, inclusive.
	 */
	private Node<AnyType> getNode(int idx, int lower, int upper){
		
		Node<AnyType> p;
		
		if(idx < lower || idx>upper)
			throw new IndexOutOfBoundsException("getNode index:" + idx + "; size: " + size());
		
		if(idx < size() / 2) {
			p = beginMarker.next;
			for(int i = 0 ; i < idx; i++)
				p = p.next;
			
		}else {
			p = endMarker;
			for(int i = size(); i > idx; i--)
				p = p.prev;
		}
		return p;
			
	}
	
	
	 
	
	

}
