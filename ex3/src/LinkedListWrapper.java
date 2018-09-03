import java.util.LinkedList;

/**
 * a wrapper-class that has-a LinkedList<String> and delegates methods to it.
 *
 * Created by Yosef Yehoshua.
 */
public class LinkedListWrapper {

    /** LinkedList<String> object type */
    protected LinkedList<String> linkedListWrapper = new LinkedList<>();

    /** _________________________ Methods _________________________ */

    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set
     */
    public boolean add(String newValue) {
        if (!contains(newValue)) {
            return this.linkedListWrapper.add(newValue);
        }
        return false;
    }
    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    public boolean contains(String searchVal) {
        return this.linkedListWrapper.contains(searchVal);
    }
    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    public boolean delete(String toDelete) {
        if (contains(toDelete)) {
            return this.linkedListWrapper.remove(toDelete);
        }
        return Boolean.parseBoolean(null);
    }

    /**
     * poping the first value
     * @return String of the removed value
     */
    public String pop() {
        if (this.linkedListWrapper.size() > 0) {
            return this.linkedListWrapper.remove();
        }
        return null;
    }
    /**
     * @return The number of elements currently in the set
     */
    public int size() {
        return linkedListWrapper.size();
    }

}
