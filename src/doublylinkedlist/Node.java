package doublylinkedlist;
/**
 * = Node class =
 * 
 * - which is similar to the ListNode class.
 * - The main difference is that, because we use a doubly linked list,
 *   we have both prev and next links.
 *   
 */

/**
 * This is the doubly-linked list node.
 *
 */

public class Node<AnyType> {
	
	public AnyType data;
	public Node<AnyType> prev;
	public Node<AnyType> next;
	
	public Node(AnyType d, Node<AnyType> p, Node<AnyType> n) {
		data = d;
		prev = p;
		next = n;
				
	}
	
	

}
