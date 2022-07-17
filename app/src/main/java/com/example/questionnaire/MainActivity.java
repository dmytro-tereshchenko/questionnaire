package com.example.questionnaire;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText name;
    private EditText age;
    private SeekBar salaryBar;
    private final static String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.second_layout);
        salaryBar = findViewById(R.id.salary);
        final TextView salaryMark = findViewById(R.id.salarymark);
        salaryBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                salaryMark.setText(String.valueOf(seekBar.getProgress())+" $");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        final Button submitButton = findViewById(R.id.submitButton);

        final TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence text, int start, int count, int after) {
                int ageValue=0;
                try{
                    ageValue = Integer.parseInt(age.getText().toString());
                } catch (NumberFormatException e) {
                    submitButton.setEnabled(false);
                }
                if(name.getText()!=null && name.getText().length()>3 && ageValue>18){
                    submitButton.setEnabled(true);
                }
                else{
                    submitButton.setEnabled(false);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        name.addTextChangedListener(textWatcher);
        age.addTextChangedListener(textWatcher);
    }

    public void onSubmit(View view) {
        final TextView messageBar = findViewById(R.id.messageBar);
        StringBuilder message = new StringBuilder();
        int ageValue = Integer.parseInt(age.getText().toString());
        int salaryValue = salaryBar.getProgress();
        if(ageValue<21||ageValue>40){
            message.append("You are not the right age. ");
        }
        if(salaryValue>1600){
            message.append("You are too expensive specialist.");
        }
        if(message.length()>0){
            messageBar.setText(message.toString());
            messageBar.setVisibility(View.VISIBLE);
            return;
        }

        int points = 0;
        final RadioButton answer1 = findViewById(R.id.answer1_3);
        final RadioButton answer2 = findViewById(R.id.answer2_1);
        final RadioButton answer3 = findViewById(R.id.answer3_2);
        final RadioButton answer4 = findViewById(R.id.answer4_2);
        final RadioButton answer5 = findViewById(R.id.answer5_3);
        if(answer1.isChecked()){
            points += 2;
        }
        if(answer2.isChecked()){
            points += 2;
        }
        if(answer3.isChecked()){
            points += 2;
        }
        if(answer4.isChecked()){
            points += 2;
        }
        if(answer5.isChecked()){
            points += 2;
        }
        final RadioButton experience = findViewById(R.id.experience);
        final RadioButton command = findViewById(R.id.command);
        final RadioButton trips = findViewById(R.id.trips);
        if(experience.isChecked()){
            points++;
        }
        if(command.isChecked()){
            points++;
        }
        if(trips.isChecked()){
            points++;
        }

        if(points>=10){
            message.append("We hire you. Our contacts: **************");
        }
        else{
            message.append("We'll call you.");
        }
        messageBar.setText(message.toString());
        messageBar.setVisibility(View.VISIBLE);
    }
}