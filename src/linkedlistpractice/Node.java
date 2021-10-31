package linkedlistpractice;
/**
 * = Singly Linked Lists =
 *  
 *  - is a collection of nodes that together form a liner ordering
 *  - each nodes is an objects that store a reference to an element and a reference to another node called next.
 *  - next can be viewed as a link or pointer to another node
 *  - the first node => head
 *  - the last node => tail => we can identify the tail when the tail having a null next.
 *  
 *   same with the Array:
 *   - linked list keeps its elements in a certain order. => determined by the chain of next links
 *   
 *   difference between Array:
 *   - linked list does not have a predetermined fixed size.
 *   - we do not track of any index numbers for nodes, so we can not know just by examining a node if it is the second, fifth or twentieth node
 * 
 * = implement a linked list = 
 * 
 * - Node class
 *   - each node is an object of a class that has 2 instance variables,
 *      -> one for data/element
 *      -> one for next
 *     
 * 
 * 
 *
 */
public class Node {
	private String element;
	private Node next;
	
	public Node() {
		element = null;
		next = null;
	}
	
	public Node(String element, Node next) {
		this.element = element;
		this.next = next;
	}	

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}	

}
