package models.binarySearchTree;

@SuppressWarnings("unchecked")
public class StackModel<T> implements StackInterface<T> {
    private T[] stack; // Array of stack entries
    private int topIndex; // Index of top entry
    private static final int DEFAULT_CAPACITY = 50;

    public StackModel() {
        this(DEFAULT_CAPACITY);
    }

    public StackModel(int initialCapacity) {
        T[] tempStack = (T[]) new Object[initialCapacity];
        stack = tempStack;
        topIndex = -1;
    }

    @Override
    public void push(T newEntry) {
        stack[topIndex + 1] = newEntry;
        topIndex++;
    }

    @Override
    public T pop() {
        T top = stack[topIndex];
        stack[topIndex] = null;
        topIndex--;
        return top;
    }

    @Override
    public T peek() {
        return stack[topIndex];
    }

    @Override
    public boolean isEmpty() {
        return topIndex < 0;
    }

    public void displayStack() {
        for (T t : stack) {
            if (t != null) {
                System.out.println(t.toString());
            }
        }
    }
}
