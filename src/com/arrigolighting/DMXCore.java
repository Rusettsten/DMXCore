package com.arrigolighting;
import java.io.IOException;
import jd2xx.JD2XX;

/**
 *DMXCore is a way to output DMX using a FTDI Serial converter chip via Java.
 *<p>
 *You must define your own DMX Variable array. The output of DMX will not stop unless the "stop()" method is called.
 *You must call the "init()" method before calling the "start()" method.
 *<p>
 *References the jd2xx jar and uses JD2XX2.so and jd2xx.dll files.
 * 
 *
 * @author Ben Arrigo
 * @email ben@arrigolighting.com
 * @version 1.1
 * 
 * @param usbPort the USB Port your FTDI Serial device is plugged into - "0" works most of the time
 * @param dmxVar this byte array must have 513 values. Value [0] remains unused, as this DMX distribution is base 1
 * 
 *
 */

public class DMXCore{
	
JD2XX dmx = new JD2XX();
boolean dmxBool = true;

	public void set(int usbPort) {
		try {
			//If you keep getting an error, go online and find a JD2XX library file (.so or.dll) that fits your architecture.
			dmx.open(usbPort); //opens device based on USB input
			dmx.setBaudRate(25000);
		    dmx.setDataCharacteristics(JD2XX.BITS_8, JD2XX.STOP_BITS_2, JD2XX.PARITY_NONE);
		    dmx.setLatencyTimer(88);
		    dmx.setBitMode(0, 0);
		    dmx.setFlowControl(JD2XX.FLOW_NONE,0,0);
		    dmx.setTimeouts(1000,1000);
			}catch(IOException e){
				System.out.println(">>>DMXCore has encountered an IOException.<<<");
				System.out.println(">>>Check connections, and usbPort setting.<<<");
			}
		return;
	}
	
	public void start(byte dmxVar[]) {
		dmxBool = true;
		try{		
			do{
				dmx.setBaudRate(125000);//Sets baud rate for break
				dmx.write((byte)0x00); //Break
				dmx.setBaudRate(250000); //Sets baud rate for data
				dmx.write((byte)0x00);//Start Code DO NOT TAMPER WITH
				for(int i = 1; i < 513 ; i++) {
					dmx.write(dmxVar[i]); //Output 512 times
				}
				dmx.write((byte)0xFF); //End Code DO NOT TAMPER WITH
				Thread.sleep(1000/44); //This is the equivalent of 1/44, meaning it transmits 44 times a second.
		}while(dmxBool);
			
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println(">>>DMXCore not outputting. IOException on run.<<<");
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println(">>>DMXCore Thread could not sleep<<<");
		}
	}
	public void stop() {
		dmxBool = false;
	}
}