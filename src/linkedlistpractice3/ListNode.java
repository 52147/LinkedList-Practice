package linkedlistpractice3;
// Basic node stored in a linked list
// Note that this class is not accessible outside of package

public class ListNode<AnyType> {
	
	public AnyType element;
	public ListNode<AnyType> next;
	
	// Constructor
	public ListNode(AnyType theElement) {
		this(theElement, null);
	}
	
	public ListNode(AnyType theElement, ListNode<AnyType> n) {
		element = theElement;
		next = n;
	}

}
