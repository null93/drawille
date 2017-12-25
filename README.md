# drawille
> Pixel graphics in terminal implemented with unicode braille characters

![MIT License](https://img.shields.io/badge/License-MIT-lightgrey.svg?style=for-the-badge)
![Version 1.0.0](https://img.shields.io/badge/Version-1.0.0-lightgrey.svg?style=for-the-badge)

## About
This project is a Java port of the original [drawille](https://github.com/asciimoo/drawille) python project by [asciimoo](https://github.com/asciimoo).

## Examples
```java
Canvas canvas = new Canvas ( 75, 50 );
for ( int x = 0; x <= canvas.getWidth () * 10; x++ ) {
	canvas.set ( x / 10, ( int ) Math.round ( 10 + Math.cos ( x * Math.PI / 180 ) * 10 ) );
}
canvas.render ();
```
![Example #01](doc/assets/example_1.png)
```java
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
```
![Example #02](doc/assets/example_2.png)

## Building & Running
-	mvn package
-	java -Dfile.encoding=UTF-8 -cp target/drawille-1.0.0.jar io.raffi.drawille.Application 
