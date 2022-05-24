package docs.examples;

import io.raffi.drawille.Canvas;

public class CanvasDemo {

    public static void main (String[] args) {
        Canvas canvas = new Canvas ( 75, 6 );
        for ( int x = 0; x <= canvas.getWidth () * 8; x++ ) {
            canvas.set ( x / 10, ( int ) Math.round ( 10 + Math.cos ( x * Math.PI / 180 ) * 10 ) );
        }
        canvas.render ();
    }

}