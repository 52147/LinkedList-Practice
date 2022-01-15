package doublylinkedlist;

/**
 * 
 * - the LinkedListIterator, which is perhaps the trickiest part of the whole implementation.
 * 
 * - The iterator maintains a current position.
 * - current represents the node containing the item that is to be returned by a call to next.
 * - Observe that when current is positioned at the endmarker,
 *   a call to next is illegal, but the call to previous should give the first item, going backwards.
 *   
 * - As in the ArrayList, the iterator also maintains the modCount of the list
 *   it is iterating over, initialized at the time the iterator was constructed.
 * - This variable, expextedModCount, can change only if the iterator performs a remove.
 * - lastVisited is used to represent the last node that was visited;
 *   this is used by remove.
 * - If lastVisited is null, the remove is illegal.
 * - Finally, lastMoveWasPrev is true if the last movement of the iterator prior to remove was via previous;
 *   it is false if the last movement was via next.
 *   
 * hasNext()
 * hasPrevious()
 * - Both throw an exception if an external modification to the list has been detected.
 * 
 * next()
 * - The next method advances current after getting the value in the node that is to be returned.
 * - Data fileds lastVisited and lastMoveWasPrev are updated, respectively.
 * 
 * previous()
 * - The implementation of previous is not exactly symmetric, because for previous,
 *   we advance current prior to obtaining the value.
 * - This is evident when one considers that the initial state for backwards iteration
 *   is that current is at the endmarker.
 * 
 * remove()
 * - After the obligatory error checks,
 *   we use the LinkedList remove method to remove the lastVisited node.
 * - The explicit reference to the outer class is required because the iterator remove hides the list remove.
 * - After making lastVisited null, to disallow a second remove, 
 *   we check whether the last operation was next or previous.
 * - In the latter case, we adjust current, to its state prior to the previous/ remove combination.
 *
 */

public class LinkedListIterator {

}
