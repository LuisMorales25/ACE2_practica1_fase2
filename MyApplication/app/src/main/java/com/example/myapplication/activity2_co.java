package com.example.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class activity2_co extends AppCompatActivity {

    private BarChart barChart;
    private String[]gases=conexion.fechas;//new String[]{"Co","Co2","UV"};
    private  int[]sale=conexion.monoxidomin;//new int[]{3,5,2};
    private int[]color=new int[]{Color.RED,Color.GREEN,Color.BLUE,Color.WHITE,Color.MAGENTA,Color.YELLOW};
    // double aux=api.comida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2_co);
        barChart=(BarChart)findViewById(R.id.BarChart2);
        createCharts();
    }

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

    private ArrayList<BarEntry>getBarEntries(){
        ArrayList<BarEntry> entries =new ArrayList<>();
        for (int i=0;i<sale.length;i++){
            entries.add(new BarEntry(i, sale[i]));
        }
        return entries;

    }

    private void ejeX(XAxis axis){
        axis.setGranularityEnabled(true);
        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis.setValueFormatter(new IndexAxisValueFormatter(gases));

    }

    private void axisLeft(YAxis axis){
        axis.setSpaceTop(5);
        axis.setAxisMaximum(70);

    }

    private  void axisRight(YAxis axis){

        axis.setEnabled(false);
    }

    public void createCharts(){
        barChart=(BarChart)getSameChart(barChart,"",Color.RED,Color.CYAN,3000);
        barChart.setDrawGridBackground(true);
        barChart.setDrawBarShadow(true);
        barChart.setData(getBarData());
        barChart.invalidate();
        ejeX(barChart.getXAxis());
        axisLeft(barChart.getAxisLeft());
        axisRight(barChart.getAxisRight());
        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                float dato=e.getY();
                // aux=Float.parseFloat(dato);
                String aux2=conexion.DameReporte("M",dato);
                Toast.makeText(activity2_co.this,aux2,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

    }

    private DataSet getData(DataSet dataSet){
        dataSet.setColors(color);
        dataSet.setValueTextSize(Color.WHITE);
        dataSet.setValueTextSize(10);
        return dataSet;
    }

    private BarData getBarData(){
        BarDataSet barDataSet=(BarDataSet)getData(new BarDataSet(getBarEntries(),""));
        barDataSet.setBarShadowColor(Color.GRAY);
        BarData barData=new BarData(barDataSet);
        barData.setBarWidth(0.45f);
        return barData;
    }
}
