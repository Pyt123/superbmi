package com.example.dawid.superbmi;

public class BmiForEng extends Bmi {
    private static final double TO_KG_MULTIPLIER = 0.45359237;
    private static final double TO_M_MULTIPLIER = 0.3048;
    private static final double BMI_MULTIPLIER_FOR_ENG_UNITS = 4.88;

    public BmiForEng(double weight, double height) {
        super(weight, height);
    }

    public double calculateBmi() {
        double inKg = TO_KG_MULTIPLIER * weight;
        double inM = TO_M_MULTIPLIER * height;
        if(areDataValid(inKg, inM)) {
            return BMI_MULTIPLIER_FOR_ENG_UNITS * weight / Math.pow(height, 2);
        }
        else {
            throw new IllegalArgumentException();
        }
    }
}
