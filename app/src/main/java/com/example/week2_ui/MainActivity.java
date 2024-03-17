package com.example.week2_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button convertButton;
    EditText valueInput;
    TextView resultsOutput;

    Spinner sourceUnitSpinner;
    Spinner destUnitSpinner;



    String mystring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        convertButton = findViewById(R.id.convert_button);
        valueInput = findViewById(R.id.value_input);
        resultsOutput = findViewById(R.id.results_output);
        sourceUnitSpinner = findViewById(R.id.source_unit_spinner);
        destUnitSpinner = findViewById(R.id.destination_unit_spinner);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double valueOutputDouble;

                if (valueInput.getText().toString().length() != 0) {
                    valueOutputDouble =
                            lengthConversion(
                                    sourceUnitSpinner.getSelectedItem().toString(),
                                    destUnitSpinner.getSelectedItem().toString(),
                                    Double.valueOf(valueInput.getText().toString())
                            );

                    resultsOutput.setText(valueOutputDouble.toString());
                } else {
                    resultsOutput.setText("0");
                }
            }
        });
    }

    static Double lengthConversion(String source, String dest, Double value) {
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
            case "Centimeters":
                break;
            case "Meters":
                value = value * 100;
            case "Kilometers":
                value = value * 100000;
                break;
            default:
                break;
        }

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
            case "Centimeters":
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
}

