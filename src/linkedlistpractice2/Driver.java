package linkedlistpractice2;

public class Driver {

	public static void main(String[] args) {

		LinkedListWithIterator list = new LinkedListWithIterator();

		list.addFirstNode("One"); // Adds head node
		list.resetIteration(); // current = head
		list.insertNodeAfterCurrent("Two");
		list.insertNodeAfterCurrent("Three");
		list.insertNodeAfterCurrent("Four");

		System.out.println();
		System.out.println("List has " + list.getLength() + " entries.");
		System.out.println("List has " + list.length() + " entries.");

		System.out.println();
		System.out.println("Current node is at : " + list.getElementAtCurrent());
		System.out.println();

		list.insertNodeAfterCurrent("a number.");
		System.out.println();

		list.showList();

		System.out.println();

		list.setElementAtCurrent("Five");
		System.out.println("Replace the current element by : " + list.getElementAtCurrent());
		System.out.println();
		list.showList();

		System.out.println();
		list.goToNext(); // move the current to the next node
		System.out.println("The current node at : " + list.getElementAtCurrent());
		System.out.println();

		list.showList();
		System.out.println();
		System.out.println("List has " + list.getLength() + " entries.");

		System.out.println("List has " + list.length() + " entries.");

		System.out.println();
		list.insertNodeAfterCurrent("Six");
		System.out.println();
		list.showList();
		
		System.out.println();
		System.out.println("The current node at : " + list.getElementAtCurrent());
		System.out.println();

		list.deleteCurrentNode();
		System.out.println();

		list.showList();
		
		
		System.out.println();
		System.out.println("The current node at : " + list.getElementAtCurrent());
		System.out.println();

		System.out.println("List has " + list.length() + " entries.");

	}

}
