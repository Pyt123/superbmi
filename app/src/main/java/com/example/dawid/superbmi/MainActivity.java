package com.example.dawid.superbmi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    private Button calculateButton;
    private Switch toEngUnitsSwitch;
    private EditText massInput;
    private EditText heightInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        getUiReferences();
        setUiListeners();
        getDataFromSharedPrefs();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_button:
                saveDataToSharedPrefs();
                Toast.makeText(getApplicationContext(), R.string.data_saved, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.credits_button:
                Intent intent = new Intent(this, CreditsActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getUiReferences() {
        calculateButton = findViewById(R.id.calculate_button);
        toEngUnitsSwitch = findViewById(R.id.units_switch);
        massInput = findViewById(R.id.mass_input);
        heightInput = findViewById(R.id.height_input);
    }

    private void handleUnitsChange(boolean isEng) {
        if(isEng) {
            massInput.setHint(R.string.lb);
            heightInput.setHint(R.string.ft);
        }
        else {
            massInput.setHint(R.string.kg);
            heightInput.setHint(R.string.m);
        }
        massInput.setText(R.string.empty);
        heightInput.setText(R.string.empty);
    }

    private void getDataFromSharedPrefs() {
        boolean isEng = sharedPreferences.getBoolean(getString(R.string.switch_status_key), false);
        toEngUnitsSwitch.setChecked(isEng);
        String mass = sharedPreferences.getString(getString(R.string.mass_input_key), getString(R.string.empty));
        massInput.setText(mass);
        String height = sharedPreferences.getString(getString(R.string.height_input_key), getString(R.string.empty));
        heightInput.setText(height);
    }

    private void saveDataToSharedPrefs() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.mass_input_key), massInput.getText().toString());
        editor.putString(getString(R.string.height_input_key), heightInput.getText().toString());
        editor.putBoolean(getString(R.string.switch_status_key), toEngUnitsSwitch.isChecked());
        editor.apply();
    }

    private void setUiListeners() {
        toEngUnitsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleUnitsChange(isChecked);
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                try {
                    double result = calculateBmiFromInput();
                    showCalculatedValue(result);
                } catch (IllegalArgumentException e)
                {
                    Toast.makeText(getApplicationContext(), R.string.invalid_data, Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });
    }

    private double calculateBmiFromInput() throws IllegalArgumentException {
        double mass = Double.valueOf(massInput.getText().toString());
        double height = Double.valueOf(heightInput.getText().toString());
        Bmi bmi;
        if (toEngUnitsSwitch.isChecked()) {
            bmi = new BmiForEng(mass, height);
        }
        else {
            bmi = new BmiForKgM(mass, height);
        }
        return bmi.calculateBmi();
    }

    private void showCalculatedValue(double result) {
        Intent intent = new Intent(this, BmiActivity.class);
        intent.putExtra(getString(R.string.bmi_value_key), result);
        startActivity(intent);
    }
}
