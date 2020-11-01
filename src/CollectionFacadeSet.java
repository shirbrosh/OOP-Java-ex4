/**
 * Wraps an underlying Collection and serves to both simplify its API and give it a common type with the
 * implemented SimpleHashSets.
 */
public class CollectionFacadeSet extends java.lang.Object implements SimpleSet {
    protected java.util.Collection<java.lang.String> collection;

    /**
     * Creates a new facade wrapping the specified collection
     * @param collection - the collection to wrap.
     */
    public CollectionFacadeSet(java.util.Collection<java.lang.String> collection){
        this.collection = collection;
    }

    /**
     * Add a specified element to the set if it's not already in it
     * @param newValue New value to add to the set
     * @return true if the element was added or false otherwise
     */
    public boolean add(String newValue){
        if(collection.contains(newValue)){
            return false;
        }
        collection.add(newValue);
        return true;
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return true if searchVal is found in the set or false otherwise
     */
    public boolean contains(String searchVal){
        return collection.contains(searchVal);
    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return true if toDelete is deleted from the set or false otherwise
     */
    public boolean delete(String toDelete){
        if(!collection.contains(toDelete)){
            return false;
        }
        collection.remove(toDelete);
        return true;
    }

    /**
     * Returns the number of elements currently in the set.
     */
    public int size(){ return collection.size(); }
}
