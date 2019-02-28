package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class pie1 extends AppCompatActivity {

    private PieChart pieChart;
    private String[]gases=conexion.fechas;//new String[]{"Dia1","Dia2","Dia3","Dia4","Dia5","Dia6"};
    private  double[]sale=conexion.radiacionpromedio;//new int[]{150,55,215,180,130,170};
    private int[]color=new int[]{Color.RED,Color.GREEN,Color.BLUE,Color.GRAY,Color.MAGENTA,Color.CYAN};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie1);
        pieChart=(PieChart)findViewById(R.id.pieChart);
        createCharts();
    }
/*
    private ArrayList<PieEntry>getPieEntries(){
        ArrayList<PieEntry> entries =new ArrayList<>();
        for (int i=0;i<sale.length;i++){
            entries.add(new PieEntry(i,sale[i]));
        }
        return entries;

    }*/

    private Chart getSameChart(Chart chart, String description, int TextColor, int Background, int animate){
        chart.getDescription().setText(description);
        chart.getDescription().setTextSize(15);
        chart.setBackgroundColor(Background);
        chart.animateY(animate);
        legend(chart);
        return  chart;

    }

    private  void legend(Chart chart){
        Legend legend =chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        ArrayList<LegendEntry> entries=new ArrayList<>();
        for (int i=0; i<gases.length;i++){
            LegendEntry entry = new LegendEntry();
            entry.formColor=color[i];
            entry.label=gases[i];
            entries.add(entry);

        }

        legend.setCustom(entries);
    }

    private ArrayList<PieEntry>getPieEntries(){
        ArrayList<PieEntry> entries =new ArrayList<>();
        for (int i=0;i<sale.length;i++){
            entries.add(new PieEntry((float) sale[i]));
        }
        return entries;

    }

    public void createCharts(){
        pieChart=(PieChart)getSameChart(pieChart,"",Color.GRAY,Color.WHITE,3000);
        pieChart.setHoleRadius(15);
        pieChart.setTransparentCircleRadius(17);
        pieChart.setData(getPieData());
        pieChart.invalidate();
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                float dato=e.getY();
                // aux=Float.parseFloat(dato);
                String aux2=conexion.DameReporte("R",dato);
                Toast.makeText(pie1.this,aux2,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
        //pieChart.setDrawHoleEnabled(false);

    }

    private DataSet getData(DataSet dataSet){
        dataSet.setColors(color);
        dataSet.setValueTextSize(Color.WHITE);
        dataSet.setValueTextSize(10);
        return dataSet;
    }

    private PieData getPieData(){
        PieDataSet pieDataSet=(PieDataSet)getData(new PieDataSet(getPieEntries(),""));
        pieDataSet.setSliceSpace(2);
        //pieDataSet. setValueFormatter(new DefaultValueFormatter);

        return new PieData(pieDataSet);
    }
}
