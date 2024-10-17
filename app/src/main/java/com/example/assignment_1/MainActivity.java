package com.example.assignment_1;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btnFilter, btnOk;
    TextView txtPriceRange;
    SeekBar seekBarPrice;
    CheckBox chk1, chk2, chk3, chk4, chk5;
    CheckBox[] bhkCheckboxes;

    String[] bhkMessages = {
            "1 BHK Flat Booked\n",
            "2 BHK Flat Booked\n",
            "3 BHK Flat Booked\n",
            "4 BHK Flat Booked\n",
            "5 BHK Flat Booked\n"
    };
    int price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListeners();
    }

    private void initViews() {
        txtPriceRange = findViewById(R.id.txtPriceRange);
        seekBarPrice = findViewById(R.id.seekBarPrice);
        btnFilter = findViewById(R.id.btnFilter);
        btnOk = findViewById(R.id.btnOk);

        bhkCheckboxes = new CheckBox[]{
                findViewById(R.id.chk1),
                findViewById(R.id.chk2),
                findViewById(R.id.chk3),
                findViewById(R.id.chk4),
                findViewById(R.id.chk5)
        };

        seekBarPrice.setMax(5);
    }

    private void initListeners() {
        // Initially hide the filter options
        txtPriceRange.setVisibility(View.GONE);
        seekBarPrice.setVisibility(View.GONE);
        btnOk.setVisibility(View.GONE);
            for (CheckBox checkbox : bhkCheckboxes) {
            checkbox.setVisibility(View.GONE);
        }

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtPriceRange.setVisibility(View.VISIBLE);
                seekBarPrice.setVisibility(View.VISIBLE);
                btnOk.setVisibility(View.VISIBLE);
                for (CheckBox checkbox : bhkCheckboxes) {
                    checkbox.setVisibility(View.VISIBLE);
                }
            }
        });

        // Listener for SeekBar to display the selected price range
        seekBarPrice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                // Display price based on SeekBar progress
                price = progress  * 10000; // 1 to 5 corresponds to 10k to 50k
                txtPriceRange.setText("Selected Price Range: " + price);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Button to confirm selection
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selected Flat");

        StringBuilder message = new StringBuilder();
        for (int i = 0; i < bhkCheckboxes.length; i++) {
            if (bhkCheckboxes[i].isChecked()) {
                message.append(bhkMessages[i]);
            }
        }
        message.append("Selected Price Range: ").append(price).append("\n");

        // If no BHK is selected
        if (message.length() == 0) {
            message.append("No flats selected.");
        }

        builder.setMessage(message.toString())
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}