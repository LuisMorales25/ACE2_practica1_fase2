package com.example.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class lineC_co2 extends AppCompatActivity {

    private static final String TAG = "lineC";

    private LineChart mChart;
    private String[]gases=conexion.fechas;//new String[]{"Co","Co2","UV","pedo","humo","comida"};
    private  int[] datos=conexion.airemax;//new double[]{60.2,40.2,55.5,70,75.8,65};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_c_co2);
        mChart=(LineChart)findViewById(R.id.LineChart);
        // mChart.setOnChartGestureListener(lineC.this);
        //mChart.setOnChartValueSelectedListener(lineC.this);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);

        ArrayList<Entry> yValue=new ArrayList<>();
        for (int i=0;i<datos.length;i++){
            yValue.add(new Entry(i,datos[i]));
        }/**
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
        legend(mChart);
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
                String aux2=conexion.DameReporte("A",dato);
                Toast.makeText(lineC_co2.this,aux2,Toast.LENGTH_SHORT).show();
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
            entry.label=gases[i]+"      ";
            entries.add(entry);

        }

        legend.setCustom(entries);
    }
}
