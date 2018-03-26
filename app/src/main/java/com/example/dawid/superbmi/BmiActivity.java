package com.example.dawid.superbmi;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import java.util.Locale;
import java.util.ResourceBundle;

public class BmiActivity extends AppCompatActivity {
    private static final String BMI_VALUE_KEY = "bmi_value_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        setBackButton();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            double bmiVal = bundle.getDouble(BMI_VALUE_KEY);
            showBmiValue(bmiVal);
            judgeTheUser(bmiVal);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void judgeTheUser(double bmiValue) {
        TextView bmiOpinionText = findViewById(R.id.opinion_text);
        ConstraintLayout background = findViewById(R.id.bmi_layout);
        Bmi.BodyType bodyType = Bmi.getBodyType(bmiValue);
        switch (bodyType) {
            case OK:
                bmiOpinionText.setText(R.string.ok_state);
                background.setBackgroundColor(getResources().getColor(R.color.colorOk));
                break;
            case TOO_FAT:
                bmiOpinionText.setText(R.string.fat_state);
                background.setBackgroundColor(getResources().getColor(R.color.colorFat));
                break;
            case TOO_SLIM:
                bmiOpinionText.setText(R.string.slim_state);
                background.setBackgroundColor(getResources().getColor(R.color.colorSlim));
        }
    }

    private void showBmiValue(double bmiVal) {
        TextView bmiText = findViewById(R.id.bmi_value_text);
        bmiText.setText(String.format(Locale.ENGLISH,getString(R.string.bmi_precision_format), bmiVal));
    }

    private void setBackButton() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    
    public static void start(Context context, double bmiValue) {
        Intent starter = new Intent(context, BmiActivity.class);
        starter.putExtra(BMI_VALUE_KEY, bmiValue);
        context.startActivity(starter);
    }
}
