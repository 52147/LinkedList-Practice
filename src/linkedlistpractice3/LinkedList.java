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
 * - The printList method outputs the contents of a list.
 * - printList uses only public methods and a typical iteration sequence of obtaining a starting point (via first),
 *   testing that it has not gone past the ending point(via isValid),
 *   and advancing in each iteration(via advance).    
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
	
	// Simple print method
	public static<AnyType> void printList(LinkedList<AnyType> theList) {
		if(theList.isEmpty())
			System.out.println("Empty list");
		else {
			LinkedListIterator<AnyType> itr = theList.first();
			for(;itr.isValid(); itr.advance())
				System.out.println(itr.retrieve() + " ");
		}
		
		System.out.println();
			
	}
	
	
	
	
	public static <AnyType> int listSize(LinkedList<AnyType> theList) {
		LinkedListIterator<AnyType> itr;
		int size = 0;
		
		for(itr = theList.first(); itr.isValid(); itr.advance())
			size++;
		
		return size;
	}

}
