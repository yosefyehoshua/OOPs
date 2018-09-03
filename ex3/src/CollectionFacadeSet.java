import java.util.Collection;

/**
 * Wraps an underlying Collection and serves to both simplify its API and give it a common type with the
 * implemented SimpleHashSets.
 *
 * Created by Yosef Yehoshua.
 */
public class CollectionFacadeSet implements SimpleSet {

    protected int collectionSize;

    /** LinkedList<String> object type */
    Collection<String> collection;

    public CollectionFacadeSet(Collection<String> collection) {
        this.collection = collection;
        this.collectionSize = collection.size();
    }

    /**
     * Add a specified element to the set if it's not already in it.
     *
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set
     */
    public boolean add(String newValue) {
        if (!contains(newValue)) {
            collectionSize++;
            return this.collection.add(newValue);
        }
        return false;
    }

    /**
     * Look for a specified value in the set.
     *
     * @param searchVal Value to search for
     * @return True if searchVal is found in the set
     */
    public boolean contains(String searchVal) {
        return this.collection.contains(searchVal);
    }

    /**
     * Remove the input element from the set.
     *
     * @param toDelete Value to delete
     * @return True if toDelete is found and deleted
     */
    public boolean delete(String toDelete) {
        if (!contains(toDelete)) {
            collectionSize--;
            return this.collection.remove(toDelete);
        }
        return false;
    }

    /**
     * @return The number of elements currently in the set
     */
    public int size() {
        return collectionSize;
    }
}
