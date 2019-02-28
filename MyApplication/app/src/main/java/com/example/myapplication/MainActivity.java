package com.example.myapplication;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Double> list_latitud = new ArrayList<Double>();
    public static ArrayList<Double> list_longitud = new ArrayList<Double>();
    public static ArrayList<Double> list_uvMax = new ArrayList<Double>();
    public static ArrayList<Double> list_uvMin = new ArrayList<Double>();
    public static ArrayList<Double> list_uvMedio = new ArrayList<Double>();
    public static ArrayList<Double> list_mq7Max = new ArrayList<Double>();
    public static ArrayList<Double> list_mq7Min = new ArrayList<Double>();
    public static ArrayList<Double> list_mq7Medio = new ArrayList<Double>();
    public static ArrayList<Double> list_mq135Max = new ArrayList<Double>();
    public static ArrayList<Double> list_mq135Min = new ArrayList<Double>();
    public static ArrayList<Double> list_mq135Medio = new ArrayList<Double>();
    public static ArrayList<Integer> al = new ArrayList<Integer>();
    public static ArrayList<String> hora = new ArrayList<String>();

    public  static String sensor="";
    public  static String fecha="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button boton = (Button) findViewById(R.id.button3);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevoForm = new Intent(MainActivity.this,Sub_co2.class);
                startActivity(nuevoForm);
            }
        });

        Button boton2 = (Button) findViewById(R.id.button2);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevoForm = new Intent(MainActivity.this,Sub_uv.class);
                startActivity(nuevoForm);
            }
        });

        Button boton3 = (Button) findViewById(R.id.button4);
        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevoForm = new Intent(MainActivity.this,Sub_co.class);
                startActivity(nuevoForm);
            }
        });

        Button boton5 = (Button) findViewById(R.id.button5);
        boton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent nuevoForm = new Intent(MainActivity.this,Sub_co.class);
                //startActivity(nuevoForm);
                Intent nuevoForm = new Intent(MainActivity.this,conexion.class);
                startActivity(nuevoForm);
                TextView p1=(TextView)findViewById(R.id.textView);
                p1.setText(conexion.airegeneral);

                TextView p2=(TextView)findViewById(R.id.textView2);
                p2.setText(conexion.monoxidogeneral);

                TextView p3=(TextView)findViewById(R.id.textView3);
                p3.setText(conexion.radiaciongeneral);

                if (conexion.conexion==1){
                    Toast.makeText(MainActivity.this,"Conectado",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"nel prro",Toast.LENGTH_SHORT).show();
                }



            }
        });

        FloatingActionButton floatingActionButton =findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);

                //Cargar los datos a las listas respectivas
                list_latitud.add(14.604642);
                //14.604642, -90.531120
                list_longitud.add(-90.531120);
                list_uvMax.add(1.0);
                list_uvMin.add(1.0);
                list_uvMedio.add(1.0);
                list_mq7Max.add(80.0);
                list_mq7Min.add(35.0);
                list_mq7Medio.add(41.959839357429722);
                list_mq135Max.add(37.0);
                list_mq135Min.add(25.0);
                list_mq135Medio.add(29.430127288816283);

                //Manera en la cual se pasa la información al activity del Maps
                intent.putExtra("listaLatitud", list_latitud);
                intent.putExtra("listaLongitud", list_longitud);
                intent.putExtra("listauvMax", list_uvMax);
                intent.putExtra("listauvMin", list_uvMin);
                intent.putExtra("listauvMedio", list_uvMedio);
                intent.putExtra("listamq7Max", list_mq7Max);
                intent.putExtra("listamq7Min", list_mq7Min);
                intent.putExtra("listamq7Medio", list_mq7Medio);
                intent.putExtra("listamq135Max", list_mq135Max);
                intent.putExtra("listamq135Min", list_mq135Min);
                intent.putExtra("listamq135Medio", list_mq135Medio);

                startActivity(intent);
            }
        });

        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
    }
}
