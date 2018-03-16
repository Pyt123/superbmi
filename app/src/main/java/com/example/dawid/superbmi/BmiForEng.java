package com.example.dawid.superbmi;

public class BmiForEng extends Bmi
{
    private static final double kgMul = 0.45359237;
    private static final double mMul = 0.3048;

    public BmiForEng(double weight, double height)
{
    super(weight, height);
}

    public double calculateBmi()
    {
        double inKg = kgMul * weight;
        double inM = mMul * height;
        if(areDataValid(inKg, inM))
        {
            return 4.88 * weight / Math.pow(height, 2);
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }
}
