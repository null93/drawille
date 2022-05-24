package io.raffi.drawille;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * This class is used to hold all the BrailleMap objects and uses them as sub-matrices. It is an
 * abstraction of a pixel screen.  Methods to interact with those pixels can be found in this class.
 * @version             1.0.2
 * @package             io.raffi.drawille
 * @author              Rafael Grigorian <me@raffi.io>
 * @copyright           2018 Rafael Grigorian — All Rights Reserved
 * @license             MIT License
 */
public class Canvas {

	/**
	 * @var             Byte []             lineEndingBytes     New line bytes
	 * @var             Integer             width               Width of the canvas
	 * @var             Integer             height              Height of the canvas
	 * @var             BrailleMap []       screen              Flattened screen matrix
	 */
	private byte [] lineEndingBytes = System.lineSeparator().toString ().getBytes ();
	protected int width;
	protected int height;
	protected BrailleMap [] screen;

	/**
	 * This constructor takes in a width and height and initializes a flattened matrix of BrailleMap
	 * objects. These objects serve as sub-matrices and extend the 'pixel' definition that can be
	 * displayed on a screen.
	 * @param           Integer             width               The desired width of the canvas
	 * @param           Integer             height              The desired height of the canvas
	 */
	public Canvas ( int width, int height ) {
		this.width = width;
		this.height = height;
		this.screen = new BrailleMap [ this.width * this.height ];
		for ( int i = 0; i < this.width * this.height; i++ ) {
			this.screen [ i ] = new BrailleMap ();
		}
	}

	/**
	 * This method takes in a horizontal and vertical component and checks to see if it is in range
	 * of the screen matrix. Since braille can be expressed by a 3 by 2 dot matrix, these bounds are
	 * taken to be the upper bound respectively while negative numbers are taken as the lower bound.
	 * These values are taken into effect by the getWidth and getHeight methods.
	 * @param           Integer             x                   Horizontal coordinate
	 * @param           Integer             y                   Vertical coordinate
	 * @return          void
	 */
	protected void checkRange ( int x, int y ) {
		if ( x >= this.getWidth () || y >= this.getHeight () || x < 0 || y < 0 ) {
			throw new DrawilleException ( x, y );
		}
	}

	/**
	 * This method returns the screen width in the true pixel definition. The user supplied width is
	 * multiplied by 2 because a braille dot matrix has 2 columns.
	 * @return          Integer                                 True pixel width
	 */
	public int getWidth () {
		return this.width * 2;
	}

	/**
	 * This method returns the screen height in the true pixel definition. The user supplied height
	 * is multiplied by 4 because a braille dot matrix has 4 rows.
	 * @return          Integer                                 True pixel width
	 */
	public int getHeight () {
		return this.height * 4;
	}

	/**
	 * This method takes in a horizontal and vertical coordinate and returns the value of the
	 * activation of said pixel. If true, the pixel is turned on, otherwise it is off.
	 * @param           Integer             x                   Horizontal coordinate of pixel
	 * @param           Integer             y                   Vertical coordinate of pixel
	 * @return          Boolean                                 The activation value of the pixel
	 */
	public Boolean get ( int x, int y ) {
		this.checkRange ( x, y );
		BrailleMap map = this.screen [ ( ( y / 4 ) * this.width ) + ( x / 2 ) ];
		return map.get ( x % 2, y % 4 );
	}

	/**
	 * This method takes in a horizontal and vertical coordinate as well as an activation state. It
	 * then applies that activation to said pixel that lives in the passed coordinates.
	 * @param           Integer             x                   Horizontal coordinate of pixel
	 * @param           Integer             y                   Vertical coordinate of pixel
	 * @param           Boolean             value               Activation to set on pixel
	 * @return          void
	 */
	public void change ( int x, int y, Boolean value ) {
		this.checkRange ( x, y );
		BrailleMap map = this.screen [ ( ( y / 4 ) * this.width ) + ( x / 2 ) ];
		map.change ( x % 2, y % 4, value );
	}

	/**
	 * This method takes in a horizontal and vertical coordinate, it then activates said pixel by
	 * setting it's value to true.
	 * @param           Integer             x                   Horizontal coordinate of pixel
	 * @param           Integer             y                   Vertical coordinate of pixel
	 * @return          void
	 */
	public void set ( int x, int y ) {
		this.change ( x, y, true );
	}

	/**
	 * This method takes in a horizontal and vertical coordinate, it then deactivates said pixel by
	 * setting it's value to false.
	 * @param           Integer             x                   Horizontal coordinate of pixel
	 * @param           Integer             y                   Vertical coordinate of pixel
	 * @return          void
	 */
	public void unset ( int x, int y ) {
		this.change ( x, y, false );
	}

	/**
	 * This method takes in a horizontal and vertical coordinate, it then toggles the activation of
	 * said pixel based on the value that it currently has.
	 * @param           Integer             x                   Horizontal coordinate of pixel
	 * @param           Integer             y                   Vertical coordinate of pixel
	 * @return          void
	 */
	public void toggle ( int x, int y ) {
		this.change ( x, y, !this.get ( x, y ) );
	}

	/**
	 * This method traverses through all the BrailleMap objects that is stored to make up the
	 * screen, it then resets all the values in those sub-matrices.
	 * @return          void
	 */
	public void clear () {
		for ( int i = 0; i < this.width * this.height; i++ ) {
			this.screen [ i ].reset ();
		}
	}

	/**
	 * This method traverses through all the BrailleMap objects and renders out the sub-matrices by
	 * asking for the object's string value with the getString method. It then prints them all out
	 * to the screen by using the overloaded cooresponding render method.
	 * @return void
	 */
	public void render () {
		ByteArrayOutputStream stream = new ByteArrayOutputStream ();
		try {
			this.render ( stream );
		}
		catch ( IOException e ) {
			System.out.print ( e );
		}
		System.out.print ( stream.toString () );
	}

	/**
	 * This method traverses through all the BrailleMap objects and renders out the sub-matrices by
	 * asking for the object's string value with the getString method. It then writes said output to
	 * the specified ByteArrayOutputStream. This stream is then returned back to caller for method
	 * chaining.
	 * @param           ByteArrayOutputStream       stream      Stream to write to
	 * @return          ByteArrayOutputStream                   Same stream that was passed in
	 * @throws          IOException                             ByteArrayOutputStream throws exception
	 */
	public ByteArrayOutputStream render ( ByteArrayOutputStream stream ) throws IOException {
		for ( int i = 0; i < this.width * this.height; i++ ) {
			String brailleMap = this.screen [ i ].toString ();
			byte [] buffer = brailleMap.getBytes ();
			stream.write ( buffer );
			if ( i % this.width == this.width - 1 ) {
				stream.write ( lineEndingBytes );
			}
		}
		return stream;
	}

}
