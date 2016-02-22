package us.narin.dimigoin.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seungwoo on 2015. 12. 24..
 */
public class Meal {

    List<String> mealMorning = new ArrayList<>();
    List<String> mealLunch = new ArrayList<>();
    List<String> mealDinner = new ArrayList<>();
    List<String> mealSnack = new ArrayList<>();

    public Meal(List<String> mealMorning, List<String>mealLunch, List<String> mealDinner, List<String> mealSnack){
        this.mealMorning = mealMorning;
        this.mealLunch = mealLunch;
        this.mealDinner = mealDinner;
        this.mealSnack = mealSnack;
    }

    public List<String> getMealMorning() {
        return mealMorning;
    }

    public List<String> getMealLunch() {
        return mealLunch;
    }

    public List<String> getMealDinner() {
        return mealDinner;
    }

    public List<String> getMealSnack() {
        return mealSnack;
    }
}
