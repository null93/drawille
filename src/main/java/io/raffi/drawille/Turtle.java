package io.raffi.drawille;

class Turtle extends Canvas {

	protected double x = 0;
	protected double y = 0;
	protected int rotation = 0;

	protected Boolean isDrawing = false;

	public Turtle ( int width, int height ) {
		super ( width, height );
	}

	public double getX () {
		return this.x;
	}

	public double getY () {
		return this.y;
	}

	public int getRotation () {
		return this.rotation;
	}

	public void down () {
		this.isDrawing = false;
	}

	public void up () {
		this.isDrawing = true;
	}

	public void forward ( double length ) {
		double theta = this.rotation / 180.0 * Math.PI;
		double x = this.x + length * Math.cos ( theta );
		double y = this.y + length * Math.sin ( theta );
		this.move ( x, y );
	}

	public void back ( double length ) {
		this.forward ( length * -1 );
	}

	public void right ( int angle ) {
		this.rotation += angle;
	}

	public void left ( int angle ) {
		this.rotation -= angle;
	}

	public void move ( double x, double y ) {
		if ( !this.isDrawing ) {
			int x1 = ( int ) Math.round ( this.x );
			int y1 = ( int ) Math.round ( this.y );
			int x2 = ( int ) Math.round ( x );
			int y2 = ( int ) Math.round ( y );
			int xdiff = Math.max ( x1, x2 ) - Math.min ( x1, x2 );
			int ydiff = Math.max ( y1, y2 ) - Math.min ( y1, y2 );
			int xdir = x1 <= x2 ? 1 : -1;
			int ydir = y1 <= y2 ? 1 : -1;
			int r = Math.max ( xdiff, ydiff );
			for ( int i = 0; i <= r; i++ ) {
				int x0 = x1;
				int y0 = y1;
				if ( ydiff > 0 ) {
					y0 += ( ( double ) i * ydiff ) / r * ydir;
				}
				if (xdiff > 0) {
					x0 += ( ( double ) i * xdiff ) / r * xdir;
				}
				this.set ( x0, y0 );
			}
		}
		this.x = x;
		this.y = y;
	}

}