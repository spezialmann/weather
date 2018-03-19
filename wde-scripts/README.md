## Rasbian Installation
* https://www.raspberrypi.org/documentation/installation/installing-images/mac.md
* first login: pi / raspberry (Achtung X <--> Y auf en Tastatur)
* sudo raspi-config
* Enable SSH from remote

## WDE 1-2 Installation
* https://www.kompf.de/weather/technik.html
* dmesg | grep ELV  
    + [    3.910593] usb 1-1.3: Product: ELV USB-WDE1 Wetterdatenempfänger
* sudo apt-get install python-serial
* sudo apt-get install python-pip
* pip install requests
* copy serialmon.py
* Richtigen heroku Endpunkt ergänzen
* Test: python serialmon.py

## Install Python Script as a service
* cd /lib/systemd/system/
* sudo vim.tiny serialmon.service
* Inhalt aus File install-python-sa-service kopieren
* sudo systemctl enable serialmon.service
* In /etc/crontab
    + 0 * * * *       root    service serialmon restart
