package oop.ex4.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of the AVL tree data structure.
 *
 * Created by Yosef.Yehoshua on 04/05/2016.
 */
public class AvlTree implements Iterable<Integer> {

    private static final int PLACE_TO_THE_LEFT = -1;
    private static final int PLACE_TO_THE_RIGHT = 1;
    private static final int INITIAL_SIZE = 0;
    private static final int INITIAL_HEIGHT = 0;
    private static final int LR_ROTATION = -2;
    private static final int LL_ROTATION = -1;
    private static final int RR_ROTATION = 1;
    private static final int RL_ROTATION = 2;
    private static final int LEFT_BALANCE_LIMIT = 2;
    private static final int RIGHT_BALANCE_LIMIT = -2;
    private static final int LEFT_LEFT_BALANCE_LIMIT = 1;
    private static final int LEFT_RIGHT_BALANCE_LIMIT = -1;
    private static final int BALANCED = 0;
    private static final int NOT_CONTAINS = -1;


    private static final int INITIAL_DEPTH = 0;
    private static final int NO_VALID_HEIGHT = -1;
    private static final int NO_VALID_DEPTH = -1;
    private static final int LEAF = 0;
    private static final int ONLY_LEFT_SON = 1;
    private static final int ONLY_RIGHT_SON = 2;
    private static final int TWO_SONS = 3;

    /** AVL tree Iterator */
    AvlNode root;
    int size;
    int desiredLocation;
    /**============================ Constructors ============================**/

    /**
     * The default constructor.
     */
    public AvlTree(){
        root = null;
        size = INITIAL_SIZE;
    }

    /**
     * A constructor that builds a new AVL tree containing all unique values in the input array.
     * @param data - the values to add to tree.
     */
    public AvlTree(int[] data){
        this();
        for (int newValue : data) {
            this.add(newValue);
        }
    }

    /**
     * A copy constructor that creates a deep copy of the given AvlTree. The new tree contains all the values
     * of the given tree, but not necessarily in the same structure.
     * @param tree - The AVL tree to be copied.
     */
    public AvlTree(AvlTree tree) {
        for (int node : tree) {
            this.add(node);
        }
    }

    /**============================== Methods ==============================**/

    /**
     * set new root
     * @param newRoot AvlNode type
     */
    private void setRoot(AvlNode newRoot){
        root = newRoot;
    }

    /**
     * Add a new node with the given key to the tree.
     * @param newValue the value of the new node to add.
     * @return true if the value to add is not already in the tree and it was successfully added, false
     * otherwise.
     */
    public boolean add(int newValue){
        if (size == INITIAL_SIZE){
            setRoot(new AvlNode(newValue));
            size++;
            return true;
        }
        AvlNode nodeToAdd = searchNode(newValue);
        if (nodeToAdd.getValue() == newValue) { return false; }
        if (desiredLocation == PLACE_TO_THE_LEFT){ // adding to the left
            AvlNode newNode = new AvlNode(newValue);
            AvlNode.setLeftSon(newNode, nodeToAdd);
            size++;
            violationsTraverse(newNode);
            return true;
        } else if (desiredLocation == PLACE_TO_THE_RIGHT) { // adding to the right
            AvlNode newNode = new AvlNode(newValue);
            AvlNode.setRightSon(newNode, nodeToAdd);
            size++;
            violationsTraverse(newNode);
            return true;
        }
        return false;
    }

    /**
     * Check whether the tree contains the given input value.
     * @param searchVal value to search for
     * @return if val is found in the tree, return the depth of the node (0 for the root) with the given
     * value if it was found in the tree, -1 otherwise.
     */
    public int contains(int searchVal) {
            if (size == 0) { return NOT_CONTAINS; }
            AvlNode nodeInTree = searchNode(searchVal);
            if (nodeInTree.getValue() == searchVal) { return nodeInTree.getDepth(); }
            return NOT_CONTAINS;
    }


    /**
     * Removes the node with the given value from the tree, if it exists.
     * @param toDelete the value to remove from the tree.
     * @return true if the given value was found and deleted, false otherwise.
     */
    public boolean delete(int toDelete) {
        if (size == INITIAL_SIZE) { return false; }
        AvlNode nodeToDelete = searchNode(toDelete);
        if (nodeToDelete.getValue() != toDelete) {
            return false;
        }
        switch (AvlNode.isParent(nodeToDelete)) {
            case (LEAF): // 0 is node that have no sons,
                deleteALeaf(nodeToDelete);
                violationsTraverse(nodeToDelete);
                size--;
                return true;
            case (ONLY_LEFT_SON): // 1 if it has only a left child
                deleteALeftSingleSonParent(nodeToDelete);
                violationsTraverse(nodeToDelete);
                size--;
                return true;
            case (ONLY_RIGHT_SON): //  2 if it has only a right son
                deleteARightSingleSonParent(nodeToDelete);
                violationsTraverse(nodeToDelete);
                size--;
                return true;
            case (TWO_SONS): // 3 if it has both.
                deleteATwoSonsParent(nodeToDelete);
                violationsTraverse(nodeToDelete);
                size--;
                return true;
        }
        return false;
    }

