import java.util.*;
/**
*   Program to move one or more robots
*   Input 
*   4 8
*   (2, 3, E) LFRFF
*   (0, 2, N) FFLFRFF
*   Output
*   (4, 4, E)
*   (0, 4, W) LOST
*/
class Rovers{

    public int numOfRover = 0;
    public int[] x = new int[2];
    public int[] y = new int[2];
    public char[] direction = new char[2];

    private static int yAxis;
    private static int xAxis;
    public static String[] commands = new String[2];

    public Rovers(){ }

    public static void main(String args[]) {
        Rovers marsRover = new Rovers();

        // Get user inputs
        marsRover.getGridSize();
        while (marsRover.numOfRover<2) {
            marsRover.getRoverPositionAndCommands();
            marsRover.numOfRover++;
        }
        // Get and print Rovers final position
        marsRover.numOfRover=0;
        while(marsRover.numOfRover<2){
            System.out.print("Rover Number: "+ (marsRover.numOfRover+1) + "\n");
            // Loop through given commands and process them
            for(int i = 0; i < commands[marsRover.numOfRover].length(); i++){
                char command = commands[marsRover.numOfRover].charAt(i);
                marsRover.processCommand(command);
            }

            // Print Final Position
            marsRover.printPosition();
            marsRover.numOfRover++;
        }
    }
    /**
    *   Read inputs (Grid size) from scanner
    */
    public void getGridSize(){
        Scanner sc = new Scanner(System.in);

        // Create the grid based on a given input size <x y>
        System.out.print("Enter Grid Size : ");

        String gridSize = sc.nextLine();
        this.xAxis = Integer.valueOf(gridSize.substring(0,1));
        this.yAxis = Integer.valueOf(gridSize.substring(2,3));
        validateGridSize(this.xAxis, this.yAxis);
    }
    /**
    *   Read inputs (Rover initial position - Commands) from scanner
    */
    public void getRoverPositionAndCommands(){
        Scanner sc = new Scanner(System.in);

        // Set starting position and commands <(x, y, direction) COMMANDS>
        System.out.print("\nEnter Starting Position and commands : ");

        String start = sc.nextLine();
        x[numOfRover] = Integer.valueOf(start.substring(1,2));
        y[numOfRover] = Integer.valueOf(start.substring(4,5));
        direction[numOfRover] =  start.substring(7,8).charAt(0);
        commands[numOfRover] = start.substring(10);
    }
    /**
    *   Check the validity of the grid size
    */
    public void validateGridSize(int xAxis, int yAxis){
        if(xAxis == 0 || yAxis == 0){
            throw new IllegalArgumentException("Grid Size must be greater than 0!");
        }
    }

    /**
    *   Identify type of command and call relevant method to process it
    */
    public void processCommand(char command) {
        if(command == 'F'){
            moveRover();
        }else if(command == 'L'){
            turnLeft();
        }else if(command == 'R'){
            turnRight();
        }else{
            throw new IllegalArgumentException("Unknown command: '" + command + ". The input must follow the form: (x, y, N) FLR ");
        }
    }
    /**
    *   Rotate rover Left based on current direction
    */
    public void turnLeft() {
        if(this.direction[numOfRover] == 'N'){
           this.direction[numOfRover] = 'W';
        }else if(this.direction[numOfRover] == 'S'){
           this.direction[numOfRover] = 'E';
        }else if(this.direction[numOfRover] == 'E'){
           this.direction[numOfRover] = 'N';
        }else if(this.direction[numOfRover] == 'W'){
           this.direction[numOfRover] = 'S';
        }
    }
    /**
    *   Rotate rover Right based on current direction
    */
    public void turnRight() {
        if(this.direction[numOfRover] == 'N'){
           this.direction[numOfRover] = 'E';
        }else if(this.direction[numOfRover] == 'S'){
           this.direction[numOfRover] = 'W';
        }else if(this.direction[numOfRover] == 'E'){
           this.direction[numOfRover] = 'S';
        }else if(this.direction[numOfRover] == 'W'){
           this.direction[numOfRover] = 'N';
        }
    }
    /**
    *   Move rover forward and
    *   Print last visible position if rover moves outside the grid
    */
    public void moveRover() {
        if(this.direction[numOfRover] == 'E'){
            this.x[numOfRover]++;
            if(this.x[numOfRover] > xAxis){
                printPosition(this.x[numOfRover]-1,y[numOfRover],direction[numOfRover], "LOST");
            }
        }else if(this.direction[numOfRover] == 'N'){
            this.y[numOfRover]++;
            if(this.y[numOfRover] > yAxis){
                printPosition(this.x[numOfRover],this.y[numOfRover]-1,direction[numOfRover], "LOST");
            }
        }else if(this.direction[numOfRover] == 'S'){
            this.y[numOfRover]--;
            if(this.y[numOfRover] < 0){
                printPosition(this.x[numOfRover],y[numOfRover]+1,direction[numOfRover], "LOST");
            }
        }else if(this.direction[numOfRover] == 'W'){
            this.x[numOfRover]--;
            if(this.x[numOfRover] < 0){
                printPosition(this.x[numOfRover]+1,y[numOfRover],direction[numOfRover], "LOST");
            }
        }
    }
    /**
    *   Print current position if rover is in the grid
    */
    public void printPosition(){
        // Print position only if it's in the grid
        if(!(x[numOfRover] < 0 || x[numOfRover] > xAxis || y[numOfRover] < 0 || y[numOfRover]> yAxis)){
            printPosition(x[numOfRover],y[numOfRover],direction[numOfRover], "FOUND");
        }
    }
    /**
    *   Print position given current position
    */
    public void printPosition(int xValue, int yValue, char currentDirection, String lost){
        System.out.println("("+xValue+", "+yValue+", "+currentDirection+") "+ lost);
    }
}