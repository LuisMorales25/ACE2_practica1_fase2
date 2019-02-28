package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Sub_co2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_co4);
        Button boton = (Button) findViewById(R.id.button9);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevoForm = new Intent(Sub_co2.this,pie_co2.class);
                startActivity(nuevoForm);
            }
        });

        Button boton2 = (Button) findViewById(R.id.button10);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevoForm = new Intent(Sub_co2.this,lineC_co2.class);
                startActivity(nuevoForm);
            }
        });

        Button boton3 = (Button) findViewById(R.id.button11);
        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevoForm = new Intent(Sub_co2.this,activity2_co2.class);
                startActivity(nuevoForm);
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                  //      .setAction("Action", null).show();
                Intent nuevoForm = new Intent(Sub_co2.this,hora_co2.class);
                startActivity(nuevoForm);

            }
        });
    }

}
