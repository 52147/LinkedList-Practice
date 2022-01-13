package linkedlistpractice3;
/**
 * = singly Linked List =
 * 
 * - In a singly linked list, each node consists of the data element and a link to the next node in the list.
 * - The last node in the list has a null next link.
 * 
 * - The first node in the linked list is accessible by a reference.
 * - We can print or search in the linked list by starting at the first item and following the chain of next links.
 * - The 2 basic operations that must be performed are insertion and deletion of an arbitrary item x.
 * 
 * - For insertion we must define where the insertion is to take place.
 * - If we have a reference to some node in the list, 
 *   the easiest place to insert is immediately after that item.
 *   
 *   - We must perform the following steps:
 *     
 *     tmp = new ListNode();      // Create a new node
 *     tmp.element = x;           // Place x in the element member
 *     tmp.next = current.next;   // x's next node is b
 *     current.next = tmp;        // a's next node is x
 *     
 *     Insertion in a linked list:
 *     Create new node(tmp), copy in x, 
 *     set tmp's next link, and set current's next link.
 *     
 *     a: current
 *     x: tmp
 *     
 *     ... -> a -> b ->...
 *             \  /
 *              x
 *              
 *              
 *    - As a result of these statements, the old list ...a, b, ... now appears as ... a, x, b, ...
 *    - We can simplify the code if the ListNode has a constructor that initializes the data members directly.
 *      - In that case, we obtain
 *      
 *        tmp = new ListNode(x, current.next);  // Create new node
 *        current.next = tmp;                   // a's next node is x
 *        
 *        - We now see that tmp is no longer necessary.
 *        - Thus we have the one-liner
 *         
 *         current.next = new ListNode(x, current.next);
 *         
 *    - The remove command can be executed in one link change.
 *    - We set current to be the node prior to x and then have current's next link bypass x.
 *    - This operation is expressed by the statement
 *    
 *         current.next = current.next.next;
 *         
 *         The list ...a, x, b,... now appears as ... a, b, ...
 *         
 *         Deletion from a linked list:
 *         
 *         a: current
 *         
 *         ... -> a -> x -> b -> ...
 *                \________/
 *                
 *   - The fundamental property of a linked list is that changes to it can be made by using only a constant number of data movement,
 *     which is a great improvement over an array implementation.
 *   - Maintaining contiguousness in an array means that whenever an item is added or deleted,
 *     all items that follow it in the list must move.   
 *     
 *               
 *  = header nodes =
 *  
 *  - There is one problem with the basic description:
 *    - It assumes that whenever an item x is removed, some previous item is always present to allow a bypass.
 *    - Consequently, removal of the first item in the linked list becomes a special case.
 *    - Similarly, the insert routine does not allow us to insert an item to be the new first element in the list.
 *      - The reason is that insertions must follow some existing item.
 *      
 *      - So, although the basic algorithm works fine, some annoying special cases must be dealt with.
 *      
 *   - Special cases are always problematic in algorithm design and frequently lead to bugs in the code.
 *   - Consequently, writing code that avoids special cases is generally preferable.
 *   - One way to do that is to introduce a header node.
 *   
 *   - A header node is an extra node in a linked list that hold no data
 *     but serves to satisfy the requirement that every node containing an item 
 *     have a previous node in the list.
 *     
 *     using a header node for the linked list
 *     
 *     ()-> a -> b -> c -> null
 *     
 *     (): header
 *     
 *     - Note that a is no longer a special case.
 *     - It can be deleted just like any other node by having current reference the node before it.
 *     - We can also add a new first element to the list by setting current equal to the header node
 *       and calling the insertion routine.
 *       
 *       - By using the header node, we greatly simplify the code--with a negligible space penalty.
 *       - In more complex applications, header nodes not only simplify the code but also improve speed
 *         because, after all, fewer tests means less time.
 *         
 *  - The use of a header node is somewhat controversial.
 *  - Some argue that avoiding special cases is not sufficient justification for adding fictitious cells;
 *    they view the use of header nodes as little more than old-style hacking.
 *    
 *    - Even so, we use them here precisely because they allow us to demonstrate the basic link manipulations
 *      without obscuring the code with special cases.
 *  
 *  - Whether a header should be used is a matter of personal preference.
 *  - Furthermore, in a class implementation, its use would be completely transparent to the user.
 *  
 *  - However, we must be careful:
 *    - The printing routine must skip over the head node,
 *      as must all searching routines.
 *    
 *   - Moving to the front now means setting the current position to header.next, and so on.
 *   - Furthermore, with a dummy header node, a list is empty if header.next is null. 
 *    
 * = iterator classes =
 * 
 * - The typically primitive strategy identifies a linked list by a reference to the header node.
 * - Each individual item in the list can then be accessed by providing a reference to the node that stores it.
 * 
 * - The problem with that strategy is that checking for errors is difficult.
 *   - For example, a user could pass a reference to something that is a node in a different list.
 *   - One way to guarantee that this can not happen is to store a current position as part of a list class.
 *   
 *   - To do so, we add a second data member, current.
 *   - Then, as all access to the list goes through the class methods, 
 *     we can be certain that current always represents a node in the list,
 *     the header node, or null.
 *     
 *     - This scheme has a problem:
 *       - With only one position, the case of 2 iterators needing to access the list independently is left unsupported.
 *       
 *       - One way to avoid this problem is to define a separate iterator class,
 *         which maintains a notion of its current position.
 *         
 *       - A list class would then not maintain any notion of a current position and would only have methods that treat the list as unit,
 *         such as isEmpty and makeEmpty, or that accept an iterator as a parameter, such as inert.
 *  
 *       - Routines that depend only on an iterator itself, such as the advance routine that 
 *         advances the iterator to the next position, would reside in the iterator class.
 *       - Access to the list is granted by making the iterator class either package visible or an inner class.
 *       - We can view each instance of an iterator class as one in which only legal list operations,
 *         such as advancing in the list, are allowed.  
 *         
 *                
 *  - The LinkedListIterator class that implements the concept of position.
 *  - The class stores a reference to a ListNode, representing the current position of the iterator.
 *  - The isValid method returns true if the position is not past the end of the list,
 *    retrieve returns the element stored in the current position, 
 *    and advance advances the current position to the next position.
 *  
 *  - The constructor for LinkedListIterator requires a reference to a node that is to be the current node.
 *  - Note that this constructor is package-visible and thus cannot be used by client methods.
 *  - Instead, the general idea is that the LinkedList class returns preconstructed LinkedList objects, as appropriate;
 *    LinkedList is in the same package as LinkedListIterator, so it can invoke the LinkedListIterator constructor.
 *                              
 *              
 *
 */

// LinkedListIterator class; maintains "current position"
//
// Construction: Package visible only, with a ListNode
// 
// Public operations:
// void advance()     -----> Advance
// boolean isValid()  -----> True if at valid position in list
// AnyType retrieve() -----> Return item in current position


public class LinkedListIterator<AnyType> {
	
	ListNode<AnyType> current; // Current position
	
	/**
	 * Construct the list iterator
	 * @param theNode any node in the linked list.
	 */
	LinkedListIterator(ListNode<AnyType> theNode){
		current = theNode;
	}
	
	/**
	 * Test if the current position is a valid position in the list.
	 * @return true if the current position is valid.
	 */
	public boolean isValid() {
		return current != null;
	}
	
	/**
	 * Return the item stored in the current position.
	 * @return the stored item or null if the current position is not in the list.
	 */
	public AnyType retrieve() {
		return isValid() ? current.element : null; 
	}
	
	/**
	 * Advance the current position to the next node in the list.
	 * If the current position is null, then do nothing.
	 */
	public void advance() {
		if(isValid())
			current = current.next;
	}

}