    /**
     * a small function that deals with the first case of deletion: when the deleted node is a leaf
     * @param leaf AvlNode type
     */
    private void deleteALeaf(AvlNode leaf){
        if (desiredLocation == PLACE_TO_THE_LEFT){
            AvlNode.setLeftSon(null, leaf.parent);
        } else if (desiredLocation == PLACE_TO_THE_RIGHT) { // desiredLocation == 1
            AvlNode.setRightSon(null, leaf.parent);
        }
    }

    /**
     * a small function that deals with the second1 case of deletion when the deleted node is a parent of a
     * single left node
     * @param singleSonParent AvlNode type
     */
    private void deleteALeftSingleSonParent(AvlNode singleSonParent) {
        AvlNode.setLeftSon(singleSonParent.leftSon, singleSonParent.parent);
    }

    /**
     * a small function that deals with the second2 case of deletion when the deleted node is a parent of a
     * single right node
     * @param singleSonParent AvlNode type
     */
    private void deleteARightSingleSonParent(AvlNode singleSonParent) {
        AvlNode.setRightSon(singleSonParent.rightSon,singleSonParent.parent);
    }

    /**
     * a small function that deals with the third case of deleting a node when it has two sons
     * @param TwoSonsParent AvlNode type
     */
    private void deleteATwoSonsParent(AvlNode TwoSonsParent){
        AvlNode successor = findSuccessor(TwoSonsParent);
        TwoSonsParent.setNewValue(successor.getValue()); // replacing TwoSonsParent with its successor..
        AvlNode.setLeftSon(AvlNode.getRightSon(successor) ,AvlNode.getParent(successor));
    }

    /**
     * @return the number of nodes in the tree.
     */
    public int size(){return size;}
    /**
     * Returns an iterator over elements of type {@code T}.
     * @return an iterator for the Avl Tree. The returned iterator iterates over the tree nodes in an
     * ascending order, and does NOT implement the remove() method.
     */
    public Iterator<Integer> iterator() {
        AvlNode minNode = getMinNode(root);
        return new MyIter(minNode);
    }

    /**
     * Calculates the minimum number of nodes in an AVL tree of height h.
     * @param h the height of the tree (a non-negative number) in question.
     * @return the minimum number of nodes in an AVL tree of the given height.
     */
    public static int findMinNodes(int h) { // no need of magic number here its a set up equation..
        return (int)(Math.round(((Math.sqrt(5)+2)/ Math.sqrt(5))*Math.pow((1+ Math.sqrt(5))/2,h)-1));
    }

    /**
     * given a node it will find the successor if the node doesnt have any it will return none
     * @param node AvlNode class type
     * @return AvlNode - successor or null if the node doesnt have any
     */
    private AvlNode findSuccessor(AvlNode node){ // TODO: 09/05/2016 check if you covered all cases!!
        if (node == null){ return null; }
        if (node.rightSon == null) {
            if (node.parent != null){
                return node.parent;
            } else {
                return null;
            }
        } else { return getMinNode(node.rightSon); }
    }

    /**
     * find the minimum node in a tree
     * @param node AvlNode class type
     * @return min node or null will there is only one node in the tree
     */
    private AvlNode getMinNode(AvlNode node){
        if (node == null) {
            return null;
        }
        AvlNode curRoot = null;
        while (node != null){
            curRoot = node;
            node = node.leftSon;
        }
        return curRoot;
    }

    /**
     * rotate the the given node left
     * @param node AvlNode Class
     */
    private void leftRotation(AvlNode node){
        AvlNode oldRightSon = node.rightSon;
        oldRightSon.parent = node.parent;
        node.rightSon = oldRightSon.leftSon;
        if (node.rightSon != null){
            node.rightSon.parent = node;
        }
        oldRightSon.leftSon = node;
        node.parent = oldRightSon;
        if (oldRightSon.parent != null){
            if (AvlNode.getRightSon(oldRightSon.parent) == node){
                oldRightSon.parent.rightSon = oldRightSon;
            }   else if (AvlNode.getLeftSon(oldRightSon.parent) == node){
                oldRightSon.parent.leftSon = oldRightSon;
            }
        }
    }

