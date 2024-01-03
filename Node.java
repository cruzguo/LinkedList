/**
 * @author Cruz Guo
 * @version 1.0.0
 * @param <T> Represents any data type that will work with this generic class
 */
public class Node<T> {
    private T data;
    private Node<T> next;

    /**
     * A constructor that sets the node's data and next pointer.
     *
     * @param data The data the node stores
     * @param next The reference this node is pointing to
     */
    public Node(T data, Node<T> next) {
        if (data == null) {
            throw new IllegalArgumentException("Data can not be null");
        } else {
            this.data = data;
        }
        this.next = next;
    }

    /**
     * A method that sets the node's data and defaults the node's next pointer to null.
     *
     * @param data The data the node stores
     */
    public Node(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data can not be null");
        } else {
            this.data = data;
        }
        this.next = null;
    }

    /**
     * A method that retrieves the node's data.
     *
     * @return The node's data
     */
    public T getData() {
        return this.data;
    }

    /**
     * A method that retrieves the node's pointer reference to the next node.
     *
     * @return The node's pointer reference
     */
    public Node<T> getNext() {
        return this.next;
    }

    /**
     * A method that sets the Node's data.
     *
     * @param data The data we want to set the Node to
     */
    public void setData(T data) {
        if (data != null) {
            this.data = data;
        } else {
            throw new IllegalArgumentException("Data is null");
        }
    }

    /**
     * A method that sets the Node's pointer reference.
     *
     * @param next The Node's pointer reference
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }
}
