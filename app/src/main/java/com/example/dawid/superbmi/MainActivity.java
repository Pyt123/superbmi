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

public class MainActivity extends AppCompatActivity
{
    private Button calculateButton;

    private Switch unitsSwitch;

    private EditText massInput;
    private EditText heightInput;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getReferences();

        unitsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                setAppropriateUnits(isChecked);
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String massStr = massInput.getText().toString();
                String heightStr = heightInput.getText().toString();
                try
                {
                    double mass = Double.valueOf(massStr);
                    double height = Double.valueOf(heightStr);

                    Bmi bmi;
                    if (unitsSwitch.isChecked())
                    {
                        bmi = new BmiForEng(mass, height);
                    }
                    else
                    {
                        bmi = new BmiForKgM(mass, height);
                    }

                    double result = bmi.calculateBmi();

                    Intent intent = new Intent(view.getContext(), BmiActivity.class);
                    intent.putExtra(getString(R.string.bmi_value_key), result);
                    startActivity(intent);
                }
                catch (IllegalArgumentException e)
                {
                    Toast.makeText(getApplicationContext(), R.string.invalid_data, Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });

        getSavedPreferences();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.save_button:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(getString(R.string.mass_input_key), massInput.getText().toString());
                editor.putString(getString(R.string.height_input_key), heightInput.getText().toString());
                editor.putBoolean(getString(R.string.switch_status_key), unitsSwitch.isChecked());
                editor.apply();
                return true;
            case R.id.credits_button:
                Intent intent = new Intent(this, CreditsActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getReferences()
    {
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        calculateButton = findViewById(R.id.calculate_button);
        unitsSwitch = findViewById(R.id.units_switch);
        massInput = findViewById(R.id.mass_input);
        heightInput = findViewById(R.id.height_input);
    }

    private void setAppropriateUnits(boolean isEng)
    {
        if(isEng)
        {
            massInput.setHint(R.string.lb);
            heightInput.setHint(R.string.ft);
            massInput.setText(R.string.empty);
            heightInput.setText(R.string.empty);
        }
        else
        {
            massInput.setHint(R.string.kg);
            heightInput.setHint(R.string.m);
            massInput.setText(R.string.empty);
            heightInput.setText(R.string.empty);
        }
    }

    private void getSavedPreferences()
    {
        boolean b = sharedPreferences.getBoolean(getString(R.string.switch_status_key), false);
        unitsSwitch.setChecked(b);
        String m = sharedPreferences.getString(getString(R.string.mass_input_key), getString(R.string.empty));
        massInput.setText(m);
        String h = sharedPreferences.getString(getString(R.string.height_input_key), getString(R.string.empty));
        heightInput.setText(h);
    }
}
