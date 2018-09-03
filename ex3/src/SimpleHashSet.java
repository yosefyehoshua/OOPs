/**
 * an abstract class implementing SimpleSet.
 * Created by Yosef.Yehoshua.
 */
abstract public class SimpleHashSet implements SimpleSet {

    /**
     * int number of elements in hash set
     */
    protected int numberOfElements = 0;
    /**
     * int initialCapacity of the set
     */
    final protected int INITIAL_CAPACITY = 16;

    /**
     * int current Capacity may change
     */
    protected int currentCapacity = 16;

    protected int capacityMinusOne = currentCapacity - 1;

    /**
     * float loadFactor
     */
    protected float loadFactor = numberOfElements / currentCapacity;

    /**
     * float upperLoadFactor
     */
    protected float upperLoadFactor = 0.75f;

    /**
     * float lowerLoadFactor
     */
    protected float lowerLoadFactor = 0.25f;

    protected final int MORE_ELEMENTS = 1;
    protected final int LESS_ELEMENTS = 2;
    protected final int BIGGER_TABLE = 3;
    protected final int SMALLER_TABLE = 4;

    /** -------- Methods ------------ */

    /**
     * Add a specified element to the set if it's not already in it.
     *
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set
     */
    public abstract boolean add(java.lang.String newValue);

    /**
     * Look for a specified value in the set.
     *
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    public abstract boolean contains(java.lang.String searchVal);

    /**
     * Remove the input element from the set.
     *
     * @param toDelete Value to delete
     * @return True if toDelete is found and deleted
     */
    public abstract boolean delete(java.lang.String toDelete);

    /**
     * @return The number of elements currently in the set
     */
    public int size() {return numberOfElements;}

    /**
     * capacity in class SimpleHashSet
     * @return The current capacity (number of cells) of the table.
     */
    public int capacity() {return currentCapacity; }

    /**
     * update numberOfElements or currentCapacity given the needed case and also update the loadfactor
     * @param whatToDo int const. symbolize what to do...
     */
    protected void updateGeneralTableValues(int whatToDo) {
        switch (whatToDo) {
            case MORE_ELEMENTS:
                numberOfElements++;
                break;
            case LESS_ELEMENTS:
                numberOfElements--;
                break;
            case BIGGER_TABLE:
                currentCapacity *= 2;
                break;
            case SMALLER_TABLE:
                currentCapacity /= 2;
                break;
            default:
                break;
        }
        loadFactor = numberOfElements / (float) currentCapacity;
    }
}
