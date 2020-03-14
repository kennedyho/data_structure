import java.util.EmptyStackException;
import java.util.Iterator;

public class Queue<T> implements Iterable<T> {
    private java.util.LinkedList<T> queue = new java.util.LinkedList<T>();

    // Default constructor
    public Queue() {
    }

    // Insert first element
    public Queue(T elem) {
        offer(elem);
    }

    // Return the size of the queue
    public int size() {
        return queue.size();
    }

    // Check if the queue is empty or not
    public boolean isEmpty() {
        return (size() == 0);
    }

    // Add the element to the end of list
    public void offer(T elem) {
        queue.addLast(elem);
    }

    // Remove the element from the front of the list
    public T poll() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return queue.removeFirst();
    }

    // Return the element at the front of the list
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return queue.getFirst();
    }

    @Override
    public Iterator<T> iterator() {
        return queue.iterator();
    }
}
