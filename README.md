# MarsRover by Laura Motteran

https://github.com/lauramott/MarsRover written in Java

## How to run it:

Open the command line
```
git clone https://github.com/lauramott/MarsRover.git
```
Make sure you are in the rootfolder of the MarsRover repository and run:
```
java Rovers
```

Add the inputs following the format (guidelines to interpret the results will be displayed in the console):

 ```
4 8 //gridsize
(2, 3, E) LFRFF //rover1 position and commands
(0, 2, N) FFLFRFF //rover2 position and commands
 ```

## The Code

There are two variants of the program: _Rovers.java_ and _MarsRovers.java_. The main difference between the two is that in the first you can add the commands for two rovers and then get the results, while in the second one you can add the command of one rover and get the final position, then add the command for a second rover and get its final position. The logic used is the same so I'll refer to the code in _Rovers.java_ for simplicity.

In _Rovers.java_ the code is in a unique single file. Its key elements are

	- The coordinates x and y and direction of rovers, stored in an array to allow the possibility to run the program with more than two rovers.
	- Size of the grid

## The Problem
Write a program that takes in commands and moves one or more robots around
Mars.
- Each robot has a position (x, y), and an orientation (N, E, S, W)
- Each robot can move forward one space (F), rotate left by 90 degrees (L), or rotate
right by 90 degrees (R)
- If a robot moves off the grid, it is marked as ‘lost’ and its last valid grid position and
orientation is recorded.

## INPUT:

```
4 8
(2, 3, E) LFRFF
(0, 2, N) FFLFRFF
```

The first line of the input ‘4 8’ specifies the size of the grid. The subsequent lines each
represent the initial state and commands for a single robot. (0, 2, N) specifies the initial state
of the form (x, y, orientation). FFLFRFF represents the sequence of movement commands
for the robot.


## OUTPUT:

```
Rover Number: 1
(4, 4, E) FOUND
Rover Number: 2
(0, 4, W) LOST
```

Each line represents the final position and orientation of the robots of the form (x, y,
orientation) and optionally whether the robot was lost.

The rovers will move one after the other, so the the second rover will start to move once the first one has finished moving. 

**If the rover goes off the grid and comes back in the output will show the last visible position and the final position* if it's in the grid**

For example:
**Input:**
```
2 2
(0, 0, S) FLFLF
(0, 1, W) FRF
```
**Output**
```
Rover Number: 1
(0, 0, S) LOST
(1, 0, N) FOUND
Rover Number: 2
(0, 1, W) LOST
```
