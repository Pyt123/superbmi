package com.example.dawid.superbmi;

public abstract class Bmi {

    private static final double MAX_OK_BMI = 25.0;
    private static final double MIN_OK_BMI = 18.5;
    private static final double MIN_VALID_KG = 10;
    private static final double MAX_VALID_KG = 10000;
    private static final double MIN_VALID_M = 0.3;
    private static final double MAX_VALID_M = 5;

    public enum BodyType {
        UNKNOWN, OK, TOO_FAT, TOO_SLIM
    }

    protected double weight;
    protected double height;

    public Bmi(double weight, double height) {
        this.weight = weight;
        this.height = height;
    }

    public abstract double calculateBmi() throws IllegalArgumentException;

    protected boolean areDataValid(double kg, double m) {
        return kg >= MIN_VALID_KG && kg <= MAX_VALID_KG && m >= MIN_VALID_M && m <= MAX_VALID_M;
    }

    public static BodyType getBodyType(double bmiValue) {
        BodyType bodyType = BodyType.UNKNOWN;
        if(bmiValue < MIN_OK_BMI) {
            bodyType = BodyType.TOO_SLIM;
        }
        else if (bmiValue > MAX_OK_BMI) {
            bodyType = BodyType.TOO_FAT;
        }
        else {
            bodyType = BodyType.OK;
        }
        return bodyType;
    }
}
