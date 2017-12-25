package io.raffi.drawille;

/**
 * This class inherits from the RuntimeException class. It is meant to be thrown whenever an out of
 * range value is passed to the Canvas and BrailleMap class. The message is statically defined in
 * this class and the caller only has to pass in the out of bounds (x,y) value pairs.
 * @version             1.0.0
 * @package             io.raffi.drawille
 * @author              Rafael Grigorian <me@raffi.io>
 * @copyright           2018 Rafael Grigorian — All Rights Reserved
 * @license             MIT License
 */
public class DrawilleException extends RuntimeException {

	/**
	 * This constructor takes in an (x,y) value pair and displays those pairs to the user. These
	 * values are defined to be out of range by the caller, therefore the caller will be alerted.
	 * @param           Integer             x                   The passed horizontal coordinate
	 * @param           Integer             y                   The passed vertical coordinate
	 */
	public DrawilleException ( int x, int y ) {
		super ( String.format ( "Out of range {x:%d,y:%d}", x, y ) );
	}

}