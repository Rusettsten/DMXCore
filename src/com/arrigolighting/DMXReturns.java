package com.arrigolighting;
public class DMXReturns {

	static void dmxIntFinder(int dmxChan, int dmxInt, byte dmxVar[]){ //Give back a DMX value from the array after searching
	    for(int i = 1; i < 513 ; i++) {
	    	if(dmxChan==i){
	    		dmxVar[i] = (byte) (dmxInt);
	    	}
		}
	    return;
	}

	static void dmxIntAll(int dmxInt, byte dmxVar[]){ //Changes all variables to the same one
		for(int i = 1; i < 513 ; i++) {
			dmxVar[i] = (byte) (dmxInt);
		}
		return;
	}
}