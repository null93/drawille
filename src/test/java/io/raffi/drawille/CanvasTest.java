package io.raffi.drawille;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class CanvasTest extends TestCase {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream ();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream ();

	private Boolean throwsException ( int x, int y ) {
		Canvas canvas = new Canvas ( 1, 1 );
		Boolean thrown = false;
		try {
			canvas.set ( x, y );
		}
		catch ( DrawilleException e ) {
			thrown = true;
		}
		catch ( Exception e ) {
			thrown = false;
		}
		return thrown;
	}

	public void testExceptionIsThrown () {
		assertTrue ( this.throwsException ( -1, 0 ) );
		assertTrue ( this.throwsException ( 0, -1 ) );
		assertTrue ( this.throwsException ( 2, 0 ) );
		assertTrue ( this.throwsException ( 1, 4 ) );
		assertFalse ( this.throwsException ( 0, 0 ) );
		assertFalse ( this.throwsException ( 1, 2 ) );
	}

	public void testDimensionGetterWorks () {
		Canvas c1 = new Canvas ( 10, 30 );
		Canvas c2 = new Canvas ( 0, 0 );
		Canvas c3 = new Canvas ( 2, 5 );
		assertTrue ( c1.getWidth () == 10 * 2 );
		assertTrue ( c1.getHeight () == 30 * 4 );
		assertTrue ( c2.getWidth () == 0 * 2 );
		assertTrue ( c2.getHeight () == 0 * 4 );
		assertTrue ( c3.getWidth () == 2 * 2 );
		assertTrue ( c3.getHeight () == 5 * 4 );
	}

	public void testGetterWorks () {
		Canvas canvas = new Canvas ( 1, 1 );
		assertFalse ( canvas.get ( 0, 0 ) );
		assertFalse ( canvas.get ( 1, 1 ) );
		canvas.set ( 0, 0 );
		canvas.set ( 1, 1 );
		assertTrue ( canvas.get ( 0, 0 ) );
		assertTrue ( canvas.get ( 1, 1 ) );
	}

	public void testChangeWorks () {
		Canvas canvas = new Canvas ( 1, 1 );
		assertFalse ( canvas.get ( 0, 0 ) );
		assertFalse ( canvas.get ( 1, 1 ) );
		canvas.change ( 0, 0, true );
		canvas.change ( 1, 1, true );
		assertTrue ( canvas.get ( 0, 0 ) );
		assertTrue ( canvas.get ( 1, 1 ) );
		canvas.change ( 0, 0, false );
		canvas.change ( 1, 1, false );
		assertFalse ( canvas.get ( 0, 0 ) );
		assertFalse ( canvas.get ( 1, 1 ) );
	}

	public void testSetWorks () {
		Canvas canvas = new Canvas ( 1, 1 );
		assertFalse ( canvas.get ( 0, 0 ) );
		assertFalse ( canvas.get ( 1, 1 ) );
		canvas.set ( 0, 0 );
		canvas.set ( 1, 1 );
		assertTrue ( canvas.get ( 0, 0 ) );
		assertTrue ( canvas.get ( 1, 1 ) );
	}

	public void testUnsetWorks () {
		Canvas canvas = new Canvas ( 1, 1 );
		assertFalse ( canvas.get ( 0, 0 ) );
		assertFalse ( canvas.get ( 1, 1 ) );
		canvas.set ( 0, 0 );
		canvas.set ( 1, 1 );
		assertTrue ( canvas.get ( 0, 0 ) );
		assertTrue ( canvas.get ( 1, 1 ) );
		canvas.unset ( 0, 0 );
		canvas.unset ( 1, 1 );
		assertFalse ( canvas.get ( 0, 0 ) );
		assertFalse ( canvas.get ( 1, 1 ) );
	}

	public void testToggleWorks () {
		Canvas canvas = new Canvas ( 1, 1 );
		assertFalse ( canvas.get ( 0, 0 ) );
		assertFalse ( canvas.get ( 1, 1 ) );
		canvas.toggle ( 0, 0 );
		canvas.toggle ( 1, 1 );
		assertTrue ( canvas.get ( 0, 0 ) );
		assertTrue ( canvas.get ( 1, 1 ) );
		canvas.toggle ( 0, 0 );
		canvas.toggle ( 1, 1 );
		assertFalse ( canvas.get ( 0, 0 ) );
		assertFalse ( canvas.get ( 1, 1 ) );
	}

	public void testClearWorks () {
		Canvas canvas = new Canvas ( 1, 1 );
		assertFalse ( canvas.get ( 0, 0 ) );
		assertFalse ( canvas.get ( 1, 1 ) );
		assertFalse ( canvas.get ( 1, 2 ) );
		canvas.toggle ( 0, 0 );
		canvas.toggle ( 1, 1 );
		canvas.toggle ( 1, 2 );
		assertTrue ( canvas.get ( 0, 0 ) );
		assertTrue ( canvas.get ( 1, 1 ) );
		assertTrue ( canvas.get ( 1, 2 ) );
		canvas.clear ();
		assertFalse ( canvas.get ( 0, 0 ) );
		assertFalse ( canvas.get ( 1, 1 ) );
		assertFalse ( canvas.get ( 1, 2 ) );
	}

}