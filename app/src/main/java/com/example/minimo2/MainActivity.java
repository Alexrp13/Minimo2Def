package com.example.minimo2;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.*;


public class MainActivity extends AppCompatActivity {

    public static final String USUARIO = "com.example.minimo2.USUARIO";
    private Button button;
    private Button button2;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.PerfilBT);
        button2 = (Button) findViewById(R.id.InsigniasBT);
        editText = (EditText) findViewById(R.id.usuario);
        //CustomLoading custom  = new CustomLoading(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                intent.putExtra(USUARIO, name);

                custom.showDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        custom.cancelDialog();
                        startActivity(intent);
                    }
                }, 2000);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                custom.showDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        custom.cancelDialog();
                        startActivity(intent);
                    }
                }, 2000);
            }
        });
    }
}
