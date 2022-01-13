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

public class LinkedList {
	
	public static <AnyType> int listSize(LinkedList<AnyType> theList) {
		LinkedListIterator<AnyType> itr;
		int size = 0;
		
		for(itr = theList.first(); itr.isValid(); itr.advance())
			size++;
		
		return size;
	}

}
