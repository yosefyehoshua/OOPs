/**
 * a class hash-set based on chaining. Extends SimpleHashSet.
 Note: the capacity of a chaining based hash-set is simply the number of buckets (the length of the array of lists).
 *
 * Created by Yosef Yehoshua.
 */
public class OpenHashSet extends SimpleHashSet {

    /** array LinkedListWrapper objects */
    protected LinkedListWrapper[] openHashTable;

    /** _________________________ Constructor _________________________ */

    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16), upper load
     * factor (0.75) and lower load factor (0.25).
     */
    public OpenHashSet(){
        openHashTable = new LinkedListWrapper[INITIAL_CAPACITY];
    }

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor The upper load factor of the hash table.
     * @param lowerLoadFactor The lower load factor of the hash table.
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor){
        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;
        int INITIAL_CAPACITY = 16;
        openHashTable = new LinkedListWrapper[INITIAL_CAPACITY];
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one. Duplicate values should be
     * ignored. The new table has the default values of initial capacity (16), upper load factor (0.75),
     * and lower load factor (0.25).
     * @param data Values to add to the set.
     */
    public OpenHashSet(java.lang.String[] data){
        openHashTable = new LinkedListWrapper[INITIAL_CAPACITY];
        for (int i = 0; i < data.length; i++) {
            add(data[i]);
        }
    }

    /** _________________________ Methods _________________________ */

    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue
     * @return
     */
    public boolean add(java.lang.String newValue){
        int index = clamper(newValue);
        if (openHashTable[index] == null) {
            openHashTable[index] = new LinkedListWrapper();}
        if (!contains(newValue)) {
            updateGeneralTableValues(MORE_ELEMENTS);
            openHashTable[index].add(newValue);
            if (loadFactor > upperLoadFactor) {
                updateGeneralTableValues(BIGGER_TABLE);
                reHash();
            }
            return true;
    }
        return false;
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal
     * @return
     */
    public boolean contains(java.lang.String searchVal){
        int index = clamper(searchVal);
        if (openHashTable[index] == null)
            return false;
        return openHashTable[index].contains(searchVal);
    }

    /**
     * Remove the input element from the set.
     * @param toDelete
     * @return
     */
    public boolean delete(java.lang.String toDelete){
        if (contains(toDelete)) {
            updateGeneralTableValues(LESS_ELEMENTS);
            int index = clamper(toDelete);
            openHashTable[index].delete(toDelete);
            if (loadFactor < lowerLoadFactor) {
                updateGeneralTableValues(SMALLER_TABLE);
                reHash();
            }
            return true;
        }
        return false;
    }

    /**
     * clam by give the index of a given value using his hash code
     * @param string string value
     * @return int the index in the table
     */
    private int clamper(String string){return string.hashCode()&(currentCapacity-1);}

    /**
     * rehasing the table in case the loadfactor is bigger/smaller the the upper/lower bound.
     */
    private void reHash() {
        LinkedListWrapper[] newOpenHashTable = new LinkedListWrapper[currentCapacity];
        for (int i = 0; i < openHashTable.length ; i++) {
            if (openHashTable[i] == null) continue;
            // pops the last word
            String word = openHashTable[i].pop();
            while (word != null) {
                int newI = clamper(word);
                if (newOpenHashTable[newI] == null)
                    newOpenHashTable[newI] = new LinkedListWrapper();
                newOpenHashTable[newI].add(word);
                word = openHashTable[i].pop();
            }
        }
        openHashTable = newOpenHashTable;
    }

}

