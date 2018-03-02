package com.arrigolighting;

import com.arrigolighting.DMXCore;

public class Output implements Runnable {

	public static byte dmxUniverse[];
	
	public static void main(String[] args) throws InterruptedException {
		
		//Try's opening FTDI device on specified USB Port. For this example, we'll use 0.
		DMXCore.set(0);
		
		//Let's now activate our array.
		dmxUniverse = new byte[513];
		
		//We'll need to isolate the DMXCore value. We can continue working on commands and processing while the DMXCore executes in a separate Thread.
		
		Output obj = new Output(); //Name of your class goes in place of "Output"
		Thread dmxThread = new Thread(obj);
		dmxThread.start();
		
		//Now, changing the values in this universe is simple. Just reference its array value.
		//Please note that the DMX channels are base one. Array value [0] is unused.
		
		//This example will set channels 1 and 2 at full after five seconds. The "Interrupted Exception" is for the sleep method.
		Thread.sleep(5000); //Time in milliseconds
		dmxUniverse[1] = (byte) 255;
		dmxUniverse[2] = (byte) 255;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		DMXCore.start(dmxUniverse);
	}

}
