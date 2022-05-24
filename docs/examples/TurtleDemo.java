package docs.examples;

import io.raffi.drawille.Turtle;

public class TurtleDemo {

    public static void main (String[] args) {
        Turtle turtle = new Turtle ( 75, 50 );
        turtle.move ( turtle.getWidth () / 2, turtle.getHeight () / 2 );
        turtle.down ();
        for ( int x = 0; x < 72; x++ ) {
            turtle.right ( 20 );
            for ( int y = 0; y < 72; y++ ) {
                turtle.right ( 20 );
                turtle.forward ( 10 );
            }
        }
        turtle.render ();
    }

}