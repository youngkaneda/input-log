# input-log
A java input display for my fighting games experience.

## Installation
You will need Java 9+.

```
$ git clone https://github.com/youngkaneda/input-log
$ cd input-log
$ mvn clean package
$ java -jar target/input-log-1.0.jar
```

## How configure buttons.

For mapping the buttons, I created a json filed called ``config.json`` in resources folder, just write a object with the key code, and where are the sprite for that button.
```
[
  {
    "keyCode": 76,
    "sprite": "path/to/file.png"
  },
...
]
```
*I used the JNativeHook key code values for the keyboard keys.*

Said that you can use your own sprites, add new buttons, remove buttons, map it in your own way, just fork and change.

## Screenshot
![screenshot](ss.png)

## Motivation
A lot of training modes hava input display, but they are built-in, and I wanted one for when I was playing online, see if I had missed some moves execution.

## Limitations (SOLVED)
~~When you press and hold more than one button, the second is not considered a "press", and it spammed in the window like if you are typing it.~~

## TODO
1. Create an option for the user map they buttons, they coming from any input device, controllers, arcades, keyboard.
2. ~~Refact the default button mapping.~~
3. Add more input commands, like DR, DL, UL, UR, or even QCB, QCF...

## What I learned
Well I could refresh some past contents in my head, writing a event bus, listeners, and I had to implement by myself some option for the JFrame since I was using an undecorated one for design purposes. I wrote a drag listener to move the window through the screen, and implemented one bottom border resize listener, since the user dragged the window in the bottom border, the listener recognized and resized the window according the user drag, I had to use some high school vectorial math, so, was a good chance to remember.

---
Feel free to fork and contribute.
