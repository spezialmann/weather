#!/usr/bin/python -u
print("******************************************************")
print("* Starte USB WDE1 Serial Monitoring                  *")
print("******************************************************")

import sys
import os
import json

# serial port of USB-WDE1
port = '/dev/ttyUSB0'

# MAIN
def main():
    print "Hello World";  
    input = "$1;1;;22,1;;;;;;;;39;;;;;;;;20,4;35;0,0;1668;0;0";
    data = input.split(";");
    
    print data;
    print len(data);
    
    #Wohnzimmer
    wozi = {'stationId':'focke85_wozi', 'temp':float(data[3].replace(',','.')), 'humidity':float(data[11].replace(',','.')), 'precip_count':0};
    
    #Garten
    garten = {'stationId':'focke85_garten', 'temp':float(data[19].replace(',','.')), 'humidity':float(data[20].replace(',','.')), 'precip_count':float(data[22].replace(',','.'))};
    
    print wozi;
    print garten;
    
    
    

if __name__ == '__main__':
  main()
