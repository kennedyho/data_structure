import java.util.Iterator;

/**
 * Dynamic array implementation
 *
 * @author Kennedy Ho
 */

public class DynamicArray<T> implements Iterable<T> {

    private T array[];
    private int size = 0;
    private int capacity = 0;

    public DynamicArray() {
        this(16);
    }

    public DynamicArray(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity of array cannot be less than 0!");
        }
        this.capacity = capacity;
        array = (T[]) new Object[this.capacity];
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return (size() == 0);
    }

    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index out of bounds!");
        }
        return this.array[index];
    }

    public void set(int index, T element) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index out of bounds!");
        }
        this.array[index] = element;
    }

    public void clear() {
        for (T item : this.array) {
            item = null;
        }
        this.size = 0;
    }

    public void add(T element) {
        if (element == null) {
            throw new NullPointerException("The element is null!");
        }

        if (size() + 1 >= capacity) {
            if (capacity == 0) {
                capacity = 1;
            } else {
                this.capacity *= 2;
            }
            T[] newArray = (T[]) new Object[this.capacity];
            for (int i = 0; i < size(); ++i) {
                newArray[i] = this.array[i];
            }
            this.array = newArray;
        }
        this.array[size++] = element;
    }

    public T removeAt(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index out of bounds!");
        }
        T temp = this.array[index];

        for (int i = index + 1; i < size(); ++i) {
            this.array[i - 1] = this.array[i];
        }
        this.array[--size] = null;

        // resize it to a smaller capacity if size is less than 1/4 of current capacity
        if (size() <= capacity / 4) {
            T[] newArray = (T[]) new Object[size()];
            for (int i = 0; i < size(); ++i) {
                newArray[i] = this.array[i];
            }
            this.array = newArray;
        }

        return temp;
    }

    public boolean remove(Object object) {
        int index = indexOf(object);
        if (index == -1) {
            return false;
        }
        removeAt(index);
        return true;
    }

    public int indexOf(Object object) {
        for (int i = 0; i < size(); ++i) {
            if (object == null) {
                if (array[i] == null) {
                    return i;
                }
            } else {
                if (object.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean contains(Object object) {
        return (indexOf(object) != -1);
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder().append("[");
        for (int i = 0; i < size() - 1; ++i) {
            sb.append(array[i]);
            sb.append(", ");
        }
        sb.append(array[size() - 1]);
        return sb.append("]").toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return (index < size());
            }

            @Override
            public T next() {
                return array[index++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String args[]) {
        DynamicArray<Integer> list = new DynamicArray<>(1);

        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println("Size: " + list.size());

        System.out.println(list.get(1));

        System.out.println(list.toString());

        list.removeAt(2);
        System.out.println("Size: " + list.size());
        System.out.println(list.toString());

        list.remove(4);
        System.out.println("Size: " + list.size());
        System.out.println(list.toString());
    }
}
