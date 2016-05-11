package us.narin.dimigoin.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seungwoo on 2015. 12. 24..
 */
public class Meal {

    @SerializedName("no")
    long mealNumber;
    @SerializedName("date")
    String mealDate;
    @SerializedName("breakfast")
    String mealBreakfast;
    @SerializedName("lunch")
    String mealLunch;
    @SerializedName("dinner")
    String mealDinner;
    @SerializedName("snack")
    String mealSnack;
    @SerializedName("added")
    String mealAdded;

    public long getMealNumber() {
        return mealNumber;
    }

    public String getMealAdded() {
        return mealAdded;
    }

    public String getMealBreakfast() {
        return mealBreakfast;
    }

    public String getMealDate() {
        return mealDate;
    }

    public String getMealDinner() {
        return mealDinner;
    }

    public String getMealLunch() {
        return mealLunch;
    }

    public String getMealSnack() {
        return mealSnack;
    }
}
