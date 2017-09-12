# weather

[![Apache License 2](https://img.shields.io/badge/license-ASF2-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.txt)
[![Build Status](https://travis-ci.org/spezialmann/weather.svg?branch=master)](https://travis-ci.org/spezialmann/weather)
[![CircleCI](https://circleci.com/gh/spezialmann/weather.svg?style=svg)](https://circleci.com/gh/spezialmann/weather)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/91ebfefd316d48828464163f9fe9a7df)](https://www.codacy.com/app/spezialmann/weather?utm_source=github.com&utm_medium=referral&utm_content=spezialmann/weather&utm_campaign=badger)

## Simple web project / api for
 
- Worldweatheronline wheater data
- Measurement and analysis for weather station
- http://www.elv.de/elv-funk-kombi-wettersensor-ks-300-2-br-inkl-hochwertigem-2-m-edelstahlmast-br-und-qualitaetsbatterien.html
- http://www.elv.de/usb-wetterdaten-empfaenger-usb-wde1-komplettbausatz-1.html 

## System requirements
- Java 8
- MongoDB

I use the weather app on a raspberry pi 2 with raspbian (jessie) and python for reading the wde1 serieal data (See wde-scripts dir: https://github.com/spezialmann/weather/tree/master/wde-scripts)


## @ToDo
- E-Mail with daily overview
- Warning message in case of storm, massive rain, extreme heat / frost  
- Set locations active / inactive for request weather data every hour 
