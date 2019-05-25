package com.example.deathtrainer;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesConfig {
    private SharedPreferences sharedPreferences;
    private Context context;

    public SharedPreferencesConfig(Context context)
    {
        this.context=context;
        sharedPreferences = context.getSharedPreferences("com.example.deathtrainer_preferences",Context.MODE_PRIVATE);
    }

    public void writePreferencesMain(int number)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("number",number);
        editor.commit();
    }

    public void writePreferencesDaughter(int success,int failure)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("success",success);
        editor.putInt("failure",failure);
        editor.commit();
    }

    public void writeMaxTrials(int maxTrials)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("maxTrials",maxTrials);
        editor.commit();
    }

    public int readNumber()
    {
        int n = sharedPreferences.getInt("number",0);
        return n;
    }

    public int readSuccess()
    {
        int n = sharedPreferences.getInt("success", 0);
        return n;
    }

    public int readMaxTrials()
    {
        int n = sharedPreferences.getInt("maxTrials",7);
        return n;
    }

    public int readFailure()
    {
        int n = sharedPreferences.getInt("failure",0);
        return n;
    }

}


