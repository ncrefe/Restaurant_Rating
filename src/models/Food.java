package models;

public class Food implements Orderable {
    private String name;
    private double price;
    private int stock;
    private Restaurant restaurant;

    public Food(String name, double price, int stock, Restaurant restaurant) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.restaurant = restaurant;
    }

    public Food() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    @Override
    public String toString() {
        return "Food " + "name:'" + name + '\'' + " ,price:" + price + " ,stock:" + stock + " ," + restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void updatePrice(double price) {
        this.price = price;
    }

    @Override
    public void updateStock(int stock) {
        this.stock = stock;
    }
}
