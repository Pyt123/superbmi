package com.example.dawid.superbmi;

public abstract class Bmi
{
    protected double weight;
    protected double height;

    private static final double minKg = 10;
    private static final double maxKg = 10000;
    private static final double minM = 0.3;
    private static final double maxM = 5;

    public Bmi(double weight, double height)
    {
        this.weight = weight;
        this.height = height;
    }

    public abstract double calculateBmi() throws IllegalArgumentException;

    protected boolean areDataValid(double kg, double m)
    {
        return kg >= minKg && kg <= maxKg && m >= minM && m <= maxM;
    }
}
