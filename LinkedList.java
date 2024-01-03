import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Cruz Guo
 * @version 1.0.0
 * @param <T> Any type of data in our linked list.
 */
public class LinkedList<T> implements List<T> {
    private Node<T> head;
    private int size;

    /**
     * A constructor that defines the default head and size of the linked list.
     */
    public LinkedList() {
        head = null;
        size = 0;
    }

    /**
     * A constructor that defines the head and size of the linked list.
     * @param data An array of data we are adding to our linked list
     */
    public LinkedList(T[] data) {
        if (data == null) {
            throw new IllegalArgumentException("Array is null or empty");
        }
        if (data.length == 0) {
            this.head = null;
            size = 0;
        } else {
            this.head = new Node<T>(data[0]);
            Node<T> current = this.head;
            size++;
            for (int i = 1; i < data.length; i++) {
                current.setNext(new Node<T>(data[i]));
                current = current.getNext();
                size++;
            }
        }
    }

    /**
     * A method that retrieves the head of the Linked List.
     *
     * @return The head of the Linked List.
     */
    public Node<T> getHead() {
        return head;
    }

    /**
     *A method that turns the data in the linked list into an array.
     *
     * @return An array containing the data in the linked list
     */
    public T[] toArray() {
        T[] arr = (T[]) new Object[size];
        LinkedListIterator<T> iterator = (LinkedListIterator<T>) iterator();
        for (int i = 0; i < arr.length && iterator.hasNext(); i++) {
            arr[i] = (iterator.next());
        }
        return arr;
    }

    /**
     * A method that prints out the information of the Linked List.
     *
     * @return The information of the Linked List.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(
                String.format("===== LINKEDLIST %d =====\\nisEmpty: %s\\nsize: %d\\\n"
                        + "nhead: %s\\ndata: [", hashCode(), isEmpty(), size(), (head == null ? "null" : head.getData())
                ));
        T[] data = toArray();
        if (data == null) {
            sb.append("TODO: Implement toArray method...");
        } else {
            for (int i = 0; i < data.length - 1; ++i) {
                sb.append(String.format("%s, ", data[i])); // append all but last value
            }
            if (data.length > 0) {
                sb.append(String.format("%s", data[data.length - 1])); // append last value
            }
        }
        sb.append("]\n============================");
        return sb.toString();
    }

    /**
     * A method that adds the specified element to the end of the linked list.
     *
     * @param element the element we are adding to the list
     * @throws IllegalArgumentException The specified element is null
     */
    @Override
    public void add(T element) throws IllegalArgumentException {
        if (element == null) {
            throw new IllegalArgumentException("Element is invalid / null");
        }
        if (this.head == null || this.size <= 0) {
            this.head = new Node<T>(element);
            size++;
        } else {
            Node<T> newNode = new Node<T>(element);
            LinkedListIterator<T> iterator = (LinkedListIterator<T>) iterator();
            Node<T> current = this.head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
            size++;
        }
    }

