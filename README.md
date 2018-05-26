# Drawille
> Pixel graphics in terminal implemented with unicode braille characters

![MIT License](https://img.shields.io/badge/License-MIT-lightgrey.svg?style=for-the-badge)
![Version 1.0.0](https://img.shields.io/badge/Version-1.0.0-lightgrey.svg?style=for-the-badge)
![Travis CI](https://img.shields.io/travis/null93/drawille.svg?style=for-the-badge&colorB=9f9f9f)

## About
This project is a Java port of the original [drawille](https://github.com/asciimoo/drawille) python project by [asciimoo](https://github.com/asciimoo). This project serves as a library for Java to draw in the console using braille letters that are part of the unicode character space.  Braille characters in unicode have a 4 by 2 matrix that can be used as a sub-matrix for each character in a console screen.  This braille dot matrix effectively raises the resolution of any console by eight times. The examples below were rendered in the console using this Java library.  The original ideas for these examples came from the original project repository.

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
This project uses maven as a build system. Therefore to package this library into a jar, execute `mvn package` while in the project root directory. Since braille characters are part of the unicode domain, it is important to append the `-Dfile.encoding=UTF-8` flag when running your Java application.  This will ensure that the braille characters are rendered correctly in your console. If this flag is not passed, then you will likely see the `?` character in place of it.

## Bugs / Feature Requests
If you have any feature requests, please open up an issue. Similarly if there are any bugs found, please report them by opening up an issue.  If a bug is found, please include steps to reproduce the issue, alongside the expected and actual behavior.
