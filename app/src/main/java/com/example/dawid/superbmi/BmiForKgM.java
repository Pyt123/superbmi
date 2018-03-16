package com.example.dawid.superbmi;

public class BmiForKgM extends Bmi
{
    public BmiForKgM(double weight, double height)
    {
        super(weight, height);
    }

    public double calculateBmi()
    {
        if(areDataValid(weight, height))
        {
            return weight / Math.pow(height, 2);
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }
}
