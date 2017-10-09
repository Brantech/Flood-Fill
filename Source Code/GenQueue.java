package FloodFill;

import java.util.HashMap;

// A generic queue used to store data at the tail of the linked list, and retrieve from the head.
// This class will also limit the amount of duplicates being added to the queue.
class GenQueue<T> {
    
    
    private final HashMap<T, Integer> duplicates;
    private final int maxDup;
    // maxDup is the maximum amount of duplicates that can be stored in the queue.
    // duplicates is a HashMap that uses the value as a key and stores how many times the key was added.
    private Node<T> head, tail;
    
    // Constructor takes the maximum duplicate count when the class is instantiated.
    public GenQueue( int maxDup ) {
        duplicates = new HashMap<>();
        this.maxDup = maxDup;
    }
    
    // Adds the passed value to the queue if adding it won't exceed the maximum duplicate
    // count for that value.
    public void enqueue( T value ) {
        Boolean key = duplicates.containsKey(value);
        int count = key ? duplicates.get(value) : -1;
        
        if( head == null ) {
            head = tail = new Node<>(value);
        } else if( count + 1 > maxDup ) return;
        else {
            Node<T> temp = new Node<>(value);
            tail.next = temp;
            tail = temp;
        }
        
        duplicates.put(value, count + 1);
    }
    
    // Removes the head node and reduces its duplicate count.
    public T dequeue() {
        if( head == null ) return null;
        
        T temp = head.value;
        if( head == tail ) head = tail = null;
        else {
            head = head.next;
        }
        
        int count = duplicates.get(temp);
        if( count == 0 ) duplicates.remove(temp);
        else duplicates.put(temp, count--);
        
        return temp;
    }
    
    // Returns the value at the front of the queue.
    public T peek() {
        if( head == null ) return null;
        return head.value;
    }
    
    // Empties the queue and clears the duplicate hashmap.
    public void clear() {
        head = tail = null;
        duplicates.clear();
    }
    
    // Ordinary node class which stores a value and the next node.
    class Node<T> {
        final T value;
        Node<T> next;
        
        public Node( T value ) {
            this.value = value;
        }
    }
}
