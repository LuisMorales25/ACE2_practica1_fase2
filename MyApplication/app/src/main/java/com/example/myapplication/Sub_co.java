package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Sub_co extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_co);


        Button boton = (Button) findViewById(R.id.button7);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevoForm = new Intent(Sub_co.this,pie_co.class);
                startActivity(nuevoForm);
            }
        });

       Button boton2 = (Button) findViewById(R.id.button8);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevoForm = new Intent(Sub_co.this,lineC_co.class);
                startActivity(nuevoForm);
            }
        });

        Button boton3 = (Button) findViewById(R.id.button99);
        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevoForm = new Intent(Sub_co.this,activity2_co.class);
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
                Toast.makeText(Sub_co.this,"Conectado",Toast.LENGTH_SHORT).show();
                Intent nuevoForm = new Intent(Sub_co.this,hora_co.class);
                startActivity(nuevoForm);
            }
        });
    }

}
