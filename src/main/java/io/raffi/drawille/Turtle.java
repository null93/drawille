package io.raffi.drawille;

/**
 * This class inherits from the Canvas class and it tries to implement turtle graphics. The methods
 * in this class can be abstracted with an idea of a pen and paper. One can move the pen in three
 * axis and based on the z axis, when the pen moves it either draws on the paper or not.
 * @version             1.0.0
 * @package             io.raffi.drawille
 * @author              Rafael Grigorian <me@raffi.io>
 * @copyright           2018 Rafael Grigorian — All Rights Reserved
 * @license             MIT License
 */
class Turtle extends Canvas {

	/**
	 * @param           Double              x                   Horizontal coordinate
	 * @param           Double              y                   Vertical coordinate
	 * @param           Double              a                   Angle
	 * @param           Boolean             isDrawing           Is the pen down?
	 */
	protected double x = 0;
	protected double y = 0;
	protected double a = 0;
	protected Boolean isDrawing = false;

	/**
	 * This constructor simply calls the super constructor and passes the desired dimensions.
	 * @param           Integer             width               Desired width of canvas
	 * @param           Integer             width               Desired height of canvas
	 */
	public Turtle ( int width, int height ) {
		super ( width, height );
	}

	/**
	 * This method simply returns the horizontal component of the position of the pen.
	 * @return          double                                  Horizontal position of pen
	 */
	public double getX () {
		return this.x;
	}

	/**
	 * This method simply returns the vertical component of the position of the pen.
	 * @return          double                                  Vertical position of pen
	 */
	public double getY () {
		return this.y;
	}

	/**
	 * This method simply returns the angle component of the position of the pen.
	 * @return          double                                  Angle of pen
	 */
	public double getAngle () {
		return this.a;
	}

	/**
	 * This method simply sets the state of the pen to be in the drawing state.
	 * @return          void
	 */
	public void down () {
		this.isDrawing = true;
	}

	/**
	 * This method simply sets the state of the pen to be in the non-drawing state.
	 * @return          void
	 */
	public void up () {
		this.isDrawing = false;
	}

	/**
	 * This method takes in the angle to add to the right and adds it to the pen angle.
	 * @param           double              angle               Angle to move right
	 * @return          void
	 */
	public void right ( double angle ) {
		this.a += angle;
	}

	/**
	 * This method takes in the angle to add to the left and subtracts it from pen angle.
	 * @param           double              angle               Angle to move right
	 * @return          void
	 */
	public void left ( double angle ) {
		this.a -= angle;
	}

	/**
	 * This method takes in the length and expects us to move backwards with the pen based on the
	 * current pen angle. The forward method is used to implement this method by simply passing in
	 * the same scalar value of length but in the opposite direction.
	 * @param           double              length              Length to move back
	 * @return          void
	 */
	public void backward ( double length ) {
		this.forward ( length * -1 );
	}

	/**
	 * This method takes in the length and expects us to move forwards with the pen based on the
	 * current pen angle.
	 * @param           double              length              Length to move forward
	 * @return          void
	 */
	public void forward ( double length ) {
		double theta = this.a / 180.0 * Math.PI;
		double x = this.x + length * Math.cos ( theta );
		double y = this.y + length * Math.sin ( theta );
		this.move ( x, y );
	}

	/**
	 * This method takes in a new horizontal and vertical coordinate and based on the current
	 * coordinates, it draws a line to connect them.  If the state of isDrawing is false, then we do
	 * not draw and instead just move the pen to those coordinates.
	 * @param           double              x                   Horizontal coordinate
	 * @param           double              y                   Vertical coordinate
	 * @return          void
	 */
	public void move ( double x, double y ) {
		if ( this.isDrawing ) {
			int x1 = ( int ) Math.round ( this.x );
			int y1 = ( int ) Math.round ( this.y );
			int x2 = ( int ) Math.round ( x );
			int y2 = ( int ) Math.round ( y );
			int deltaX = Math.max ( x1, x2 ) - Math.min ( x1, x2 );
			int deltaY = Math.max ( y1, y2 ) - Math.min ( y1, y2 );
			int directionX = x1 <= x2 ? 1 : -1;
			int directionY = y1 <= y2 ? 1 : -1;
			int a = Math.max ( deltaX, deltaY );
			for ( int i = 0; i <= a; i++ ) {
				int x0 = x1;
				int y0 = y1;
				if ( deltaY > 0 ) {
					y0 += ( ( double ) i * deltaY ) / a * directionY;
				}
				if (deltaX > 0) {
					x0 += ( ( double ) i * deltaX ) / a * directionX;
				}
				this.set ( x0, y0 );
			}
		}
		this.x = x;
		this.y = y;
	}

}