import java.util.LinkedList;

/**
 * A wrapper class for the openHashSet class- builds a linked list object that will be assigned in every
 * element in the openHashSet Array
 */
public class WrapperHashSet {
    LinkedList<String> theLinkedList;

    public WrapperHashSet(){

        theLinkedList = new LinkedList<String>();
    }
    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set
     */
    boolean add(String newValue){
        if (theLinkedList.contains(newValue)){
            return false;
        }
        theLinkedList.add(newValue);
        return true;
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    boolean contains(String searchVal){
        return theLinkedList.contains(searchVal);
    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    boolean delete(String toDelete){
        return theLinkedList.remove(toDelete);
    }

    /**
     * @return The number of elements currently in the set
     */
    public int size(){
        return theLinkedList.size();
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index - the index in which the wanted elemant is in
     * @return the element at the specified position in this list.
     */
    public String  get(int index){
        return theLinkedList.get(index);
    }

}
