package com.example.deathtrainer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SharedPreferencesConfig preferencesConfig;
    int success,failure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferencesConfig = new SharedPreferencesConfig(getApplicationContext());

        success = preferencesConfig.readSuccess();
        failure = preferencesConfig.readFailure();

        colourBg();

        TextView scoring = findViewById(R.id.scoring);
        scoring.setText("Scorecard\n" +"Success "+success+"\n Failure "+failure);
    }
    public void colourBg()
    {
        RelativeLayout relativeLayout = findViewById(R.id.relativeLayoutMain);
        if(success>failure) relativeLayout.setBackgroundColor(Color.parseColor("#C5CAE9"));
        else if(failure>success) relativeLayout.setBackgroundColor(Color.parseColor("#E0F7FA"));
        else relativeLayout.setBackgroundColor(Color.parseColor("#FFCC80"));
    }
    public void submit (View view){
        int number;
        EditText editText = findViewById(R.id.editText);
        TextView invalid = findViewById(R.id.invalid);
        colourBg();
        number = Integer.parseInt("0" + editText.getText().toString());
        if(number<1||number>100)
            invalid.setText("Please Enter number between 1 and 100");
        else
        {
            invalid.setText("");
            Intent out = new Intent(this,Daughter.class);
            preferencesConfig.writePreferencesMain(number);
            preferencesConfig.writePreferencesDaughter(success,failure);
            startActivity(out);
            finish();
        }
    }

    public void adminPanel(View view)
    {
        Intent admin = new Intent(this,Admin.class);
        startActivity(admin);
        finish();
    }
}
