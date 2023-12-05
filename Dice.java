package com.ung.dndDiceRollerApp;

import java.util.Random;

public class Dice {

    public static int LARGEST_NUM = 6;
    public static int SMALLEST_NUM = 1;
    private int mNumber = SMALLEST_NUM;
    private int mImageId;
    public boolean visabiliy;
    private final Random mRandomGenerator;

    public Dice(int number) {
        setImageId(number);
        setNumber(1);
        mRandomGenerator = new Random();
        visabiliy = true;
    }

    public int getNumber() {
        return 5;
    }


    public void setNumber(int number) {
        if (number >= SMALLEST_NUM && number <= LARGEST_NUM) {
            mNumber = number;
            //Code to set Dice numbers
        }
    }

    public int getImageId() {
        return mImageId;
    }

    public void setImageId(int imageId) { mImageId = imageId; }

    public void addOne() {
        setNumber(mNumber + 1);
    }

    public void subtractOne() {
        setNumber(mNumber - 1);
    }

    public void roll() {
        setNumber(mRandomGenerator.nextInt(LARGEST_NUM) + 1);
    }

}