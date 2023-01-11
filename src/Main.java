import java.io.FileNotFoundException;

import models.*;
import models.binarySearchTree.*;
import service.FileService;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
    /*
    1) Generics, Interfaces: We have equipped each model in our assignment with generic structures. We extracted the
    interfaces of each of the implementation's we used.

    2) Queue ADT, Stack ADT: They were created in line with best-practice codes. Queue and Stack constructs were used
    in iterators without any problems.

    3) Iterators, Comparators: Iterators are shaped as in Best-Practice codes. It was designed based on Queue and
    Stack. They work as generic constructs. We have included two different implementations for each of them, recursive
    and withoutRecursion, in case you want to see them.

    4) Binary Search Trees: Our Bst model consists of a generic structure. In addition to the basic functions, it is
    included in the Comparator interface First of all, we can say that our enum type variable (binarySearchTreeType) is
    the criticial point of our assignment. Thanks to it, we don't have to write different trees for each feature and
    repeat the code. With the type we give when creating the tree, our tree model creates our tree according to the
    required compare method and continues its operations. In our research, we have seen functions that can give elements
    in advanced trees with Traversal-based method, and we do this with  an inorderIterator which returns queue under the
    name GetElements and returning values to us.

    Muhammed efe İncir 270201029
    */

        // Read data from files
        IBag<Restaurant> restaurants = FileService.readRestaurants();
        IBag<Food> foods = FileService.readFoods();

        // Variables for queue operations
        QueueModel<Restaurant> restaurantsQueue;
        QueueModel<Food> foodQueue;

        // ---------------------------------------------------------------- //

        // Create new bst for sorting restaurants by rating but reverse
        BinarySearchTree<Restaurant> bstRestaurants = new BinarySearchTree<Restaurant>(BinarySearchTreeType.RatingReverse);

        // Add objects to bst that readed from file
        for (Restaurant restaurant : restaurants.items) {
            bstRestaurants.add(restaurant);
        }

        // Create new bst for sorting foods by price
        BinarySearchTree<Food> bstFoods = new BinarySearchTree<Food>(BinarySearchTreeType.Price);

        // Add objects to bst that readed from file
        for (Food food : foods.items) {
            bstFoods.add(food);
        }

        // Create new bst for sorting restaurants by delivery time
        BinarySearchTree<Restaurant> bstRestaurantsDelivery = new BinarySearchTree<Restaurant>(
                BinarySearchTreeType.Delivery);

        // Add objects to bst that readed from file
        for (Restaurant restaurant : restaurants.items) {
            bstRestaurantsDelivery.add(restaurant);
        }

        // Create new bst for sorting foods by stock
        BinarySearchTree<Food> bstFoodStock = new BinarySearchTree<Food>(BinarySearchTreeType.Stock);

        // Add objects to bst that readed from file
        for (Food food : foods.items) {
            bstFoodStock.add(food);
        }

        // ---------------------------------------------------------------- //

        System.out.println("----------------------- 1 -----------------------\n");

        // Print restaurants in descending order of rating
        restaurantsQueue = bstRestaurants.getElementsSorted();

        // Print the name of the Pizza restaurant that has the shortest delivery
        while (!restaurantsQueue.isEmpty()) {
            Restaurant tempRestaurant = restaurantsQueue.dequeue();
            System.out.println(fixedLengthString(tempRestaurant.getName(), 30) + "  " + tempRestaurant.getRating());
        }
        System.out.println("");

        // ---------------------------------------------------------------- //

        System.out.println("----------------------- 2 -----------------------\n");

        // Print food in ascending order of price
        foodQueue = bstFoods.getElementsSorted();

        while (!foodQueue.isEmpty()) {
            Food tempFood = foodQueue.dequeue();
            System.out.println(fixedLengthString(tempFood.getName(), 30) + "  "
                    + fixedLengthString(String.format("%.2f", tempFood.getPrice()), 8) + "  " + tempFood.getStock());
        }
        System.out.println("");

        // ---------------------------------------------------------------- //

        System.out.println("----------------------- 3 -----------------------\n");

        restaurantsQueue = bstRestaurantsDelivery.getElementsSorted();

        // Print the name of the Pizza restaurant that has the shortest delivery
        while (!restaurantsQueue.isEmpty()) {
            Restaurant tempRestaurant = restaurantsQueue.dequeue();

            if (tempRestaurant.getCuisine().equals("Pizza")) {
                System.out.println(fixedLengthString(tempRestaurant.getName(), 30) + "  " + tempRestaurant.getDeliveryTime());
                System.out.println("");
                break;
            }
        }

        // ---------------------------------------------------------------- //

        System.out.println("----------------------- 4 -----------------------\n");

        // Print the name of the Coffee with the highest amount of stock.
        foodQueue = bstFoodStock.getElementsSorted();

        // Print the name of the Pizza restaurant that has the shortest delivery
        Food largerFood = new Food();

        while (!foodQueue.isEmpty()) {
            Food tempFood = foodQueue.dequeue();

            if (tempFood.getRestaurant().getCuisine().equals("Coffee")) {
                largerFood = tempFood;
            }
        }

        System.out.println(fixedLengthString(largerFood.getName(), 30) + "  " + largerFood.getStock());
        System.out.println("");

        // ---------------------------------------------------------------- //

        System.out.println("----------------------- 5 -----------------------\n");

        foodQueue = bstFoods.getElementsSorted();

        // Print the name of the Pizza restaurant that has the shortest delivery
        while (!foodQueue.isEmpty()) {
            Food tempFood = foodQueue.dequeue();

            if (tempFood.getPrice() > 80) {
                bstFoods.delete(tempFood);
                System.out.println(fixedLengthString(tempFood.getName(), 30) + "  "
                        + fixedLengthString(String.format("%.2f", tempFood.getPrice()), 8) + "  " + "Removed");
            }
        }
        System.out.println("");

        // ---------------------------------------------------------------- //

        System.out.println("----------------------- 6 -----------------------\n");

        restaurantsQueue = bstRestaurants.getElementsSorted();

        // Print the name of the Pizza restaurant that has the shortest delivery
        while (!restaurantsQueue.isEmpty()) {
            Restaurant tempRestaurant = restaurantsQueue.dequeue();

            if (tempRestaurant.getRating() < 8) {
                bstRestaurants.delete(tempRestaurant);
                System.out.println(fixedLengthString(tempRestaurant.getName(), 30) + "  "
                        + fixedLengthString(String.format("%.2f", tempRestaurant.getRating()), 8) + "  " + "Removed");
            }
        }
        System.out.println("");

        // ---------------------------------------------------------------- //

        System.out.println("----------------------- 7 -----------------------\n");

        foodQueue = bstFoods.getElementsSorted();
        bstFoods.clear();

        // Print the name of the Pizza restaurant that has the shortest delivery
        while (!foodQueue.isEmpty()) {
            Food tempFood = foodQueue.dequeue();

            tempFood.updatePrice(tempFood.getPrice() * 12 / 10);
            bstFoods.add(tempFood);
        }

        System.out.println("Prices in FoodBSTs are updated.");
        System.out.println("");

        // ---------------------------------------------------------------- //

        System.out.println("----------------------- 8 -----------------------\n");

        foodQueue = bstFoods.getElementsSorted();
        bstFoods.clear();

        // Print the name of the Pizza restaurant that has the shortest delivery
        while (!foodQueue.isEmpty()) {
            Food tempFood = foodQueue.dequeue();

            tempFood.updateStock(tempFood.getStock() / 2);
            bstFoods.add(tempFood);
        }

        System.out.println("Stocks in FoodBSTs are updated.");
        System.out.println("");

        // ---------------------------------------------------------------- //

        System.out.println("----------------------- 9 -----------------------\n");

        // Print restaurants in descending order of rating
        restaurantsQueue = bstRestaurants.getElementsSorted();

        // Print the name of the Pizza restaurant that has the shortest delivery
        while (!restaurantsQueue.isEmpty()) {
            Restaurant tempRestaurant = restaurantsQueue.dequeue();
            System.out.println(fixedLengthString(tempRestaurant.getName(), 30) + "\t" + tempRestaurant.getRating());
        }
        System.out.println("");

        // ---------------------------------------------------------------- //

        System.out.println("----------------------- 10 -----------------------\n");

        // Print food in ascending order of price
        foodQueue = bstFoods.getElementsSorted();

        while (!foodQueue.isEmpty()) {
            Food tempFood = foodQueue.dequeue();
            System.out.println(fixedLengthString(tempFood.getName(), 30) + "  "
                    + fixedLengthString(String.format("%.2f", tempFood.getPrice()), 8) + "  " + tempFood.getStock());
        }
        System.out.println("");

    }

    public static String fixedLengthString(String string, int length) {
        return String.format("%-" + length + "." + length + "s", string) + "|";
    }

}
