package com.example.deathtrainer;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v4.view.TintableBackgroundView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Daughter extends AppCompatActivity {
    private SharedPreferencesConfig preferencesConfig;
    int number,guess,tries=1,maxTrials,success,failure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daughter);

        preferencesConfig = new SharedPreferencesConfig(getApplicationContext());

        number = preferencesConfig.readNumber();
        success = preferencesConfig.readSuccess();
        failure = preferencesConfig.readFailure();
        maxTrials=preferencesConfig.readMaxTrials();
    }

    public void colourBg(int diff,int div)  //div takes values of 100-number or number
    {
        RelativeLayout relativeLayout = findViewById(R.id.relativeLayout);
        int bias = (255*7/div)*diff/7;     //the 7 is present to make change more obvious
        relativeLayout.setBackgroundColor(Color.parseColor("#"+String.format("%02x", 0+bias)+String.format("%02x", 255-bias)+"00"));

    }

    public void checker(View view)
    {
        EditText editText = findViewById(R.id.input);
        TextView textView = findViewById(R.id.result);
        TextView inval = findViewById(R.id.invalDaughter);
        TextView triesView = findViewById(R.id.noOfTries);

        triesView.setText("Max Tries "+ maxTrials +"\nTry number "+tries);
        guess = Integer.parseInt("0"+editText.getText().toString());
        if(guess<1||guess>100) {
            inval.setText("Please Enter number between 1 and 100");
            textView.setText("");
        }

        else
        {
            inval.setText("");
            if(guess>number) {
                textView.setText("Guess is higher");
                colourBg(guess-number,100-number);
                if(tries==maxTrials)
                {
                    failure++;
                    exit();
                }
            }
            else if (guess<number)
            {
                textView.setText("Guess is lower");
                colourBg(number - guess,number);
                if(tries==maxTrials)
                {
                    failure++;
                    exit();
                }
            }
            else {
                textView.setText("Right Guess!");
                success++;
                exit();
            }
            tries++;
        }
    }

    public void exit()
    {
        Intent out = new Intent(this,MainActivity.class);
        preferencesConfig.writePreferencesDaughter(success,failure);
        startActivity(out);
        finish();
    }
}
