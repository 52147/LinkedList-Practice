package linkedlistpractice2;

/** = Use Node current as an iterator =
 * 
 * If we want to change the element or add node or delete node, use a array to iterate is not suffix.
 * 
 * we need to instance node "current" as an iterator.
 *  - because current is private, we need method to manipulate it.
 *  - we can also use current in methods for adding and deleting node
 *    -> the iterator make it easier because it can name an arbitrary node
 *    
 * = current node =
 * 
 *  - Initially, current node is the first node.(head)
 *  - current node can be changed to the next node until the iteration has moved beyond the end(null).
 *  
 *  = previous =
 *  
 *  previous node before the current node
 *  we need the previous node to backing up one node
 *  
 *  = reference type =
 *  
 *  reference type :
 *   - is a data type based on class
 *   - when you create a object java allocates the memory to store the object.
 *   - thus, when you assign an object to the variable, the variable is actually assigned a reference to the object, not the object it self.
 *   - the reference is the address of memory location where object be stored.
 *   
 *   - To declare a variable of reference type -> use the class name as data type
 *     - ex: Node head -> head contains the reference to the Node object
 * 
 * = length(entries) of the linked list =
 *  
 *  - length/entries : how many nodes in the linked list?
 *  
 *  - two way to find the length:
 *     
 *    - First:
 *     - set a private variable int "length"
 *     - add first node -> length = 1
 *     - insert the node -> length++
 *    
 *    - Second:
 *     -length method
 *      - set the iterator count = 0
 *      - set the pointer position = head
 *      - while position is not null
 *        - count ++
 *        - advance the pointer to the next node
 *      - return the count(number of the nodes)  
 *
 */

public class LinkedListWithIterator {
	private Node head;
	private Node current;
	private Node previous;
	private int length;

	public LinkedListWithIterator() {
		head = null;
		current = null;
		previous = null;
		length = 0;
	}
	
	public int getLength() {
		return length;
	}
	
	/**
	 *  Adds the first node(head) and gives the reference into the previous.
	 */
	public void addFirstNode(String addElement) {
		head = new Node(addElement, head);
		System.out.println("Add the first node '" + addElement +"' at the head node.");
		if ((current == head.next) && (current != null))
			System.out.println("Add the first node '" + addElement +"' at the head node.");
			previous = head;
			length = 1;
	}

	/**
	 * Sets iterator to the first node(head) of the list.
	 * -> gives the current a reference to head
	 */
	public void resetIteration() {
		current = head;
		previous = null;
	}

	/**
	 * Return true if iteration is not finished.
	 */

	public boolean moreToIterate() {
		return current != null;
	}

	/**
	 * Advances iterator to next node. -> give the previous and current pointer a new reference to the next node
	 * 
	 * Step 1: give the reference of current pointer to the previous pointer.
	 * previous = current
	 * 
	 * before:
	 * A->B->C 
	 * previous  -> A
	 * current  -> B 	
	 * 
	 * after:  previous = current
	 * A->B->C
	 * previous -> B
	 * current  -> B 
	 * 
	 * Step 2: move the current pointer to the next node
	 * current = current.next 
	 * 
	 * before:
	 * A->B->C
	 * previous ->B
	 * current -> B
	 * 
	 * after:
	 * A->B->C 
	 * current -> C 	
	 * 
	 * Final result :  
	 * A->B->C
	 * previous -> B
	 * current -> C
	 * 
	 */
	public void goToNext() {
		if (current != null) {
			System.out.println("Move the current to the next node.");
			previous = current;
			current = current.next;
		} else if (head != null) {
			System.out.println("Iterated too many times or uninitialized iteration");
			System.exit(0);
		} else {
			System.out.println("Iterating with an empty list.");
			System.exit(0);
		}

	}

