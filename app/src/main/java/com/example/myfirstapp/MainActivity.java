package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button first, second;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        first = (Button) findViewById(R.id.button2);

        second = (Button)    findViewById(R.id.button3);
     /*   first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(view);
            }
        });
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(view);
            }
        });*/
        first.setOnClickListener(this);
        second.setOnClickListener(this);
    }


    public void sendMessage(View view)
    {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
    public void startService(View view) {
        startService(new Intent(getBaseContext(), MyService.class));

            Configuration config = getResources().getConfiguration();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                LM_Fragment lm_fragment = new LM_Fragment();
                fragmentTransaction.replace(android.R.id.content, lm_fragment);
            }else{
                PM_Fragment pm_fragment = new PM_Fragment();
                fragmentTransaction.replace(android.R.id.content, pm_fragment);
            }
            fragmentTransaction.commit();
        }


    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), MyService.class));
    }

    @Override
    public void onClick(View view) {
        if (view == first){
            startService(view);
        }
        else if (view == second){

            stopService(view);
        }
    }
}
