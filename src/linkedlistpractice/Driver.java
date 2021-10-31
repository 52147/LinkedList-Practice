package linkedlistpractice;

import java.util.Arrays;

public class Driver {

	public static void main(String[] args) {

		LinkdedListDemo list = new LinkdedListDemo();

		list.addNode("One"); // The last node is One.
		list.addNode("Two");
		list.addNode("Three");
		list.addNode("Four");
		list.addNode("Five");
		list.addNode("Six"); // The first node is Six.

		System.out.println("List has " + list.length() + " entries.");
		
		System.out.println();

		list.showList();
		
		System.out.println();

		if (list.onList("Three")) {
			System.out.println("Three is on the list.");
		} else
			System.out.println("Three is not on the list.");
		
		System.out.println();

		list.deleteFirstNode();

		list.showList();

		list.deleteFirstNode();
		list.deleteFirstNode();
		list.deleteFirstNode();
		
		System.out.println();

		if (list.onList("Three"))
			System.out.println("Three is on the list.");

		else
			System.out.println("Three is not on the list.");
		System.out.println();

		System.out.println("Start: ");
		list.showList();
		
		System.out.println();
		System.out.println("List has " + list.length() + " entries.");
		System.out.println();
		
		System.out.println(Arrays.toString(list.placeToArray()));
		
		list.printArray();
		
	}

}
