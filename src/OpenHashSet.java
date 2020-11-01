/***
 * A class that builds the Open Hash Set data structure, implementing SimpleSet
 */
public class OpenHashSet extends SimpleHashSet{

    /**
     * The Array containing WrapperObject's
     */
    WrapperHashSet[] theWrapperList;
    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16), upper
     * load factor (0.75) and lower load factor (0.25).
     */
    public OpenHashSet(){
        super();
        createWrapperList();
    }

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity
     * (16).
     * @param upperLoadFactor - the upper load factor before rehashing
     * @param lowerLoadFactor - the lower load factor before rehashing
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor){
        super(upperLoadFactor, lowerLoadFactor);
        createWrapperList();

    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one. Duplicate values should
     * be ignored. The new table has the default values of initial capacity (16), upper load factor
     * (0.75), and lower load factor (0.25).
     * @param data - Values to add to the set.
     */
    public OpenHashSet(java.lang.String[] data){
        super();
        createWrapperList();
        for(String value: data){
            add(value);
        }
    }

    private void createWrapperList (){
        theWrapperList = new WrapperHashSet[INITIAL_CAPACITY];
        for (int i=0; i< this.capacity; i++){
            theWrapperList[i] = new WrapperHashSet();
        }
    }

    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set
     */
    @Override
    public boolean add(String newValue) {
        if(contains(newValue)){
            return false;
        }
        int indexToInsert = clamp(newValue.hashCode());
        this.theWrapperList[indexToInsert].theLinkedList.add(newValue);
        size++;

        if(increase()){
            reHash();
        }
        return true;
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    @Override
    public boolean contains(String searchVal) {
        int newIndex = searchVal.hashCode() &(capacity-1);
        if(theWrapperList[newIndex].contains(searchVal)){
            return true;
        }
        return false;
    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    @Override
    public boolean delete(String toDelete) {
        if(!contains(toDelete)){
            return false;
        }
        for(WrapperHashSet linkedList: theWrapperList){
            if(linkedList.contains(toDelete)){
                linkedList.delete(toDelete);
                break;
            }
        }
        size--;
        if(decrease()){
            reHash();
        }
        return true;
    }

    /**
     * @return The number of elements currently in the set
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the capacity (numbers of cells) of the table.
     * @return the capacity of the table.
     */
    @Override
    public int capacity() {
        return capacity;
    }

    /**
     * A method that reHashes the values from the current table to the new resized table
     */
    public void reHash() {
        WrapperHashSet[] theNewWrapperList = new WrapperHashSet[capacity];
        for (int i = 0; i < this.capacity; i++) {
            theNewWrapperList[i] = new WrapperHashSet();
        }
        for (WrapperHashSet linkedList : theWrapperList) {
            for (int j = 0; j < linkedList.size(); j++) {
                int index_to_insert = clamp(linkedList.get(j).hashCode());
                theNewWrapperList[index_to_insert].add(linkedList.get(j));
            }
        }
        theWrapperList = theNewWrapperList;
    }
}
