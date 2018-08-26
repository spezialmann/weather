#!/usr/bin/python -u
print("******************************************************")
print("* Starte USB WDE1 Serial Monitoring                  *")
print("******************************************************")

import serial
import sys
import os
import requests
#import datetime
import json

# serial port of USB-WDE1
port = '/dev/ttyUSB0'

# MAIN
def main():
  # open serial line
  ser = serial.Serial(port, 9600)
  # ser = serial.Serial(port, baudrate=9600, bytesize=8, stopbits=1, timeout=0 )
  if not ser.isOpen():
    print "Unable to open serial port %s" % port
    sys.exit(1)

  while(1==1):
    # read line from WDE1
    line = ser.readline()
    line = line.strip()
    print line
    # now = datetime.datetime.now()

    # post_data = {'stationId' : '@home', 'timeOfRecording':now.isoformat(), 'rawData':line}
    post_data = {'stationId' : '@home', 'rawData':line}
    print post_data
    # POST some form-encoded data:
    post_response = requests.post(url='https://fockestreet.herokuapp.com/rawdata', json=post_data)
    print post_response

    data = line.split(";");

    #Wohnzimmer
    wozi = {"stationId":"focke85_wozi", "temp":float(data[3].replace(',','.')), "humidity":float(data[11].replace(',','.')), "precipCount":0, "windspeedKmph":float(data[21].replace(',','.')) };
    post_response = requests.post(url='https://fockestreet2.herokuapp.com/rawdata', json=wozi)

    #Garten
    garten = {"stationId":"focke85_garten", "temp":float(data[19].replace(',','.')), "humidity":float(data[20].replace(',','.')), "precipCount":int(data[22]), "windspeedKmph":float(data[21].replace(',','.')) };
    post_response = requests.post(url='https://fockestreet2.herokuapp.com/rawdata', json=garten)

if __name__ == '__main__':
  main()
