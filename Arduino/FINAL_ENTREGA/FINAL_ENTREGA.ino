#include <GUVA-S12SD.h>
#include <SoftwareSerial.h>
int VMQ7;
int VMQ135;
int VUV;
String msgt;
String msg1;
String msg2;
String msg3;

SoftwareSerial mySerial(10, 7); // RX, TX
GUVAS12SD uv(A0);

void setup() {
  mySerial.begin(9600);
  Serial.begin(9600);
  pinMode(A0, INPUT);
  pinMode(A1, INPUT);
  //pinMode(A2, INPUT);
}

void loop() {
  while (mySerial.available() > 0)
  {
  VMQ135 = analogRead(A0);
  VMQ7 = analogRead(A1); 
  //VUV = analogRead(A2);
  float mV = uv.read();
  float uv_index = uv.index(mV);
  //Serial.println(uv_index);

  msg1 = VMQ135;
  msg2 = VMQ7;
  msg3 = VUV;
  msgt = "Dato_"+msg1 + "_" + msg2 + "_" + uv_index + "_ggg_#";
  //Serial.println(msgt);
  
  mySerial.print(msgt);
  
  
  }
  
  Serial.print(msgt);
  //delay(1000);
  

}
