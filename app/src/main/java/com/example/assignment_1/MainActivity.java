package com.example.assignment_1;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btnFilter;
    CheckBox[] bhkCheckboxes;

    String[] bhkMessages = {
            "1 BHK Flat Booked\n",
            "2 BHK Flat Booked\n",
            "3 BHK Flat Booked\n",
            "4 BHK Flat Booked\n",
            "5 BHK Flat Booked\n"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListeners();
    }

    private void initViews() {
        btnFilter = findViewById(R.id.btnFilter);
    }

    private void initListeners() {
        btnFilter.setOnClickListener(view -> showCustomDialog());
    }

    private void showCustomDialog() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog);

        SeekBar priceSeekBar = dialog.findViewById(R.id.seekBarPrice);
        TextView priceRange = dialog.findViewById(R.id.txtPriceRange);
        Button buttonOk = dialog.findViewById(R.id.btnOk);

        // Initialize checkboxes
        bhkCheckboxes = new CheckBox[]{
                dialog.findViewById(R.id.chk1),
                dialog.findViewById(R.id.chk2),
                dialog.findViewById(R.id.chk3),
                dialog.findViewById(R.id.chk4),
                dialog.findViewById(R.id.chk5)
        };

        priceSeekBar.setMax(5);
        priceSeekBar.setProgress(0);
        priceRange.setText("Selected Price Range: 0");


        // listeners for SeekBar and OK button
        SeekBarListener(dialog, priceSeekBar, priceRange, buttonOk);


        dialog.show();
    }
    private void SeekBarListener(Dialog dialog, SeekBar priceSeekBar, TextView priceRange, Button buttonOk) {
        priceSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                priceRange.setText("Selected Price Range: " + (progress * 10000));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        buttonOk.setOnClickListener(view -> {
            StringBuilder message = new StringBuilder();
            for (int i = 0; i < bhkCheckboxes.length; i++) {
                if (bhkCheckboxes[i].isChecked()) {
                    message.append(bhkMessages[i]);
                    // Set the SeekBar progress and price range based on the selected checkbox
                    switch (i) {
                        case 0: // 1 BHK
                            priceSeekBar.setProgress(1);
                            break;
                        case 1: // 2 BHK
                            priceSeekBar.setProgress(2);
                            break;
                        case 2: // 3 BHK
                            priceSeekBar.setProgress(3);
                            break;
                        case 3: // 4 BHK
                            priceSeekBar.setProgress(4);
                            break;
                        case 4: // 5 BHK
                            priceSeekBar.setProgress(5);
                            break;
                    }
                }
            }
            message.append("Selected Price Range: ").append(priceSeekBar.getProgress() * 10000).append("\n");

            if (message.length() == 0) {
                message.append("No flats selected.");
            }

            // Show the dialog with selected options
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Selected Flat")
                    .setMessage(message.toString())
                    .setPositiveButton("OK", (dialog1, id) -> dialog.dismiss())
                    .create()
                    .show();
        });
    }
}






































