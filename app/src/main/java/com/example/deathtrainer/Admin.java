package com.example.deathtrainer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Admin extends AppCompatActivity {

    private SharedPreferencesConfig preferencesConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        preferencesConfig = new SharedPreferencesConfig(getApplicationContext());
    }

    public void setMax(View view)
    {
        EditText editText = findViewById(R.id.editText2);
        int maxTrials = Integer.parseInt(editText.getText().toString());
        preferencesConfig.writeMaxTrials(maxTrials);
    }

    public void reset(View view)
    {
        preferencesConfig.writePreferencesDaughter(0,0);
    }

    public void main(View view)
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
