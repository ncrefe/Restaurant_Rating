package models.binarySearchTree;

import models.*;

@SuppressWarnings("unchecked")
public class BinarySearchTree<T> implements BinarySearchTreeInterface<T>, Comparator<T> {
    private BinarySearchTreeType binarySearchTreeType;
    private int sortWay = 1;
    private Node<T> root;

    public BinarySearchTree(BinarySearchTreeType binarySearchTreeType) {
        this.binarySearchTreeType = binarySearchTreeType;

        if (binarySearchTreeType == BinarySearchTreeType.DeliveryReverse
                || binarySearchTreeType == BinarySearchTreeType.PriceReverse
                || binarySearchTreeType == BinarySearchTreeType.RatingReverse
                || binarySearchTreeType == BinarySearchTreeType.StockReverse)
            sortWay = -1;
    }

    public void add(T value) {
        root = addRecursive(root, value);
    }

    public void clear() {
        root = null;
    }

    public QueueModel<T> getElementsSorted() {
        int size = getSize();
        QueueModel<T> queue = new QueueModel<T>(size);

        StackModel<Node<T>> stack = new StackModel<>();
        Node<T> current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            Node<T> top = stack.pop();
            queue.enqueue(top.value);
            current = top.right;
        }
        return queue;
    }

    private Node<T> addRecursive(Node<T> current, T value) {
        if (current == null) {
            return new Node<T>(value);
        }
        if (compare(value, current.value) == (sortWay * -1)) {
            current.left = addRecursive(current.left, value);
        } else if (compare(value, current.value) == (sortWay)) {
            current.right = addRecursive(current.right, value);
        }
        return current;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int getSize() {
        return getSizeRecursive(root);
    }

    private int getSizeRecursive(Node<T> current) {
        return current == null ? 0 : getSizeRecursive(current.left) + 1 + getSizeRecursive(current.right);
    }

    public boolean containsNode(T value) {
        return containsNodeRecursive(root, value);
    }

    private boolean containsNodeRecursive(Node<T> current, T value) {
        if (current == null) {
            return false;
        }
        if (value == current.value) {
            return true;
        }
        return compare(value, current.value) == (sortWay * -1) ? containsNodeRecursive(current.left, value)
                : containsNodeRecursive(current.right, value);
    }

    public void delete(T value) {
        root = deleteRecursive(root, value);
    }

    private Node<T> deleteRecursive(Node<T> current, T value) {
        if (current == null) {
            return null;
        }
        if (value == current.value) {
            // Case 1: no children
            if (current.left == null && current.right == null) {
                return null;
            }
            // Case 2: only 1 child
            if (current.right == null) {
                return current.left;
            }
            if (current.left == null) {
                return current.right;
            }
            // Case 3: 2 children
            T smallestValue = findSmallestValue(current.right);
            current.value = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        }
        if (compare(value, current.value) == (sortWay * -1)) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }
        current.right = deleteRecursive(current.right, value);
        return current;
    }

    public T findSmallestValue(Node<T> root) {
        return root.right == null ? root.value : findSmallestValue(root.right);
    }

    public void traverseInOrder(Node<T> node) {
        if (node != null) {
            traverseInOrder(node.left);
            visit(node.value);
            traverseInOrder(node.right);
        }
    }

    public void traversePreOrder(Node<T> node) {
        if (node != null) {
            visit(node.value);
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }

    public void traversePostOrder(Node<T> node) {
        if (node != null) {
            traversePostOrder(node.left);
            traversePostOrder(node.right);
            visit(node.value);
        }
    }

    public void traverseLevelOrder() {
        if (root == null) {
            return;
        }
        QueueModel<Node<T>> nodes = new QueueModel<Node<T>>();
        nodes.enqueue(root);
        while (!nodes.isEmpty()) {
            Node<T> node = nodes.dequeue();
            System.out.println(" " + node.value);
            if (node.left != null) {
                nodes.enqueue(node.left);
            }
            if (node.right != null) {
                nodes.enqueue(node.right);
            }
        }
    }

    public void traverseInOrderWithoutRecursion() {
        StackModel<Node<T>> stack = new StackModel<>();
        Node<T> current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            Node<T> top = stack.pop();
            visit(top.value);
            current = top.right;
        }
    }

    public void traversePreOrderWithoutRecursion() {
        StackModel<Node<T>> stack = new StackModel<>();
        Node<T> current = root;
        stack.push(root);
        while (current != null && !stack.isEmpty()) {
            current = stack.pop();
            visit(current.value);
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
    }

    public void traversePostOrderWithoutRecursion() {
        StackModel<Node<T>> stack = new StackModel<>();
        Node<T> prev = root;
        Node<T> current = root;
        stack.push(root);

        while (current != null && !stack.isEmpty()) {
            current = stack.peek();
            boolean hasChild = (current.left != null || current.right != null);
            boolean isPrevLastChild = (prev == current.right || (prev == current.left && current.right == null));

            if (!hasChild || isPrevLastChild) {
                current = stack.pop();
                visit(current.value);
                prev = current;
            } else {
                if (current.right != null) {
                    stack.push(current.right);
                }
                if (current.left != null) {
                    stack.push(current.left);
                }
            }
        }
    }

    private void visit(T value) {
        System.out.println(" " + value);
    }

    public int comparePrice(Food o, Food o2) {
        if (o.getPrice() == o2.getPrice()) {
            return 0;
        } else if (o.getPrice() > o2.getPrice()) {
            return 1;
        } else {
            return -1;
        }
    }

    public int compareStock(Food o, Food o2) {
        if (o.getStock() == o2.getStock()) {
            return 0;
        } else if (o.getStock() > o2.getStock()) {
            return 1;
        } else {
            return -1;
        }
    }

    public int compareDeliveryTime(Restaurant o, Restaurant o2) {
        if (o.getDeliveryTime() == o2.getDeliveryTime()) {
            return 0;
        } else if (o.getDeliveryTime() > o2.getDeliveryTime()) {
            return 1;
        } else {
            return -1;
        }
    }

    public int compareRating(Restaurant o, Restaurant o2) {
        if (o.getRating() == o2.getRating()) {
            return 0;
        } else if (o.getRating() > o2.getRating()) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public int compare(T o, T o2) {
        if (o == null) {
            return 0;
        } else if (o instanceof Restaurant && (
                binarySearchTreeType == BinarySearchTreeType.Price ||
                        binarySearchTreeType == BinarySearchTreeType.PriceReverse ||
                        binarySearchTreeType == BinarySearchTreeType.Stock ||
                        binarySearchTreeType == BinarySearchTreeType.StockReverse
        )) {
            throw new AssertionError("This sorting is not valid for Restaurant : " + this);
        } else if (o instanceof Food && (
                binarySearchTreeType == BinarySearchTreeType.Delivery ||
                        binarySearchTreeType == BinarySearchTreeType.DeliveryReverse ||
                        binarySearchTreeType == BinarySearchTreeType.Rating ||
                        binarySearchTreeType == BinarySearchTreeType.RatingReverse
        )) {
            throw new AssertionError("This sorting is not valid for Food : " + this);
        }

        switch (binarySearchTreeType) {
            case Delivery:
            case DeliveryReverse:
                return compareDeliveryTime((Restaurant) o, (Restaurant) o2);
            case Price:
            case PriceReverse:
                return comparePrice((Food) o, (Food) o2);
            case Rating:
            case RatingReverse:
                return compareRating((Restaurant) o, (Restaurant) o2);
            case Stock:
            case StockReverse:
                return compareStock((Food) o, (Food) o2);
            default:
                return 0;
        }
    }

}
