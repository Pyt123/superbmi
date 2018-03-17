package com.example.dawid.superbmi;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import java.util.Locale;

public class BmiActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        setBackButton();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            double bmiVal = bundle.getDouble(getString(R.string.bmi_value_key));
            showBmiValue(bmiVal);
            judgeTheUser(bmiVal);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void judgeTheUser(double bmiValue)
    {
        TextView bmiOpinionText = findViewById(R.id.opinion_text);
        ConstraintLayout background = findViewById(R.id.bmi_layout);
        if(bmiValue > 25)
        {
            bmiOpinionText.setText(R.string.youre_fat);
            background.setBackgroundColor(getResources().getColor(R.color.colorFat));
        }
        else if(bmiValue < 18)
        {
            bmiOpinionText.setText(R.string.youre_too_slim);
            background.setBackgroundColor(getResources().getColor(R.color.colorSlim));
        }
        else
        {
            bmiOpinionText.setText(R.string.youre_ok);
            background.setBackgroundColor(getResources().getColor(R.color.colorOk));
        }
    }

    private void showBmiValue(double bmiVal)
    {
        TextView bmiText = findViewById(R.id.bmi_value_text);
        bmiText.setText(String.format(Locale.ENGLISH,getString(R.string.precision_format), bmiVal));
    }

    private void setBackButton()
    {
        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
