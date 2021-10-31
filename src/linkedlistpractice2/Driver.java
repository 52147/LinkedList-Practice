package linkedlistpractice2;

public class Driver {

	public static void main(String[] args) {
		
		LinkedListWithIterator list = new LinkedListWithIterator();		
		
		list.addFirstNode("One");
		list.resetIteration();
		list.insertNodeAfterCurrent("Two");
		list.insertNodeAfterCurrent("Three");
		list.insertNodeAfterCurrent("Four");
		list.resetIteration();
		System.out.println();
		System.out.println("Current : " + list.getElementAtCurrent());
		
		
		list.insertNodeAfterCurrent("a number.");
		
		
		list.showList();
		
		
		System.out.println();
		System.out.println(list.getElementAtCurrent());
		list.goToNext();
		
		list.showList();
		
		System.out.println(list.length());
		
		System.out.println();
		list.insertNodeAfterCurrent("Four");
		list.showList();
		

	}

}
