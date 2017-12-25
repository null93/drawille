package io.raffi.drawille;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class DrawilleExceptionTest extends TestCase {

	public void testExceptionThrown () {
		Boolean thrown = false;
		try {
			throw new DrawilleException ( -1, -1 );
		}
		catch ( DrawilleException e ) {
			thrown = true;
		}
		catch ( Exception e ) {
			thrown = false;
		}
		assertTrue ( thrown );
	}

	public void testExceptionPassParameters () {
		String message = "";
		try {
			throw new DrawilleException ( -1, -2 );
		}
		catch ( DrawilleException e ) {
			message = e.getMessage ();
		}
		assertTrue ( message.equals ("Out of range {x:-1,y:-2}") );
	}

}