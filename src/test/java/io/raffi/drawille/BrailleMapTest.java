package io.raffi.drawille;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class BrailleMapTest extends TestCase {

	private Boolean throwsException ( int x, int y ) {
		BrailleMap map = new BrailleMap ();
		Boolean thrown = false;
		try {
			map.set ( x, y );
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

	public void testChangeWorks () {
		BrailleMap map = new BrailleMap ();
		assertFalse ( map.get ( 0, 0 ) );
		map.change ( 0, 0, true );
		assertTrue ( map.get ( 0, 0 ) );
		map.change ( 0, 0, false );
		assertFalse ( map.get ( 0, 0 ) );
	}

	public void testGetWorks () {
		BrailleMap map = new BrailleMap ();
		assertFalse ( map.get ( 0, 0 ) );
		map.change ( 0, 0, true );
		assertTrue ( map.get ( 0, 0 ) );
	}

	public void testSetWorks () {
		BrailleMap map = new BrailleMap ();
		assertFalse ( map.get ( 0, 0 ) );
		map.set ( 0, 0 );
		assertTrue ( map.get ( 0, 0 ) );
		assertFalse ( map.get ( 1, 3 ) );
		map.set ( 1, 3 );
		assertTrue ( map.get ( 1, 3 ) );
	}

	public void testUnsetWorks () {
		BrailleMap map = new BrailleMap ();
		assertFalse ( map.get ( 0, 0 ) );
		map.set ( 0, 0 );
		assertTrue ( map.get ( 0, 0 ) );
		map.unset ( 0, 0 );
		assertFalse ( map.get ( 0, 0 ) );
	}

	public void testToggleWorks () {
		BrailleMap map = new BrailleMap ();
		assertFalse ( map.get ( 0, 0 ) );
		map.toggle ( 0, 0 );
		assertTrue ( map.get ( 0, 0 ) );
		map.toggle ( 0, 0 );
		assertFalse ( map.get ( 0, 0 ) );
	}

	public void testResetWorks () {
		BrailleMap map = new BrailleMap ();
		map.toggle ( 0, 0 );
		map.toggle ( 0, 1 );
		map.toggle ( 1, 1 );
		map.reset ();
		assertFalse ( map.get ( 0, 0 ) );
		assertFalse ( map.get ( 0, 1 ) );
		assertFalse ( map.get ( 1, 1 ) );
	}

	public void testToStringWorks () {
		BrailleMap map = new BrailleMap ();
		assertTrue ( map.toString ().equals ("⠀") );
		map.change ( 0, 0, true );
		assertTrue ( map.toString ().equals ("⠁") );
		map.change ( 0, 0, false );
		assertTrue ( map.toString ().equals ("⠀") );
	}

}