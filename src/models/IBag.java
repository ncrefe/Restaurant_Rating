package models;

public class IBag<T> {
    public T[] items;

    public void printBagItems() {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                System.out.println((i + 1) + ")" + items[i]);
            }
        }
    }

    public T ReturnNameByIndex(int index) {
        return items[index - 1];
    }

    public T[] getItems() {
        return items;
    }

    public void setItems(T[] items) {
        this.items = items;
    }
}