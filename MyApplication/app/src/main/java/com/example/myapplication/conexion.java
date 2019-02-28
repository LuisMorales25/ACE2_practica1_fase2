package com.example.myapplication;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class conexion extends AppCompatActivity {
    TextView sal;
    public static double[] radiacionpromedio =new double[6];
    ArrayList<String> datos= new ArrayList<>();
    ArrayList<String> latitud= new ArrayList<>();
    ArrayList<String> longitud= new ArrayList<>();
    public static int[] radiacionmax =new int[6];
    public static int[] radiacionmin =new int[6];
    public static double[] monoxidopromedio =new double[6];
    public static int[] monoxidomax =new int[6];
    public static int[] monoxidomin =new int[6];
    public static double[] airepromedio =new double[6];
    public static int[] airemax =new int[6];
    public static int[] airemin =new int[6];
    public static String[] fechas = new String[6];
    //public static String[] latitud = new String[30];
    //public static String[] longitud = new String[30];
    public static int p=0,min=0,max=0,conexion,l=0;
    public static String airegeneral,radiaciongeneral,monoxidogeneral;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conexion);
        Inicializar();
        getData("http://medidor-ambiente.azurewebsites.net/api/Estadisticas/Diarias","grafica");
        getData("http://medidor-ambiente.azurewebsites.net/api/Coordenadas","mapa");
        getData("http://medidor-ambiente.azurewebsites.net/api/Estadisticas","general");

        finish();
        //Intent nuevoForm = new Intent(conexion.this,MainActivity.class);
        //startActivity(nuevoForm);
        //TextView p1 =(TextView)findViewById(R.id.textView);
        //p1.setText(airegeneral);
