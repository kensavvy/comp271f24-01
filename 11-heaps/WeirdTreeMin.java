import java.util.ArrayList;

/** A naive min heap for integers */

public class WeirdTreeMin {

    /** A dynamic array to hold the contents of the complete binary tree */
    private ArrayList<Integer> heap;

    /** Default constructor */
    public WeirdTreeMin() {
        this.heap = new ArrayList<>();
    } // default constructor

    /**
     * The position of the left child of node at position i in the array
     * 
     * @param i parent index
     * @return index of parent's left child
     */
    public int leftChildIndex(int i) {
        return 2 * i + 1;
    } // method leftChildIndex

    /**
     * The position of the right child of node at position i in the array
     * 
     * @param i parent index
     * @return index of parent's right child
     */
    public int rightChildIndex(int i) {
        return 2 * (i + 1);
    } // method rightChildIndex

    /**
     * The position of the parent of a node at position i in the array
     * 
     * @param i child index
     * @return position of child's parent
     */
    public int parentIndex(int i) {
        return (i - 1) / 2;
    } // method parentIndex

    /**
     * Add a new value to the tree and ensure that the min-heap property is
     * observed.
     * 
     * @param value int value to add
     */
    public void insert(int value) {
        // Place the new value at the end of the arraylist
        this.heap.add(value);
        // Move the newly added value where it needs to be
        this.floatUp(heap.size() - 1);
    } // method insert

    /**
     * Ensure that the element at position i is properly moved through the array to
     * maintain the min. heap property. The element is compared to the value of its
     * parent node (whose position is available through the parentIndex method. If
     * the element's value is less that its parents', we swap places and try again,
     * noting that the element is now at the parent's position.)
     * 
     * @param i position of the element to ensure it's properly ... heapified.
     */
    public void floatUp(int i) {
        // Loop ends when item reaches the root of the tree (i==0) or when the heap
        // property parent < child is achieved earlier.
        while (i > 0 && heap.get(i) < heap.get(parentIndex(i))) {
            // Swap child with parent
            this.swap(i, parentIndex(i));
            // repeat for the new position of the child
            // (which is now where its parent was)
            i = parentIndex(i);
        }
    } // method floatUp

    /**
     * Swap places between two elements in the arraylist
     * 
     * @param i position of the first of the two elements
     * @param j position of the second of the two elements
     */
    public void swap(int i, int j) {
        int temp = this.heap.get(i);
        this.heap.set(i, this.heap.get(j));
        this.heap.set(j, temp);
    } // method swap

    /**
     * Obtain the minimum value in the arraylist without removing the element
     * 
     * @return the first element of the arraylist always contains the smallest value
     */

    public int getMin() {
        return this.heap.get(0);
    } // method getMin

    /**
     * Remove and return the smallest value in the array list, then rearrange the
     * arraylist to maintain the min. heap property.
     * 
     * @return the first element of the arraylist which is always the smallest value
     *         in the arraylist.
     */
    public int removeMin() {
        // Item to return
        int minValue = this.getMin();
        int lastElement = this.heap.remove(this.heap.size() - 1);
        // Take the last item out of the arraylist and place it in the beginning (since
        // the element there is being returned as minValue)
        if (!this.heap.isEmpty()) {
            // Place the last element to the beginning of the heap. This will probably
            // violate the min heap property.
            this.heap.set(0, lastElement);
            // Restore min heap property by pushing the newly placed value as far down as
            // needed.
            this.floatDn(0);
        }
        return minValue;
    } // method removeMin

    /**
     * Move a value down the tree where it is under a parent node with a smaller
     * value -- effectively we perform this operation by moving the value further
     * into the array until the min heap property is restored.
     * 
     * @param i value in array to move to a new position that restores the heap
     *          property
     */
    public void floatDn(int i) {
        int minIndex = i;
        int left = this.leftChildIndex(i);
        if (left < this.heap.size() && this.heap.get(left) < this.heap.get(minIndex)) {
            minIndex = left;
        }
        int right = rightChildIndex(i);
        if (right < this.heap.size() && this.heap.get(right) < this.heap.get(minIndex)) {
            minIndex = right;
        }
        if (i != minIndex) {
            this.swap(i, minIndex);
            this.floatDn(minIndex);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!this.heap.isEmpty()) {
            int i = 0;
            while (i < this.heap.size()) {
                sb.append(String.format("[ %d ]", this.heap.get(i)));
                i++;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        WeirdTreeMin demo = new WeirdTreeMin();
        demo.insert(50);
        demo.insert(10);
        demo.insert(66);
        demo.insert(5);
        System.out.println(demo);
    }

}
