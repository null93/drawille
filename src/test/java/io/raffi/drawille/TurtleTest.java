package io.raffi.drawille;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TurtleTest extends TestCase {

	public void testGettersWork () {
		Turtle turtle = new Turtle ( 1, 1 );
		assertTrue ( turtle.getX () == 0 );
		assertTrue ( turtle.getY () == 0 );
		turtle.move ( 1, 2 );
		assertTrue ( turtle.getX () == 1 );
		assertTrue ( turtle.getY () == 2 );
	}

	public void testAngleWorks () {
		Turtle turtle = new Turtle ( 1, 1 );
		assertTrue ( turtle.getAngle () == 0 );
		turtle.left ( 45 );
		assertTrue ( turtle.getAngle () == -45 );
		turtle.right ( 90 );
		assertTrue ( turtle.getAngle () == 45 );
	}

	public void testPenIsDrawing () {
		Turtle turtle = new Turtle ( 1, 1 );
		assertFalse ( turtle.get ( 0, 0 ) );
		assertFalse ( turtle.get ( 0, 1 ) );
		assertFalse ( turtle.get ( 0, 2 ) );
		assertFalse ( turtle.get ( 0, 3 ) );
		turtle.move ( 0, 3 );
		assertFalse ( turtle.get ( 0, 0 ) );
		assertFalse ( turtle.get ( 0, 1 ) );
		assertFalse ( turtle.get ( 0, 2 ) );
		assertFalse ( turtle.get ( 0, 3 ) );
		turtle.down ();
		turtle.move ( 0, 0 );
		assertTrue ( turtle.get ( 0, 0 ) );
		assertTrue ( turtle.get ( 0, 1 ) );
		assertTrue ( turtle.get ( 0, 2 ) );
		assertTrue ( turtle.get ( 0, 3 ) );
		turtle.up ();
		turtle.move ( 1, 0 );
		assertFalse ( turtle.get ( 1, 0 ) );
		assertTrue ( turtle.get ( 0, 0 ) );
	}

}