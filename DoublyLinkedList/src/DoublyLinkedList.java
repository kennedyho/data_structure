import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T> {
    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> previous;

        public Node(T data, Node<T> previous, Node<T> next) {
            this.data = data;
            this.previous = previous;
            this.next = next;
        }
    }

    public void clear() {
        Node<T> pointer = head;
        while (pointer != null) {
            Node<T> nextItem = pointer.next;
            pointer.data = null;
            pointer.previous = null;
            pointer.next = null;
            pointer = nextItem;
        }
        head = tail = pointer = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public void add(T element) {
        addLast(element);
    }

    public void addLast(T element) {
        if (isEmpty()) {
            head = tail = new Node<T>(element, null, null);
        } else {
            tail.next = new Node<T>(element, tail, null);
            tail = tail.next;
        }
        ++size;
    }

    public void addFirst(T element) {
        if (isEmpty()) {
            head = tail = new Node<T>(element, null, null);
        } else {
            head.previous = new Node<T>(element, null, head);
            head = head.previous;
        }
        ++size;
    }

    public void addAt(int index, T element) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            addFirst(element);
            return;
        }

        if (index == size()) {
            addLast(element);
            return;
        }

        Node<T> temp = head;
        for (int i = 0; i < index - 1; ++i) {
            temp = temp.next;
        }
        Node<T> newNode = new Node<T>(element, temp, temp.next);
        temp.next.previous = newNode;
        temp.next = newNode;

        ++size;
    }

    public T peekFirst() {
        if (isEmpty()) {
            throw new RuntimeException("List is empty!");
        }
        return head.data;
    }

    public T peekLast() {
        if (isEmpty()) {
            throw new RuntimeException("List is empty!");
        }
        return tail.data;
    }

    public T removeFirst() {
        if (isEmpty()) {
            throw new RuntimeException("List is empty!");
        }
        T temp = head.data;
        head = head.next;
        --size;

        if (isEmpty()) {
            tail = null;
        } else {
            head.previous = null;
        }

        return temp;
    }

    public T removeLast() {
        if (isEmpty()) {
            throw new RuntimeException("List is empty!");
        }

        T temp = tail.data;
        tail = tail.previous;
        --size;

        if (isEmpty()) {
            head = null;
        } else {
            tail.next = null;
        }

        return temp;
    }

    public T remove(Node<T> node) {
        if (isEmpty()) {
            throw new RuntimeException("Empty list.");
        }

        if (node.previous == null) {
            return removeFirst();
        }

        if (node.next == null) {
            return removeLast();
        }

        node.previous.next = node.next;
        node.next.previous = node.previous;

        T temp = node.data;
        node.previous = null;
        node.next = null;
        node.data = null;
        node = null;
        --size;

        return temp;
    }

    public T removeAt(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            return removeFirst();
        }

        if (index == size() - 1) {
            return removeLast();
        }

        Node<T> nodeToRemove = head;
        for (int i = 0; i < index; ++i) {
            nodeToRemove = nodeToRemove.next;
        }

        return remove(nodeToRemove);
    }

    public boolean remove(Object obj) {
        Node<T> pointer;
        if (obj == null) {
            for (pointer = head; pointer != null; pointer = pointer.next) {
                if (pointer.data == null) {
                    remove(pointer);
                    return true;
                }
            }
        } else {
            for (pointer = head; pointer != null; pointer = pointer.next) {
                if (pointer.data.equals(obj)) {
                    remove(pointer);
                    return true;
                }
            }
        }
        return false;
    }

    public int indexOf(Object obj) {
        Node<T> pointer = head;
        if (obj == null) {
            for (int i = 0; pointer != null; ++i, pointer = pointer.next) {
                if (pointer.data == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; pointer != null; ++i, pointer = pointer.next) {
                if (pointer.data.equals(obj)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private Node<T> pointer = head;

            @Override
            public boolean hasNext() {
                return (pointer.next != null);
            }

            @Override
            public T next() {
                T temp = pointer.data;
                pointer = pointer.next;
                return temp;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder().append("[");
        Node<T> pointer = head;
        while (pointer != null) {
            sb.append(pointer.data);
            if (pointer.next != null) {
                sb.append(", ");
            }
            pointer = pointer.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        System.out.println(list.toString());
    }

}
