/***
 * A class that builds the Close Hash Set data structure, implementing SimpleSet
 */
public class ClosedHashSet extends SimpleHashSet {
    /**
    * The Array containing the Strings
    */
    String [] closeHash;
    /**
     * The String value that will be used whenever a value from the closeHash list will be deleted
     */
    String wasDeleted = "this String was deleted";
    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16), upper
     * load factor (0.75) and lower load factor (0.25).
     */
    public ClosedHashSet(){
        super();
        createCloseList();
    }
    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity
     * (16).
     * @param upperLoadFactor - the upper load factor before rehashing
     * @param lowerLoadFactor - the lower load factor before rehashing
     */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor){
        super(upperLoadFactor, lowerLoadFactor);
        createCloseList();

    }
    /**
     * Data constructor - builds the hash set by adding the elements one by one. Duplicate values should
     * be ignored. The new table has the default values of initial capacity (16), upper load factor
     * (0.75), and lower load factor (0.25).
     * @param data - Values to add to the set.
     */
    public ClosedHashSet(java.lang.String[] data) {
        super();
        createCloseList();
        for(String value: data){
            add(value);
        }
    }
    /**
     * A method that creates the Array that contains the String values.
     */
    public void createCloseList(){
        closeHash = new String[INITIAL_CAPACITY];
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
        if(clamp(newValue.hashCode()) == MINUS_ONE){
            capacity = capacity*MULTI;
            reHash();
        }
        int indexToInsert = clamp(newValue.hashCode());
        closeHash[indexToInsert] = newValue;
        size++;

        if(increase()){
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
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    @Override
    public boolean contains(String searchVal) {
        if (clampParamString(searchVal) == ZERO) {
            return false;
        }
        return true;
    }


    /**
     * Clamps hashing indices to fit within the current table capacity
     * @param index - the index before clamping
     * @return an index properly clamped
     */
    @Override
    protected int clamp(int index) {
        int i=ZERO;
        int newIndex=ZERO;
        boolean hasFound = false;
        while (!hasFound){
            newIndex = (index + (i+(i^MULTI))/MULTI) & (capacity-ONE);
            if(closeHash[newIndex]==null || closeHash[newIndex]== wasDeleted ){
                hasFound = true;
            }
            i++;
            if(i == capacity && !hasFound){
                return MINUS_ONE;
            }
        }
        return newIndex;
    }

    /**
     * A method that reHashes the values from the current table to the new resized table
     */
    public void reHash() {
        String[] theNewHashSetArray = new String[capacity];
        for (String value : closeHash) {
            if(value!=null && value!=wasDeleted) {
                int index_to_insert = clampReHash(value.hashCode(), theNewHashSetArray);
                theNewHashSetArray[index_to_insert] = value;
            }
        }
        closeHash = theNewHashSetArray;
    }

    /**
     * Clamps hashing indices to fit to the given table capacity
     * @param index - the index before clamping
     * @param hashSetArray - the table to which the clamp will fit
     * @return an index properly clamped
     */
    protected int clampReHash(int index,  String[] hashSetArray) {
        int i=ZERO;
        int newIndex=ZERO;
        boolean hasFound = false;
        while (!hasFound){
            newIndex = (index + (i+(i^MULTI))/MULTI) & (capacity-ONE);
            if(hashSetArray[newIndex]==null || hashSetArray[newIndex]==wasDeleted ){
                hasFound = true;
            }
            i++;
        }
        return newIndex;
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
        closeHash[clampParamString(toDelete)]= wasDeleted;
        size--;
        if(decrease()){
            reHash();
        }
        return true;
    }

    /**
     * Clamps hashing indices to fit within the current table capacity
     * @param value - the value which we wont to clamp
     * @return an index properly clamped, or ZERO if cant be clamped
     */
    private int clampParamString(String value){
        int i=ZERO;
        boolean hasFound = false;
        int newIndex=0;
        while (!hasFound){
            newIndex = ((value.hashCode()+ (i+(i^MULTI))/MULTI)) & (capacity-ONE);
            if(closeHash[newIndex] == null){
                return ZERO;
            }
            if(closeHash[newIndex].equals(value) && closeHash[newIndex]!=wasDeleted){
                hasFound = true;
            }
            i++;
            if(i == capacity && !hasFound){
                return ZERO;
            }
        }
        return newIndex;
    }
}
