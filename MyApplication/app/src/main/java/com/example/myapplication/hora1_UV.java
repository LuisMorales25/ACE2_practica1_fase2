package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class hora1_UV extends AppCompatActivity /*implements AdapterView.OnItemSelectedListener*/ {

    private static final String TAG = "lineC";

    private LineChart mChart;
    private String[]gases=conexion.fechas;//new String[]{"Co","Co2","UV","pedo","humo","comida"};
    private  int[] datos=conexion.radiacionmax;//new double[]{60.2,40.2,55.5,70,75.8,65};
    private String[]letra=conexion.fechas;




    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hora1__uv);

       /* Button re = (Button) findViewById(R.id.btn1);
        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent nuevoForm = new Intent(hora1_UV.this,activity2_co.class);
                //startActivity(nuevoForm);
                datos=conexion.radiacionmin;
                creatChart();
            }
        });*/
       /* al.add(10);
        al.add(18);
        al.add(9);
        al.add(10);
        al.add(15);
        al.add(2);
        hora.add("dia1");
        hora.add("dia2");
        hora.add("dia3");
        hora.add("dia4");
        hora.add("dia5");
        hora.add("dia6");*/
        Spinner spinner = (Spinner)findViewById(R.id.spinner1);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, letra));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
            {
                Toast.makeText(adapterView.getContext(),
                        (String) adapterView.getItemAtPosition(pos), Toast.LENGTH_SHORT).show();
                String op=(String) adapterView.getItemAtPosition(pos);
                MainActivity.sensor="Radiacion";
                MainActivity.fecha=op;

                MainActivity.al.clear();
                MainActivity.hora.clear();

                conexion.DameDia(MainActivity.sensor,MainActivity.fecha);



                /*
                if (op.equalsIgnoreCase("02-27")){
                    datos=conexion.radiacionmin;
                }else if (op.equalsIgnoreCase("02-26")){
                    datos=conexion.radiacionmax;
                }*/

                creatChart();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {    }
        });

    }

    public  void creatChart(){
        mChart=(LineChart)findViewById(R.id.LineChart);
        // mChart.setOnChartGestureListener(lineC.this);
        //mChart.setOnChartValueSelectedListener(lineC.this);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);



        ArrayList<Entry> yValue=new ArrayList<>();
        for(int i=0; i<MainActivity.al.size();i++){
            yValue.add(new Entry(i,MainActivity.al.get(i)));
        }


       /* for (int i=0;i<datos.length;i++){
            yValue.add(new Entry(i,(float)datos[i]));
        }*//**
         yValue.add(new Entry(0,60f));
         yValue.add(new Entry(1,50f));
         yValue.add(new Entry(2,70f));
         yValue.add(new Entry(3,80f));
         yValue.add(new Entry(4,90f));
         yValue.add(new Entry(5,65f));*/
        LineDataSet set1=new LineDataSet(yValue,"Dia 1      Dia2                Dia3                    Dia4                    Dia5                    Dia6");
        set1.setFillAlpha(110);
        set1.setColor(Color.RED);
        set1.setLineWidth(2f);
        set1.setValueTextSize(10);
        set1.setFillColor(Color.BLUE);
        set1.setDrawFilled(true);
        mChart.animateY(2500);
        axisRight(mChart.getAxisRight());
        axisRight(mChart.getAxisLeft());
        topAxis(mChart.getXAxis());
        //mChart.animateXY(1500,1500);
        legend2(mChart);
        mChart.getDescription().setText("");

        // mChart.getLegend()

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        LineData data =new  LineData(dataSets);
        mChart.setData(data);
        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                float dato=e.getY();
                // aux=Float.parseFloat(dato);
                String aux2=conexion.DameReporte("R",dato);
                Toast.makeText(hora1_UV.this,aux2,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

    }

    private  void axisRight(YAxis axis){

        axis.setEnabled(false);
    }
    private  void topAxis(XAxis axis){

        axis.setEnabled(false);
    }

    private  void legend(Chart chart){
        Legend legend =chart.getLegend();
        //legend.setForm(Legend.LegendForm.CIRCLE);
        //legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        ArrayList<LegendEntry> entries=new ArrayList<>();
        for (int i=0; i<gases.length;i++){
            LegendEntry entry = new LegendEntry();
            //entry.formColor=color[i];
            entry.label=gases[i];
            entries.add(entry);

        }

        legend.setCustom(entries);
    }

    private  void legend2(Chart chart){
        Legend legend =chart.getLegend();
        //legend.setForm(Legend.LegendForm.CIRCLE);
        //legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        ArrayList<LegendEntry> entries=new ArrayList<>();
        for (int i=0; i<MainActivity.hora.size();i++){
            LegendEntry entry = new LegendEntry();
            //entry.formColor=color[i];
            entry.label=MainActivity.hora.get(i);
            entries.add(entry);

        }

        legend.setCustom(entries);
    }


/*
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text=parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
        creatChart();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }*/
}