/*
        if (conexion==1){
            Toast.makeText(conexion.this,"Conect",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(conexion.this," NO Conect",Toast.LENGTH_SHORT).show();
        }


       /* Button boton2 = (Button) findViewById(R.id.button6);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevoForm = new Intent(conexion.this,MainActivity.class);
                startActivity(nuevoForm);
            }
        });*/

    }

    public void Inicializar(){
        for(int i =0;i<6;i++){
            radiacionmax[i]=0;
            radiacionmin[i]=0;
            radiacionpromedio[i]=0;
            airemax[i]=0;
            airemin[i]=0;
            airepromedio[i]=0;
            monoxidomax[i]=0;
            monoxidomin[i]=0;
            monoxidopromedio[i]=0;
            fechas[i]="dia"+i;
        }
        p=0;min=0;max=0;l=0;conexion=0;
        airegeneral="";monoxidogeneral="";radiaciongeneral="";
    }

    /*static int[] DameDatos(String dato){
        String consulta;
        switch (dato){
            case "Radiacion<":
                consulta="http://medidor-ambiente.azurewebsites.net/api/Estadisticas";
                break;
        }
    }*/

    public void getData(String ruta,String sensor){
        String sql = ruta;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        URL url = null;
        HttpURLConnection conn;
        try {
            url = new URL(sql);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            String json = "";
            while((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }
            json = response.toString();
            JSONArray jsonArr = null;
            jsonArr = new JSONArray(json);
            String mensaje = "";
            for(int i = 0;i<jsonArr.length();i++){
                JSONObject jsonObject = jsonArr.getJSONObject(i);
                if (sensor.equalsIgnoreCase("mapa")){
                    MainActivity.list_longitud.add(jsonObject.getDouble("Longitud"));
                    MainActivity.list_latitud.add(jsonObject.getDouble("Latitud"));
                    MainActivity.list_uvMax.add(jsonObject.getDouble("Maximo_Radiacion"));
                    MainActivity.list_uvMin.add(jsonObject.getDouble("Minimo_Radiacion"));
                    MainActivity.list_uvMedio.add(jsonObject.getDouble("Promedio_Radiacion"));
                    MainActivity.list_mq135Max.add(jsonObject.getDouble("Maximo_Monoxido"));
                    MainActivity.list_mq135Min.add(jsonObject.getDouble("Minimo_Monoxido"));
                    MainActivity.list_mq135Medio.add(jsonObject.getDouble("Promedio_Monoxido"));
                    MainActivity.list_mq7Max.add(jsonObject.getDouble("Maximo_Aire"));
                    MainActivity.list_mq7Min.add(jsonObject.getDouble("Minimo_Aire"));
                    MainActivity.list_mq7Medio.add(jsonObject.getDouble("Promedio_Aire"));
                    //latitud.add(jsonObject.getString("latitud"));
                    l++;
                }else if (sensor.equalsIgnoreCase("grafica")){
                    switch (jsonObject.optString("Sensor")){
                        case "Monoxido_Carbono":
                            monoxidomax[max]=jsonObject.getInt("Maximo");
                            monoxidomin[max]=jsonObject.getInt("Minimo");
                            monoxidopromedio[max]=jsonObject.getDouble("Promedio");
                            String a=jsonObject.optString("Fecha");
                            a=a.replace("T00:00:00","");
                            a=a.replace("2019-","");
                            fechas[max]=a+"     ";
                            max++;
                            break;
                        case "Aire":
                            airemax[min]=jsonObject.getInt("Maximo");
                            airemin[min]=jsonObject.getInt("Minimo");
                            airepromedio[min]=jsonObject.getDouble("Promedio");
                            min++;
                            break;
                        case "Radiacion":
                            radiacionmax[p]=jsonObject.getInt("Maximo");
                            radiacionmin[p]=jsonObject.getInt("Minimo");
                            radiacionpromedio[p]=jsonObject.getDouble("Promedio");
                            p++;
                            break;
                        default:

                            break;
                    }
                    //mensaje += "Sensor"+i+": "+jsonObject.optString("Sensor")+"\n";
                    //radiacionpromedio[i]=jsonObject.getInt("Maximo");
                }else if(sensor.equalsIgnoreCase("general")){
                    switch (i){
                        case 0:
                            monoxidogeneral=DameReporte("M",jsonObject.getDouble("Promedio") );//"Promedio:\n"+jsonObject.getInt("Promedio")+" PPM";
                            break;
                        case 1:
                            airegeneral=DameReporte("A",jsonObject.getDouble("Promedio"));//"Promedio:\n"+jsonObject.getInt("Promedio")+" PPM";
                            //TextView p1 =(TextView)findViewById(R.id.textView);
                            //p1.setText(airegeneral);
                            break;
                        case 2:
                            radiaciongeneral=DameReporte("R",jsonObject.getDouble("Promedio"));//="Promedio:\n"+jsonObject.getInt("Promedio")+" W/m^2" +
                                   // "";
                            break;
                    }
                }
            }
            conexion=1;
        } catch (MalformedURLException e) {
            conexion=0;
            e.printStackTrace();
        } catch (IOException e) {
            conexion=0;
            e.printStackTrace();
        } catch (JSONException e) {
            conexion=0;
            e.printStackTrace();
        }

    }

    public static void DameDia(String sensor, String fecha){
        String sql="";
        switch (sensor){
            case "Monoxido":
                sql = "http://medidor-ambiente.azurewebsites.net/api/MonoxidoCarbono/PorDia?fecha=2019-"+fecha;
                break;
            case "Aire":
                sql = "http://medidor-ambiente.azurewebsites.net/api/Aire/PorDia?fecha=2019-"+fecha;
                break;
            case "Radiacion":
                sql = "http://medidor-ambiente.azurewebsites.net/api/Radiacion/PorDia?fecha=2019-"+fecha;
                break;
        }
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        URL url = null;
        HttpURLConnection conn;
        try {
            url = new URL(sql);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            String json = "";
            while((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }
            json = response.toString();
            JSONArray jsonArr = null;
            jsonArr = new JSONArray(json);
            String mensaje = "";
            for(int i = 0;i<jsonArr.length();i++){
                JSONObject jsonObject = jsonArr.getJSONObject(i);
                MainActivity.al.add(jsonObject.getInt("Promedio"));
                String f=jsonObject.getString("Hora");
                f=f.replace(":00","");
                MainActivity.hora.add(f);
                mensaje+="Sensor:"+sensor+"->"+jsonObject.getString("Hora")+"\n";

            }
            //sal.setText(mensaje);
            conexion=1;
        } catch (MalformedURLException e) {
            conexion=0;
            e.printStackTrace();
        } catch (IOException e) {
            conexion=0;
            e.printStackTrace();
        } catch (JSONException e) {
            conexion=0;
            e.printStackTrace();
        }
    }

    static String DameReporte(String sensor,double dato){
        String ret="";
        switch (sensor){
            case "M":
                if (dato>=0&&dato<=10){
                    ret=dato+"ppm ligero dolor de cabeza en algunos casos.";
                }else if(dato>10&&dato<=30){
                    ret=dato+"ppm no se excede el nivel carboxihemoglobina del 2.5 %, aun cuando un sujeto normal realice ejercicio ligero o moderado durante 8 horas.";
                }else if(dato>30&&dato<=34){
                    ret=dato+"ppm no se excede el nivel carboxihemoglobina del 2.5 %, aun cuando un sujeto normal realice ejercicio ligero o moderado durante una hora.";
                }else if(dato>34&&dato<=40){
                    ret=dato+"ppm la exposición diaria a esta concentración es equivalente a fumar 20 cigarrillos al día.";
                }else if(dato>40&&dato<=60){
                    ret=dato+"ppm las personas que tienen enfermedades cardíacas no deben exponerse a niveles superiores a esta concentración.";
                }else if(dato>60&&dato<=100){
                    ret=dato+"ppm no se excede el nivel carboxihemoglobina del 2.5 %, aun cuando un sujeto normal realice ejercicio ligero o moderado durante 30 minutos.";
                }else if(dato>100&&dato<=200){
                    ret=dato+"ppm después de 5-6 horas se puede observar un leve dolor de cabeza, nauseas, vértigo y síntomas mentales.";
                }else if(dato>200){
                    ret=dato+"ppm después de 1-1.5 horas hay posibilidad de muerte.";
                }
                break;
            case "A":
                if (dato>=0&&dato<=350){
                    ret=dato+"ppm conecntracion natural en la atmosfera.";
                }else if (dato>=350&&dato<=1000){
                    ret=dato+"ppm es la concentración de calidad aceptable en un recinto cerrado.";
                }else if (dato>=1000&&dato<=2000){
                    ret=dato+"ppm la calidad del aire es considerada baja.";
                }else if (dato>=2000&&dato<=5000){
                    ret=dato+"ppm empieza a causar problemas (dolor de cabeza, insomnio, naúseas). Es aire viciado.";
                }else if (dato>5000){
                    ret=dato+"ppm alteran la presencia de otros gases presentes en el aire, creándose una atmósfera tóxica o deficiente en oxígeno de consecuencias fatales según incrementa la concentración.";
                }
                break;
            case "R":
                if (dato>=0&&dato<=2){
                    ret=dato+"UV bajo peligro de los rayos UV del sol para una persona promedio.\nSi se quema con facilidad, cúbrase y use un protector solar de amplio espectro SPF 30+.";
                }else if(dato>2&&dato<=5){
                    ret=dato+"UV riesgo moderado de daño por exposición al sol sin protección.\nPermanezca a la sombra cerca del mediodía, cuando el sol está más fuerte.";
                }else if(dato>5&&dato<=7){
                    ret=dato+"UV riesgo alto de daño por exposición al sol sin protección. Es necesario protegerse la piel y los ojos para que no sufran daños.\nReduzca el tiempo al sol entre las 10 a. m y las 4 p. m.";
                }else if(dato>7&&dato<=11){
                    ret=dato+"UV  riesgo muy alto de daño por exposición al sol sin protección. Tome precauciones adicionales porque la piel y los ojos sin protección resultarán dañados y pueden quemarse rápidamente.\nSi está al aire libre, busque la sombra y utilice ropa de protección, un sombrero de ala ancha y anteojos de sol que bloqueen los rayos UV.";
                }else if(dato>11){
                    ret=dato+"UV iesgo extremo de daño por exposición al sol sin protección. Tome todas las precauciones porque la piel y los ojos sin protección pueden quemarse en minutos..\nAplíquese generosamente un protector solar de amplio espectro SPF 30+ cada 2 horas, incluso si está nublado, y después de nadar o sudar.";
                }
                break;
        }
        return ret;
    }
}
