package models.binarySearchTree;

@SuppressWarnings("unchecked")
public class QueueModel<T> implements QueueInterface<T> {
    private T[] queue;
    // location
    private int frontIndex;
    private int backIndex;
    private static final int DEFAULT_CAPACITY = 50;

    public QueueModel() {
        this(DEFAULT_CAPACITY);
    }

    public QueueModel(int initialCapacity) {
        T[] tempQueue = (T[]) new Object[initialCapacity + 1];
        queue = tempQueue;
        frontIndex = 0;
        backIndex = initialCapacity;
    }

    @Override
    public void enqueue(T newEntry) {
        backIndex = (backIndex + 1) % queue.length;
        queue[backIndex] = newEntry;
    }

    @Override
    public void clear() {
        queue = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public T dequeue() {
        T front = queue[frontIndex];
        queue[frontIndex] = null;
        frontIndex = (frontIndex + 1) % queue.length;
        return front;
    }

    @Override
    public T getFront() {
        return queue[frontIndex];
    }

    @Override
    public boolean isEmpty() {
        return frontIndex == ((backIndex + 1) % queue.length);
    }
}
