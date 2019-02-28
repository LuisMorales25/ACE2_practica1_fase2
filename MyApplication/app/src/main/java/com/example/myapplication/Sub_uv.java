package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Sub_uv extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_uv);

        Button boton = (Button) findViewById(R.id.button12);
        boton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevoForm = new Intent(Sub_uv.this,pie1.class);
                startActivity(nuevoForm);
            }
        });

        Button boton2 = (Button) findViewById(R.id.button13);
        boton2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevoForm = new Intent(Sub_uv.this,lineC.class);
                startActivity(nuevoForm);
            }
        });

        Button boton3 = (Button) findViewById(R.id.button14);
        boton3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevoForm = new Intent(Sub_uv.this,activity2.class);
                startActivity(nuevoForm);
            }
        });

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                 //       .setAction("Action", null).show();
                Toast.makeText(Sub_uv.this,"Conectado",Toast.LENGTH_SHORT).show();
                Intent nuevoForm = new Intent(Sub_uv.this,hora1_UV.class);
                startActivity(nuevoForm);
            }
        });


    }




}