	/**
	 * Returns the element at the current node.
	 */
	public String getElementAtCurrent() {
		String result = null;
		if (current != null) {
			result = current.element;
		} else {
			System.out.println("Current is not at any node, so there is no element to get.");
			System.exit(0);
		}
		return result;
	}

	/**
	 * Replace the element at the current node.
	 */
	public void setElementAtCurrent(String newElement) {
		if (current != null) {
			current.element = newElement;
		} else {
			System.out.println("Can not set the element because current is not at any node");
			System.exit(0);
		}
	}
	
	/**
	 * Insert a new node containing new element after current node.
	 * 
	 * The current node is the same after invocation.
	 * Precondition:
	 * - List is not empty.
	 * - current node is not beyond the list.
	 * 
	 * step 1: set the newNode next point to the node that next to the current 
	 * newNode.next = current.next
	 * 
	 * ex:
	 * The new node will insert between the B and C node
	 * 
	 * B -> current node
	 * C -> current.next node  
	 * 
	 * before A-> B-> C 
	 * after D(new node)->C
	 * 
	 * step 2: insert the new node between the current and current.next
	 * current.next = newNode
	 * 
	 *  ex: 
	 *  before A-> B->C   D->C
	 *  after A-> B-> D-> C
	 */
	public void insertNodeAfterCurrent(String newElement) {
		Node newNode = new Node();
		newNode.element = newElement;
		if(current != null) {
			System.out.println("Insert a node '" + newElement + "' after the current node.");
			newNode.next = current.next;		
			current.next = newNode;
			length++;
		}
		else if (head != null) {
			System.out.println("Iterator is not initialized.");
			System.exit(0);
		}
		else {
			System.out.println("The list is empty.");
			System.exit(0);
		}
	}
	
	/**
	 * Deletes the current node. And move the current node to the next node.
	 * 
	 * After this method invocation, the current node is either the node after the deleted node 
	 * or null if there us no next node.
	 * 
	 * Step 1:
	 * Let the previous.next node point to the node next to the current node.
	 * Gives the reference of curren.next to the previous.next
	 * previous.next (new current C 's previous.next) = current.next (C -> next node of old current B )
	 *  
	 * before :
	 * previous -> A
	 * current -> B(x)
	 * A-> B(x)-> C-> D
	 * 
	 * after: previous.next = current.next
	 * 
	 * A-> C 
	 * B(x)-> C-> D
	 * 
	 * Step2:
	 * Let the node next to the current be the new current
	 * current(new current C) = current.next (C-> next to the old current B)
	 * 
	 * before:
	 * previous -> A
	 * current -> B
	 * 
	 * A-> C
	 * B(x)-> C->D
	 * 
	 * after: current = current.next
	 * 
	 * previous -> A
	 * new current -> C
	 * old current be deleted -> B(x)
	 *  
	 * A-> C-> D
	 *    
	 */
	public void deleteCurrentNode() {
		if((current != null)&&(previous != null)) {
			System.out.println("Delete the current node.");
			previous.next = current.next;
			current = current.next;
		}else if((current != null)&&(previous == null)){
			System.out.println("Delete the current node/head node, because the current node is at head node.");
			head = current.next; // current is at head node, so no previous node
			current = head;	
		}else {
			System.out.println("Deleting with uninitialized current or an empty list");
			System.exit(0);
		}
	}
	
	public int length() {
		int count = 0;
		Node position = head;
		while (position != null) {
			count++;
			position = position.next;
		}
		return count;
	}
	
	private  Node find(String target) {
		boolean found = false;
		Node position = head;
		while ((position != null) && !found) {
			String elementAtPosition = position.element;
			if(elementAtPosition.equals(target)) {
				found = true;
			}else {
				position = position.next;
			}
			
		}
		return position;
	}
	
	public boolean onList(String target) {
		return (find(target)!= null);
	}
	public void showList() {
		Node position = head;
		System.out.println("List: ");
		while(position != null) {
			System.out.println(position.element);
			position = position.next;
		
		}
	}
	

}
