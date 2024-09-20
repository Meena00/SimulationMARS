/**
 * A dynamic array-based class to simulate a line of items, allowing adding,
 * removing, and retrieving items by index.
 *
 * @param <T> the type of elements in this line
 */
class Line<T> {
    /**
     * The array that stores the elements of the line.
     */
    private T[] data;
    
    /**
     * The current number of elements in the line.
     */
    private int size;

    /**
     * Constructs a new Line with an initial capacity of 1.
     */
    @SuppressWarnings("unchecked")
    public Line() {
        // Default for data should be capacity of 1
        data = (T[]) new Object[1];  // Initialize with capacity of 1
        size = 0;  // No elements initially
    }

    /**
     * Returns the element at the specified index in the line.
     *
     * @param index the index of the element to retrieve
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public T get(int index) {
        // Should throw appropriate exception if index is out of bounds
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return data[index];
    }

    /**
     * Removes the element at the specified index in the line, shifting any
     * subsequent elements to the left.
     *
     * @param index the index of the element to remove
     * @return the removed element
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public T remove(int index) {
        // Should throw appropriate exception if index is out of bounds
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        // Remove the object at the given index
        T removedItem = data[index];
        
        // Shift elements to the left
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        
        // Nullify the last item and decrease the size
        data[size - 1] = null;
        size--;
        
        return removedItem;
    }

    /**
     * Adds the specified element at the specified index in the line, shifting
     * any subsequent elements to the right. If the line reaches its capacity,
     * it is doubled.
     *
     * @param item  the element to add
     * @param index the index at which to insert the element
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    @SuppressWarnings("unchecked")
    public void add(T item, int index) {
        // Should throw appropriate exception if index is out of bounds
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        
        // Double the data array size if more space is needed
        if (size == data.length) {
            T[] newData = (T[]) new Object[data.length * 2];
            // Copy existing elements
            for (int i = 0; i < size; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }

        // Shift elements to the right
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        // Add the new item
        data[index] = item;
        size++;
    }

    /**
     * Returns the number of elements in the line.
     *
     * @return the number of elements in the line
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the current capacity of the line.
     *
     * @return the current capacity of the line
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * The main method for testing the functionality of the Line class.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // This is a main-method tester.
        // You may alter/change/remove this method.
        
        // It doesn't test everything, but it should give you an idea of
        // the types of scenarios you should be testing when writing this
        // class. The JUnit tests for grading will test a lot more things!
        
        Line<Integer> test = new Line<>();
        if(test.getSize() == 0 && test.getCapacity() == 1) {
            System.out.println("yay 1");
        }
        
        test.add(1, 0);
        if(test.getSize() == 1 && test.getCapacity() == 1 && test.get(0) == 1) {
            System.out.println("yay 2");
        }
        
        test.add(2, 0);
        if(test.getSize() == 2 && test.getCapacity() == 2 && test.get(0) == 2 && test.get(1) == 1) {
            System.out.println("yay 3");
        }
        
        test.add(3, 2);
        if(test.getSize() == 3 && test.getCapacity() == 4 && test.get(0) == 2 && test.get(1) == 1 && test.get(2) == 3) {
            System.out.println("yay 4");
        }
        
        test.remove(2);
        if(test.getSize() == 2 && test.getCapacity() == 4 && test.get(0) == 2 && test.get(1) == 1) {
            System.out.println("yay 5");
        }
        
        test.remove(0);
        if(test.getSize() == 1 && test.getCapacity() == 4 && test.get(0) == 1) {
            System.out.println("yay 6");
        }
        
        try {
            test.add(4, 2);
        }
        catch(IndexOutOfBoundsException e) {
            System.out.println("yay 7");
        }
        
        try {
            test.remove(-1);
        }
        catch(IndexOutOfBoundsException e) {
            System.out.println("yay 8");
        }
        
        test.remove(0);
        try {
            test.get(0);
        }
        catch(IndexOutOfBoundsException e) {
            System.out.println("yay 9");
        }
    }
}
