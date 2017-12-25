package io.raffi.drawille;

import java.util.Arrays;

public class BrailleMap {
	
	protected final static int UNICODE_OFFSET = 10240;

	protected final static int [] TRANSFORM_MATRIX = { 1, 8, 2, 16, 4, 32, 64, 128 };

	protected Boolean [] map;

	public BrailleMap () {
		this.map = new Boolean [ 8 ];
		this.reset ();
	}

	protected void checkRange ( int x, int y ) {
		if ( x < 0 || y < 0 || x > 1 || y > 3 ) {
			throw new DrawilleException ("BrailleMap: x or y is out of range");
		}
	}

	public void change ( int x, int y, Boolean value ) {
		this.checkRange ( x, y );
		this.map [ y * 2 + x ] = value;
	}

	public Boolean get ( int x, int y ) {
		this.checkRange ( x, y );
		return this.map [ y * 2 + x ];
	}

	public void set ( int x, int y ) {
		this.change ( x, y, true );
	}

	public void unset ( int x, int y ) {
		this.change ( x, y, false );
	}

	public void toggle ( int x, int y ) {
		this.change ( x, y, !this.get ( x, y ) );
	}

	public void reset () {
		Arrays.fill ( this.map, Boolean.FALSE );
	}

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