    /**
     * rotate the the given node right
     * @param node AvlNode Class
     */
    private void rightRotation(AvlNode node){
        AvlNode oldLeftSon = node.leftSon;
        oldLeftSon.parent = node.parent;
        node.leftSon = oldLeftSon.rightSon;
        if (node.leftSon != null){
            node.leftSon.parent = node;
        }
        oldLeftSon.rightSon = node;
        node.parent = oldLeftSon;
        if (oldLeftSon.parent != null){
            if (AvlNode.getRightSon(oldLeftSon.parent) == node){
                oldLeftSon.parent.rightSon = oldLeftSon;
            } else if (AvlNode.getLeftSon(oldLeftSon.parent) == node){
                oldLeftSon.parent.leftSon = oldLeftSon;
            }
        }
    }

    /**
     * rotate the nodes using the type of violation
     * @param node AvlNode class
     */
    private void isViolated(AvlNode node){
        if (node != null){
            switch (findViolationType(node)){
                case (LR_ROTATION): // LR rotation
                    leftRotation(AvlNode.getLeftSon(node));
                    rightRotation(node);
                    break;
                case (LL_ROTATION): // LL rotation
                    rightRotation(node);
                    break;
                case (RR_ROTATION): // RR rotation
                    leftRotation(node);
                    break;
                case (RL_ROTATION): // RL rotation
                    rightRotation(AvlNode.getRightSon(node));
                    leftRotation(node);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * finds violation in the the tree using balance factor
     * @param node AvlNode class
     * @return int while: -1 is LL rotation, -2 LR rotation, 1 is RR rotation, 2 RL rotation
     */
    private int findViolationType(AvlNode node){
        int balanceFactor = node.getBalance();
        if (balanceFactor == LEFT_BALANCE_LIMIT){
            if (node.leftSon.getBalance() == LEFT_LEFT_BALANCE_LIMIT){
                return LL_ROTATION; // LL rotation
            } else if (node.leftSon.getBalance() == LEFT_RIGHT_BALANCE_LIMIT) {
                return LR_ROTATION; // LR rotation
            }
        } else if (balanceFactor == RIGHT_BALANCE_LIMIT){
            if (node.rightSon.getBalance() == LEFT_RIGHT_BALANCE_LIMIT){
                return RR_ROTATION; // RR rotation
            } else if (node.rightSon.getBalance() == LEFT_LEFT_BALANCE_LIMIT){
                return RL_ROTATION; // RL rotation
            }
        }
        return BALANCED;
    }

    /**
     * traverse on tree till root
     * @param node AvlNode class type
     */
    private void violationsTraverse(AvlNode node){
        AvlNode curNode;
        curNode = node;
        AvlNode newRood = null;
        while (curNode != null){
            newRood = curNode;
            isViolated(curNode);
            curNode = AvlNode.getParent(curNode);
        }
        if (newRood != null){
            setRoot(newRood); // update the root if it changed
        }

    }

    /**
     * search for a node in the tree, and update desiredLocation
     * @param searchValue
     * @return if the tree is empty it returns null, if the node is in the tree it will return the node,
     * if the the node isnt in the tree it will return the
     */
    private AvlNode searchNode(int searchValue){
        AvlNode curNode = root;
        AvlNode node = null;
        while (curNode != null) {
            node = curNode;
            if (curNode.value == searchValue) {
                return curNode;
            } else if (searchValue < curNode.value) {
                curNode = curNode.leftSon;
                desiredLocation = PLACE_TO_THE_LEFT;
            } else if (searchValue > curNode.value) {
                curNode = curNode.rightSon;
                desiredLocation = PLACE_TO_THE_RIGHT;
            }
        }
        return node;
    }

    /**##################################### Inner Classes #####################################**/


    /************************************** AvlNode Class ****************************************/
    /**
     * a class that represent a Node in an AVL tree
     * Created by Yosef.Yehoshua on 05/05/2016.
     */
    static class AvlNode {

        protected int value, height ,depth;
        protected int balance;
        protected AvlNode parent, leftSon, rightSon = null;

        /**============================ Constructor ============================**/

        /**
         * A constructor that builds a new node, with given key & value
         * @param Value integer the Value of the node
         */
        AvlNode(int Value) {
            value = Value;
            height = INITIAL_HEIGHT;
            depth = INITIAL_DEPTH;
        }

        /**============================== Getters ==============================**/

        /**
         * @return an int - value
         */
        public int getValue() {return value;}

        /**
         *
         * @param node AvlNode class
         * @return height
         */
        static int getHeight(AvlNode node) {
            if (node != null) {
                node.setHeight();
                return node.height;
            } else {
                return NO_VALID_HEIGHT;
            }
        }

        /**
         * @return depth
         */
        public int getDepth() {
            setDepth();
            return depth;
        }

        /**
         * @return tree balance
         */
        public int getBalance() {
            setBalance();
            return balance;
        }

        /**
         * @return rightSon
         */
        static AvlNode getRightSon(AvlNode node) {
            if (node != null) {
                return node.rightSon;
            }
            return null;
        }

        /**
         * @return leftSon
         */
        static AvlNode getLeftSon(AvlNode node) {
            if (node != null) {
                return node.leftSon;
            }
            return null;
        }

        /**
         * @return parent
         */
        static AvlNode getParent(AvlNode node) {
            if (node != null) {
                return node.parent;
            }
            return null;
        }

        /**============================== Setters ==============================**/

        public void setNewValue(int newValue) {
            value = newValue;
        }

        /**
         * sets a Parent for the node
         * @param newParent - the parent
         */
        static void setParent(AvlNode newSon, AvlNode newParent) {
            if (newSon != null) {
                newSon.parent = newParent;
                newSon.setDepth();
            }
        }

        /**
         * sets a left son for the node
         * @param newLeftSon - the left son
         */
        static void setLeftSon(AvlNode newLeftSon, AvlNode parent) {
            if (parent != null) {
                parent.leftSon = newLeftSon;
                setParent(newLeftSon, parent);
                parent.setHeight();
            }
        }

        /**
         * sets a right son for the node
         * @param newRightSon - the left son
         */
        static void setRightSon(AvlNode newRightSon, AvlNode parent) {
            if (parent != null) {
                parent.rightSon = newRightSon;
                setParent(newRightSon, parent);
                parent.setHeight();
            }
        }

        /**
         * sets the height of the node
         */
        protected void setHeight() { height = findHeight(this); }

        /**
         * sets the depth of the node
         */
        protected void setDepth() { depth = findDepth(this); }

        protected void setBalance() { balance = findBalance(this); }

        /**============================== Methods ==============================**/
        /**
         * finds the height of a given node
         * @param node AvlNode class type
         * @return integer the is the height of the node
         */
        protected int findHeight(AvlNode node) {
            if (node == null){
                return  NO_VALID_HEIGHT;
            } else {
                return Math.max(findHeight(node.leftSon), findHeight(node.rightSon)) + 1;
            }
        }

        /**
         * find the depth of a given node
         * @param node AvlNode class type
         * @return positive/zero integer of -1 if node is null
         */
        protected int findDepth(AvlNode node) {
            if (node == null) {
                return  NO_VALID_DEPTH;
            } else {
                return findDepth(getParent(node)) + 1;
            }
        }

        /**
         * finds balance by calculating the heights of left & right sub trees
         * @param node AvlNode class type
         * @return positive/negative integer represents the balance
         */
        protected int findBalance(AvlNode node){
            return getHeight(node.leftSon) - getHeight(node.rightSon);
        }

        /**
         * @param node AvlNode class
         * @return integer: 0 is node have no sons, 1 if it has only a left, 2 if it has only a right son
         * 3 if it has both.
         */
        static int isParent(AvlNode node){
            if (node.leftSon == null && node.rightSon == null){
                return LEAF;
            } else if (node.rightSon == null){
                return ONLY_LEFT_SON;
            } else if (node.leftSon == null){
                return ONLY_RIGHT_SON;
            } else {
                return TWO_SONS;
            }
        }
    }


    /************************************** MyIter Class ****************************************/
    /**
     * a local class of AVL tree iterator
     */
    class MyIter implements Iterator<Integer>{
        private static final int INITIAL_INDEX = 0;
        private static final int FIRST_ITER_INDEX = 1;

        AvlNode curNode;
        int index;
        MyIter(AvlNode node){
            index = INITIAL_INDEX;
            curNode = node;
        }
        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return index < size && curNode != null;
        }
        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public Integer next() {
            if (this.hasNext()){
                index++;
                if (index == FIRST_ITER_INDEX) { return curNode.value; }
                curNode = findSuccessor(curNode);
            } else {
                throw new NoSuchElementException();
            }
            return curNode.value;
        }
    }
}
