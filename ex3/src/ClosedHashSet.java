/**
 * a class hash-set based on closed-hashing with quadratic probing. Extends SimpleHashSet.
 *
 * Created by Yosef Yehoshua.
 */
public class ClosedHashSet extends SimpleHashSet {

    protected String[] closedHashTable;

    private static final int PROBING_ADD = 1;
    private static final int PROBING_CONTAINS = 2;
    private static final int PROBING_DELETE = 3;
    private static final String DELETED_VALUE = new String("DeletedValue");


    /** _________________________ Constructors _________________________ */

    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16), upper
     * load factor (0.75) and lower load factor (0.25).
     */
    public ClosedHashSet(){ closedHashTable = new String[INITIAL_CAPACITY];}

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor The upper load factor of the hash table.
     * @param lowerLoadFactor The lower load factor of the hash table.
     */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor){
        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;
        int INITIAL_CAPACITY = 16;
        closedHashTable = new String[INITIAL_CAPACITY];
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one. Duplicate values should be
     * ignored. The new table has the default values of initial capacity (16), upper load factor (0.75),
     * and lower load factor (0.25).
     * @param data Values to add to the set.
     */
    public ClosedHashSet(java.lang.String[] data){
        closedHashTable = new String[INITIAL_CAPACITY];
        for (int i = 0; i < data.length; i++) add(data[i]);
    }

    /** ------------------------- Methods ------------------------- */

    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set
     */
    public boolean add(java.lang.String newValue) { return probing(PROBING_ADD, newValue);}

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    public boolean contains(java.lang.String searchVal){ return probing(PROBING_CONTAINS, searchVal); }


    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    public boolean delete(java.lang.String toDelete) { return probing(PROBING_DELETE, toDelete); }

    /**
     * calculate the hash index of a given string
     * @param string Value.
     */
    public int clamper(String string, int i) { return (string.hashCode() + (i + i*i)/2)&
            (currentCapacity-1);}

    /**
     *
     */
    public void reHash(){
        String[] newClosedHashTable = new String[currentCapacity];
        for (int i = 0; i < closedHashTable.length; i++) {
            if (closedHashTable[i] == null) continue;
            for (int j = 0; j <currentCapacity ; j++) {
                int newIndex = clamper(closedHashTable[i], j);
                if (newClosedHashTable[newIndex] == null){
                    newClosedHashTable[newIndex] = closedHashTable[i];
                    break;
                }
            }
        }
        closedHashTable = newClosedHashTable;
    }

    /**
     * probing function that returns a boolean related to the case/action you want: add, delete, contains,
     * rehash...it also delete or add..
     * @param action int const. symbolize what to do...
     * @param value given value
     * @return boolean if the action was made or not.
     */
    private boolean probing(int action, String value){
        for (int i = 0; i < closedHashTable.length ; i++) {
            int index = clamper(value, i);
            // first condition, checking is the cell in the array is null
            if (closedHashTable[index] == null) {
                // dividing for cases: add, delete, contains.
                switch (action) {
                    case PROBING_ADD:
                        closedHashTable[index] = value;
                        updateGeneralTableValues(MORE_ELEMENTS);
                        if (loadFactor > upperLoadFactor) {
                            updateGeneralTableValues(BIGGER_TABLE);
                            reHash();
                            return true;
                        }
                        return true;
                    case PROBING_CONTAINS:
                        return false;
                    case PROBING_DELETE:
                        continue;
                    default:
                        break;
                }
            }
            // second condition, checking is the cell in the array is equals to DELETED_VALUE mark.
            if (closedHashTable[index].equals(DELETED_VALUE)){
                // dividing for cases: add, delete, contains.
                switch (action) {
                    case PROBING_ADD:
                        closedHashTable[index] = value;
                        updateGeneralTableValues(MORE_ELEMENTS);
                        if (loadFactor > upperLoadFactor) {
                            updateGeneralTableValues(BIGGER_TABLE);
                            reHash();
                            return true;
                        }
                        return true;
                    case PROBING_CONTAINS:
                        continue;
                    case PROBING_DELETE:
                        continue;
                    default:
                        break;
                }
            }
            // third condition, checking if the cell in the array is equals to value.
            if (closedHashTable[index].equals(value)){
                switch (action) {
                    case PROBING_ADD:
                        return false;
                    case PROBING_CONTAINS:
                        return true;
                    case PROBING_DELETE:
                        closedHashTable[index] = DELETED_VALUE;
                        updateGeneralTableValues(LESS_ELEMENTS);
                        if (loadFactor < lowerLoadFactor) {
                            updateGeneralTableValues(SMALLER_TABLE);
                            reHash(); // i like spaghetti :)
                            return true;
                        }
                        return true;
                    default:
                        break;
                }
            }
        }
        return false;
    }
}

