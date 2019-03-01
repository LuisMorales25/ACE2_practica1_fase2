
import android.content.Intent;
import android.os.Bundle;
import ketai.net.bluetooth.*;
import ketai.ui.*;
import ketai.net.*;
import android.os.Bundle;                                 // 1
import android.content.Intent;                            // 2
import oscP5.*;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.Manifest;


PFont fontMy;
boolean bReleased = true; //no permament sending when finger is tap
KetaiBluetooth bt;
boolean isConfiguring = true;
String info = "";
String info2 = "";
String date1="0";
String date2="0";
String date3="0";
LocationManager locationManager;
MyLocationListener locationListener;

// Variables to hold the current GPS data
float currentLatitude  = 0;
float currentLongitude = 0;
float currentAccuracy  = 0;
String currentProvider = "";

boolean hasLocation = false;


KetaiList klist;
ArrayList devicesDiscovered = new ArrayList();
import controlP5.*;
ControlP5 cp5;
Knob m1, m2, m3;
int cont=0;
PVector                remoteCursor = new PVector();

//********************************************************************
// The following code is required to enable bluetooth at startup.
//********************************************************************

void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  bt = new KetaiBluetooth(this);
}

void onActivityResult(int requestCode, int resultCode, Intent data) {
  bt.onActivityResult(requestCode, resultCode, data);
}

void setup() {
  // size(displayWidth, displayHeight);
  requestPermission("android.permission.ACCESS_FINE_LOCATION", "initLocation");
  fullScreen();
  //frameRate(10);
  orientation(PORTRAIT);
  background(255);

  //start listening for BT connections
  bt.start();
  //at app start select device…
  isConfiguring = true;
  //font size
  fontMy = createFont("SansSerif", 40);
  textFont(fontMy);

  cp5 = new ControlP5(this);
 m1 = cp5.addKnob("VMQ7")
    .setRange(0, 250)
    .setValue(10)
    .setPosition(175, 75)
    .setRadius(180)
    .setDragDirection(Knob.VERTICAL);
  m1.getCaptionLabel().setFont(createFont("", 40));
  m1.getValueLabel().setFont(createFont("",50));
  m1.getCaptionLabel().setColor(0);
  

  m2 = cp5.addKnob("VMQ135")
    .setRange(0, 250)
    .setValue(10)
    .setPosition(175, 475)
    .setRadius(180)
    .setDragDirection(Knob.VERTICAL);
  m2.getCaptionLabel().setFont(createFont("", 40));
  m2.getValueLabel().setFont(createFont("",50));
  m2.getCaptionLabel().setColor(0);

  m3 = cp5.addKnob("VUV")
    .setRange(0, 250)
    .setValue(10)
    .setPosition(175, 875)
    .setRadius(180)
    .setDragDirection(Knob.VERTICAL);

  m3.getCaptionLabel().setFont(createFont("", 40));
  m3.getValueLabel().setFont(createFont("",50));
  m3.getCaptionLabel().setColor(0);
}

void draw() {
  if (isConfiguring)
  {

    background(78, 93, 75);
    klist = new KetaiList(this, bt.getPairedDeviceNames());
    isConfiguring = false;
  } else
  {
    background(255);
    if ((mousePressed) && (bReleased == true))
    {
      //send with BT
      byte[] data = {'s', 'w', 'i', 't', 'c', 'h', '\r'};
      bt.broadcast(data);
      //first tap off to send next message
      bReleased = false;
    }
    if (mousePressed == false)
    {
      bReleased = true; //finger is up
    }
    //print received data
    fill(255);
    noStroke();
    textAlign(LEFT);



    //m1.setValue(nums[0]);
    try {
      String[] ejemplo=info.split("_");
    date1=ejemplo[1];
    date2=ejemplo[2];
    date3=ejemplo[3];
      //m1.setValue(int(ejemplo[0]));
      //m2.setValue(int(ejemplo[1]));
      //m3.setValue(int(ejemplo[2]));      
     /// String[] lines = loadStrings("https://medidor-ambiente.azurewebsites.net/api/Pronosticos?monoxido_carbono=2000&aire=800&radiacion_solar=11&latitud=-60.46&longitud=-90.45");
     
          
    }
    catch(Exception e) {
    }
    
    
    
    //println(date1);
    //println(date2);
    //println(date3);
    //println(str(currentLatitude));
    //println(str(currentLatitude));
    
    m1.setValue(int(date1));
    m2.setValue(int(date2));
    m3.setValue(int(date3));
    
    
    if(date1!="0"&&date2!="0"&&date3!="0"){
    String url="https://medidor-ambiente.azurewebsites.net/api/Pronosticos?monoxido_carbono="+date1+"&aire="+date2+"&radiacion_solar="+date3+"&latitud="+str(currentLatitude)+"&longitud="+str(currentLongitude)+"";
    String[] lines = loadStrings(url);
    println(url);
    }
    


    
    
    
    
    
  }
  
//  delay(1000);
}

void onKetaiListSelection(KetaiList klist) {
  String selection = klist.getSelection();
  bt.connectToDeviceByName(selection);
  //dispose of list for now
  klist = null;
}

//Call back method to manage data received
void onBluetoothDataEvent(String who, byte[] data) {
  if (isConfiguring)
    return;
  //received
    
  info += new String(data);
 
  //clean if string to long
  if (info.contains("#"))
    info = "";
}

void initLocation(boolean granted) {
  if (granted) {    
    Context context = getContext();
    locationListener = new MyLocationListener();
    locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);    
    // Register the listener with the Location Manager to receive location updates
    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    hasLocation = true;
  } else {
    hasLocation = false;
  }
}

// Class for capturing the GPS data
class MyLocationListener implements LocationListener {
  public void onLocationChanged(Location location) {
    currentLatitude  = (float)location.getLatitude();
    currentLongitude = (float)location.getLongitude();
    currentAccuracy  = (float)location.getAccuracy();
    currentProvider  = location.getProvider();
  }

  public void onProviderDisabled (String provider) { 
    currentProvider = "";
  }

  public void onProviderEnabled (String provider) { 
    currentProvider = provider;
  }

  public void onStatusChanged (String provider, int status, Bundle extras) {
  }
}
