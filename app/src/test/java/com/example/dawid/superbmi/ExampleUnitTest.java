package com.example.dawid.superbmi;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest
{
    @Test
    public void for_valid_data_bmi_kgM_return_value1()
    {
        Bmi bmiCounter = new BmiForKgM(60, 1.70);
        double bmi = bmiCounter.calculateBmi();

        assertEquals(20.761, bmi, 0.001);
    }

    @Test
    public void for_valid_data_bmi_kgM_return_value2()
    {
        Bmi bmiCounter = new BmiForKgM(120, 2);
        double bmi = bmiCounter.calculateBmi();

        assertEquals(30, bmi, 0.001);
    }

    @Test
    public void for_valid_data_bmi_eng_return_value1()
    {
        Bmi bmiCounter = new BmiForEng(120, 5);
        double bmi = bmiCounter.calculateBmi();

        assertEquals(23.424, bmi, 0.001);
    }

    @Test
    public void for_valid_data_bmi_eng_return_value2()
    {
        Bmi bmiCounter = new BmiForEng(200.12, 5.45);
        double bmi = bmiCounter.calculateBmi();

        assertEquals(32.879, bmi, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void for_zero_bmi_kgM_throw_exception()
    {
        Bmi bmiCounter = new BmiForKgM(0, 0);
        double bmi = bmiCounter.calculateBmi();
    }

    @Test(expected = IllegalArgumentException.class)
    public void for_zero_bmi_eng_throw_exception()
    {
        Bmi bmiCounter = new BmiForEng(0, 0);
        double bmi = bmiCounter.calculateBmi();
    }

    @Test(expected = IllegalArgumentException.class)
    public void for_big_arg1_eng_throw_exception()
    {
        Bmi bmiCounter = new BmiForEng(162421.13, 5.9);
        double bmi = bmiCounter.calculateBmi();
    }

    @Test(expected = IllegalArgumentException.class)
    public void for_big_arg2_eng_throw_exception()
    {
        Bmi bmiCounter = new BmiForEng(160, 42);
        double bmi = bmiCounter.calculateBmi();
    }

    @Test(expected = IllegalArgumentException.class)
    public void for_big_arg1_kgM_throw_exception()
    {
        Bmi bmiCounter = new BmiForKgM(75423.1, 1.8);
        double bmi = bmiCounter.calculateBmi();
    }

    @Test(expected = IllegalArgumentException.class)
    public void for_big_arg2_kgM_throw_exception()
    {
        Bmi bmiCounter = new BmiForKgM(80, 12.1);
        double bmi = bmiCounter.calculateBmi();
    }
}