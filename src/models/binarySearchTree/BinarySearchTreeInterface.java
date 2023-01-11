package models.binarySearchTree;

public interface BinarySearchTreeInterface<T> {

    public void add(T value);

    public void clear();

    public boolean isEmpty();

    public int getSize();

    public boolean containsNode(T value);

    public void delete(T value);

    public void traverseInOrder(Node<T> node);

    public void traversePreOrder(Node<T> node);

    public void traversePostOrder(Node<T> node);

    public void traverseLevelOrder();

    public void traverseInOrderWithoutRecursion();

    public void traversePreOrderWithoutRecursion();

    public void traversePostOrderWithoutRecursion();

    public T findSmallestValue(Node<T> root);

}
