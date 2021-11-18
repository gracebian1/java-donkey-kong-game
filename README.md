
##Introduction
This project presents a simplified version of Donkey Kong game. The game runs on a square board, all coordinates are integers. 

There are three characters: Mario, gorilla, and princess. The game randomly places the positions of gorilla, Mario, hammer, cask and fireball. As in the Donkey Kong game, the princess always locates next to the gorilla. The oil is always placed at the bottom left of the board.

When the game starts, Mario, cask and fireball move in turns. For each turn, Mario, cask, and fireball randomly move up, down, left or right. Cask and fireball can move one step each turn; Mario can move one or two steps each turn. To simulate the jumping and climbing up or down moves, Mario is considered jumping if Mario’s x-coordinate moves two steps; Mario is considered climbing if Mario’s y-coordinate moves two steps.

* If Mario hits the cask, fireball, or gorilla, Mario dies, game is over.

* If Mario hits the spot where the hammer is located, system will print “Mario has hammer” message. Hammer will last for 5 rounds. Mario will not die if he holds the hammer when running into the obstacles. The fireball will change color into blue when Mario has the hammer.

* If Mario hits the spot where the princess stands, the player wins. 



##Features
Some preset features of this game: 

* The positions of gorilla and princess are always next to each other and with the same y-coordinate;
* Positions for characters and obstacles as well as hammers are randomly generated;
* Mario will be dead if he hits either gorilla, or cask or fireball;
* Mario could use hammer to hit cask and fireball;
* Three hammers has been set to the game, a reasonable size to get ideal performance;
* Each hammer could be used for 5 rounds;
* When certain cask drops into the oil, it will change into fireball;
* When Mario got a hammer, fireball changed color;
* Gorilla throws a new cask every 3 rounds; 
* Cask and fireball move 1 step for each round; 
* Mario can move 1 or 2 steps each round; 
* To simulate the jump and climb moves, Mario is considered jumping if x-coordinate moves two steps; Mario is considered climbing if y-coordinate moves two steps.


##OOP used in the program
The program includs five classes. 

* `DonkeyKong.java` includes the main method from which to start the game.
* `GameOps.java` includes different game running methods, such as randomly generated coordinates for characters and obstacles, different check method to check positions of Mario, cask, fireball, hammer, etc.
* `Character.java` includes inheritance usage, superclass Character and subclasses to extend superclass and use overriding.
* `Obstacle.java` includes inheritance usage, superclass Obstacle and subclasses to extend it and use overridng and overloading.
* `Hammer.java` includes the hammer related variables and getters and setters.



##How to run
1. Start the IDE (the game has been tested and verified working in Eclipse with JDK version 8) and import the folder into Eclipse. 
2. Go to package `donkeykong`, go to `DonkeyKong.java` where the main method is located. Then right click, and select `Run As` -> `Java Application`.
3. Game will start with welcome message. Then enter `1` to start the game, or `0` to exit the program. The user could play multiple times to experience different scenarios.