    /**
     * A method that adds the specified element at the specified index.
     *
     * @param index the index to add the element at
     * @param element the element we are adding to the list
     * @throws IndexOutOfBoundsException The specified index is out of the linked list's index
     * @throws IllegalArgumentException The specified element is null
     */
    @Override
    public void add(int index, T element) throws IndexOutOfBoundsException,
            IllegalArgumentException {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("No desired index found in the Linked List");
        }
        if (element == null) {
            throw new IllegalArgumentException("Element is null");
        }
        Node<T> current = this.head;
        Node<T> newNode = new Node<T>(element);
        Node<T> previous = this.head;
        if (index == 0) {
            newNode.setNext(this.head);
            this.head = newNode;
            size++;
        } else {
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
            size++;
        }
    }

    /**
     * A method that removes the first element in the linked list.
     *
     * @return The removed element
     * @throws NoSuchElementException When the list does not have any elements to remove.
     */
    @Override
    public T remove() throws NoSuchElementException {
        if (this.head == null || this.size <= 0) {
            throw new NoSuchElementException("There is no element to remove.");
        }
        T removes = this.head.getData();
        this.head = this.head.getNext();
        size--;
        return removes;
    }

    /**
     * A method that removes the element at the specified index.
     *
     * @param index the index of the element to be removed
     * @return The removed element from the linked list
     * @throws NoSuchElementException When no element is found
     * @throws IndexOutOfBoundsException When the index is out of the linked list's bounds.
     */
    @Override
    public T remove(int index) throws NoSuchElementException,
            IndexOutOfBoundsException {
        if (this.head == null || this.size <= 0) {
            throw new NoSuchElementException("There is no element to remove.");
        }
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("No desired index found in the Linked List");
        }
        Node<T> current = this.head;
        Node<T> previous = this.head;
        T removes = null;
        if (index == 0) {
            removes = this.head.getData();
            this.head = current.getNext();
            size--;
            return removes;
        }
        for (int i = 0; i < index + 1; i++) {
            if (i == index - 1) {
                previous = current;
            } else if (i == index) {
                removes = current.getData();
                break;
            }
            current = current.getNext();
        }
        if (current.getNext() != null) {
            previous.setNext(current.getNext());
        } else {
            previous.setNext(null);
        }
        size--;
        return removes;
    }

    /**
     * A method that removes the first occurrence of the specified element in the Linked List if available.
     *
     * @param element the element to be removed
     * @return The element removed from the linked list
     * @throws IllegalArgumentException The specified element wanted to be removed is null
     * @throws NoSuchElementException When specified element to be removed is not found in the Linked List
     */
    @Override
    public T remove(T element) throws IllegalArgumentException,
            NoSuchElementException {
        if (element == null) {
            throw new IllegalArgumentException("Element is null");
        }
        Node<T> current = this.head;
        Node<T> remove = null;
        if (this.head == null || this.size <= 0) {
            throw new NoSuchElementException("There is no element to remove");
        }
        if (element.equals(current.getData())) {
            this.head = current.getNext();
            size--;
            return current.getData();
        } else {
            while (current.getNext() != null) {
                if (element.equals(current.getNext().getData())) {
                    if (remove == null) {
                        remove = current.getNext();
                        if (remove.getNext() != null) {
                            current.setNext(remove.getNext());
                        } else {
                            current.setNext(null);
                        }
                        size--;
                    }
                    break;
                }
                current = current.getNext();
            }
            if (remove != null) {
                return remove.getData();
            } else {
                throw new NoSuchElementException("Element not fond in the list");
            }
        }
    }

    /**
     * A method that changes the element at the specified index to the specified element.
     *
     * @param index the index of the element to be replaced
     * @param element the element that should replace the existing element at the
    passed in index
     * @return The element originally at the index.
     * @throws IndexOutOfBoundsException When the specified index is out of the Linked List's boudns
     * @throws IllegalArgumentException When the desired element to be put into the list is null
     *
     */
    @Override
    public T set(int index, T element) throws IndexOutOfBoundsException,
            IllegalArgumentException {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index is invalid");
        }
        if (element == null) {
            throw new IllegalArgumentException("Desired element is invalid");
        }
        Node<T> current = this.head;
        T replaced = null;
        if (index == 0) {
            replaced = this.head.getData();
            this.head.setData(element);
            return replaced;
        }
        for (int i = 0; i < index + 1; i++) {
            if (i == index) {
                replaced = current.getData();
                current.setData(element);
                break;
            }
            if (current.getNext() != null) {
                current = current.getNext();
            }
        }
        return replaced;
    }

    /**
     * A method that returns the element at the specified index.
     *
     * @param index the index of the element to get
     * @return The element at the specified index
     * @throws IndexOutOfBoundsException When the index is out of the linked List bounds
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index is invalid");
        }
        Node<T> current = this.head;
        T data = null;
        for (int i = 0; i < index + 1; i++) {
            if (i == index) {
                data = current.getData();
                break;
            }
            if (current.getNext() != null) {
                current = current.getNext();
            }
        }
        return data;
    }

    /**
     * A method that checks to see if the linked list contains the specified element.
     *
     * @param element the element to search for in the list
     * @return A boolean indicating if the Linked List has the specified element or not.
     * @throws IllegalArgumentException The specified element is null
     */
    @Override
    public boolean contains(T element) throws IllegalArgumentException {
        if (element == null) {
            throw new IllegalArgumentException("Element is null");
        }
        LinkedListIterator<T> iterator = (LinkedListIterator<T>) iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == element) {
                return true;
            }
        }
        return false;
    }

    /**
     * A method that removes all of the elements from the linked list.
     */
    @Override
    public void clear() {
        this.head = null;
        this.size = 0;
    }

    /**
     * A method that checks if the Linked list is empty or not.
     * @return A boolean indicating if the Linked list is empty or not
     */
    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    /**
     * A method that returns the number of elements in the linked list.
     *
     * @return the number of elements in the list
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * A method that makes the linked list iterable.
     *
     * @return A LinkedListIterator object
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<T>(this);
    }
}
