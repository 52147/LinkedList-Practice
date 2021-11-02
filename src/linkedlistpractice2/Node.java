package linkedlistpractice2;

public class Node {
	String element;
	Node next;

	public Node() {
		element = null;
		next = null;
	}

	public Node(String element, Node next) {
		this.element = element;
		this.next = next;
	}

	public String element() {
		return element;
	}

	public Node next() {
		return next;
	}

}
