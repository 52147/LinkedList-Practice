package linkedlistpractice;

/**
 * = empty list =
 * 
 *  - Linked list usually start out empty. 
 *  - give head the value null in order to indicate an empty list.
 * 
 * = deleted node =
 * 
 *  - The memory of the deleted node will be reuse, which is the process of the automatic garbage collection.
 * 
 * = garbage collection =
 * 
 *  - Java virtual machine (JVM)  automatically determine what memory is no longer being used
 *   and to recycle this memory for other uses.
 * 
 * = iterator =
 * 
 *  - iterate: go from element to element
 *  - If we do not want to change the elements in the list, but want only to look at the element.
 *     -> we can put the element in the array and iterate the element.
 * 
 *  - for example : 
 *     - placeToArray method -> place each element into array
 *	   - printArray method -> iterator each element in array
 *
 * = Array =
 * 
 *  - is a collection of elements having the same data type
 *  - An iterator for an array is an int variable index.
 *  - we can iterate elements with the action index++
 *  
 *  = System.exit(0) =
 *  
 *  The exit() method of System class terminates the JVM.
 *  - exit(0) : indicates successful termination
 *  - exit(1) : indicates unsuccessful termination
 *  - exit(-1) : indicates unsuccessful termination with Exception 
 * 	
 */
public class LinkdedListDemo {

	private Node head;

	public LinkdedListDemo() {
		head = null; // head is null because linked list is start out empty.
	}

	/**
	 * Display the element on the list.
	 */
	public void showList() {
		Node position = head;
		while (position != null) {
			System.out.println(position.getElement());
			position = position.getNext();
		}
	}

	/**
	 * Returns the number of nodes.
	 */
	public int length() {
		int count = 0;
		Node position = head;
		while (position != null) {
			count++;
			position = position.getNext();
		}
		return count;
	}

	/**
	 * Add a node containing the element at the start of the list(head).
	 */
	public void addNode(String addElement) {
		head = new Node(addElement, head);
	}

	/**
	 * Deletes the first node.
	 */
	public void deleteFirstNode() {
		if (head != null)
			head = head.getNext();
		else {
			System.out.println("The list is already empty.");
			System.exit(0); // exit the program
		}
	}

	/**
	 * Use the element(in linked list) equal to element(target) to find whether the	target in the list.
	 * 
	 * Set a new pointer that traverse from the head to trail to find the target. 
	 * - need the pointer value be head 
	 * - target : the target(element) is equal to the element in the list.
	 * 
	 */

	private Node find(String target) {
		boolean found = false;
		Node position = head;

		while ((position != null && !found)) {
			String elementAtPosition = position.getElement();
			if (elementAtPosition.equals(target))
				found = true;
			else
				position = position.getNext();
		}
		return position;
	}

	/**
	 * Sees whether target node is on the list.
	 */
	public boolean onList(String target) {
		return find(target) != null;
	}

	/**
	 * Placing the element into an Array.
	 */
	public String[] placeToArray() {
		String[] array = new String[length()];
		Node position = head;
		int i = 0;
		while (position != null) {
			array[i] = position.getElement();
			i++;
			position = position.getNext();
		}
		return array;
	}

	/**
	 * An iterator for an array.
	 */
	public void printArray() {
		// Use index++ to iterate the array
		for (int index = 0; index < placeToArray().length; index++) {
			String array[] = placeToArray(); 
			System.out.println(array[index]);
		}
	}

}
