package doublylinkedlist;

/**
 * = doubly linked lists and circularly linked lists =
 * 
 * - the singly linked list does not efficiently support some important operations.
 * 
 *   - For instance, although it is easy to go to the front of the list, 
 *     it is time consuming to go to the end.
 *     
 *   - Although we can easily advance via advance, implementing retreat cannot be done efficiently
 *     with only a next link.
 *   - In some applications that might be crucial.
 *   
 *     - For instance, when designing a text editor, we can maintain the internal image of the file as a  linked list of lines.
 *     - We want to be able to move up just as easily as down in the list,
 *       to insert both before and after a line rather than just after,
 *       and to be able to get to the last line quickly.
 *       
 *     - A moment's thought suggests that to implement this procedure efficiently we should have each node maintain 2 link:
 *     
 *       one to the next node in the list and one to the previous node.
 *       - Then, to make everything symmetric, we should have not only a header
 *         but also a tail.
 *         -> A linked list that allows bidirectional traversal by
 *            sorting 2 links per node is called a doublely linked list.
 *         -> Each node now has 2 links (next and prev),
 *            and searching and moving can easily be performed in both directions.
 *         -> Obviously, there are some important changes from the singly linked list.
 *         
 * 
 * - First, an empty list now consists of a head and tail.
 * - Note that head.prev and tail.next are not needed in the algorithms
 *   and are not initialized.
 *   
 *   - The test for emptiness is now:
 *   
 *     head.next == tail
 *     
 *     or
 *     
 *     tail.prev == head
 *     
 *     - We no longer use null to decide whether an advance has taken us past the end of the list.
 *     - Instead, we have gone past the end if current is either head or tail(recall that we can go in either direction).
 *     - The retreat operation can be implemented by
 *     
 *       current = current.prev
 *       
 *       A empty doubly linked list:
 *       
 *         head -> tail 
 *              <-
 *   
 * - Before describing some of the additional operations that are available,
 *   let us consider how the insertion and removal operation change.
 * - Naturally, we can now do both insertBefore and insertAfter.
 * - Twice as many link moves are involved for insertAfter with doubly linked list as with singly linked lists.
 * - If we write each statement explicitly, we obtain
 * 
 *   newNode = new DoublyLinkedListNode(x);
 *   newNode.prev = current;      // Set x's prev link
 *   newNode.next = current.next; // Set x's next link
 *   newNode.prev.next = newNode; // Set a's next link
 *   newNode.next.prev = newNode; // Set b's prev link
 *   current = newNode;           // Set b's prev link
 *   
 *   
 *   Insertion in a doubly linked list by getting new node and then changing pointers in the order indicated
 *     3      2
 *   a ->  x  ->  b
 *     <-     <-
 *     1      4
 *     
 *     
 * - To do a complete doubly linked list implementation, we need to decide which operations to support.
 * - We can reasonably expect twice as many operations as in the linked list.
 * - Each individual procedure is similar to the linked list routines;
 *   only the dynamic operations involve additional link moves.
 * - Moreover, for many of the routines, the code is dominated by error checks.
 * 
 * - Although some of the checks will change(e.g., we do not test against null),
 *   they certainly do not become any more complex.
 * - We use a doubly linked list to implement the Collections API linked list class,
 *   along with its associated iterators.
 * - There are lots of routines, but most are short.
 * 
 * - A popular convention is to create a circularly linked list,
 *   in which the last cell's next link references first, which can be done with or without a header.
 *   
 *   - Typically, it is done without a header because the header's main purpose
 *     is to ensure that every node has a previous node,
 *     which is already true for a nonempty circularly linked list.
 *     
 * - Without a header, we have only the empty list as a special case.
 * - We maintain a reference to the first node, but that is not the same as a header node.
 * - We can use circularly linked lists and doubly linked lists simultaneously.
 * - The circular list is useful when we want to searching to allow wraparound,
 *   as is the case for some text editors.
 *   
 * = implementing the collections api LinkedList class =
 * 
 * - We need a class to store the basic list node, a class for the iterator, and a class for the list itself.
 * - LinkedList implements the List and Queue interfaces and, as usual, it extends AbstractCollection.
 * 
 * - 
 *       
 *   
 *   
 *     
 * 
 *          
 *          
 *          
 *          
 *         
 *      
 *   
 *     
 *   
 *       
 * 
 *
 */

/**
 * 
 * LinkedList class implements a doubly-linked list.
 *
 */

public class LinkedList<AnyType> extends AbstractCollection {

}
