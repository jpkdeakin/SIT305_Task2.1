package com.example.week2_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button convertButton;
    EditText valueInput;
    TextView resultsOutput;
    TextView resultUnit;
    Spinner unitTypeSpinner;
    Spinner sourceUnitSpinner;
    Spinner destUnitSpinner;
    ArrayAdapter<String> arrayLengthUnits;
    ArrayAdapter<String> arrayWeightUnits;
    ArrayAdapter<String> arrayTempUnits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Allocating widgets
        convertButton = findViewById(R.id.convert_button);
        valueInput = findViewById(R.id.value_input);
        resultsOutput = findViewById(R.id.results_output);
        resultUnit = findViewById(R.id.result_unit);
        sourceUnitSpinner = findViewById(R.id.source_unit_spinner);
        destUnitSpinner = findViewById(R.id.destination_unit_spinner);
        unitTypeSpinner = findViewById(R.id.unit_type_spinner);

        // Allocating resources for unit arrays
        arrayLengthUnits = new ArrayAdapter<>(
                this,android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.length_units));

        arrayWeightUnits = new ArrayAdapter<>(
                this,android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.weight_units));

        arrayTempUnits = new ArrayAdapter<>(
                this,android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.temp_units));


        unitTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                switch(position) {
                    case 0: // Length unit selected
                        sourceUnitSpinner.setAdapter(arrayLengthUnits);
                        destUnitSpinner.setAdapter(arrayLengthUnits);
                        break;
                    case 1: // Weight units selected
                        sourceUnitSpinner.setAdapter(arrayWeightUnits);
                        destUnitSpinner.setAdapter(arrayWeightUnits);
                        break;
                    case 2: // Temp unites selected
                        sourceUnitSpinner.setAdapter(arrayTempUnits);
                        destUnitSpinner.setAdapter(arrayTempUnits);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing.
            }

        });

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double valueOutputDouble = 0.0;

                // Action determined by unit type selected
                if (!valueInput.getText().toString().isEmpty()) {

                    switch(unitTypeSpinner.getSelectedItemPosition()) {
                        case 0: // Length unit selected
                            valueOutputDouble =
                                    lengthConversion(
                                            sourceUnitSpinner.getSelectedItem().toString(),
                                            destUnitSpinner.getSelectedItem().toString(),
                                            Double.valueOf(valueInput.getText().toString())
                                    );
                            break;
                        case 1: // Weight units selected
                            valueOutputDouble =
                                    weightConversion(
                                            sourceUnitSpinner.getSelectedItem().toString(),
                                            destUnitSpinner.getSelectedItem().toString(),
                                            Double.valueOf(valueInput.getText().toString())
                                    );
                            break;
                        case 2: // Temp unites selected
                            valueOutputDouble =
                                    tempConversion(
                                            sourceUnitSpinner.getSelectedItem().toString(),
                                            destUnitSpinner.getSelectedItem().toString(),
                                            Double.valueOf(valueInput.getText().toString())
                                    );
                            break;
                        default:
                            break;
                    }

                    resultsOutput.setText(String.format(Locale.ENGLISH,"%.2f", valueOutputDouble));
                    resultUnit.setText(destUnitSpinner.getSelectedItem().toString());

                } else {

                    resultsOutput.setText(String.format(Locale.ENGLISH,"%.2f", 0.0));
                    resultUnit.setText(destUnitSpinner.getSelectedItem().toString());

                }
            }
        });
    }

    static Double lengthConversion(String source, String dest, Double value) {
        // Convert source to centimeters
        switch(source) {
            case "Inches":
                value = value * 2.54;
                break;
            case "Feet":
                value = value * 30.48;
                break;
            case "Yards":
                value = value * 91.44;
                break;
            case "Miles":
                value = value * 160934;
                break;
            case "Meters":
                value = value * 100;
            case "Kilometers":
                value = value * 100000;
                break;
            default:
                break;
        }

        // Convert centimeters to destination unit
        switch(dest) {
            case "Inches":
                value = value / 2.54;
                break;
            case "Feet":
                value = value / 30.48;
                break;
            case "Yards":
                value = value / 91.44;
                break;
            case "Miles":
                value = value / 160934;
                break;
            case "Meters":
                value = value / 100;
            case "Kilometers":
                value = value / 100000;
                break;
            default:
                break;
        }

        return value;
    }

    static Double weightConversion(String source, String dest, Double value) {
        // Convert to grams
        switch(source) {
            case "Ounce":
                value = value * 28.3495;
                break;
            case "Pound":
                value = value * 453.592;
                break;
            case "Ton":
                value = value * 907185;
                break;
            case "Kilogram":
                value = value * 1000;
                break;
            case "Tonne":
                value = value * 1000000;
                break;
            default:
                break;
        }

        // Convert grams to destination unit
        switch(dest) {
            case "Ounce":
                value = value / 28.3495;
                break;
            case "Pound":
                value = value / 453.592;
                break;
            case "Ton":
                value = value / 907185;
                break;
            case "Kilogram":
                value = value / 1000;
                break;
            case "Tonne":
                value = value / 1000000;
                break;
            default:
                break;
        }

        return value;
    }

    static Double tempConversion(String source, String dest, Double value) {
        // Convert to Celsius
        switch(source) {
            case "Fahrenheit":
                value = (value - 32) / 1.8;
                break;
            case "Kelvin":
                value = value - 273.15;
                break;
            default:
                break;
        }

        // Convert Celsius to destination unit
        switch(dest) {
            case "Fahrenheit":
                value = (value * 1.8) + 32;
                break;
            case "Kelvin":
                value = value + 273.15;
                break;
            default:
                break;
        }

        return value;
    }
}

