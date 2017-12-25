package io.raffi.drawille;

import java.util.Arrays;

public class Canvas {

	protected int width;

	protected int height;

	protected BrailleMap [] screen;

	public Canvas ( int width, int height ) {
		this.width = width;
		this.height = height;
		this.screen = new BrailleMap [ this.width * this.height ];
		for ( int i = 0; i < this.width * this.height; i++ ) {
			this.screen [ i ] = new BrailleMap ();
		}
	}

	protected void checkRange ( int x, int y ) {
		if ( x < 0 || y < 0 || x > this.getWidth () || y > this.getHeight () ) {
			throw new DrawilleException ("Canvas: x or y is out of range");
		}
	}

	public Boolean get ( int x, int y ) {
		this.checkRange ( x, y );
		BrailleMap map = this.screen [ ( ( y / 4 ) * this.width ) + ( x / 2 ) ];
		return map.get ( x % 2, y % 4 );
	}

	public int getWidth () {
		return this.width * 2;
	}

	public int getHeight () {
		return this.height * 4;
	}

	public void change ( int x, int y, Boolean value ) {
		this.checkRange ( x, y );
		BrailleMap map = this.screen [ ( ( y / 4 ) * this.width ) + ( x / 2 ) ];
		map.change ( x % 2, y % 4, value );
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

	public void clear () {
		System.out.println ("\033[2J");
		for ( int i = 0; i < this.width * this.height; i++ ) {
			this.screen [ i ].reset ();
		}
	}

	public void render () {
		for ( int i = 0; i < this.width * this.height; i++ ) {
			System.out.print ( this.screen [ i ] );
			if ( i % this.width == this.width - 1 ) {
				System.out.println ("");
			}
		}
	}

}