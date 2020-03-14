import java.util.Iterator;

public class Stack<T> implements Iterable<T> {
    // Linked list to simulate the stack
    private java.util.LinkedList<T> stack = new java.util.LinkedList<>();

    // Default empty constructor
    public Stack() {
    }

    // Parameterized constructor
    public Stack(T elem) {
        push(elem);
    }

    // Get the size of stack
    public int size() {
        return stack.size();
    }

    // Check if stack is empty or not
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    // Push item to the head of the linked list
    public void push(T elem) {
        stack.addFirst(elem);
    }

    // Pop item from the head of the linked list
    public T pop() {
        if (isEmpty()) {
            throw new java.util.EmptyStackException();
        }
        return stack.removeFirst();
    }

    // Return the first item of the linked list
    public T peek() {
        if (isEmpty()) {
            throw new java.util.EmptyStackException();
        }
        return stack.getFirst();
    }

    @Override
    public Iterator<T> iterator() {
        return stack.iterator();
    }
}
