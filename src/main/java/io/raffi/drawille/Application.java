package io.raffi.drawille;

public class Application {

	public static void main ( String [] args ) throws Exception {

		// Canvas c = new Canvas ( 208, 54 - 1 );

		// for ( int i = 1; i < 100; i++ ) {
		// 	for ( int x = 0; x <= c.getWidth (); x++ ) {
		// 	    c.set ( x, ( int ) Math.round ( 10 + Math.cos ( x * Math.PI / 180 ) * 10 ) );
		// 	}
		// 	c.render ();
		// 	Thread.sleep ( 100 );
		// 	c.clear ();
		// }

		Turtle t = new Turtle ( 208, 54 - 1 );
		t.move ( 100, 100 );
		for ( int x = 0; x < 36; x++ ) {
		    t.right ( 10 );
		    for ( int y = 0; y < 36; y++ ) {
		        t.right ( 10 );
		        t.forward ( 8 );
		    }
		}
		t.render ();

	}

}