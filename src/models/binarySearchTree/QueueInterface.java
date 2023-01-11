package models.binarySearchTree;

public interface QueueInterface<T> {

    public void enqueue(T newEntry);

    public void clear();

    public T dequeue();

    public T getFront();

    public boolean isEmpty();
}
