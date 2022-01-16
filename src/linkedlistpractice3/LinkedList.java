package linkedlistpractice3;
/**
 * - we define a generic list class LinkedList and an iterator class LinkedListIterator.
 * - The LinkedList class does not have the same semantics as java.util.LinkedList.
 * 
 * listSize()
 * 
 * - To show how the nonstandard version works, let us look at a static method
 *   that returns the size of a linked list.
 *   
 * - We declare itr as an iterator that can access the linked list theList.
 * 
 * - We initialize itr to the first element in theList(skipping over the header, of course)
 *   by referencing the iterator given by theList.first().
 *   
 * - The test itr.isValid() attempts to mimic the test p != null that would be conducted if
 *   p were a visible reference to a node.
 * 
 * - Finally, the expression itr.advance() mimics the conventional idiom p = p.next.
 * 
 * - Thus, as long as the iterator class defines a few simple operations,
 *   we can iterate over the list naturally.
 *   
 * - There is a natural parallel between the methods defined in the LinkedList and LinkedListIterator classes
 *   and those in the Collections API LinkedList class.
 *   
 *   - For instance, the LinkedListIterator advance method is roughly equivalent to hasNext in the Collections API iterators.
 *   
 *  
 * = java implementation =
 * 
 * - A suggested in the preceding description, a list is implemented as three separate generic classes:
 * 
 *   one class is the list itself(LinkedList), another represents the node(ListNode),
 *   and the third represents the position(LinkedListIterator).
 *   
 *   
 * - The single data member is a reference to the header node allocated by the constructor.
 * 
 * - isEmpty is an easily implemented short one-liner.
 * - The methods zeroth and first return iterators corresponding to the header and first element, respectively.
 * 
 * 
 * printList()
 * 
 * - The printList method outputs the contents of a list.
 * - printList uses only public methods and a typical iteration sequence of obtaining a starting point (via first),
 *   testing that it has not gone past the ending point(via isValid),
 *   and advancing in each iteration(via advance).    
 *   
 * 
 * - Let us revisit the issue of whether all three classes are necessary. 
 *   - For instance, couldn't we just have the LinkedList class maintain a notion of a current position?
 *   - Although this option is feasible and work for many applications,
 *     using a separate iterator class expresses the abstraction that the
 *     position and list actually are separate objects.
 *   - Moreover, it allows for a list to be accessed in several places simultaneously.
 *     - For instance, to remove a sublist from a list, we can easily add a remove operation to the list class
 *       that uses 2 iterators to specify the starting and ending points of the sublist to be removed.
 *       - Without the iterator class, this action would be more difficult to express.
 *   
 * find()
 * 
 * - which returns the position in the list of some element.
 * - take advantage of the fact that the and(&&) operations is short-circuited:
 * 
 *   - if the first half of the and is false, the result is automatically false and the second half is not evaluated.
 *   
 *   
 * remove()
 * 
 * - remove some element x from the list.
 * - We need to decide what to do if x occurs more than once or not at all.
 * - Our routine removes the first occurrence of x and does nothing if x is not in the list.
 * - To make that happen, we find p, which is the cell prior to the one containing x, via a call to findPrevious.
 * 
 *   - This code is not foolproof:
 *     - There may be 2 iterators, and one can be left logically in limbo if the other removes a node.
 *  
 * findPrevious()
 * find()
 * - The findPrevious routine is similar to the find routine.
 * 
 * 
 * insertion()
 * 
 * - We pass an element to be inserted and a position p.
 * - This particular insertion routine inserts an element after position p.
 * - Note that the insert routine makes no use of the list it is in; it depends only on p.
 * 
 * 
 * time analysis:
 * 
 * - With the exception of the find and findPrevious routines(and remove, which calls findPrevious),
 *   all the operations that we have coded so far take O(1) time.
 *   - The find and findPrevious routines take O(N) time in the worst case because the entire list might need to be traversed if the element either is not found or is last in the list.
 *   
 * - On average, the running time is O(N), because on average half the list must be traversed.
 * 
 *   
 *   
 * - We certainly could have added more operations, but this basic set is quite powerful.
 * - Some operations, such as retreat, are not efficiently supported by this version of the linked list;
 *   variations on the linked list that allow constant time implementation of that and other operators.  
 *
 */

// LinkedList class
//
// Construction: with no initializer
// Access is via LinkedListIterator class
//
// Public operations:
// boolean isEmpty()                  -----> Return true if empty; else false
// void makeEmpty()                   -----> Remove all items
// LinkedListIterator zeroth()        -----> Return position to prior to first
// LinkedListIterator first()         -----> Return first position
// void insert(x, p)                  -----> Insert x after current iterator position p
// void remove (x)                    -----> Remove x
// LinkedListIterator find(x)         -----> Return position that views x
// LinkedListIterator findPrevious(x) -----> Return position prior to x
//
// Errors:
// No special errors
 

public class LinkedList<AnyType> {
	
	private ListNode<AnyType> header;
	
	/**
	 * Construct the list	
	 */
	public LinkedList() {
		header = new ListNode<AnyType>(null);
	}
	
	/**
	 * Test if the list is logically empty.
	 * @return true if empty, false otherwise
	 */
	public boolean isEmpty() {
		return header.next == null;
	}
	
	/**
	 * Make the list logically empty.
	 */
	public void makeEmpty() {
		header.next = null;
	}
	
	/**
	 * Return an iterator representing the header node.
	 */
	public LinkedListIterator<AnyType> zeroth(){
		return new LinkedListIterator<AnyType>(header);
	}
	
	/**
	 * Return an iterator representing the first node in the list.
	 * This operation is valid for empty lists.
	 */
	public LinkedListIterator<AnyType> first(){
		return new LinkedListIterator<AnyType>(header.next);
	}
	
	/**
	 * Insert after p.
	 * @param x the item to insert.
	 * @param p the position prior to the newly inserted item
	 */
	public void insert(AnyType x, LinkedListIterator<AnyType> p) {
		if(p != null && p.current != null)
			p.current.next = new ListNode<AnyType>(x, p.current.next);
	}
	
	/**
	 * Return iterator corresponding to the first node containing an item.
	 * @param x the item to search for
	 * @return an iterator; iterator is not valid if item is not found
	 */
	public LinkedListIterator<AnyType> find(AnyType x){
		ListNode<AnyType> itr = header.next;
		
		while(itr != null && !itr.element.equals(x))
			itr = itr.next;
		
		return new LinkedListIterator<AnyType>(itr);
	}
	
	/**
	 * Return iterator prior to the first node containing an item.
	 * find the node before x
	 * @param x the item to search for
	 * @return appropriate iterator if the item is found. Otherwise, the iterator corresponding to the last element in the list is returned.
	 */
	public LinkedListIterator<AnyType> findPrevious(AnyType x){
		
		ListNode<AnyType> itr = header;
		
		while(itr.next != null && !itr.next.element.equals(x))
			itr = itr.next;
		
		return new LinkedListIterator<AnyType>(itr);
	}
	
	/**
	 * Remove the first occurrence of an item.
	 * @param x the item to remove
	 */
	public void remove(AnyType x) {
		LinkedListIterator<AnyType> p = findPrevious(x);
		
		if(p.current.next != null)
			p.current.next = p.current.next.next; // Bypass deleted node
	}
	


}
