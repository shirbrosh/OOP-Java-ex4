/**
 * A superclass for implementations of hash-sets implementing the SimpleSet interface.
 */
public abstract class SimpleHashSet extends java.lang.Object implements SimpleSet{
    protected static int ZERO=0;
    protected static int ONE=1;
    protected static int MULTI=2;
    protected static int MINUS_ONE = -1;
    /**
     * Describes the higher load factor of a newly created hash set
     */
    protected static float DEFAULT_HIGHER_CAPACITY=0.75f;
    /**
     * Describes the lower load factor of a newly created hash set
     */
    protected static float DEFAULT_LOWER_CAPACITY= 0.25f;
    /**
     * Describes the capacity of a newly created hash set
     */
    protected static int INITIAL_CAPACITY=16;

    protected float higher_capacity;
    protected float lower_capacity;
    protected int capacity;
    protected int size;


    /**
     * Constructs a new hash set with the default capacities given in DEFAULT_LOWER_CAPACITY and
     * DEFAULT_HIGHER_CAPACITY
     */
    public SimpleHashSet(){
        higher_capacity = DEFAULT_HIGHER_CAPACITY;
        lower_capacity = DEFAULT_LOWER_CAPACITY;
        capacity = INITIAL_CAPACITY;
        size = ZERO;

    }

    /**
     * Constructs a new hash set with capacity INITIAL_CAPACITY
     * @param upperLoadFactor - the upper load factor before rehashing
     * @param lowerLoadFactor - the lower load factor before rehashing
     */
    public SimpleHashSet(float upperLoadFactor, float lowerLoadFactor){
        higher_capacity = upperLoadFactor;
        lower_capacity = lowerLoadFactor;
        capacity = INITIAL_CAPACITY;
        size = ZERO;
    }

    /**
     * Returns the capacity (numbers of cells) of the table.
     * @return the capacity of the table.
     */
    public abstract int capacity();

    /**
     * Clamps hashing indices to fit within the current table capacity
     * @param index - the index before clamping
     * @return an index properly clamped
     */
    protected int clamp(int index){
        return index&(capacity-1);
    }

    /**
     * Returns the lower load factor of the table
     * @return the lower load factor of the table
     */
    protected float getLowerLoadFactor(){
        return lower_capacity;
    }

    /**
     * Returns the higher load factor of the table
     * @return the higher load factor of the table
     */
    protected float getUpperLoadFactor(){
        return higher_capacity;
    }

    /**
     * A method that increase the Array's capacity if
     * @return
     */
    protected boolean increase(){
        if((float) size/capacity > higher_capacity){
            capacity = capacity*MULTI;
            return true;
        }
        return false;
    }

    protected boolean decrease(){
        if((float) size/capacity  < lower_capacity && capacity>ONE){
            capacity = capacity/MULTI;
            return true;
        }
        return false;
    }

    public abstract void reHash();
}
