package io.raffi.drawille;

import java.util.Arrays;

/**
 * This class stores a 4 by 2 pixel matrix that is eventually translated into a braille character.
 * This method abstracts away all the calculations that is needed to transform a matrix into a
 * braille character. This class is meant to be used as a sub-matrix.
 * @version             1.0.3
 * @package             io.raffi.drawille
 * @author              Rafael Grigorian <me@raffi.io>
 * @copyright           2018 Rafael Grigorian — All Rights Reserved
 * @license             MIT License
 */
public class BrailleMap {

	/**
	 * @var             Integer             UNICODE_OFFSET      Braille characters unicode offset
	 * @var             Integer []          TRANSFORM_MATRIX    Transformation matrix for braille
	 * @var             Boolean []          map                 Flattened pixel map matrix (4 by 2)
	 */
	protected final static int UNICODE_OFFSET = 10240;
	protected final static int [] TRANSFORM_MATRIX = { 1, 8, 2, 16, 4, 32, 64, 128 };
	protected Boolean [] map;

	/**
	 * This constructor initializes the pixel map matrix and resets the matrix by initializing the
	 * values with false.
	 */
	public BrailleMap () {
		this.map = new Boolean [ 8 ];
		this.reset ();
	}

	/**
	 * This method takes in a horizontal and vertical component and checks to see if it is in range
	 * of the pixel matrix.  Since braille can be expressed by a 4 by 2 dot matrix, these bounds are
	 * taken to be the upper bound respectively while negative numbers are taken as the lower bound.
	 * @param           Integer             x                   Horizontal coordinate
	 * @param           Integer             y                   Vertical coordinate
	 * @return          void
	 */
	protected void checkRange ( int x, int y ) {
		if ( x < 0 || y < 0 || x > 1 || y > 3 ) {
			throw new DrawilleException ( x, y );
		}
	}

	/**
	 * This method takes in a horizontal and vertical coordinates alongside a matrix entry value. It
	 * then sets said value into the pixel matrix based on the passed coordinates
	 * @param           Integer             x                   Horizontal coordinate
	 * @param           Integer             y                   Vertical coordinate
	 * @param           Boolean             value               The value to set matrix entry to
	 * @return          void
	 */
	public void change ( int x, int y, Boolean value ) {
		this.checkRange ( x, y );
		this.map [ y * 2 + x ] = value;
	}

	/**
	 * This method takes in a horizontal and vertical coordinates and it returns the value that is
	 * saved in the pixel matrix based on the passed coordinates.
	 * @param           Integer             x                   Horizontal coordinate
	 * @param           Integer             y                   Vertical coordinate
	 * @return          Boolean                                 Saved state based on coordinates
	 */
	public Boolean get ( int x, int y ) {
		this.checkRange ( x, y );
		return this.map [ y * 2 + x ];
	}

	/**
	 * This method takes in a horizontal and vertical coordinates, it then activates the value into
	 * the pixel matrix based on the passed coordinates.
	 * @param           Integer             x                   Horizontal coordinate
	 * @param           Integer             y                   Vertical coordinate
	 * @return          void
	 */
	public void set ( int x, int y ) {
		this.change ( x, y, true );
	}

	/**
	 * This method takes in a horizontal and vertical coordinates, it then deactivates the value
	 * into the pixel matrix based on the passed coordinates.
	 * @param           Integer             x                   Horizontal coordinate
	 * @param           Integer             y                   Vertical coordinate
	 * @return          void
	 */
	public void unset ( int x, int y ) {
		this.change ( x, y, false );
	}

	/**
	 * This method takes in a horizontal and vertical coordinates, it then toggles the value in the
	 * pixel matrix based on the passed coordinates.
	 * @param           Integer             x                   Horizontal coordinate
	 * @param           Integer             y                   Vertical coordinate
	 * @return          void
	 */
	public void toggle ( int x, int y ) {
		this.change ( x, y, !this.get ( x, y ) );
	}

	/**
	 * This method traverses through the pixel map matrix and deactivates all the pixels in the
	 * matrix by setting all the values to false.
	 * @return          void
	 */
	public void reset () {
		Arrays.fill ( this.map, Boolean.FALSE );
	}

	/**
	 * This method traverses through the pixel map matrix and transforms the matrix into a braille
	 * character. The resulting character is returned in string value.
	 * @return          String                                  Pixel matrix as braille character
	 */
	public String toString () {
		int decimal = BrailleMap.UNICODE_OFFSET;
		for ( int i = 0; i < 8; i++ ) {
			if ( this.map [ i ] ) {
				decimal += BrailleMap.TRANSFORM_MATRIX [ i ];
			}
		}
		return Character.toString ( ( char ) decimal );
	}

}