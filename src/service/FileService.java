package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import models.*;

public class FileService {
    public static IBag<Restaurant> readRestaurants() throws FileNotFoundException {
        Scanner inFile = new Scanner(new File("src/assets/CENG112_HW4.csv"));
        String line = null;
        Restaurant[] restaurantsList = new Restaurant[10];
        int index = 0;

        while (inFile.hasNextLine()) {
            boolean isExist = false;
            line = inFile.nextLine();
            String[] params = line.split(", ");

            for (Restaurant value : restaurantsList) {
                if (value != null) {
                    if (params[3].equals(value.getName())) {
                        isExist = true;
                        break;
                    }
                } else {
                    break;
                }
            }

            if (!isExist) {
                Restaurant restaurant = new Restaurant(params[3], Double.parseDouble(params[4]), params[5],
                        Integer.parseInt(params[6]));

                restaurantsList[index++] = restaurant;
            }
        }

        IBag<Restaurant> restaurantList = new IBag<Restaurant>();
        restaurantList.setItems(restaurantsList);
        return restaurantList;
    }

    public static IBag<Food> readFoods() throws FileNotFoundException {
        IBag<Restaurant> restaurants = readRestaurants();

        Scanner inFile = new Scanner(new File("src/assets/CENG112_HW4.csv"));
        String line = null;
        Food[] foodList = new Food[20];
        int index = 0;

        while (inFile.hasNextLine()) {
            boolean alreadyExist = false;
            line = inFile.nextLine();

            String[] params = line.split(", ");

            for (Food value : foodList) {
                if (value != null) {
                    if (params[0].equals(value.getName())) {
                        alreadyExist = true;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (!alreadyExist) {
                for (Restaurant restaurant : restaurants.getItems()) {
                    if (restaurant.getName().equals(params[3])) {
                        foodList[index++] = new Food(params[0], Double.parseDouble(params[1]), Integer.parseInt(params[2]),
                                restaurant);
                        break;
                    }
                }

            }
        }
        IBag<Food> foodBagList = new IBag<Food>();
        foodBagList.setItems(foodList);
        return foodBagList;
    }
}
