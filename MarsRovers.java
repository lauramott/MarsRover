import java.util.*;
/**
*   Program to move one or more robots
*   Input 
*   4 8
*   (2, 3, E) LFRFF
*   Output
*   (4, 4, E)
*/
class MarsRovers{

    int x = 0;;
    int y = 0;
    char direction = 'W';

    private static int yAxis;
    private static int xAxis;
    private static String commands;

    public MarsRovers(){ }

    public static void main(String args[]) {
        MarsRovers marsRover = new MarsRovers();
        // Get user inputs
        marsRover.getGridSize();
        Integer j = 0;
        while (j<2) {
            marsRover.getRoverPositionAndCommands();

            // Loop through given commands and process them
            for(int i = 0; i < commands.length(); i++){
                char command = commands.charAt(i);
                marsRover.processCommand(command);
            }

            // Print Final Position
            marsRover.printPosition();
            j++;
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
        x = Integer.valueOf(start.substring(1,2));
        y = Integer.valueOf(start.substring(4,5));
        direction = start.substring(7,8).charAt(0);
        // If there are no commands - returns the initial position
        try{
            commands = start.substring(10);
        }catch(Exception e){
            validateCommands(commands);
        }

        setPosition(x,y,direction);
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
    *   Check the validity of given commands
    */
    public void validateCommands(String commands){
        if(commands==null){
            printPosition(x,y,direction, "");
            throw new IllegalArgumentException("No Commands Found");
        }
    }
    /**
    *   Set initial rover position
    */
    public void setPosition(int x, int y, char direction){
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    /**
    *   Identify type of command and call relevant method to process it
    */
    public void processCommand(char command) {
        if(command=='F'){
            moveRover();
        }else if(command=='L'){
            turnLeft();
        }else if(command=='R'){
            turnRight();
        }else{
            throw new IllegalArgumentException("Unknown command: '" + command + ". The input must follow the form: (x, y, N) FLR ");
        }
    }
    /**
    *   Rotate rover Left based on current direction
    */
    public void turnLeft() {
        if(this.direction=='N'){
           this.direction='W';
        }else if(this.direction=='S'){
           this.direction='E';
        }else if(this.direction=='E'){
           this.direction='N';
        }else if(this.direction=='W'){
           this.direction='S';
        }
    }
    /**
    *   Rotate rover Right based on current direction
    */
    public void turnRight() {
        if(this.direction=='N'){
           this.direction='E';
        }else if(this.direction=='S'){
           this.direction='W';
        }else if(this.direction=='E'){
           this.direction='S';
        }else if(this.direction=='W'){
           this.direction='N';
        }
    }
    /**
    *   Move rover forward and
    *   Print last visible position if rover moves outside the grid
    */
    public void moveRover() {
        if(this.direction=='E'){
            this.x++;
            if(this.x>xAxis){
                printPosition(this.x-1,y,direction, "LOST");
            }
        }else if(this.direction=='N'){
            this.y++;
            if(this.y>yAxis){
                printPosition(this.x,y-1,direction, "LOST");
            }
        }else if(this.direction=='S'){
            this.y--;
            if(this.y<0){
                printPosition(this.x,y+1,direction, "LOST");
            }
        }else if(this.direction=='W'){
            this.x--;
            if(this.x<0){
                printPosition(this.x+1,y,direction, "LOST");
            }
        }
    }
    /**
    *   Print current position if rover is in the grid
    */
    public void printPosition(){
        // Print position only if it's in the grid
        if(!(x<0 || x>xAxis || y<0 || y>yAxis)){
            printPosition(x,y,direction, "FOUND");
        }
    }
    /**
    *   Print position given current position
    */
    public void printPosition(int xValue, int yValue, char currentDirection, String lost){
        System.out.println("("+xValue+", "+yValue+", "+currentDirection+") "+ lost);
    }
}