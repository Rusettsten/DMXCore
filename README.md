# DMXCore
DMX-512 output using Java and FTDI Serial Converter

### Notes
Please have your FTDI Serial converter chip properly re-programmed. A guide can be found [here:](https://stevenbreuls.com/2013/05/diy-usb-dmx-dongle-interface-for-under-10/)

It would also be a good idea to install the FTDI drivers, available from [FTDI's Website.](http://www.ftdichip.com/Drivers/D2XX.htm)

When running on linux/Raspberry Pi enter the two commands "sudo rmmod ftdi_sio" and then "sudo rmmod usbserial" in the terminal before each use of the chip.

