package linkedlistpractice3;

public class Driver {
	
	public static <AnyType> int listSize(LinkedList<AnyType> theList) {
		LinkedListIterator<AnyType> itr;
		int size = 0;
		
		for(itr = theList.first(); itr.isValid(); itr.advance())
			size++;
		
		return size;
	}
	
	// Simple print method
		public static<AnyType> void printList(LinkedList<AnyType> theList) {
			if(theList.isEmpty())
				System.out.println("Empty list");
			else {
				LinkedListIterator<AnyType> itr = theList.first();
				for(;itr.isValid(); itr.advance())
					System.out.println(itr.retrieve() + " ");
			}
			
			System.out.println();
				
		}

	public static void main(String[] args) {
		
		LinkedList<Integer> theList = new LinkedList<Integer>();
		LinkedListIterator<Integer> theItr;
		
		int i;
		
		theItr = theList.zeroth();
		printList(theList);
		
		for(i = 0 ; i < 10; i++) {
			theList.insert(i, theItr);
			printList(theList);
			theItr.advance(); // If does not add advance, list will in reverse order.
		}
		
		System.out.println("Size was:" + listSize(theList));
		
		for(i = 0; i < 10; i+=2)
			theList.remove(i);
		
		for(i = 0; i < 10 ; i++)
			if((i%2 == 0) == (theList.find(i).isValid()))
				System.out.println("Find fails!");
		
		
		System.out.println("Finished deletions");
		printList(theList);
		
		

	}

}
